package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Truck;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-28T02:11:52+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_352 (Amazon.com Inc.)"
)
public class TruckMapperImpl implements TruckMapper {

    @Override
    public Truck entityToDto(TruckEntity truckEntity) {
        if ( truckEntity == null ) {
            return null;
        }

        Truck truck = new Truck();

        truck.hopType( truckEntity.getHopType() );
        truck.code( truckEntity.getCode() );
        truck.description( truckEntity.getDescription() );
        truck.processingDelayMins( truckEntity.getProcessingDelayMins() );
        truck.locationName( truckEntity.getLocationName() );
        truck.locationCoordinates( geoCoordinateEntityToGeoCoordinate( truckEntity.getLocationCoordinates() ) );
        truck.setRegionGeoJson( truckEntity.getRegionGeoJson() );
        truck.setNumberPlate( truckEntity.getNumberPlate() );

        return truck;
    }

    @Override
    public TruckEntity dtoToEntity(Truck truck) {
        if ( truck == null ) {
            return null;
        }

        TruckEntity truckEntity = new TruckEntity();

        truckEntity.setHopType( truck.getHopType() );
        truckEntity.setCode( truck.getCode() );
        truckEntity.setDescription( truck.getDescription() );
        truckEntity.setProcessingDelayMins( truck.getProcessingDelayMins() );
        truckEntity.setLocationName( truck.getLocationName() );
        truckEntity.setLocationCoordinates( geoCoordinateToGeoCoordinateEntity( truck.getLocationCoordinates() ) );
        truckEntity.setRegionGeoJson( truck.getRegionGeoJson() );
        truckEntity.setNumberPlate( truck.getNumberPlate() );

        return truckEntity;
    }

    protected GeoCoordinate geoCoordinateEntityToGeoCoordinate(GeoCoordinateEntity geoCoordinateEntity) {
        if ( geoCoordinateEntity == null ) {
            return null;
        }

        GeoCoordinate geoCoordinate = new GeoCoordinate();

        geoCoordinate.setLat( geoCoordinateEntity.getLat() );
        geoCoordinate.setLon( geoCoordinateEntity.getLon() );

        return geoCoordinate;
    }

    protected GeoCoordinateEntity geoCoordinateToGeoCoordinateEntity(GeoCoordinate geoCoordinate) {
        if ( geoCoordinate == null ) {
            return null;
        }

        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();

        geoCoordinateEntity.setLat( geoCoordinate.getLat() );
        geoCoordinateEntity.setLon( geoCoordinate.getLon() );

        return geoCoordinateEntity;
    }
}
