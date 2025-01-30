package lk.ijse.nltec.nltecconvertlayerda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Repair {
    private String repairId;
    private LocalDate reciveDate;
    private LocalDate returnDate;
    private double cost;
    private String description;
    private String custId;
    private String itemName;
}
