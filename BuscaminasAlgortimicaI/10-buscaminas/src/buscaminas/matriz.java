package buscaminas;

import java.util.Random;

public class matriz {

	int x;

	public matriz(int x) {
		this.x = x;

	}

	Random random = new Random();
	char[][] matrix;
	char[][] matrizMuestra;
	int[] vx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	int[] vy = { -1, 0, 1, -1, 1, -1, 0, 1 };

	int[] xx = { 0, 0, 1, -1 };
	int[] yy = { 1, -1, 0, 0 };

	

	boolean gameOver= true;

	public boolean isGameOver() {
		return gameOver;
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void matrizPaint() {
		matrix = new char[this.x][this.x];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = '-';
			}
		}
		addMinas();
	}
	
	public void addMinas() {
		int minas = matrix.length + 3;
		// hacer una matriz cuadrada
		for (int y = 0; y < minas; y++) {
			char randomX = (char) random.nextInt(matrix.length);
			char randomY = (char) random.nextInt(matrix.length);
			matrix[randomX][randomY] = '*';
		}
		
		for (int q = 0; q < x; q++) {
			for (int t = 0; t < x; t++) {
				if (matrix[q][t] == '*') {
					floodFill(q, t);
				}
			}
			
		}
		 //mostrarMatriz(matrix);
		
	}
	
	public void floodFill(int q, int t) {
		for (int i = 0; i < 8; i++) {
			
			int nx = q + vx[i];
			int ny = t + vy[i];
			if (nx < x && ny < x && nx >= 0 && ny >= 0) {
				if (matrix[nx][ny] == '-') {
					matrix[nx][ny] += 4;
				} else if (matrix[nx][ny] != '-' && matrix[nx][ny] != '*') {
					matrix[nx][ny] += 1;
				}
				
			}
			
		}
	}
	
	public void matrizPrincipal() {
		matrizMuestra = new char[this.x][this.x];
		for (int i = 0; i < matrizMuestra.length; i++) {
			for (int j = 0; j < matrizMuestra.length; j++) {
				matrizMuestra[i][j] = ' ';
			}
		}
		mostrarMatriz(matrizMuestra);
	}

	public void clickMatriz(int posX, int posY) {
		if (matrix[posX][posY] == '-') {
			floodFillGuion(posX, posY);
		} else if (matrix[posX][posY] == '*') {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					//if(matrix[i][j] == '*') { //porsia
						matrizMuestra[i][j] = matrix[i][j];
					//}
				}
			}
			setGameOver(gameOver=false); 
		}else {
			matrizMuestra[posX][posY] = matrix[posX][posY];
		}
		mostrarMatriz(matrizMuestra);
	}
	
	public void floodFillGuion(int guionX, int guionY) {
		matrizMuestra[guionX][guionY] = matrix[guionX][guionY];
		matrix[guionX][guionY] = '+';
		for (int i = 0; i < 4; i++) {
			int nxx = guionX + xx[i];
			int nyy = guionY + yy[i];
			if (nxx <x && nyy <x && nxx >= 0 && nyy >= 0 && matrix[nxx][nyy] == '-') {
			
				floodFillGuion(nxx, nyy);
			}
		}
	}

	
	public void mostrarMatriz(char[][] mat) { //"poner bonito la matriz"
		for (int f = 0; f < mat.length; f++) {
//			 System.out.print(f);
			for (int c = 0; c < mat[f].length - 1; c++) {			
				System.out.print("[" + mat[f][c] + "]");
			}
			System.out.println("[" + mat[f][mat[f].length - 1] + "]");
		}
	}
}