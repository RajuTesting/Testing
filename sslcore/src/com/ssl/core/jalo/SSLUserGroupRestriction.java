package com.ssl.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.security.PrincipalGroup;
import de.hybris.platform.jalo.type.ComposedType;

import org.apache.log4j.Logger;

public class SSLUserGroupRestriction extends GeneratedSSLUserGroupRestriction
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger( SSLUserGroupRestriction.class.getName() );
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem( ctx, type, allAttributes );
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}

    @Override
    protected boolean isFulfilledInternal(AbstractOrder order)
    {
      for(PrincipalGroup group:getUserGroups())
      {
        if(order.getUser().isMemberOf(group))
        {
            return true;
        }
      }
        return false;
    }

    @Override
    protected boolean isFulfilledInternal(Product arg0)
    {
        // YTODO Auto-generated method stub
        return false;
    }
	
}
