package Utils;

import redis.clients.jedis.Jedis;

public class LoginInfo {
    private String loginName;
    private String loginStyle;
    private static volatile LoginInfo loginInfo;
    private Jedis jedis;

    private LoginInfo() {
        RedisUtil redisUtil = new RedisUtil();
        jedis = redisUtil.redisPoolFactory().getResource();
    }

    public static LoginInfo getInstace(){
        if (loginInfo == null){
            synchronized (LoginInfo.class){
                if (loginInfo == null){
                    loginInfo = new LoginInfo();
                    return loginInfo;
                }
            }
        }
        return loginInfo;
    }
}
