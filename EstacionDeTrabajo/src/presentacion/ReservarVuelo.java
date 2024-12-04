package presentacion; 

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import logica.*;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

/*import logica.enumerado.*;
import logica.datatype.*;
import logica.controlador.*;*/
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ReservarVuelo extends JInternalFrame{
	
	private static final long serialVersionUID = 1L;
	private Asiento tipoAsiento;
	private JTextField txtNombre;
	private JTextField txtFecha;
	private JTextField txtDuracion;
	private JTextField txtAsientosTuristas;
	private JTextField txtAsientosEjecutivos;
	private JTextField txtFechaDeAlta;
	private boolean updating = false;
	private String lastAerolinea = "";
	private String lastRuta = "";
	private String lastVuelo = "";
	private JTextField textFieldApellido;
	private JTextField textFieldNombre;
	private int contadorPasajes = 1;
	private Set<DataPasaje> pasajes = new HashSet<>();
	
	public ReservarVuelo(IControladorUsuarios ICU ,IControladorRutas ICR, IControladorVuelos ICV) {
		setClosable(true);
		setTitle("Reservar Vuelo");
		JLabel lblAerolinea = new JLabel("Elegir una Aerolinea:");
		JComboBox listaAerolineas = new JComboBox();
		
		JLabel lblRutaDeVuelo = new JLabel("Elegir una Ruta de Vuelo:");
		
		JComboBox listaRutas = new JComboBox();
		
		JLabel lblVuelo = new JLabel("Elegir un Vuelo:");
		
		JComboBox listaVuelos = new JComboBox();
		
		JPanel panel = new JPanel();

		List<String> myList = new ArrayList<>(0);
		panel.setBackground(new Color(206, 206, 206));
		
		//----------------------------------------------------------------------------------------------------------------------------------------------
		Set<String> nombresAerolineas = ICU.listarAerolineas();
		for (String aerolinea : nombresAerolineas) {
			listaAerolineas.addItem(aerolinea);
		}
		listaAerolineas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!updating && (listaAerolineas.getSelectedItem() != lastAerolinea)) {
					updating = true;
					listaRutas.removeAllItems();
					listaVuelos.removeAllItems();
					Set<String> rutasDeAerolinea = ICU.listarRutasDeAerolinea((String) listaAerolineas.getSelectedItem());
					lastAerolinea = (String) listaAerolineas.getSelectedItem();
					for (String ruta : rutasDeAerolinea) {
						listaRutas.addItem(ruta);
						
					}
					updating = false;
				}
			}});
		listaRutas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!updating && (listaRutas.getSelectedItem() != lastRuta)) {
				updating = true;
				listaVuelos.removeAllItems();
				Set<String> vuelosDeRuta = ICR.vuelosDeRuta((String) listaRutas.getSelectedItem());
				lastRuta = (String) listaRutas.getSelectedItem();
				for (String vuelo : vuelosDeRuta) {
					listaVuelos.addItem(vuelo);
				}
				updating = false;}
			}
		});
		listaVuelos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!updating && (listaVuelos.getSelectedItem() != lastVuelo)) {
				updating = true;
				lastVuelo = (String) listaVuelos.getSelectedItem();
				String v = (String) listaVuelos.getSelectedItem();
				DataVuelo vuelo = ICV.listarDatosVuelo(v);
				txtNombre.setText(vuelo.getNombre());
				txtFecha.setText(vuelo.getFecha().toString());
				txtDuracion.setText(vuelo.getDuracion().toString());
				txtAsientosTuristas.setText(String.valueOf(vuelo.getAsientosTurista()));
				txtAsientosEjecutivos.setText(String.valueOf(vuelo.getAsientosEjecutivo()));
				txtFechaDeAlta.setText(vuelo.getFechaAlta().toString());
				updating = false;
				}
			}
			}
		);
		
		Set<String> nicknamesAux = ICU.listarClientes();
		String[] nicknames = nicknamesAux.toArray(String[]::new);
		JComboBox listaNickname = new JComboBox();
		listaNickname.setModel(new DefaultComboBoxModel(nicknames));
		
		JComboBox listaAsientos = new JComboBox();
		listaAsientos.setModel(new DefaultComboBoxModel(new String[] {"Turista", "Ejecutivo"}));
		
		JLabel lblNickname = new JLabel("Nickname");
		
		JLabel lblAsiento = new JLabel("Asiento");
		
		JSpinner spinnerEquipaje = new JSpinner();
		spinnerEquipaje.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		
		JLabel lblEquipaje = new JLabel("Cant. Equipaje Extra");
		
		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		
		JLabel lblNombrePasaje = new JLabel("Nombre");
		
		JLabel lblApellidoPasaje = new JLabel("Apellido");
		
		JTextPane textPanePasajes = new JTextPane();
		textPanePasajes.setEditable(false);
		Integer contadorPasajesAux = (Integer) contadorPasajes;
		textPanePasajes.setText(contadorPasajesAux.toString());
		
		JLabel lblPasajes = new JLabel("Cant. Pasajes");
		
		JLabel lblAgregarPasajes = new JLabel("Agregar Pasajes: Cliente ya incluído");
		
		JLabel lblFechaReserva = new JLabel("Fecha de Reserva");
		
		JSpinner spinnerDia = new JSpinner();
		spinnerDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		
		JSpinner spinnerMes = new JSpinner();
		spinnerMes.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		
		JSpinner spinnerAnio = new JSpinner();
		spinnerAnio.setModel(new SpinnerNumberModel(0, 0, 9999, 1));
		
		JButton btnPasaje = new JButton("Agregar Pasaje");
		btnPasaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldNombre.getText();
				String apellido = textFieldApellido.getText();
				if (nombre.isEmpty() || apellido.isEmpty()) {
		            JOptionPane.showMessageDialog(ReservarVuelo.this, "Campo(s) vacío(s)", "Reserva de Vuelo", JOptionPane.ERROR_MESSAGE);
				} else {
					DataPasaje dp = new DataPasaje(nombre, apellido);
					pasajes.add(dp);
					contadorPasajes++;
					Integer contadorPasajesAux = (Integer) contadorPasajes;
					textPanePasajes.setText(contadorPasajesAux.toString());
					textFieldNombre.setText("");
					textFieldApellido.setText("");
				}
			}
			}
		);
		
		JButton btnReservarVuelo = new JButton("Reservar Vuelo");
		btnReservarVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vuelo = (String) listaVuelos.getSelectedItem();
				String cliente = (String) listaNickname.getSelectedItem();
				Asiento tipoAsiento = Asiento.turista;
				if (listaAsientos.getSelectedItem() == "Ejecutivo")
					tipoAsiento = Asiento.ejecutivo;
				Integer dia = (Integer) spinnerDia.getValue();
				Integer mes = (Integer) spinnerMes.getValue();
				Integer anio = (Integer) spinnerAnio.getValue();
				Integer nroEquipaje = (Integer) spinnerEquipaje.getValue();
				LocalDate fechaRes = null;
				Boolean valida = true;
				if ((vuelo == null) || (cliente == null)) {
		            JOptionPane.showMessageDialog(ReservarVuelo.this, "Campo(s) vacío(s)", "Reserva de Vuelo", JOptionPane.ERROR_MESSAGE);
				} else {
		        	try {
		        		fechaRes = LocalDate.of(anio,mes,dia);
		        		} catch(DateTimeException e1){
		        		valida = false;
		        		JOptionPane.showMessageDialog(ReservarVuelo.this, "La fecha ingresada no es válida", "Reserva de Vuelo", JOptionPane.ERROR_MESSAGE);
		        	}
		        	if (valida) {
		        		Integer res = ICV.reservaDeVuelo(vuelo, cliente, fechaRes, tipoAsiento, contadorPasajes, nroEquipaje, pasajes);
		        		if (res == 2) {
		        			JOptionPane.showMessageDialog(ReservarVuelo.this, "No hay asientos disponibles para cubrir todos los pasajes solicitados", "Reserva de Vuelo", JOptionPane.ERROR_MESSAGE);
		        		}else {
		        			if (res == 1) {
		        				JOptionPane.showMessageDialog(ReservarVuelo.this, "El vuelo ya fue reservado por el cliente anteriormente", "Reserva de Vuelo", JOptionPane.ERROR_MESSAGE);
		        			} else {
		        				limpiar();
		        				JOptionPane.showMessageDialog(ReservarVuelo.this, "Vuelo reservado con éxito", "Reserva de Vuelo",
		        						JOptionPane.INFORMATION_MESSAGE);
		        				setVisible(false);
		        			}
		        		}
		        	}
				}
			}
			}
		);
		//----------------------------------------------------------------------------------------------------------------------------------------------
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(listaNickname, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(359, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblAerolinea)
											.addComponent(listaAerolineas, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblRutaDeVuelo)
											.addComponent(listaRutas, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblVuelo, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
											.addComponent(listaVuelos, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblEquipaje)
											.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
											.addComponent(spinnerEquipaje, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNombrePasaje, Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblAsiento)
											.addPreferredGap(ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
											.addComponent(lblApellidoPasaje))
										.addComponent(listaAsientos, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addComponent(lblFechaReserva)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(spinnerDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(spinnerMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(spinnerAnio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblNickname))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblPasajes)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textPanePasajes, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblAgregarPasajes)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textFieldApellido, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(btnReservarVuelo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnPasaje, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
							.addGap(140))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAerolinea)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(listaAerolineas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblRutaDeVuelo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(listaRutas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblVuelo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(listaVuelos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(spinnerEquipaje, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEquipaje)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAgregarPasajes)
						.addComponent(lblNickname))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(listaNickname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPasajes)
							.addComponent(lblNombrePasaje))
						.addComponent(textPanePasajes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAsiento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(listaAsientos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFechaReserva)
								.addComponent(spinnerDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerAnio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnPasaje)
								.addComponent(textFieldApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblApellidoPasaje))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReservarVuelo)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblTitulo = new JLabel("DATOS DEL VUELO");
		lblTitulo.setFont(new Font("Sylfaen", Font.BOLD, 12));
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		txtNombre = new JTextField();
		txtNombre.setBorder(null);
		txtNombre.setEditable(false);
		txtNombre.setBackground(new Color(206, 206, 206));
		txtNombre.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		
		JLabel lblDuracion = new JLabel("Duracion:");
		
		JLabel lblAsientosTuristas = new JLabel("Asientos Turistas:");
		
		JLabel lblAsientosEjecutivos = new JLabel("Asientos Ejecutivos:");
		
		JLabel lblFechaAlta = new JLabel("Fecha de Alta:");
		
		txtFecha = new JTextField();
		txtFecha.setBorder(null);
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBackground(new Color(206, 206, 206));
		
		txtDuracion = new JTextField();
		txtDuracion.setBorder(null);
		txtDuracion.setEditable(false);
		txtDuracion.setColumns(10);
		txtDuracion.setBackground(new Color(206, 206, 206));
		
		txtAsientosTuristas = new JTextField();
		txtAsientosTuristas.setBorder(null);
		txtAsientosTuristas.setEditable(false);
		txtAsientosTuristas.setColumns(10);
		txtAsientosTuristas.setBackground(new Color(206, 206, 206));
		
		txtAsientosEjecutivos = new JTextField();
		txtAsientosEjecutivos.setBorder(null);
		txtAsientosEjecutivos.setEditable(false);
		txtAsientosEjecutivos.setColumns(10);
		txtAsientosEjecutivos.setBackground(new Color(206, 206, 206));
		
		txtFechaDeAlta = new JTextField();
		txtFechaDeAlta.setBorder(null);
		txtFechaDeAlta.setEditable(false);
		txtFechaDeAlta.setColumns(10);
		txtFechaDeAlta.setBackground(new Color(206, 206, 206));
		
	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblDuracion, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDuracion, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblAsientosEjecutivos, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtAsientosEjecutivos, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblFechaAlta)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtFechaDeAlta, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblAsientosTuristas)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtAsientosTuristas, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTitulo)))
					.addContainerGap(257, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitulo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFecha)
						.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDuracion)
						.addComponent(txtDuracion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAsientosTuristas)
						.addComponent(txtAsientosTuristas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAsientosEjecutivos)
						.addComponent(txtAsientosEjecutivos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaAlta)
						.addComponent(txtFechaDeAlta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	private void casosPrueba(IControladorUsuarios ICU, IControladorRutas ICR, IControladorVuelos ICV) {
		/*ICU.crearAerolinea("ote", "nombre", "email1", "web", "descripcion");
		ICU.crearAerolinea("fefo", "nombre", "email2", "web", "descripcion");
		ICU.crearAerolinea("joaco", "nombre", "email3", "web", "descripcion");
		ICU.crearAerolinea("manu", "nombre", "email4", "web", "descripcion");
		ICU.crearAerolinea("vale", "nombre", "email5", "web", "descripcion");
		ICU.crearCliente("nickCLiente", "nombreCliente", "email7", "apellidoCliente", LocalDate.of(2002, 9, 22), "Uruguay", Documento.CI, "53238989");
		ICU.crearCliente("joaquito", "joaco", "email8", "vila", LocalDate.of(2002, 9, 22), "Uruguay", Documento.CI, "53238988");
		ICR.altaCiudad("Montevideo", "Uruguay", "Aeropuerto Internacional de Carrasco", "Uruguay noma", "www.sitioweb.com", LocalDate.of(2024, 9, 4));
		ICR.altaCiudad("Sydney", "Australia", "Aeropuerto Internacional Kingsford Smith", "Canguros", "www.sitioweb2.com", LocalDate.of(2024, 9, 3));
		ICR.ingresarNombre("categoria1");
		ICR.ingresarNombre("categoria2");
		Set<String> categorias = new HashSet<>(Arrays.asList("categoria1","categoria2"));
		ICR.altaRutaVuelo("ote", "oteNombreDeRuta", "ruta de vuelo de ote", LocalTime.now(),(float) 100, (float)150, (float)111, "Montevideo"+"Uruguay", "Sydney"+"Australia", LocalDate.of(2024, 10, 7), categorias, "", "");
		ICV.altaVuelo("oteNombreDeRuta", "vuelo de ote", LocalDate.of(2024, 10, 10), LocalTime.of(8, 30), 50, 20, LocalDate.of(2024,10, 5));
		DataPasaje pasaje = new DataPasaje("nombreCliente", "apellidoCliente");
		Set<DataPasaje> pasajes = new HashSet<>();
		pasajes.add(pasaje);
		DataPasaje pasaje2 = new DataPasaje("nombreCliente2", "apellidoCliente2");
		Set<DataPasaje> pasajes2 = new HashSet<>();
		pasajes2.add(pasaje2);
		ICV.reservaDeVuelo("vuelo de ote", "nickCLiente", LocalDate.of(2024, 9, 4), tipoAsiento.ejecutivo, 1, 20, pasajes );
		ICV.reservaDeVuelo("vuelo de ote", "joaquito", LocalDate.of(2024, 9, 4), tipoAsiento.turista, 1, 20, pasajes2 );
		*/
    }
	private void limpiar() {
		textFieldNombre.setText("");
		textFieldApellido.setText("");
	}
    
	public static void main(String[] args) {
		
	}
}