
package elämänkiertokulku.simulaatio;


public class Elain {
    private int id;
    private Ruutu ruutu;
    private int ruokatilanne;
    private String tavoite;
    
    public Elain(int id, Ruutu ruutu) {
        this.id = id;
        this.ruutu = ruutu;
    }
    
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

    public void setRuokatilanne(int ruokatilanne) {
        this.ruokatilanne = ruokatilanne;
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
    
    @Override
    public String toString() {
        return "id "+this.id+":ruoka "+this.ruokatilanne;
    }
}
