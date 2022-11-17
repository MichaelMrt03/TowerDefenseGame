import GLOOP.*;
public class Spielverwalter {

    static GLKamera cam;
    GLLicht licht;
    Map map;
    public Spielverwalter(){
         cam = new GLSchwenkkamera();
        new Map();
        new GLLicht();
       spielerperspektive();
    }

    public static void spielerperspektive() {
        cam.verschiebe(0,1200,-300);
        cam.setzeBlickpunkt(0,0,0);    
    }
}
