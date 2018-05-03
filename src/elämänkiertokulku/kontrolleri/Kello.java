
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
            kontrolleri.getKartta().piirraKartta();
            
            
            System.out.println("Lauman x: "+lauma.getRuutu().getxKoord() + ",  y: " + lauma.getRuutu().getyKoord());
            System.out.println("Lauman tavoite: "+lauma.getTavoite());
            System.out.println("Lauman tavoiteruutu: "+lauma.getTavoiteRuutu());
            System.out.println("liike vaihe: "+lauma.getLiikkeenVaihe());
            System.out.println("");
            
            System.out.println("Eläimen x: "+lauma.getJasenet().get(0).getRuutu().getxKoord() + ",  y: " + lauma.getJasenet().get(0).getRuutu().getyKoord());
            System.out.println("Eläimen tavoite: "+lauma.getJasenet().get(0).getTavoite());
            System.out.println("Eläimen tavoiteruutu: "+lauma.getJasenet().get(0).getTavoiteRuutu());
            System.out.println("liike vaihe: "+lauma.getJasenet().get(0).getLiikkeenVaihe());
            System.out.println("Ruokatilanne: "+lauma.getJasenet().get(0).getRuokatilanne());
    };
    }
    
    public Runnable getRunnable() {
        return this.helloRunnable;
    }

}
