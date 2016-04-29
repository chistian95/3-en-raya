import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Juego {
	private int[][] tablero;
	private int turno;
	private int modo;
	private IA ia;
	private IA ia2;
	private Pantalla pantalla;
	private boolean verbose;
	
	Juego(int modo) {
		this.modo = modo;	
		
		tablero = new int[3][3];
		turno = 1;
		
		verbose = false;
		
		pantalla = new Pantalla(this);
		
		if(modo != Modos.JvJ) {
			ia = new IA(this);
		}
		if(modo == Modos.IAvIA) {
			ia2 = new IA(this);
			ia2.hacerTurno();
		}
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
		if(modo == Modos.IAvIA) {
			Timer t = new Timer(500, new ActionListener() {
			    public void actionPerformed(ActionEvent evt) {		
					if(turno == 2) {
						ia.hacerTurno();
					} else {
						ia2.hacerTurno();
					}
			    }
			});
			t.setRepeats(false);
			t.start();
		}
		if(modo == Modos.JvIA && turno == 2) {
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
	public int getModo() { return modo; }
	public boolean getVerbose() { return verbose; }
}