package interfaceUsuario;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class InterfaceBotones extends VentanaModelo {

	private static final long serialVersionUID = 1L;
	private JPanel panelGeneral;
	public JLabel lbl1A;
	public JButton btn1A;
	public JLabel lbl1B;
	public JButton btn1B;
	public JLabel lbl1C;
	public JButton btn1C;
	public JLabel lbl2A;
	public JButton btn2A;
	public JLabel lbl2B;
	public JButton btn2B;
	public JLabel lbl2C;
	public JButton btn2C;
	public JButton btnVolver;

	public InterfaceBotones(String nombreVentana) {
		
		super(nombreVentana);
		panelGeneral = new JPanel();
		setContentPane(panelGeneral);
		SpringLayout contenedor = new SpringLayout();
		panelGeneral.setLayout(contenedor);
				
		lbl1A = new JLabel();
		lbl1A.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lbl1A, 100, SpringLayout.NORTH, panelGeneral);
		contenedor.putConstraint(SpringLayout.WEST, lbl1A, 75, SpringLayout.WEST, panelGeneral);
		contenedor.putConstraint(SpringLayout.EAST, lbl1A, 116, SpringLayout.WEST, lbl1A);
		lbl1A.setVisible(false);
		panelGeneral.add(lbl1A);
		
		btn1A = new JButton();
		contenedor.putConstraint(SpringLayout.NORTH, btn1A, 15, SpringLayout.NORTH, lbl1A);
		contenedor.putConstraint(SpringLayout.WEST, btn1A, 6, SpringLayout.WEST, lbl1A);
		contenedor.putConstraint(SpringLayout.SOUTH, btn1A, 94, SpringLayout.NORTH, btn1A);
		contenedor.putConstraint(SpringLayout.EAST, btn1A, 104, SpringLayout.WEST, btn1A);
		btn1A.setVisible(false);
		panelGeneral.add(btn1A);
		
		lbl1B = new JLabel();
		lbl1B.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lbl1B, 100, SpringLayout.NORTH, panelGeneral);
		contenedor.putConstraint(SpringLayout.WEST, lbl1B, 75, SpringLayout.EAST, lbl1A);
		contenedor.putConstraint(SpringLayout.EAST, lbl1B, 116, SpringLayout.WEST, lbl1B);
		lbl1B.setVisible(false);
		panelGeneral.add(lbl1B);
		
		btn1B = new JButton();
		contenedor.putConstraint(SpringLayout.NORTH, btn1B, 15, SpringLayout.NORTH, lbl1B);
		contenedor.putConstraint(SpringLayout.WEST, btn1B, 6, SpringLayout.WEST, lbl1B);
		contenedor.putConstraint(SpringLayout.SOUTH, btn1B, 94, SpringLayout.NORTH, btn1B);
		contenedor.putConstraint(SpringLayout.EAST, btn1B, 104, SpringLayout.WEST, btn1B);
		btn1B.setVisible(false);
		panelGeneral.add(btn1B);
		
		lbl1C = new JLabel();
		lbl1C.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lbl1C, 100, SpringLayout.NORTH, panelGeneral);
		contenedor.putConstraint(SpringLayout.WEST, lbl1C, 75, SpringLayout.EAST, lbl1B);
		contenedor.putConstraint(SpringLayout.EAST, lbl1C, 116, SpringLayout.WEST, lbl1C);
		lbl1C.setVisible(false);
		panelGeneral.add(lbl1C);
		
		btn1C = new JButton();
		contenedor.putConstraint(SpringLayout.NORTH, btn1C, 15, SpringLayout.NORTH, lbl1C);
		contenedor.putConstraint(SpringLayout.WEST, btn1C, 6, SpringLayout.WEST, lbl1C);
		contenedor.putConstraint(SpringLayout.SOUTH, btn1C, 94, SpringLayout.NORTH, btn1C);
		contenedor.putConstraint(SpringLayout.EAST, btn1C, 104, SpringLayout.WEST, btn1C);
		btn1C.setVisible(false);
		panelGeneral.add(btn1C);
		
		lbl2A = new JLabel();
		lbl2A.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lbl2A, 100, SpringLayout.SOUTH, btn1A);
		contenedor.putConstraint(SpringLayout.WEST, lbl2A, 75, SpringLayout.WEST, panelGeneral);
		contenedor.putConstraint(SpringLayout.EAST, lbl2A, 116, SpringLayout.WEST, lbl2A);
		lbl2A.setVisible(false);
		panelGeneral.add(lbl2A);
		
		btn2A = new JButton();
		contenedor.putConstraint(SpringLayout.NORTH, btn2A, 15, SpringLayout.NORTH, lbl2A);
		contenedor.putConstraint(SpringLayout.WEST, btn2A, 6, SpringLayout.WEST, lbl2A);
		contenedor.putConstraint(SpringLayout.SOUTH, btn2A, 94, SpringLayout.NORTH, btn2A);
		contenedor.putConstraint(SpringLayout.EAST, btn2A, 104, SpringLayout.WEST, btn2A);
		btn2A.setVisible(false);
		panelGeneral.add(btn2A);
		
		lbl2B = new JLabel();
		lbl2B.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lbl2B, 100, SpringLayout.SOUTH, btn1B);
		contenedor.putConstraint(SpringLayout.WEST, lbl2B, 75, SpringLayout.EAST, lbl2A);
		contenedor.putConstraint(SpringLayout.EAST, lbl2B, 116, SpringLayout.WEST, lbl2B);
		lbl2B.setVisible(false);
		panelGeneral.add(lbl2B);
		
		btn2B = new JButton();
		contenedor.putConstraint(SpringLayout.NORTH, btn2B, 15, SpringLayout.NORTH, lbl2B);
		contenedor.putConstraint(SpringLayout.WEST, btn2B, 6, SpringLayout.WEST, lbl2B);
		contenedor.putConstraint(SpringLayout.SOUTH, btn2B, 94, SpringLayout.NORTH, btn2B);
		contenedor.putConstraint(SpringLayout.EAST, btn2B, 104, SpringLayout.WEST, btn2B);
		btn2B.setVisible(false);
		panelGeneral.add(btn2B);
		
		lbl2C = new JLabel();
		lbl2C.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lbl2C, 100, SpringLayout.SOUTH, btn1C);
		contenedor.putConstraint(SpringLayout.WEST, lbl2C, 75, SpringLayout.EAST, lbl2B);
		contenedor.putConstraint(SpringLayout.EAST, lbl2C, 116, SpringLayout.WEST, lbl2C);
		lbl2C.setVisible(false);
		panelGeneral.add(lbl2C);
		
		btn2C = new JButton();
		contenedor.putConstraint(SpringLayout.NORTH, btn2C, 15, SpringLayout.NORTH, lbl2C);
		contenedor.putConstraint(SpringLayout.WEST, btn2C, 6, SpringLayout.WEST, lbl2C);
		contenedor.putConstraint(SpringLayout.SOUTH, btn2C, 94, SpringLayout.NORTH, btn2C);
		contenedor.putConstraint(SpringLayout.EAST, btn2C, 104, SpringLayout.WEST, btn2C);
		btn2C.setVisible(false);
		panelGeneral.add(btn2C);
		
		btnVolver = new JButton("Volver");
		contenedor.putConstraint(SpringLayout.SOUTH, btnVolver, -15, SpringLayout.SOUTH, panelGeneral);
		contenedor.putConstraint(SpringLayout.EAST, btnVolver, -15, SpringLayout.EAST, panelGeneral);
		contenedor.putConstraint(SpringLayout.WEST, btnVolver, -90, SpringLayout.EAST, btnVolver);
		panelGeneral.add(btnVolver);
	}
}
