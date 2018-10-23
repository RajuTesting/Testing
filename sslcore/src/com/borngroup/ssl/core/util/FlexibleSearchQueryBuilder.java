/**
 *
 */
package com.borngroup.ssl.core.util;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;


/**
 * Helper to build a flexible search query object. For example<br />
 * <code>
 * FlexibleSearchQuery query = new FlexibleSearchQueryBuilder().from(ProductModel._TYPECODE).limit(20).build();
 * </code>
 */
public class FlexibleSearchQueryBuilder
{
    private static final Logger       LOG         = Logger.getLogger(FlexibleSearchQueryBuilder.class);
    private static final String       CODE_FORMAT = "{%s:%s}";
    private static final String STRIMG_FUNCTION_CODE_FORMAT = "%s({%s:%s})";
    private String                    fromType;
    private String                    fromCode;
    private int                       limit;
    private boolean                   hasLimit;
    private int                       page;
    private boolean                   hasPage;
    private boolean                   distinctSelect;
    private String                    orderClause;
    private final List<String>        joinTypes;
    private final List<String>        whereClauses;
    private final List<String>        selectClauses;
    private final Map<String, Object> params;

    /**
     * Ensures that each variable is uniquely defined in the prepared statement parameters. This adds a 0 to the end to
     * "var" to ensure uniqueness. Keeps adding 0s until a unique variable is found.
     *
     * @param var
     *            The variable to use for prepared parameter
     * @param value
     *            The value of the parameter
     * @return The actually inserted parameter
     */
    private <T> String checkParam(final String var, final T value)
    {
        if (params.containsKey(var))
        {
            return checkParam(var + '0', value);
        }
        if (value instanceof Boolean)
        {
            final Boolean boolValue = (Boolean) value;
            if (boolValue.equals(Boolean.TRUE))
            {
                params.put(var, Integer.valueOf(1));
            }
            else
            {
                params.put(var, Integer.valueOf(0));
            }
        }
        else
        {
            params.put(var, value);
        }
        return var;
    }

    /**
     * @param value
     * @return a flexible search query compatible "TO_DATE" format.
     */
    private String getDateFormat(final long value)
    {
        final Date date = new Date();
        date.setTime(value);
        final String dateParam = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return "TO_DATE('" + dateParam + "', 'YYYY-MM-DD HH24:MI:SS')";
    }

    /**
     * Creates a {code:var} formatted data to use for flexible query
     *
     * @param code
     * @param var
     * @return the formatted data
     */
    private String param(final String code, final String var)
    {
        return String.format(CODE_FORMAT, code, var);
    }

    public enum Functions
    {
        AVG, MAX, MIN;
    }

    public enum JoinType
    {
        LEFT, LEFT_OUTER, INNER, RIGHT, RIGHT_OUTER
    }

    /**
     * Constructs a new builder with initialized values.
     */
    public FlexibleSearchQueryBuilder()
    {
        fromType = "";
        fromCode = "";
        joinTypes = new ArrayList<String>();
        whereClauses = new ArrayList<String>();
        selectClauses = new ArrayList<String>();
        params = new HashMap<String, Object>();
        limit = -1;
        hasLimit = false;
        orderClause = "";
        distinctSelect = false;
    }

    /**
     * Adds a select clause field to the query.
     *
     * @param code
     *            The code of the item to use for the field
     * @param column
     *            The field of the item
     * @return This builder
     */
    public FlexibleSearchQueryBuilder select(final String code, final String column)
    {
        selectClauses.add(param(code, column));
        return this;
    }

    /**
     * Adds a select clause field to the query with the required function. Example
     * selectFunction(FulfillmentQueryBuilder.Functions.AVG, "c", "reviews"); will add a avg({c.reviews}) to the select
     * clause.
     *
     * @param func
     *            The function to add
     * @param code
     *            The code of the item to use for the field
     * @param column
     *            The field of the item
     * @return This builder
     */
    public FlexibleSearchQueryBuilder selectFunction(final Functions func, final String code, final String column)
    {
        selectClauses.add(func.toString() + "(" + param(code, column) + ")");
        return this;
    }

    /**
     * Adds a distinct select to the query.
     *
     * @return This builder
     */
    public FlexibleSearchQueryBuilder selectDistinct()
    {
        distinctSelect = true;
        return this;
    }

