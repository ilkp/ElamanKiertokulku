
package elämänkiertokulku.simulaatio;

import java.util.ArrayList;
import java.util.List;


public class Lauma {
    private int id;
    private Ruutu ruutu;
    private List<Elain> jasenet = new ArrayList();
    
    public Lauma(int id, Ruutu ruutu) {
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

    public List<Elain> getJasenet() {
        return jasenet;
    }

    public void setJasenet(List<Elain> jasenet) {
        this.jasenet = jasenet;
    }
    
    
    
    public void lisaaJasen(Elain elain) {
        elain.getRuutu().lisaaElain(elain);
        this.jasenet.add(elain);
    }
    
    public boolean poistaJasen(Elain jasen) {
        if (this.jasenet.contains(jasen)) {
            this.jasenet.remove(jasen);
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return this.id+" "+this.jasenet;
    }
}
