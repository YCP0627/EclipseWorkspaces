package UI;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class Password {
    public static String encryption(String password){
        return DigestUtils.md5Hex(("yami" + password).getBytes());
    }


}
