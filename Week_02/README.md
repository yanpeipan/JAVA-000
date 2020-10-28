1. 使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。

```
-XX:+UseSerialGC = Serial New (DefNew) + Serial Old

-XX:+UseParNewGC = ParNew + SerialOld

-XX:+UseConc(urrent)MarkSweepGC = ParNew + CMS + Serial Old

-XX:+UseParallelGC = Parallel Scavenge + Parallel Old (1.8默认) 

-XX:+UseParallelOldGC = Parallel Scavenge + Parallel Old

-XX:+UseG1GC = G1


java8 -XX:+UseSerialGC  -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -classpath target/classes/ com.demo.GCLogAnalysis
```

* 随机函数导致结果波动很大
* 输出日志对结果影响较大
* -Xms -Xmx 设置一样，性能会有小幅提升，减少GC次数？
* 随着内存增加，G1无明显变化（略微提升)，其他GC，性能先升后降（3G左右）
* 存在直接进入老年代问题，10g内存老年代使用率为0，GC次数为0
* -XX:MaxTenuringThreshold对 UseParallelGC 无效...

2. 使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例

```
siege -c 100 -r 10 http://localhost:8808/test

wrk -c 100 http://localhost:8808/test
```

* 效率相差不大
* 多次运行，会有较长时间卡顿（1818.18 -> 181.82 trans/sec，初步怀疑是siege导致
* wrk性能高很多
