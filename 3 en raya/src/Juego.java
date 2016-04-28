import javax.swing.JOptionPane;

public class Juego {
	private int[][] tablero;
	private int turno;
	private int jugadores;
	private IA ia;
	private Pantalla pantalla;
	private boolean verbose;
	
	Juego(int jugadores) {
		this.jugadores = jugadores;
		if(jugadores < 1 || jugadores > 2) {
			JOptionPane.showMessageDialog(null, "La cantidad de jugadores debe ser 1 o 2");
			System.exit(0);
		}
		
		if(jugadores == 1) {
			ia = new IA(this);
		}
		
		tablero = new int[3][3];
		turno = 1;
		
		verbose = true;
	}
	
	public void jugar() {
		pantalla = new Pantalla(this);
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
		turno = turno%2 + 1;
		if(jugadores == 1 && turno == 2) {
			ia.hacerTurno();
		}
	}
	
	private boolean haGanado() {
		for(int linea=0; linea<3; linea++) {
			int contador = 0;
			for(int col=0; col<3; col++) {
				if(tablero[linea][col] == turno) {
					contador++;
				}				
			}	
			if(contador == 3) {
				return true;
			}
		}
		for(int col=0; col<3; col++) {
			int contador = 0;
			for(int linea=0; linea<3; linea++) {				
				if(tablero[linea][col] == turno) {
					contador++;
				}
			}	
			if(contador == 3) {
				return true;
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
	public Pantalla getPantalla() { return pantalla; }
	public boolean getVerbose() { return verbose; }
}
