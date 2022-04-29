package com.zhang.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;


public class RedisClusterDemo {

    public static void main(String[] args) {

        HostAndPort hostAndPort = new HostAndPort("8.140.158.200", 6379);
        JedisCluster jedisCluster=new JedisCluster(hostAndPort);
        //进行操作
        jedisCluster.set("b1","value1");
        String b1 = jedisCluster.get("b1");
        System.out.println(b1);
        jedisCluster.close();

    }
}
