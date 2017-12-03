package logika;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

import java.util.*;
import utils.SubjektZmenyKufru;
import utils.ObserverZmenyKufru;
/*******************************************************************************
 * Instance třídy Kufr představují inventáře ve hře
 *
 * @author    Jan Šlechta
 */
public class Kufr implements SubjektZmenyKufru
{
    //== Datové atributy (statické i instancí)======================================
    private int kapacita;
    private List<Vec> obsahKufru;
    private List<Vec> namontovaneDily;
    private List<ObserverZmenyKufru> seznamPozorovatelu;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Kufr()
    {
       
        kapacita = 4;
        obsahKufru = new ArrayList<Vec>();
        namontovaneDily = new ArrayList<Vec>();
        seznamPozorovatelu = new ArrayList<ObserverZmenyKufru>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda pro vlozeni veci do kufru
     * 
     * @param Vec neco, indentifikace veci
     * @return vraci true, pokud se podarilo vlozit vec do kufru, v opacnem pripade vraci false
     */
    public boolean vlozitVec(Vec neco){
        if(obsahKufru.size()< kapacita && neco.getPrenositelnost()){
            obsahKufru.add(neco);
            upozorniPozorovateleKufru(this);
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Metoda pro výběr předmětu z inventáře
     * 
     * @param String neco
     * @return vraci null, pokud předmět neni v inventáři, jinak vrátí hledanou věc
     */
    public Vec vyberVec(String neco){
        Vec vyber = null;
        for(Vec vecicka : obsahKufru){
            if(vecicka.getNazev().equals(neco)){
                vyber = vecicka;
                break;
            }
        }
        return vyber;
    }

    /**
     * Metoda odstranění věci z inventáře
     * 
     * @param String neco
     * @return vraci null, pokud věc neni v inventaři, jinak odstraní věc a vrátí jí na výstupu
     */
    public Vec odebiraniVeci(String neco){
        Vec rusena = null;
        for(Vec vecicka : obsahKufru){
            if(vecicka.getNazev().equals(neco)){
                rusena = vecicka;
                obsahKufru.remove(vecicka);
                upozorniPozorovateleKufru(this);
                break;
            }
        }
        return rusena;
    }

    /**
     * Metoda pro získání počtu věcí v v kufru
     * 
     * @param 
     * @return vrací počet předmětů v inventáři
     */
    public int getPocetVeciVKufru(){
        int pomoc = 0;
        for(Vec vecicka : obsahKufru){
            pomoc ++;
        }
        return pomoc;
    }

    public int getKapacita(){
        return kapacita;
    }

    /**
     * Metoda pro ověření, zda je předmět v inventáři
     * 
     * @param String nazev
     * @return vraci true, pokud hledaný předmět je v inventáři, jinak vrací false
     */
    public boolean obsahujeVec(String nazev){
        boolean jeTu = false;

        for(Vec neco : obsahKufru){
            if (neco.getNazev().equals(nazev)){
                jeTu = true;
                break;
            }
        }
        return jeTu;
    }
    
    /**
     * Metoda pro výpis předmětů z inventáře
     * 
     * @param 
     * @return vraci výpis názvů předmětů z inventáře
     */
    public String obsahKufru(){
        String obsah = "Kufr obsahuje: ";
        for(Vec neco: obsahKufru){
            obsah = obsah + neco.getNazev() + " ";
        }

        return obsah;
    }
   
    
    
    public List<Vec> getObsahKufru()
    {
        return obsahKufru;
    }
    /**
     * Metoda pro přidání věci na auto
     * 
     * @param Vec soucastka
     * @return vraci true, pokud se podarilo vlozit vec na auto, jinak vraci false
     */
    public  boolean namontovatDil(Vec soucastka){
        if (soucastka != null && soucastka.getNamontovatelnost()){
            namontovaneDily.add(soucastka);
            return true;
        }
        return false;
    }
    
    /**
     * Metoda pro ověření, zda je auto dost vylepšeno
     * 
     * @param 
     * @return vraci true, pokud je namontováno dost dílů, jinak vrací false
     */
    public boolean dostDilu(){
        int pomoc = 0;
        for (Vec dil : namontovaneDily){
            pomoc++;
        }
        if(pomoc>3){
            return true;
        }else{
            if(pomoc == 3){
                return true;
            }
            return false;

        }

    }
    
    /**
     * Metoda pro rozhodnutí, který z konců bude použit
     * 
     * @param 
     * @return vraci true, pokud je namontováno více jak 3 díly, jinak false
     */
    public boolean dobryKonec(){
        int pomoc = 0;
        for (Vec dil : namontovaneDily){
            pomoc++;
        }
        if(pomoc>3){
            return true;
        }
        return false;
    }
    //== Soukromé metody (instancí i třídy) ========================================

    @Override
    public void zaregistrujPozorovatele(ObserverZmenyKufru pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }

    @Override
    public void odregistrujPozorovatele(ObserverZmenyKufru pozorovatel) {
        seznamPozorovatelu.remove(pozorovatel);
    }

    @Override
    public void upozorniPozorovateleKufru(Kufr kufr) {
        for (ObserverZmenyKufru pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj(this);
        }
    }
}
