package at.fhtw.swen3.persistence.entities;

import com.sun.xml.bind.v2.TODO;
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
@Table(name="geoCoordinate")
public class GeoCoordinateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    //TODO LAT AND LON TO ONE STRING
    @Column
    private Double lat;
    @Column
    private Double lon;
}
