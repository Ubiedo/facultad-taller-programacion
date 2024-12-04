package presentacion;
/*
 * ConsultarVuelo
 * 
 * Implementacion de un internal frame para la ventana ConsultarVuelo.
 *
 * 04/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
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

import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import logica.*;
/*import logica.enumerado.*;
import logica.datatype.*;
import logica.controlador.*;*/

public class ConsultarVuelo extends JInternalFrame{
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
	public ConsultarVuelo(IControladorUsuarios ICU ,IControladorRutas ICR, IControladorVuelos ICV) {
		
		//---------------------------------------------------DESCOMENTAR PARA CASOS PRUEBA-------------------------------------------
		//casosPrueba(ICU, ICR, ICV);
		//---------------------------------------------------------------------------------------------------------------------------
		setClosable(true);
		setTitle("Consulta De Vuelo");
		JLabel lblAerolinea = new JLabel("Elegir una Aerolinea:");
		JComboBox listaAerolineas = new JComboBox();
		
		
		JLabel lblRutaDeVuelo = new JLabel("Elegir una Ruta de Vuelo:");
		
		JComboBox listaRutas = new JComboBox();
		
		JLabel lblVuelo = new JLabel("Elegir un Vuelo:");
		
		JComboBox listaVuelos = new JComboBox();
		
		JPanel panel = new JPanel();

		List<String> myList = new ArrayList<>(0);
		JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
		list.setEnabled(false);
		JScrollPane scrollPaneTexto = new JScrollPane(); 
		scrollPaneTexto.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
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
					limpiarTexto(scrollPaneTexto);
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
				limpiarTexto(scrollPaneTexto);
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
				Set<String> reservasAsociadas = ICV.listarReservasAsociadasVuelo(v);
				List<String> myList = new ArrayList<>(reservasAsociadas.size());
				for (String reserva : reservasAsociadas) {
					   myList.add(reserva);
					}
				JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
				scrollPaneTexto.setViewportView(list);
				updating = false;
				}
			}
			}
		);
		//----------------------------------------------------------------------------------------------------------------------------------------------
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAerolinea)
						.addComponent(listaAerolineas, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRutaDeVuelo)
						.addComponent(listaRutas, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVuelo, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addComponent(listaVuelos, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
					//.addContainerGap(200, Short.MAX_VALUE))
				.addGap(20)
		));
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
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
							.addComponent(listaVuelos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(20))
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
		
		JLabel lblReservasAsociadas = new JLabel("Reservas Asociadas:");
		
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
									.addComponent(lblReservasAsociadas)
									.addGap(10)
									.addComponent(scrollPaneTexto, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
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
									.addComponent(txtAsientosTuristas, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(142, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(119, Short.MAX_VALUE)
					.addComponent(lblTitulo)
					.addGap(117))
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
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblReservasAsociadas)
						.addComponent(scrollPaneTexto, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addGap(46))
		);
		panel.setLayout(gl_panel);
		scrollPaneTexto.setViewportView(list);
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
	private void limpiarTexto(JScrollPane pane) {
		txtNombre.setText("");
		txtFecha.setText("");
		txtDuracion.setText("");
		txtAsientosTuristas.setText("");
		txtAsientosEjecutivos.setText("");
		txtFechaDeAlta.setText("");
		List<String> myList = new ArrayList<>(0);
		JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
		pane.setViewportView(list);
	}
    
	public static void main(String[] args) {
		
	}
}

