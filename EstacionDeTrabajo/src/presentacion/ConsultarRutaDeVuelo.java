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
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JButton;
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
import java.awt.Component;

public class ConsultarRutaDeVuelo extends JInternalFrame{
	private JTextField txtNombreRuta;
	private boolean updating = false;
	private boolean opcionEnListaVuelo = false;
	private JPanel panel = new JPanel();
	private JTextField txtNombreVuelo;
	private JTextField txtFechaVuelo;
	private JTextField txtDuracionVuelo;
	private JTextField txtAsientosEjecutivosVuelo;
	private JTextField txtFechaDeAltaVuelo;
	private JTextField txtAsientosTuristasVuelo;
	private Asiento tipoAsiento;
	private String lastAerolinea = "";
	private String lastRuta = "";
	private String lastVuelo = "";
	private JTextField txtHoraRuta;
	private JTextField txtFechaDeAltaRuta;
	private JTextField txtPrecioTuristaRuta;
	private JTextField txtPrecioEjecutivoRuta;
	private JTextField txtPrecioUnEqExtraRuta;
	private JTextArea descripcionRuta_1;
	private JTextArea descripcionRuta_1_1;
	private JTextField txtOrigen;
	private JTextField txtDestino;
	private JTextField txtEstado;
	public ConsultarRutaDeVuelo(IControladorUsuarios ICU ,IControladorRutas ICR, IControladorVuelos ICV) {
		//---------------------------------------------------DESCOMENTAR PARA CASOS PRUEBA-------------------------------------------
		//casosPrueba(ICU, ICR, ICV);
		//---------------------------------------------------------------------------------------------------------------------------
		setClosable(true);
		setTitle("Consulta De Ruta De Vuelo");
		String test;
		JLabel lblAerolinea = new JLabel("Elegir una Aerolinea:");
		JComboBox listaAerolineas = new JComboBox();
		
		JLabel lblRutaDeVuelo = new JLabel("Elegir una Ruta de Vuelo:");
		
		JScrollPane scrollPaneTexto = new JScrollPane();
		scrollPaneTexto.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		List<String> myList = new ArrayList<>(0);
		JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
		scrollPaneTexto.setViewportView(list);
		
		JComboBox listaRutas = new JComboBox();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(206, 206, 206));
		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		JTextArea descripcionRuta = nuevoText("");
		
		txtNombreRuta = new JTextField();
		txtNombreRuta.setBorder(null);
		txtNombreRuta.setEditable(false);
		txtNombreRuta.setColumns(10);
		txtNombreRuta.setBackground(new Color(206, 206, 206));
		
		JLabel lblHora = new JLabel("Hora:");
		
		JLabel lblDatosDeLa = new JLabel("DATOS DE LA RUTA DE VUELO");
		lblDatosDeLa.setFont(new Font("Sylfaen", Font.BOLD, 12));
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		
		JScrollPane scrollPaneTexto_1 = new JScrollPane();
		JScrollPane scrollPaneTexto_1_1 = new JScrollPane();
		scrollPaneTexto_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneTexto_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel lblFechaDeAlta = new JLabel("Fecha de Alta:");
		
		JLabel lblPrecioTurista = new JLabel("Precio Turista:");
		
		JLabel lblPrecioEjecutivo = new JLabel("Precio Ejecutivo:");
		
		JLabel lblPrecioUnidadDe = new JLabel("Precio Unidad de Equipaje Extra:");
		
		txtHoraRuta = new JTextField();
		txtHoraRuta.setBorder(null);
		txtHoraRuta.setEditable(false);
		txtHoraRuta.setColumns(10);
		txtHoraRuta.setBackground(new Color(206, 206, 206));
		
		txtFechaDeAltaRuta = new JTextField();
		txtFechaDeAltaRuta.setBorder(null);
		txtFechaDeAltaRuta.setEditable(false);
		txtFechaDeAltaRuta.setColumns(10);
		txtFechaDeAltaRuta.setBackground(new Color(206, 206, 206));
		
		txtPrecioTuristaRuta = new JTextField();
		txtPrecioTuristaRuta.setBorder(null);
		txtPrecioTuristaRuta.setEditable(false);
		txtPrecioTuristaRuta.setColumns(10);
		txtPrecioTuristaRuta.setBackground(new Color(206, 206, 206));
		
		txtPrecioEjecutivoRuta = new JTextField();
		txtPrecioEjecutivoRuta.setBorder(null);
		txtPrecioEjecutivoRuta.setEditable(false);
		txtPrecioEjecutivoRuta.setColumns(10);
		txtPrecioEjecutivoRuta.setBackground(new Color(206, 206, 206));
		
