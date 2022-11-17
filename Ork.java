import GLOOP.*;
/**
 * Beschreiben Sie hier die Klasse Ork.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Ork extends Monster
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
   //GLQuader koerper, beinRechts, beinLinks, armLinks, armRechts, kopf, ohrRechts, ohrLinks;


public Ork(int pX,int pGroesse)
{ super(pX,pGroesse);
x = pX;
	g= pGroesse;
	y= g/1.55;
	lebenspunkte = 50;
	Startlebenspunkte = lebenspunkte;


	koerper = new GLQuader(x,y+g/2,z,g/2,g,g/3);
	koerper.setzeFarbe(0.1,0.4,0.1);
	
     beinLinks = new GLQuader(x+g/10,y-g/4,z,g/6,g/1.25,g/6);
     beinRechts = new GLQuader(x-g/10,y-g/4,z,g/6,g/1.25,g/6);
     beinLinks.setzeFarbe(0,1,0);
     beinRechts.setzeFarbe(0,1,0);
    
    
    hals = new GLZylinder(x,y+g,z,g/10,g/5);
    hals.drehe(90,0, 0);
    hals.setzeFarbe(0.1,0.4,0.1);
    
    kopf = new GLKugel(x,y+g+g/4,z,g/5);
    kopf.setzeFarbe(0,1,0);
    
    augeR = new GLKugel(kopf.gibX()-g/15,kopf.gibY()+g/20,kopf.gibZ()+g/7,g/13);
    augeL = new GLKugel(kopf.gibX()+g/15,kopf.gibY()+g/20,kopf.gibZ()+g/7,g/13);
    augeR.setzeFarbe(1,0,0);
    augeL.setzeFarbe(1,0,0);
    augeR.setzeSelbstleuchten(1, 0, 0);
    augeL.setzeSelbstleuchten(1, 0, 0);
  
    
     armLinks = new GLQuader(x+g/4+g/20,y+g/1.5,z+g/5,g/10,g/8,g/2);
     armRechts= new GLQuader(x-g/4-g/20,y+g/1.5,z+g/5,g/10,g/8,g/2);
     armRechts.setzeFarbe(0,1,0);
     armLinks.setzeFarbe(0,1,0);
     super.aktualisiereLebensbalken();
    super.drehe(0, -90, 0);

    
}


}

  
