package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public enum RedisUtils {
    INSTANCE;
    static JedisPool jedisPool = null;
    static int redisDbIndex = 0;

    static {
        //1 创建连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //2 默认配置
        config.setMaxIdle(1);
        config.setMaxTotal(11);
        config.setMaxWaitMillis(10 * 1000l);
        config.setTestOnBorrow(true);
        //3 通过配置对象创建连接池对象
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(RedisUtils.class.getClassLoader().getResourceAsStream("redis.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String host = properties.getProperty("redis.host");
        String port = properties.getProperty("redis.port");
        String dbIndex = properties.getProperty("redis.dbIndex");
        if (dbIndex != null && dbIndex.length() > 0) {
            try {
                redisDbIndex = Integer.valueOf(dbIndex);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
//        String password = properties.getProperty("redis.password");
//        String timeout = properties.getProperty("redis.timeout");
//        jedisPool = new JedisPool(config, host, Integer.valueOf(port), Integer.valueOf(timeout), password);
        jedisPool = new JedisPool(config, host, Integer.valueOf(port));
    }

    //获取连接
    public Jedis getSource() {
        Jedis resource = jedisPool.getResource();
        resource.select(redisDbIndex);
        return resource;
    }

    //关闭资源
    public void closeSource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * hash操作字符串==============================================
     */
    public List<String> hvals(String key) {
        Jedis jedis = getSource();
        List<String> hvals = jedis.hvals(key);
        closeSource(jedis);
        return hvals;
    }

    public Set<String> hkeys(String key) {
        Jedis jedis = getSource();
        Set<String> hkeys = jedis.hkeys(key);
        closeSource(jedis);
        return hkeys;
    }

    public Long hset(String key, String field, String value) {
        Jedis jedis = getSource();
        Long result = jedis.hset(key, field, value);
        closeSource(jedis);
        return result;
    }

    public String hmset(String key, Map<String, String> data) {
        Jedis jedis = getSource();
        String result = jedis.hmset(key, data);
        closeSource(jedis);
        return result;
    }

    public String hget(String key, String field) {
        Jedis jedis = getSource();
        String value = jedis.hget(key, field);
        closeSource(jedis);
        return value;
    }

    /**
     * hash操作byte[]==============================================
     */
    public List<byte[]> hvals(byte[] key) {
        Jedis jedis = getSource();
        List<byte[]> hvals = jedis.hvals(key);
        closeSource(jedis);
        return hvals;
    }

    public Set<byte[]> hkeys(byte[] key) {
        Jedis jedis = getSource();
        Set<byte[]> hkeys = jedis.hkeys(key);
        closeSource(jedis);
        return hkeys;
    }

    public Long hset(byte[] key, byte[] field, byte[] value) {
        Jedis jedis = getSource();
        Long result = jedis.hset(key, field, value);
        closeSource(jedis);
        return result;
    }

    public String hmset(byte[] key, Map<byte[], byte[]> data) {
        Jedis jedis = getSource();
        String result = jedis.hmset(key, data);
        closeSource(jedis);
        return result;
    }

    public byte[] hget(byte[] key, byte[] field) {
        Jedis jedis = getSource();
        byte[] value = jedis.hget(key, field);
        closeSource(jedis);
        return value;
    }

    /**
     * 删除key
     */
    public boolean del(String key) {
        Jedis jedis = getSource();
        long result = jedis.del(key);
        closeSource(jedis);
        return result > 0;
    }

    /**
     * 设置字符值
     *
     * @param key
     * @param value :value是字符串，如果要存储对象，转成JSON字符串在存储
     */
    public void set(String key, String value) {
        Jedis jedis = getSource();
        jedis.set(key, value);
        closeSource(jedis);
    }

    /**
     * 设置
     *
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        Jedis jedis = getSource();
        jedis.set(key, value);
        closeSource(jedis);
    }

    /**
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        Jedis jedis = getSource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSource(jedis);
        }
        return null;
    }

    /**
     * 设置字符值
     *
     * @param key
     * @return ：返回的是JSON格式的字符串 ，考虑转对象
     */
    public String get(String key) {
        Jedis jedis = getSource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSource(jedis);
        }
        return null;
    }
}
