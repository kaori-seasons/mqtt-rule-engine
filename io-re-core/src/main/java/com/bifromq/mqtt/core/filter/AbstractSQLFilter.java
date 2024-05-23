package com.bifromq.mqtt.core.filter;


public class AbstractSQLFilter implements SQLFilter {
    private SQLCondition sqlCondition;

    @Override
    public void doFilter(SQLRequest request, SQLFilterChain chain) {

    }

    public SQLCondition getSQLCondition() {
        return sqlCondition;
    }

    public void setSQLCondition(SQLCondition sqlCondition) {
        this.sqlCondition = sqlCondition;
    }
}
