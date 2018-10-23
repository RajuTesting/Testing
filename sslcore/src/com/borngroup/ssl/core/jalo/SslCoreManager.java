package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class SslCoreManager extends GeneratedSslCoreManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( SslCoreManager.class.getName() );
	
	public static final SslCoreManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (SslCoreManager) em.getExtension(SslCoreConstants.EXTENSIONNAME);
	}
	
}
