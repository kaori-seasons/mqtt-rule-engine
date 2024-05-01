package com.bifromq.mqtt.job;

import com.bifromq.mqtt.core.RuleEngineJobDetail;
import com.bifromq.mqtt.task.TaskFilter;
import com.bifromq.mqtt.task.TaskFilterChain;
import com.bifromq.mqtt.task.TaskRequest;
import com.bifromq.mqtt.task.TaskResponse;
import com.bifromq.mqtt.task.impl.DataFilterImpl;
import com.bifromq.mqtt.task.impl.KafkaFilterImpl;
import com.bifromq.mqtt.task.impl.TSDBFilterImpl;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;

import java.util.Map;

public class MockMQTTJobImpl implements JobRunner {

    @Override
    public Result run(JobContext jobContext) throws Throwable {
        String tenantID = jobContext.getJob().getParam(RuleEngineJobDetail.TENANT_ID);
        String jobTask = jobContext.getJob().getParam(RuleEngineJobDetail.JOB_TASK);
        Map<String,String> jobParams = jobContext.getJob().getExtParams();

        //TODO 获取Mqtt任务
        while(true){
            //模拟数据，1秒钟发一次
            Thread.currentThread().sleep(1000);
            String requestData = "{equipmentId:10000,data:[" +
                    "{temp:'35.6', kw: '52.5'}," +
                    "{temp:'42.6', kw: '100.5'}," +
                    "]}";
            TaskRequest request = new TaskRequest();
            request.setTenantID(tenantID);
            request.setRequestStr(requestData);
            request.setParams(jobParams);

            TaskResponse response = new TaskResponse();
            TaskFilterChain filterChain = new TaskFilterChain();
            String[] tasks = jobTask.split(",");
            for(String task : tasks){
                if(task.equals(RuleEngineJobDetail.TASK_DATA)){
                    TaskFilter dataFilter = new DataFilterImpl();
                    filterChain.addFilter(dataFilter);
                }else if(task.equals(RuleEngineJobDetail.TASK_TSDB)){
                    TaskFilter tsdbFilter = new TSDBFilterImpl();
                    filterChain.addFilter(tsdbFilter);
                }else if(task.equals(RuleEngineJobDetail.TASK_KAFKA)){
                    TaskFilter kafkaFilter = new KafkaFilterImpl();
                    filterChain.addFilter(kafkaFilter);
                }
            }
            filterChain.doFilter(request, response, filterChain);
        }
    }
}

