package it.academy.service.impl;

import it.academy.dto.ProductDto;
import it.academy.mapper.IMapper;
import it.academy.mapper.impl.PageableMapper;
import it.academy.mapper.impl.ProductMapper;
import it.academy.models.Product;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IProductRepository;
import it.academy.repositories.impl.ProductRepository;
import it.academy.service.IProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static it.academy.utils.constants.DataUI.STRING_NULL;

public class ProductService implements IProductService {
    private final IProductRepository productRepository = new ProductRepository();

    private final IMapper<Product, ProductDto> productMapper = new ProductMapper();
    private final IMapper<Pageable<Product>, Pageable<ProductDto>> pageableMapper
            = new PageableMapper<>(productMapper);

    @Override
    public Pageable<ProductDto> getPageableRecords(Integer pageSize,
                                                   Integer pageNumber,
                                                   HashMap<String, Object> searchFields,
                                                   String sortField) {
        Pageable<Product> pageable = productRepository
                .getPageableRecords(pageSize,
                        pageNumber,
                        searchFields,
                        STRING_NULL.equals(sortField) ? null : sortField);

        return pageableMapper.entityToDto(pageable);
    }

    @Override
    public boolean createProduct(ProductDto productDto) {
        Product product = productMapper.dtoToEntity(productDto);
        return productRepository.save(product);
    }

    @Override
    public ProductDto findProductById(Integer id) {
        Product product = productRepository.findById(id);
        return productMapper.entityToDto(product);
    }

    @Override
    public boolean updateProduct(ProductDto productDto) {
        Product product = productMapper.dtoToEntity(productDto);
        return productRepository.update(product);
    }

    @Override
    public boolean deleteProductById(Integer id) {
        return productRepository.delete(id);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> productList = productRepository.findAll();
        return Optional.ofNullable(productList)
                .orElse(new ArrayList<>())
                .stream()
                .map(productMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getMachineProducts(Integer machineId) {
        List<Product> productList = productRepository.getMachineProducts(machineId);

        return Optional.ofNullable(productList)
                .orElse(new ArrayList<>())
                .stream()
                .map(productMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
