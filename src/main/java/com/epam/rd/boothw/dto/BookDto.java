package com.epam.rd.boothw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class BookDto {
    private String title;
    private String authorName;
}
