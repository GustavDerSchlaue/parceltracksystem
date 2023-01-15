package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.Hop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferwarehouseEntity extends HopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull(message = "RegionGeoJson cannot be null")
    @NotBlank(message = "RegionGeoJson cannot be blank")
    @Column
    private String regionGeoJson;
    @NotNull(message = "LogisticsPartner cannot be null")
    @NotBlank(message = "LogisticsPartner cannot be blank")
    @Size(min = 2, max = 100000, message = "A valid logisticsPartner must contain more than 2 characters and max 100000")
    @Column
    private String logisticsPartner;
    @NotNull(message = "logisticsPartnerUrl cannot be null")
    @NotBlank(message = "logisticsPartnerUrl cannot be blank")
    @Size(min = 2, max = 1000, message = "A valid logisticsPartnerUrl must contain more than 2 characters and max 1000")
    @Column
    private String logisticsPartnerUrl;

}
