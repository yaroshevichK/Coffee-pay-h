package it.academy.mapper.impl;

import it.academy.dto.AddressDto;
import it.academy.dto.MachineDto;
import it.academy.dto.ModelDto;
import it.academy.dto.ProductDto;
import it.academy.mapper.IMapper;
import it.academy.models.Address;
import it.academy.models.Machine;
import it.academy.models.Model;
import it.academy.models.Product;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MachineMapper implements IMapper<Machine, MachineDto> {
    private final IMapper<Address, AddressDto> addressMapper = new AddressMapper();
    private final IMapper<Model, ModelDto> modelMapper = new ModelMapper();
    private final IMapper<Product, ProductDto> productMapper = new ProductMapper();

    @Override
    public Machine dtoToEntity(MachineDto machineDto) {
        Model model = Optional.ofNullable(machineDto.getModel())
                .map(modelMapper::dtoToEntity)
                .orElse(null);
        Address address = Optional.ofNullable(machineDto.getAddress())
                .map(addressMapper::dtoToEntity)
                .orElse(null);

        Set<Product> products = Optional.ofNullable(machineDto.getProducts())
                .orElse(new HashSet<>())
                .stream()
                .map(productMapper::dtoToEntity)
                .collect(Collectors.toSet());

        return Machine.builder()
                .id(machineDto.getId())
                .serialNumber(machineDto.getSerialNumber())
                .model(model)
                .address(address)
                .products(products)
                .build();
    }

    @Override
    public MachineDto entityToDto(Machine machine) {
        ModelDto modelDto = Optional.ofNullable(machine.getModel())
                .map(modelMapper::entityToDto)
                .orElse(null);
        AddressDto addressDto = Optional.ofNullable(machine.getAddress())
                .map(addressMapper::entityToDto)
                .orElse(null);

        Set<ProductDto> productsDto = Optional.ofNullable(machine.getProducts())
                .orElse(new HashSet<>())
                .stream()
                .map(productMapper::entityToDto)
                .collect(Collectors.toSet());

        return MachineDto.builder()
                .id(machine.getId())
                .serialNumber(machine.getSerialNumber())
                .model(modelDto)
                .address(addressDto)
                .products(productsDto)
                .build();
    }
}
