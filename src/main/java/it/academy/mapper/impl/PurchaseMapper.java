package it.academy.mapper.impl;

import it.academy.dto.CreditCardDto;
import it.academy.dto.CustomerDto;
import it.academy.dto.DiscountDto;
import it.academy.dto.MachineDto;
import it.academy.dto.ProductDto;
import it.academy.dto.PurchaseDto;
import it.academy.dto.TypePaymentDto;
import it.academy.mapper.IMapper;
import it.academy.models.CreditCard;
import it.academy.models.Customer;
import it.academy.models.Discount;
import it.academy.models.Machine;
import it.academy.models.Product;
import it.academy.models.Purchase;
import it.academy.models.TypePayment;

import java.util.Optional;

public class PurchaseMapper implements IMapper<Purchase, PurchaseDto> {
    IMapper<Customer, CustomerDto> customerMapper = new CustomerMapper();
    IMapper<Discount, DiscountDto> discountMapper = new DiscountMapper();
    IMapper<Machine, MachineDto> machineMapper = new MachineMapper();
    IMapper<Product, ProductDto> productMapper = new ProductMapper();
    IMapper<CreditCard, CreditCardDto> creditCardMapper = new CreditCardMapper();
    IMapper<TypePayment, TypePaymentDto> typePaymentMapper = new TypePaymentMapper();

    @Override
    public Purchase dtoToEntity(PurchaseDto purchaseDto) {
        Customer customer = Optional.ofNullable(purchaseDto.getCustomer())
                .map(customerMapper::dtoToEntity)
                .orElse(null);

        Discount discount = Optional.ofNullable(purchaseDto.getDiscount())
                .map(discountMapper::dtoToEntity)
                .orElse(null);

        Machine machine = Optional.ofNullable(purchaseDto.getMachine())
                .map(machineMapper::dtoToEntity)
                .orElse(null);

        Product product = Optional.ofNullable(purchaseDto.getProduct())
                .map(productMapper::dtoToEntity)
                .orElse(null);

        CreditCard creditCard = Optional.ofNullable(purchaseDto.getCreditCard())
                .map(creditCardMapper::dtoToEntity)
                .orElse(null);

        TypePayment typePayment = Optional.ofNullable(purchaseDto.getTypePayment())
                .map(typePaymentMapper::dtoToEntity)
                .orElse(null);

        return Purchase.builder()
                .id(purchaseDto.getId())
                .price(purchaseDto.getPrice())
                .amount(purchaseDto.getAmount())
                .customer(customer)
                .discount(discount)
                .machine(machine)
                .product(product)
                .creditCard(creditCard)
                .typePayment(typePayment)
                .build();
    }

    @Override
    public PurchaseDto entityToDto(Purchase purchase) {
        CustomerDto customer = Optional.ofNullable(purchase.getCustomer())
                .map(customerMapper::entityToDto)
                .orElse(null);

        DiscountDto discount = Optional.ofNullable(purchase.getDiscount())
                .map(discountMapper::entityToDto)
                .orElse(null);

        MachineDto machine = Optional.ofNullable(purchase.getMachine())
                .map(machineMapper::entityToDto)
                .orElse(null);

        ProductDto product = Optional.ofNullable(purchase.getProduct())
                .map(productMapper::entityToDto)
                .orElse(null);

        CreditCardDto creditCard = Optional.ofNullable(purchase.getCreditCard())
                .map(creditCardMapper::entityToDto)
                .orElse(null);

        TypePaymentDto typePayment = Optional.ofNullable(purchase.getTypePayment())
                .map(typePaymentMapper::entityToDto)
                .orElse(null);

        return PurchaseDto.builder()
                .createDate(purchase.getCreateDate())
                .id(purchase.getId())
                .price(purchase.getPrice())
                .amount(purchase.getAmount())
                .customer(customer)
                .discount(discount)
                .machine(machine)
                .product(product)
                .creditCard(creditCard)
                .typePayment(typePayment)
                .build();
    }
}
