
package elämänkiertokulku.simulaatio;


public class LaumaKasvinsyoja extends Lauma {
    
    public LaumaKasvinsyoja(int id, Ruutu ruutu, Ruutu[][] ruudukko) {
        super(id, ruutu, ruudukko);
    }
    
    @Override
    public void ajaLauma() {
        this.jasenet.forEach((elain) -> {
            elain.ajaElain();
        });
        
        maaritaLaumanTavoite();
        switch (this.tavoite) {
            case PAKENE:
                // implement
                break;
            case ODOTA:
                // implement
                break;
            case UUSI_RUOKAPAIKKA:
                this.tavoiteRuutu = seuraavanRuokapaikanSijainti();
                // ei breakkiä -> uuden ruudun laskemisen jälkeen liikutaan myös
            case LIIKU_RUOKA:
                // implement
                int liikkeenXSuunta = this.tavoiteRuutu.getxKoord() - this.ruutu.getxKoord();
                if (liikkeenXSuunta != 0) {
                    liikkeenXSuunta /= Math.abs(liikkeenXSuunta);
                }
                
                int liikkeenYSuunta = this.tavoiteRuutu.getyKoord() - this.ruutu.getyKoord();
                if (liikkeenYSuunta != 0) {
                    liikkeenYSuunta /= Math.abs(liikkeenYSuunta);
                }
                
                int uusiX = this.ruutu.getxKoord() + liikkeenXSuunta;
                int uusiY = this.ruutu.getyKoord() + liikkeenYSuunta;
                
                Ruutu seuraavaRuutu = this.ruudukko[uusiX][uusiY];
                this.setRuutu(seuraavaRuutu);
                
                if (this.ruutu == this.tavoiteRuutu) {
                    this.tavoiteRuutu = null;
                }
                break;
        }
        tyhjennaPoistettavat();
    }
    
    @Override
    public void maaritaLaumanTavoite() {
        if (1 == 0) {
            this.tavoite = LaumanTavoite.PAKENE; //alueella lihansyöjiä -> täytyy implementoida
        } else if (this.tavoiteRuutu != null) {
            this.tavoite = LaumanTavoite.LIIKU_RUOKA;
        } else if (ruoanMaaraAlueella(this.ruutu.getxKoord(), this.ruutu.getyKoord()) < this.RUOAN_RAJAARVO) {
            this.tavoite = LaumanTavoite.ODOTA;
        } else {
            this.tavoite = LaumanTavoite.UUSI_RUOKAPAIKKA;
        }
    }
    
    @Override
    public Ruutu seuraavanRuokapaikanSijainti() {
        double parasArvo = 0;
        int ruoanMaara = 0;
        double ruudunArvo = 0;
        Ruutu palautettava = this.ruudukko[0][0];
        
        for (int i = this.ALUEEN_KOKO; i < this.ruudukko.length - this.ALUEEN_KOKO; i++) {
            for (int j = this.ALUEEN_KOKO; j < this.ruudukko[i].length - this.ALUEEN_KOKO; j++) {
                if (i != this.ruutu.getxKoord() && j != this.ruutu.getyKoord()) {
                    ruoanMaara = ruoanMaaraAlueella(i , j);
                    ruudunArvo = ruoanMaara / this.etaisyysRuudusta(this.ruudukko[i][j]);
                    if (ruudunArvo > parasArvo) {
                        palautettava = this.ruudukko[i][j];
                        parasArvo = ruudunArvo;
                    }
                }
            }
        }
        return palautettava;
    }
    
    @Override
    public int ruoanMaaraAlueella(int x, int y) {
        int maara = 0;
        for (int i = -this.ALUEEN_KOKO; i < this.ALUEEN_KOKO + 1; i++) {
            for (int j = -this.ALUEEN_KOKO; j < this.ALUEEN_KOKO + 1; j++) {
                int xKoord = x + i;
                int yKoord = y + j;
                maara += this.ruudukko[xKoord][yKoord].getKasviruoka();
            }
        }
        return maara;
    }
}
