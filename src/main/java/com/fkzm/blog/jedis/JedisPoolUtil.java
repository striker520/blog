package com.fkzm.blog.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    private static volatile JedisPool jedisPool=null;
    private JedisPoolUtil(){}
    public static JedisPool getJedisPool(){
        if(jedisPool==null){
            synchronized (JedisPool.class){
                if(jedisPool==null){
                    JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(1000);
                    jedisPoolConfig.setMaxIdle(32);
                    jedisPoolConfig.setMaxWaitMillis(100*1000);
                    jedisPoolConfig.setTestOnBorrow(true);
                    jedisPool=new JedisPool(jedisPoolConfig,"127.0.0.1",6379);
                }
            }
        }
        return jedisPool;
    }
    public static void release(JedisPool jedisPool, Jedis jedis){
        if(jedis!=null){
            jedisPool.returnResourceObject(jedis);
        }
    }
}
