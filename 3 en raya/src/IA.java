import java.util.ArrayList;
import java.util.List;

public class IA {
	Juego jg;
	
	IA(Juego jg) {
		this.jg = jg;
	}
	
	public void hacerTurno() {
		if(jg.getVerbose()) {
			System.out.println("\nturno ia ("+jg.getTurno()+")");
		}		
		if(!colocarVictoria() && !defender()) {
			if(jg.getVerbose()) {
				System.out.println("atacar");
			}				
			atacar();
		}			
		jg.getPantalla().referscarPantalla();
		jg.siguienteTurno();
		if(jg.getVerbose()) {
			System.out.println("\n");
		}
	}
	
	private boolean defender() {
		for(int linea=0; linea<3; linea++) {
			int contador = 0;
			for(int col=0; col<3; col++) {
				if(jg.getTablero()[linea][col] != 0 && jg.getTablero()[linea][col] != jg.getTurno()) {
					contador++;
				}
			}
			if(contador == 2) {
				if(colocarEnLinea(linea)) {
					if(jg.getVerbose()) {
						System.out.println("colocar en linea "+linea);
					}
					return true;
				}				
			}
		}
		
		for(int col=0; col<3; col++) {
			int contador = 0;
			for(int linea=0; linea<3; linea++) {
				if(jg.getTablero()[linea][col] != 0 && jg.getTablero()[linea][col] != jg.getTurno()) {
					contador++;
				}
			}
			if(contador == 2) {
				if(colocarEnColumna(col)) {
					if(jg.getVerbose()) {
						System.out.println("colocar en columna "+col);
					}
					return true;
				}				
			}
		}
		
		if(diagonalIzq()) {
			if(jg.getVerbose()) {
				System.out.println("colocar en diagonal izq");
			}
			return true;
		}
		
		if(diagonalDrc()) {
			if(jg.getVerbose()) {
				System.out.println("colocar en diagonal drc");
			}
			return true;
		}
		if(jg.getTablero()[1][1] == 0 && !hayEsquinas()) {
			if(jg.getVerbose()) {
				System.out.println("colocar en centro");
			}
			jg.getTablero()[1][1] = jg.getTurno();
			return true;
		}
		return false;
	}
	
	private void atacar() {
		if(jg.getTablero()[1][1] == 0) {
			jg.getTablero()[1][1] = jg.getTurno();
			if(jg.getVerbose()) {
				System.out.println("colocar medio");
			}
			return;
		}
		if(sinEsquinas()) {
			if(jg.getVerbose()) {
				System.out.println("sin esquinas");
			}
			return;
		}
		if(colocarEsquinas()) {
			if(jg.getVerbose()) {
				System.out.println("colocar esquinas");
			}
			return;
		}
		colocarRandom();
		if(jg.getVerbose()) {
			System.out.println("colocar random");
		}
	}
	
	private boolean hayEsquinas() {
		if(jg.getTablero()[0][0] != 0 && jg.getTablero()[0][0] != jg.getTurno()) {
			return true;
		}
		if(jg.getTablero()[0][2] != 0 && jg.getTablero()[0][0] != jg.getTurno()) {
			return true;
		}
		if(jg.getTablero()[2][0] != 0 && jg.getTablero()[0][0] != jg.getTurno()) {
			return true;
		}
		if(jg.getTablero()[2][2] != 0 && jg.getTablero()[0][0] != jg.getTurno()) {
			return true;
		}
		return false;
	}
	
	private void colocarRandom() {
		List<int[]> casillas = new ArrayList<int[]>();
		for(int linea=0; linea<3; linea++) {
			for(int col=0; col<3; col++) {
				if(jg.getTablero()[linea][col] == 0) {
					int[] casilla = {linea, col};
					casillas.add(casilla);
				}
			}
		}
		int rnd = (int) (Math.random()*casillas.size());
		int[] casilla = casillas.get(rnd);
		jg.getTablero()[casilla[0]][casilla[1]] = jg.getTurno();
	}
	
	private boolean colocarVictoria() {
		for(int linea=0; linea<3; linea++) {
			int contador = 0;
			for(int col=0; col<3; col++) {
				if(jg.getTablero()[linea][col] == jg.getTurno()) {
					contador++;
				}
			}
			if(contador == 2) {
				if(colocarEnLinea(linea)) {
					return true;
				}				
			}
		}
		for(int col=0; col<3; col++) {
			int contador = 0;
			for(int linea=0; linea<3; linea++) {
				if(jg.getTablero()[linea][col] == jg.getTurno()) {
					contador++;
				}
			}
			if(contador == 2) {
				if(colocarEnColumna(col)) {
					return true;
				}				
			}
		}
		int contador = 0;
		if(jg.getTablero()[0][0] == jg.getTurno()) {
			contador++;
		}
		if(jg.getTablero()[1][1] == jg.getTurno()) {
			contador++;
		}
		if(jg.getTablero()[2][2] == jg.getTurno()) {
			contador++;
		}
		if(contador == 2) {
			if(diagonalIzq()) {
				return true;
			}			
		}
		
		contador = 0;
		if(jg.getTablero()[0][2] == jg.getTurno()) {
			contador++;
		}
		if(jg.getTablero()[1][1] == jg.getTurno()) {
			contador++;
		}
		if(jg.getTablero()[2][0] == jg.getTurno()) {
			contador++;
		}
		if(contador == 2) {
			if(diagonalDrc()) {
				return true;
			}			
		}
		return false;
	}
	
