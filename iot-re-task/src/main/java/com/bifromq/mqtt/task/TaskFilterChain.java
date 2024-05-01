package com.bifromq.mqtt.task;

import java.util.ArrayList;
import java.util.List;

public class TaskFilterChain implements TaskFilter {
	List<TaskFilter> filters = new ArrayList<TaskFilter>();
	int index = 0;

	public TaskFilterChain addFilter(TaskFilter f) {
		this.filters.add(f);
		return this;
	}

	public void doFilter(TaskRequest request, TaskResponse response, TaskFilterChain chain) {
		if (index == filters.size())
			return;
		TaskFilter f = filters.get(index);
		index++;
		f.doFilter(request, response, chain);
	}
}
