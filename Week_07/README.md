学习笔记

## 拉取 MySQL 容器镜像

```
docker pull mysql:5.7
```

## 创建主数据库容器

```
docker run -itd --name mysql-master -p 3308:3307 -v /etc/docker/mysql/master/:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=123456 mysql:5.7
```

## 创建从数据库容器

```
docker run -itd --name mysql-slave -p 3308:3308 -v /etc/docker/mysql/slave/:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=123456 mysql:5.7
```

docker run --name mysql-slave -p 3308:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
这里我们分别使用了 3307 和 3308 端口，因为我们本地宿主机器上已经跑了 MySQL 了，3306 端口被占用。

## 配置主数据库

```
docker exec -it mysql-master /bin/bash 
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456'
```

mysql> show master status;
+------------------+----------+--------------+------------------+-------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+------------------+----------+--------------+------------------+-------------------+
| mysql-bin.000003 |      154 |              |                  |                   |
+------------------+----------+--------------+------------------+-------------------+
1 row in set (0.00 sec)

## 接下来连接 slave 服务器，配置主从复制：

mysql>change master to
master_host='x.x.x.x',
master_user='user',
master_log_file='mysql-bin.000003',
master_log_pos=154,
master_port=3307,
master_password='123456';
mysql> start slave;
mysql>show slave status\G

```
Slave_IO_Running: Yes
Slave_SQL_Running: Yes
```

