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
public class Item {
    private String itemId;
    private String Name;
    private String category;
    private String brand;
    private LocalDate stockDate;
    private String description;
    private String warranty;
    private String type;
    private String path;
}
