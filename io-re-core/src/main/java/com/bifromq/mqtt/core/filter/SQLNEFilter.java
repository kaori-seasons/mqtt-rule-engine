package com.bifromq.mqtt.core.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * !=过滤器
 */
public class SQLNEFilter extends AbstractSQLFilter{
    @Override
    public void doFilter(SQLRequest request, SQLFilterChain chain) {
        SQLCondition sqlCondition = getSQLCondition();
        JSONArray origin = request.getOrigin();
        JSONArray target = request.getTarget();
        //and 过滤
        if(sqlCondition.getJoin().equals(SQLCondition.SQL_JOIN_AND)){
            for(int i=0; i<target.size(); i++){
                JSONObject obj = target.getJSONObject(i);
                for(Map.Entry<String,Object> entry : obj.entrySet()){
                    if(sqlCondition.getJoinCondition().getKey().equals(entry.getKey())){
                          System.out.println("过滤!=" + entry.getValue() + "的数据");
//                        if(entry.getValue() < sqlFilter.getJoinFilter().getValue()){//保留
//                            target.remove(i);
//                        }
                    }
                }
            }
        }
        chain.doFilter(request, chain);
    }
}
