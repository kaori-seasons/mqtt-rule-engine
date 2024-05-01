package com.bifromq.mqtt.job;

import com.github.ltsopensource.core.cluster.Node;
import com.github.ltsopensource.core.commons.utils.StringUtils;
import com.github.ltsopensource.core.listener.MasterChangeListener;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.spring.boot.annotation.MasterNodeListener;

@MasterNodeListener
public class MasterNodeChangeListener implements MasterChangeListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterNodeChangeListener.class);

    /**
     * @param master   master节点
     * @param isMaster 表示当前节点是不是master节点
     */
    public void change(Node master, boolean isMaster) {
        // 一个节点组master节点变化后的处理 , 譬如我多个JobClient， 但是有些事情只想只有一个节点能做。
        if (isMaster) {
            LOGGER.info("设置TaskTracker节点组中的master节点了{}", master);
        } else {
            LOGGER.info(StringUtils.format("master节点变成了{}", master));
        }
    }
}
