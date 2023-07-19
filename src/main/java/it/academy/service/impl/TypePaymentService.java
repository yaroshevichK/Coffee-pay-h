package it.academy.service.impl;

import it.academy.dto.TypePaymentDto;
import it.academy.mapper.IMapper;
import it.academy.mapper.impl.TypePaymentMapper;
import it.academy.models.TypePayment;
import it.academy.repositories.ITypePaymentRepository;
import it.academy.repositories.impl.TypePaymentRepository;
import it.academy.service.ITypePaymentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TypePaymentService implements ITypePaymentService {
    private final ITypePaymentRepository typePaymentRepository = new TypePaymentRepository();
    private final IMapper<TypePayment, TypePaymentDto> typePaymentMapper = new TypePaymentMapper();

    @Override
    public boolean createTypePayment(TypePaymentDto typePaymentDto) {
        TypePayment typePayment = typePaymentMapper.dtoToEntity(typePaymentDto);
        return typePaymentRepository.save(typePayment);
    }

    @Override
    public TypePaymentDto findTypePaymentById(Integer id) {
        TypePayment typePayment = typePaymentRepository.findById(id);
        return typePaymentMapper.entityToDto(typePayment);
    }

    @Override
    public boolean updateTypePayment(TypePaymentDto typePaymentDto) {
        TypePayment typePayment = typePaymentMapper.dtoToEntity(typePaymentDto);
        return typePaymentRepository.update(typePayment);
    }

    @Override
    public boolean deleteTypePaymentById(Integer id) {
        return typePaymentRepository.delete(id);
    }

    @Override
    public List<TypePaymentDto> findAllTypePayments() {
        List<TypePayment> typePaymentList = typePaymentRepository.findAll();
        return Optional.ofNullable(typePaymentList)
                .orElse(new ArrayList<>())
                .stream()
                .map(typePaymentMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
