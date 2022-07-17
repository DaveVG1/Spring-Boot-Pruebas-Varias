package com.example.demo.validator;

import com.example.demo.validation.Dni;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.function.Predicate;
import java.util.regex.Pattern;

//https://blog.clairvoyantsoft.com/spring-boot-creating-a-custom-annotation-for-validation-edafbf9a97a4
public class DniValidator implements ConstraintValidator<Dni, String> {

    private static final Pattern DNI_VALIDATOR = Pattern.compile("\\d{8}\\p{Upper}");

    Predicate<String> isDNI = s -> DNI_VALIDATOR.matcher(s).matches();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("DniValidation: " + value);
        return isDNI.test(value);
    }
}
