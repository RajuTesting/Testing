/**
 * 
 */
package com.borngroup.ssl.core.dao;

import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;


/**
 * @author dien.nguyen
 */
public interface SSLEmailAttachmentDao
{
	EmailAttachmentModel findByCode(String code);
}
