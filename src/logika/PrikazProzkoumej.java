package logika;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */


/*******************************************************************************
 * Instance třídy PrikazProzkoumej představují implemtanci příkazu Prozkoumej
 *
 * @author    Jan Šlechta
 * @version   28.12.2013
 */
public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazProzkoumej(HerniPlan plan)
    {
        this.plan = plan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
     /**
     *  Provádí příkaz "Prozkoumej". Prozkoumává aktuální místnost. Odhaluje skryté předměty v místnosti.
     *  pokud nezadáš jméno místnosti, vypíše se chybová hláška.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru, který se má prozkoumat.
     *@return zpráva, kterou vypíše hra hráči(seznam předmětů)
     */ 
    public String proved(String... parametry){
         String text="";
       if (parametry.length == 0) {
            return "Zadejte, co chcete prozkoumat.(mistnost )";
        }
        
       String objekt = parametry[0];
      System.out.println("predmety v mistnosti:"+ plan.getAktualniProstor().seznamVeci());
       
       if(objekt.equals(plan.getAktualniProstor().getNazev())){
            
            String neco = plan.getAktualniProstor().getSkryteVeci();
            if (neco.equals("")){
               text="nic noveho jsi nenasel";
                
            }else{
                plan.notifyAllObservers();
               text= "Nasel jsi: "+ neco;
            }
        
        }
        return text;
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
	public String getNazev(){
	    return NAZEV;
	   }


    //== Soukromé metody (instancí i třídy) ========================================

}
