package lk.ijse.nltec.nltecconvertlayerda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderDetailDTO {
    private String orderId;
    private String itemCode;
    private int qty;
    private double unitPrice;
}
