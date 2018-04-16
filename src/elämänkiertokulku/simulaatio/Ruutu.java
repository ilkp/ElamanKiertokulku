
package elämänkiertokulku.simulaatio;

import java.util.ArrayList;
import java.util.List;


public class Ruutu {
    private final int xKoord;
    private final int yKoord;
    private int ruoka;
    private List laumat = new ArrayList();
    private List elaimet = new ArrayList();

    public Ruutu(int xKoord, int yKoord) {
        this.xKoord = xKoord;
        this.yKoord = yKoord;
        //Ruoan testaamiseksi toistaiseksi 100
        this.ruoka = 100;
    }

    public int getxKoord() {
        return xKoord;
    }

    public int getyKoord() {
        return yKoord;
    }
    
    public int getRuoka() {
        return ruoka;
    }

    public void setRuoka(int ruoka) {
        this.ruoka = ruoka;
    }

    public List getLaumat() {
        return laumat;
    }

    public List getElaimet() {
        return elaimet;
    }
    
    public void lisaaLauma(Lauma lauma) {
        this.laumat.add(lauma);
    }
    
    public boolean poistaLauma(Lauma lauma) {
        if (this.laumat.contains(lauma)) {
            this.laumat.remove(lauma);
            return true;
        }
        return false;
    }
    
    public void lisaaElain(Elain elain) {
        this.elaimet.add(elain);
    }
    
    public boolean poistaElain(Elain elain) {
        if (this.elaimet.contains(elain)) {
            this.elaimet.remove(elain);
            return true;
        }
        return false;
    }
    
    public void lisaaRuoka(int maara) {
        if (this.ruoka + maara < 100) {
            this.ruoka += maara;
        } else {
            this.ruoka = 100;
        }
    }
    
    public void vahennaRuoka(int maara) {
        if (this.ruoka - maara > 0) {
            this.ruoka -= maara;
        } else {
            this.ruoka = 0;
        }
    }
    
    @Override
    public String toString() {
        return ""+this.ruoka;
    }
}
