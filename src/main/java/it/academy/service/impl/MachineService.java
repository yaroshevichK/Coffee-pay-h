package it.academy.service.impl;

import it.academy.dto.MachineDto;
import it.academy.mapper.IMapper;
import it.academy.mapper.impl.MachineMapper;
import it.academy.mapper.impl.PageableMapper;
import it.academy.models.Machine;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IMachineRepository;
import it.academy.repositories.impl.MachineRepository;
import it.academy.service.IMachineService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MachineService implements IMachineService {
    private final IMachineRepository machineRepository = new MachineRepository();

    private final IMapper<Machine, MachineDto> machineMapper = new MachineMapper();
    private final IMapper<Pageable<Machine>, Pageable<MachineDto>> pageableMapper
            = new PageableMapper<>(machineMapper);

    @Override
    public Pageable<MachineDto> getPageableRecords(Integer pageSize,
                                                   Integer pageNumber,
                                                   HashMap<String, Object> searchFields,
                                                   String sortField) {
        Pageable<Machine> pageable = machineRepository
                .getPageableRecords(pageSize,
                        pageNumber,
                        searchFields,
                        sortField);

        return pageableMapper.entityToDto(pageable);
    }

    @Override
    public boolean createMachine(MachineDto machineDto, Integer addressId, Integer modelId) {
        Machine machine = machineMapper.dtoToEntity(machineDto);
        return machineRepository.createMachine(machine, addressId, modelId);
    }

    @Override
    public MachineDto findMachineById(Integer id) {
        Machine machine = machineRepository.findById(id);
        return machineMapper.entityToDto(machine);
    }

    @Override
    public boolean updateMachine(MachineDto machineDto, Integer addressId, Integer modelId) {
        Machine machine = machineMapper.dtoToEntity(machineDto);
        return machineRepository.updateMachine(machine, addressId, modelId);
    }

    @Override
    public boolean deleteMachineById(Integer id) {
        return machineRepository.delete(id);
    }

    @Override
    public List<MachineDto> findAllMachines() {
        List<Machine> machineList = machineRepository.findAll();
        return Optional.ofNullable(machineList)
                .orElse(new ArrayList<>())
                .stream()
                .map(machineMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean createMachineProduct(Integer machineId, Integer productId) {
        return machineRepository.addProductInMachine(machineId, productId);
    }

    @Override
    public boolean deleteMachineProduct(Integer machineId, Integer productId) {
        return machineRepository.deleteProductInMachine(machineId, productId);
    }
}