    /**
     * Adds a from clause to the query. This is the only requirement for the builder. Call to this method must be made
     * before the build call otherwise there will be no flexible query built.
     *
     * @param item
     *            The item type to use
     * @param code
     *            The code to use
     * @return This builder
     */
    public FlexibleSearchQueryBuilder from(final String item, final String code)
    {
        fromType = item + " AS " + code;
        fromCode = code;
        return this;
    }

    /**
     * Adds a join clause to the query. The join happens with the originating "from" type with this item's PK field.
     *
     * @param item
     *            The item to join with
     * @param code
     *            The code of this item
     * @param sourceField
     *            The source field of the from type to join with
     * @return This builder
     */
    public FlexibleSearchQueryBuilder join(final String item, final String code, final String sourceField)
    {
        return join(item, "PK", code, sourceField, fromCode, JoinType.INNER);
    }

    /**
     * Adds a join clause to the query based on the join type. Join happens with the originating "from" type with this
     * item's PK field.
     *
     * @param item
     *            The item to join with
     * @param code
     *            The code of this item
     * @param sourceField
     *            The source field of the from type to join with
     * @param joinType
     *            The join type to use.
     * @return This builder
     */
    public FlexibleSearchQueryBuilder join(final String item, final String code, final String sourceField,
            final JoinType joinType)
    {
        return join(item, "PK", code, sourceField, fromCode, joinType);
    }

    /**
     * Adds a join clause to the query based on the join type. Join happens with the provided source code type with this
     * item's PK field.
     *
     * @param item
     *            The item to join with
     * @param code
     *            The code of this item
     * @param sourceField
     *            The source field of the from type to join with
     * @param sourceCode
     *            The source item's code
     * @return This builder
     */
    public FlexibleSearchQueryBuilder join(final String item, final String code, final String sourceField,
            final String sourceCode)
    {
        return join(item, "PK", code, sourceField, sourceCode, JoinType.INNER);
    }

    /**
     * Adds a join clause to the query based on the join type. Join happens with the provided source code type with this
     * item's PK field.
     *
     * @param item
     *            The item to join with
     * @param code
     *            The code of this item
     * @param sourceField
     *            The source field of the from type to join with
     * @param sourceCode
     *            The source item's code
     * @param joinType
     *            The join type to use.
     * @return This builder
     */
    public FlexibleSearchQueryBuilder join(final String item, final String code, final String sourceField,
            final String sourceCode, final JoinType joinType)
    {
        return join(item, "PK", code, sourceField, sourceCode, JoinType.INNER);
    }

    /**
     * Adds a join clause to the query based on the join type. Join happens with the provided source code type with this
     * item's PK field.
     *
     * @param item
     *            The item to join with
     * @param code
     *            The code of this item
     * @param sourceField
     *            The source field of the from type to join with
     * @param sourceCode
     *            The source item's code
     * @param joinType
     *            The join type to use.
     * @return This builder
     */
    public FlexibleSearchQueryBuilder join(final String item, final String field, final String code, final String sourceField,
            final String sourceCode, final JoinType joinType)
    {
        String join = "";
        switch (joinType)
        {
            case LEFT:
                join = " LEFT ";
                break;
            case LEFT_OUTER:
                join = " LEFT OUTER ";
                break;
            case RIGHT:
                join = " RIGHT ";
                break;
            case RIGHT_OUTER:
                join = " RIGHT OUTER ";
                break;
            case INNER:
            default:
                break;
        }
        joinTypes
                .add(join + " JOIN " + item + " AS " + code + " ON " + param(sourceCode, sourceField) + "=" + param(code, field));
        return this;
    }

    /**
     * Adds a where {var} CONTAINS {value} to the query
     *
     * @param code
     *            The code of the item to use for the variable
     * @param var
     *            The field of the item
     * @param value
     *            The value of the item
     * @return This builder
     */
    public <T> FlexibleSearchQueryBuilder whereContains(final String code, final String var, final T value)
    {
        if (value != null)
        {
            if (value instanceof String)
            {
                return whereLike(code, var, value);
            }
            else
            {
                return whereEquals(code, var, value);
            }
        }
        return this;
    }

