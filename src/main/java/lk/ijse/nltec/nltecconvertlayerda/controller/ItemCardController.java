package lk.ijse.nltec.nltecconvertlayerda.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.nltec.nltecconvertlayerda.entity.ItemCard;
import lk.ijse.nltec.nltecconvertlayerda.util.MyListener;

public class ItemCardController {

    @FXML
    private AnchorPane card_form;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblPrice;

    private ItemCard itemCard;
    private MyListener myListener;

    public void setData(ItemCard itemCard, MyListener myListener) { // Set Data In Item Card
        this.itemCard = itemCard;
        this.myListener = myListener;

        lblItemName.setText(itemCard.getItemName());
        double profit = itemCard.getPrice() * 0.25;
        double netPrice = itemCard.getPrice() + profit;

        lblPrice.setText(String.valueOf(netPrice));
        Image image = new Image(itemCard.getImage(),190, 155, false, true);
        itemImage.setImage(image);


    }
    @FXML
    public void click(javafx.scene.input.MouseEvent mouseEvent) {
        myListener.onClickListener(itemCard);
    }
}
