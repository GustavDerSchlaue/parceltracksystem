package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-28T02:11:52+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_352 (Amazon.com Inc.)"
)
public class NewParcelInfoMapperImpl implements NewParcelInfoMapper {

    @Override
    public NewParcelInfo entityToDto(ParcelEntity parcelEntity) {
        if ( parcelEntity == null ) {
            return null;
        }

        NewParcelInfo newParcelInfo = new NewParcelInfo();

        newParcelInfo.setTrackingId( parcelEntity.getTrackingId() );

        return newParcelInfo;
    }

    @Override
    public ParcelEntity dtoToEntity(NewParcelInfo newParcelInfo) {
        if ( newParcelInfo == null ) {
            return null;
        }

        ParcelEntity parcelEntity = new ParcelEntity();

        parcelEntity.setTrackingId( newParcelInfo.getTrackingId() );

        return parcelEntity;
    }
}
