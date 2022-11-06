package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="warehouseNextHops")
public class WarehouseNextHopsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    @Positive(message = "Description cannot be null")
    private Integer traveltimeMins;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "hop_id", nullable = false, updatable = false)
    @NotNull(message = "Hop cannot be null")
    private HopEntity hop;

}