import GLOOP.*;

public class Map
{
    GLQuader Quader1,Quader2,Quader3,Quader4,Quader5,Quader6,Quader7;
    GLQuader[] boden,boden2;
    /**
     * Konstruktor f�r Objekte der Klasse Karte
     */	GLQuader[] qArr;
     int quaderanzahl=100,quaderzaehler=0;
     
    public Map()
    { qArr = new GLQuader[7];
 	
    //Strecke
    qArr[0] = new GLQuader(800,0,0,400,0,100);
    qArr[1] = new GLQuader(600,0,-150,100,0,400);
    qArr[2] = new GLQuader(400,0,-300,300,0,100);
    qArr[3] = new GLQuader(200,0,-50,100,0,603);
    qArr[4] = new GLQuader(0,2,300,500,0,100);
    qArr[5] = new GLQuader(-200,0,100,100,0,500);
    qArr[6] = new GLQuader(-550,0,-100,600,0,100);  
   // boden = new GLQuader(0,-1,0,10000,1,10000);
   // boden.setzeFarbe(0,0.2,0);

for(int i=0;i<qArr.length;i++) {
	qArr[i].setzeFarbe(0,0,0);

    } 

boden2= new GLQuader[18];
boden = new GLQuader[30]; 
for(int i=0;i<30;i++) {
	boden[i]= new GLQuader(i*100-1400,-1,600,100,1,100); // Ganze untere erste Reihe
	if(i%2 == 1) {boden[i].setzeFarbe(0, 0.3, 0);} //alle ungeraden
	if(i%2!=1) {boden[i].setzeFarbe(0, 0.2, 0);} //alle geraden		
	for(int k=0;k<18;k++) {		
		boden2[k] = new GLQuader(i*100-1400,-1,-k*100+500,100,1,100); //alle oberen reihen
		//moeglichkeit 1 fuer dunkelgruen
		if(i%2 != 1){ //alle ungeraden
			if(k%2 != 1) {boden2[k].setzeFarbe(0, 0.3, 0);} //alle ungeraden
	}	
		//moeglichkeit 2 fuer dunkelgruen
		if(i%2 == 1){ //alle geraden
			if(k%2 == 1) {boden2[k].setzeFarbe(0, 0.3, 0);} //alle geraden
	}
		
		//hellgruen
		//moeglichkeit 1 fuer hellgruen
		if(i%2 != 1){ //alle ungeraden
			if(k%2 == 1) {boden2[k].setzeFarbe(0, 0.2, 0);} //alle geraden
	}
		//moeglichkeit 2 fuer hellgruen
		if(i%2 == 1){ //alle geraden
			if(k%2 != 1) {boden2[k].setzeFarbe(0, 0.2, 0);} //alle ungeraden
	}
	}
}


}  
} 


    
    



