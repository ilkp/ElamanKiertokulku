
package elämänkiertokulku.simulaatio;


public class Elain {
    private int id;
    protected Ruutu ruutu;
    protected int ruokatilanne;
    protected ElaimenTavoite tavoite;
    
    public Elain(int id, Ruutu ruutu) {
        this.id = id;
        this.ruutu = ruutu;
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
    
    
    //Alla omat metodit
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
    
    @Override
    public String toString() {
        return "id "+this.id+":ruoka "+this.ruokatilanne;
    }
}
