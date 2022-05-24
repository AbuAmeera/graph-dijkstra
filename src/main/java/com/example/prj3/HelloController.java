package com.example.prj3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloController {
    @FXML
    private AnchorPane ap;

    @FXML
    private Button browse;

    @FXML
    private TextArea ta;

    @FXML
    private ChoiceBox<String> src;

    @FXML
    private ChoiceBox<String> dest;

    @FXML
    private Button find;

    @FXML
    private Label dist;


int size=0;
    Calc c = new Calc();
    public void browse(ActionEvent actionEvent) {

        FileChooser fileChooserShares = new FileChooser();
        File selectedFile = fileChooserShares.showOpenDialog(null);
        fileChooserShares.setTitle("Select shares file .txt");
        System.out.println(selectedFile);
        if (String.valueOf(selectedFile).equals("null")) {
            return;
        }


        try {
            c.Scan(selectedFile.toString());
            c.fun();
            size = c.citys.size();


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }


    }
    public void read(ActionEvent actionEvent) {

        insertcityonmap();

    }

    double arrx[] = new double[100];
    double arry[] = new double[100];
    private void insertcityonmap() {

        double x,y;
        String name;

        String s1 = null;
        String s2 = null;
        AtomicInteger flag = new AtomicInteger();
        ObservableList< String> s = FXCollections.observableArrayList();
        Button arrb[] =new Button[size];

        int cr=0;
        for (int i=0;i<c.citys.size();i++){

            int space = c.citys.get(i).indexOf(",");
            int space1 = c.citys.get(i).lastIndexOf(",");
             x = Double.parseDouble( c.citys.get(i).substring(space+1,space1) );
             y = Double.parseDouble( c.citys.get(i).substring(space1+1) );
             name = c.citys.get(i).substring(0,space);
             Button b = new Button();

             arrb[i] = b;
            String str = c.getPositionOnScreen(x,y);
            s.add(name);
            space = str.indexOf(" ");
            x = Double.parseDouble(  str.substring(0,space) );
            y = Double.parseDouble(  str.substring(space+1) );
            Label l = new Label(name);
            arrx[i]=x;
            arry[i]=y;
            l.setLayoutY(y-10);
            l.setLayoutX(x);
            arrb[i]=b;
             b.setLayoutY(y);
             b.setLayoutX(x);

            b.setStyle(
                    "-fx-background-radius: 5em; " +
                            "-fx-min-width: 10px; " +
                            "-fx-min-height: 10px; " +
                            "-fx-max-width: 10px; " +
                            "-fx-max-height: 10px;"
            );

            String finalName = name;
            b.setOnAction(e->{
                if (flag.get() == 0){
                    src.setValue(finalName);
                    flag.set(1);
                }
                else {
                    dest.setValue(finalName);
                    flag.set(0);
                }
            });
           // src.setPromptText(name);
            src.setItems(s);
            dest.setItems(s);

            ap.getChildren().addAll(b,l);

        }


    }



    public void find(ActionEvent actionEvent) {

        int vc = 50;
        List<Line> la = new ArrayList<>();
        la.clear();
        ta.clear();
        Calc c = new Calc();
        Graph g = new Graph();
            String str = c.shorts(src.getValue(),dest.getValue());
            ta.appendText(str);
            ta.setDisable(true);

        int space =str.indexOf( "\n");
        String str1 = str.substring(space+1);



        for (int i=0;i<c.list1.size();i++){

            Line l = new Line();
            la.add(l);
           int h = g.find( c.list1.get(i));

            l.setFill(Color.RED);
            l.setStartX(arrx[h]);
            l.setStartY(arry[h]);

            if (i+1 < c.list1.size()) {

                h = g.find(c.list1.get(i + 1));

                l.setEndX(arrx[h]);
                l.setEndY(arry[h]);
                ap.getChildren().add(l);
            }
        }

        c.list1.clear();
    }

    public void reset(ActionEvent actionEvent) {





    }
}
