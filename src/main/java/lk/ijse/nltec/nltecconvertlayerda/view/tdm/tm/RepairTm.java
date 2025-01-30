package lk.ijse.nltec.nltecconvertlayerda.view.tdm.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepairTm {
    private String repId;
    private String itemName;
    private String description;
    private String custId;
    private double cost;
    private LocalDate reciveDate;
    private LocalDate reternDate;
}
