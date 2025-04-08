package com.daangit.la8kierulf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentDashboardController implements Initializable {

    @FXML
    private TableColumn<User, String> fname;

    @FXML
    private TableColumn<User,String > id;

    @FXML
    private TableColumn<User, String> lname;

    @FXML
    private TableView<User> table;

    ObservableList<User> initData(){
        User u1 = new User ("Hello", "World");
        User u2 = new User ("Hello", "World");
        User u3 = new User ("Hello", "World");
        User u4 = new User ("Hello", "World");
        return FXCollections.observableArrayList(u1, u2, u3, u4);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//    fname.setCellValueFactory(new PropertyValueFactory<User, String >("username"));
//    lname.setCellValueFactory(new PropertyValueFactory<User, String >("password"));
//    id.setCellValueFactory(new PropertyValueFactory<User, String >("username"));

//        id.setCellValueFactory(new PropertyValueFactory<>(""));       // Matches getter: getId()
        fname.setCellValueFactory(new PropertyValueFactory<>("fname")); // Matches getter: getFname()
        lname.setCellValueFactory(new PropertyValueFactory<>("lname")); // Matches getter: getLname()
    table.setItems(initData());
    }

    @FXML
private  void insertData(ActionEvent actionEvent){
//        User = new user(tf.name, tf.lname.gettext)
//        table.getItems.add(newData)
    }
}
