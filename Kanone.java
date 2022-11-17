import GLOOP.*;

/**
 * Beschreiben Sie hier die Klasse Kanone.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Kanone extends Gebaeude {
    GLQuader boden, stelze[];
    GLZylinder drehAchse;
    GLPrismoid kanone, kanoneAusschuss;

    public Kanone(double x, double y, double z, double Groesse) {
        super(x, y, z, Groesse);
        // Bauteile
        achse = new GLVektor(0, 1, 0);
        stelze = new GLQuader[4];
        stelze[0] = new GLQuader(x - Groesse * 4, y + Groesse, z - Groesse * 4, Groesse, Groesse * 2, Groesse);
        stelze[0].drehe(45, 45, 0);
        stelze[1] = new GLQuader(x + Groesse * 4, y + Groesse, z - Groesse * 4, Groesse, Groesse * 2, Groesse);
        stelze[1].drehe(45, -45, 0);
        stelze[2] = new GLQuader(x - Groesse * 4, y + Groesse, z + Groesse * 4, Groesse, Groesse * 2, Groesse);
        stelze[2].drehe(-45, -45, 0);
        stelze[3] = new GLQuader(x + Groesse * 4, y + Groesse, z + Groesse * 4, Groesse, Groesse * 2, Groesse);
        stelze[3].drehe(-45, 45, 0);
        boden = new GLQuader(x, y + Groesse * 1.8, z, Groesse * 8.5, Groesse * 1, Groesse * 8.5);
        drehAchse = new GLZylinder(x, y + Groesse * 1.8 + Groesse, z, Groesse * 1, Groesse * 1);
        drehAchse.drehe(90, 0, 0);
        kanone = new GLPrismoid(x, y + Groesse * 1.8 + Groesse + Groesse, z, Groesse * 1.2, Groesse * 0.8, 30,
                Groesse * 5);
        kanoneAusschuss = new GLPrismoid(x, y + Groesse * 1.8 + Groesse + Groesse, z - Groesse * 2.5, Groesse * 1.2,
                Groesse * 0.8, 30, Groesse * 5);
        kanone.setzeFarbe(1, 0, 0);
        kanone.setzeSelbstleuchten(1, 0, 0);
        kanoneAusschuss.setzeFarbe(0, 0, 0);
        boden.setzeFarbe(0.1, 0.1, 0.1);
        for (int i = 0; i < 4; i++) {
            stelze[i].setzeFarbe(0.3, 0.3, 0.3);
        }
        kanonenkugelgroesse = Groesse;
        kanonenkugel = new GLKugel(x, y + Groesse * 1.8 + Groesse + Groesse, -Groesse * 5.5, Groesse);
        kanonenkugel.setzeSichtbarkeit(false);
        kanonenkugel.setzeFarbe(1, 1, 0);

    }
    
//repell und schieße zusammen sind die schussanimation der kanone
    public void repell() {
        if (z == 0) {
            getroffen = false;
            kanonenkugel.setzePosition(spitzenposition.gibX() + kanonenposition.gibX(),
                    spitzenposition.gibY() + kanonenposition.gibY(), spitzenposition.gibZ() + kanonenposition.gibZ());
            kanonenkugel.setzeSichtbarkeit(true);
        }
        z++;

        if (z <= 150) {

            kanone.verschiebe(vektorrichtung);
            kanoneAusschuss.verschiebe(vektorrichtung);
            if (z == 150) {
                vektorrichtung.multipliziere(-1);
            }
        }
        if (z > 150) {
            kanone.verschiebe(vektorrichtung);
            kanoneAusschuss.verschiebe(vektorrichtung);
            if (z == 300) {
                vektorrichtung.multipliziere(-1);
                z = 0;
            }
        }
    }

    public void schiesse(double pWeite) {
        i++;

        if (i <= 300) {
            repell();
        }
        if (getroffen == false) {
            kanonenkugel.verschiebe(schussrichtung);
        }

        if (i >= pWeite) {
            i = 0;
            resetKugel();

        }
    }
//gibt die position der Kugel
    public double gibkugelX() {
        return kanonenkugel.gibX();
    }

    public double gibkugelY() {
        return kanonenkugel.gibY();
    }

    public double gibkugelZ() {
        return kanonenkugel.gibZ();
    }
//Setzt die Kugel zurück ur spitze der Kanone
    public void resetKugel() {
        kanonenkugel.setzePosition(spitzenposition.gibX() + kanonenposition.gibX(),
                spitzenposition.gibY() + kanonenposition.gibY(), spitzenposition.gibZ() + kanonenposition.gibZ());
        kanonenkugel.setzeSichtbarkeit(false);
        getroffen = true;
    }

    //SChwenkt die Kanone um pWinkel
    public void schwenke(double pWinkel){
        kanone.drehe(0, pWinkel, 0,px,py,pz);
        kanoneAusschuss.drehe(0, pWinkel, 0,px,py,pz);
    
        vektorrichtung.rotiere(pWinkel,achse);
        schussrichtung.rotiere(pWinkel,achse);
        spitzenposition.rotiere(pWinkel,achse);
        ausrichtung=pWinkel;

    }
}