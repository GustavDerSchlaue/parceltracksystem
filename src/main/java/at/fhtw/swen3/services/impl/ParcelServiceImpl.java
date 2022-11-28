package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.NewParcelInfoMapper;
import at.fhtw.swen3.services.validation.Validator;
import com.mifmif.common.regex.Generex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.log.SystemLogHandler;

import javax.validation.constraints.NotNull;
import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository parcelRepository;
    private final RecipientRepository recipientRepository;
    private final Validator validator;

    private String generateRandomTrackingId(){
        Generex generex = new Generex("[A-Z0-9]{9}");
        return generex.random();
    }

    @Override
    public NewParcelInfo submitNewParcel(@NotNull ParcelEntity parcelEntity) {
        NewParcelInfo newParcelInfo = new NewParcelInfo();
        //set Infos for ParcelEntity
        parcelEntity.setTrackingId(generateRandomTrackingId());
        log.info("random generated trackingId of parcel:" + parcelEntity.getTrackingId());
        parcelEntity.setState(TrackingInformation.StateEnum.INTRANSPORT);
        parcelEntity.setFutureHops(new ArrayList<>());
        parcelEntity.setVisitedHops(new ArrayList<>());
        //Validate Entity
        if(validator.validate(parcelEntity)){
            //Save Entity in REPO
            log.info("Saving ParcelEntity to REPO in submitNewParcel()");
            recipientRepository.save(parcelEntity.getSender());
            recipientRepository.save(parcelEntity.getRecipient());
            ParcelEntity savedParcelEntity = parcelRepository.save(parcelEntity);
            //ParcelEntity ParcelEntityByTrackingId = parcelRepository.findByTrackingId(parcelEntity.getTrackingId());
            newParcelInfo = NewParcelInfoMapper.INSTANCE.entityToDto(savedParcelEntity);
            log.info("Saved ParcelEntity to REPO in submitNewParcel()");
        }
        return newParcelInfo;
    }



    @Override
    public Parcel getTrackingInformation(@NotNull String trackingId) {
        log.info("Returning ParcelDto from getTrackingInformation() to ParcelApiController");
        return null;
    }


    @Override
    public NewParcelInfo reportParcelHop(@NotNull String trackingId, @NotNull String code){

        ParcelEntity parcel = parcelRepository.findByTrackingId(trackingId);
        List<HopArrivalEntity> visitedHops = parcel.getVisitedHops();
        List<HopArrivalEntity> futureHops = parcel.getFutureHops();
        for (int i = 0; i < futureHops.size(); i++)
        {
            if (code.equals(futureHops.get(i).getCode())){
                visitedHops.add(parcel.getFutureHops().get(i));
                parcel.setVisitedHops(visitedHops);
                futureHops.remove(i);
                parcel.setFutureHops(futureHops);
                parcelRepository.save(parcel);
                log.info("Hop with code " + code + " MOVED from futurehops list to visitedhop list of Parcel with trackid " + trackingId);
                return new NewParcelInfo();
            }
        }
        log.info("Hop with code " + code + " NOT FOUND in futurehops list of Parcel with trackid " + trackingId);
        return null;
    }


    @Override
    public boolean reportParcelDelivery(@NotNull String trackingId){

        ParcelEntity parcel = parcelRepository.findByTrackingId(trackingId);

        if (parcel == null){

            log.info("Parcel with tracking id " + trackingId + " NOT FOUND");
            return false;
        }
        parcel.setState(TrackingInformation.StateEnum.DELIVERED);
        System.out.println("_____________________________________________");
        System.out.println(parcelRepository.save(parcel));
        System.out.println("_____________________________________________");
        log.info("Parcel with tracking id " + trackingId + " was DELIEVERED");
        return true;
    }

    @Override
    public boolean findParcelbyTrackindID(String trackingId){
        ParcelEntity parcel = parcelRepository.findByTrackingId(trackingId);
        if (parcel == null){

            log.info("Parcel with tracking id " + trackingId + " NOT FOUND");
            return false;
        }
        return true;
    }
}
