import GLOOP.GLPrismoid;
import GLOOP.*;
import GLOOP.GLQuader;
import GLOOP.GLZylinder;
/**
* Beschreiben Sie hier die Klasse Kanone.
*
* @author (Ihr Name)
* @version (eine Versionsnummer oder ein Datum)
*/
public class Gebaeude
{
	static int schaden=5;
	public double ausrichtung;
    //Einzelne Bauteile
GLQuader boden, stelze[];
GLZylinder drehAchse;
GLPrismoid kanone, kanoneAusschuss;
//Vektoren
GLVektor vektorrichtung,achse,schussrichtung,spitzenposition,xachse,yachse,zachse,kanonenposition;
GLKugel kanonenkugel;

//globale Variablen um Parameter weiter zu nutzen
double px,py,pz,pGroesse;

//globale Variablen für woanders
double drehwinkel;
int z=0;
int i=0;
double kanonenkugelgroesse;
boolean schonPassiert = false;
double steigung=0;
int zaehl=0;
int wert=1;
public boolean getroffen=false;


public Gebaeude(double x, double y, double z, double Groesse)
{   
    //VEKTOREN
    vektorrichtung=new GLVektor(0,0,0.2);
    schussrichtung=new GLVektor(0,0,-1);
    kanonenposition=new GLVektor(x,y+Groesse*1.8+Groesse+Groesse,z);
    spitzenposition=new GLVektor(0,0,-Groesse*5.5);
    
//Referenzvektoren und Variabeln
    xachse=new GLVektor(1,0,0);
    yachse=new GLVektor(0,1,0);
    zachse=new GLVektor(0,0,1);
    px=x;
    py=y;
    pz=z;
    pGroesse=Groesse;

}


//Winkel zwischen dem Ziel und der gegenwärtigen ausrichtung der kanone
public double gibWinkel(GLVektor pZiel) {
	
	double pWinkel= Math.toDegrees(Math.acos(pZiel.gibSkalarprodukt(schussrichtung)/(schussrichtung.gibBetrag()*pZiel.gibBetrag())));	
	//sorgt dafür dass die kanone sich in beide richtungen drehen kann
 	if(zaehl==0) {
		steigung=pWinkel;
	}
	if(zaehl==1) {
		if(steigung<pWinkel) {wert=wert*-1;}	
		zaehl=-1;
	}
	zaehl++;
	if(wert==-1) {
		pWinkel=-pWinkel;
	}
	return pWinkel;}


//gibt die gegenwärtige ausrichtung der Kanone
public double ausrichtung() {
	return ausrichtung;
}
//liefert den eingetragenen schadenswert pro schuss
public int gibSchaden() {
return schaden;}

	
	
}

