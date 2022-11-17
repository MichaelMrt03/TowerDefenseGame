import GLOOP.*;
public class Monster {
	public int g, zaehler=0;
    public boolean tot=false;
	double Monsterstrecke = 0;
	double premonsterstrecke=0;
	GLQuader koerper, beinRechts, beinLinks, armLinks, armRechts, ohrRechts, ohrLinks; 
	GLKugel kopf,augeL,augeR;
	GLZylinder hals;
	GLQuader hilfsquader;
	int rotierWinkelA;
	int rotierWinkelB;
	
	 int d = 0; // Variable um 180 Grad zu schwingen
	int arm_Kalibrieren = 0; //kalibriert, dass erst ein arm und ein Bein 90 zurück schwingen
	boolean vorn; //Prüft das Schwingen der Arme
	boolean nichtErzeugt = true;
	boolean nichtErzeugt2 = true;
	int x = 1050; // Spawnposition
	double y = g / 1.55; // Spawnhöhe
	int z = 0; //spawn z
	String richtung; // String in welche Richtung die Laufanimation ablaufen soll
	int lebenspunkte;
	int Startlebenspunkte;
	GLQuader lebensbalken,todesbalken;
	GLVektor ovBR, ovBL, ovAR, ovAL, rv, position, aenderung, laufvektor, yachse, prelaufvektor, zukunft; // ovBR = Ortsvektor Bein Rechts/ werden benötigt um die Arme und Beine um eine Achse zu rotieren.
	GLVektor ovBR1, ovBL1, ovAR1, ovAL1, rv1;
	GLVektor[] vektorarray;
	GLVektor[] zvektorarray;
	
	GLQuader lq;
	GLQuader tq;
	public Monster(int pX,int pGroesse) {
		g = pGroesse;	
		x = pX;
		position = new GLVektor(x,y,z);
		laufvektor = new GLVektor(-0.1, 0, 0);
		yachse = new GLVektor(0, 1, 0);		
		aenderung = new GLVektor(0, 0, 0);	
		zukunft= new GLVektor(0, 0, 0); 
		prelaufvektor = new GLVektor(-0.1,0,0);
		vektorarray=new GLVektor[4];
		vektorarray[0]=new GLVektor(-0.1,0,0);
		vektorarray[1]=new GLVektor(0,0,-0.1);
		vektorarray[2]=new GLVektor(0,0,0.1);
		vektorarray[3]=new GLVektor(0.1,0,0);
		zvektorarray=new GLVektor[4];
		zvektorarray[0]=new GLVektor(0,0,0);
		zvektorarray[1]=new GLVektor(0,0,0);
		zvektorarray[2]=new GLVektor(0,0,0);
		zvektorarray[3]=new GLVektor(0,0,0);
		Monsterstrecke=10*(1050-x); 
	}

	public void verschiebe(GLVektor pV) {
		beinLinks.verschiebe(pV);
		beinRechts.verschiebe(pV);
		koerper.verschiebe(pV);
		kopf.verschiebe(pV);
		hals.verschiebe(pV);
		armRechts.verschiebe(pV);
		armLinks.verschiebe(pV);
		augeR.verschiebe(pV);
		augeL.verschiebe(pV);
	 lebensbalken.verschiebe(pV);
	 if(todesbalken!=null) {
			todesbalken.verschiebe(pV);
		}
		// position.addiere(pV);

		// ohrRechts.verschiebe(pV);
		// ohrLinks.verschiebe(pV);
	}

