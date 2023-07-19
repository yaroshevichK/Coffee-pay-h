package it.academy.converter;

import javax.servlet.http.HttpServletRequest;

public interface IConverter<T> {
    T convertToDto(HttpServletRequest request);
}
