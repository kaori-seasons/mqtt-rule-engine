package com.bifromq.mqtt.core.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL的过滤链
 */
public class SQLFilterChain implements SQLFilter {
	List<SQLFilter> filters = new ArrayList<SQLFilter>();
	int index = 0;

	public SQLFilterChain addFilter(SQLFilter filter) {
		this.filters.add(filter);
		return this;
	}

	@Override
	public void doFilter(SQLRequest request, SQLFilterChain chain) {
		if (index == filters.size())
			return;
		SQLFilter filter = filters.get(index);
		index++;
		filter.doFilter(request, chain);
	}
}
