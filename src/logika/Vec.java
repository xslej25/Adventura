package logika;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */


/*******************************************************************************
 * Instance třídy Vec představují předměty ve hře
 *
 * @author    Jan Šlechta
 * @version   0.00.000
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean viditelnost;
    private boolean prenositelnost;
    private boolean namontovatelnost;
    private String image;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelnost, boolean viditelnost,boolean namontovatelnost)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.viditelnost = viditelnost;
        this.namontovatelnost = namontovatelnost;
        this.image = nazev + ".jpg";
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda pro získání jména
     * @param 
     * @return vraci nazev
     */
    public String getNazev() {
        return nazev;       
    }
    
    /**
     * Metoda pro získání namontovatelnosti
     * @param 
     * @return vraci true nebo false
     */
    public boolean getNamontovatelnost(){
        return namontovatelnost;
    }
    
    /**
     * Metoda pro nastavení názvu
     * @param 
     * @return vraci nazev
     */
    public String setNazev(){
        this.nazev = nazev;
        return nazev;
    }
    
    /**
     * Metoda pro získání viditelnosti
     * @param 
     * @return vraci true nebo false
     */
     public boolean getViditelnost() {
        return viditelnost;       
    }
    
    /**
     * Metoda pro nastavení viditelnosti
     * @param 
     * @return vraci true nebo false
     */
    public boolean setViditelnost(){
        this.viditelnost = true;
        return viditelnost;
    }
    
    /**
     * Metoda pro získání prenositelnosti
     * @param 
     * @return vraci true nebo false
     */
    public boolean getPrenositelnost() {
        return prenositelnost;       
    }
    
    /**
     * Metoda pro nastavení přenostitelnosti
     * @param 
     * @return 
     */
    public void setPrenositelnost(){
        this.prenositelnost = false;
    }

    public String getImage() {
        return image;
    }
    

    //== Soukromé metody (instancí i třídy) ========================================

}