    /**
     * Adds a where {var} IS NOT NULL to the query
     *
     * @param code
     *            the code of the item to use for the variable
     * @param var
     *            the field of the item
     * @return this builder
     */
    public FlexibleSearchQueryBuilder whereNotNull(final String code, final String var)
    {
        whereClauses.add(param(code, var) + " IS NOT NULL");
        return this;
    }

    /**
     * Adds a where {var} IS NULL to the query
     *
     * @param code
     *            the code of the item to use for the variable
     * @param var
     *            the field of the item
     * @return this builder
     */
    public FlexibleSearchQueryBuilder whereNull(final String code, final String var)
    {
        whereClauses.add(param(code, var) + " IS NULL");
        return this;
    }

    /**
     * Adds a where {var} = {value} to the query
     *
     * @param code
     *            The code of the item to use for the variable
     * @param var
     *            The field of the item
     * @param value
     *            The value of the item
     * @return This builder
     */
    public <T> FlexibleSearchQueryBuilder whereEquals(final String code, final String var, final T value)
    {
        if (value != null)
        {
            final String var1 = checkParam(var, value);
            whereClauses.add(param(code, var) + "=?" + var1);
        }
        return this;
    }

    /**
     * Adds a where LOWER({var}) = LOWER({value}) to the query
     *
     * @param code
     *            The code of the item to use for the variable
     * @param var
     *            The field of the item
     * @param value
     *            The value of the item
     * @return This builder
     */
    public <T> FlexibleSearchQueryBuilder whereEqualsIgnoreCase(final String code, final String var, final T value)
    {
        if (value != null)
        {
            final String var1 = checkParam(var, value);
            whereClauses.add("LOWER(" + param(code, var) + ")=LOWER(?" + var1 + ")");
        }
        return this;
    }

    /**
     * Adds a where ({var} = {value1} OR {var} = {value2}) to the query
     *
     * @param code
     *            The code of the item to use for the variable
     * @param var
     *            The field of the item
     * @param value1
     *            The first value of the item
     * @param value2
     *            The second value of the item
     * @return This builder
     */
    public <T> FlexibleSearchQueryBuilder whereEqualsOr(final String code, final String var, final T value1, final T value2)
    {
        if (value1 != null && value2 != null)
        {
            final String var1 = checkParam(var, value1);
            final String var2 = checkParam(var, value2);
            final String p = param(code, var);
            whereClauses.add("(" + p + "=?" + var1 + " OR " + p + "=?" + var2 + ")");
        }
        return this;
    }

    /**
     * Adds a where {var} like {value} to the query
     *
     * @param code
     *            The code of the item to use for the variable
     * @param var
     *            The field of the item
     * @param value
     *            The value of the item
     * @return This builder
     */
    public <T> FlexibleSearchQueryBuilder whereLike(final String code, final String var, final T value)
    {
        if (value != null)
        {
            if (value instanceof String)
            {
                final String sValue = (String) value;
                whereClauses.add(param(code, var) + " LIKE CONCAT('%', CONCAT('" + sValue + "', '%'))");
            }
            else
            {
                final String var1 = checkParam(var, value);
                whereClauses.add(param(code, var) + " LIKE ?" + var1);
            }
        }
        return this;
    }

    /**
     * Adds a where {var} <= {value} to the query
     *
     * @param code
     *            The code of the item to use for the variable
     * @param var
     *            The field of the item
     * @param value
     *            The value of the item
     * @return This builder
     */
    public <T> FlexibleSearchQueryBuilder whereLessThanEquals(final String code, final String var, final T value)
    {
        if (value != null)
        {
            final String var1 = checkParam(var, value);
            whereClauses.add(param(code, var) + "<=?" + var1);
        }
        return this;
    }

    /**
     * Adds a where {var} <= TO_DATE({value}, '--date format---') to the query
     *
     * @param code
     *            The code of the item to use for the variable
     * @param var
     *            The field of the item
     * @param value
     *            The timestamp value, 0 if none available.
     * @return This builder
     */
    public FlexibleSearchQueryBuilder whereLessThanEqualsDate(final String code, final String var, final long value)
    {
        if (value > 0)
        {
            //final String var1 = checkParam(var, getDateValue(value));
            whereClauses.add(param(code, var) + "<=" + getDateFormat(value));
        }
        return this;
    }

