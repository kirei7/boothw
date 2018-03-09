package com.epam.rd.boothw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorDto {
    private String name;
    private String[] bookTitles;
}
