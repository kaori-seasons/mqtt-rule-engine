# mqtt-rule-engine(施工中)
开放原子大赛 - BifroMQ赛题二 - 基于规则引擎的消息分发系统

此系统的核心目标是实现一个“实现中立”的物联网消息转发规则引擎。在架构设计上，系统需要与MQTT Broker保持松耦合的集成方式，充分利用MQTT协议本身的强大功能，实现与不同MQTT Broker之间的灵活对接。

要点概述：

- 系统架构与MQTT Broker松耦合：赛题要求参赛者设计的规则引擎系统不应过度依赖于特定的MQTT Broker实现。这意味着系统应能与各种MQTT Broker兼容，无论是商业产品还是开源项目。

- 依赖MQTT协议本身的能力：参赛者需要展示他们对MQTT协议的深入理解，通过利用MQTT协议的内在机制（如主题过滤、QoS等级、保留消息等）来实现消息的有效转发和处理。

-  物联网消息转发规则的实现：系统应能根据设定的规则，处理和转发来自物联网设备的消息。这些规则可能包括基于特定主题的消息转发、基于消息内容的处理逻辑等。

-  支持不同MQTT Broker的对接：考虑到物联网领域中MQTT Broker的多样性，参赛作品需能适应不同的Broker，实现高效的消息交换和规则执行。

赛题挑战：

- 设计一个高效、灵活的规则引擎，能够处理大量并发消息。

-  确保规则引擎的稳定性和可扩展性，适应不同规模的物联网应用场景。

-  实现对MQTT协议各项功能的深入利用，优化消息处理和转发效率。

### 名词解释

lts任务调度系统: https://github.com/ltsopensource/light-task-scheduler

### 设计架构
![7868787.jpg](https://github.com/kaori-seasons/mqtt-rule-engine/assets/20021404/1dee35e2-be32-4b7c-9ea5-8213385f9764)

 - (1) 结合实际规则引擎具体任务，任务场景如下：
 - (2) 设备数据通过MQTT协议上传至云平台；
- (3) 用户在页面选择MQTT发送地址和过滤规则，封装后调用客户端发送请求；
- (4) 客户端提交任务给JobTracker
- (6) JobTracker分发任务给其中一个节点的TaskTracker
- (7) TaskTracker接受任务后，将任务分配给MQTTJob，MQTTJob负责从MQTT的Topic里面拉取数据；
- (8) 拉取后的数据经过RuleEngineTask规则引擎任务进行处理；

### 规则引擎处理逻辑

RuleEngineTask是规则引擎数据处理的核心类，前面主要负责设备数据如何经过LTS的处理交给不同的节点去处理，最后都是交给每个节点的RuleEngineTask来完成任务， 每个节点上的RuleEngineTask都一样。
![75675.png](https://github.com/kaori-seasons/mqtt-rule-engine/assets/20021404/23a9da25-e734-4d98-8c77-354d09f8fb74)


- (1) RuleEnineTask是一个标准的责任链模式，同样的数据从一条链上穿过，交给不同的数据过滤器去处理；
- (2) ShuffleFilter是典型的数据过滤器，对Json格式的数据进行处理，支持类SQL语句，例如：select f1,f2,f3 from t where tmp>50 and kw<100
- (3) DBFilter 数据库过滤器，将数据分发到不同的数据库，例如：TSDBFilter，分发给时序数据库，DBFilter不支持用户自定义转发规则，Json数据会按照设备分表，每个Json字段对应数据库的表字段。一个设备对应一张表，一个Json对应一个表子段。