	public void laufe() {

		verschiebe(laufvektor); //verschiebt das Monster in Laufrichtung
		if (Monsterstrecke == 10*(1050-x)) {  //Muss zu Beginn nach Links
			richtung = "links";
		}
		laufAnimation(richtung);
		if (Monsterstrecke ==900*5) { // nach 900 Strecke muss es nach hinten also z wird kleiner
			richtung = "hinten";
			drehe(0, -90, 0);
			laufvektor.rotiere(-90, yachse);
			prelaufvektor.rotiere(-90, yachse);

		}
		if (Monsterstrecke == 1500*5) {
			drehe(0, 90, 0);
			richtung = "links";
			laufvektor.rotiere(90, yachse);
			prelaufvektor.rotiere(90, yachse);
		}
		if (Monsterstrecke == 2300*5) {

			drehe(0, 90, 0);	
			laufvektor.rotiere(90, yachse);
			prelaufvektor.rotiere(90, yachse);
			richtung = "vorne";
		}
		if (Monsterstrecke == 3500*5) {
			drehe(0, -90, 0);
			laufvektor.rotiere(-90, yachse);
			prelaufvektor.rotiere(-90, yachse);
			richtung = "links";
		}
		if (Monsterstrecke == 4300*5) {
			drehe(0, -90, 0);
			
			laufvektor.rotiere(-90, yachse);
			prelaufvektor.rotiere(-90, yachse);
			richtung = "hinten";
		}
		if (Monsterstrecke == 5100*5) {
			drehe(0, 90, 0);
			laufvektor.rotiere(90, yachse);
			prelaufvektor.rotiere(90, yachse);
			richtung = "links";
		}
		Monsterstrecke+=1;
		aenderung.addiere(laufvektor);
	}

	public void drehe(double x, double y, double z) {
		beinLinks.drehe(x, y, z, koerper.gibX(), koerper.gibY(), koerper.gibZ());
		beinRechts.drehe(x, y, z, koerper.gibX(), koerper.gibY(), koerper.gibZ());
		koerper.drehe(x, y, z, koerper.gibX(), koerper.gibY(), koerper.gibZ());
		kopf.drehe(x, y, z, koerper.gibX(), koerper.gibY(), koerper.gibZ());
		armRechts.drehe(x, y, z, koerper.gibX(), koerper.gibY(), koerper.gibZ());
		armLinks.drehe(x, y, z, koerper.gibX(), koerper.gibY(), koerper.gibZ());
        augeR.drehe(x, y, z, koerper.gibX(), koerper.gibY(), koerper.gibZ());
        augeL.drehe(x, y, z, koerper.gibX(), koerper.gibY(), koerper.gibZ());
        
        // ohrRechts.drehe(x,y,z,koerper.gibX(),koerper.gibY(),koerper.gibZ());
		// ohrLinks.drehe(x,y,z,koerper.gibX(),koerper.gibY(),koerper.gibZ());
	}

	public void laufAnimation(String pRichtung) {

		

		rCheck(pRichtung);
		if(pRichtung=="links"||pRichtung=="rechts"||pRichtung=="vorne"){ //Je nachdem in welche Richtung die Laufanitmation stattfindet muss hier der rotierwinkel angepasst werden
			rotierWinkelA =-1;
			rotierWinkelB = 1;
		}
		if(pRichtung=="hinten"){ //Je nachdem in welche Richtung die Laufanitmation stattfindet muss hier der rotierwinkel angepasst werden
			rotierWinkelA =1;
			rotierWinkelB = -1;
		}
		
		
	if (arm_Kalibrieren <= 180) { // Einmal schwingen
			armRechts.rotiere(rotierWinkelB, rv, ovAR);

			if (arm_Kalibrieren <= 90) {
				beinRechts.rotiere(rotierWinkelA, rv, ovBR);
				beinLinks.rotiere(rotierWinkelB, rv, ovBL);
			}
			arm_Kalibrieren++;

		}
		
	
		if (arm_Kalibrieren > 180) {
			// Durchgehendes schwingen
			if (d <= 180) {
				armRechts.rotiere(rotierWinkelA, rv, ovAR); // nach vorne schwingen
				armLinks.rotiere(rotierWinkelB, rv, ovAL); // nach hinten schwingen
				beinRechts.rotiere(rotierWinkelB, rv, ovBL); // nach vorne schwingen
				beinLinks.rotiere(rotierWinkelA, rv, ovBL);
				d++;
			}
			if (d == 180) {
				vorn = true;

			}
			if (vorn) {
				armRechts.rotiere(rotierWinkelB, rv, ovAR);
				armLinks.rotiere(rotierWinkelA, rv, ovAL);
				beinRechts.rotiere(rotierWinkelA, rv, ovBR);
				beinLinks.rotiere(rotierWinkelB, rv, ovBL);
				d++;
			}
			if (d == 360) {
				d = 0;
				vorn = false;
			}

		}

	}

