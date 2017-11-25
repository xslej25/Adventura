package logika;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */


/*******************************************************************************
 *  PrikazSeber představuje příkaz, pomocí kterého lze zvednout předmět
 *
 * @author    Jan Šlechta
 * @version  28.12.2013
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
     private static final String NAZEV = "seber";
     private HerniPlan plan;
     private Kufr kufr;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazSeber(HerniPlan plan, Kufr kufr)
    {
        this.plan = plan;
        this.kufr = kufr;
    }
    
    /**
     *  Provádí příkaz "jdi". Pokouší se sebrat předmět z místnosti. Předmět se seber jen v případě,
     *  že jsou dodrženy stanovené podmínky(přenositelnost, viditelnost, volné místo v inventáři,předmět
     *  musí existovat).
     *
     *@param parametry - jako  parametr obsahuje jméno předmětu),
     *                         který má sebrat
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String proved(String... parametry){
        if(parametry.length == 0){
            return "Co mam sebrat?";
        }
        Prostor aktualniProstor = plan.getAktualniProstor();
        String neco = parametry[0];
        Vec vecicka = plan.getAktualniProstor().obsahujeVec(neco);
        if (vecicka == null ){
            
            return "To tady neni";
        
        }
        if(kufr.getPocetVeciVKufru()<kufr.getKapacita()){
        if(vecicka.getPrenositelnost() && vecicka.getViditelnost() ){
            kufr.vlozitVec(vecicka);
            aktualniProstor.odebratVec(vecicka);
        
        }else{
            if(vecicka.getViditelnost()){
              
            return "nelze odnest";
           }else{
               
               
               return "nic takoveho tu neni";
            
        }
    }
}else{
    return "Máš plnej kufr";
}
        
       return  neco +" byl sebrán";
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
