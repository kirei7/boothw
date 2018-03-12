package com.epam.rd.boothw.util.validation;

import com.epam.rd.boothw.dto.AuthorDto;
import com.epam.rd.boothw.dto.BookDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookDtoValidator implements Validator {

    private final Pattern regex;

    public BookDtoValidator(String pattern) {
        regex = Pattern.compile(pattern);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(BookDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BookDto target = (BookDto) o;
        Matcher matcher = regex.matcher(target.getTitle());
        if (!matcher.matches())
            errors.rejectValue("title", "pattern mismatch");
    }

}
