package it.academy.service;

import it.academy.dto.MachineDto;
import it.academy.models.pageable.Pageable;

import java.util.HashMap;
import java.util.List;

public interface IMachineService {
    Pageable<MachineDto> getPageableRecords(Integer pageSize,
                                            Integer pageNumber,
                                            HashMap<String, Object> searchFields,
                                            String sortField);

    boolean createMachine(MachineDto machineDto, Integer addressId, Integer modelId);

    MachineDto findMachineById(Integer id);

    boolean updateMachine(MachineDto machineDto, Integer addressId, Integer modelId);

    boolean deleteMachineById(Integer id);

    List<MachineDto> findAllMachines();

    boolean createMachineProduct(Integer machineId, Integer productId);

    boolean deleteMachineProduct(Integer machineId, Integer productId);
}
