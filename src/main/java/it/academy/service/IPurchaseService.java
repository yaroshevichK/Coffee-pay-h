package it.academy.service;

import it.academy.dto.PurchaseDto;
import it.academy.models.pageable.Pageable;

import java.util.HashMap;

public interface IPurchaseService {
    Pageable<PurchaseDto> getPageableRecords(Integer pageSize,
                                             Integer pageNumber,
                                             HashMap<String, Object> searchFields,
                                             String sortField);

    boolean createPurchase(PurchaseDto purchaseDto,
                           Integer customerId,
                           Integer discountId,
                           Integer machineId,
                           Integer productId,
                           Integer creditCardId,
                           Integer typePaymentId);

    PurchaseDto findPurchaseById(Integer id);

    boolean updatePurchase(PurchaseDto purchaseDto,
                           Integer customerId,
                           Integer discountId,
                           Integer machineId,
                           Integer productId,
                           Integer creditCardId,
                           Integer typePaymentId);

    boolean deletePurchaseById(Integer id);

    Float getSumPurchases(String username);
}
