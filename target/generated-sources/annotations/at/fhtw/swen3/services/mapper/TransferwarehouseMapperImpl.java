package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-28T02:11:52+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_352 (Amazon.com Inc.)"
)
public class TransferwarehouseMapperImpl implements TransferwarehouseMapper {

    @Override
    public Transferwarehouse entityToDto(TransferwarehouseEntity transferwarehouseEntity) {
        if ( transferwarehouseEntity == null ) {
            return null;
        }

        Transferwarehouse transferwarehouse = new Transferwarehouse();

        transferwarehouse.setRegionGeoJson( transferwarehouseEntity.getRegionGeoJson() );
        transferwarehouse.setLogisticsPartner( transferwarehouseEntity.getLogisticsPartner() );
        transferwarehouse.setLogisticsPartnerUrl( transferwarehouseEntity.getLogisticsPartnerUrl() );

        return transferwarehouse;
    }

    @Override
    public TransferwarehouseEntity dtoToEntity(Transferwarehouse transferwarehouse) {
        if ( transferwarehouse == null ) {
            return null;
        }

        TransferwarehouseEntity transferwarehouseEntity = new TransferwarehouseEntity();

        transferwarehouseEntity.setRegionGeoJson( transferwarehouse.getRegionGeoJson() );
        transferwarehouseEntity.setLogisticsPartner( transferwarehouse.getLogisticsPartner() );
        transferwarehouseEntity.setLogisticsPartnerUrl( transferwarehouse.getLogisticsPartnerUrl() );

        return transferwarehouseEntity;
    }
}
