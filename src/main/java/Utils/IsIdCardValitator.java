package Utils;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsIdCardValitator implements ConstraintValidator<IsIdCard,String> {
    private boolean required = false;

    @Override
    public void initialize(IsIdCard constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return isIdCard(s);
        }else {
            if (StringUtils.isEmpty(s)){
                return true;
            }else {
                return isIdCard(s);
            }
        }
    }

    private  boolean isIdCard(String str){
        Pattern mobile_pattern = Pattern.compile("/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/");
        if (StringUtils.isEmpty(str)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(str);
        return m.matches();
    }
}
