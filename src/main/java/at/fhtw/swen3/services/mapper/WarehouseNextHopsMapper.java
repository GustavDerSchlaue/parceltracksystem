package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = HopMapper.class)
public interface WarehouseNextHopsMapper {

    WarehouseNextHopsMapper INSTANCE = Mappers.getMapper(WarehouseNextHopsMapper.class);
    WarehouseNextHops entityToDto(WarehouseNextHops warehouseNextHops);
    WarehouseNextHopsEntity dtoToEntity(WarehouseNextHopsEntity warehouseNextHopsEntity);
}

