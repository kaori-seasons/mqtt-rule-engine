package com.bifromq.mqtt.core.filter;

public interface SQLFilter {
	public void doFilter(SQLRequest request, SQLFilterChain chain);
}
