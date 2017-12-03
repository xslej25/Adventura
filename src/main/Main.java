/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.KufrPole;
import UI.Mapa;
import UI.MenuPole;
import UI.PanelVychodu;
import UI.VeciMistnostPanel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.HerniPlan;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 *
 * @author JanSlechta
 */
public class Main extends Application {

    private Mapa mapa;
    private MenuPole menu;
    private IHra hra;
    private TextArea centerText;
    private Stage primaryStage;
   private  KufrPole kufrPole;
    private PanelVychodu panelVychodu;
    private VeciMistnostPanel veciMistnostPanel;
    private HerniPlan plan;
    
    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        hra = new Hra();
        mapa = new Mapa(hra);
        menu = new MenuPole(this);
        
        
          Label zadejPrikazLabel = new Label("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        TextField zadejPrikazTextField = new TextField("Sem zadej prikaz");
        
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String zadanyPrikaz = zadejPrikazTextField.getText();
                String odpoved = hra.zpracujPrikaz(zadanyPrikaz);
                
                centerText.appendText("\n" + zadanyPrikaz + "\n");
                centerText.appendText("\n" + odpoved + "\n");
                
                zadejPrikazTextField.setText("");
                
                if(hra.konecHry()){
                    zadejPrikazTextField.setEditable(false);
                }
                
            }
        });
        
       
        
        //Dolni panel
        FlowPane dolniPanel = new FlowPane();
        dolniPanel.setAlignment(Pos.CENTER);
        dolniPanel.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
        
        //text s vypisem
        centerText = new TextArea();
        centerText.setText(hra.vratUvitani());
        centerText.setEditable(false);
        
        
        kufrPole = new KufrPole(hra.getKufr(), hra.getHerniPlan(),centerText);
        BorderPane borderPane = new BorderPane();
          
        BorderPane pravyPanel = new BorderPane();
        
        veciMistnostPanel = new VeciMistnostPanel(hra.getHerniPlan(),hra, centerText);
       
        
        //Levy panel
        BorderPane levyPanel = new BorderPane();
        levyPanel.setTop(mapa);
       
        levyPanel.setCenter(veciMistnostPanel.getList());
        borderPane.setCenter(centerText);
        
        
         
        panelVychodu = new PanelVychodu(hra.getHerniPlan(),centerText,zadejPrikazTextField, hra); 
        pravyPanel.setCenter(panelVychodu.getList());
        pravyPanel.setTop(kufrPole.getList());

       
        
        
      
        borderPane.setRight(pravyPanel);
        //panel prikaz
        borderPane.setBottom(dolniPanel);
        //obrazek s mapou
        borderPane.setLeft(levyPanel);
        //menu adventury
        borderPane.setTop(menu);
     //   borderPane.setRight(kufrPole.getList());
        Scene scene = new Scene(borderPane, 800, 650);

        primaryStage.setTitle("Moje Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        zadejPrikazTextField.requestFocus();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
                textoveRozhrani.hraj();
            } else {
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

    public void novaHra() {
      start(primaryStage);
    }

    /**
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

}
