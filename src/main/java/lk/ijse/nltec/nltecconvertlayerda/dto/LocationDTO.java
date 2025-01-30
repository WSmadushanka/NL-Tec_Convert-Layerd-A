package lk.ijse.nltec.nltecconvertlayerda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class LocationDTO {
    private String place;
    private Double latitude;
    private Double longitude;
}
