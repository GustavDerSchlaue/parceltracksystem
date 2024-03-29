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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="hop")
public class HopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull(message = "HopType cannot be null")
    @NotBlank(message = "HopType cannot be blank")
    @Size(min = 2, max = 100, message = "A valid hopType must contain more than 2 characters and max 100")
    @Column
    private String hopType;
    @NotNull(message = "Code cannot be null")
    @NotBlank(message = "Code cannot be blank")
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$", message = "A valid code must match 4 characters and 1-4 digits for example: ABCD2345")
    @Column
    private String code;
    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be blank")
    @Size(min = 2, max = 100000, message = "A valid description must contain more than 2 characters and max 100000")
    @Column
    private String description;
    @PositiveOrZero(message = "A valid processingDelayMins must be positive or zero")
    @Column
    private Integer processingDelayMins;
    @NotNull(message = "LocationName cannot be null")
    @NotBlank(message = "LocationName cannot be blank")
    @Size(min = 2, max = 100, message = "A valid LocationName must contain more than 2 characters and max 100")
    @Column
    private String locationName;
    @ManyToOne
    @NotNull(message = "locationCoordinates cannot be null")
    @JoinColumn(name = "locationCoordinates_id")
    private GeoCoordinateEntity locationCoordinates;
}
