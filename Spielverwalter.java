import GLOOP.*;

public class Spielverwalter {

    static GLKamera cam;
    GLLicht licht;
    GLTastatur tas;
    Map map;
    Ork orkArray[];
    int orkanzahl=20;

    Kanone kanone;
    public Spielverwalter() {
        //GLOOP Grundlagen
        cam = new GLSchwenkkamera();
        tas = new GLTastatur();
        new Map();
        new GLLicht();
        spielerperspektive();
        kanone = new Kanone(0,0,-200,10);
        //Erzeugen der Orks
        orkArray = new Ork[orkanzahl];
        for(int i=0;i<orkanzahl;i++) {
            orkArray[i] = new Ork(1050+90*i,50);
        }
        
        hauptschleife();
    }

    public void hauptschleife(){
        while(!tas.esc()){
            for(int k=0;k<orkanzahl;k++) {
                orkArray[k].laufe(); 
         }
         if(!tas.istGedrueckt('z')){
             Sys.warte();
         }
        } //Ende der hauptschleife
    }

    public static void spielerperspektive() {
        cam.verschiebe(0, 1200, -300);
        cam.setzeBlickpunkt(0, 0, 0);
    }
}


