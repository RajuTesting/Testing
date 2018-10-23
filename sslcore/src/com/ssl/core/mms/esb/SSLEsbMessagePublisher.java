/**
 *
 */
package com.ssl.core.mms.esb;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.xml.transform.StringResult;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.fulfilmentprocess.enums.UnprocessedItemType;
import com.borngroup.ssl.fulfilmentprocess.model.UnprocessedItemModel;
import com.ssl.core.mms.jaxb.api.MMSMessagePublishDTO;

/**
 * @author pankajgandhi
 *
 */
@Service("sslEsbMessagePublisher")
public class SSLEsbMessagePublisher {

    @Resource
    private JmsTemplate jmsTemplate;

    @Resource(name = "mmsJaxbMarshaller")
    private Jaxb2Marshaller marshaller;

    @Resource
    private ModelService modelService;

    @Resource
    private ConfigurationService configurationService;

    private static final Logger LOG = Logger.getLogger(SSLEsbMessagePublisher.class);

    private static final String MMS_ENABLED = "mms.esb.enable";

    /**
     * This method will transform the object into string xml message and then send it to ESB. Just make sure, object belogs to the Jaxb.api
     * package, as via spring configuration all the classes under this package are scanned by JAXBContext
     *
     * @param messageDTO
     *        : object to be transformed to xml
     */
    public boolean sendMessageToMMS(final MMSMessagePublishDTO messageDTO) {
        if (!configurationService.getConfiguration().getBoolean(MMS_ENABLED, false)) {
            LOG.info("##### MMS ESB disabled in configuration  #####");
            return true;
        }

        try {
            final StringResult writer = new StringResult();
            marshaller.marshal(messageDTO, writer);
            sendMessage(writer.toString());
        } catch (final Exception e) {
            LOG.error(
                    "############## Sending MMS message to Unprocessed Entry. Exception encountered while sending the request to MMS ESB: ",
                    e);
            final LocalDateTime now = LocalDateTime.now();
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SslCoreConstants.DATE_FORMAT_MMS_TIMESTAMP);
            messageDTO.setDate(now.format(formatter));
            dropUnprocessesItem(messageDTO);
            return false;
        }
        return true;
    }

    private void sendMessage(final String messageText) {
        LOG.info(" ###### Sending message to EMS Queue ###### " + messageText);
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(final Session session) throws JMSException {
                final TextMessage message = session.createTextMessage(messageText);
                return message;
            }
        });
        LOG.info(" ###### Message sent to EMS ######");
    }

    /**
     * Method to put unprocessed item into schema for processing later
     *
     * @param messageDTO
     */
    private void dropUnprocessesItem(final MMSMessagePublishDTO messageDTO) {
        final UnprocessedItemModel unprocessedItemModel = modelService.create(UnprocessedItemModel.class);
        unprocessedItemModel.setItem(UnprocessedItemType.MMS);
        unprocessedItemModel.setTimeInMilli(Long.valueOf(System.currentTimeMillis()));
        unprocessedItemModel.setMmsMessageDto(messageDTO);
        modelService.save(unprocessedItemModel);
    }

}
