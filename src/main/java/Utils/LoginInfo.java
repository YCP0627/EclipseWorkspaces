package Utils;

import Model.Adminstrator;
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

    public static LoginInfo getInstance(){
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

    public void setAdminInRedis(Adminstrator admin){
        jedis.set("java_admin_name",admin.getName());
        jedis.set("java_admin_style",admin.getStyle());
    }

    public String getLoginName(){
        return jedis.get("java_admin_name");
    }

    public String getLoginStyle(){
        return jedis.get("java_admin_style");
    }
}
