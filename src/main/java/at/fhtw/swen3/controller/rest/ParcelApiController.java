package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
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
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        log.info("mapping parcelDto to parcelEntity in ParcelApiController");
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);
        log.info("submit parcelEntity in ParcelApiController in submitParcel()");
        NewParcelInfo newParcelInfo = parcelService.submitNewParcel(parcelEntity);
        return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> reportParcelHop(String trackingId, String code) {
        log.info("m");
        //__________________________________________________
        // CODE CANNOT BE FOUND YET
        //__________________________________________________
        //if (warehouseService.findWarehousebyCode(code))
        if (true)
        {
            if (parcelService.reportParcelHop(trackingId, code) != null){
                log.info("ReportParcelDelivery OK");
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                log.info("ReportParcelDelivery BAD REQUEST");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> reportParcelDelivery(String trackingId) {
        if (parcelService.findParcelbyTrackindID(trackingId))
        {
            if (parcelService.reportParcelDelivery(trackingId)){
                log.info("ReportParcelDelivery OK");
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                log.info("ReportParcelDelivery BAD REQUEST");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }
        log.info("ReportParcelDelivery Parcel NOT FOUND");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
