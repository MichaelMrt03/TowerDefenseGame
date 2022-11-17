import GLOOP.*;

public class Spielverwalter {

    static GLKamera cam;
    GLLicht licht;
    GLTastatur tas;
    Map map;
    Ork orkArray[];
    int orkanzahl=20;

    static Kanone kanone;
    public Spielverwalter() {
        //GLOOP Grundlagen
        cam = new GLSchwenkkamera();
        tas = new GLTastatur();
        new Map();
        new GLLicht();
       spielerperspektive();
    }

    public static void spielerperspektive() {
        cam.verschiebe(0, 1200, -300);
        cam.setzeBlickpunkt(0, 0, 0);
    }
}

