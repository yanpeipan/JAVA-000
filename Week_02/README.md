学习笔记

java -XX:+PrintGCDetails main.GCLogAnalysis

java -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps main.GCLogAnalysis

java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps main.GCLogAnalysis

java -XX:+UseParallelGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps main.GCLogAnalysis

java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps main.GCLogAnalysis