package it.academy.service.impl;

import it.academy.dto.CreditCardDto;
import it.academy.mapper.IMapper;
import it.academy.mapper.impl.CreditCardMapper;
import it.academy.models.CreditCard;
import it.academy.repositories.ICreditCardRepository;
import it.academy.repositories.ICustomerRepository;
import it.academy.repositories.impl.CreditCardRepository;
import it.academy.repositories.impl.CustomerRepository;
import it.academy.service.ICreditCardService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreditCardService implements ICreditCardService {
    private final IMapper<CreditCard, CreditCardDto> creditCardMapper = new CreditCardMapper();
    private final ICreditCardRepository creditCardRepository = new CreditCardRepository();
    private final ICustomerRepository customerRepository = new CustomerRepository();

    @Override
    public List<CreditCardDto> findCreditCardByUsername(String username) {
        List<CreditCard> creditCards = creditCardRepository.findCreditCardByUsername(username);
        return Optional.ofNullable(creditCards)
                .orElse(new ArrayList<>())
                .stream()
                .map(creditCardMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean createCreditCard(String username, String number) {
        return creditCardRepository.createCreditCard(username, number);
    }

    @Override
    public CreditCardDto findCreditCardById(Integer id) {
        CreditCard creditCard = creditCardRepository.findById(id);
        return creditCardMapper.entityToDto(creditCard);
    }

    @Override
    public boolean updateCreditCard(CreditCardDto creditCardDto, Integer customerId) {
        CreditCard creditCard = creditCardMapper.dtoToEntity(creditCardDto);
        return creditCardRepository.updateCreditCard(creditCard, customerId);
    }

    @Override
    public boolean deleteCreditCardById(Integer creditCardId) {
        return creditCardRepository.delete(creditCardId);
    }

    @Override
    public List<CreditCardDto> findAllCreditCards() {
        List<CreditCard> creditCards = creditCardRepository.findAll();
        return Optional.ofNullable(creditCards)
                .orElse(new ArrayList<>())
                .stream()
                .map(creditCardMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
