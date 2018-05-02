
package elämänkiertokulku.simulaatio;


public class Elain {
    private int id;
    private double kuolintodennakoisyys;
    private final Lauma omaLauma;
    private Ruutu ruutu;
    private Ruutu tavoiteRuutu;
    private int ruokatilanne;
    private ElaimenTavoite tavoite;
    
    public Elain(int id, Ruutu ruutu, Lauma lauma) {
        this.id = id;
        this.ruutu = ruutu;
        this.omaLauma = lauma;
        this.kuolintodennakoisyys = 0.01;
    }
    
    //Alla getterit ja setterit
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ruutu getRuutu() {
        return ruutu;
    }

    public void setRuutu(Ruutu ruutu) {
        this.ruutu = ruutu;
    }

    public int getRuokatilanne() {
        return ruokatilanne;
    }

    //Käytä vain testaukseen. Simulaatiossa käytä 'vahennaRuoka(int muutos)' tai 'lisaaRuoka(int muutos)'
    public void setRuokatilanne(int ruokatilanne) {
        this.ruokatilanne = ruokatilanne;
    }
    
    public ElaimenTavoite getTavoite() {
        return this.tavoite;
    }
    
    public void setTavoite(ElaimenTavoite tavoite) {
        this.tavoite = tavoite;
    }

    public Ruutu getTavoiteRuutu() {
        return tavoiteRuutu;
    }

    public void setTavoiteRuutu(Ruutu tavoiteRuutu) {
        this.tavoiteRuutu = tavoiteRuutu;
    }

    public double getKuolintodennakoisyys() {
        return kuolintodennakoisyys;
    }

    public void setKuolintodennakoisyys(double kuolintodennakoisyys) {
        this.kuolintodennakoisyys = kuolintodennakoisyys;
    }
    
    public Lauma getLauma() {
        return this.omaLauma;
    }
    
    
    
    
    //Alla omat metodit
    
    // Metodin nimeämistä varten, käyttö aliluokassa
    public void maaritaTavoite(){}
    
    // Metodia kutsuu eläimen oma lauma. ajaElain testaa kuoleeko eläin ja tekee tarvittavat toimenpiteet.
    public void ajaElain() {
        
        if (!kuoleekoElain()) {
            maaritaTavoite();
            
            if (this.tavoite == ElaimenTavoite.PAKENE) {
                // implement
            } else if (this.tavoite == ElaimenTavoite.LIIKU_LAHIN_RUOKA) {
                
            }
            
        } else {
            tapaElain();
        }
    }
    
    public boolean kuoleekoElain() {
        double rnd = Math.random();
        if (rnd < this.kuolintodennakoisyys) {
            return true;
        }
        this.kuolintodennakoisyys += 0.005;
        return false;
    }
    
    public void tapaElain() {
        this.ruutu.lisaaLiharuoka(100);
        this.omaLauma.siirraPoistettaviin(this);
        this.ruutu.getElaimet().remove(this);
    }
    
    public void lisaaRuoka(int muutos) {
        if (this.ruokatilanne + muutos < 100) {
            this.ruokatilanne += muutos;
        } else {
            this.ruokatilanne = 100;
        }
    }
    
    public void vahennaRuoka(int muutos) {
        if (this.ruokatilanne - muutos > 0) {
            this.ruokatilanne += muutos;
        } else {
            this.ruokatilanne = 0;
        }
    }
    
    // tarkistaa onko eläin oman lauman alueella
    public boolean onkoLaumanAlueella() {
        int alueenKoko = this.omaLauma.getAlueenKoko();
        int xKoord = this.ruutu.getxKoord();
        int yKoord = this.ruutu.getyKoord();
        int laumanXKoord = this.omaLauma.getRuutu().getxKoord();
        int laumanYKoord = this.omaLauma.getRuutu().getyKoord();
        return xKoord > laumanXKoord - alueenKoko && xKoord < laumanXKoord + alueenKoko && yKoord > laumanYKoord - alueenKoko && yKoord < laumanYKoord + alueenKoko;
    }
    
    
    @Override
    public String toString() {
        return "id "+this.id+":ruoka "+this.ruokatilanne;
    }
}
