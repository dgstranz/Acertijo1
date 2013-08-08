public class Prueba {
	public static void main(String[] args) {
		int i;		//Un simple contador.
		int pos;	//Contador de nodo.
		int dato;	//Dato que vamos a introducir en el grafo en el nodo en la posición pos.
		int numSol=0;	//Número de soluciones al problema.
		Grafo g=new Grafo();
		
		/** Lo primero es construir el grafo con los nodos tal como los queremos.
		 *  Primero añado nodos al grafo. */
		System.out.println(g);
		for(i=0;i<15;i++) {
			g.addNodo();
		}
		/** Java empieza a contar en 0, pero a mí me conviene contar a partir de 1.
		 *  Así que ignoraré en lo posible el nodo 0.
		 *  Ahora enlazo unos nodos con otros. */
		for(i=1;i<15;i++) {
			g.addEnlace(i,i+1);
			g.addEnlace(i,i+3);
			g.addEnlace(i,i+4);
		}
		g.deleteEnlace(3,4);
		g.deleteEnlace(7,8);
		g.deleteEnlace(11,12);
		g.deleteEnlace(4,7);
		g.deleteEnlace(8,11);
	
		/** El resultado es este.
		 *    1---2---3
		 *   / \ / \ / \
		 *  4---5---6---7
		 *   \ / \ / \ / \
		 *    8---9--10--11
		 *     \ / \ / \ /
		 *     12--13--14
		 *     
		 * Enunciado del problema: se busca colocar en los nodos los números del 1 al 14
		 * de forma que:
		 * 1) ningún número sea vecino de otro número consecutivo y
		 * 2) ningún número sea vecino de otro que lo divida (con la excepción del 1, claro).
		 */
		
		pos=1;
		dato=1;
		while(pos>0) {
			while(g.ocupado(dato)) { // Esto puede ser por varias razones, véase método en la clase Grafo.
				dato++;
				while(dato>=g.getNumNodos()) { // Nos hemos pasado, así que hay que volver atrás.
					g.setNodo(pos,0);
					pos--;
					dato=g.getNodo(pos)+1; // Si pos==0, en la siguiente iteración finalizará el bucle.
				}
			}
			while(!g.ocupado(dato)) {
				g.setNodo(pos,dato);
				if(g.esPosLegal(pos)) {
					if(pos<g.getNumNodos()-1) {
						pos++;
						dato=1;
					}
					else {
						numSol++;
						System.out.print("Solución número "+numSol+": "+g);
						g.setNodo(pos,0); // Una vez encontrada una solución, hay que volver atrás.
						pos--;
						dato=g.getNodo(pos)+1;
					}
				}
				else {
					dato++; // Si esta posición no es legal, habrá que buscar una que lo sea.
				}
			}
		}
	}
}