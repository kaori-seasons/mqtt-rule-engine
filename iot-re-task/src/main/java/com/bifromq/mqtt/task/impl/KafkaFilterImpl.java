package com.bifromq.mqtt.task.impl;

import com.bifromq.mqtt.task.TaskFilter;
import com.bifromq.mqtt.task.TaskFilterChain;
import com.bifromq.mqtt.task.TaskRequest;
import com.bifromq.mqtt.task.TaskResponse;

public class KafkaFilterImpl implements TaskFilter {

    @Override
    public void doFilter(TaskRequest request, TaskResponse response, TaskFilterChain chain) {
        response.responseStr = request.getRequestStr().replace("<", "[")
                .replace(">", "]");
        chain.doFilter(request, response, chain);
    }
}
