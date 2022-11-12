package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    public WarehouseRepository warehouserepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouserepository = warehouserepository;
    };

}
