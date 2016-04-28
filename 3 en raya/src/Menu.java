import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Menu extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final Font fuente = new Font("Monospaced", Font.BOLD, 20);
	
	private JButton[] arrayBotones;

	Menu() {
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(3, 1));
		
		arrayBotones = new JButton[3];
		for(int i=0; i<3; i++)  {
			arrayBotones[i] = new JButton();
			arrayBotones[i].setHorizontalAlignment(JTextField.CENTER);
			arrayBotones[i].setFont(fuente);
			arrayBotones[i].addActionListener(this);
			cp.add(arrayBotones[i]);
		}
		
		arrayBotones[0].setText("Jugador Vs. Jugador");
		arrayBotones[0].setActionCommand(Modos.JvJ+"");
		
		arrayBotones[1].setText("Jugador Vs. IA");
		arrayBotones[1].setActionCommand(Modos.JvIA+"");
		
		arrayBotones[2].setText("IA Vs. IA");
		arrayBotones[2].setActionCommand(Modos.IAvIA+"");		
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("3 En Raya - Menú");
		setSize(300, 300);
		setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent event) {
		int modo = Integer.parseInt(event.getActionCommand());
		this.setVisible(false);
		new Juego(modo);
	}
}
