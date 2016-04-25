import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Pantalla extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final Font fuente = new Font("Monospaced", Font.BOLD, 20);
	
	private Juego jg;
	private JButton[][] botones;
	
	Pantalla(Juego jg) {
		this.jg = jg;
		
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(3, 3));		
		botones = new JButton[3][3];
		
		for(int linea=0; linea<3; linea++) {
			for(int col=0; col<3; col++) {
				botones[linea][col] = new JButton();
				cp.add(botones[linea][col]);
				
				int n = jg.getTablero()[linea][col];
				
				botones[linea][col].setText(Casillas.CASILLA[n]);
				botones[linea][col].setEnabled(true);
				botones[linea][col].setActionCommand(linea+", "+col);
				botones[linea][col].setHorizontalAlignment(JTextField.CENTER);
				botones[linea][col].setFont(fuente);
				botones[linea][col].addActionListener(this);
			}
		}
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("3 En Raya");
		setSize(600, 600);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int turno = jg.getTurno();		
		String[] botonArr = e.getActionCommand().split(", ");
		int linea = Integer.parseInt(botonArr[0]);
		int col = Integer.parseInt(botonArr[1]);
		botones[linea][col].setText(Casillas.CASILLA[turno]);
		botones[linea][col].setEnabled(false);
		jg.getTablero()[linea][col] = turno;
		jg.siguienteTurno();
	}
}
