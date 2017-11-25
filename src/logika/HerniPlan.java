package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Jan šlechta
 *@version  28.12.2013
 */
public class HerniPlan implements Subject {
    
    private List<Observer> listObserveru = new ArrayList<Observer>();
    private Prostor aktualniProstor;
    
    private Kufr kufr;
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
       this.kufr = kufr;
       
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        
        // vytvářejí se jednotlivé prostory
        Prostor namesti = new Prostor("namesti"," namesti,na kterem je fontana a je to velke rozcesti", 110.5,40);
        Prostor vrakoviste = new Prostor("vrakoviste", " vrakoviste, mraky plechu, dost materialu", 110.5,40);
        Prostor tunningShop = new Prostor("tunningShop"," Obchůdek s autodíly", 110.5,40);
        Prostor zavodSCarlem = new Prostor("zavodSCarlem"," jdeš vyzvat Carla", 110.5,40);
        Prostor garaz = new Prostor("garaz"," garaz, konečně možnost namontovat díly na auto", 110.5,40);
        Prostor prace = new Prostor("prace","práce, možnost si vydělat peníze", 110.5,40);
        Prostor jaimehoBarak = new Prostor("jaimehoBarak","Dobrej kámoš, vždycky poradí", 110.5,40);
        Prostor zkusebniDraha = new Prostor("zkusebniDraha", " otestoval si svoje auto, zeptej se personalu, jak to dopadlo", 110.5,40);
        Prostor mamincinyDobrutky = new Prostor("mamincinyDobrutky"," cukrářství, určitě půjdou získat nějaké dobrůtky", 110.5,40);
        Prostor policejniStanice = new Prostor("policejniStanice"," konečně si našel mistra šerifa", 110.5,40);
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        namesti.setVychod(vrakoviste);
        namesti.setVychod(garaz);
        namesti.setVychod(jaimehoBarak);
        namesti.setVychod(prace);
        namesti.setVychod(mamincinyDobrutky);
        namesti.setVychod(zavodSCarlem);
        namesti.setVychod(tunningShop);
        garaz.setVychod(jaimehoBarak);
        tunningShop.setVychod(zkusebniDraha);
        tunningShop.setVychod(namesti);
        vrakoviste.setVychod(namesti);
        garaz.setVychod(namesti);
        jaimehoBarak.setVychod(namesti);
        jaimehoBarak.setVychod(garaz);
        tunningShop.setVychod(namesti);
        prace.setVychod(namesti);
        mamincinyDobrutky.setVychod(namesti);
        zavodSCarlem.setVychod(namesti);
        zkusebniDraha.setVychod(tunningShop);
        policejniStanice.setVychod(zavodSCarlem);
        zavodSCarlem.setVychod(policejniStanice);
                
        aktualniProstor = namesti;  // hra začíná na namesti  
        
        Vec fontana = new Vec("fontana",false,true,false);
        namesti.vlozVec(fontana);
        Vec okousanyRohlik = new Vec("okousanyRohlik",true, false,false);
        namesti.vlozVec(okousanyRohlik);
        Vec turbo = new Vec ("turbo",true, true, true);
        Vec brzdy = new Vec ("brzdy",true, false,true);
        vrakoviste.vlozVec (brzdy);
        Vec dusik = new Vec ("dusik", true, false,true);       
        Vec karbonovaKaroserie = new Vec("karbonovaKaroserie", true, false,true);
        tunningShop.vlozVec(karbonovaKaroserie);
        Vec rozbitaAuta = new Vec ("rozbitaAuta", false, true,false);
        vrakoviste.vlozVec(rozbitaAuta);
        Vec spoiler = new Vec ("spoiler", true, false, true);
        vrakoviste.vlozVec(spoiler);
        Vec ztracenyNausnice = new Vec ("ztracenaNausnice",true, false,false);
        namesti.vlozVec(ztracenyNausnice);        
        Vec dort = new Vec ("dort", true, true,false);
        Vec prevodovka = new Vec ("prevodovka", true, true,true);
        Vec kamenA = new Vec ("kamenA", true, false,false);
        prace.vlozVec(kamenA);
        Vec kamenB = new Vec ("kamenB", true, false,false);
        prace.vlozVec(kamenB);
        Vec kamenC = new Vec ("kamenC", true, false,false);
        prace.vlozVec(kamenC);
        Vec poukazNaDil = new Vec ("poukazNaDil",true, true,false);
        Vec naradi = new Vec("naradi",false, true, false);
        garaz.vlozVec(naradi);
       
       
        Postava sef = new Postava ("sef","Ahoj, mám pro tebe možnost vyhrát poukaz na díl. Musíš uhádnout naší otázku: kolik je 2+0?/ Máš na výběr a)1, b)2, c)3... Odpovídej pomocí kamenů, které leží před tebou.(např. pro odpověd a), mi dej kamenA). Ted tě nechám přemýšlet)","Výborně, vyhrál si poukaz na díl!!!", "kamenB","poukazNaDil");
        prace.vlozPostavu(sef);
        Postava kolemjdouci = new Postava ("kolemjdouci","Dobrý den, dneska je krásně ","už jsme se zdravili",null, null );
        namesti.vlozPostavu(kolemjdouci);
        Postava jaime = new Postava("jaime","ahoj, jsem rád, že tě pustili, koukám, že už máš auto... Mám tady navíc převodvku, dám ti ji za něco sladkýho, vždyt víš, že jsem ujetej","super, děkuju, pokud potřebuješ turbo, myslím, že na vrakovišti je chlápek, co ti pomůže","dort","prevodovka");
        jaimehoBarak.vlozPostavu(jaime );
        Postava coudy = new Postava("coudy", "ahoj, mám hrozný hlad, neměl bys něco k snědku?","děkuji ti, myslím, že tohle se ti bude hodit","okousanyRohlik", "turbo");
        Postava prodavac = new Postava("prodavac", "dobrý den, co to bude? Máte nějaký poukaz?","Bohužel, když koukám na vaše auto, už nemám žádní díl, který by vám Vaše auto vylepšil","poukazNaDil", "dusik");
        tunningShop.vlozPostavu(prodavac );
        Postava prodavacka = new Postava("prodavacka", "dobrý den, omlouvám se, ale ztratila jsem náušnici po babičce, bez ní nemůžu prodávat?","Děkuji moc za pomoc, dám vám alespoň dort","ztracenaNausnice", "dort");
        mamincinyDobrutky.vlozPostavu(prodavacka );
        Postava serif = new Postava ("serif",""  , "",null, null);
                    
        Postava personal = new Postava ("personal",  "podle meho auto potřebuje ještě vylepšit","tohle auto nemá konkurenci", null, null );
        zkusebniDraha.vlozPostavu(personal);
        Postava carl = new Postava("carl", "Vyhrál si závod a je milíto co jsem udělal, ale já tě musel udat, protože policejní šerif měl mojí manželku, pojď se mnou za ním a dostanem z něj pravdu ","Opravdu, nekecám ti, pojď a uvidíš ",null, null);
        zavodSCarlem.vlozPostavu(carl);        
       
        zavodSCarlem.setPomocnejProstor(policejniStanice);
        zkusebniDraha.setPomocnaPostava(personal);
        zavodSCarlem.setPomocnaPostava(serif);
        namesti.setPomocnaPostava(kolemjdouci);
        jaimehoBarak.setPomocnejProstor(vrakoviste);
        jaimehoBarak.setPomocnaPostava(coudy);
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    
    public Kufr getKufr(){
        return this.kufr;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
    
    
}
