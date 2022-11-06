package at.fhtw.swen3.persistence.entities;

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
@Table(name="truck")
public class TruckEntity extends HopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull(message = "LogisticsPartner cannot be null")
    @NotBlank(message = "LogisticsPartner cannot be blank")
    @Column
    private String regionGeoJson;
    @NotNull(message = "NumberPlate cannot be null")
    @NotBlank(message = "NumberPlate cannot be blank")
    @Size(min = 4, max = 20, message = "A valid numberPlate must contain more than 4 characters and max 20")
    @Column
    private String numberPlate;

}
