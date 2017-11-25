package logika;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */


/*******************************************************************************
 * Příkaz "dej" představuje způsob, jak darovat předět
 *
 * @author    Jan Šlechta
 * @version   28.12.2013
 */
public class PrikazDej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    public static final String NAZEV = "dej";
    private HerniPlan plan;
    private Kufr kufr;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazDej(HerniPlan plan, Kufr kufr)
    {
        this.plan = plan;
        this.kufr = kufr;
    }
    
    /**
     *  Provádí příkaz "dej". Pokousi se zvolene postave darovat předmět z inventáře.
     *  Pokud postava tento předmět nechce, nebo tento předmět nemáš, vypíše se chybová zpráva.
     *  Pokud vše proběhne v pořádku, vypíše se zpráva a postava vám dá předmět na oplátku.
     *  
     *
     *@param parametry - jako  parametr obsahuje název předmětu a jméno postavy
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String proved(String... parametry){
        if(parametry.length < 2 || parametry.length > 2 ){
            return "Komu mam co dat? musis zadat prikaz dej+co";
        }
        
        String neco = parametry[0];
        String komu = parametry[1];
       Prostor aktualniProstor = plan.getAktualniProstor();
       Postava pomocna = aktualniProstor.vyberPostavu(komu);
       if(kufr.obsahujeVec(neco)&& pomocna != null){
          if(pomocna.spravnyPredmet(neco)){
       kufr.odebiraniVeci(neco);
       pomocna.setPodminka();
       pomocna.setRec();
       Vec vecicka = new Vec( pomocna.dejVec(), true, true, true);
       aktualniProstor.vlozVec(vecicka);
       if(komu.equals("jaime") && neco.equals("dort") ){
           Prostor cil = aktualniProstor.getPomocnejProstor();
           cil.vlozPostavu(aktualniProstor.getPomocnaPostava());
           aktualniProstor.getPomocnaPostava().setRec();
        }
        System.out.println(pomocna.getRec());
       return "daroval si: "+ neco + "a na oplatku si muzes vzit: " + vecicka.getNazev();}
       else{
           if(kufr.obsahujeVec(neco)){
           return "tohle "+ komu + " nechce";
        }else{
            return "Tohle nemáš v kufru";
        }
       }
    }
       return null;
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev(){
        return NAZEV;
    }
    //== Nesoukromé metody (instancí i třídy) ======================================


    //== Soukromé metody (instancí i třídy) ========================================

}
