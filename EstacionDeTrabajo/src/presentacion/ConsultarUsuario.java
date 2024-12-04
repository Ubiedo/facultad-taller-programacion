package presentacion;
/*
 * ConsultarUsuario
 * 
 * Implementacion de un internal frame para la ventana principal.
 *
 * 04/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import logica.*;
/*import logica.datatype.*;
import logica.controlador.*;*/
import javax.swing.JTextArea;
/*
 * ConsultarUsuario
 * 
 * Implementacion de la ventada para el caso de uso Consulta de Usuario
 *
 * 02/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
public class ConsultarUsuario extends JInternalFrame{
	private JPanel panel;
	private JPanel panelRuta;
	private JLabel lblWeb;
	private JLabel lblDescripcion;
	private JLabel lblNacionalidad;
	private JLabel lblApellido;
	private JLabel lblFechaNac;
	private JLabel lblTipoDoc;
	private JLabel lblNumDoc;
	private JTextArea descripcionAero;
	private JTextArea descripcionRuta;
	private JTextArea descripcionPaquete;
	private JTextArea descripcionRuta2;
	private JTextArea descripcionRuta2_2;
	private JTextField txtNicknameC;
	private JTextField txtNombreC;
	private JTextField txtApellido;
	private JTextField txtEmailC;
	private JTextField txtNacionalidad;
	private JTextField txtTipoDoc;
	private JTextField txtNumDoc;
	private JTextField txtFechaNac;
	private JTextField txtNicknameA;
	private JTextField txtNombreA;
	private JTextField txtEmailA;
	private JTextField txtWeb;
	private JTextField txtFReservaVuelo;
	private JTextField txtTipoAsiento;
	private JTextField txtCantPasajes;
	private JTextField txtCantEqExtra;
	private JTextField txtCostoReserva;
	private JTextField txtFCompra;
	private JTextField txtVencimiento;
	private JTextField txtCostoPaquete;
	private JPanel panelCliente = new JPanel();
	private JPanel panelAerolinea = new JPanel();
	private boolean updating = false;
	private boolean agregandoRutas = false;
	private String lastUsuario = "";
	private String lastRuta = "";
	private String lastVuelo = "";
	private String lastListaRutas = "";
	private String lastListaVuelos = "";
	private String lastVueloAsociado = "";
	private int contadorReservas = 0;
	private int contadorPaquetes = 0;
	private int contadorRutas = 0;
	//private JScrollPane scrollPanePasajes;
	private JList<String> listPasajes;
	//private ClienteVuelo[] reservasUsuario;
	private List<DataClienteVuelo> reservasUsuario = new ArrayList<DataClienteVuelo>(0);
	private List<DataClientePaquete> paquetesUsuario = new ArrayList<DataClientePaquete>(0);
	private List<DataPaqueteRuta> rutasUsuario = new ArrayList<DataPaqueteRuta>(0);
	private JList<String> listPaquetes;
	private JTextField txtContadorReservas;
	private JTextField txtNombreVuelo;
	private JTextField txtFechaVuelo;
	private JTextField txtDuracionVuelo;
	private JTextField txtAsEjVuelo;
	private JTextField txtFechaAltaVuelo;
	private JTextField txtAsTurVuelo;
	private JTextField txtContadorPaquetes;
	private JTextField txtNomVueRes;
	private JTextField txtNombrePaqueteComp;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField txtContadorRutas;
	private JTextField textField_9;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_8;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField txtEstado;
	private JTextField textContrasenhaA;
	private JTextField textField_25;
	private JTextField txtContrasenhaC;
	public ConsultarUsuario(IControladorUsuarios ICU ,IControladorRutas ICR, IControladorVuelos ICV) {
		setClosable(true);
		setTitle("Consultar Usuario");
		JComboBox listaUsuarios = new JComboBox();
		JScrollPane scrollPaneTextoAero = new JScrollPane();
		JPanel panelVuelo = new JPanel();
		JComboBox listaVuelos = new JComboBox();
		listaVuelos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lastListaVuelos != listaVuelos.getSelectedItem()) {
					panelVuelo.setVisible(false);
					setVisible(false);
					setVisible(true);
					lastListaVuelos = (String) listaVuelos.getSelectedItem();
				}
			}
		});
		JComboBox listaRutas = new JComboBox();
		
		JPanel panelPaquete = new JPanel();
		panelPaquete.setVisible(false);
		JScrollPane scrollPaneTexto_1 = new JScrollPane();
		JScrollPane scrollPaneTexto_1_1 = new JScrollPane();
		descripcionRuta2 = nuevoTextRuta2("");
		descripcionRuta2_2 = nuevoTextRuta2_2("");
		JScrollPane scrollPaneTextoRuta = new JScrollPane();
		JScrollPane scrollPaneTextoRutaCorta = new JScrollPane();
		//----
		List<String> myListPasajes = new ArrayList<>(0);
		JList<String> listPasajes = new JList<String>(myListPasajes.toArray(new String[myListPasajes.size()]));
		listPasajes.setEnabled(false);
		JScrollPane scrollPanePasajes = new JScrollPane(); 
		scrollPanePasajes.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPanePasajes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//---
		
		txtContadorReservas = new JTextField("");
		txtContadorReservas.setBorder(null);
		JComboBox listaVuelos_1 = new JComboBox();
		listaVuelos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lastVueloAsociado!=listaVuelos_1.getSelectedItem()) {
					lastVueloAsociado=(String) listaVuelos_1.getSelectedItem();
					panelVuelo.setVisible(false);
				}
			}
		});
		txtContadorReservas.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPaneTextoAero.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPaneTextoAero.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneTextoAero.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Set<String> usuarios = ICU.listarUsuarios();
		lastUsuario = (String) listaUsuarios.getSelectedItem();
		for (String u : usuarios) {
			listaUsuarios.addItem(u);
			
		}
		JPanel panelRutaAsociada = new JPanel();
		panelRutaAsociada.setVisible(false);
		listaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!updating && (listaUsuarios.getSelectedItem() != lastUsuario)) {
					updating = true;
					lastUsuario = (String) listaUsuarios.getSelectedItem();
					//limpiarTexto(scrollPaneTexto);
					panelVuelo.setVisible(false);
					panelPaquete.setVisible(false);
					panelRutaAsociada.setVisible(false);
					String usuario = (String) listaUsuarios.getSelectedItem();
					if (ICU.esCliente(usuario)) {
						clearCliente(scrollPanePasajes);
						panelCliente.setVisible(true);
						panelAerolinea.setVisible(false);
						DataCliente DTu = (DataCliente) ICU.listarDatosUsuarioNickname(usuario);
						txtNicknameC.setText(DTu.getNickname());
						txtNombreC.setText(DTu.getNombre());
						txtContrasenhaC.setText(DTu.getContrasenha());
						txtApellido.setText(DTu.getApellido());
						txtEmailC.setText(DTu.getEmail());
						txtNacionalidad.setText(DTu.getNacionalidad());
						txtTipoDoc.setText(DTu.getTipoDocumento().toString());
						txtNumDoc.setText(DTu.getNroDocumento());
						txtFechaNac.setText(DTu.getFechaNac().toString());
						
						Set<DataClienteVuelo> ru = ICU.listarReservasUsuario(usuario);
						reservasUsuario.clear();
						for (DataClienteVuelo r : ru) {
							reservasUsuario.add(r);
						}
						contadorReservas = 0;
						if (!ru.isEmpty()) {
							txtContadorReservas.setText("1/" + ru.size());
							actualizarReserva(0, scrollPanePasajes);
						}
						else txtContadorReservas.setText("0/0");

						Set<DataClientePaquete> pu = ICU.listarPaquetesUsuario(usuario);
						paquetesUsuario.clear();
						for (DataClientePaquete p : pu) {
							paquetesUsuario.add(p);
						}
						contadorPaquetes = 0;
						if (!pu.isEmpty()) {
							txtContadorPaquetes.setText("1/" + pu.size());
							actualizarPaquete(0);
						}
						else txtContadorPaquetes.setText("0/0");
						
					}
					else {
						clearRutas(scrollPaneTextoRuta, listaVuelos, scrollPaneTextoRutaCorta);
						panelAerolinea.setVisible(true);
						panelCliente.setVisible(false);
						DataAerolinea DTa = (DataAerolinea) ICU.listarDatosUsuarioNickname(usuario);
						txtNicknameA.setText(DTa.getNickname());
						txtNombreA.setText(DTa.getNombre());
						txtEmailA.setText(DTa.getEmail());
						textContrasenhaA.setText(DTa.getContrasenha());
						txtWeb.setText(DTa.getWeb());
						descripcionAero = nuevoTextAero(DTa.getDescripcion());
						scrollPaneTextoAero.setViewportView(descripcionAero); 
						agregandoRutas = true;
						listaRutas.removeAllItems();
						Set<String> rutas = ICU.listarRutasDeAerolinea(usuario);
						for (String r : rutas) {
							listaRutas.addItem(r);
						}
						agregandoRutas = false;
					}
					
					setVisible(false);
					setVisible(true);
					updating = false;
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Elegir un usuario:");
		//JPanel panelCliente = new JPanel();
		panelCliente.setBackground(new Color(206, 206, 206));
		JTextArea descripcionAero = nuevoTextAero("");
		JTextArea descripcionRuta = nuevoTextRuta("");
		JTextArea descripcionPaquete = nuevoTextPaquete("");
		//JPanel panelAerolinea = new JPanel();
		panelAerolinea.setBackground(new Color(206, 206, 206));
		
		
		panelVuelo.setVisible(false);
		panelVuelo.setBackground(new Color(206, 206, 206));
		
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
		
		JScrollPane scrollPaneTexto = new JScrollPane();
		scrollPaneTexto.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel lblDuracion = new JLabel("Duracion:");
		
		txtDuracionVuelo = new JTextField();
		txtDuracionVuelo.setEditable(false);
		txtDuracionVuelo.setColumns(10);
		txtDuracionVuelo.setBorder(null);
		txtDuracionVuelo.setBackground(new Color(206, 206, 206));
		
		JLabel lblAsientosEjecutivos = new JLabel("Asientos Ejecutivos:");
		
		txtAsEjVuelo = new JTextField();
		txtAsEjVuelo.setEditable(false);
		txtAsEjVuelo.setColumns(10);
		txtAsEjVuelo.setBorder(null);
		txtAsEjVuelo.setBackground(new Color(206, 206, 206));
		
		JLabel lblFechaAlta = new JLabel("Fecha de Alta:");
		
		txtFechaAltaVuelo = new JTextField();
		txtFechaAltaVuelo.setEditable(false);
		txtFechaAltaVuelo.setColumns(10);
		txtFechaAltaVuelo.setBorder(null);
		txtFechaAltaVuelo.setBackground(new Color(206, 206, 206));
		
		JLabel lblAsientosTuristas = new JLabel("Asientos Turistas:");
		JButton izquierdaPaquetes_1 = new JButton("<");
		txtAsTurVuelo = new JTextField();
		txtAsTurVuelo.setEditable(false);
		txtAsTurVuelo.setColumns(10);
		txtAsTurVuelo.setBorder(null);
		txtAsTurVuelo.setBackground(new Color(206, 206, 206));
		GroupLayout gl_panelVuelo = new GroupLayout(panelVuelo);
		gl_panelVuelo.setHorizontalGroup(
			gl_panelVuelo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelVuelo.createSequentialGroup()
					.addGroup(gl_panelVuelo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelVuelo.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtNombreVuelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelVuelo.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFechaVuelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelVuelo.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelVuelo.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelVuelo.createSequentialGroup()
									.addComponent(lblReservasAsociadas)
									.addGap(10)
									.addComponent(scrollPaneTexto, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelVuelo.createSequentialGroup()
									.addComponent(lblDuracion, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDuracionVuelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelVuelo.createSequentialGroup()
									.addComponent(lblAsientosEjecutivos, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtAsEjVuelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelVuelo.createSequentialGroup()
									.addComponent(lblFechaAlta)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtFechaAltaVuelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelVuelo.createSequentialGroup()
									.addComponent(lblAsientosTuristas)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtAsTurVuelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panelVuelo.createSequentialGroup()
							.addGap(98)
							.addComponent(lblTitulo)))
					.addGap(20))
		);
		gl_panelVuelo.setVerticalGroup(
			gl_panelVuelo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelVuelo.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitulo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelVuelo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombreVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_panelVuelo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFecha)
						.addComponent(txtFechaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelVuelo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDuracion)
						.addComponent(txtDuracionVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelVuelo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAsientosTuristas)
						.addComponent(txtAsTurVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelVuelo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAsientosEjecutivos)
						.addComponent(txtAsEjVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelVuelo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaAlta)
						.addComponent(txtFechaAltaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelVuelo.createParallelGroup(Alignment.LEADING)
						.addComponent(lblReservasAsociadas)
						.addComponent(scrollPaneTexto, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addGap(20))
		);
		panelVuelo.setLayout(gl_panelVuelo);
		
		panelPaquete.setBackground(new Color(206, 206, 206));
		
		JLabel lblDatosDelPaquete = new JLabel("DATOS DEL PAQUETE");
		lblDatosDelPaquete.setFont(new Font("Sylfaen", Font.BOLD, 12));
		
		JLabel lblVuelo_1 = new JLabel("Rutas Asociadas:");
		
		JButton btnInfoVuelo_1 = new JButton("Mostrar Info Ruta");
		btnInfoVuelo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rutasUsuario.size()!=0) {
					DataRuta dr = ICR.listarDatosRuta(rutasUsuario.get(contadorRutas).getRuta());
					textField_19.setText(dr.getNombre());
					textField_20.setText(dr.getHora().toString());
					textField_24.setText(dr.getFechaAlta().toString());
					textField_22.setText(String.valueOf(dr.getTuristaBase()));
					textField_21.setText(String.valueOf(dr.getEjecutivoBase()));
					textField_23.setText(String.valueOf(dr.getUnidadEquipajeExtra()));
					textField_25.setText(dr.getEstado().toString());
					textField_18.setText(ICR.getOrigenRuta(dr.getNombre()).getNombre() + ", " + ICR.getOrigenRuta(dr.getNombre()).getPais());
					textField_8.setText(ICR.getDestinoRuta(dr.getNombre()).getNombre() + ", " + ICR.getDestinoRuta(dr.getNombre()).getPais());
					descripcionRuta2 = nuevoTextRuta2(dr.getDescripcion());
					descripcionRuta2_2 = nuevoTextRuta2_2(dr.getDescCorta());
					scrollPaneTexto_1.setViewportView(descripcionRuta2);
					scrollPaneTexto_1_1.setViewportView(descripcionRuta2_2);
					panelRutaAsociada.setVisible(true);
					listaVuelos_1.removeAllItems();
					Set<String> vuelos = ICR.vuelosDeRuta(dr.getNombre());
					for (String v : vuelos) {
						listaVuelos_1.addItem(v);
					}
					panelVuelo.setVisible(false);
					setVisible(false);
					setVisible(true);
				}
				
			}
		});
		
		JLabel lblDescripcion_3 = new JLabel("Descripcion:");
		
		JScrollPane scrollPaneDescripcionPaquete = new JScrollPane();
		scrollPaneDescripcionPaquete.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneDescripcionPaquete.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel lblNombre_1_2 = new JLabel("Nombre:");
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBorder(null);
		textField_10.setBackground(new Color(206, 206, 206));
		
		JLabel lblCantidadDeRutas = new JLabel("Cantidad De Rutas:");
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBorder(null);
		textField_11.setBackground(new Color(206, 206, 206));
		
		JLabel lblDescuento = new JLabel("Descuento:");
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBorder(null);
		textField_12.setBackground(new Color(206, 206, 206));
		
		JLabel lblDiasDeValidez = new JLabel("Dias de Validez:");
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		textField_13.setBorder(null);
		textField_13.setBackground(new Color(206, 206, 206));
		
		JLabel lblCosto = new JLabel("Costo:");
		
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBorder(null);
		textField_14.setBackground(new Color(206, 206, 206));
		
		JLabel lblFechaDeAlta_1 = new JLabel("Fecha de Alta:");
		
		textField_15 = new JTextField();
		textField_15.setEditable(false);
		textField_15.setColumns(10);
		textField_15.setBorder(null);
		textField_15.setBackground(new Color(206, 206, 206));
		
		
		izquierdaPaquetes_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		izquierdaPaquetes_1.setBackground(new Color(206, 206, 206));
		
		txtContadorRutas = new JTextField("");
		txtContadorRutas.setHorizontalAlignment(SwingConstants.CENTER);
		txtContadorRutas.setEditable(false);
		txtContadorRutas.setColumns(10);
		txtContadorRutas.setBorder(null);
		txtContadorRutas.setBackground(new Color(206, 206, 206));
		
		JButton derechaPaquetes_1 = new JButton(">");
		derechaPaquetes_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		derechaPaquetes_1.setBackground(new Color(206, 206, 206));
		
		JLabel lblFechaDeAlta_1_1 = new JLabel("Cantidad:");
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBorder(null);
		textField_9.setBackground(new Color(206, 206, 206));
		
		JLabel lblDiasDeValidez_1 = new JLabel("Tipo de Asiento:");
		
		textField_16 = new JTextField();
		textField_16.setEditable(false);
		textField_16.setColumns(10);
		textField_16.setBorder(null);
		textField_16.setBackground(new Color(206, 206, 206));
		
		JLabel lblDescuento_1 = new JLabel("Ruta de Vuelo:");
		
		textField_17 = new JTextField();
		textField_17.setEditable(false);
		textField_17.setColumns(10);
		textField_17.setBorder(null);
		textField_17.setBackground(new Color(206, 206, 206));
		GroupLayout gl_panelPaquete = new GroupLayout(panelPaquete);
		gl_panelPaquete.setHorizontalGroup(
			gl_panelPaquete.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPaquete.createSequentialGroup()
					.addGap(142, 172, Short.MAX_VALUE)
					.addComponent(lblDatosDelPaquete, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addGap(157))
				.addGroup(gl_panelPaquete.createSequentialGroup()
					.addGap(128)
					.addComponent(izquierdaPaquetes_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtContadorRutas, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(derechaPaquetes_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(198, Short.MAX_VALUE))
				.addGroup(gl_panelPaquete.createSequentialGroup()
					.addGap(165)
					.addComponent(lblVuelo_1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(210, Short.MAX_VALUE))
				.addGroup(gl_panelPaquete.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panelPaquete.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPaquete.createSequentialGroup()
							.addComponent(lblCantidadDeRutas, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelPaquete.createSequentialGroup()
							.addComponent(lblDescuento, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelPaquete.createSequentialGroup()
							.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelPaquete.createSequentialGroup()
							.addComponent(lblNombre_1_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelPaquete.createSequentialGroup()
							.addComponent(lblDescripcion_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPaneDescripcionPaquete, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelPaquete.createSequentialGroup()
							.addComponent(lblFechaDeAlta_1_1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_17, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelPaquete.createSequentialGroup()
							.addGroup(gl_panelPaquete.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblDescuento_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDiasDeValidez_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
							.addGroup(gl_panelPaquete.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelPaquete.createSequentialGroup()
									.addGap(25)
									.addComponent(btnInfoVuelo_1))
								.addGroup(gl_panelPaquete.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelPaquete.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_16, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_panelPaquete.createSequentialGroup()
							.addGroup(gl_panelPaquete.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblFechaDeAlta_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDiasDeValidez, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
							.addGroup(gl_panelPaquete.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelPaquete.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panelPaquete.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))))).addGap(20)
		);
		gl_panelPaquete.setVerticalGroup(
			gl_panelPaquete.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPaquete.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatosDelPaquete)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelPaquete.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre_1_2)
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelPaquete.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantidadDeRutas)
						.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelPaquete.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaDeAlta_1)
						.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelPaquete.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDiasDeValidez)
						.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelPaquete.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescuento)
						.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelPaquete.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCosto)
						.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelPaquete.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcion_3)
						.addComponent(scrollPaneDescripcionPaquete, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addComponent(lblVuelo_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelPaquete.createParallelGroup(Alignment.TRAILING)
						.addComponent(izquierdaPaquetes_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelPaquete.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtContadorRutas, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addComponent(derechaPaquetes_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelPaquete.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPaquete.createSequentialGroup()
							.addGroup(gl_panelPaquete.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDescuento_1)
								.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelPaquete.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDiasDeValidez_1)
								.addComponent(textField_16, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelPaquete.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFechaDeAlta_1_1)
								.addComponent(textField_17, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelPaquete.createSequentialGroup()
							.addGap(89)
							.addComponent(btnInfoVuelo_1)))
					.addGap(20))
		);
		panelPaquete.setLayout(gl_panelPaquete);
		
		
		panelRutaAsociada.setBackground(new Color(206, 206, 206));
		
		JLabel lblDatosDeLa_1 = new JLabel("DATOS DE LA RUTA DE VUELO");
		lblDatosDeLa_1.setFont(new Font("Sylfaen", Font.BOLD, 12));
		
		JLabel lblVuelo_2 = new JLabel("Vuelos Asociados:");
		
		
		
		JButton btnInfoVuelo_2 = new JButton("Mostrar Info Vuelo");
		btnInfoVuelo_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaVuelos_1.getItemCount()!=0) {
					DataVuelo vuelo = ICV.listarDatosVuelo((String) listaVuelos_1.getSelectedItem());
					Set<String> reservasAsociadas = ICV.listarReservasAsociadasVuelo(vuelo.getNombre());
					List<String> myList = new ArrayList<>(reservasAsociadas.size());
					for (String reserva : reservasAsociadas) {
						   myList.add(reserva);
						}
					JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
					scrollPaneTexto.setViewportView(list);
					
					txtNombreVuelo.setText(vuelo.getNombre());
					txtFechaVuelo.setText(vuelo.getFecha().toString());
					txtDuracionVuelo.setText(vuelo.getDuracion().toString());
					txtAsTurVuelo.setText(String.valueOf(vuelo.getAsientosTurista()));
					txtAsEjVuelo.setText(String.valueOf(vuelo.getAsientosEjecutivo()));
					txtFechaAltaVuelo.setText(vuelo.getFechaAlta().toString());
					panelVuelo.setVisible(true);
					setVisible(false);
					setVisible(true);
				}
				
			}
		});
		
		JLabel lblDescripcion_4 = new JLabel("Descripcion:");
		
		
		scrollPaneTexto_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneTexto_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		
		JLabel lblOrigen_1 = new JLabel("Origen:");
		
		JLabel lblDestino_1 = new JLabel("Destino:");
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBorder(null);
		textField_8.setBackground(new Color(206, 206, 206));
		
		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBorder(null);
		textField_18.setBackground(new Color(206, 206, 206));
		
		JLabel lblNombre_1_3 = new JLabel("Nombre:");
		
		textField_19 = new JTextField();
		textField_19.setEditable(false);
		textField_19.setColumns(10);
		textField_19.setBorder(null);
		textField_19.setBackground(new Color(206, 206, 206));
		
		JLabel lblHora_1 = new JLabel("Hora:");
		
		textField_20 = new JTextField();
		textField_20.setEditable(false);
		textField_20.setColumns(10);
		textField_20.setBorder(null);
		textField_20.setBackground(new Color(206, 206, 206));
		
		JLabel lblPrecioEjecutivo_1 = new JLabel("Precio Ejecutivo:");
		
		textField_21 = new JTextField();
		textField_21.setEditable(false);
		textField_21.setColumns(10);
		textField_21.setBorder(null);
		textField_21.setBackground(new Color(206, 206, 206));
		
		JLabel lblPrecioTurista_1 = new JLabel("Precio Turista:");
		
		textField_22 = new JTextField();
		textField_22.setEditable(false);
		textField_22.setColumns(10);
		textField_22.setBorder(null);
		textField_22.setBackground(new Color(206, 206, 206));
		
		JLabel lblPrecioUnidadDe_1 = new JLabel("Precio Unidad de Equipaje Extra:");
		
		textField_23 = new JTextField();
		textField_23.setEditable(false);
		textField_23.setColumns(10);
		textField_23.setBorder(null);
		textField_23.setBackground(new Color(206, 206, 206));
		
		JLabel lblFechaDeAlta_2 = new JLabel("Fecha de Alta:");
		
		textField_24 = new JTextField();
		textField_24.setEditable(false);
		textField_24.setColumns(10);
		textField_24.setBorder(null);
		textField_24.setBackground(new Color(206, 206, 206));
		
		JLabel lblDestino_1_1 = new JLabel("Estado:");
		
		textField_25 = new JTextField();
		textField_25.setEditable(false);
		textField_25.setColumns(10);
		textField_25.setBorder(null);
		textField_25.setBackground(new Color(206, 206, 206));
		
		JLabel lblDescripcion_4_1 = new JLabel("Descripcion Corta:");
		
		//JScrollPane scrollPaneTexto_1_1 = new JScrollPane();
		scrollPaneTexto_1_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneTexto_1_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panelRutaAsociada = new GroupLayout(panelRutaAsociada);
		gl_panelRutaAsociada.setHorizontalGroup(
			gl_panelRutaAsociada.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRutaAsociada.createSequentialGroup()
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelRutaAsociada.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelRutaAsociada.createSequentialGroup()
									.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.LEADING)
										.addComponent(lblOrigen_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDestino_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelRutaAsociada.createSequentialGroup()
											.addGap(8)
											.addComponent(textField_8, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
										.addGroup(gl_panelRutaAsociada.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField_18, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panelRutaAsociada.createSequentialGroup()
									.addComponent(lblNombre_1_3)
									.addGap(10)
									.addComponent(textField_19, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelRutaAsociada.createSequentialGroup()
									.addComponent(lblHora_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_20, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelRutaAsociada.createSequentialGroup()
									.addComponent(lblPrecioEjecutivo_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_21, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelRutaAsociada.createSequentialGroup()
									.addComponent(lblPrecioUnidadDe_1, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_23, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelRutaAsociada.createSequentialGroup()
									.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblFechaDeAlta_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblPrecioTurista_1, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_24, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_22, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
							.addGap(433))
						.addGroup(gl_panelRutaAsociada.createSequentialGroup()
							.addGap(127)
							.addComponent(lblDatosDeLa_1, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)))
					.addGap(136))
				.addGroup(gl_panelRutaAsociada.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDestino_1_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_25, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(820, Short.MAX_VALUE))
				.addGroup(gl_panelRutaAsociada.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDescripcion_4_1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTexto_1_1, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(667, Short.MAX_VALUE))
				.addGroup(gl_panelRutaAsociada.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelRutaAsociada.createSequentialGroup()
							.addComponent(lblVuelo_2, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(listaVuelos_1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnInfoVuelo_2))
						.addGroup(gl_panelRutaAsociada.createSequentialGroup()
							.addComponent(lblDescripcion_4)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPaneTexto_1, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)))
					.addGap(19))
		);
		gl_panelRutaAsociada.setVerticalGroup(
			gl_panelRutaAsociada.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRutaAsociada.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatosDeLa_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre_1_3)
						.addComponent(textField_19, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHora_1)
						.addComponent(textField_20, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaDeAlta_2)
						.addComponent(textField_24, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecioTurista_1)
						.addComponent(textField_22, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecioEjecutivo_1)
						.addComponent(textField_21, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecioUnidadDe_1)
						.addComponent(textField_23, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrigen_1)
						.addComponent(textField_18, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDestino_1)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDestino_1_1)
						.addComponent(textField_25, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcion_4_1)
						.addComponent(scrollPaneTexto_1_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcion_4)
						.addComponent(scrollPaneTexto_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panelRutaAsociada.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVuelo_2)
						.addComponent(listaVuelos_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInfoVuelo_2))
					.addGap(22))
		);
		panelRutaAsociada.setLayout(gl_panelRutaAsociada);
	
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelAerolinea, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelCliente, GroupLayout.PREFERRED_SIZE, 566, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelPaquete, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panelRutaAsociada, GroupLayout.PREFERRED_SIZE, 503, GroupLayout.PREFERRED_SIZE)
								.addComponent(panelVuelo, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNewLabel)
						.addComponent(listaUsuarios, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
					.addGap(22))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(5)
					.addComponent(listaUsuarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelPaquete, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelRutaAsociada, GroupLayout.PREFERRED_SIZE, 621, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panelVuelo, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelAerolinea, GroupLayout.PREFERRED_SIZE, 1090, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelCliente, GroupLayout.PREFERRED_SIZE, 592, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		
		JLabel lblNickname_1 = new JLabel("Nickname:");
		
		JLabel lblEmail_1 = new JLabel("Email:");
		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		
		JLabel lblWeb_1 = new JLabel("Web");
		
		JLabel lblDescripcion_1 = new JLabel("Descripción:");
		
		JLabel lblDatosDeAero = new JLabel("DATOS DE LA AEROLINEA");
		lblDatosDeAero.setFont(new Font("Sylfaen", Font.BOLD, 12));
		
		txtNicknameA = new JTextField();
		txtNicknameA.setBorder(null);
		txtNicknameA.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtNicknameA.setEditable(false);
		txtNicknameA.setColumns(10);
		txtNicknameA.setBackground(new Color(206, 206, 206));
		
		txtNombreA = new JTextField();
		txtNombreA.setBorder(null);
		txtNombreA.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtNombreA.setEditable(false);
		txtNombreA.setColumns(10);
		txtNombreA.setBackground(new Color(206, 206, 206));
		
		txtEmailA = new JTextField();
		txtEmailA.setBorder(null);
		txtEmailA.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtEmailA.setEditable(false);
		txtEmailA.setColumns(10);
		txtEmailA.setBackground(new Color(206, 206, 206));
		
		txtWeb = new JTextField();
		txtWeb.setBorder(null);
		txtWeb.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtWeb.setEditable(false);
		txtWeb.setColumns(10);
		txtWeb.setBackground(new Color(206, 206, 206));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(197, 197, 197));
		
		JLabel lblDatosDeLa = new JLabel("DATOS DE LA RUTA DE VUELO");
		lblDatosDeLa.setFont(new Font("Sylfaen", Font.BOLD, 12));
		
		JLabel lblVuelo = new JLabel("Vuelos Asociados:");
		
	
		JButton btnInfoVuelo = new JButton("Mostrar Info Vuelo");
		btnInfoVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listaVuelos.getItemCount()!=0) {
					DataVuelo vuelo = ICV.listarDatosVuelo((String) listaVuelos.getSelectedItem());
				Set<String> reservasAsociadas = ICV.listarReservasAsociadasVuelo(vuelo.getNombre());
				List<String> myList = new ArrayList<>(reservasAsociadas.size());
				for (String reserva : reservasAsociadas) {
					   myList.add(reserva);
					}
				JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
				scrollPaneTexto.setViewportView(list);
				
				txtNombreVuelo.setText(vuelo.getNombre());
				txtFechaVuelo.setText(vuelo.getFecha().toString());
				txtDuracionVuelo.setText(vuelo.getDuracion().toString());
				txtAsTurVuelo.setText(String.valueOf(vuelo.getAsientosTurista()));
				txtAsEjVuelo.setText(String.valueOf(vuelo.getAsientosEjecutivo()));
				txtFechaAltaVuelo.setText(vuelo.getFechaAlta().toString());
				panelVuelo.setVisible(true);
				setVisible(false);
				setVisible(true);}
				
			}
		});
		
		JLabel lblDescripcion_2 = new JLabel("Descripcion:");
		
		
		scrollPaneTextoRuta.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneTextoRuta.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel lblOrigen = new JLabel("Origen:");
		
		JLabel lblDestino = new JLabel("Destino:");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(new Color(197, 197, 197));
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBackground(new Color(197, 197, 197));
		
		JLabel lblNombre_1_1 = new JLabel("Nombre:");
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		textField_2.setBackground(new Color(197, 197, 197));
		
		JLabel lblHora = new JLabel("Hora:");
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBorder(null);
		textField_3.setBackground(new Color(197, 197, 197));
		
		JLabel lblPrecioEjecutivo = new JLabel("Precio Ejecutivo:");
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBorder(null);
		textField_4.setBackground(new Color(197, 197, 197));
		
		JLabel lblPrecioTurista = new JLabel("Precio Turista:");
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBorder(null);
		textField_5.setBackground(new Color(197, 197, 197));
		
		JLabel lblPrecioUnidadDe = new JLabel("Precio Unidad de Equipaje Extra:");
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBorder(null);
		textField_6.setBackground(new Color(197, 197, 197));
		
		JLabel lblFechaDeAlta = new JLabel("Fecha de Alta:");
		//scrollPaneTextoRuta.setViewportView(descripcionRuta);
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBorder(null);
		textField_7.setBackground(new Color(197, 197, 197));
		
		JLabel lblEstado = new JLabel("Estado:");
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBorder(null);
		txtEstado.setBackground(new Color(197, 197, 197));
		
		JLabel lblDescripcion_corta = new JLabel("Descripcion Corta:");
		
		//JScrollPane scrollPaneTextoRutaCorta = new JScrollPane();
		scrollPaneTextoRutaCorta.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneTextoRutaCorta.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(127)
					.addComponent(lblDatosDeLa, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
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
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNombre_1_1)
							.addGap(10)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblHora, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblPrecioEjecutivo, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblPrecioUnidadDe, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblFechaDeAlta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblPrecioTurista, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
					.addGap(22))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtEstado, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(123, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDescripcion_corta, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPaneTextoRutaCorta, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addGap(95))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblDescripcion_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPaneTextoRuta, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblVuelo, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(listaVuelos, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnInfoVuelo)))
					.addGap(22))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatosDeLa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre_1_1)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHora)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaDeAlta)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecioTurista)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecioEjecutivo)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecioUnidadDe)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrigen)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDestino)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(txtEstado, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcion_corta)
						.addComponent(scrollPaneTextoRutaCorta, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcion_2)
						.addComponent(scrollPaneTextoRuta, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVuelo)
						.addComponent(listaVuelos, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInfoVuelo))
					.addGap(22))
		);
		panel_1.setLayout(gl_panel_1);
		
		/*listaRutas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lastListaRutas != listaRutas.getSelectedItem()) {
					listaVuelos.removeAllItems();
					panelVuelo.setVisible(false);
					setVisible(false);
					setVisible(true);
					lastListaRutas = (String) listaRutas.getSelectedItem();
				}
			}
		});*/
		listaRutas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lastListaRutas != listaRutas.getSelectedItem()){
					listaVuelos.removeAllItems();
					panelVuelo.setVisible(false);
					lastListaRutas = (String) listaRutas.getSelectedItem();
					//if(!agregandoRutas) {
					if(listaRutas.getItemCount()!=0) {
						DataRuta ruta = ICR.listarDatosRuta((String) listaRutas.getSelectedItem());
						textField_2.setText(ruta.getNombre());
						textField_3.setText(ruta.getHora().toString());
						textField_7.setText(ruta.getFechaAlta().toString());
						textField_5.setText(String.valueOf(ruta.getTuristaBase()));
						textField_4.setText(String.valueOf(ruta.getEjecutivoBase()));
						textField_6.setText(String.valueOf(ruta.getUnidadEquipajeExtra()));
						txtEstado.setText(ruta.getEstado().toString());
						textField_1.setText(ICR.getOrigenRuta(ruta.getNombre()).getNombre() + ", " + ICR.getOrigenRuta(ruta.getNombre()).getPais());
						textField.setText(ICR.getDestinoRuta(ruta.getNombre()).getNombre() + ", " + ICR.getDestinoRuta(ruta.getNombre()).getPais());
						JTextArea descripcionRuta = nuevoTextRuta(ruta.getDescripcion());
						JTextArea descripcionRutaCorta = nuevoTextRuta(ruta.getDescCorta());
						scrollPaneTextoRutaCorta.setViewportView(descripcionRutaCorta);
						scrollPaneTextoRuta.setViewportView(descripcionRuta);
						Set<String> vuelos = ICR.vuelosDeRuta(ruta.getNombre());
						for (String v : vuelos) {
							listaVuelos.addItem(v);
						}
						setVisible(false);
						setVisible(true);
						
				}
				
				}
			}
		});
		
		JLabel lblRutaDeVuelo = new JLabel("Elegir una Ruta de Vuelo:");
		
		JLabel lblConstrasenha_1 = new JLabel("Contraseña:");
		
		textContrasenhaA = new JTextField();
		textContrasenhaA.setEditable(false);
		textContrasenhaA.setColumns(10);
		textContrasenhaA.setBorder(null);
		textContrasenhaA.setBackground(new Color(206, 206, 206));
		textContrasenhaA.setAlignmentX(0.0f);
		GroupLayout gl_panelAerolinea = new GroupLayout(panelAerolinea);
		gl_panelAerolinea.setHorizontalGroup(
			gl_panelAerolinea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAerolinea.createSequentialGroup()
					.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAerolinea.createSequentialGroup()
							.addGap(108)
							.addComponent(lblDatosDeAero, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelAerolinea.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelAerolinea.createSequentialGroup()
									.addComponent(lblNickname_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNicknameA, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelAerolinea.createSequentialGroup()
									.addComponent(lblNombre_1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNombreA, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelAerolinea.createSequentialGroup()
									.addComponent(lblConstrasenha_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textContrasenhaA, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
							.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRutaDeVuelo, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addComponent(listaRutas, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))))
					.addGap(22))
				.addGroup(gl_panelAerolinea.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWeb_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtWeb, 253, 253, 253)
					.addGap(194))
				.addGroup(gl_panelAerolinea.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEmail_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtEmailA, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(194, Short.MAX_VALUE))
				.addGroup(gl_panelAerolinea.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcion_1)
						.addComponent(scrollPaneTextoAero, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(177, Short.MAX_VALUE))
				.addGroup(gl_panelAerolinea.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		gl_panelAerolinea.setVerticalGroup(
			gl_panelAerolinea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAerolinea.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatosDeAero, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAerolinea.createSequentialGroup()
							.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNickname_1)
								.addComponent(txtNicknameA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombre_1)
								.addComponent(txtNombreA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.LEADING)
								.addComponent(lblConstrasenha_1)
								.addComponent(textContrasenhaA, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelAerolinea.createSequentialGroup()
							.addComponent(lblRutaDeVuelo)
							.addGap(11)
							.addComponent(listaRutas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWeb_1)
						.addComponent(txtWeb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail_1)
						.addComponent(txtEmailA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDescripcion_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTextoAero, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		panelAerolinea.setLayout(gl_panelAerolinea);
		
		JLabel lblNickname = new JLabel("Nickname:");
		
		txtNicknameC = new JTextField();
		txtNicknameC.setBorder(null);
		txtNicknameC.setEditable(false);
		txtNicknameC.setBackground(new Color(206, 206, 206));
		txtNicknameC.setColumns(10);
		
		JLabel lblNombreC = new JLabel("Nombre:");
		
		JLabel lblEmail = new JLabel("Email:");
		
		JLabel lblApellido = new JLabel("Apellido:");
		
		JLabel lblFechaNac = new JLabel("Fecha de Nacimiento:");
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		
		JLabel lblTipoDoc = new JLabel("Tipo de Documento:");
		
		JLabel lblNumDoc = new JLabel("Numero de Documento:");
		
		txtNombreC = new JTextField();
		txtNombreC.setBorder(null);
		txtNombreC.setEditable(false);
		txtNombreC.setColumns(10);
		txtNombreC.setBackground(new Color(206, 206, 206));
		
		txtApellido = new JTextField();
		txtApellido.setBorder(null);
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBackground(new Color(206, 206, 206));
		
		txtEmailC = new JTextField();
		txtEmailC.setBorder(null);
		txtEmailC.setEditable(false);
		txtEmailC.setColumns(10);
		txtEmailC.setBackground(new Color(206, 206, 206));
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.setBorder(null);
		txtNacionalidad.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtNacionalidad.setEditable(false);
		txtNacionalidad.setColumns(10);
		txtNacionalidad.setBackground(new Color(206, 206, 206));
		
		txtTipoDoc = new JTextField();
		txtTipoDoc.setBorder(null);
		txtTipoDoc.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtTipoDoc.setEditable(false);
		txtTipoDoc.setColumns(10);
		txtTipoDoc.setBackground(new Color(206, 206, 206));
		
		txtNumDoc = new JTextField();
		txtNumDoc.setBorder(null);
		txtNumDoc.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtNumDoc.setEditable(false);
		txtNumDoc.setColumns(10);
		txtNumDoc.setBackground(new Color(206, 206, 206));
		
		txtFechaNac = new JTextField();
		txtFechaNac.setBorder(null);
		txtFechaNac.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtFechaNac.setEditable(false);
		txtFechaNac.setColumns(10);
		txtFechaNac.setBackground(new Color(206, 206, 206));
		
		JLabel lblDatosDelCliente = new JLabel("DATOS DEL CLIENTE");
		lblDatosDelCliente.setFont(new Font("Sylfaen", Font.BOLD, 12));
		
		JPanel panelReservas = new JPanel();
		panelReservas.setBackground(new Color(197, 197, 197));
		
		JLabel lblNewLabel_5_1 = new JLabel("Costo:");
		
		JLabel lblNewLabel_4_1 = new JLabel("Cantidad De Equipaje Extra:");
		
		JLabel lblNewLabel_3_1 = new JLabel("Cantidad De Pasajes:");
		
		JLabel lblNewLabel_2_1 = new JLabel("Tipo De Asiento:");
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha De Reserva:");
		
		JLabel txtReserva = new JLabel("Vuelos Reservados:");
		
		
		
		JButton btnVuelo = new JButton("Mostrar Info Del Vuelo Reservado");
		btnVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (reservasUsuario.size()!=0) {
					panelVuelo.setVisible(true);
					Set<String> reservasAsociadas = ICV.listarReservasAsociadasVuelo(reservasUsuario.get(contadorReservas).getVuelo());
					List<String> myList = new ArrayList<>(reservasAsociadas.size());
					for (String reserva : reservasAsociadas) {
						   myList.add(reserva);
						}
					JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
					scrollPaneTexto.setViewportView(list);
					DataVuelo vuelo = ICV.listarDatosVuelo(reservasUsuario.get(contadorReservas).getVuelo());
					txtNombreVuelo.setText(vuelo.getNombre());
					txtFechaVuelo.setText(vuelo.getFecha().toString());
					txtDuracionVuelo.setText(vuelo.getDuracion().toString());
					txtAsTurVuelo.setText(String.valueOf(vuelo.getAsientosTurista()));
					txtAsEjVuelo.setText(String.valueOf(vuelo.getAsientosEjecutivo()));
					txtFechaAltaVuelo.setText(vuelo.getFechaAlta().toString());
					setVisible(false);
					setVisible(true);
				}
				
			}
		});
		JButton btnPaquete = new JButton("Mostrar Info Del Paquete Comprado");
		btnPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paquetesUsuario.size()!=0) {
					panelPaquete.setVisible(true);
					panelRutaAsociada.setVisible(false);
					panelVuelo.setVisible(false);
					DataPaquete paquete = ICV.getDataPaquete(paquetesUsuario.get(contadorPaquetes).getPaquete());
					textField_10.setText(paquete.getNombre());
					textField_11.setText(String.valueOf(paquete.getCantRutas()));
					textField_15.setText(paquete.getFechaAlta().toString());
					textField_13.setText(paquete.getDiasValidez().toString());
					textField_12.setText((String.valueOf(paquete.getDescuento()))); 
					textField_14.setText((String.valueOf(paquete.getCosto())));
					JTextArea descripcionPaquete = nuevoTextPaquete(paquete.getDescripcion());
					scrollPaneDescripcionPaquete.setViewportView(descripcionPaquete);
					contadorRutas = 0;
					Set<DataPaqueteRuta> DTpr = ICV.getDataPaqueteRuta(paquete.getNombre());
					rutasUsuario.clear();
					for (DataPaqueteRuta dp : DTpr) {
						rutasUsuario.add(dp);
					}
					if (!DTpr.isEmpty()) {
						txtContadorRutas.setText("1/" + DTpr.size());
						actualizarRuta(0);
					}
					else txtContadorRutas.setText("0/0");
					panelPaquete.setVisible(true);
					setVisible(false);
					setVisible(true);
					/*Set<String> reservasAsociadas = ICV.listarReservasAsociadasVuelo(reservasUsuario.get(contadorReservas).getVuelo());
					List<String> myList = new ArrayList<>(reservasAsociadas.size());
					for (String reserva : reservasAsociadas) {
						   myList.add(reserva);
						}
					JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
					scrollPaneTexto.setViewportView(list);
					DataVuelo vuelo = ICV.listarDatosVuelo(reservasUsuario.get(contadorReservas).getVuelo());
					txtNombreVuelo.setText(vuelo.getNombre());
					txtFechaVuelo.setText(vuelo.getFecha().toString());
					txtDuracionVuelo.setText(vuelo.getDuracion().toString());
					txtAsTurVuelo.setText(String.valueOf(vuelo.getAsientosTurista()));
					txtAsEjVuelo.setText(String.valueOf(vuelo.getAsientosEjecutivo()));
					txtFechaAltaVuelo.setText(vuelo.getFechaAlta().toString());*/
				}
			}
		});
		
		txtFReservaVuelo = new JTextField();
		txtFReservaVuelo.setBorder(null);
		txtFReservaVuelo.setEditable(false);
		txtFReservaVuelo.setColumns(10);
		txtFReservaVuelo.setBackground(new Color(197, 197, 197));
		
		txtTipoAsiento = new JTextField();
		txtTipoAsiento.setBorder(null);
		txtTipoAsiento.setEditable(false);
		txtTipoAsiento.setColumns(10);
		txtTipoAsiento.setBackground(new Color(197, 197, 197));
		
		txtCantPasajes = new JTextField();
		txtCantPasajes.setBorder(null);
		txtCantPasajes.setEditable(false);
		txtCantPasajes.setColumns(10);
		txtCantPasajes.setBackground(new Color(197, 197, 197));
		
		txtCantEqExtra = new JTextField();
		txtCantEqExtra.setBorder(null);
		txtCantEqExtra.setEditable(false);
		txtCantEqExtra.setColumns(10);
		txtCantEqExtra.setBackground(new Color(197, 197, 197));
		
		txtCostoReserva = new JTextField();
		txtCostoReserva.setBorder(null);
		txtCostoReserva.setEditable(false);
		txtCostoReserva.setColumns(10);
		txtCostoReserva.setBackground(new Color(197, 197, 197));
		
		
		txtContadorReservas.setEditable(false);
		txtContadorReservas.setColumns(10);
		txtContadorReservas.setBackground(new Color(197, 197, 197));
		
		JButton izquierdaReserva = new JButton("<");
		izquierdaReserva.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		izquierdaReserva.setBackground(new Color(206, 206, 206));
		izquierdaReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (reservasUsuario.size()!=0) {
					panelVuelo.setVisible(false);
					setVisible(false);
					setVisible(true);
					if (contadorReservas > 0) contadorReservas --;
					else contadorReservas=reservasUsuario.size()-1;
					txtContadorReservas.setText((contadorReservas+1)+ "/"+ reservasUsuario.size());
					txtContadorReservas.setVisible(false);
					txtContadorReservas.setVisible(true);
					actualizarReserva(contadorReservas, scrollPanePasajes);
				}
				
			}
		});
		JButton derechaReserva = new JButton(">");
		derechaReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (reservasUsuario.size()!=0) {
					panelVuelo.setVisible(false);
					setVisible(false);
					setVisible(true);
					if (contadorReservas < (reservasUsuario.size()-1)) contadorReservas ++;
					else contadorReservas=0;
					txtContadorReservas.setText((contadorReservas+1)+ "/"+ reservasUsuario.size());
					txtContadorReservas.setVisible(false);
					txtContadorReservas.setVisible(true);
					actualizarReserva(contadorReservas, scrollPanePasajes);
				}
				
			}
		});
		JButton izquierdaPaquetes = new JButton("<");
		izquierdaPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paquetesUsuario.size()!=0) {
					panelVuelo.setVisible(false);
					panelPaquete.setVisible(false);
					panelRutaAsociada.setVisible(false);
					setVisible(false);
					setVisible(true);
					if (contadorPaquetes > 0) contadorPaquetes --;
					else contadorPaquetes=paquetesUsuario.size()-1;
					txtContadorPaquetes.setText((contadorPaquetes+1)+ "/"+ paquetesUsuario.size());
					txtContadorPaquetes.setVisible(false);
					txtContadorPaquetes.setVisible(true);
					actualizarPaquete(contadorPaquetes);
				}
				
			}
		});
		
		JButton derechaPaquetes = new JButton(">");
		derechaPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paquetesUsuario.size()!=0) {
					panelVuelo.setVisible(false);
					panelPaquete.setVisible(false);
					panelRutaAsociada.setVisible(false);
					setVisible(false);
					setVisible(true);
					if (contadorPaquetes < (paquetesUsuario.size()-1)) contadorPaquetes ++;
					else contadorPaquetes=0;
					txtContadorPaquetes.setText((contadorPaquetes+1)+ "/"+ paquetesUsuario.size());
					txtContadorPaquetes.setVisible(false);
					txtContadorPaquetes.setVisible(true);
					actualizarPaquete(contadorPaquetes);
				}
				
			}
		});
		
		
		izquierdaPaquetes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rutasUsuario.size()!=0) {
					panelVuelo.setVisible(false);
					panelRutaAsociada.setVisible(false);
					setVisible(false);
					setVisible(true);
					if (contadorRutas > 0) contadorRutas --;
					else contadorRutas=rutasUsuario.size()-1;
					txtContadorRutas.setText((contadorRutas+1)+ "/"+ rutasUsuario.size());
					txtContadorRutas.setVisible(false);
					txtContadorRutas.setVisible(true);
					actualizarRuta(contadorRutas);
				}
				
			}
		});
		derechaReserva.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		derechaReserva.setBackground(new Color(206, 206, 206));
		
		derechaPaquetes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rutasUsuario.size()!=0) {
					panelVuelo.setVisible(false);
					panelRutaAsociada.setVisible(false);
					setVisible(false);
					setVisible(true);
					if (contadorRutas < (rutasUsuario.size()-1)) contadorRutas ++;
					else contadorRutas=0;
					txtContadorRutas.setText((contadorRutas+1)+ "/"+ rutasUsuario.size());
					txtContadorRutas.setVisible(false);
					txtContadorRutas.setVisible(true);
					actualizarRuta(contadorRutas);
				}
				
			}
		});
		derechaReserva.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		derechaReserva.setBackground(new Color(206, 206, 206));
		
		JLabel txtPasajes = new JLabel("Pasajes:");
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Vuelo:");
		
		txtNomVueRes = new JTextField();
		txtNomVueRes.setEditable(false);
		txtNomVueRes.setColumns(10);
		txtNomVueRes.setBorder(null);
		txtNomVueRes.setBackground(new Color(197, 197, 197));
		
		
		GroupLayout gl_panelReservas = new GroupLayout(panelReservas);
		gl_panelReservas.setHorizontalGroup(
			gl_panelReservas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelReservas.createSequentialGroup()
					.addGap(38)
					.addComponent(izquierdaReserva, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtContadorReservas, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(derechaReserva, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(55))
				.addGroup(gl_panelReservas.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(txtNomVueRes, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
				.addGroup(gl_panelReservas.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelReservas.createParallelGroup(Alignment.LEADING)
						.addComponent(txtPasajes, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelReservas.createSequentialGroup()
							.addComponent(lblNewLabel_5_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCostoReserva, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelReservas.createSequentialGroup()
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFReservaVuelo, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelReservas.createSequentialGroup()
							.addComponent(lblNewLabel_4_1, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCantEqExtra, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelReservas.createSequentialGroup()
							.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCantPasajes, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelReservas.createSequentialGroup()
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTipoAsiento, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
					.addGap(22))
				.addGroup(gl_panelReservas.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPanePasajes, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
				.addGroup(gl_panelReservas.createSequentialGroup()
					.addGap(65)
					.addComponent(txtReserva, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
				.addGroup(gl_panelReservas.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVuelo)
					.addGap(22))
		);
		gl_panelReservas.setVerticalGroup(
			gl_panelReservas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelReservas.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtReserva)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelReservas.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtContadorReservas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(derechaReserva)
						.addComponent(izquierdaReserva))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelReservas.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNomVueRes, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelReservas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1)
						.addComponent(txtFReservaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelReservas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1)
						.addComponent(txtTipoAsiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panelReservas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3_1)
						.addComponent(txtCantPasajes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelReservas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4_1)
						.addComponent(txtCantEqExtra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelReservas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5_1)
						.addComponent(txtCostoReserva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(txtPasajes)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPanePasajes, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnVuelo)
					.addGap(22))
		);
		panelReservas.setLayout(gl_panelReservas);
		
		JPanel panelReservas_1 = new JPanel();
		panelReservas_1.setBackground(new Color(197, 197, 197));
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Costo:");
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Vencimiento:");
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha De Compra:");
		
		JLabel txtPaquetes = new JLabel("Paquetes Comprados:");
		
	
		
		
		
		txtFCompra = new JTextField();
		txtFCompra.setBorder(null);
		txtFCompra.setEditable(false);
		txtFCompra.setColumns(10);
		txtFCompra.setBackground(new Color(197, 197, 197));
		
		txtVencimiento = new JTextField();
		txtVencimiento.setBorder(null);
		txtVencimiento.setEditable(false);
		txtVencimiento.setColumns(10);
		txtVencimiento.setBackground(new Color(197, 197, 197));
		
		txtCostoPaquete = new JTextField();
		txtCostoPaquete.setBorder(null);
		txtCostoPaquete.setEditable(false);
		txtCostoPaquete.setColumns(10);
		txtCostoPaquete.setBackground(new Color(197, 197, 197));
		
		
		
		izquierdaPaquetes.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		izquierdaPaquetes.setBackground(new Color(206, 206, 206));
		
		
		derechaPaquetes.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		derechaPaquetes.setBackground(new Color(206, 206, 206));
		
		txtContadorPaquetes = new JTextField("");
		txtContadorPaquetes.setHorizontalAlignment(SwingConstants.CENTER);
		txtContadorPaquetes.setEditable(false);
		txtContadorPaquetes.setColumns(10);
		txtContadorPaquetes.setBorder(null);
		txtContadorPaquetes.setBackground(new Color(197, 197, 197));
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Paquete:");
		
		txtNombrePaqueteComp = new JTextField();
		txtNombrePaqueteComp.setBorder(null);
		txtNombrePaqueteComp.setEditable(false);
		txtNombrePaqueteComp.setColumns(10);
		txtNombrePaqueteComp.setBackground(new Color(197, 197, 197));
		GroupLayout gl_panelReservas_1 = new GroupLayout(panelReservas_1);
		gl_panelReservas_1.setHorizontalGroup(
			gl_panelReservas_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelReservas_1.createSequentialGroup()
					.addGroup(gl_panelReservas_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelReservas_1.createSequentialGroup()
							.addGap(54)
							.addComponent(izquierdaPaquetes, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtContadorPaquetes, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(derechaPaquetes, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelReservas_1.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_panelReservas_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelReservas_1.createSequentialGroup()
									.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNombrePaqueteComp, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnPaquete)
								.addGroup(gl_panelReservas_1.createSequentialGroup()
									.addComponent(lblNewLabel_5_1_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCostoPaquete, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelReservas_1.createSequentialGroup()
									.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtFCompra, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelReservas_1.createSequentialGroup()
									.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtVencimiento, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panelReservas_1.createSequentialGroup()
							.addGap(77)
							.addComponent(txtPaquetes)))
					.addGap(22))
		);
		gl_panelReservas_1.setVerticalGroup(
			gl_panelReservas_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelReservas_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtPaquetes)
					.addGap(11)
					.addGroup(gl_panelReservas_1.createParallelGroup(Alignment.LEADING)
						.addComponent(izquierdaPaquetes, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtContadorPaquetes, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(derechaPaquetes, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_panelReservas_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1)
						.addComponent(txtNombrePaqueteComp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelReservas_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1)
						.addComponent(txtFCompra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelReservas_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1_1)
						.addComponent(txtVencimiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelReservas_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5_1_1)
						.addComponent(txtCostoPaquete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(btnPaquete)
					.addGap(22))
		);
		panelReservas_1.setLayout(gl_panelReservas_1);
		
		JLabel lblContrasenha = new JLabel("Contraseña:");
		
		txtContrasenhaC = new JTextField();
		txtContrasenhaC.setEditable(false);
		txtContrasenhaC.setColumns(10);
		txtContrasenhaC.setBorder(null);
		txtContrasenhaC.setBackground(new Color(206, 206, 206));
		GroupLayout gl_panelCliente = new GroupLayout(panelCliente);
		gl_panelCliente.setHorizontalGroup(
			gl_panelCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCliente.createSequentialGroup()
					.addGroup(gl_panelCliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCliente.createSequentialGroup()
							.addGap(201)
							.addComponent(lblDatosDelCliente, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCliente.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelReservas, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panelReservas_1, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCliente.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelCliente.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, gl_panelCliente.createSequentialGroup()
									.addComponent(lblContrasenha, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtContrasenhaC))
								.addGroup(Alignment.LEADING, gl_panelCliente.createSequentialGroup()
									.addComponent(lblNickname)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtNicknameC, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panelCliente.createSequentialGroup()
									.addComponent(lblNombreC, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNombreC, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panelCliente.createSequentialGroup()
									.addComponent(lblApellido)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panelCliente.createSequentialGroup()
									.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtEmailC, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)))
							.addGap(32)
							.addGroup(gl_panelCliente.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCliente.createSequentialGroup()
									.addComponent(lblNacionalidad)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNacionalidad, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelCliente.createSequentialGroup()
									.addComponent(lblTipoDoc)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtTipoDoc, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelCliente.createSequentialGroup()
									.addComponent(lblFechaNac)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtFechaNac, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelCliente.createSequentialGroup()
									.addComponent(lblNumDoc)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNumDoc, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_panelCliente.setVerticalGroup(
			gl_panelCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCliente.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatosDelCliente, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCliente.createSequentialGroup()
							.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNickname)
								.addComponent(txtNicknameC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombreC)
								.addComponent(txtNombreC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblApellido)
								.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail)
								.addComponent(txtEmailC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelCliente.createSequentialGroup()
							.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNacionalidad)
								.addComponent(txtNacionalidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipoDoc)
								.addComponent(txtTipoDoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumDoc)
								.addComponent(txtNumDoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFechaNac)
								.addComponent(txtFechaNac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCliente.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContrasenha)
						.addComponent(txtContrasenhaC, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCliente.createParallelGroup(Alignment.LEADING)
						.addComponent(panelReservas_1, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelReservas, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		panelCliente.setLayout(gl_panelCliente);
		getContentPane().setLayout(groupLayout);
		panelCliente.setVisible(false);
		panelAerolinea.setVisible(false);
		//scrollPaneTextoAero.setViewportView(descripcionAero);
		scrollPanePasajes.setViewportView(listPasajes);
		
	}

	
	private JTextArea nuevoTextAero(String texto) {
		descripcionAero = new JTextArea(texto);
		descripcionAero.setEditable(false);
		descripcionAero.setWrapStyleWord(true);
		descripcionAero.setLineWrap(true);
		return descripcionAero;
	}
	private JTextArea nuevoTextRuta(String texto) {
		descripcionRuta = new JTextArea(texto);
		descripcionRuta.setEditable(false);
		descripcionRuta.setWrapStyleWord(true);
		descripcionRuta.setLineWrap(true);
		return descripcionRuta;
	}
	private JTextArea nuevoTextPaquete(String texto) {
		descripcionPaquete = new JTextArea(texto);
		descripcionPaquete.setEditable(false);
		descripcionPaquete.setWrapStyleWord(true);
		descripcionPaquete.setLineWrap(true);
		return descripcionPaquete;
	}
	private JTextArea nuevoTextRuta2(String texto) {
		descripcionRuta2 = new JTextArea(texto);
		descripcionRuta2.setEditable(false);
		descripcionRuta2.setWrapStyleWord(true);
		descripcionRuta2.setLineWrap(true);
		return descripcionRuta2;
	}
	private JTextArea nuevoTextRuta2_2(String texto) {
		descripcionRuta2_2 = new JTextArea(texto);
		descripcionRuta2_2.setEditable(false);
		descripcionRuta2_2.setWrapStyleWord(true);
		descripcionRuta2_2.setLineWrap(true);
		return descripcionRuta2_2;
	}
	private void actualizarReserva(int i, JScrollPane scrollPanePasajes) {
		txtFReservaVuelo.setText(reservasUsuario.get(i).getFechaReserva().toString());
		txtTipoAsiento.setText(reservasUsuario.get(i).getTipoAsiento().toString());
		txtCantPasajes.setText(String.valueOf(reservasUsuario.get(i).getCantPasajes()));
		txtCantEqExtra.setText(String.valueOf(reservasUsuario.get(i).getCantEquipajeExtra()));
		txtCostoReserva.setText(String.valueOf(reservasUsuario.get(i).getCosto()));
		txtNomVueRes.setText(reservasUsuario.get(i).getVuelo());
		Set<DataPasaje> pasajes = reservasUsuario.get(i).getPasajes();
		Set<String> nombres = new HashSet<>();
		for (DataPasaje p : pasajes) {
			nombres.add(p.getNombre()+" "+p.getApellido());
		}
		List<String> myList = new ArrayList<>(nombres.size());
		JList<String> listPasajes = new JList<String>(nombres.toArray(new String[nombres.size()]));
		scrollPanePasajes.setViewportView(listPasajes);
				
	}

	private void clearCliente(JScrollPane scrollPanePasajes) {
		txtFReservaVuelo.setText("");
		txtTipoAsiento.setText("");
		txtCantPasajes.setText("");
		txtCantEqExtra.setText("");
		txtCostoReserva.setText("");
		txtNomVueRes.setText("");
		txtNombrePaqueteComp.setText("");
		txtFCompra.setText("");
		txtVencimiento.setText("");
		txtCostoPaquete.setText("");
		JList<String> listPasajes = new JList<String>();
		scrollPanePasajes.setViewportView(listPasajes);
				
	}
	private void clearRutas(JScrollPane scrollPaneTextoRuta, JComboBox listaVuelos, JScrollPane scrollPaneTextoRutaCorta) {
		textField_2.setText("");
		textField_3.setText("");
		textField_7.setText("");
		textField_5.setText("");
		textField_4.setText("");
		textField_6.setText("");
		textField_1.setText("");
		txtEstado.setText("");
		textField.setText("");
		JTextArea descripcionRuta = nuevoTextRuta("");
		scrollPaneTextoRuta.setViewportView(descripcionRuta);
		JTextArea descripcionRutaCorta = nuevoTextRuta("");
		scrollPaneTextoRuta.setViewportView(descripcionRutaCorta);
		listaVuelos.removeAllItems();
	}
	
	private void actualizarPaquete(int i) {
		txtNombrePaqueteComp.setText(paquetesUsuario.get(i).getPaquete());
		txtFCompra.setText(paquetesUsuario.get(i).getFecha().toString());
		txtVencimiento.setText(paquetesUsuario.get(i).getVencimiento().toString());
		txtCostoPaquete.setText(String.valueOf(paquetesUsuario.get(i).getCosto()));
	}
	private void actualizarRuta(int i) {
		textField_9.setText(rutasUsuario.get(i).getRuta());
		textField_16.setText(rutasUsuario.get(i).getAsiento().toString());
		textField_17.setText(String.valueOf(rutasUsuario.get(i).getCantidad()));
	
	}
	public static void main(String[] args) {
		
	}
}
