package com.epam.rd.boothw.dto;

public interface DtoMapper<D, O> {
    D dtoFromObject(O object);
    O objectFromDto(D dto);
}
