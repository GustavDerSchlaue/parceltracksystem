package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.persistence.BLDataNotFoundException;
import at.fhtw.swen3.persistence.BLException;
import at.fhtw.swen3.persistence.BLValidationException;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.NewParcelInfoMapper;
import at.fhtw.swen3.services.mapper.TrackingInformationMapper;
import at.fhtw.swen3.services.validation.Validator;
import com.mifmif.common.regex.Generex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository parcelRepository;
    private final RecipientRepository recipientRepository;

    private final TruckRepository truckRepository;
    private final WarehouseRepository warehouseRepository;
    private final TransferwarehouseRepository transferwarehouseRepository;
    private final Validator validator;

    private String generateRandomTrackingId(){
        Generex generex = new Generex("[A-Z0-9]{9}");
        return generex.random();
    }

    @Override
    public NewParcelInfo submitNewParcel(@NotNull ParcelEntity parcelEntity) throws Exception {
        NewParcelInfo newParcelInfo = new NewParcelInfo();
        //set Infos for ParcelEntity
        parcelEntity.setTrackingId(generateRandomTrackingId());
        log.info("random generated trackingId of parcel:" + parcelEntity.getTrackingId());
        parcelEntity.setState(TrackingInformation.StateEnum.PICKUP);
        parcelEntity.setFutureHops(new ArrayList<>()); //Get GPS coordinates for package sender/recipient (using Geo Encoding Proxy of your choice)
                                                       //Predict future hops (=route between sender --> recipient)
        parcelEntity.setVisitedHops(new ArrayList<>());
        //Validate Entity
        Set<ConstraintViolation<ParcelEntity>> violationSet;
        try {
            violationSet = validator.validates(parcelEntity);
        } catch (BLValidationException e) {
            throw new BLException(1L, "Error validating parcel", e);
        }
        if(violationSet.isEmpty()) {
            try {
                //Save Entity in REPO
                log.info("Validated parcel - found no violations");
                log.info("Saving ParcelEntity to REPO in submitNewParcel()");
                recipientRepository.save(parcelEntity.getSender());
                recipientRepository.save(parcelEntity.getRecipient());
                ParcelEntity savedParcelEntity = parcelRepository.save(parcelEntity);
                newParcelInfo = NewParcelInfoMapper.INSTANCE.entityToDto(savedParcelEntity);
                log.info("Saved ParcelEntity to REPO in submitNewParcel()");
            } catch (Exception e){
                log.error(Arrays.toString(e.getStackTrace()));
                throw new Exception(e);
            }
        } else {
            log.error("throwing BLException in validation of submitNewParcel()");
            throw new BLValidationException(1L, "Error validating parcel", null);
        }
        log.info("submitted parcel, returning parcelInfo to ParcelApiController");
        return newParcelInfo;
    }

    @Override
    public NewParcelInfo submitNewParcel(@NotNull String trackingID, @NotNull ParcelEntity parcelEntity) throws Exception {
        log.info("trying to transactionParcel with given trackingId");
        NewParcelInfo newParcelInfo = new NewParcelInfo();
        //set Infos for ParcelEntity
        ParcelEntity parcelEntityByTrackingId = parcelRepository.findByTrackingId(trackingID);
        if(parcelEntityByTrackingId == null) {
            log.info("trying to transactionParcel with given trackingId - trackingId is not in the database. OK");
            parcelEntity.setTrackingId(trackingID);
            parcelEntity.setState(TrackingInformation.StateEnum.PICKUP);
            parcelEntity.setFutureHops(new ArrayList<>());
            parcelEntity.setVisitedHops(new ArrayList<>());
            //Validate Entity
            Set<ConstraintViolation<ParcelEntity>> violationSet;
            try {
                violationSet = validator.validates(parcelEntity);
            } catch (BLValidationException e) {
                throw new BLValidationException(1L, "Error validating submit parcel", e);
            }
            if(violationSet.isEmpty()) {
                try {
                    //Save Entity in REPO
                    log.info("Saving ParcelEntity to REPO in submitNewParcel()");
                    recipientRepository.save(parcelEntity.getSender());
                    recipientRepository.save(parcelEntity.getRecipient());
                    ParcelEntity savedParcelEntity = parcelRepository.save(parcelEntity);
                    newParcelInfo = NewParcelInfoMapper.INSTANCE.entityToDto(savedParcelEntity);
                    log.info("Saved ParcelEntity to REPO in submitNewParcel()");
                } catch (Exception e){
                    log.error(Arrays.toString(e.getStackTrace()));
                    throw new Exception(e);
                }
            } else {
                log.error("throwing BLException in validation of submitNewParcel()");
                throw new BLValidationException(1L, "Error validating parcel", null);
            }
        } else {
            log.error("Error transferring parcel, trackingId already exists");
            throw new BLException(1L, "Error transferring parcel, trackingId already exists", null);
        }
        log.info("Transferred parcel successfully, returning parcelinfo");
        return newParcelInfo;
    }

    @Override
    public TrackingInformation getTrackingInformation(@NotNull String trackingId) throws Exception {
        TrackingInformation trackingInformation = new TrackingInformation();
        log.info("TRACKINGID: " + trackingId);
        ParcelEntity parcel = parcelRepository.findByTrackingId(trackingId);
        if(parcel != null) {
            log.info("getTrackingInformation(): Parcel found with this trackingId: " + trackingId);
            trackingInformation = TrackingInformationMapper.INSTANCE.entityToDto(parcel);
        } else {
            log.error("getTrackingInformation(): Parcel not found with this trackingId: " + trackingId);
            throw new BLDataNotFoundException(1L, "Parcel not found with this trackingId: " + trackingId, null);
        }
        log.info("Returning TrackingInformationDto from getTrackingInformation() to ParcelApiController");
        return trackingInformation;
    }


    @Override
    public NewParcelInfo reportParcelHop(@NotNull String trackingId, @NotNull String code) throws BLException {
        try {
            ParcelEntity parcel = parcelRepository.findByTrackingId(trackingId);
            if(parcel == null) {
                log.error("Found no parcel with given trackingId: " + trackingId);
                return null;
            }
            List<HopArrivalEntity> visitedHops = parcel.getVisitedHops();
            List<HopArrivalEntity> futureHops = parcel.getFutureHops();
            for (int i = 0; i < futureHops.size(); i++)
            {
                if (code.equals(futureHops.get(i).getCode())) {
                    TrackingInformation.StateEnum currenTrackingState = getCurrenTrackingState(trackingId, code);
                    if(currenTrackingState != null) {
                        visitedHops.add(parcel.getFutureHops().get(i));
                        parcel.setVisitedHops(visitedHops);
                        parcel.setState(currenTrackingState);
                        futureHops.remove(i);
                        parcel.setFutureHops(futureHops);
                        parcelRepository.save(parcel);
                        log.info("Hop with code " + code + " MOVED from futureHops list to visitedHops list of Parcel with trackingId " + trackingId);
                        return new NewParcelInfo();
                    }
                }
            }
            log.error("Hop with code " + code + " NOT FOUND in futureHops list of Parcel with trackingId " + trackingId);
            return null;
        } catch (Exception e) {
            throw new BLException(1L, e.getMessage(), null);
        }
    }


    @Override
    public boolean reportParcelDelivery(@NotNull String trackingId) throws Exception {
        ParcelEntity parcel = parcelRepository.findByTrackingId(trackingId);
        if (parcel == null) {
            log.info("Parcel with tracking id " + trackingId + " NOT FOUND");
            throw new BLDataNotFoundException(1L, "Parcel with tracking id" + trackingId + " NOT FOUND", null);
        }
        try {
            parcel.setState(TrackingInformation.StateEnum.DELIVERED);
            parcelRepository.save(parcel);
            log.info("Parcel with tracking id " + trackingId + " was DELIVERED");
            return true;
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new Exception(e);
        }
    }

    @Override
    public boolean findParcelByTrackingId(String trackingId){
        ParcelEntity parcel = parcelRepository.findByTrackingId(trackingId);
        if (parcel == null) {
            log.info("Parcel with tracking id " + trackingId + " NOT FOUND");
            return false;
        }
        return true;
    }

    private TrackingInformation.StateEnum getCurrenTrackingState(@NotNull String trackingId, @NotNull String code) {
        if (truckRepository.findByCode(code) != null) {
            return TrackingInformation.StateEnum.INTRUCKDELIVERY;
        } else if (warehouseRepository.findByCode(code) != null) {
            return TrackingInformation.StateEnum.INTRANSPORT;
        } else if(transferwarehouseRepository.findByCode(code) != null) {
            callLogisticsPartnerAPI(transferwarehouseRepository.findByCode(code).getLogisticsPartnerUrl(), trackingId);
            return TrackingInformation.StateEnum.TRANSFERRED;
        } else {
            return null;
        }
    }

    private  void callLogisticsPartnerAPI(@NotNull String partnerURL, @NotNull String trackingId) {
        URI url = URI.create("https://" + partnerURL + "/parcel" + trackingId);
        //HttpClient client = java.net.http.HttpClient.newBuilder().build();
        String body = null;
    }
}
