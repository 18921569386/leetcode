 <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>commons-lang</groupId>
      <artifactId>commons-lang</artifactId>
      <version>2.6</version>
    </dependency>
    <dependency>
      <groupId>cglib</groupId>
      <artifactId>cglib</artifactId>
      <version>3.2.5</version>
    </dependency>
    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.4</version>
    </dependency>
    <dependency>
      <groupId>asm</groupId>
      <artifactId>asm-all</artifactId>
      <version>2.2</version>
    </dependency>
    <dependency>
      <groupId>com.sun.tools.btrace</groupId>
      <artifactId>btrace-client</artifactId>
      <version>1.2</version>
    </dependency>
    <dependency>
      <groupId>org.javassist</groupId>
      <artifactId>javassist</artifactId>
      <version>3.22.0-CR2</version>
    </dependency>


https://github.com/ReactivePlatform/netty-in-action-cn.git

https://segmentfault.com/a/1190000007403873

这些代码的调优原则是：临时对象能改成静态对象进行复用就改成公用对象否则要想方设法缩短其生命周期；高频访问代码提高响应速度。根据 jvm gc 日志发现很多 young gc 之后堆内存已用空间不仅下降反而上升至最大使用量导致 full gc，临时对象如果可以和其它线程复用的话改成静态对象以减少大量线程 local 对象的产生。

缓存基本数据，共用数据到本地， 减少数据库调用 ，与网络传输的延时
可深度定制
当面临更大流量的需求时，通过线性扩容的方法，即可应对

可以考虑卸载流量.比如每十个请求,随机抛弃九个,只放行一个请求到后续处理环节.把秒杀的排序模式,变为随机抽奖的模式.
 三种返回结果途径
执行部件和调用者可以通过三种途径返回结果：
a.状态、
b. 通知、
c.回调函数。


完成处理程序
使用 Future 对象的替代机制，是向异步操作注册一个 callback 。接口 CompletionHandler 有两个方法：
void completed(V result, A attachment) 在任务完成结果中具有类型 V 时执行。
void failed(Throwable e, A attachment) 在任务由于 Throwable e 而失败时执行。
两个方法的附件参数都是一个传递到异步操作的对象。如果相同的对象用于多个操作，其可用于追踪哪个操作已完成。

https://github.com/btraceio/btrace

MapReduce架构的操作模块：
1. 输入流模块:输入模块负责把输入数据分成16MB或者128MB的小数据块，然后把它们传给Map模块
2. Map模块：Map模块得到每一个key/value对，处理后产生一个或多个key/value对。这里的输入key/value对与输出key/value对是不一样的。
3. Partition模块：它用于负责把上面输出的key映射到不同的reduce方法中去
4. comapre模块：对reduce所读入的数据进行比较
5. Reduce模块：对上面已经排好序的并且带有相同key的数据进行迭代计算
6. 输出模块：把reduce的输出结果写到存储系统中去，一般都会用分布式文件系统，如GFS



状态模式和命令模式一样，也可以用于消除 if...else 等条件选择语句。 ： 1、行为随状态改变而改变的场景。 2、条件、分支语句的代替者。+
注意事项：在行为受状态约束的时候使用状态模式，而且状态不超过 5 个。



java中是否存在闭包，怎么实现的。怎么理解 ？


并行程序设计模式主要有Future模式 、Master-Worker模式、Guarded Suspension模式、不变模式和生产者-消费者模式


Reactor模式的组成角色

1. Reactor：负责派发IO事件给对应的角色处理。为了监听IO事件，select必须实现在Reactor中。

2. Acceptor：负责接受client的连线，然后给client绑定一个Handler并注册IO事件到Reactor上监听。

3. Handler：负责处理与client交互的事件或行为。通常因为Handler要处理与所对应client交互的多个事件或行为，为了简化设计，会以状态模式来实现Handler。




NETTY线程模型：
单线程模型:一个线程，负责接收、分发、读写处理
多线程模型：一个线程负责 接收，利用线程池负责分发。提升event的分发能力，读写单线程
主从Reactor多线程模型：
	多线程主从模式运行逻辑：
	客户端连接Main Reactor线程池 ,（负责监听外部的连线请求，并派发给Acceptor处理。故Main Reactor中的selector只有注册OP_ACCEPT事件，也只能监听OP_ACCEPT事件。）
	
	Acceptor接受连线后会给client绑定一个Handler并注册IO事件到Sub Reactor上监听，
	
	对于有多个Sub Reactor的情况下，IO事件选择注册给哪个Sub Reactor则是采用Round-robin的机制来分配。
	
	Sub Reactor：負責监听IO事件，並派发IO事件给Handler处理。Sub Reactor线程的数量可以设置为CPU核心数。

Netty的高效并发编程主要体现在如下几点：
1) volatile的大量、正确使用;
2) CAS和原子类的广泛使用；
3) 线程安全容器的使用；
4) 通过读写锁提升并发性能。
Netty除了使用reactor来提升性能，当然还有
1、零拷贝，IO性能优化
2、通信上的粘包拆包
2、同步的设计
3、高性能的序列




2.2.7. 高性能的序列化框架
影响序列化性能的关键因素总结如下：

1) 序列化后的码流大小（网络带宽的占用）；

2) 序列化&反序列化的性能（CPU资源占用）；

3) 是否支持跨语言（异构系统的对接和开发语言切换）。

Netty默认提供了对Google Protobuf的支持，通过扩展Netty的编解码接口，用户可以实现其它的高性能序列化框架，例如Thrift的压缩二进制编解码框架。

下面我们一起看下不同序列化&反序列化框架序列化后的字