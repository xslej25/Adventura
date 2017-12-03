
package logika;
import java.util.*;
/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2013/2014
 */
public class Prostor {
    
    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Set<Vec> seznamVeci;
    private Set<Postava> seznamPostav;
    private Prostor pomoc;
    private Postava pomocna;
    
    private double posX;
    private double posY;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis, double posX, double posY) {
        this.nazev = nazev;
        this.popis = popis;
        this.posX = posX;
        this.posY = posY;
        vychody = new HashSet<>();
        seznamVeci = new HashSet<>();
        seznamPostav = new HashSet<>();
        
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object object) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == object) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(object instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) object;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu()+ ".\n" + "predmety: " + seznamVeci() + "\n" + 
                "postavy: " + seznamPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    public String popisVychodu() {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    
    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    public String getPopisVychodu() {
        String vracenyText = "";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev()+",";
        }
        return vracenyText;
    }
    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        if (nazevSouseda == null) {
            return null;
        }
        for (Prostor sousedni : vychody) {
            if (sousedni.getNazev().equals(nazevSouseda)) {
                return sousedni;
            }
        }
        return null;  // prostor nenalezen
    }
    
    /**
     * Metoda odkrytí skrytých věcí v místnosti
     * 
     * @param 
     * @return vrací seznam věcí, které byly odkryty
     */
    public String getSkryteVeci(){
       String veci = "";
       for(Vec neco : seznamVeci){
           if(!neco.getViditelnost()){
               veci +=neco.getNazev()+" ";
               neco.setViditelnost();
            }
        }
        return veci;
    }
   
    public Set<Vec> getSeznamVeci()
    {
        return seznamVeci;
    }
    
    
    /**
     * Metoda pro výpis věcí v místnosti
     * 
     * @param 
     * @return výpis věcí z místnosti
     */
    public String seznamVeci(){
        String veci = "";
        for (Vec neco : seznamVeci) {
            if(neco.getViditelnost()){
            veci += neco.getNazev()+" ";
        }
        }
        return veci;
    }
    
   
    /**
     * Metoda pro zjištění, zda je postava v místnosti
     * 
     * @param String postava
     * @return vraci true, pokud se postava v místnosti nachází, jinak false
     */
    public boolean obsahujePostavu(String postava){
        for (Postava pomoc : seznamPostav){
            if(pomoc.getJmeno().equals(postava)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metoda pro zjištění, zda je věc v místnosti
     * 
     * @param String neco
     * @return vrací předmět, pokud je v místnosti, jinak vrací null
     */ 
   public Vec obsahujeVec(String neco){
       if(neco == null){ 
           return  null;
        }
       for(Vec pomoc : seznamVeci){
           if(pomoc.getNazev().equals(neco)){
               return pomoc;
            }
        }
       return null; // predmet nenalezen
    }

     /**
     * Metoda pro odebrání předmětu z místnosti
     * 
     * @param Vec neco
     * @return vraci true, pokud se podaří odebrat předmět, jinak false
     */
    public boolean odebratVec(Vec neco){
        if (neco == null){
            return false;
        }
        
        for(Vec vecicka: seznamVeci){
            if(vecicka.equals(neco)){
            seznamVeci.remove(neco);
            return true;
        }        
        }
        return false;
    }

     /**
     * Metoda pro vybrání postavy z místnosti
     * 
     * @param String postava
     * @return vraci postavu, pokud je v místnosti, jinak null
     */
    public Postava vyberPostavu(String postava){
          if(postava == null){ 
           return  null;
        }
       for(Postava pomoc : seznamPostav){
           if(pomoc.getJmeno().equals(postava)){
               return pomoc;
            }
        }
       return null; // predmet nenalezen
    }
    
     /**
     * Metoda pro výpis postav z místnosti
     * 
     * @param
     * @return vraci seznam postav
     */
     public String seznamPostav(){
        String postavy = "";
        for (Postava nejaka : seznamPostav) {
            
            postavy += nejaka.getJmeno()+" ";
        
        }
        return postavy;
    }
    
  
    
     /**
     * Metoda pro přidání postavy do místnosti
     * 
     * @param Postava postava
     * @return 
     */
    public boolean vlozPostavu (Postava postava){
        if(postava != null){
        seznamPostav.add(postava);
        return true;
    }
    return false;
       
    }
    
     /**
     * Metoda pro zjištění, zda je postava v místnosti
     * 
     * @param String postava
     * @return vraci postavu, pokud se postava v místnosti nachází, jinak null
     */
    public Postava najdiPostavu(String postava){
        for(Postava hledanaPostava : seznamPostav){
            if (hledanaPostava.getJmeno().equals(postava)){
                return hledanaPostava;
            }
        }
        return null;
    }
    
     /**
     * Metoda pro vlotení věci do místnosti
     * 
     * @param Vec neco
     * @return
     */
    public void vlozVec(Vec neco){
     if(neco != null){
            seznamVeci.add(neco);
        }
    
    }
    
    
    public void setPomocnejProstor(Prostor pomoc){
        this.pomoc = pomoc;       
    }
    
    public Prostor getPomocnejProstor(){
        return pomoc;
    }
    
    public void setPomocnaPostava (Postava pomocna){
        this.pomocna = pomocna ;
    }
    
    public Postava getPomocnaPostava(){
        return pomocna;
    }
    
    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
     /**
     * @return the posX
     */
    public double getPosX() {
        return posX;
    }

    /**
     * @return the posY
     */
    public double getPosY() {
        return posY;
    }
}
