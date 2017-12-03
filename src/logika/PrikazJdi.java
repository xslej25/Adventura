package logika;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jan Šlechta
 *@version    28.12.2013
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Kufr kufr;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan, Kufr kufr) {
        this.plan = plan;
        this.kufr = kufr;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        Media sound = new Media(this.getClass().getResource("/zdroje/blbost.mp3").toString());
        
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        
        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
       
        if(smer.equals("zavodSCarlem")){
            if(kufr.dostDilu()){
                plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
            }else{
            return "Na tenhle závod nemáš dost vylepšené auto";
            }
        }
        
        
             if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else {
            mediaPlayer.play();
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
