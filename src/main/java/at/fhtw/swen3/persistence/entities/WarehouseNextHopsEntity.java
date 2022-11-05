package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseNextHopsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer traveltimeMins;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "hop_id", nullable = false, updatable = false)
    private HopEntity hop;

}