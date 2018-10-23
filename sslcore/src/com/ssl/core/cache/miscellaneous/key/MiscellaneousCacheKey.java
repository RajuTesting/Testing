/**
 *
 */
package com.ssl.core.cache.miscellaneous.key;

import de.hybris.platform.regioncache.key.CacheKey;
import de.hybris.platform.regioncache.key.CacheUnitValueType;

import org.apache.commons.lang.StringUtils;

/**
 * @author Nagarro Dev
 *
 */
public class MiscellaneousCacheKey implements CacheKey {
	private static final String MISCELLANEOUS_CACHE_UNIT_CODE = "Miscellaneous";
	private final String key;
	private final String tenantId;
	private final String typeCode;

	public MiscellaneousCacheKey(final String key, final String tenantId, final String typeCode) {
		this.key = key;
		this.tenantId = tenantId;
		this.typeCode = typeCode;
	}

	@Override
	public Object getTypeCode() {
		return StringUtils.isNotEmpty(typeCode) ? typeCode : MISCELLANEOUS_CACHE_UNIT_CODE;
	}

	@Override
	public String getTenantId() {
		return tenantId;
	}

	@Override
	public CacheUnitValueType getCacheValueType() {
		return CacheUnitValueType.SERIALIZABLE;
	}

	@Override
	public String toString() {
		return "MiscellaneousCacheKey [key=" + key + ", tenantId=" + tenantId + "]";
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + ((key == null) ? 0 : key.hashCode());
		result = 31 * result + ((tenantId == null) ? 0 : tenantId.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (super.getClass() != obj.getClass()) {
			return false;
		}
		final MiscellaneousCacheKey other = (MiscellaneousCacheKey) obj;
		if (tenantId == null) {
			if (other.tenantId != null) {
				return false;
			}
		} else if (!(tenantId.equals(other.tenantId))) {
			return false;
		}
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!(key.equals(other.key))) {
			return false;
		}
		return true;
	}
}