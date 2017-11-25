package logika;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

/*******************************************************************************
 * Instance třídy PrikazPromluv představují implementaci příkazu promluv
 *
 * @author    Jan Šlechta
 * @version   28.12.2013
 */
public class PrikazPromluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    public static final String NAZEV = "promluv";
    private HerniPlan plan;
    private Kufr kufr;
    private Hra hra;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazPromluv(HerniPlan plan, Kufr kufr)
    {
        this.plan = plan;
        this.kufr = kufr;
    }

     /**
     *  Provádí příkaz "promluv". Příkazem se snažíme promluvit s postavami. 
     *  Postava musí být v místnosti, jinak se vypíše chybová hláška.
     *  Metoda obsahuje různé podmínky, pokud je oslovena určitá postava, provedou se kromě 
     *  vypsání testu ještě další události.
     *
     *@param parametry - jako parametr obsahuje jméno postavy, se kterou se snažíme promluvit
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String proved(String... parametry){

        if(parametry.length == 0 || parametry.length >1){
            return "Koho se mám zeptat? Zadej jmeno postavy.";
        }
        String pomocny;
        String parametrPromluv = parametry[0];
        Postava tazanaPostava = plan.getAktualniProstor().najdiPostavu(parametrPromluv);

        if(parametrPromluv.equals("kolemjdouci") ){
            pomocny =  tazanaPostava.setRec();
           tazanaPostava.setPodminka();
            return pomocny;

        }

       if(parametrPromluv.equals("carl") ){
            pomocny = tazanaPostava.setRec();
            tazanaPostava.setPodminka();
            plan.getAktualniProstor().getPomocnejProstor().vlozPostavu( plan.getAktualniProstor().getPomocnaPostava());
            return pomocny;

        }

        if(parametrPromluv.equals("serif")&& kufr.dobryKonec() ){
            tazanaPostava.setPodminka();
            hra.setRadneDohrano();
            hra.setKonecHry(true);
        }
        

        if(parametrPromluv.equals("personal") ){
            if (kufr.dostDilu()){
                plan.getAktualniProstor().getPomocnaPostava().setPodminka();
                pomocny =  plan.getAktualniProstor().getPomocnaPostava().setRec();
                return pomocny;
            }else{
                pomocny =  plan.getAktualniProstor().getPomocnaPostava().setRec();
            }
        }
        
        if(parametrPromluv.equals("sef")){
           plan.getAktualniProstor().getSkryteVeci();
        }

        if(tazanaPostava == null){
            return "ten tady neni";                                          
        }

        return tazanaPostava.setRec();
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
