package it.academy.service;

import it.academy.dto.TypePaymentDto;

import java.util.List;

public interface ITypePaymentService {
    boolean createTypePayment(TypePaymentDto typePaymentDto);

    TypePaymentDto findTypePaymentById(Integer id);

    boolean updateTypePayment(TypePaymentDto typePaymentDto);

    boolean deleteTypePaymentById(Integer id);

    List<TypePaymentDto> findAllTypePayments();
}
