import javax.swing.JOptionPane;

public class Juego {
	private int[][] tablero;
	private int turno;
	
	Juego() {
		tablero = new int[3][3];
		turno = 1;
	}
	
	public void jugar() {
		new Pantalla(this);
	}
	
	public void siguienteTurno() {
		if(haGanado()) {
			JOptionPane.showMessageDialog(null, "El jugador "+turno+" ha ganado!");
			System.exit(0);
		}
		if(tableroLleno()) {
			JOptionPane.showMessageDialog(null, "Empate!");
			System.exit(0);
		}
		turno++;
		if(turno > 2) {
			turno = 1;
		}
	}
	
	private boolean haGanado() {
		for(int linea=0; linea<3; linea++) {
			for(int col=0; col<3; col++) {
				if(tablero[linea][col] != turno) {
					break;
				}
				if(col == 2) {
					return true;
				}
			}	
		}	
		for(int col=0; col<3; col++) {
			for(int linea=0; linea<3; linea++) {
				if(tablero[linea][col] != turno) {
					break;
				}
				if(linea == 2) {
					return true;
				}
			}	
		}
		if(tablero[0][0] == turno && tablero[1][1] == turno && tablero[2][2] == turno) {
			return true;
		}
		if(tablero[0][2] == turno && tablero[1][1] == turno && tablero[2][0] == turno) {
			return true;
		}
		return false;
	}
	private boolean tableroLleno() {
		for(int linea=0; linea<3; linea++) {
			for(int col=0; col<3; col++) {
				if(tablero[linea][col] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int[][] getTablero() { return tablero; }
	public int getTurno() { return turno; }
}
