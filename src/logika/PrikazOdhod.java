package logika;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */


/*******************************************************************************
 * Instance třídy PrikazOdhod představují implementaci příkazu odhod
 *
 * @author   Jan Šlechta
 * @version  28.12.2013
 */
public class PrikazOdhod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
public static final String NAZEV = "odhod";
 private HerniPlan plan;
 private Kufr kufr;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazOdhod(HerniPlan plan, Kufr kufr)
    {
        this.plan = plan;
        this.kufr = kufr;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
 /**
     *  Provádí příkaz "odhod". Umožnuje odhodit předmět z inventáře. Kontroluje, zda je
     *  předmět v inventáři, pokud ne, vypíše chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno předmětu, který má být odhozen
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String proved(String... parametry){
          
       if (parametry.length == 0) {
            return "Zadejte, co chcete odhodit.(musíš to mít v kufru )";
        }        
       String neco = parametry[0];
       Prostor aktualniProstor = plan.getAktualniProstor();
       Vec vecicka = kufr.odebiraniVeci(neco);
       if (vecicka == null){
           return "Tohle nemáš v kufru";
        }
        aktualniProstor.vlozVec(vecicka);
        return neco + "bylo odhozeno";
        
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
	public String getNazev(){
	    return NAZEV;
	   }
	
}


    //== Soukromé metody (instancí i třídy) ========================================

