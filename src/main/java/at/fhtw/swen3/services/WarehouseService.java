package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.BLException;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;

public interface WarehouseService {
    boolean findWarehousebyCode(String trackingId);
    public void importNewWarehouses(WarehouseEntity warehouse) throws BLException;
}

