package lk.ijse.nltec.nltecconvertlayerda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class DailyOrders {
    Date date;
    int countOr;
    int countQty;
}
