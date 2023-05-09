package buscaminas;

import java.util.*;

public class main {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Bienvenido a buscaminas.");
		System.out.println("Las reglas son las siguientes:");
		System.out.println("-Debes mencionar la posicion en la matriz donde quieres hacer click");
		System.out.println("-Si haces click en una mina pierdes");
		System.out.println("QUE EL JUEGO COMIENZE, HOY CORRERA SANGRE");
		
		System.out.println("Inserte el tamanio del recuadro: ");
		int tamanioX = reader.nextInt();
		matriz m = new matriz(tamanioX);
		m.matrizPrincipal();
		System.out.println("En que posicion del tablero quiere hacer click: ");
		//bucle hasta que ya no haya casillas donde apretar
		m.matrizPaint();
		for(int i =0; i<tamanioX*tamanioX ;i++) {
		System.out.println("Posicion vertical");
			int posX = reader.nextInt();
			System.out.println("Posicion horizontal");
			int posY=reader.nextInt();
			if(posX < tamanioX || posY < tamanioX) {
				m.clickMatriz(posX, posY);				
			}else {
				System.out.println("Quieres acceder a una casillas inexistente de la matriz ");
			}
				if(!m.gameOver) {
					break;
				}
			}
		System.out.println("Game over ");
		
	}
}