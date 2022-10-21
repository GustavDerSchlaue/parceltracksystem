package at.fhtw.swen3.persistence.entity;

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
public class ParcelEntity {

    @Id
    @Column(name ="Id", nullable = false)
    private Long id;
    @NotNull(message = "TrackingId cannot be null")
    @NotBlank(message = "TrackingId cannot be blank")
    @Size(min = 1, max = 999999999, message = "A valid trackingId must contain more than 1 characters and max 999999999")
    private String trackingId;
    private TrackingInformation.StateEnum state;
    @NotNull(message = "VisitedHops cannot be null")
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();
    @NotNull(message = "FutureHops cannot be null")
    private List<HopArrivalEntity> futureHops = new ArrayList<>();
    @Positive(message = "A valid weight must be positive and cannot be 0")
    private Float weight;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    @NotNull(message = "Recipient cannot be null")
    private RecipientEntity recipient;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @NotNull(message = "Sender cannot be null")
    private RecipientEntity sender;
}
