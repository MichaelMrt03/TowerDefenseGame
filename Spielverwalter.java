import GLOOP.*;
public class Spielverwalter {

    static GLKamera cam;
    GLLicht licht;
    Map map;
    static Kanone kanone;
    public Spielverwalter(){
         cam = new GLSchwenkkamera();
        new Map();
        new GLLicht();
       spielerperspektive();
      // kanone = new Gebaeude(0,0,-200,10);
      kanone = new Kanone(0,0,-200,10);
  
    }

    public static void spielerperspektive() {
        cam.verschiebe(0,1200,-300);
        cam.setzeBlickpunkt(0,0,0);    
    }
}
