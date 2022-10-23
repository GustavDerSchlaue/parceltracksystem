package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrackingInformationMapper {

    TrackingInformationMapper INSTANCE = Mappers.getMapper(TrackingInformationMapper.class);
    TrackingInformation entityToDto(ParcelEntity parcelEntity);
    ParcelEntity dtoToEntity(TrackingInformation trackingInformation);
}
