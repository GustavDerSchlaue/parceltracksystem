package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.GeoEncodingService;
import at.fhtw.swen3.persistence.BLException;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseNextHopsRepository warehouseNextHopsRepository;
    private final HopRepository hopRepository;
    private final TruckRepository truckRepository;
    private final TransferwarehouseRepository transferwarehouseRepository;
    private final GeoCoordinateRepository geoCoordinateRepository;

    private final Validator validator;

    @Override
    public boolean findWarehousebyCode(String code){
        WarehouseEntity warehouse = warehouseRepository.findByCode(code);
        if (warehouse == null){

            log.info("Warehouse with code " + code + " NOT FOUND");
            return false;
        }
        return true;
    }

    @Override
    public void importNewWarehouses(WarehouseEntity warehouse) throws BLException
    {
        //System.out.println("TTTEEESSSTTT");
        try
        {
            warehouseNextHopsRepository.deleteAll();
            warehouseRepository.deleteAll();
            hopRepository.deleteAll();
            truckRepository.deleteAll();
            transferwarehouseRepository.deleteAll();
            geoCoordinateRepository.deleteAll();
            log.info("Reset DB SUCC");
        }
        catch (Exception e)
        {
            log.info("Reset DB FAIL");
            //IMPLEMENT THROW
        }


        try
        {
            WarehouseEntity savedWarehouse = saveWarehouse(warehouse);
            System.out.println("Warehouse "+ savedWarehouse.getCode() + " store SUCC");
            log.info("Warehouse "+ savedWarehouse.getCode() + " store SUCC");
        }
        catch (Exception e) {
            log.info("Warehouse store FAIL" + e.getMessage());
            //IMPLEMENT THROW
        }

    }

    private WarehouseEntity saveWarehouse(WarehouseEntity warehouse) throws BLException
    {
        //Validation


        log.info("Warehouse Iteration Begin");
        for (WarehouseNextHopsEntity nextHop: warehouse.getNextHops())
        {
            HopEntity savedHop = saveHop(nextHop.getHop());
            nextHop.getHop().setId(savedHop.getId());
            nextHop.setHopasWarehouse(warehouse);
        }
        log.info("Warehouse Iteration SUCC");


        WarehouseEntity savedWarehouse = warehouseRepository.save(warehouse);
        log.info("Warehouse "+ savedWarehouse.getCode() + " save SUCC");
        return  savedWarehouse;

    }

    private TruckEntity saveTruck(TruckEntity truck) throws BLException
    {
        TruckEntity savedTruck = null;
        try
        {
            savedTruck = truckRepository.save(truck);
            System.out.println("Truck "+ savedTruck.getCode() + " store SUCC");
            log.info("Truck "+ savedTruck.getCode() + " store SUCC");
        }
        catch (Exception e)
        {
            System.out.println("Truck "+ savedTruck.getCode() + " store FAIL");
            log.info("Truck "+ savedTruck.getCode() + " store FAIL");
            //IMPLEMENT THROW
        }
        log.info("Truck "+ savedTruck.getCode() + " save SUCC");
        return savedTruck;
    }

    private TransferwarehouseEntity saveTransferwarehouse(TransferwarehouseEntity transferwarehouse) throws BLException {

        TransferwarehouseEntity savedTransferwarehouse = null;

        try{
            savedTransferwarehouse = transferwarehouseRepository.save(transferwarehouse);
            System.out.println("Transferwarehouse "+ savedTransferwarehouse.getCode() + " store FAIL");
            log.info("Transferwarehouse "+ savedTransferwarehouse.getCode() + " store SUCC");
        }
        catch (Exception e) {
            System.out.println("Transferwarehouse "+ savedTransferwarehouse.getCode() + " store FAIL");
            log.info("Transferwarehouse "+ savedTransferwarehouse.getCode() + " store FAIL");
            //IMPLEMENT THROW
        }
        log.info("Transferwarehouse "+ savedTransferwarehouse.getCode() + " save SUCC");
        return savedTransferwarehouse;
    }

    public HopEntity saveHop(HopEntity hop) throws BLException {
        System.out.println("Saving Hop Entity of type " + hop.getHopType());

        if (hop.getHopType().equalsIgnoreCase("truck"))
        {
            TruckEntity truck = (TruckEntity) hop;
            TruckEntity savedTruck = saveTruck(truck);
            System.out.println("Truck" + truck.getCode() + "save COMPLETED");
            log.info("Truck" + truck.getCode() + "save COMPLETED");
            return savedTruck;
        }

        if (hop.getHopType().equalsIgnoreCase("warehouse"))
        {
            //DEBUG
            //System.out.println("POG");
            WarehouseEntity warehouse = (WarehouseEntity) hop;
            //DEBUG
            //System.out.println("POG2");
            WarehouseEntity savedWarehouse = saveWarehouse(warehouse);
            System.out.println("warehouse" + warehouse.getCode() + "save COMPLETED");
            log.info("warehouse" + warehouse.getCode() + "save COMPLETED");
            return savedWarehouse;
        }

        if (hop.getHopType().equalsIgnoreCase("transferwarehouse"))
        {
            TransferwarehouseEntity transferwarehouse = (TransferwarehouseEntity) hop;
            TransferwarehouseEntity savedTransferwarehouse = saveTransferwarehouse(transferwarehouse);
            System.out.println("transferwarehouse" + transferwarehouse.getCode() + "save COMPLETED");
            log.info("transferwarehouse" + transferwarehouse.getCode() + "save COMPLETED");
            return savedTransferwarehouse;
        }
        System.out.println("Hop" + hop.getCode() + "without valid Type | NOT SAVED");
        log.info("Hop" + hop.getCode() + "without valid Type | NOT SAVED");
        return null;
    }
}
