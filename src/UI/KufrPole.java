/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import logika.HerniPlan;
import logika.IHra;
import logika.Kufr;
import logika.Vec;
import main.Main;
import utils.Observer;
import utils.ObserverZmenyKufru;

/**
 *
 * @author JanSlechta
 */
public class KufrPole implements ObserverZmenyKufru {

     private IHra hra;
    private HerniPlan plan;
     private Kufr kufr;  
    ListView<Object> list;
   ObservableList<Object> data;
   private TextArea centralText;

    public KufrPole(Kufr kufr, HerniPlan plan,TextArea text){
    this.kufr = kufr;
    kufr.zaregistrujPozorovatele(this);
     centralText = text;
    this.plan = plan;

    hra = plan.getHra();
    
        init();
    }
 
     private void init() {
     list = new ListView<>();
     data = FXCollections.observableArrayList();
     list.setItems(data);
     list.setPrefWidth(200);
     
    String obsah = kufr.obsahKufru();
     List <Vec> seznamVeci = kufr.getObsahKufru();
       String[] vec = obsah.split(" ");
        for (int i = 1; i < vec.length; i++) {
            data.add(vec[i]);         
        }
       
       list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                if (click.getClickCount() == 2) 
                {
                    int index = list.getSelectionModel().getSelectedIndex();
                    
                    
                    List<Vec> seznam  = new ArrayList<Vec>();
                    seznam = kufr.getObsahKufru() ;
                  
                  
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (Vec x : seznam) 
                    {
                       if(pomocna == index)
                       {
                           nazev = x.getNazev();
                       }
                       pomocna++;
                       
                    }
                    System.out.println(seznam.size());
                    System.out.println(nazev);
                    String vstupniPrikaz = "odhod "+nazev;
                 
                    String odpovedHry = hra.zpracujPrikaz("odhod "+nazev);
                    
                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");
                }
            }
           
        }); 
        
        aktualizuj(kufr);
        
 }
    
     public ListView<Object> getList() {
        return list;
    }
     
     /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */
     public void nastaveniKufru (Kufr kufr){
        this.kufr = kufr;
        kufr.zaregistrujPozorovatele(this);
        this.aktualizuj(kufr);
    }

     public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        this.aktualizuj(kufr);
    }
     
     
     
     
   @Override
    public void aktualizuj(Kufr aktualniVeci) {
                
      
       List<Vec> seznam  = new ArrayList<Vec>();
        seznam = kufr.getObsahKufru();
        data.clear();
        for (int i = 0; i < seznam.size(); i++) 
        {
        Vec pomocna = seznam.get(i);
        System.out.println(pomocna.getImage());
        ImageView obrazek = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/"+pomocna.getImage()), 50, 50, false, false));
        data.add(obrazek);
        }
        
 /*       for (Vec vec : seznam) {
            System.out.println(vec.getNazev() + " ");
            data.add(vec.getNazev());
        }
     */  
        
    }
    
    public void novaHra(IHra hra) {
        hra.getKufr().odregistrujPozorovatele(this);
        this.hra = hra;
        this.plan = plan;
        
        hra.getKufr().zaregistrujPozorovatele(this);
        aktualizuj(kufr);
        
    }
}
