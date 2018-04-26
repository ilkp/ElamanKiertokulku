
package elämänkiertokulku.simulaatio;


public class LaumaKasvinsyoja extends Lauma {
    
    public LaumaKasvinsyoja(int id, Ruutu ruutu, Ruutu[][] ruudukko) {
        super(id, ruutu, ruudukko);
    }
    
    @Override
    public void maaritaLaumanTavoite() {
        if (1 == 0) {
            this.tavoite = LaumanTavoite.PAKENE; //alueella lihansyöjiä -> täytyy implementoida
        } else if (ruoanMaaraAlueella(this.ruutu.getxKoord(), this.ruutu.getyKoord()) < this.RUOAN_RAJAARVO) {
            this.tavoite = LaumanTavoite.ODOTA;
        } else {
            this.tavoite = LaumanTavoite.LIIKU_RUOKA;
        }
    }
    
    public Ruutu seuraavanRuokapaikanSijainti() {
        double parasArvo = 0;
        int ruoanMaara = 0;
        double ruudunArvo = 0;
        Ruutu palautettava = this.ruudukko[0][0];
        
        for (int i = this.ALUEEN_KOKO; i < this.ruudukko.length - this.ALUEEN_KOKO; i++) {
            for (int j = this.ALUEEN_KOKO; j < this.ruudukko[i].length - this.ALUEEN_KOKO; j++) {
                ruoanMaara = ruoanMaaraAlueella(i , j);
                if (ruoanMaara > parasArvo) {
                    //palautettava = this.ruudukko[];
                }
            }
        }
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
