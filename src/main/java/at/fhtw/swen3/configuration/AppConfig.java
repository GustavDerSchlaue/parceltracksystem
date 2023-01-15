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
    public WarehouseServiceImpl warehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseNextHopsRepository warehouseNextHopsRepository, Validator validator) {
        return new WarehouseServiceImpl(warehouseRepository, warehouseNextHopsRepository, validator);
    }
}
