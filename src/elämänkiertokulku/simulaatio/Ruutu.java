
package elämänkiertokulku.simulaatio;

import java.util.ArrayList;
import java.util.List;


public class Ruutu {
    private final int xKoord;
    private final int yKoord;
    private int kasviruoka;
    private int liharuoka;
    private List laumat = new ArrayList();
    private List elaimet = new ArrayList();

    public Ruutu(int xKoord, int yKoord) {
        this.xKoord = xKoord;
        this.yKoord = yKoord;
        //Ruoan testaamiseksi toistaiseksi 100
        this.kasviruoka = 100;
    }

    public int getxKoord() {
        return xKoord;
    }

    public int getyKoord() {
        return yKoord;
    }
    
    public int getKasviruoka() {
        return this.kasviruoka;
    }
    
    public void setKasviruoka(int maara) {
        this.kasviruoka = maara;
    }
    
    public int getLiharuoka() {
        return this.liharuoka;
    }
    
    public void setLiharuoka(int maara) {
        this.liharuoka = maara;
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
    
    public void lisaaKasviruoka(int maara) {
        if (this.kasviruoka + maara < 100) {
            this.kasviruoka += maara;
        } else {
            this.kasviruoka = 100;
        }
    }
    
    public void vahennaKasviRuoka(int maara) {
        if (this.kasviruoka - maara > 0) {
            this.kasviruoka -= maara;
        } else {
            this.kasviruoka = 0;
        }
    }
    
    public void lisaaLiharuoka (int maara) {
        this.liharuoka += maara;
    }
    
    public void vahennaLiharuoka (int maara) {
        if (this.liharuoka - maara > 0) {
            this.liharuoka -= maara;
        } else {
            this.liharuoka = 0;
        }
    }
    
    @Override
    public String toString() {
        return "";
    }
}
