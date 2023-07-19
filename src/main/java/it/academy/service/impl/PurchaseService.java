package it.academy.service.impl;

import it.academy.dto.PurchaseDto;
import it.academy.mapper.IMapper;
import it.academy.mapper.impl.PageableMapper;
import it.academy.mapper.impl.PurchaseMapper;
import it.academy.models.Purchase;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IPurchaseRepository;
import it.academy.repositories.impl.PurchaseRepository;
import it.academy.service.IPurchaseService;

import java.util.HashMap;
import java.util.Optional;

public class PurchaseService implements IPurchaseService {
    private final IPurchaseRepository purchaseRepository = new PurchaseRepository();

    private final IMapper<Purchase, PurchaseDto> purchaseMapper = new PurchaseMapper();
    private final IMapper<Pageable<Purchase>, Pageable<PurchaseDto>> pageableMapper
            = new PageableMapper<>(purchaseMapper);

    @Override
    public Pageable<PurchaseDto> getPageableRecords(Integer pageSize,
                                                    Integer pageNumber,
                                                    HashMap<String, Object> searchFields,
                                                    String sortField) {
        Pageable<Purchase> pageable = purchaseRepository
                .getPageableRecords(pageSize,
                        pageNumber,
                        searchFields,
                        sortField);

        return pageableMapper.entityToDto(pageable);
    }

    @Override
    public boolean createPurchase(PurchaseDto purchaseDto,
                                  Integer customerId,
                                  Integer discountId,
                                  Integer machineId,
                                  Integer productId,
                                  Integer creditCardId,
                                  Integer typePaymentId) {
        Purchase purchase = purchaseMapper.dtoToEntity(purchaseDto);
        return purchaseRepository.createPurchase(purchase,
                customerId,
                discountId,
                machineId,
                productId,
                creditCardId,
                typePaymentId);
    }

    @Override
    public PurchaseDto findPurchaseById(Integer id) {
        Purchase purchase = purchaseRepository.findById(id);
        return purchaseMapper.entityToDto(purchase);
    }

    @Override
    public boolean updatePurchase(PurchaseDto purchaseDto,
                                  Integer customerId,
                                  Integer discountId,
                                  Integer machineId,
                                  Integer productId,
                                  Integer creditCardId,
                                  Integer typePaymentId) {
        Purchase purchase = purchaseMapper.dtoToEntity(purchaseDto);
        return purchaseRepository.updatePurchase(purchase,
                customerId,
                discountId,
                machineId,
                productId,
                creditCardId,
                typePaymentId);
    }

    @Override
    public boolean deletePurchaseById(Integer id) {
        return purchaseRepository.delete(id);
    }

    @Override
    public Float getSumPurchases(String username) {
        Float amount = purchaseRepository.getSumPurchases(username);
        return Optional.ofNullable(amount).orElse(0f);
    }
}
