package Utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsIdCardValitator.class)
@Documented
public @interface IsIdCard {
    boolean required() default true;

    String message() default "身份证号码格式不对";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
