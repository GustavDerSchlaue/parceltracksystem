package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.Parcel;

public interface ParcelService {

    ParcelEntity mapParcelDtoToParcelEntity(Parcel parcelDto);
    Parcel mapParcelEntityToParcelDto(ParcelEntity parcelEntity);
    void submitNewParcel(Parcel parcelDto);
    Parcel getTrackingInformation(String trackingId);
}
