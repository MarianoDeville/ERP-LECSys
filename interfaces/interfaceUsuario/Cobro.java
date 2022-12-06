package interfaceUsuario;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class Cobro extends VentanaModelo {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JScrollPane scrollTabla1;
	public JScrollPane scrollTabla2;
	public JTable tabla1;
	public JTable tabla2;
	public JLabel lblNombre;
	public JLabel lblDescGrupo;
	public JLabel lblInscripción;
	public JLabel lblDescEfectivo;
	public JLabel lblTotalPagar;
	public JLabel lblEmail;
	public JLabel lblFactura;
	public JLabel lblMsgError;
	public JCheckBox chckbxEnviarEmail;
	public JCheckBox chckbxTabla2;
	public JTextField txtNombre;
	public JTextField txtDescuento;
	public JTextField txtInscripción;
	public JTextField txtDescEfectivo;
	public JTextField txtTotalPagar;
	public JTextField txtFactura;
	public JTextField txtEmail;
	public JTextField txtTabla2;
	public JButton btnCobrar;
	public JButton btnVolver;

	public Cobro(String nombreVentana) {
		
		super(nombreVentana);
		panel = new JPanel();
		setContentPane(panel);
		SpringLayout contenedor = new SpringLayout();
		panel.setLayout(contenedor);
		
		scrollTabla1 = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla1, 20, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla1, 15, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla1, -410, SpringLayout.SOUTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla1, -20, SpringLayout.EAST, panel);
		panel.add(scrollTabla1);
		tabla1 = new JTable();
		scrollTabla1.setViewportView(tabla1);
		
		lblNombre = new JLabel("Nombre de familia:");
		contenedor.putConstraint(SpringLayout.NORTH, lblNombre, 20, SpringLayout.SOUTH, scrollTabla1);
		contenedor.putConstraint(SpringLayout.WEST, lblNombre, 35, SpringLayout.WEST, panel);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtNombre, 0, SpringLayout.NORTH, lblNombre);
		contenedor.putConstraint(SpringLayout.WEST, txtNombre, 40, SpringLayout.EAST, lblNombre);
		txtNombre.setEditable(false);
		panel.add(txtNombre);
		txtNombre.setColumns(20);
		
		lblDescGrupo = new JLabel("Porcentage de descunto:");
		contenedor.putConstraint(SpringLayout.NORTH, lblDescGrupo, 20, SpringLayout.SOUTH, lblNombre);
		contenedor.putConstraint(SpringLayout.WEST, lblDescGrupo, 0, SpringLayout.WEST, lblNombre);
		lblDescGrupo.setVisible(false);
		panel.add(lblDescGrupo);
		
		txtDescuento = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtDescuento, 0, SpringLayout.NORTH, lblDescGrupo);
		contenedor.putConstraint(SpringLayout.WEST, txtDescuento, 0, SpringLayout.WEST, txtNombre);
		txtDescuento.setVisible(false);
		panel.add(txtDescuento);
		txtDescuento.setColumns(3);
		
		lblInscripción = new JLabel("Inscripción:");
		contenedor.putConstraint(SpringLayout.NORTH, lblInscripción, 20, SpringLayout.SOUTH, lblDescGrupo);
		contenedor.putConstraint(SpringLayout.WEST, lblInscripción, 0, SpringLayout.WEST, lblNombre);
		panel.add(lblInscripción);
		
		txtInscripción = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtInscripción, 0, SpringLayout.NORTH, lblInscripción);
		contenedor.putConstraint(SpringLayout.WEST, txtInscripción, 0, SpringLayout.WEST, txtNombre);
		panel.add(txtInscripción);
		txtInscripción.setColumns(7);
		
		lblDescEfectivo = new JLabel("Decuento pago efectivo:");
		contenedor.putConstraint(SpringLayout.NORTH, lblDescEfectivo, 20, SpringLayout.SOUTH, lblInscripción);
		contenedor.putConstraint(SpringLayout.WEST, lblDescEfectivo, 0, SpringLayout.WEST, lblInscripción);
		panel.add(lblDescEfectivo);
		
		txtDescEfectivo = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtDescEfectivo, 0, SpringLayout.NORTH, lblDescEfectivo);
		contenedor.putConstraint(SpringLayout.WEST, txtDescEfectivo, 0, SpringLayout.WEST, txtNombre);
		panel.add(txtDescEfectivo);
		txtDescEfectivo.setColumns(7);
		
		lblTotalPagar = new JLabel("Total a pagar:");
		contenedor.putConstraint(SpringLayout.NORTH, lblTotalPagar, 20, SpringLayout.SOUTH, lblDescEfectivo);
		contenedor.putConstraint(SpringLayout.WEST, lblTotalPagar, 0, SpringLayout.WEST, lblDescEfectivo);
		panel.add(lblTotalPagar);
		
		txtTotalPagar = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtTotalPagar, 0, SpringLayout.NORTH, lblTotalPagar);
		contenedor.putConstraint(SpringLayout.WEST, txtTotalPagar, 0, SpringLayout.WEST, txtNombre);
		txtTotalPagar.setEditable(false);
		panel.add(txtTotalPagar);
		txtTotalPagar.setColumns(10);
		
		lblFactura = new JLabel("Factura nro.:");
		contenedor.putConstraint(SpringLayout.NORTH, lblFactura, 20, SpringLayout.SOUTH, lblTotalPagar);
		contenedor.putConstraint(SpringLayout.WEST, lblFactura, 0, SpringLayout.WEST, lblNombre);
		panel.add(lblFactura);
		
		txtFactura = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtFactura, 0, SpringLayout.NORTH, lblFactura);
		contenedor.putConstraint(SpringLayout.WEST, txtFactura, 10, SpringLayout.EAST, lblFactura);
		panel.add(txtFactura);
		txtFactura.setColumns(16);	
		
		chckbxEnviarEmail = new JCheckBox("Enviar comprobante por e-mail");
		contenedor.putConstraint(SpringLayout.NORTH, chckbxEnviarEmail, 20, SpringLayout.SOUTH, lblFactura);
		contenedor.putConstraint(SpringLayout.WEST, chckbxEnviarEmail, 0, SpringLayout.WEST, lblNombre);
		panel.add(chckbxEnviarEmail);
				
		lblEmail = new JLabel("E-mail:");
		contenedor.putConstraint(SpringLayout.NORTH, lblEmail, 20, SpringLayout.SOUTH, chckbxEnviarEmail);
		contenedor.putConstraint(SpringLayout.WEST, lblEmail, 0, SpringLayout.WEST, lblNombre);
		lblEmail.setVisible(false);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtEmail, 0, SpringLayout.NORTH, lblEmail);
		contenedor.putConstraint(SpringLayout.WEST, txtEmail, 10, SpringLayout.EAST, lblEmail);
		panel.add(txtEmail);
		txtEmail.setVisible(false);
		txtEmail.setColumns(25);		

		chckbxTabla2 = new JCheckBox("Agragar a grupo");
		contenedor.putConstraint(SpringLayout.NORTH, chckbxTabla2, 20, SpringLayout.SOUTH, scrollTabla1);
		contenedor.putConstraint(SpringLayout.WEST, chckbxTabla2, 15, SpringLayout.EAST, txtNombre);
		panel.add(chckbxTabla2);	
		
		txtTabla2 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtTabla2, 15, SpringLayout.SOUTH, chckbxTabla2);
		contenedor.putConstraint(SpringLayout.WEST, txtTabla2, 0, SpringLayout.WEST, chckbxTabla2);
		panel.add(txtTabla2);
		txtTabla2.setColumns(15);

		scrollTabla2 = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla2, 15, SpringLayout.SOUTH, txtTabla2);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla2, 15, SpringLayout.EAST, txtNombre);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla2, -50, SpringLayout.SOUTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla2, -20, SpringLayout.EAST, panel);
		panel.add(scrollTabla2);
		tabla2 = new JTable();
		scrollTabla2.setViewportView(tabla2);

		lblMsgError = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblMsgError, 20, SpringLayout.SOUTH, lblEmail);
		contenedor.putConstraint(SpringLayout.WEST, lblMsgError, 0, SpringLayout.WEST, lblEmail);
		contenedor.putConstraint(SpringLayout.EAST, lblMsgError, 0, SpringLayout.EAST, txtNombre);
		panel.add(lblMsgError);
			
		btnCobrar = new JButton("Cobrar");
		contenedor.putConstraint(SpringLayout.SOUTH, btnCobrar, -10, SpringLayout.SOUTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, btnCobrar, 20, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.EAST, btnCobrar, 90, SpringLayout.WEST, btnCobrar);
		panel.add(btnCobrar);
		
		btnVolver = new JButton("Volver");
		contenedor.putConstraint(SpringLayout.SOUTH, btnVolver, -10, SpringLayout.SOUTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, btnVolver, -20, SpringLayout.EAST, panel);
		contenedor.putConstraint(SpringLayout.WEST, btnVolver, -90, SpringLayout.EAST, btnVolver);
		panel.add(btnVolver);
	}
}