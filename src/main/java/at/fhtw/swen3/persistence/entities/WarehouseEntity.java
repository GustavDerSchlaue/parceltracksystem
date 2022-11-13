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
public class WarehouseEntity extends HopEntity {

    @Size(min = 4, max = 20, message = "A valid numberPlate must contain more than 4 characters and max 20")
    @Column
    private Integer level;
    @NotNull(message = "NextHops cannot be null")
    @OneToMany
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();


}
