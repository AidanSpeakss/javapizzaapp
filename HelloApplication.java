package com.devaidan.pizzaapp;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Label lblSizes = new Label("Sizes:");
        RadioButton size1 = new RadioButton("10 inches");
        size1.setId("10");
        RadioButton size2 = new RadioButton("12 inches");
        size2.setId("12");
        RadioButton size3 = new RadioButton("14 inches");
        size3.setId("14");
        RadioButton size4 = new RadioButton("16 inches");
        size4.setId("16");


        ToggleGroup tg = new ToggleGroup();
        size1.setToggleGroup(tg);
        size2.setToggleGroup(tg);
        size3.setToggleGroup(tg);
        size4.setToggleGroup(tg);


        HBox hb1 = new HBox();
        hb1.getChildren().addAll(lblSizes, size1, size2, size3, size4);
        hb1.setSpacing(10);


        Label lblStyle = new Label("Style/Type:");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Hand-tossed",
                        "Deep-dish",
                        "Thin Crust"
                );
        ComboBox cmbStyles = new ComboBox(options);
        HBox hb2 = new HBox();
        hb2.getChildren().addAll(lblStyle, cmbStyles);
        hb2.setSpacing(10);



        Label lblToppings = new Label("Toppings:");
        CheckBox topping1 = new CheckBox("Pepperoni");
        CheckBox topping2 = new CheckBox("Sausage");
        CheckBox topping3 = new CheckBox("Mushrooms");
        HBox hb3 = new HBox();
        hb3.getChildren().addAll(lblToppings, topping1, topping2, topping3);
        hb3.setSpacing(10);



        Label lblName = new Label("Name:");
        TextField txtName = new TextField();
        HBox hb4 = new HBox();
        hb4.getChildren().addAll(lblName, txtName);
        hb4.setSpacing(10);



        Label lblTime = new Label("Time for Pickup: ");
        Text lblFormat = new Text("We are open noon to midnight.");
        Slider sliderTime = new Slider(1, 12, 1);
        sliderTime.setMajorTickUnit(1);
        sliderTime.setBlockIncrement(1);
        Label time = new Label(" ");
        sliderTime.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number >
                                                observable, Number oldValue, Number newValue)
                    {

                        time.setText("Time Selected: " + Math.round((Double) newValue) + " pm");
                    }
                });
        HBox hb5 = new HBox();
        VBox vbHb5 = new VBox();
        vbHb5.getChildren().addAll(lblTime, lblFormat);
        hb5.getChildren().addAll(vbHb5, sliderTime);
        VBox vb5 = new VBox();
        vb5.getChildren().addAll(hb5, time);
        hb5.setSpacing(10);

        Button btnSubmit = new Button("Submit Order");
        Label lblOrder = new Label(" ");
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                RadioButton selectedRadioButton = (RadioButton) tg.getSelectedToggle();
                if(selectedRadioButton != null && cmbStyles.getValue() != null) {

                    Double cost = 1.055*(Integer.parseInt(selectedRadioButton.getId()) + 0.99 + (topping1.isSelected() ? 1.25 : 0) + (topping2.isSelected() ? 1.25 : 0) + (topping3.isSelected() ? 1.25 : 0));
                    DecimalFormat f = new DecimalFormat("##.00");
                    lblOrder.setText("Size: " + selectedRadioButton.getText() + "\n" + "Style/Type: " + cmbStyles.getValue() + "\nToppings: " + "\n   Pepperoni: " + (topping1.isSelected() ? "Yes" : "No") + "\n   Sausage: " + (topping2.isSelected() ? "Yes" : "No") + "\n   Mushrooms: " + (topping3.isSelected() ? "Yes" : "No") + "\nName: " + txtName.getText() + "\nPickup Time: " + Math.round((Double) sliderTime.getValue()) + " pm" + "\nCost: " + f.format(cost));
                } else {
                    lblOrder.setText("Please fill out every part of the form.");
                }
            }
        });


        Group root = new Group();
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(hb1, hb2, hb3, hb4, vb5, lblOrder, btnSubmit);
        vb1.setSpacing(10);
        root.getChildren().add(vb1);
        vb1.setPadding(new Insets(10, 50, 50, 50));
        vb1.setSpacing(10);



        Scene scene = new Scene(root,600, 600);
        stage.setScene(scene);
        stage.setTitle("Pizza App");
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}