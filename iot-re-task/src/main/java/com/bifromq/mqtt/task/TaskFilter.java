package com.bifromq.mqtt.task;

public interface TaskFilter {
	public void doFilter(TaskRequest request, TaskResponse response, TaskFilterChain chain);
}
