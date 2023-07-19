package it.academy.repositories.impl;

import it.academy.models.Model;
import it.academy.repositories.IModelRepository;

import static it.academy.utils.constants.DataGeneral.MODEL_CLASS;

public class ModelRepository extends CrudRepository<Model>
        implements IModelRepository {
    public ModelRepository() {
        super(MODEL_CLASS);
    }

}
