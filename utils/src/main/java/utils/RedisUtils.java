package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @Author Rayn
 * @Vendor liuwei412552703@163.com
 * Created by Rayn on 2017/2/6 15:54.
 */


public class RedisUtils {

    private static final String host = "192.168.1.235";
    private static final int port = 9999;

    private Jedis jedis;


    /**
     * 创建连接
     */
    private Jedis conn() {
        jedis = new Jedis(host, port, 3000);
        jedis.select(0);
        return jedis;
    }

    /**
     * Put 数据
     *
     * @param keys
     */
    public void piplinePut(List<String> keys) {
        Pipeline pipelined = conn().pipelined();

        for (String key : keys) {
            pipelined.setnx(key, "0");
        }
        pipelined.sync();

        try {
            pipelined.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Object> piplinePutNx(Set<String> keys) {
        Pipeline pipelined = conn().pipelined();

        for (String key : keys) {
            pipelined.setnx(key, "0");
        }
        return pipelined.syncAndReturnAll();
    }

    /**
     * Get 数据
     *
     * @param keys
     */
    public List<Object> piplineGet(Set<String> keys) {
        Pipeline pipelined = conn().pipelined();

        for (String key : keys) {
            pipelined.get(key);
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

        Jedis conn = conn();
        String all = conn.flushAll();

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        RedisUtils redis = new RedisUtils();
        Jedis conn = redis.conn();
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
