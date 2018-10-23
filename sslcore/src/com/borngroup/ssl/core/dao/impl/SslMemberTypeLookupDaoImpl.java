/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.Collections;
import java.util.List;

import com.borngroup.ssl.core.dao.SslMemberTypeLookupDao;
import com.borngroup.ssl.loyalty.model.MemberTypeMappingModel;


/**
 * @author t.balagopalan
 *
 */
public class SslMemberTypeLookupDaoImpl extends DefaultGenericDao<MemberTypeMappingModel>implements SslMemberTypeLookupDao
{

    /**
     * @param typecode
     */
    public SslMemberTypeLookupDaoImpl()
    {
        super("MemberTypeMapping");
        // YTODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslMemberTypeLookupDao#getMemberTypeForInputString(java.lang.String)
     */
    @Override
    public MemberTypeMappingModel getMemberTypeForInputString(final String lookupCode)
    {
        final List<MemberTypeMappingModel> memberType = find(Collections.singletonMap("memberTypeCode", lookupCode));
        return (memberType.iterator().hasNext() ? memberType.iterator().next() : null);
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslMemberTypeLookupDao#getMemberType()
     */
    @Override
    public List<MemberTypeMappingModel> getMemberType()
    {
        return find();
    }

}
