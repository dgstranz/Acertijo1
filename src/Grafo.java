public class Grafo {
	private int tamGrafo=15;	// Tama�o m�ximo del grafo
	private int numNodos; // Tama�o del grafo
	private int [] nodos; // Nodos. Su �ndice es su posici�n, su contenido es un entero.
	private boolean [][] adyacencia; // Matriz de adyacencia, indica si dos nodos est�n enlazados o no.
	private boolean [] valores; // Valores que est�n ocupados.
	
	/* CONSTRUCTOR */
	public Grafo() {
		numNodos=0;
		nodos = new int[tamGrafo];
		adyacencia = new boolean[tamGrafo][tamGrafo];
		valores = new boolean[tamGrafo];
		for(int i=0;i<tamGrafo;i++) {
			for(int j=0;j<tamGrafo;j++) {
				adyacencia[i][j]=false;
			}
			valores[i]=false;
		}
	}

	/* M�TODOS GET Y SET */
	public int getNumNodos() {
		return numNodos;
	}
	
	public boolean ocupado(int dato) {
		if(dato>0 && dato<numNodos) {
			return valores[dato];
		}
		else return true;
	}
	
	public int getNodo(int pos) {
		return nodos[pos];
	}
	
	public void setNodo(int pos,int dato) {
		if(pos<numNodos && dato<numNodos) {
			if(pos>0) {
				if(dato>0 && !valores[dato]) {
					valores[nodos[pos]]=false;
					nodos[pos]=dato;
					valores[dato]=true;
				}
				else if(dato==0) {
					valores[nodos[pos]]=false;
					nodos[pos]=dato;
				}
			}
			else if(pos==0) {
				if(dato>0) {
					nodos[pos]=-1;
				}
				else if(dato==0) {
					nodos[pos]=dato;
				}
			}
		}
	}
	
	/* M�TODO TOSTRING */
	/* Aqu� ignoro a prop�sito el nodo 0. */
	public String toString() {
		if(numNodos==0) {
			return "Grafo vac�o";
		}
		// Si el grafo no est� vac�o, primero devuelve su contenido...
		String res=new String("[");
		for(int i=1;i<numNodos-1;i++) {
			res+=nodos[i];
			if(!enlazados(i,i+1)) res+=";";
			res+=" ";
		}
		res+=nodos[numNodos-1]+"]\n"; //En el �ltimo no quiero que a�ada " " sino "]".
		return res;
	}
	
	public String toStringLargo() { //Similar al anterior, pero devuelve tambi�n la matriz de adyacencia.
		if(numNodos==0) {
			return "Grafo vac�o";
		}
		// Si el grafo no est� vac�o, primero devuelve su contenido...
		String res=new String("N�mero de nodo (Contenido): Nodos con los que est� enlazado\n");
		for(int i=1;i<numNodos;i++) {
			res+=""+i+" ("+nodos[i]+"): ";
			if(numEnlazados(i)==0) {
				res+="Ninguno";
			}
			else {
				for(int j=0;j<numNodos;j++) {
					if(enlazados(i,j)) res+=j+" ";
				}
			}
			res+="\n"; //Salto de l�nea justo antes de pasar al siguiente valor de i.
		}
		return res;
	}
	
	public String toStringValores() {
		if(numNodos==0) {
			return "Grafo vac�o";
		}
		// Si el grafo no est� vac�o, primero devuelve su contenido...
		String res=new String("Contenido de los valores disponibles:\n[");
		for(int i=1;i<numNodos-1;i++) {
			if(valores[i]) res+="1 ";
			else res+="0 ";
		}
		if(valores[numNodos-1]) res+="1]\n";
		else res+="0]\n";
		return res;
	}
	
	/* A�ADIR NODOS */	
	public void addNodo(int dato) {
		if(numNodos<tamGrafo) {
			nodos[numNodos]=dato;
			numNodos++;
		}
	}
	
	public void addNodo() {
		if(numNodos<tamGrafo) {
			nodos[numNodos]=0;
			numNodos++;
		}
	}
	
	/* A�ADIR Y QUITAR ENLACES */
	public void addEnlace(int i,int j) {
		if(i>=0 && j>=0 && i!=j && i<numNodos && j<numNodos) {
			adyacencia[i][j]=true;
			adyacencia[j][i]=true;
		}
	}
	
	public void deleteEnlace(int i,int j) {
		if(i>=0 && j>=0 && i<numNodos && j<numNodos) {
			adyacencia[i][j]=false;
			adyacencia[j][i]=false;
		}
	}	
	
	/* VER SI DOS NODOS DADOS EST�N ENLAZADOS */
	public boolean enlazados(int i,int j) {
		if(i>=0 && j>=0 && i<numNodos && j<numNodos) {
			return adyacencia[i][j];
		}
		return false;
	}
	
	/* CU�NTOS NODOS EST�N ENLAZADOS CON UN NODO DADO */
	public int numEnlazados(int i) {
		int num=0;
		for(int j=0;j<numNodos;j++) {
			if(enlazados(i,j)) num++;
		}
		return num;
	}
	
	/* VER SI UNA DISPOSICI�N ES LEGAL CON UNAS REGLAS AL USO */
	public boolean esPosLegal(int i) {
		if(i<1 || i>=numNodos) return false; 	//Excede del rango que nos interesa.
		if(nodos[i]==0) return true; 			//A�n no tiene un valor asignado.
		for(int j=1;j<i;j++) { 					//Solamente comprueba de 1 a i-1.
			if(nodos[j]>0 && enlazados(i,j)) {	//Solamente importa si los nodos i y j est�n enlazados.
				if(nodos[i]-nodos[j]==1 || nodos[j]-nodos[i]==1) {
					return false;
				}
				if((nodos[i]%nodos[j]==0 && nodos[j]!=1) || (nodos[j]%nodos[i]==0 && nodos[i]!=1)) {
					return false;
				}
			}
		}
		return true;
	}
}
