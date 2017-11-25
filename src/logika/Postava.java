package logika;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

/*******************************************************************************
 * Instance třídy Postava představují postavy ve hře
 *
 * @author    Jan Šlechta
 * @version   28.12.2013
 */
public class Postava
{
    //== Datové atributy (statické i instancí)======================================
    private String jmeno;
    private String rec;
    private String recB;
    private boolean podminka;
    private String predmet;    
    private String vecicka;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Postava(String jmeno, String rec, String recB, String predmet, String vecicka)
    {
        this.jmeno = jmeno;
        this.rec = rec;
        this.recB = recB;
        this.predmet = predmet;
        this.vecicka = vecicka;

    }
    //== Nesoukromé metody (instancí i třídy) ======================================
   
    /**
     * Metoda pro získání jména
     * @param 
     * @return vraci jmeno
     */
    public String getJmeno(){
        return jmeno;
    }

    /**
     * Metoda pro získání podmínky
     * @param 
     * @return vraci true nebo false
     */
    public boolean getPodminka(){
        return podminka;
    }

    /**
     * Metoda pro nastavení podmínky
     * @param 
     * @return 
     */
    public void setPodminka(){
        podminka = true;
    }

   /**
     * Metoda pro získání názvu věci, kterou postava bude dávat
     * @param 
     * @return vraci nazev veci
     */
    public String dejVec(){
        return vecicka;
    }

    /**
     * Metoda pro získání řeči
     * @param 
     * @return vraci rec
     */
    public String getRec(){
        return rec;
    }

    /**
     * Metoda pro nastavení řeči u postav v závislosti na podmínce
     * @param 
     * @return vraci rec
     */
    public String setRec(){
        if(podminka){
            this.rec = recB;

        }
        return rec;    
    }

    /**porovnání, zda se jedná o správný předmět
     * @param 
     * @return vraci true nebo false
     */
    public boolean spravnyPredmet(String neco){
        if(neco.equals(predmet)){
            return true;}
        return false;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
