package Utils;

import Entity.AppConfig;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisUtil {
    private String host = AppConfig.REDIS_URL.getValue();
    private int port = Integer.valueOf(AppConfig.REDIS_PORT.getValue());
    private String password = AppConfig.REDIS_PASSWORD.getValue();

    public JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        return new JedisPool(jedisPoolConfig,host,port,3000,password);
    }
}
