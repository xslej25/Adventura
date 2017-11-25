package logika;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

/*******************************************************************************
 * Instance třídy PrikazNamontuj představují implementaci příkazu namontuj
 *
 * @author    Jan Šlechta
 * @version   28.12.2013
 */
public class PrikazNamontuj implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    public static final String NAZEV = "namontuj";
    private HerniPlan plan;
    private Kufr kufr;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazNamontuj(HerniPlan plan, Kufr kufr)
    {
        this.plan = plan;
        this.kufr = kufr;
    }

     /**
     *  Provádí příkaz "namontuj". Příkaz umožnuje namontovat díly na tvé auto.
     *  Metoda kontroluje, zda je předmět namontovatelný.
     *  Lze použít pouze pokud jsi v garáži, jinak se vypíše chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno předmětu, který chceš namontovat
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String proved(String... parametry){
        if(parametry.length == 0 || parametry.length >1){
            return "Co mám namontovat?";
        }

        String parametr =  parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec vec = kufr.vyberVec(parametr);
        if(kufr.obsahujeVec(parametr)&& vec.getNamontovatelnost()){
            if(aktualniProstor.getNazev().equals("garaz")){
               kufr.odebiraniVeci(parametr);
                kufr.namontovatDil(vec);
                return "Tvoje auto bylo zlepšeno o " + vec.getNazev();

                
            }else{
                return "Součástky na auto lze namontovat pouze v garáži!!!";
            }
           

        }
    
      if(!vec.getNamontovatelnost() && vec != null){             
                return "toto na auto nelze namontovat";
            
        }
        return parametr +"nemáš v kufru";

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
