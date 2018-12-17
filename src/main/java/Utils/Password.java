package Utils;

import com.mysql.cj.util.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

public class Password {
    public static String encryption(String password){
        if (StringUtils.isEmptyOrWhitespaceOnly(password)){
            return "";
        }
        return DigestUtils.md5Hex(("yami" + password).getBytes());
    }
}
