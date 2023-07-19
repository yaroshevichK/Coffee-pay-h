package it.academy.service;

import it.academy.dto.ProductDto;
import it.academy.models.pageable.Pageable;

import java.util.HashMap;
import java.util.List;

public interface IProductService {
    Pageable<ProductDto> getPageableRecords(Integer pageSize,
                                            Integer pageNumber,
                                            HashMap<String, Object> searchFields,
                                            String sortField);

    boolean createProduct(ProductDto productDto);

    ProductDto findProductById(Integer id);

    boolean updateProduct(ProductDto productDto);

    boolean deleteProductById(Integer id);

    List<ProductDto> findAllProducts();

    List<ProductDto> getMachineProducts(Integer machineId);
}
