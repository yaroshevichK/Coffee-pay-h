package it.academy.repositories.impl;

import it.academy.models.TypePayment;
import it.academy.repositories.ITypePaymentRepository;

import static it.academy.utils.constants.DataGeneral.TYPE_PAYMENT_CLASS;

public class TypePaymentRepository extends CrudRepository<TypePayment>
        implements ITypePaymentRepository {
    public TypePaymentRepository() {
        super(TYPE_PAYMENT_CLASS);
    }

}