	public void verschiebe(double pX, double pY, double pZ) {
		beinLinks.verschiebe(pX, pY, pZ);
		beinRechts.verschiebe(pX, pY, pZ);
		koerper.verschiebe(pX, pY, pZ);
		kopf.verschiebe(pX, pY, pZ);
		armRechts.verschiebe(pX, pY, pZ);
		armLinks.verschiebe(pX, pX, pZ);
		// ohrRechts.verschiebe(pX,pY,pZ);
		// ohrLinks.verschiebe(pX,pY,pZ);
		lebensbalken.verschiebe(pX,pY,pZ);
		if(todesbalken!=null) {
			todesbalken.verschiebe(pX,pY,pZ);
		}
	}

	public int gibSize() {
		return g;
	}

	public void rCheck(String pRichtung) { // Passt die Achsen an, um die Arme zur jeweiligen Richtung zu sortieren
		switch (pRichtung) {
		case "rechts":

			ovBR = new GLVektor(koerper.gibX(), y - g / 4 + g / 1.25 / 2, g / 10);
			ovBL = new GLVektor(koerper.gibX(), y - g / 4 + g / 1.25 / 2, -g / 10);
			ovAR = new GLVektor(g / 100 + koerper.gibX(), koerper.gibY() + g / 5, -g / 10);
			ovAL = new GLVektor(koerper.gibX(), koerper.gibY() + g / 5, g / 10);
			rv = new GLVektor(0, 0, 1);
			
			break;
		case "links":
//		
			ovBR = new GLVektor(koerper.gibX(), y - g / 4 + g / 1.25 / 2, g / 10);
			ovBL = new GLVektor(koerper.gibX(), y - g / 4 + g / 1.25 / 2, -g / 10);
			ovAR = new GLVektor(g / 100 + koerper.gibX(), koerper.gibY() + g / 5, -g / 10);
			ovAL = new GLVektor(koerper.gibX(), koerper.gibY() + g / 5, g / 10);
			rv = new GLVektor(0, 0, 1);
			
			break;
		case "hinten":
			//new GLQuader(g / 10 + koerper.gibX(), y - g / 4 + g / 1.25 / 2, koerper.gibZ(),1000,1,1);
			ovBR = new GLVektor(g / 10 + koerper.gibX(), y - g / 4 + g / 1.25 / 2, koerper.gibZ());
			ovBL = new GLVektor(-g / 10 + koerper.gibX(), y - g / 4 + g / 1.25 / 2, koerper.gibZ());
			ovAR = new GLVektor(-g / 10 + koerper.gibX(), koerper.gibY() + g / 5, g / 100 + koerper.gibZ());
			ovAL = new GLVektor(g / 10 + koerper.gibX(), koerper.gibY() + g / 5, g / 100 + koerper.gibZ());
			rv = new GLVektor(1, 0, 0);
			//OVAR Q
			//new GLQuader(-g / 10 + koerper.gibX(), koerper.gibY() + g / 5, g / 100 + koerper.gibZ(),1,200,1);
			
			break;
		case "vorne": // vektorwerte um nach vorne zu laufen -> animation

			ovBR = new GLVektor(g / 10, y - g / 4 + g / 1.25 / 2, koerper.gibZ());
			ovBL = new GLVektor(-g / 10, y - g / 4 + g / 1.25 / 2, koerper.gibZ());
			ovAR = new GLVektor(-g / 10, koerper.gibY() + g / 5, g / 100 + koerper.gibZ());
			ovAL = new GLVektor(g / 10, koerper.gibY() + g / 5, g / 100 + koerper.gibZ());
			rv = new GLVektor(1, 0, 0);
			break;
		default:
			throw new IllegalArgumentException("verbotener String");
		}

	}


