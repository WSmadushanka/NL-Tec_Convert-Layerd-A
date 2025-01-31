package lk.ijse.nltec.nltecconvertlayerda.view.tdm.tm;

import lk.ijse.nltec.nltecconvertlayerda.entity.Repair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepairTm extends Repair {
    private String repId;
    private String itemName;
    private String description;
    private String custId;
    private double cost;
    private LocalDate reciveDate;
    private LocalDate reternDate;
}
