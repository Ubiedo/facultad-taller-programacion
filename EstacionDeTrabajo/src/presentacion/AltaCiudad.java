package presentacion;
/*
 * AltaCiudad
 * 
 * Implementacion de un internal frame para la ventana principal.
 *
 * 04/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import javax.swing.JInternalFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import logica.*;
//import logica.controlador.*;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import java.time.DateTimeException;
import java.time.LocalDate;

public class AltaCiudad extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldPais;
	private JTextField textFieldAeropuerto;
	private JTextField textFieldDescripcion;
	private JTextField textFieldSweb;
	private JSpinner spinner_dia;
	private JSpinner spinner_mes;
	private JSpinner spinner_anio;
	public AltaCiudad(IControladorRutas CtrlRutas) {
		setClosable(true);
		
		setTitle("Alta de Ciudad");
		spinner_dia = new JSpinner();
		
		spinner_anio = new JSpinner();

		spinner_mes = new JSpinner();

		JLabel lblAnio = new JLabel("Año");
		
		JLabel lblMes = new JLabel("Mes");
		
		JLabel lblDia = new JLabel("Día");
		
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		
		textFieldPais = new JTextField();
		textFieldPais.setColumns(10);
		
		textFieldAeropuerto = new JTextField();
		textFieldAeropuerto.setColumns(10);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setColumns(10);
		
		textFieldSweb = new JTextField();
		textFieldSweb.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		JLabel lblAeropuerto = new JLabel("Aeropuerto:");
		
		JLabel lblSitioWeb = new JLabel("Sitio Web");
		
		JLabel lblPais = new JLabel("Pais:");
		
		JLabel lblFechaAlta = new JLabel("FechaAlta :");
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = textFieldNombre.getText();
				String descripcion = textFieldDescripcion.getText();
				String pais = textFieldPais.getText();
				String aeropuerto = textFieldAeropuerto.getText();
				String sweb = textFieldSweb.getText();
				Integer dia = (Integer) spinner_dia.getValue();
				Integer mes = (Integer) spinner_mes.getValue();
				Integer anio = (Integer) spinner_anio.getValue();
				Boolean valida = true;
				LocalDate fechaAlta = null;
				
				if (nombre.isEmpty() || descripcion.isEmpty() || pais.isEmpty() || aeropuerto.isEmpty() || sweb.isEmpty()) {
		            JOptionPane.showMessageDialog(AltaCiudad.this, "No puede haber campos vacíos", "Alta Ciudad", JOptionPane.ERROR_MESSAGE);
		        }else {
		        	try {
		        		fechaAlta = LocalDate.of(anio,mes,dia);
		        		}catch(DateTimeException e){
		        			valida = false;
		        			JOptionPane.showMessageDialog(AltaCiudad.this, "La fecha ingresada no es válida", "Alta Ciudad", JOptionPane.ERROR_MESSAGE);
		        	}
		        	if (valida) {
		        		Boolean res = CtrlRutas.altaCiudad(nombre, pais, aeropuerto, descripcion, sweb, fechaAlta);
		        		if (!res) {
		        			JOptionPane.showMessageDialog(AltaCiudad.this, "La ciudad ingresada ya existe en el sistema", "Alta Ciudad", JOptionPane.ERROR_MESSAGE);
		        		}else {
		        			limpiar();
		        			JOptionPane.showMessageDialog(AltaCiudad.this, "La ciudad ha sido de alta con éxito", "Alta Ciudad",
		                            JOptionPane.INFORMATION_MESSAGE);
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
		
		JLabel lblNewLabel = new JLabel("Descripción:");
	
		
		
	
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNombre)
										.addComponent(lblAeropuerto))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textFieldNombre, Alignment.TRAILING)
										.addComponent(textFieldAeropuerto, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(19)
											.addComponent(lblPais))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNewLabel)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textFieldPais)
										.addComponent(textFieldDescripcion, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSitioWeb)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldSweb))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblFechaAlta)
											.addGap(51)
											.addComponent(spinner_dia, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(56)
													.addComponent(lblMes))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(47)
													.addComponent(spinner_mes, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
											.addGap(52))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnBorrar)
											.addGap(69)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnCancelar)
										.addComponent(lblAnio)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(spinner_anio, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(43)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnConfirmar)
									.addGap(100))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDia)
									.addGap(59)))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldPais, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPais))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAeropuerto)
						.addComponent(textFieldAeropuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSitioWeb)
						.addComponent(textFieldSweb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDia)
						.addComponent(lblAnio)
						.addComponent(lblMes))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaAlta)
						.addComponent(spinner_dia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_anio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_mes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConfirmar)
						.addComponent(btnCancelar)
						.addComponent(btnBorrar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	private void limpiar() {
		textFieldNombre.setText("");
		textFieldPais.setText("");
		textFieldDescripcion.setText("");
		textFieldAeropuerto.setText("");
		textFieldSweb.setText("");
		spinner_dia.setValue(0);
		spinner_mes.setValue(0);
		spinner_anio.setValue(0);
		
	}
}
