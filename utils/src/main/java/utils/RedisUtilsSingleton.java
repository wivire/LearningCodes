package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 基于RedisUtils 将其改写为单例模式
 * <p/>
 * 待改进：如何传入一个int值，用来选择redis库
 */


public class RedisUtilsSingleton {

    private static final String host = "192.168.1.235";
    private static final int port = 9999;
    private Jedis jedis;

    //实现单例模式的思路是：一个类能返回对象一个引用(永远是同一个)和一个获得该实例的方法（必须是静态方法，通常使用getInstance这个名称）

    private static RedisUtilsSingleton singleRedisUtils = null;

    private RedisUtilsSingleton() {
    }

    public static RedisUtilsSingleton getSingleReidsUtils() {
        if (singleRedisUtils == null) {
            synchronized (RedisUtilsSingleton.class) {
                if (singleRedisUtils == null) {
                    singleRedisUtils = new RedisUtilsSingleton();
                }
            }
        }
        return singleRedisUtils;
    }


    public void setjedis(int dbNum) {
        jedis = new Jedis(host, port, 3000);
        jedis.select(dbNum);
    }

    /**
     * 创建连接
     */
    private Jedis getJedis() {

        return jedis;
    }

    /**
     * Put 数据
     *
     * @param keys
     */
    public void piplinePut(List<String> keys) {
        Pipeline pipelined = getJedis().pipelined();

        for (String key : keys) {
            pipelined.setnx("device-" + key, "0");
        }
        pipelined.sync();

        try {
            pipelined.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Object> piplinePutNx(Set<String> keys) {
        Pipeline pipelined = getJedis().pipelined();

        for (String key : keys) {
            pipelined.setnx("device-" + key, "0");
        }
        return pipelined.syncAndReturnAll();
    }

    /**
     * Get 数据
     *
     * @param keys
     */
    public List<Object> piplineGet(Set<String> keys) {
        Pipeline pipelined = getJedis().pipelined();

        for (String key : keys) {
            pipelined.get("device-" + key);
        }

        List<Object> objects = pipelined.syncAndReturnAll();

        try {
            pipelined.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objects;
    }

    /**
     * 每来一个key,就将redis中对应key的值加1
     */
    public List<Object> piplineIncrease(Set<String> keys) {
        Pipeline pipelined = getJedis().pipelined();

        for (String key : keys) {
            pipelined.incrBy(key, 1);
        }

        List<Object> objects = pipelined.syncAndReturnAll();

        try {
            pipelined.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objects;
    }

    /**
     * 关闭
     */
    public void close() {
        jedis.close();
    }

    /**
     * 清库中所有数据
     */
    public void clear() {

        Jedis conn = getJedis();
        String all = conn.flushAll();

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        RedisUtilsSingleton redis = new RedisUtilsSingleton();
        Jedis conn = redis.getJedis();
        conn.set("name", "xinxin");
        System.out.println(conn.get("name"));

//        List<String> keys = new ArrayList<String>();
//        keys.add("test");
//
//        utils.RedisUtils redis = new utils.RedisUtils();
//
//        redis.piplinePut(keys);
//
//        List<Object> values = redis.piplineGet(Sets.newHashSet("test1"));
//        for (Object value : values) {
//            if (null == value) {
//                System.err.println("No Exists.");
//            } else {
//                System.err.println("Value = " + value);
//            }
//        }
//
//        /**
//         * 清库存
//         */
//        redis.clear();
//
//        redis.close();
    }


}
