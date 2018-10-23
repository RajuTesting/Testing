/**
 *
 */
package com.borngroup.ssl.core.data.enums;

import com.borngroup.ssl.core.data.SslCoreCommonEnum;


/**
 * @author t.balagopalan
 *
 */
public enum ResponseCodeEnum implements SslCoreCommonEnum
{
	SUCCESS("200"), FAILURE("0"), ESBSUCCESSCODE("1");

    private final String name;

    private ResponseCodeEnum(final String value)
    {
        name = value;
    }

    /*
    * (non-Javadoc)
    *
    * @see
    * com.borngroup.ssl.ccavenuepg.data.api.CcavenuepgEnum#equalsName(java.lang.
    * String)
    */
    @Override
    public boolean equalsName(final String otherName)
    {
        return (otherName == null) ? false : name.equals(otherName);
    }

    @Override
    public String toString()
    {
        return name;
    }
}
