/**
 *
 */
package com.borngroup.ssl.core.jalo;

import de.hybris.platform.core.GenericCondition;
import de.hybris.platform.core.GenericSearchField;
import de.hybris.platform.core.Operator;

import java.util.Map;

/**
 * @author shilpiverma
 *
 */
public abstract class SSLGenericCondition extends GenericCondition {

    /**
     * @param operator
     */
    public SSLGenericCondition(final Operator operator) {
        super(operator);
        // YTODO Auto-generated constructor stub
    }

    private Operator operator;

    @Override
    protected abstract void checkOperator();

    @Override
    public abstract Map getResettableValues();

    @Override
    public abstract void setResettableValue(String paramString, Object paramObject);

    @Override
    public void setOperator(final Operator operator) {
        this.operator = operator;
        checkOperator();
    }

    @Override
    public Operator getOperator() {
        return this.operator;
    }

    public static final GenericCondition caseInSensitive(final String fieldQualifier, final Object value) {
        return caseInSensitiveEquals(new GenericSearchField(fieldQualifier), value);
    }

    public static final GenericCondition caseInSensitiveEquals(final GenericSearchField field, final Object value) {
        return createConditionForValueComparison(field, Operator.EQUAL, value, true);
    }

}