	public GLVektor gibVektor() {

		return position;
	}
//adfaserfaesgvaervawerv
	public GLVektor gibAenderung() {
	return aenderung;
	}
double zukunftsb=0;
	public GLVektor gibZukunft(double entfernung) {
		premonsterstrecke=Monsterstrecke;
		for(int i=0;i<entfernung;i++) {
			if (premonsterstrecke ==900*5) { // nach 900 Strecke muss es nach hinten also z wird kleiner
				prelaufvektor.rotiere(-90, yachse);

			}
			if (premonsterstrecke == 1500*5) {
				prelaufvektor.rotiere(90, yachse);
			}
			if (premonsterstrecke == 2300*5) {
				prelaufvektor.rotiere(90, yachse);
			}
			if (premonsterstrecke == 3500*5) {
				prelaufvektor.rotiere(-90, yachse);
			}
			if (premonsterstrecke == 4400*5) {
				prelaufvektor.rotiere(-90, yachse);
			}
			if (premonsterstrecke == 5200*5) {
				prelaufvektor.rotiere(90, yachse);
			}
			zukunft.addiere(prelaufvektor);
			zukunftsb+=0.1;
		premonsterstrecke++;}
		return zukunft;
		
		
		}
		public double gibZukunftsbetrag() {
			return zukunftsb;
		}
	

	public void reset() {
		aenderung.multipliziere(0);
		zukunft.multipliziere(0);
		zukunftsb=0;
	}
	
	public int gibX() {
		return (int) koerper.gibX();
	}
	public int gibY() {
		return (int) koerper.gibY();
	}
	public int gibZ() {
		return (int) koerper.gibZ();
	}
	
	public void aktualisiereLebensbalken() {
		
		if(nichtErzeugt) {			
		lebensbalken =	new GLQuader(this.gibX(),this.gibY()+g*1.4,this.gibZ(),Startlebenspunkte,8,8);
	lebensbalken.setzeFarbe(0,0.5,0);
			nichtErzeugt = false;
		}
		else {

	
		lebensbalken.loesche();
		lebensbalken = new GLQuader(-25+lebenspunkte/2+this.gibX(),this.gibY()+g*1.4,this.gibZ(),lebenspunkte,8,8);
		
		lebensbalken.setzeFarbe(0,0.5,0);
		if(todesbalken!=null) {
			todesbalken.loesche();
			
		}
		
		todesbalken = new GLQuader(25-(50-lebenspunkte)/2+this.gibX(),this.gibY()+g*1.4,this.gibZ(),50-lebenspunkte,8,8);
		todesbalken.setzeFarbe(1,0,0);
		}
		if(lebenspunkte<=0) {
			todesbalken.setzeSichtbarkeit(false);
			lebensbalken.setzeSichtbarkeit(false);
			tot=true;
			this.sterbe();
		}
		
	}
	
	public void nimmSchaden(int schaden) {
		lebenspunkte = lebenspunkte-schaden;
		this.aktualisiereLebensbalken();

		
	}
	

	public boolean istTot() {
		return tot;
	}
	
	public void loesche() {
		beinLinks.loesche();
		beinRechts.loesche();
		armRechts.loesche();
		armLinks.loesche();
		augeR.loesche();
		augeL.loesche();
		kopf.loesche();
		hals.loesche();
		koerper.loesche();
	}
	
	public void sterbe() {
		//Sterben muss in Spielverwaltung passieren
		//Ka was der ganze bereich machen sollte, aber auf jeden fall wurde das monster nicht gelöscht nach dem tod...
	/* 	zaehler=0;
		if(zaehler<100) {
			zaehler++;
			beinLinks.drehe(Math.random(), Math.random(),Math.random());
			beinRechts.drehe(Math.random(), Math.random(),Math.random());
			armRechts.drehe(Math.random(), Math.random(),Math.random());
			armLinks.drehe(Math.random(), Math.random(),Math.random());
			Sys.warte(1);
		}
		if(zaehler>100) {
		loesche();
		zaehler=0;
	 }*/
	 loesche();
	}

}
