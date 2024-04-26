package com.bifromq.mqtt.task.impl;

import com.bifromq.mqtt.task.TaskFilter;
import com.bifromq.mqtt.task.TaskFilterChain;
import com.bifromq.mqtt.task.TaskRequest;
import com.bifromq.mqtt.task.TaskResponse;
import com.github.ltsopensource.core.cluster.Node;
import com.github.ltsopensource.core.commons.utils.StringUtils;
import com.github.ltsopensource.core.listener.MasterChangeListener;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.spring.boot.annotation.MasterNodeListener;

/**
 * json数据过滤器
 * @author zouping on 2018.08.24
 */
public class DataFilterImpl implements TaskFilter {
 
	@Override
	public void doFilter(TaskRequest request, TaskResponse response, TaskFilterChain chain) {
		response.responseStr = request.getRequestStr().replace("<", "[")
				.replace(">", "]");
		chain.doFilter(request, response, chain);
	}
}