    /**
     * Adds a where {var} >= {value} to the query
     *
     * @param code
     *            The code to use
     * @param var
     *            The column name
     * @param value
     *            The value
     * @return This builder
     */
    public <T> FlexibleSearchQueryBuilder whereMoreThanEquals(final String code, final String var, final T value)
    {
        if (value != null)
        {
            final String var1 = checkParam(var, value);
            whereClauses.add(param(code, var) + ">=?" + var1);
        }
        return this;
    }

    /**
     * Adds a where {code1:var1} >= {value1} OR {code2:var2} >= {value2} to the query
     *
     * @param code1
     *            The code to use
     * @param var1
     *            The column name
     * @param value1
     *            The value
     * @param code2
     *            The code to use
     * @param var2
     *            The column name
     * @param value2
     *            The value
     * @return This builder
     */
    public <T, V> FlexibleSearchQueryBuilder whereMoreThanEqualsOr(final String code1, final String var1, final T value1,
            final String code2, final String var2, final V value2)
    {
        if (value1 != null && value2 != null)
        {
            final String var11 = checkParam(var1, value1);
            final String var21 = checkParam(var2, value2);
            whereClauses.add("(" + param(code1, var1) + ">=?" + var11 + " OR " + param(code2, var2) + ">=?" + var21 + ")");
        }
        return this;
    }

    /**
     * Adds a where {var} >= TO_DATE({value}, '--date format---') to the query
     *
     * @param code
     *            The code of the item to use for the variable
     * @param var
     *            The field of the item
     * @param value
     *            The timestamp value, 0 if none available.
     * @return This builder
     */
    public FlexibleSearchQueryBuilder whereMoreThanEqualsDate(final String code, final String var, final long value)
    {
        if (value > 0)
        {
            //final String var1 = checkParam(var, getDateValue(value));
            whereClauses.add(param(code, var) + ">=" + getDateFormat(value));
        }
        return this;
    }

    /**
     * Adds a where {var} in {values} to the query.
     *
     * @param code
     *            The code of the item to use
     * @param var
     *            The field of the item to use
     * @param values
     *            The value set to use
     * @return This builder
     */
    public <T> FlexibleSearchQueryBuilder whereIn(final String code, final String var, final Set<T> values)
    {
        if (values != null)
        {
            final String var1 = checkParam(var, values);
            whereClauses.add(param(code, var) + " IN (?" + var1 + ")");
        }
        return this;
    }

    /**
     * Adds a where {var} NOT in {values} to the query.
     *
     * @param code
     *            The code of the item to use
     * @param var
     *            The field of the item to use
     * @param values
     *            The value set to use
     * @return This builder
     */
    public <T> FlexibleSearchQueryBuilder whereNotIn(final String code, final String var, final Set<T> values)
    {
        if (values != null)
        {
            final String var1 = checkParam(var, values);
            whereClauses.add(param(code, var) + " NOT IN (?" + var1 + ")");
        }
        return this;
    }

    /**
     * Adds a where {var} in {values} to the query.
     *
     * @param code
     *            The code of the item to use
     * @param var
     *            The field of the item to use
     * @param values
     *            The value set to use
     * @return This builder
     */
    public <T> FlexibleSearchQueryBuilder whereInOrNull(final String code, final String var, final Set<T> values)
    {
        if (values != null)
        {
            final String var1 = checkParam(var, values);
            whereClauses.add(param(code, var) + " IN (?" + var1 + ") OR " + param(code, var) + " IS NULL");
        }
        return this;
    }

    /**
     * @param code
     *            The code of the item to use
     * @param var1
     *            The first field of the item to use
     * @param values1
     *            The first field value set to use
     * @param var2
     *            The second field of the item to use
     * @param values2
     *            The second field value set to use
     * @return This builder
     */
    public <T, Q> FlexibleSearchQueryBuilder whereInOr(final String code, final String var1, final Set<T> values1,
            final String var2, final Set<T> values2)
    {
        if (values1 != null && values2 != null)
        {
            final String paramVar1 = checkParam(var1, values1);
            final String paramVar2 = checkParam(var2, values2);
            whereClauses.add("((" + param(code, var1) + " IN (?" + paramVar1 + ")) OR (" + param(code, var2) + " IN (?"
                    + paramVar2 + ")))");
        }
        return this;
    }

