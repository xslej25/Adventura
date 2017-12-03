/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logika.HerniPlan;
import logika.Hra;
import logika.IHra;
import utils.Observer;

/**
 *
 * @author JanSlechta
 */
public class PanelVychodu implements Observer
{
     private HerniPlan plan;
    ListView<String> list;
    ObservableList<String> data;
    private TextArea centralText;
    private TextField zadejPrikazTextArea;
    private IHra hra;

    public PanelVychodu(HerniPlan plan,  TextArea text,TextField field, IHra hra) {
       this.plan = plan;
       this.hra = hra;
       centralText = text;
        zadejPrikazTextArea = field;
       plan.registerObserver(this);
        init();
    }

    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(100);
        
         list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                if (click.getClickCount() == 2) 
                {
                    String vstupniPrikaz = "jdi "+list.getSelectionModel().getSelectedItems();
                    String odpovedHry = hra.zpracujPrikaz("jdi "+list.getSelectionModel().getSelectedItem());

                
                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");
                    
                    
                    if (hra.konecHry()) 
                    {
                    zadejPrikazTextArea.setEditable(false);
                    centralText.appendText(hra.vratEpilog());
                    }
                    
                    
                    plan.notifyAllObservers();
                }
            }
        });
       update();

        String vychody = plan.getAktualniProstor().popisVychodu();

        String[] oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
        }
    }
    
     public ListView<String> getList() {
        return list;
    }
     
     /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
        
    }

    public void update() {
        String vychody = plan.getAktualniProstor().popisVychodu();
        data.clear();
        String[] oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
        }
    }

    @Override
    public void novaHra(IHra hra) {
        hra.getHerniPlan().deleteObserver(this);
        hra.getHerniPlan().registerObserver(this);
        update();
    }

}

