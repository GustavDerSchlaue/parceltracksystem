package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-28T02:11:52+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_352 (Amazon.com Inc.)"
)
public class WarehouseNextHopsMapperImpl implements WarehouseNextHopsMapper {

    @Override
    public WarehouseNextHops entityToDto(WarehouseNextHops warehouseNextHops) {
        if ( warehouseNextHops == null ) {
            return null;
        }

        WarehouseNextHops warehouseNextHops1 = new WarehouseNextHops();

        warehouseNextHops1.setTraveltimeMins( warehouseNextHops.getTraveltimeMins() );
        warehouseNextHops1.setHop( warehouseNextHops.getHop() );

        return warehouseNextHops1;
    }

    @Override
    public WarehouseNextHopsEntity dtoToEntity(WarehouseNextHopsEntity warehouseNextHopsEntity) {
        if ( warehouseNextHopsEntity == null ) {
            return null;
        }

        WarehouseNextHopsEntity warehouseNextHopsEntity1 = new WarehouseNextHopsEntity();

        warehouseNextHopsEntity1.setId( warehouseNextHopsEntity.getId() );
        warehouseNextHopsEntity1.setTraveltimeMins( warehouseNextHopsEntity.getTraveltimeMins() );
        warehouseNextHopsEntity1.setHop( warehouseNextHopsEntity.getHop() );

        return warehouseNextHopsEntity1;
    }
}
