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
@Table(name="warehouseNextHop")
public class WarehouseNextHopsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    @Positive(message = "Description cannot be null")
    private Integer traveltimeMins;
    @OneToOne
    @NotNull(message = "Hop cannot be null")
    private HopEntity hop;

    public void setHopasWarehouse(WarehouseEntity warehouse) {
        this.hop=warehouse;
    }
}