package com.epam.rd.boothw.util.validation;

import com.epam.rd.boothw.entity.Author;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorValidator implements Validator {

    private final Pattern regex;

    public AuthorValidator(String pattern) {
        regex = Pattern.compile(pattern);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Author.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Author target = (Author) o;
        Matcher matcher = regex.matcher(target.getName());
        if (!matcher.matches())
            errors.rejectValue("name", "pattern mismatch");
    }
}
