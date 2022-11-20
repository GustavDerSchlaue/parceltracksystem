package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;

import javax.validation.constraints.NotNull;

public interface ParcelService {

    NewParcelInfo submitNewParcel(@NotNull ParcelEntity parcelEntity);

    Parcel getTrackingInformation(String trackingId);

    NewParcelInfo reportParcelHop(@NotNull String trackingId, @NotNull String code);

    boolean reportParcelDelivery(@NotNull String trackingId);

    boolean findParcelbyTrackindID(String trackingId);
}

