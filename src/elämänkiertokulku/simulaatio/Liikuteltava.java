
package elämänkiertokulku.simulaatio;

import elämänkiertokulku.kontrolleri.Kontrolleri;



public class Liikuteltava {
    private final Kontrolleri kontrolleri;
    private final int id;
    private final int NOPEUS;
    private int liikeVaihe = 0;
    private Ruutu omaRuutu;
    private Ruutu tavoiteRuutu;

    public Liikuteltava(Kontrolleri kontrolleri, int id, int NOPEUS, Ruutu omaRuutu) {
        this.kontrolleri = kontrolleri;
        this.id = id;
        this.NOPEUS = NOPEUS;
        this.omaRuutu = omaRuutu;
    }
    
    public Kontrolleri getKontrolleri() {
        return this.kontrolleri;
    }
    
    public int getId() {
        return this.id;
    }

    public int getLiikeVaihe() {
        return liikeVaihe;
    }

    public void setLiikeVaihe(int liikeVaihe) {
        this.liikeVaihe = liikeVaihe;
    }

    public Ruutu getTavoiteRuutu() {
        return tavoiteRuutu;
    }

    public void setTavoiteRuutu(Ruutu tavoiteRuutu) {
        this.tavoiteRuutu = tavoiteRuutu;
    }
    
    public int getNopeus() {
        return this.NOPEUS;
    }

    public Ruutu getOmaRuutu() {
        return omaRuutu;
    }

    public void setOmaRuutu(Ruutu omaRuutu) {
        this.omaRuutu = omaRuutu;
    }
    
    public int etaisyysRuudusta(Ruutu ruutu) {
        int yMuutos = ruutu.getyKoord() - this.omaRuutu.getyKoord();
        int xMuutos = ruutu.getxKoord() - this.omaRuutu.getxKoord() - yMuutos;
        return yMuutos + xMuutos;
    }
    
    public void liiku() {
        if (this.liikeVaihe < this.NOPEUS) {
            this.liikeVaihe++;
            return;
        }
        
        Ruutu uusiRuutu = seuraavaRuutu(liikkeenSuunta());
        this.omaRuutu.poistaIdMukaan(this.id);
        this.omaRuutu = uusiRuutu;
        if (this instanceof Elain) {
            uusiRuutu.lisaaElain((Elain) this);
        } else {
            uusiRuutu.lisaaLauma((Lauma) this);
        }
        
        if (this.omaRuutu == this.tavoiteRuutu) {
            this.tavoiteRuutu = null;
        }
        
        this.liikeVaihe = 0;
    }
    
    public int[] liikkeenSuunta() {
        int liikkeenXSuunta = this.tavoiteRuutu.getxKoord() - this.omaRuutu.getxKoord();
        if (liikkeenXSuunta != 0) {
            liikkeenXSuunta /= Math.abs(liikkeenXSuunta);
        }
        int liikkeenYSuunta = this.tavoiteRuutu.getyKoord() - this.omaRuutu.getyKoord();
        if (liikkeenYSuunta != 0) {
            liikkeenYSuunta /= Math.abs(liikkeenYSuunta);
        }
        return new int[] {liikkeenXSuunta, liikkeenYSuunta};
    }
    
    public Ruutu seuraavaRuutu(int[] liikkeenSuunta) {
        int uusiX = this.omaRuutu.getxKoord() + liikkeenSuunta[0];
        int uusiY = this.omaRuutu.getyKoord() + liikkeenSuunta[1];
        return this.kontrolleri.getKartta().getRuudut()[uusiX][uusiY];
    }
}
