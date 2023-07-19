package it.academy.service;

import it.academy.dto.CreditCardDto;

import java.util.List;

public interface ICreditCardService {
    List<CreditCardDto> findCreditCardByUsername(String username);

    boolean createCreditCard(String username, String number);

    CreditCardDto findCreditCardById(Integer id);

    boolean updateCreditCard(CreditCardDto creditCard, Integer customerId);

    boolean deleteCreditCardById(Integer creditCardId);

    List<CreditCardDto> findAllCreditCards();
}

