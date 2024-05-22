package com.bifromq.mqtt.task.impl;


import com.bifromq.mqtt.task.TaskFilter;
import com.bifromq.mqtt.task.TaskFilterChain;
import com.bifromq.mqtt.task.TaskRequest;
import com.bifromq.mqtt.task.TaskResponse;

/**
 * 数据过滤器
 * 将数据写入到数据库
 */
public class DBTaskImpl implements TaskFilter {
 
	@Override
	public void doFilter(TaskRequest request, TaskResponse response, TaskFilterChain chain) {
		//TODO 调用数据写入接口
		request.getParams();

		chain.doFilter(request, response, chain);
	}
}