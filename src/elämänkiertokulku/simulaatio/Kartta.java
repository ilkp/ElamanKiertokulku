
package elämänkiertokulku.simulaatio;


public class Kartta {
    private Ruutu[][] ruudut;
    
    public Kartta(int leveys, int pituus) {
        this.ruudut = new Ruutu[leveys][pituus];
    }
    
    public void alusta() {
        for (int i = 0; i < ruudut.length; i++) {
            for (int j = 0; j < ruudut[i].length; j++) {
                ruudut[i][j] = new Ruutu(i, j);
            }
        }
    }

    public Ruutu[][] getRuudut() {
        return ruudut;
    }

    public void setRuudut(Ruutu[][] ruudut) {
        this.ruudut = ruudut;
    }
    
    public void piirraKartta() {
        String piirrettava = "";
        for (int i = 0; i < this.ruudut.length; i++) {
            for (int j = 0; j < this.ruudut[i].length; j++) {
                if (this.ruudut[i][j].getRuoka() < 100) {
                    piirrettava += "[ "+this.ruudut[i][j].getRuoka()+"]";
                } else if (this.ruudut[i][j].getRuoka() < 10) {
                    piirrettava += "[  "+this.ruudut[i][j].getRuoka()+"]";
                } else {
                    piirrettava += "["+this.ruudut[i][j].getRuoka()+"]";
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
