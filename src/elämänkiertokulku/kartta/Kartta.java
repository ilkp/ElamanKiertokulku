
package elämänkiertokulku.kartta;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Kartta {
    private Ruutu[][] ruudut;
    
    public Kartta(int leveys, int pituus) {
        this.ruudut = new Ruutu[leveys][pituus];
    }

    public Ruutu[][] getRuudut() {
        return ruudut;
    }

    public void setRuudut(Ruutu[][] ruudut) {
        this.ruudut = ruudut;
    }
    
    // Ruudut alustetaan ruoka-arvolle 0 testausta varten. Oikea arvojen alustaminen puuttuu.
    // implementaatio: kartalle luodaan nodeja, joiden ympäröiville ruuduille spawnataan ruokaa
    public void alusta() {
        for (int i = 0; i < this.ruudut.length; i++) {
            for (int j = 0; j < this.ruudut[i].length; j++) {
                this.ruudut[i][j] = new Ruutu(i, j, 0, false);
            }
        }
        int spawnerienMaara = this.ruudut.length * this.ruudut[0].length / 100;
        if (spawnerienMaara < 1) {
            spawnerienMaara = 1;
        }
        luoKasvavatRuudut(spawnerienMaara);
    }
    
    public void luoKasvavatRuudut(int spawnerienMaara) {
        Map<Integer, Set<Integer>> sijainnit = new HashMap();
        int spawnerinXkoord;
        int spawnerinYkoord;
        
        for (int i = 0; i < spawnerienMaara; i++) {
            do {
                spawnerinXkoord = (int) (Math.random() * (this.ruudut.length - 2)) + 1;
                spawnerinYkoord = (int) (Math.random() * (this.ruudut[0].length - 2)) + 1;
            } while(!tarkistaSpawnerinSijainti(spawnerinXkoord, spawnerinYkoord, sijainnit));
            
            if (sijainnit.get(spawnerinXkoord) == null) {
                Set<Integer> ySarake = new HashSet();
                ySarake.add(spawnerinYkoord);
                sijainnit.put(spawnerinXkoord, ySarake);
            } else {
                sijainnit.get(spawnerinXkoord).add(spawnerinYkoord);
            }
            ruudutKasvamaan(spawnerinXkoord, spawnerinYkoord);
        }
    }
    
    // Estää spawnerien laittamisen päällekkäin (spawnerin koko 3x3)
    public boolean tarkistaSpawnerinSijainti(int xKoord, int yKoord, Map<Integer, Set<Integer>> sijainnit) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (sijainnit.get(i) != null && sijainnit.get(i).contains(j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Muuttaa spawnerin kohdalta 3x3 alueen kasvamaan kasviruokaa
    public void ruudutKasvamaan(int xKoord, int yKoord) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                this.ruudut[xKoord + i][yKoord + j].setKasvaakoRuoka(true);
                this.ruudut[xKoord + i][yKoord + j].setKasviruoka(100);
            }
        }
    }
    
    // Testausta varten. Valmiissa ohjelmassa käytetään graafista käyttöliittymää.
    public void piirraKartta() {
        String piirrettava = "";
        for (int i = 0; i < this.ruudut.length; i++) {
            for (int j = 0; j < this.ruudut[i].length; j++) {
                if (this.ruudut[i][j].getKasviruoka() < 1) {
                    piirrettava += "|    ";
                }else if (this.ruudut[i][j].getKasviruoka() < 10) {
                    piirrettava += "|   "+this.ruudut[i][j].getKasviruoka();
                } else if (this.ruudut[i][j].getKasviruoka() < 100) {
                    piirrettava += "|  "+this.ruudut[i][j].getKasviruoka();
                } else {
                    piirrettava += "| "+this.ruudut[i][j].getKasviruoka();
                }
            }
            piirrettava += "|\n";
            
            for (int k = 0; k < this.ruudut[i].length; k++) {
                if (this.ruudut[i][k].getLaumat().size() > 0) {
                    piirrettava += "|X  ";
                } else {
                    piirrettava += "|   ";
                }
                if (this.ruudut[i][k].getElaimet().size() > 0) {
                    piirrettava += this.ruudut[i][k].getElaimet().size();
                } else {
                    piirrettava += " ";
                }
            }
            piirrettava += "|\n";
            
            for (int k = 0; k < this.ruudut[i].length; k++) {
                piirrettava += "-----";
            }
            piirrettava += "\n";
        }
        System.out.println(piirrettava);
    }
}
