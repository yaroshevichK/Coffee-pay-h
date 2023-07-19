package it.academy.repositories;

import it.academy.models.Purchase;

public interface IPurchaseRepository extends ICrudRepository<Purchase> {
    boolean createPurchase(Purchase purchase,
                           Integer customerId,
                           Integer discountId,
                           Integer machineId,
                           Integer productId,
                           Integer creditCardId,
                           Integer typePaymentId);

    boolean updatePurchase(Purchase purchase,
                           Integer customerId,
                           Integer discountId,
                           Integer machineId,
                           Integer productId,
                           Integer creditCardId,
                           Integer typePaymentId);

    Float getSumPurchases(String username);
}
