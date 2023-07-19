package it.academy.repositories;

import it.academy.models.CreditCard;

import java.util.List;

public interface ICreditCardRepository extends ICrudRepository<CreditCard> {
    List<CreditCard> findCreditCardByUsername(String username);

    boolean createCreditCard(String username, String number);

    boolean updateCreditCard(CreditCard creditCard, Integer customerId);
}
