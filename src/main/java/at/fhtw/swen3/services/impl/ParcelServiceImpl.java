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
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.Validator;
import com.mifmif.common.regex.Generex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

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
}