    /**
     * Adds a limit to the query so that the actual query will be selected with a limited row count
     *
     * @param count
     *            The number to limit this query with
     * @return This builder
     */
    public FlexibleSearchQueryBuilder limit(final int count)
    {
        if (count > 0)
        {
            limit = count;
            hasLimit = true;
        }
        else
        {
            limit = -1;
            hasLimit = false;
        }
        return this;
    }

    /**
     * Adds a page number to the query to fetch a particular page from the limited set of rows.
     *
     * @param page
     *            the page number starting with 1
     * @return this flexible search query builder
     */
    public FlexibleSearchQueryBuilder page(final int page)
    {
        if (page > 0)
        {
            this.page = page;
            this.hasPage = true;
            if (!hasLimit)
            {
                // We will have to set a limit, if there is none
                // and a page is requested.
                return limit(10);
            }
        }
        else
        {
            this.page = -1;
            this.hasPage = false;
        }
        return this;
    }

    /**
     * Adds the order by clause to the query. The resultant will be in the form of "order by {code.sort} order"
     *
     * @param code
     *            The code of the item to use for the sort order
     * @param sort
     *            The field of the item to use for the sort order
     * @param order
     *            The sort order, true if ascending, false for descending
     * @return This builder
     */
    public FlexibleSearchQueryBuilder order(final String code, final String sort, final boolean order)
    {
        orderClause = " ORDER BY " + param(code, sort) + " " + (order ? "ASC" : "DESC");
        return this;
    }

    /**
     * Builds the flexible search query and returns that object for use. This requires at least one from clause. If
     * there was no from clause provided, this will return null.
     *
     * @return null if no query can be built, otherwise a built flexible search query
     */
    public FlexibleSearchQuery build()
    {
        if (fromCode == null || fromCode.isEmpty())
        {
            return null;
        }
        final StringBuilder query = new StringBuilder();
        // Select ...
        query.append("SELECT");
        if (distinctSelect)
        {
            query.append(" DISTINCT");
        }
        if (selectClauses.isEmpty())
        {
            query.append(" *");
        }
        else
        {
            final Iterator<String> it = selectClauses.iterator();
            while (it.hasNext())
            {
                query.append(" ").append(it.next());
                if (it.hasNext())
                {
                    query.append(",");
                }
            }
        }
        // from ... join ...
        query.append(" FROM {").append(fromType);
        if (!joinTypes.isEmpty())
        {
            for (final String join : joinTypes)
            {
                query.append(join);
            }
        }
        query.append("}");
        // where ... and ...
        if (!whereClauses.isEmpty())
        {
            query.append(" WHERE ");
            final Iterator<String> it = whereClauses.iterator();
            while (it.hasNext())
            {
                query.append(it.next());
                if (it.hasNext())
                {
                    query.append(" AND ");
                }
            }
        }
        // order by ...
        if (orderClause != null && !orderClause.isEmpty())
        {
            query.append(orderClause);
        }
        final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery(query.toString(), params);
        // limits.
        if (hasLimit)
        {
            flexibleQuery.setCount(limit);
        }
        // page #.
        if (hasPage)
        {
            int start = (page - 1) * limit;
            start = start > 0 ? start : 0;
            flexibleQuery.setStart(start);
        }
        flexibleQuery.setNeedTotal(true);
        LOG.debug(flexibleQuery);
        return flexibleQuery;
    }
    
    /**
	 * Adds a where {var} in {values} to the query.
	 *
	 * @param methodName
	 *            DB function name
	 * @param code
	 *            The code of the item to use
	 * @param var
	 *            The field of the item to use
	 * @param values
	 *            The value set to use
	 * @return This builder
	 */
	public <T> FlexibleSearchQueryBuilder whereMethod(final String methodName, final String code, final String var,
			final Set<T> values) {
		if (values != null) {
			final String var1 = checkParam(var, values);
			whereClauses.add(methodParam(methodName, code, var) + " IN (?" + var1 + ")");
		}
		return this;
	}
	
	/**
	 * Creates a TRIM({code:var}) formatted data to use for flexible query
	 * @param method
	 *            DB function name
	 * @param code
	 * @param var
	 * @return the formatted data
	 */
	private String methodParam(final String method, final String code, final String var) {
		return String.format(STRIMG_FUNCTION_CODE_FORMAT, method, code, var);
	}
}
