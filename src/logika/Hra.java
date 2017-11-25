package logika;
/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2013/2014
 */

public class Hra implements IHra{
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Kufr kufr;
    private boolean dohrano;
    

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        this.herniPlan = herniPlan;
        Kufr kufr = new Kufr();        
        herniPlan = new HerniPlan();
        this.kufr = kufr;
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan, kufr));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazPromluv(herniPlan, kufr));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan, kufr));
        platnePrikazy.vlozPrikaz(new PrikazOdhod(herniPlan, kufr));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazNamontuj(herniPlan, kufr));
        platnePrikazy.vlozPrikaz(new PrikazKufr(kufr));
        platnePrikazy.vlozPrikaz(new PrikazDej(herniPlan, kufr));
         
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Bývaly časy, kdy jsi byl na výslunní celého světa ilegálních závodů. Spolu s bratrem, Carlem,\n" +
               "jste nemohli být přemoženi. Jeden z vás vždy vyhrál. Kolem sebe si měl lidi z různých koutů světa, ale \n" +
               "přesto si je mohl nazývat „rodinou“. Tyto časy jsou ovšem pryč. Zrada přišla od toho, od koho bys jí \n" +
               "čekal nejméně. Tvého bratra. Už ho nebavilo se o svou slávu dělit, proto tě udal na policii. Na základě \n" +
               "jeho udání jsi byl chycen a zavřen na 8 let do vězení, kde si nemohl dostat z hlavy jedinou myšlenku:\n" +
               "„Pomsta bude sladká“. Po 8 letech pekla konečně nadchází den, kdy tato pomsta může být \n" +
               "uskutečněna. Z celé party zbyl jen Carl a tvůj nejlepší kámoš Jamie. Zkus za ním zajít, třeba by ti mohl \n" +
               "pomoci získat nějaké auto a dát nějaké užitečné rady. Z vězení jsi se dostal až na náměstí do tvého \n" +
               "rodného města... Měl bys zajít za tvým nejlepším přítelem Jaimem\n" +
               "\n" +
               
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        if(getRadneDohrano()){
            if(kufr.dobryKonec()){
                return "Nééé, já vím, že to, co jsem udělal, bylo špatné. Dál už s tímto svědomím nemůžu žít. Prosím vemte si tyto důkazy a  běžte pryč... \n" + 
                "Na základě důkazů jste usvědčili šerifa z únosu a dál jste s bratrem žili jako dříve. "+
                "/n"+
                "úspěšně jsi dohrál hru, gratuluji";
            }else{
                return "haha, jestli pak to nejsou naši ubrečení bráchové,kliďte se mi z očí než vás nechám zavřít do vazby... Po tomto uvítání se strhla rvačka,\n" +
                "ve které jste neštastně strčili do šerifa, který spadl na hranu obrubníku a zemřel. Jelikož bylo vše natočené na kamerách, šel jste opět do vězení,\n" +
                "tentokrát za vraždu. U šerifa jste našel důkaz, že váš bratr opravdu jednal na základě vydírání.\n"+
                "/n"+
                "úspěšně jsi dohrál hru, gratuluji";}
            }
        return "díky za hru";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        
        if(herniPlan.getAktualniProstor().obsahujePostavu("serif")){
            setRadneDohrano();
            konecHry = true;
        }
        return textKVypsani;
    }
    
    public void setRadneDohrano(){
        dohrano = true;
    }
    
    public boolean getRadneDohrano(){
        return dohrano;
    }
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
    
}

