
package elämänkiertokulku.simulaatio;

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
        /*
        int spawnerienMaara = this.ruudut.length * this.ruudut[0].length / 10;
        Map<Integer, Set<Integer>> sijainnit = new HashMap();
        Map<Integer, Set<Integer>> kaytetyt = new HashMap();
        int spawnerinXkoord;
        int spawnerinYkoord;
        
        for (int i = 0; i < spawnerienMaara; i++) {
            do {
                spawnerinXkoord = (int) (Math.random() * (this.ruudut.length - 2)) + 1;
                spawnerinYkoord = (int) (Math.random() * (this.ruudut[0].length - 2)) + 1;
            } while(!kaytetyt.get(spawnerinXkoord).contains(spawnerinYkoord));
            
            for (int j = -1; j < 2; j++) {
                Set<Integer> ySarake;
                if (kaytetyt.get(spawnerinXkoord + j) != null) {
                    ySarake = kaytetyt.get(spawnerinXkoord + j);
                } else {
                    kaytetyt.put(spawnerinXkoord + j, new HashSet());
                    ySarake = kaytetyt.get(spawnerinXkoord);
                }
                for (int k = -1; k < 2; k++) {
                    ySarake.add(spawnerinYkoord + k);
                }
            }
            
            if (sijainnit.get(spawnerinXkoord) != null) {
                Set<Integer> yKoordinaatit = new HashSet();
                yKoordinaatit.add(spawnerinYkoord);
                sijainnit.put(spawnerinXkoord, yKoordinaatit);
            } else {
                sijainnit.get(spawnerinXkoord).add(spawnerinYkoord);
            }
            this.ruudut[spawnerinXkoord][spawnerinYkoord].setKasvaakoRuoka(true);
        }*/
    }
    
    // Testausta varten. Valmiissa ohjelmassa käytetään graafista käyttöliittymää.
    public void piirraKartta() {
        String piirrettava = "";
        for (int i = 0; i < this.ruudut.length; i++) {
            for (int j = 0; j < this.ruudut[i].length; j++) {
                if (this.ruudut[i][j].getKasviruoka() < 100) {
                    piirrettava += "[ "+this.ruudut[i][j].getKasviruoka()+"]";
                } else if (this.ruudut[i][j].getKasviruoka() < 10) {
                    piirrettava += "[  "+this.ruudut[i][j].getKasviruoka()+"]";
                } else {
                    piirrettava += "["+this.ruudut[i][j].getKasviruoka()+"]";
                }
            }
            piirrettava += "\n";
            
            for (int k = 0; k < this.ruudut[i].length; k++) {
                piirrettava += "["+this.ruudut[i][k].getLaumat().size()+" "+this.ruudut[i][k].getElaimet().size()+"]";
            }
            piirrettava += "\n";
        }
        System.out.println(piirrettava);
    }
}
