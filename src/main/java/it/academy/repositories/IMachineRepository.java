package it.academy.repositories;

import it.academy.models.Machine;

public interface IMachineRepository extends ICrudRepository<Machine> {
    boolean createMachine(Machine machine, Integer addressId, Integer modelId);

    boolean updateMachine(Machine machine, Integer addressId, Integer modelId);

    boolean addProductInMachine(Integer machineId, Integer productId);

    boolean deleteProductInMachine(Integer machineId, Integer productId);
}
