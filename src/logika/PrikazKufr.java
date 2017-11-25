package logika;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */


/*******************************************************************************
 * Instance třídy PrikazKufr představují implemtnaci příkazu kufr
 *
 * @author    Jan Šlechta
 * @version   28.12.2013
 */
public class PrikazKufr implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
 public static final String NAZEV = "kufr";
Kufr kufr ;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazKufr( Kufr kufr)
    {
       
        this.kufr = kufr;
    }
 /**
     *  Provádí příkaz "kufr". vypíše předměty z inventáře.
     *
     *@param parametry - bez parametrů
     *@return zpráva, kterou vypíše hra hráči(seznam předmětů v inventáři)
     */ 
    public String proved(String... parametry){
        return  kufr.obsahKufru();
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
