package Utils;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsPhoneValitator implements ConstraintValidator<IsPhone,String> {

    private boolean required = false;

    @Override
    public void initialize(IsPhone constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return isMobile(s);
        }else {
            if (StringUtils.isEmpty(s)){
                return true;
            }else {
                return isMobile(s);
            }
        }
    }

    private  boolean isMobile(String str){
        Pattern mobile_pattern = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
        if (StringUtils.isEmpty(str)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(str);
        return m.matches();
    }
}
