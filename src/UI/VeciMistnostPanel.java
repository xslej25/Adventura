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
import logika.HerniPlan;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 *
 * @author JanSlechta
 */
public class VeciMistnostPanel implements Observer{
    private HerniPlan plan;
    private IHra hra;
    ListView<Object> list;
    ObservableList<Object> data;
    private Set<Vec> seznamVeci;
     private TextArea centralText;
    
    public VeciMistnostPanel(HerniPlan plan,IHra hra, TextArea text){
        this.plan = plan;
        this.hra = hra;
        plan.registerObserver(this);
        centralText = text;
        init();
    }
    
    
    

    @Override
    public void update() {
        Set<Vec> seznam  = new HashSet<Vec>();
        seznam = plan.getAktualniProstor().getSeznamVeci();
        data.clear();
        for (Vec x : seznam) 
        {
        Vec pomocna = x;
            if(pomocna.getViditelnost())
            {
            ImageView obrazek = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/"+pomocna.getImage()), 50, 50, false, false));
            data.add(obrazek);
            }
        }
    }

    public ListView<Object> getList() {
        return list;
    }
    
    public String veciVMistnosti(){
        String veci = null;
        plan.getAktualniProstor().seznamVeci();
        return veci;
    }
    
    public Set<Vec> getSeznamVeci(){
    return seznamVeci;
}
   
    
    
    private void init(){
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(200);
      
       list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                if (click.getClickCount() == 2) 
                {
                    int index = list.getSelectionModel().getSelectedIndex();
                    
                    
                    Set<Vec> pSeznam  = new HashSet<Vec>();
                    pSeznam = plan.getAktualniProstor().getSeznamVeci();      
                    
                    Set<Vec> seznam  = new HashSet<Vec>();
                    for (Vec x : pSeznam) 
                    {
                       if(x.getViditelnost())
                       {
                         seznam.add(x);
                       }
                    }
                    
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
                    String vstupniPrikaz = "seber "+nazev;
                    String odpovedHry = hra.zpracujPrikaz("seber "+nazev);
                    
                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");
                }
            }
        }); 
    update();
   
   
    }
    
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }
    
    @Override
    public void novaHra(IHra hra) {
        hra.getHerniPlan().deleteObserver(this);
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
}
            
        