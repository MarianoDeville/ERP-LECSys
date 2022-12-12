package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import interfaceUsuario.Cobro;
import interfaceUsuario.Listado;
import modelo.DtosCobros;

public class CtrolCobrarCuota implements ActionListener {
	
	private Listado ventanaCobros;
	private DtosCobros dtosCobros;
	private Cobro ventanaCobrar;
	private int elemento;
	private boolean haySeleccion;
	
	public CtrolCobrarCuota(Listado vista) {
		
		this.ventanaCobros = vista;
		this.dtosCobros = new DtosCobros();
		this.ventanaCobros.btnVolver.addActionListener(this);
		this.ventanaCobros.btnImprimir.addActionListener(this);
		this.ventanaCobros.btn1A.addActionListener(this);
		this.ventanaCobros.chckbx1.addActionListener(this);
		this.ventanaCobros.tabla.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {

		        	elemento = ventanaCobros.tabla.getSelectedRow();
		        	haySeleccion = (boolean)ventanaCobros.tabla.getValueAt(elemento, 6);
		        	limpiarOtros();
		        }
		      }
		  });
		this.ventanaCobros.txt2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
	}
	
	public void iniciar() {
		
		ventanaCobros.chckbx1.setText("<html>Pago adelantado</html>");
		ventanaCobros.chckbx1.setSelected(false);
		actualizar();
		ventanaCobros.lblTxt1.setVisible(true);
		ventanaCobros.lblTxt1.setText("Elementos listados:");
		ventanaCobros.txt1.setVisible(true);
		ventanaCobros.txt1.setEditable(false);
		ventanaCobros.txt2.setVisible(true);
		ventanaCobros.txt2.setEditable(true);
		ventanaCobros.btn1A.setVisible(true);
		ventanaCobros.btn1A.setText("Cobrar");
		ventanaCobros.chckbx1.setVisible(true);
		ventanaCobros.setVisible(true);
	}
	
	private void limpiarOtros() {
		
		for(int i = 0; i < ventanaCobros.tabla.getRowCount(); i++) {
			
			if(i != elemento )
				ventanaCobros.tabla.setValueAt((boolean) false, i, 6);
		}
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaCobros.chckbx1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaCobros.btn1A) {
			
			if(procesarInfo()) {
				
				ventanaCobrar = new Cobro("Cobro de cuota");
				CtrolRealizarCobro ctrolCobrar = new CtrolRealizarCobro(ventanaCobrar);
				ctrolCobrar.iniciar();
				ventanaCobrar.btnVolver.addActionListener(this);
			}
		}
		
		if(ventanaCobrar != null) {
			
			if(e.getSource() == ventanaCobrar.btnVolver) {
	
				actualizar();
			}
		}
		
		if(e.getSource() == ventanaCobros.btnImprimir) {
			
			try {
				
				ventanaCobros.tabla.print();
			} catch (PrinterException f) {
				
				f.printStackTrace();
			}
		}
		
		if(e.getSource() == ventanaCobros.btnVolver) {
			
			ventanaCobros.dispose();
		}
	}
	
	private void actualizar() {
		
		ventanaCobros.tabla.setModel(dtosCobros.getTablaDeudores(ventanaCobros.txt2.getText(), 
																 ventanaCobros.chckbx1.isSelected()));
		DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
		derecha.setHorizontalAlignment(JLabel.RIGHT);
		ventanaCobros.tabla.getColumnModel().getColumn(1).setPreferredWidth(75);
		ventanaCobros.tabla.getColumnModel().getColumn(1).setMaxWidth(90);
		ventanaCobros.tabla.getColumnModel().getColumn(1).setCellRenderer(derecha);
		ventanaCobros.tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
		ventanaCobros.tabla.getColumnModel().getColumn(2).setMaxWidth(60);
		ventanaCobros.tabla.getColumnModel().getColumn(2).setCellRenderer(derecha);
		ventanaCobros.tabla.getColumnModel().getColumn(3).setPreferredWidth(75);
		ventanaCobros.tabla.getColumnModel().getColumn(3).setMaxWidth(90);
		ventanaCobros.tabla.getColumnModel().getColumn(3).setCellRenderer(derecha);
		ventanaCobros.tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
		ventanaCobros.tabla.getColumnModel().getColumn(4).setMaxWidth(60);
		ventanaCobros.tabla.getColumnModel().getColumn(4).setCellRenderer(derecha);
		ventanaCobros.tabla.getColumnModel().getColumn(5).setPreferredWidth(75);
		ventanaCobros.tabla.getColumnModel().getColumn(5).setMaxWidth(90);
		ventanaCobros.tabla.getColumnModel().getColumn(5).setCellRenderer(derecha);
		ventanaCobros.tabla.getColumnModel().getColumn(6).setPreferredWidth(40);
		ventanaCobros.tabla.getColumnModel().getColumn(6).setMaxWidth(50);
		ventanaCobros.txt1.setText(ventanaCobros.tabla.getRowCount() + "");
	}
	
	private boolean procesarInfo() {
		
		if(!haySeleccion) {
			
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningún elemento.");
			return false;
		}
		dtosCobros.setElementoSeleccionado(elemento);
		dtosCobros.setInfoCobro();
		return true;
	}
}
