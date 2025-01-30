package lk.ijse.nltec.nltecconvertlayerda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SupplierDTO {
    private String supId;
    private String companyName;
    private String personName;
    private String tel;
    private String location;
    private String email;
}
