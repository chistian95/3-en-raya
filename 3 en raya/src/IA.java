import java.util.ArrayList;
import java.util.List;

public class IA {
	Juego jg;
	
	IA(Juego jg) {
		this.jg = jg;
	}
	
	public void hacerTurno() {
		System.out.println("turno ia");
		if(!defender()) {
			System.out.println("atacar");
			atacar();
		}			
		jg.getPantalla().referscarPantalla();
		jg.siguienteTurno();
	}
	
	private boolean defender() {
		if(jg.getTablero()[1][1] == 0 && !hayEsquinas()) {
			jg.getTablero()[1][1] = jg.getTurno();
			return true;
		}
		for(int linea=0; linea<3; linea++) {
			int contador = 0;
			for(int col=0; col<3; col++) {
				if(jg.getTablero()[linea][col] != 0 && jg.getTablero()[linea][col] != jg.getTurno()) {
					contador++;
				}
			}
			if(contador == 2) {
				if(colocarEnLinea(linea)) {
					System.out.println("colocar en linea "+linea);
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
					System.out.println("colocar en columna "+col);
					return true;
				}				
			}
		}
		
		if(diagonalIzq()) {
			System.out.println("colocar en diagonal izq");
			return true;
		}
		
		if(diagonalDrc()) {
			System.out.println("colocar en diagonal drc");
			return true;
		}
		return false;
	}
	
	private void atacar() {
		if(colocarVictoria()) {
			System.out.println("colocar victoria");
			return;
		}
		if(jg.getTablero()[1][1] == 0 && !hayEsquinas()) {
			jg.getTablero()[1][1] = jg.getTurno();
			System.out.println("colocar medio");
			return;
		}
		if(colocarEsquinas()) {
			System.out.println("colocar esquinas");
			return;
		}
		colocarRandom();
		System.out.println("colocar random");
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
	
	private boolean colocarEsquinas() {
		if(estrategiaEsquinas()) {
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
	
	private boolean diagonalIzq() {
		List<Integer> casillas = new ArrayList<Integer>();
		if(jg.getTablero()[0][0] != 0 && jg.getTablero()[0][0] != jg.getTurno()) {
			casillas.add(0);
		}
		if(jg.getTablero()[1][1] != 0 && jg.getTablero()[1][1] != jg.getTurno()) {
			casillas.add(1);
		}
		if(jg.getTablero()[2][2] != 0 && jg.getTablero()[2][2] != jg.getTurno()) {
			casillas.add(2);
		}
		if(casillas.size() == 2) {
			casillas = new ArrayList<Integer>();
			if(jg.getTablero()[0][0] == 0) {
				casillas.add(0);
			}
			if(jg.getTablero()[1][1] == 0) {
				casillas.add(1);
			}
			if(jg.getTablero()[2][2] == 0) {
				casillas.add(2);
			}
			if(casillas.size() > 0) {
				int rnd = (int) (Math.random()*casillas.size());
				int casilla = casillas.get(rnd);
				switch(casilla) {
				case 0:
					jg.getTablero()[0][0] = jg.getTurno();
					break;
				case 1:
					jg.getTablero()[1][1] = jg.getTurno();
					break;
				case 2:
					jg.getTablero()[2][2] = jg.getTurno();
					break;
				}
				return true;
			}			
		}
		return false;
	}

	private boolean diagonalDrc() {
		List<Integer> casillas = new ArrayList<Integer>();
		if(jg.getTablero()[0][2] != 0 && jg.getTablero()[0][2] != jg.getTurno()) {
			casillas.add(0);
		}
		if(jg.getTablero()[1][1] != 0 && jg.getTablero()[1][1] != jg.getTurno()) {
			casillas.add(1);
		}
		if(jg.getTablero()[2][0] != 0 && jg.getTablero()[2][0] != jg.getTurno()) {
			casillas.add(2);
		}
		if(casillas.size() == 2) {
			casillas = new ArrayList<Integer>();
			if(jg.getTablero()[0][2] == 0) {
				casillas.add(0);
			}
			if(jg.getTablero()[1][1] == 0) {
				casillas.add(1);
			}
			if(jg.getTablero()[2][0] == 0) {
				casillas.add(2);
			}
			if(casillas.size() > 0) {
				int rnd = (int) (Math.random()*casillas.size());
				int casilla = casillas.get(rnd);
				switch(casilla) {
				case 0:
					jg.getTablero()[0][2] = jg.getTurno();
					break;
				case 1:
					jg.getTablero()[1][1] = jg.getTurno();
					break;
				case 2:
					jg.getTablero()[2][0] = jg.getTurno();
					break;
				}
				return true;
			}			
		}
		return false;
	}
}
