package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewParcelInfoMapper {

    NewParcelInfoMapper INSTANCE = Mappers.getMapper(NewParcelInfoMapper.class);
    NewParcelInfo entityToDto(ParcelEntity parcelEntity);
    ParcelEntity dtoToEntity(NewParcelInfo newParcelInfo);
}
