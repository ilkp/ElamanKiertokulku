
package elämänkiertokulku.kontrolleri;

import elämänkiertokulku.simulaatio.Lauma;

public class Kello {
    private Kontrolleri kontrolleri;
    private Runnable helloRunnable;

    
    public Kello (Kontrolleri kontrolleri) {
        this.kontrolleri = kontrolleri;
        helloRunnable = () -> {
            kontrolleri.ajaLaumat();
            Lauma lauma = (Lauma) kontrolleri.getLaumat().get(0);
            
            System.out.println("Lauman x: "+lauma.getRuutu().getxKoord() + ",  y:" + lauma.getRuutu().getyKoord());
            System.out.println("Lauman tavoite: "+lauma.getTavoite());
            System.out.println("Lauman tavoiteruutu: "+lauma.getTavoiteRuutu());
            System.out.println("liike vaihe: "+lauma.getLiikkeenVaihe());
            System.out.println("");
    };
    }
    
    public Runnable getRunnable() {
        return this.helloRunnable;
    }

}
