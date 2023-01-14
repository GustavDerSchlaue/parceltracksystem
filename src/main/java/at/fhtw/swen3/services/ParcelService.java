package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.BLException;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.TrackingInformation;

import javax.validation.constraints.NotNull;

public interface ParcelService {

    NewParcelInfo submitNewParcel(@NotNull ParcelEntity parcelEntity) throws Exception;

    NewParcelInfo submitNewParcel(@NotNull String trackingID, @NotNull ParcelEntity parcelEntity) throws Exception;

    TrackingInformation getTrackingInformation(String trackingId) throws Exception;

    NewParcelInfo reportParcelHop(@NotNull String trackingId, @NotNull String code) throws BLException;

    boolean reportParcelDelivery(@NotNull String trackingId) throws Exception;

    boolean findParcelByTrackingId(@NotNull String trackingId);
}

