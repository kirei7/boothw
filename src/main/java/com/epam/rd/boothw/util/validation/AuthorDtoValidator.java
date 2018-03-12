package com.epam.rd.boothw.util.validation;

import com.epam.rd.boothw.dto.AuthorDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorDtoValidator implements Validator {

    private final Pattern regex;

    public AuthorDtoValidator(String pattern) {
        regex = Pattern.compile(pattern);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(AuthorDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AuthorDto target = (AuthorDto) o;
        Matcher matcher = regex.matcher(target.getName());
        if (!matcher.matches())
            errors.rejectValue("name", "pattern mismatch");
    }
}
