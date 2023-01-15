package at.fhtw.swen3.configuration;

import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
import at.fhtw.swen3.services.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ParcelServiceImpl parcelServiceImpl(ParcelRepository parcelRepository, RecipientRepository recipientRepository, TruckRepository truckRepository, WarehouseRepository warehouseRepository, TransferwarehouseRepository transferwarehouseRepository, Validator validator) {
        return new ParcelServiceImpl(parcelRepository, recipientRepository, truckRepository, warehouseRepository, transferwarehouseRepository, validator);
    }

    @Bean
    public WarehouseServiceImpl warehouseService(WarehouseRepository warehouseRepository, WarehouseNextHopsRepository warehouseNextHopsRepository, HopRepository hopRepository, TruckRepository truckRepository, TransferwarehouseRepository transferwarehouseRepository, GeoCoordinateRepository geoCoordinateRepository, Validator validator) {
        return new WarehouseServiceImpl(warehouseRepository, warehouseNextHopsRepository, hopRepository, truckRepository, transferwarehouseRepository, geoCoordinateRepository, validator);
    }
}
