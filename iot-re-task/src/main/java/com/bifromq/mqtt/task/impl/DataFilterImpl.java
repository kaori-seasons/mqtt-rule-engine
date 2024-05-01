package com.bifromq.mqtt.task.impl;

import com.bifromq.mqtt.task.TaskFilter;
import com.bifromq.mqtt.task.TaskFilterChain;
import com.bifromq.mqtt.task.TaskRequest;
import com.bifromq.mqtt.task.TaskResponse;
import com.github.ltsopensource.core.cluster.Node;
import com.github.ltsopensource.core.commons.utils.StringUtils;
import com.github.ltsopensource.core.json.JSON;
import com.github.ltsopensource.core.json.JSONArray;
import com.github.ltsopensource.core.json.JSONObject;
import com.github.ltsopensource.core.listener.MasterChangeListener;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.spring.boot.annotation.MasterNodeListener;

public class DataFilterImpl implements TaskFilter {
 
	public void doFilter(TaskRequest request, TaskResponse response, TaskFilterChain chain) {
		JSONObject jsonObject = JSON.parseObject(request.getRequestStr());
		JSONArray data = jsonObject.getJSONArray("data");
		chain.doFilter(request, response, chain);
	}
}