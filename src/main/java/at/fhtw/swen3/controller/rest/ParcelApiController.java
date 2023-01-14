package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.persistence.BLDataNotFoundException;
import at.fhtw.swen3.persistence.BLException;
import at.fhtw.swen3.persistence.BLValidationException;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.*;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;

import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-11T11:34:05.308118Z[Etc/UTC]")
@Controller
@Slf4j
public class ParcelApiController implements ParcelApi {

    private final NativeWebRequest request;
    private final ParcelService parcelService;
    private final WarehouseService warehouseService;

    @Autowired
    public ParcelApiController(NativeWebRequest request, ParcelService parcelService, WarehouseService warehouseService) {
        this.request = request;
        this.parcelService = parcelService;
        this.warehouseService = warehouseService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/parcel",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    @Override
    public ResponseEntity<?> submitParcel(Parcel parcel) {
        try {
            NewParcelInfo newParcelInfo = null;
            log.info("mapping parcelDto to parcelEntity in ParcelApiController");
            ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);
            log.info("submit parcelEntity in ParcelApiController in submitParcel()");
            newParcelInfo = parcelService.submitNewParcel(parcelEntity);
            return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);
        } catch (BLException e) {
            Error error = new Error();
            error.setErrorMessage(e.getInnerException().getMessage());
            return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorMessage(e.getMessage());
            return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
        }
        //TODO Future&Previous Hops
        //return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/parcel/{trackingId}",
            produces = { "application/json" }
    )
    @Override
    public ResponseEntity<?> trackParcel(String trackingId) {
        TrackingInformation trackingInformation = null;
        try {
            trackingInformation = parcelService.getTrackingInformation(trackingId);
            return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.OK);
        } catch (BLDataNotFoundException e) {
            return new ResponseEntity(" ", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorMessage(e.getMessage());
            return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/parcel/{trackingId}/reportHop/{code}",
            produces = { "application/json" }
    )
    @Override
    public ResponseEntity<?> reportParcelHop(String trackingId, String code) {
        //__________________________________________________
        // CODE CANNOT BE FOUND YET
        //__________________________________________________
        //if (warehouseService.findWarehousebyCode(code))
        try {
            if (parcelService.reportParcelHop(trackingId, code) != null){
                log.info("ReportParcelDelivery OK");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                log.info("ReportParcelDelivery NOT_FOUND");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (BLException e) {
            log.info("ReportParcelDelivery BAD_REQUEST");
            Error error = new Error();
            error.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/parcel/{trackingId}/reportDelivery/",
            produces = { "application/json" }
    )
    @Override
    public ResponseEntity<Void> reportParcelDelivery(String trackingId) {
        if (parcelService.findParcelByTrackingId(trackingId))
        {
            try {
                if (parcelService.reportParcelDelivery(trackingId)){
                    log.info("ReportParcelDelivery OK");
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else {
                    log.info("ReportParcelDelivery BAD REQUEST");
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (BLDataNotFoundException e) {
                log.info("ReportParcelDelivery Parcel NOT FOUND");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                log.info("ReportParcelDelivery BAD REQUEST");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        log.info("ReportParcelDelivery Parcel NOT FOUND");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/parcel/{trackingId}",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    @Override
    public ResponseEntity<?> transitionParcel(String trackingId, Parcel parcel){
        try {
            NewParcelInfo newParcelInfo = null;
            log.info("mapping parcelDto to parcelEntity in ParcelApiController");
            ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);
            log.info("submit parcelEntity in ParcelApiController in submitParcel()");
            newParcelInfo = parcelService.submitNewParcel(trackingId, parcelEntity);
            return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.OK);
        } catch (BLException e) {
            Error error = new Error();
            error.setErrorMessage(e.getMessage());
            return new ResponseEntity<Error>(error, HttpStatus.CONFLICT);
        } catch (BLValidationException e) {
            Error error = new Error();
            if(e.getInnerException().getMessage() != null) {
                error.setErrorMessage(e.getInnerException().getMessage());
            } else {
                error.setErrorMessage(e.getMessage());
            }
            return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorMessage(e.getMessage());
            return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
