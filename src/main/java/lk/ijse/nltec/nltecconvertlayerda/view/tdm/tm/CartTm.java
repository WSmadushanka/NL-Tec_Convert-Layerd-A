package lk.ijse.nltec.nltecconvertlayerda.view.tdm.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTm {
    private String itemName;
    private int qty;
    private double unitPrice;
    private double total;
    private JFXButton btnRemove;
}
