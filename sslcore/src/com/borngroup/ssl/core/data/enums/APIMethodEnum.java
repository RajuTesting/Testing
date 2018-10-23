/**
 *
 */
package com.borngroup.ssl.core.data.enums;

import com.borngroup.ssl.core.data.SslCoreCommonEnum;


/**
 * @author t.balagopalan
 *
 */
public enum APIMethodEnum implements SslCoreCommonEnum
{
    GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE");

    private final String name;

    private APIMethodEnum(final String value)
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
