package presentacion;
/*
 * CrearPaquete
 * 
 * Implementacion de un internal frame para la ventana principal.
 *
 * 04/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;

//import logica.controlador.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import logica.*;
public class CrearPaquete extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JSpinner spinner_dia;
	private JSpinner spinner_mes;
	private JSpinner spinner_anio;
	private JSpinner spinner_validez;
	private JSpinner spinner_descuento;
	public CrearPaquete(IControladorVuelos CtrlVuelos) {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Crear Paquete de Rutas");
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		
		JLabel lblValidez = new JLabel("Validez(en dias):");
		
		spinner_validez = new JSpinner();
		
		JLabel lblVDescuento = new JLabel("Descuento:");
		
		spinner_descuento = new JSpinner();
		
		JLabel lblVPorcentaje = new JLabel("%");
		
		JLabel lblFechaDeAlta = new JLabel("Fecha de Alta:");
		
		spinner_dia = new JSpinner();
		
		spinner_mes = new JSpinner();
	
		spinner_anio = new JSpinner();
		
		JLabel lblDia = new JLabel("Dia");
		
		JLabel lblMes = new JLabel("Mes");
		
		JLabel lblAo = new JLabel("Año");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = textField.getText();
				String descripcion = textField_1.getText();
				Integer validez = (Integer) spinner_validez.getValue();
				Integer desc = (Integer) spinner_descuento.getValue();
				Float descuento = desc.floatValue();
				Integer dia = (Integer) spinner_dia.getValue();
				Integer mes = (Integer) spinner_mes.getValue();
				Integer anio = (Integer) spinner_anio.getValue();
				Boolean valida = true;
				LocalDate fechaAlta = null;
				
				if(nombre.isEmpty()||descripcion.isEmpty()) {
					JOptionPane.showMessageDialog(CrearPaquete.this, "No puede haber campos vacíos", "Crear Paquete", JOptionPane.ERROR_MESSAGE);
				}else if (validez <= 0) {
					JOptionPane.showMessageDialog(CrearPaquete.this, "La validez debe ser mayor a 0 ", "Crear Paquete", JOptionPane.ERROR_MESSAGE);
					}else if(descuento <= 0){
						JOptionPane.showMessageDialog(CrearPaquete.this, "El descuento debe ser mayor a 0 ", "Crear Paquete", JOptionPane.ERROR_MESSAGE);

					}else {
					try {
						fechaAlta = LocalDate.of(anio, mes, dia);
					}catch(DateTimeException e) {
						valida = false;
	        			JOptionPane.showMessageDialog(CrearPaquete.this, "La fecha ingresada no es válida", "Crear Paquete", JOptionPane.ERROR_MESSAGE);
					}
					if (valida) {
						Boolean res = CtrlVuelos.crearPaquete(nombre, descripcion, validez, descuento, fechaAlta, "");
						if (!res) {
							JOptionPane.showMessageDialog(CrearPaquete.this, "El paquete ingresada ya existe en el sistema", "Crear Paquete", JOptionPane.ERROR_MESSAGE);
						}else {
							limpiar();
							JOptionPane.showMessageDialog(CrearPaquete.this, "El paquete ha sido creado con éxito", "Crear Paquete",JOptionPane.INFORMATION_MESSAGE);
		        			
						}
						
					}
				}
			}
		});
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});

		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	dispose();
            }
        });
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNombre)
							.addGap(18)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
							.addGap(56)
							.addComponent(lblValidez))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDescripcion)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
							.addComponent(lblVDescuento, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(spinner_descuento, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblVPorcentaje, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(spinner_validez, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addGap(14))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(133)
					.addComponent(lblDia, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(lblMes, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(lblAo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(137, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFechaDeAlta)
							.addGap(40)
							.addComponent(spinner_dia, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addComponent(btnAceptar)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
							.addComponent(btnCancelar)
							.addGap(30))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(spinner_mes, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spinner_anio, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(145, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(lblValidez)
						.addComponent(spinner_validez, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescripcion)
						.addComponent(lblVDescuento, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(spinner_descuento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVPorcentaje)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDia)
						.addComponent(lblMes)
						.addComponent(lblAo))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaDeAlta)
						.addComponent(spinner_dia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_mes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_anio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnBorrar)
						.addComponent(btnCancelar))
					.addGap(23))
		);
		getContentPane().setLayout(groupLayout);
	}
	private void limpiar() {
		textField.setText("");
		textField_1.setText("");
		spinner_dia.setValue(0);
		spinner_mes.setValue(0);
		spinner_anio.setValue(0);
		spinner_validez.setValue(0);
		spinner_descuento.setValue(0);
		
	}
}
