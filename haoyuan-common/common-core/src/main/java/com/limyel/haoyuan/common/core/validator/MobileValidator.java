package com.limyel.haoyuan.common.core.validator;

import com.limyel.haoyuan.common.core.validator.annotation.Mobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<Mobile, String> {

    @Override
    public void initialize(Mobile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(mobile) && mobile.matches("\\d{11}");
    }

}
