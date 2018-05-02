
package elämänkiertokulku.simulaatio;


public class LaumaKasvinsyoja extends Lauma {
    
    public LaumaKasvinsyoja(int id, Ruutu ruutu, Ruutu[][] ruudukko) {
        super(id, ruutu, ruudukko);
    }
    
    @Override
    public void ajaLauma() {
        this.getJasenet().forEach((elain) -> {
            elain.ajaElain();
        });
        
        maaritaLaumanTavoite();
        switch (this.getTavoite()) {
            case PAKENE:
                // IMPLEMENT
                break;
            case ODOTA:
                // IMPLEMENT
                break;
            case UUSI_RUOKAPAIKKA:
                this.setTavoiteRuutu(seuraavanRuokapaikanSijainti());
                // ei breakkiä -> uuden ruudun laskemisen jälkeen liikutaan myös
            case LIIKU_RUOKA:
                if (this.getLiikkeenVaihe() >= this.getNopeus()) {
                    int liikkeenXSuunta = this.getTavoiteRuutu().getxKoord() - this.getRuutu().getxKoord();
                    if (liikkeenXSuunta != 0) {
                        liikkeenXSuunta /= Math.abs(liikkeenXSuunta);
                    }

                    int liikkeenYSuunta = this.getTavoiteRuutu().getyKoord() - this.getRuutu().getyKoord();
                    if (liikkeenYSuunta != 0) {
                        liikkeenYSuunta /= Math.abs(liikkeenYSuunta);
                    }

                    int uusiX = this.getRuutu().getxKoord() + liikkeenXSuunta;
                    int uusiY = this.getRuutu().getyKoord() + liikkeenYSuunta;

                    Ruutu seuraavaRuutu = this.getRuudukko()[uusiX][uusiY];
                    this.setRuutu(seuraavaRuutu);

                    if (this.getRuutu() == this.getTavoiteRuutu()) {
                        this.setTavoiteRuutu(null);
                    }
                    this.setLiikkeenVaihe(0);
                    break;
                } else {
                    this.setLiikkeenVaihe(this.getLiikkeenVaihe()+1);
                }
                
                if (this.getRuutu().equals(this.getTavoiteRuutu())) {
                    this.setTavoiteRuutu(null);
                }
        }
        tyhjennaPoistettavat();
    }
    
    @Override
    public void maaritaLaumanTavoite() {
        if (1 == 0) {
            this.setTavoite(LaumanTavoite.PAKENE);
            // IMPLEMENT
        } else if (this.getTavoiteRuutu() != null) {
            this.setTavoite(LaumanTavoite.LIIKU_RUOKA);
        } else if (ruoanMaaraAlueella(this.getRuutu().getxKoord(), this.getRuutu().getyKoord()) > this.getRuoanRajaarvo()) {
            this.setTavoite(LaumanTavoite.ODOTA);
        } else {
            this.setTavoite(LaumanTavoite.UUSI_RUOKAPAIKKA);
        }
    }
    
    // Metodia käytetään, kun lauman alueelta loppuu ruoka, ja lauma halutaan siirtää uuteen ruutuun.
    @Override
    public Ruutu seuraavanRuokapaikanSijainti() {
        double parasArvo = 0;
        int ruoanMaara = 0;
        double ruudunArvo = 0;
        Ruutu palautettava = this.getRuudukko()[0][0];
        
        for (int i = this.getAlueenKoko(); i < this.getRuudukko().length - this.getAlueenKoko(); i++) {
            for (int j = this.getAlueenKoko(); j < this.getRuudukko()[i].length - this.getAlueenKoko(); j++) {
                if (i != this.getRuutu().getxKoord() && j != this.getRuutu().getyKoord()) {
                    ruoanMaara = ruoanMaaraAlueella(i , j);
                    ruudunArvo = ruoanMaara / this.etaisyysRuudusta(this.getRuudukko()[i][j]);
                    if (ruudunArvo > parasArvo) {
                        palautettava = this.getRuudukko()[i][j];
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
        for (int i = -this.getAlueenKoko(); i < this.getAlueenKoko() + 1; i++) {
            for (int j = -this.getAlueenKoko(); j < this.getAlueenKoko() + 1; j++) {
                int xKoord = x + i;
                int yKoord = y + j;
                maara += this.getRuudukko()[xKoord][yKoord].getKasviruoka();
            }
        }
        return maara;
    }
}
