package Utils;


import Entity.GlobalException;
import com.mysql.cj.util.StringUtils;
import org.hibernate.validator.HibernateValidator;

import javax.el.Expression;
import javax.el.ExpressionFactory;
import javax.swing.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();


    public static <T> void validate(JFrame jFrame,T obj){
        Set<ConstraintViolation<T>> set = validator.validate(obj,Default.class);
        if(set != null && set.size() >0 ){
            System.out.println(set.size());
            throw new GlobalException(jFrame,set.iterator().next().getMessage());
        }
    }
}
