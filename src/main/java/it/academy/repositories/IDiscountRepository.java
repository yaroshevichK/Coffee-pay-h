package it.academy.repositories;

import it.academy.models.Discount;

public interface IDiscountRepository extends ICrudRepository<Discount> {
    Discount getPercentDiscount(Float amount);
}