	private boolean sinEsquinas() {
		if(jg.getTablero()[0][1] != 0 && jg.getTablero()[0][1] != jg.getTurno()) {
			if(jg.getTablero()[1][0] != 0 && jg.getTablero()[1][0] != jg.getTurno()) {
				if(jg.getTablero()[0][0] == 0) {
					jg.getTablero()[0][0] = jg.getTurno();
					return true;
				}
			}
			if(jg.getTablero()[1][2] != 0 && jg.getTablero()[1][2] != jg.getTurno()) {
				if(jg.getTablero()[0][2] == 0) {
					jg.getTablero()[0][2] = jg.getTurno();
					return true;
				}
			}
		}
		
		if(jg.getTablero()[2][1] != 0 && jg.getTablero()[2][1] != jg.getTurno()) {
			if(jg.getTablero()[1][0] != 0 && jg.getTablero()[1][0] != jg.getTurno()) {
				if(jg.getTablero()[2][0] == 0) {
					jg.getTablero()[2][0] = jg.getTurno();
					return true;
				}
			}
			if(jg.getTablero()[1][2] != 0 && jg.getTablero()[1][2] != jg.getTurno()) {
				if(jg.getTablero()[2][2] == 0) {
					jg.getTablero()[2][2] = jg.getTurno();
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean colocarEsquinas() {
		if(esquinasContrarias()) {
			if(jg.getVerbose()) {
				System.out.println("esquinas contrarias");
			}
			return true;
		}
		if(estrategiaEsquinas()) {
			if(jg.getVerbose()) {
				System.out.println("estrategia esquinas");
			}
			return true;
		}
		if(esquinaPrioridad()) {
			if(jg.getVerbose()) {
				System.out.println("esquina prioridad");
			}
			return true;
		}
		List<Integer> casillas = new ArrayList<Integer>();
		if(jg.getTablero()[0][0] == 0) {
			casillas.add(0);
		}
		if(jg.getTablero()[0][2] == 0) {
			casillas.add(1);
		}
		if(jg.getTablero()[2][0] == 0) {
			casillas.add(2);
		}
		if(jg.getTablero()[2][2] == 0) {
			casillas.add(3);
		}
		if(casillas.size() > 0) {
			int rnd = (int) (Math.random()*casillas.size());
			int casilla = casillas.get(rnd);
			switch(casilla) {
			case 0:
				jg.getTablero()[0][0] = jg.getTurno();
				break;
			case 1:
				jg.getTablero()[0][2] = jg.getTurno();
				break;
			case 2:
				jg.getTablero()[2][0] = jg.getTurno();
				break;
			case 3:
				jg.getTablero()[2][2] = jg.getTurno();
				break;
			}
			return true;
		}
		return false;
	}
	
	private boolean estrategiaEsquinas() {
		if(jg.getTablero()[0][0] != 0 && jg.getTablero()[0][0] != jg.getTurno()) {
			if(jg.getTablero()[2][2] == 0) {
				jg.getTablero()[2][2] = jg.getTurno();
				return true;
			}
		}
		if(jg.getTablero()[2][2] != 0 && jg.getTablero()[2][2] != jg.getTurno()) {
			if(jg.getTablero()[0][0] == 0) {
				jg.getTablero()[0][0] = jg.getTurno();
				return true;
			}
		}
		if(jg.getTablero()[0][2] != 0 && jg.getTablero()[0][2] != jg.getTurno()) {
			if(jg.getTablero()[2][0] == 0) {
				jg.getTablero()[2][0] = jg.getTurno();
				return true;
			}
		}
		if(jg.getTablero()[2][0] != 0 && jg.getTablero()[2][0] != jg.getTurno()) {
			if(jg.getTablero()[0][2] == 0) {
				jg.getTablero()[0][2] = jg.getTurno();
				return true;
			}
		}
		return false;
	}
	
	private boolean esquinasContrarias() {
		if(jg.getTablero()[1][1] != jg.getTurno()) {
			return false;
		}
		if(jg.getTablero()[0][0] != 0 && jg.getTablero()[0][0] != jg.getTurno()) {
			if(jg.getTablero()[2][2] != 0 && jg.getTablero()[2][2] != jg.getTurno()) {
				if(colocarEnLinea(1)) {
					return true;
				}
				if(colocarEnColumna(1)) {
					return true;
				}
			}
		}
		if(jg.getTablero()[0][2] != 0 && jg.getTablero()[0][2] != jg.getTurno()) {
			if(jg.getTablero()[2][0] != 0 && jg.getTablero()[2][0] != jg.getTurno()) {
				if(colocarEnLinea(1)) {
					return true;
				}
				if(colocarEnColumna(1)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean esquinaPrioridad() {
		for(int linea=0; linea<3; linea++) {
			int blancos = 0;
			int mios = 0;
			for(int col=0; col<3; col++) {
				if(jg.getTablero()[linea][col] == 0) {
					blancos++;
					continue;
				}
				if(jg.getTablero()[linea][col] == jg.getTurno()) {
					mios++;
					continue;
				}
				break;
			}
			if(blancos == 2 && mios == 1) {
				return colocarEnEsquinaLinea(linea);
			}
			linea++;
		}
		for(int col=0; col<3; col++) {
			int blancos = 0;
			int mios = 0;
			for(int linea=0; linea<3; linea++) {
				if(jg.getTablero()[linea][col] == 0) {
					blancos++;
					continue;
				}
				if(jg.getTablero()[linea][col] == jg.getTurno()) {
					mios++;
					continue;
				}
				break;
			}
			if(blancos == 2 && mios == 1) {
				return colocarEnEsquinaColumna(col);
			}
			col++;
		}
		return false;
	}
	
	private boolean colocarEnLinea(int linea) {
		List<Integer> casillas = new ArrayList<Integer>(); 
		for(int col=0; col<3; col++) {
			if(jg.getTablero()[linea][col] == 0) {
				casillas.add(col);
			}
		}
		if(casillas.size() == 0) {
			return false;
		}
		int rnd = (int) (Math.random()*casillas.size());
		int col = casillas.get(rnd);
		jg.getTablero()[linea][col] = jg.getTurno();
		return true;
	}
	
	private boolean colocarEnColumna(int col) {
		List<Integer> casillas = new ArrayList<Integer>(); 
		for(int linea=0; linea<3; linea++) {
			if(jg.getTablero()[linea][col] == 0) {
				casillas.add(linea);
			}
		}
		if(casillas.size() == 0) {
			return false;
		}
		int rnd = (int) (Math.random()*casillas.size());
		int linea = casillas.get(rnd);
		jg.getTablero()[linea][col] = jg.getTurno();
		return true;
	}
	
	private boolean colocarEnEsquinaLinea(int linea) {
		if(jg.getTablero()[linea][0] == 0) {
			jg.getTablero()[linea][0] = jg.getTurno();
			return true;
		}
		if(jg.getTablero()[linea][2] == 0) {
			jg.getTablero()[linea][2] = jg.getTurno();
			return true;
		}
		return false;
	}
	
	private boolean colocarEnEsquinaColumna(int col) {
		if(jg.getTablero()[0][col] == 0) {
			jg.getTablero()[0][col] = jg.getTurno();
			return true;
		}
		if(jg.getTablero()[2][col] == 0) {
			jg.getTablero()[2][col] = jg.getTurno();
			return true;
		}
		return false;
	}
	
	private boolean diagonalIzq() {
		List<Integer> casillas = new ArrayList<Integer>();
		if(jg.getTablero()[0][0] == jg.getTurno()) {
			casillas.add(0);
		}
		if(jg.getTablero()[1][1] == jg.getTurno()) {
			casillas.add(1);
		}
		if(jg.getTablero()[2][2] == jg.getTurno()) {
			casillas.add(2);
		}
		if(casillas.size() == 2) {
			if(jg.getTablero()[0][0] == 0) {
				jg.getTablero()[0][0] = jg.getTurno();
				return true;
			}
			if(jg.getTablero()[1][1] == 0) {
				jg.getTablero()[1][1] = jg.getTurno();
				return true;
			}
			if(jg.getTablero()[2][2] == 0) {
				jg.getTablero()[2][2] = jg.getTurno();
				return true;
			}		
		}
		return false;
	}

	private boolean diagonalDrc() {
		List<Integer> casillas = new ArrayList<Integer>();
		if(jg.getTablero()[0][2] == jg.getTurno()) {
			casillas.add(0);
		}
		if(jg.getTablero()[1][1] == jg.getTurno()) {
			casillas.add(1);
		}
		if(jg.getTablero()[2][0] == jg.getTurno()) {
			casillas.add(2);
		}
		if(casillas.size() == 2) {
			if(jg.getTablero()[0][2] == 0) {
				jg.getTablero()[0][2] = jg.getTurno();
				return true;
			}
			if(jg.getTablero()[1][1] == 0) {
				jg.getTablero()[1][1] = jg.getTurno();
				return true;
			}
			if(jg.getTablero()[2][0] == 0) {
				jg.getTablero()[2][0] = jg.getTurno();
				return true;
			}
		}
		return false;
	}
}
