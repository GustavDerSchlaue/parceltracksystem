package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouserepository;
    private final WarehouseNextHopsRepository warehouseNextHopsRepository;
    private final Validator validator;

    @Override
    public boolean findWarehousebyCode(String code){
        WarehouseEntity warehouse = warehouserepository.findByCode(code);
        if (warehouse == null){

            log.info("Warehouse with code " + code + " NOT FOUND");
            return false;
        }
        return true;
    }

}
