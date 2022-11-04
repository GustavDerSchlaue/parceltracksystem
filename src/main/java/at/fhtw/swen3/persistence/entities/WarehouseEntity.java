package at.fhtw.swen3.persistence.entities;

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
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Size(min = 4, max = 20, message = "A valid numberPlate must contain more than 4 characters and max 20")
    private Integer level;
    @NotNull(message = "NextHops cannot be null")
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();


}
