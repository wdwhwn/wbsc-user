package com.jingzhun.wbsc.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
    private static JedisPool pool = null;

    static {
            /*创建数据库连接池
			 * 设定连接池的连接数
			 * 100 为连接数
			 * 192.168.85.137 为jedis所在系统ip
			 * */
        GenericObjectPoolConfig poolConfig = new JedisPoolConfig();
			/*poolConfig.setMaxTotal(100);*/
        pool = new JedisPool(poolConfig, "127.0.0.1");
//			pool=new JedisPool(poolConfig, "211.149.140.247");
    }

    public static Jedis getJedis() {
        if (pool != null) {
            Jedis jedis = pool.getResource();
            return jedis;
        }
        return null;
    }

    public static void close(Jedis jedis) {
        if (pool != null) {
            jedis.close();
        }
    }
}