		txtPrecioUnEqExtraRuta = new JTextField();
		txtPrecioUnEqExtraRuta.setBorder(null);
		txtPrecioUnEqExtraRuta.setEditable(false);
		txtPrecioUnEqExtraRuta.setColumns(10);
		txtPrecioUnEqExtraRuta.setBackground(new Color(206, 206, 206));
		
		JLabel lblVuelo = new JLabel("Vuelos Asociados:");
		
		JComboBox listaVuelos = new JComboBox();
		/*listaVuelos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!updating && (listaVuelos.getSelectedItem() != lastVuelo)) {
				updating = true;
				lastVuelo = (String) listaVuelos.getSelectedItem();
				
				updating = false;
				}
			}
			}
		);*/
		
		JButton btnInfoVuelo = new JButton("Mostrar Info Vuelo");
		btnInfoVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaVuelos.getItemCount()!=0) {
					panel.setVisible(true);
					String v = (String) listaVuelos.getSelectedItem();
					DataVuelo vuelo = ICV.listarDatosVuelo(v);
					txtNombreVuelo.setText(vuelo.getNombre());
					txtFechaVuelo.setText(vuelo.getFecha().toString());
					txtDuracionVuelo.setText(vuelo.getDuracion().toString());
					txtAsientosTuristasVuelo.setText(String.valueOf(vuelo.getAsientosTurista()));
					txtAsientosEjecutivosVuelo.setText(String.valueOf(vuelo.getAsientosEjecutivo()));
					txtFechaDeAltaVuelo.setText(vuelo.getFechaAlta().toString());
					Set<String> reservasAsociadas = ICV.listarReservasAsociadasVuelo(v);
					List<String> myList = new ArrayList<>(reservasAsociadas.size());
					for (String reserva : reservasAsociadas) {
						   myList.add(reserva);
						}
					JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
					scrollPaneTexto.setViewportView(list);
					Component[] componentes = getContentPane().getComponents();
					setVisible(false);
					setVisible(true);
				}
				
				/*for (Component c : componentes) {
					
					if(c.getName() == "panel" && opcionEnListaVuelo) c.setVisible(true);
					
				}*/
			}
		});
		
		JLabel lblOrigen = new JLabel("Origen:");
		
		txtOrigen = new JTextField();
		txtOrigen.setEditable(false);
		txtOrigen.setColumns(10);
		txtOrigen.setBorder(null);
		txtOrigen.setBackground(new Color(206, 206, 206));
		
		JLabel lblDestino = new JLabel("Destino:");
		
		txtDestino = new JTextField();
		txtDestino.setEditable(false);
		txtDestino.setColumns(10);
		txtDestino.setBorder(null);
		txtDestino.setBackground(new Color(206, 206, 206));
		
		JLabel lblEstado = new JLabel("Estado:");
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBorder(null);
		txtEstado.setBackground(new Color(206, 206, 206));
		
		JLabel lblDescripcionCorta = new JLabel("Descripcion Corta:");
		
		//JScrollPane scrollPaneTexto_1_1 = new JScrollPane();
		scrollPaneTexto_1_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneTexto_1_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(127)
					.addComponent(lblDatosDeLa, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(136))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblOrigen, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDestino, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(8)
									.addComponent(txtDestino, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtOrigen, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNombre_1)
							.addGap(10)
							.addComponent(txtNombreRuta, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblHora, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtHoraRuta, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblPrecioEjecutivo, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPrecioEjecutivoRuta, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblPrecioUnidadDe, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPrecioUnEqExtraRuta, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblFechaDeAlta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblPrecioTurista, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(txtFechaDeAltaRuta, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPrecioTuristaRuta, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
					.addGap(103))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtEstado, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(162, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblVuelo, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(listaVuelos, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnInfoVuelo))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblDescripcion)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPaneTexto_1, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(24, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDescripcionCorta, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTexto_1_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(71, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatosDeLa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre_1)
						.addComponent(txtNombreRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHora)
						.addComponent(txtHoraRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaDeAlta)
						.addComponent(txtFechaDeAltaRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecioTurista)
						.addComponent(txtPrecioTuristaRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecioEjecutivo)
						.addComponent(txtPrecioEjecutivoRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecioUnidadDe)
						.addComponent(txtPrecioUnEqExtraRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrigen)
						.addComponent(txtOrigen, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDestino)
						.addComponent(txtDestino, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEstado)
						.addComponent(txtEstado, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcionCorta)
						.addComponent(scrollPaneTexto_1_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcion)
						.addComponent(scrollPaneTexto_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVuelo)
						.addComponent(listaVuelos, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInfoVuelo))
					.addGap(38))
		);
		//----------------------------------------------------------------------------------------------------------------------------
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
					limpiarTextoVuelo(scrollPaneTexto);
					limpiarTextoRuta(scrollPaneTexto_1, scrollPaneTexto_1_1);
					Set<String> rutasDeAerolinea = ICU.listarRutasDeAerolinea((String) listaAerolineas.getSelectedItem());
					lastAerolinea = (String) listaAerolineas.getSelectedItem();
					for (String ruta : rutasDeAerolinea) {
						listaRutas.addItem(ruta);
						
					}
					panel.setVisible(false);
					updating = false;
				}
			}});
		listaRutas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!updating && (listaRutas.getSelectedItem() != lastRuta)) {
				updating = true;
				listaVuelos.removeAllItems();
				limpiarTextoVuelo(scrollPaneTexto);
				Set<String> vuelosDeRuta = ICR.vuelosDeRuta((String) listaRutas.getSelectedItem());
				lastRuta = (String) listaRutas.getSelectedItem();
				DataRuta r = ICR.listarDatosRuta(lastRuta);
				txtNombreRuta.setText(r.getNombre());
				txtHoraRuta.setText(r.getHora().toString());
				txtFechaDeAltaRuta.setText(r.getFechaAlta().toString());
				txtPrecioTuristaRuta.setText(r.getTuristaBase().toString());
				txtPrecioEjecutivoRuta.setText(r.getEjecutivoBase().toString());
				txtPrecioUnEqExtraRuta.setText(r.getUnidadEquipajeExtra().toString());
				txtEstado.setText(r.getEstado().toString());
				txtOrigen.setText(ICR.getOrigenRuta(r.getNombre()).getNombre() + ", " + ICR.getOrigenRuta(r.getNombre()).getPais());
				txtDestino.setText(ICR.getDestinoRuta(r.getNombre()).getNombre() + ", " + ICR.getDestinoRuta(r.getNombre()).getPais());
				JTextArea descripcionRuta = nuevoText(r.getDescripcion());
				scrollPaneTexto_1.setViewportView(descripcionRuta);
				JTextArea descripcionRuta_1 = nuevoText(r.getDescCorta());
				scrollPaneTexto_1_1.setViewportView(descripcionRuta_1);
				for (String vuelo : vuelosDeRuta) {
					listaVuelos.addItem(vuelo);
				}
				//opcionEnListaVuelo = true;
				updating = false;}
			}
		});
		
		panel_1.setLayout(gl_panel_1);
		
		//JPanel panel = new JPanel();
		panel.setBackground(new Color(206, 206, 206));
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		txtNombreVuelo = new JTextField();
		txtNombreVuelo.setEditable(false);
		txtNombreVuelo.setColumns(10);
		txtNombreVuelo.setBorder(null);
		txtNombreVuelo.setBackground(new Color(206, 206, 206));
		
		JLabel lblFecha = new JLabel("Fecha:");
		
		txtFechaVuelo = new JTextField();
		txtFechaVuelo.setEditable(false);
		txtFechaVuelo.setColumns(10);
		txtFechaVuelo.setBorder(null);
		txtFechaVuelo.setBackground(new Color(206, 206, 206));
		
		JLabel lblTitulo = new JLabel("DATOS DEL VUELO");
		lblTitulo.setFont(new Font("Sylfaen", Font.BOLD, 12));
		
		JLabel lblReservasAsociadas = new JLabel("Reservas Asociadas:");
		

		JLabel lblDuracion = new JLabel("Duracion:");
		
		txtDuracionVuelo = new JTextField();
		txtDuracionVuelo.setEditable(false);
		txtDuracionVuelo.setColumns(10);
		txtDuracionVuelo.setBorder(null);
		txtDuracionVuelo.setBackground(new Color(206, 206, 206));
		
		JLabel lblAsientosEjecutivos = new JLabel("Asientos Ejecutivos:");
		
		txtAsientosEjecutivosVuelo = new JTextField();
		txtAsientosEjecutivosVuelo.setEditable(false);
		txtAsientosEjecutivosVuelo.setColumns(10);
		txtAsientosEjecutivosVuelo.setBorder(null);
		txtAsientosEjecutivosVuelo.setBackground(new Color(206, 206, 206));
		
		JLabel lblFechaAlta = new JLabel("Fecha de Alta:");
		
		txtFechaDeAltaVuelo = new JTextField();
		txtFechaDeAltaVuelo.setEditable(false);
		txtFechaDeAltaVuelo.setColumns(10);
		txtFechaDeAltaVuelo.setBorder(null);
		txtFechaDeAltaVuelo.setBackground(new Color(206, 206, 206));
		
		JLabel lblAsientosTuristas = new JLabel("Asientos Turistas:");
		
		txtAsientosTuristasVuelo = new JTextField();
		txtAsientosTuristasVuelo.setEditable(false);
		txtAsientosTuristasVuelo.setColumns(10);
		txtAsientosTuristasVuelo.setBorder(null);
		txtAsientosTuristasVuelo.setBackground(new Color(206, 206, 206));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 482, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtNombreVuelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFechaVuelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(146)
							.addComponent(lblTitulo))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblReservasAsociadas)
									.addGap(10)
									.addComponent(scrollPaneTexto, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblDuracion, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDuracionVuelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblAsientosEjecutivos, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtAsientosEjecutivosVuelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblFechaAlta)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtFechaDeAltaVuelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblAsientosTuristas)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtAsientosTuristasVuelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 356, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitulo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombreVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFecha)
						.addComponent(txtFechaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDuracion)
						.addComponent(txtDuracionVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAsientosTuristas)
						.addComponent(txtAsientosTuristasVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAsientosEjecutivos)
						.addComponent(txtAsientosEjecutivosVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaAlta)
						.addComponent(txtFechaDeAltaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblReservasAsociadas)
							.addGap(42))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPaneTexto, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
							.addContainerGap())))
		);
		panel.setLayout(gl_panel);

		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(listaAerolineas, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAerolinea))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRutaDeVuelo)
								.addComponent(listaRutas, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE)))
					.addGap(58))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRutaDeVuelo)
								.addComponent(lblAerolinea))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(listaRutas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(listaAerolineas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		scrollPaneTexto_1.setViewportView(descripcionRuta);
		//scrollPaneTexto_1_1.setViewportView(descripcionRuta_1);
		getContentPane().setLayout(groupLayout);
		panel.setVisible(false);
		panel.setName("panel");
		//getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel, listaAerolineas, lblAerolinea, lblRutaDeVuelo, listaRutas, panel_1, lblNombre_1, txtNombreRuta, lblHora, txtHoraRuta, lblDescripcion, scrollPaneTexto_1, descripcionRuta_1, lblPrecioEjecutivo, txtPrecioEjecutivoRuta, lblPrecioTurista, txtPrecioTuristaRuta, lblPrecioUnidadDe, txtPrecioUnEqExtraRuta, lblFechaDeAlta, txtFechaDeAltaRuta, lblDatosDeLa, lblVuelo, listaVuelos, btnInfoVuelo, lblNombre, txtNombreVuelo, lblFecha, txtFechaVuelo, lblTitulo, lblReservasAsociadas, scrollPaneTexto, list, lblDuracion, txtDuracionVuelo, lblAsientosEjecutivos, txtAsientosEjecutivosVuelo, lblFechaAlta, txtFechaDeAltaVuelo, lblAsientosTuristas, txtAsientosTuristasVuelo}));
		
		
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
		ICR.altaRutaVuelo("ote", "oteNombreDeRuta", "ruta de vuelo de ote", LocalTime.of(8, 10),(float) 100, (float)150, (float)111, "Montevideo"+"Uruguay", "Sydney"+"Australia", LocalDate.of(2024, 10, 7), categorias, "", "");
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
	
	private void limpiarTextoVuelo(JScrollPane pane) {
		txtNombreVuelo.setText("");
		txtFechaVuelo.setText("");
		txtDuracionVuelo.setText("");
		txtAsientosTuristasVuelo.setText("");
		txtAsientosEjecutivosVuelo.setText("");
		txtFechaDeAltaVuelo.setText("");
		List<String> myList = new ArrayList<>(0);
		JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
		pane.setViewportView(list);
		lastVuelo = "";
		//opcionEnListaVuelo = false;
		setVisible(false);
		setVisible(true);
	}
    
	private void limpiarTextoRuta(JScrollPane pane, JScrollPane pane2){
		txtNombreRuta.setText("");
		txtHoraRuta.setText("");
		txtFechaDeAltaRuta.setText("");
		txtPrecioTuristaRuta.setText("");
		txtPrecioEjecutivoRuta.setText("");
		txtPrecioUnEqExtraRuta.setText("");
		txtEstado.setText("");
		txtOrigen.setText("");
		txtDestino.setText("");
		JTextArea descripcionRuta =  nuevoText("");
		pane.setViewportView(descripcionRuta);
		JTextArea descripcionRuta_1 =  nuevoText("");
		pane2.setViewportView(descripcionRuta_1);
		lastRuta = "";
		//opcionEnListaVuelo = false;
		setVisible(false);
		setVisible(true);
	}
	
	private JTextArea nuevoText(String texto) {
		descripcionRuta_1 = new JTextArea(texto);
		descripcionRuta_1.setEditable(false);
		descripcionRuta_1.setWrapStyleWord(true);
		descripcionRuta_1.setLineWrap(true);
		return descripcionRuta_1;
	}
	private JTextArea nuevoText2(String texto) {
		descripcionRuta_1_1 = new JTextArea(texto);
		descripcionRuta_1_1.setEditable(false);
		descripcionRuta_1_1.setWrapStyleWord(true);
		descripcionRuta_1_1.setLineWrap(true);
		return descripcionRuta_1_1;
	}
	
	
	public static void main(String[] args) {
		
	}
}

