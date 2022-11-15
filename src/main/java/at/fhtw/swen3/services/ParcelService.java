package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;

import javax.validation.constraints.NotNull;

public interface ParcelService {

    NewParcelInfo submitNewParcel(@NotNull ParcelEntity parcelEntity);

    Parcel getTrackingInformation(String trackingId);
}
