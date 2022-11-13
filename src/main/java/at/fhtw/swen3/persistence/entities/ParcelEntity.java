package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="parcel")
public class ParcelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="Id", nullable = false)
    private Long id;
    @NotNull(message = "TrackingId cannot be null")
    @NotBlank(message = "TrackingId cannot be blank")
    @Pattern(regexp = "^[A-Z0-9]{9}$", message = "A valid trackingId must not contain special characters and must have 9 digits")
    @Column
    private String trackingId;
    @Enumerated(EnumType.STRING)
    @Column
    private TrackingInformation.StateEnum state;
    @NotNull(message = "VisitedHops cannot be null")
    @OneToMany
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();
    @NotNull(message = "FutureHops cannot be null")
    @OneToMany
    private List<HopArrivalEntity> futureHops = new ArrayList<>();
    @Positive(message = "A valid weight must be positive and cannot be 0")
    @Column
    private Float weight;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    @NotNull(message = "Recipient cannot be null")
    private RecipientEntity recipient;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    @NotNull(message = "Sender cannot be null")
    private RecipientEntity sender;
}
