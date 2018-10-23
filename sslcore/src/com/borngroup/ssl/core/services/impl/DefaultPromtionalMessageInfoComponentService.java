/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.borngroup.ssl.core.dao.PromotionalMessageInfoComponentdao;
import com.borngroup.ssl.core.model.PromotionalMessageInfoComponentModel;
import com.borngroup.ssl.core.services.PromtionalMessageInfoComponentService;

/**
 * @author guneetsingh
 *
 */
@Service(value = "promtionalMessageInfoComponentService")
public class DefaultPromtionalMessageInfoComponentService implements PromtionalMessageInfoComponentService {

    @Resource(name = "promotionalMessageInfoComponentdao")
    private PromotionalMessageInfoComponentdao promotionalMessageInfoComponentdao;

    @Override
    public PromotionalMessageInfoComponentModel getActiveMessage() {
        final List<PromotionalMessageInfoComponentModel> result = promotionalMessageInfoComponentdao.getActivePromotionalMessage();
        if (result != null) {
            for (final PromotionalMessageInfoComponentModel activeMessage : result) {
                if (activeMessage.getStartDate() != null && activeMessage.getEndDate() != null) {
                    final Instant instant = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
                    final Date currentDate = Date.from(instant);
                    final Date messageEndDate = activeMessage.getEndDate();
                    final Date messageStartDate = activeMessage.getStartDate();
                    if (currentDate.getTime() > messageStartDate.getTime() && currentDate.getTime() < messageEndDate.getTime()) {
                        return activeMessage;
                    }
                }
            }
        }
        return null;
    }
}