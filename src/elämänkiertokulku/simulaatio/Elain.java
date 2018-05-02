
package elämänkiertokulku.simulaatio;


public class Elain {
    private int id;
    private double kuolintodennakoisyys;
    protected final Lauma omaLauma;
    protected Ruutu ruutu;
    protected Ruutu tavoiteRuutu;
    protected int ruokatilanne;
    protected ElaimenTavoite tavoite;
    
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
    
    
    
    
    //Alla omat metodit
    
    public void maaritaTavoite(){}
    
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
        this.ruutu = null;
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
        int xKoord = this.ruutu.getxKoord();
        int yKoord = this.ruutu.getyKoord();
        int laumanXKoord = this.omaLauma.getRuutu().getxKoord();
        int laumanYKoord = this.omaLauma.getRuutu().getyKoord();
        if (xKoord > laumanXKoord - 4 && xKoord < laumanXKoord + 4 && yKoord > laumanYKoord - 4 && yKoord < laumanYKoord + 4) {
            return true;
        }
        return false;
    }
    
    
    @Override
    public String toString() {
        return "id "+this.id+":ruoka "+this.ruokatilanne;
    }
}
