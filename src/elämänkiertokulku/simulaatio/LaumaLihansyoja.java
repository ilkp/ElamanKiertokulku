
package elämänkiertokulku.simulaatio;

import java.util.List;


public class LaumaLihansyoja extends Lauma {
    

    public LaumaLihansyoja(int id, Ruutu ruutu, Ruutu[][] ruudukko) {
        super(id, ruutu, ruudukko);
    }
    

    public Ruutu onkoRuokaa() {
        for (int i = -this.ALUEEN_KOKO; i < this.ALUEEN_KOKO + 1; i++) {
            for (int j = -this.ALUEEN_KOKO; j < this.ALUEEN_KOKO + 1; j++) {
                int xKoord = this.ruutu.getxKoord() + i;
                int yKoord = this.ruutu.getyKoord() + j;
                List<Elain> elaimet = this.ruudukko[xKoord][yKoord].getElaimet();
                for (Elain elain : elaimet) {
                    if(elain.getClass().equals(Kasvinsyoja.class)){
                        return ruudukko[xKoord][yKoord];
                    }
                }
             
            }
        }
        return null;
    }
        @Override
    public void maaritaLaumanTavoite() {
        while (onkoRuokaa() != null ){
            /*---- Lauman jäsenet alkavat jahtaamaan kasvinsyöjää.---*/
            /* getJäsenet -> setJäsenKoord(Lisättävä metodi) = onkoRuokaa()*/
            
                /*if(getJäsenKoord == onkoRuokaa()){
                    kasvinsyojä kuolee -> (koordinaatit poistuu)
                    Lihansyojä ruoka lisääntyy
                else if (getJäsenKoord != onkoRuokaa()){
                    liikuta Lihansyöjä Lauma -> getJäsenKoord suuntaan
            */
        }
        if (onkoRuokaa() == null){
            /*Lauma Liikkuu Randomilla.*/
            
        }
    }
    

}
