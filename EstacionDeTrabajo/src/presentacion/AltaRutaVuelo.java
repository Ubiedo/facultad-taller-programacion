package presentacion;

import javax.swing.JInternalFrame;

/*import logica.controlador.*;
import logica.enumerado.EstadoRuta;*/
import logica.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class AltaRutaVuelo extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldDescCorta;
	
	public AltaRutaVuelo (IControladorRutas ctrlRutas, IControladorUsuarios ctrlUsuarios) {
		setClosable(true);
		setTitle("Alta de Ruta de Vuelo");
		
		JLabel lblAerolinea = new JLabel("Aerolínea");
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblDescripcion = new JLabel("Descripción");
		
		JLabel lblHora = new JLabel("Hora");
		
		JLabel lblCostoTurista = new JLabel("Costo Turista");
		
		JLabel lblCostoEjecutivo = new JLabel("Costo Ejecutivo");
		
		JLabel lblCostoEquipaje = new JLabel("Costo Equipaje");
		
		JLabel lblCiudadOrigen = new JLabel("Ciudad Orígen");
		
		JLabel lblCiudadDestino = new JLabel("Ciudad Destino");
		
		JLabel lblCategorias = new JLabel("Categorías");
		
		JLabel lblFechaAlta = new JLabel("Fecha de Alta");
		
		Set<String> categoriasAux = ctrlRutas.listarCategorias();
		String[] categorias = categoriasAux.toArray(String[]::new);
		
		Set<String> aerolineasAux = ctrlUsuarios.listarAerolineas();
		String[] aerolineas = aerolineasAux.toArray(String[]::new);
		
		Set<String> ciudadesAux = ctrlRutas.listarCiudades();
		String[] ciudades = ciudadesAux.toArray(String[]::new);
		
		JComboBox comboBoxCOrigen = new JComboBox();
		comboBoxCOrigen.setModel(new DefaultComboBoxModel(ciudades));
		
		JSpinner spinnerHora = new JSpinner();
		spinnerHora.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		
		JSpinner spinnerMinutos = new JSpinner();
		spinnerMinutos.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JComboBox comboBoxCDestino = new JComboBox();
		comboBoxCDestino.setModel(new DefaultComboBoxModel(ciudades));
		
		JSpinner spinnerAnio = new JSpinner();
		spinnerAnio.setModel(new SpinnerNumberModel(0, 0, 9999, 1));
		
		JSpinner spinnerMes = new JSpinner();
		spinnerMes.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		
		JSpinner spinnerDia = new JSpinner();
		spinnerDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JComboBox comboBoxAerolineas = new JComboBox();
		comboBoxAerolineas.setModel(new DefaultComboBoxModel(aerolineas));
		
		JTextArea textAreaDescripcion = new JTextArea();
		
		JSpinner spinnerTurista = new JSpinner();
		spinnerTurista.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(999999), Float.valueOf(50)));
		
		JSpinner spinnerEjecutivo = new JSpinner();
		spinnerEjecutivo.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(999999), Float.valueOf(50)));
		
		JSpinner spinnerEquipaje = new JSpinner();
		spinnerEquipaje.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(999999), Float.valueOf(50)));
		
		JList<String> listCategorias = new JList<>(categorias);
		
		textFieldDescCorta = new JTextField();
		textFieldDescCorta.setColumns(10);
		
		JLabel lblDescripcionCorta = new JLabel("Descripción Corta");
		
		JButton btnCrearRutaVuelo = new JButton("Crear Ruta de Vuelo");
        btnCrearRutaVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nickname = (String) comboBoxAerolineas.getSelectedItem();
				String nombre = textFieldNombre.getText();
				String descripcion = textAreaDescripcion.getText();
				String descCorta = textFieldDescCorta.getText();
				Integer hora = (Integer) spinnerHora.getValue();
				Integer minutos = (Integer) spinnerMinutos.getValue();
				Integer dia = (Integer) spinnerDia.getValue();
				Integer mes = (Integer) spinnerMes.getValue();
				Integer anio = (Integer) spinnerAnio.getValue();
				Float turista = (Float) spinnerTurista.getValue();
				Float ejecutivo = (Float) spinnerEjecutivo.getValue();
				Float equipaje = (Float) spinnerEquipaje.getValue();
				String cOr = (String) comboBoxCOrigen.getSelectedItem();
				String cDes = (String) comboBoxCDestino.getSelectedItem();
				LocalDate fechaAlta = null;
				LocalTime horaRuta = LocalTime.of(hora, minutos);
				int[] indCats = listCategorias.getSelectedIndices();
				Set<String> catsElegidas = new HashSet<>();
				for (int i = 0; i < indCats.length; i++) {
					catsElegidas.add(categorias[i]);
				}
				Boolean valida = true;
				
				if (nickname.isEmpty() || nombre.isEmpty() || descripcion.isEmpty() || cOr.isEmpty() || cDes.isEmpty()) {
		            JOptionPane.showMessageDialog(AltaRutaVuelo.this, "Campo(s) vacío(s)", "Alta Ruta de Vuelo", JOptionPane.ERROR_MESSAGE);
				} else if (cOr == cDes){
					JOptionPane.showMessageDialog(AltaRutaVuelo.this, "Ciudades de origen y destino deben ser distintas", "Alta Ruta de Vuelo", JOptionPane.ERROR_MESSAGE);
		        } else {
		        	try {
		        		fechaAlta = LocalDate.of(anio,mes,dia);
		        		}catch(DateTimeException e){
		        			valida = false;
		        			JOptionPane.showMessageDialog(AltaRutaVuelo.this, "La fecha ingresada no es válida", "Alta Ruta de Vuelo", JOptionPane.ERROR_MESSAGE);
		        	}
		        	if (valida) {
		        		Integer res = ctrlRutas.altaRutaVuelo(nickname, nombre, descripcion, horaRuta, 
		        				turista, ejecutivo, equipaje, cOr, cDes, fechaAlta, catsElegidas, "", descCorta, EstadoRuta.Ingresada, "");
		        		if (res == 1) {
		        			JOptionPane.showMessageDialog(AltaRutaVuelo.this, "Nombre ya en uso", "Alta Ruta de Vuelo", JOptionPane.ERROR_MESSAGE);
		        		}else {
		        			JOptionPane.showMessageDialog(AltaRutaVuelo.this, "Ruta de vuelo ingresada con éxito", "Alta Ruta de Vuelo",
		                            JOptionPane.INFORMATION_MESSAGE);
		        			setVisible(false);
		        		}
		        	}
		        }
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCategorias)
						.addComponent(lblNombre)
						.addComponent(lblAerolinea)
						.addComponent(lblCostoTurista)
						.addComponent(lblFechaAlta)
						.addComponent(lblHora))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblCiudadDestino)
										.addComponent(lblCiudadOrigen))
									.addGap(4))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(spinnerHora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(spinnerMinutos, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE))
										.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(comboBoxAerolineas, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(textFieldNombre)
											.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addComponent(spinnerTurista, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(lblCostoEjecutivo))))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(spinnerEjecutivo, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblDescripcion)
													.addGap(6))
												.addComponent(lblDescripcionCorta))
											.addPreferredGap(ComponentPlacement.RELATED)))))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(110))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCostoEquipaje)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerEquipaje, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBoxCDestino, 0, 138, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBoxCOrigen, 0, 138, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldDescCorta, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
								.addComponent(scrollPane_2, Alignment.LEADING)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(spinnerDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerAnio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
							.addComponent(btnCrearRutaVuelo)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAerolinea)
								.addComponent(comboBoxAerolineas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombre)
								.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDescripcion)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldDescCorta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescripcionCorta)
						.addComponent(lblHora)
						.addComponent(spinnerHora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerMinutos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerEquipaje, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCostoEquipaje)
						.addComponent(spinnerEjecutivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCostoEjecutivo)
						.addComponent(spinnerTurista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCostoTurista))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategorias)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxCOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCiudadOrigen))
							.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(comboBoxCDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblCiudadDestino))
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCrearRutaVuelo)
						.addComponent(spinnerDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerAnio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechaAlta))
					.addGap(26))
		);
		
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane_2.setViewportView(textAreaDescripcion);
		scrollPane.setViewportView(listCategorias);
		getContentPane().setLayout(groupLayout);
		
	}
}
