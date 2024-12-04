package presentacion;

import javax.swing.JInternalFrame;
import logica.*;
/*import logica.datatype.*;
import logica.entidad.*;
import logica.controlador.*;*/
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import javax.swing.border.LineBorder;


public class ConsultarPaqueteDeRutas extends JInternalFrame{
	public ConsultarPaqueteDeRutas(IControladorUsuarios ICU ,IControladorRutas ICR, IControladorVuelos ICV) {
		setTitle("Consulta De Paquete De Rutas");
		setClosable(true);
		JLabel lblElegirPaquetes = new JLabel("Seleccionar un Paquete de Rutas:");
		
		
		//paneles
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JPanel panelVerRutas = new JPanel();
		panelVerRutas.setVisible(false);
		panelVerRutas.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panelVerVuelos = new JPanel();
		panelVerVuelos.setVisible(false);
		panelVerVuelos.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		JComboBox listaPaquetes = new JComboBox();
		Set<String> paquetesRegistrados = ICV.listasPaquetesRegistrados();
        
        for (String paquete : paquetesRegistrados) {
            listaPaquetes.addItem(paquete);
        }
        
        
        JComboBox ListaNombreRutas = new JComboBox();
        JTextArea textDescripcion = new JTextArea(5,20);
        textDescripcion.setLineWrap(true); 
        textDescripcion.setWrapStyleWord(true);
		textDescripcion.setEditable(false);
        
        listaPaquetes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String paqueteSeleccionado = (String) listaPaquetes.getSelectedItem();
                if (paqueteSeleccionado != null) {
                	limpiarRuta();
                	limpiarVuelo();
                	panelVerRutas.setVisible(false);
                	panelVerVuelos.setVisible(false);
                	
                	ParPaqueteRutas paquete = ICV.mostrarInfoPaquete(paqueteSeleccionado);
                     
                     textNombrePaquete.setText(paquete.getPaquete().getNombre());
                     textFechaAlta.setText(paquete.getPaquete().getFechaAlta().toString());
                     textValidez.setText(Integer.toString(paquete.getPaquete().getDiasValidez()));
                     textCantRutas.setText(Integer.toString(paquete.getPaquete().getCantRutas()));
                     textCosto.setText(Double.toString(paquete.getPaquete().getCostoDesc()));
                     textDescuento.setText(Double.toString(paquete.getPaquete().getDescuento()));
                     textCantEjec.setText(Integer.toString(paquete.getCantEjecutivo()));
                     textCantTurista.setText(Integer.toString(paquete.getCantTurista()));

                     ListaNombreRutas.removeAllItems();
                     for (String ruta : paquete.getNombImgRutas().keySet()) {
                    	 ListaNombreRutas.addItem(ruta); 
                     }
                     textDescripcion.setText(paquete.getPaquete().getDescripcion());
                     pack();
                }else {
                	JOptionPane.showMessageDialog(null, "Debes seleccionar un paquete para ver sus datos");
                }
            }
        });
        
        
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblElegirPaquetes)
						.addComponent(listaPaquetes, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 609, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panelVerRutas, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(panelVerVuelos, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblElegirPaquetes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(listaPaquetes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelVerVuelos, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelVerRutas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
					.addGap(20))
		);
		
		JLabel lblDatosVuelo = new JLabel("Datos del Vuelo");
		lblDatosVuelo.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		
		JLabel lblNombreVuelo = new JLabel("Nombre:");
		
		textNombreVuelo = new JTextField();
		textNombreVuelo.setEditable(false);
		textNombreVuelo.setColumns(10);
		
		JLabel lblFechaVuelo = new JLabel("Fecha:");
		
		textFechaVuelo = new JTextField();
		textFechaVuelo.setEditable(false);
		textFechaVuelo.setColumns(10);
		
		JLabel lblDuracionVuelo = new JLabel("Duración:");
		
		textDuracionVuelo = new JTextField();
		textDuracionVuelo.setEditable(false);
		textDuracionVuelo.setColumns(10);
		
		JLabel lblFechaAltaVuelo = new JLabel("Fecha alta:");
		
		textFechaAltaVuelo = new JTextField();
		textFechaAltaVuelo.setEditable(false);
		textFechaAltaVuelo.setColumns(10);
		
		JLabel lblAsientosTurista = new JLabel("Cant. asientos tipo turista:");
		
		textTuristaVuelo = new JTextField();
		textTuristaVuelo.setEditable(false);
		textTuristaVuelo.setColumns(10);
		
		JLabel lblAsientosEjecutivo = new JLabel("Cant. asientos tipo ejecutivo:");
		
		textEjecutivoVuelo = new JTextField();
		textEjecutivoVuelo.setEditable(false);
		textEjecutivoVuelo.setColumns(10);
		
		JLabel lblReservasAsociadas = new JLabel("Reservas asociadas al vuelo:");
		
		JScrollPane Reservas = new JScrollPane();
		JTextArea textReservas = new JTextArea(15,20);
		Reservas.setViewportView(textReservas);
		textReservas.setLineWrap(true); 
		textReservas.setWrapStyleWord(true);
		textReservas.setEditable(false);
		
		GroupLayout gl_panelVerVuelos = new GroupLayout(panelVerVuelos);
		gl_panelVerVuelos.setHorizontalGroup(
			gl_panelVerVuelos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelVerVuelos.createSequentialGroup()
					.addGroup(gl_panelVerVuelos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelVerVuelos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNombreVuelo, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textNombreVuelo, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
						.addGroup(gl_panelVerVuelos.createSequentialGroup()
							.addGap(42)
							.addComponent(lblDatosVuelo, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelVerVuelos.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelVerVuelos.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelVerVuelos.createSequentialGroup()
									.addComponent(lblFechaVuelo, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFechaVuelo, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelVerVuelos.createSequentialGroup()
									.addComponent(lblDuracionVuelo, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelVerVuelos.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelVerVuelos.createSequentialGroup()
											.addGap(10)
											.addComponent(textFechaAltaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(textDuracionVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_panelVerVuelos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFechaAltaVuelo, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelVerVuelos.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelVerVuelos.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblAsientosTurista, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblAsientosEjecutivo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelVerVuelos.createParallelGroup(Alignment.TRAILING)
								.addComponent(textTuristaVuelo, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelVerVuelos.createSequentialGroup()
									.addComponent(textEjecutivoVuelo, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(gl_panelVerVuelos.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelVerVuelos.createParallelGroup(Alignment.LEADING)
								.addComponent(lblReservasAsociadas, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
								.addComponent(Reservas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(20))
		);
		gl_panelVerVuelos.setVerticalGroup(
			gl_panelVerVuelos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelVerVuelos.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatosVuelo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelVerVuelos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreVuelo)
						.addComponent(textNombreVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelVerVuelos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaVuelo)
						.addComponent(textFechaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelVerVuelos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDuracionVuelo)
						.addComponent(textDuracionVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelVerVuelos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaAltaVuelo)
						.addComponent(textFechaAltaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelVerVuelos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAsientosTurista)
						.addComponent(textTuristaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelVerVuelos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAsientosEjecutivo)
						.addComponent(textEjecutivoVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblReservasAsociadas)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Reservas, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(166))
		);
		
		
		panelVerVuelos.setLayout(gl_panelVerVuelos);
		
		JLabel lblVerRuta = new JLabel("Datos de la Ruta");
		lblVerRuta.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		
		JLabel lblNombreRuta = new JLabel("Nombre:");
		
		textNombreRuta = new JTextField();
		textNombreRuta.setEditable(false);
		textNombreRuta.setColumns(10);
		
		JLabel lblHoraRuta = new JLabel("Hora:");
		
		textHoraRuta = new JTextField();
		textHoraRuta.setEditable(false);
		textHoraRuta.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
        
        textEstado = new JTextField();
        textEstado.setEditable(false);
        textEstado.setColumns(10);
        
        
		
		JLabel lblFechaAltaRuta = new JLabel("Fecha de alta:");
		
		textFechaAltaRuta = new JTextField();
		textFechaAltaRuta.setEditable(false);
		textFechaAltaRuta.setColumns(10);
		
		JLabel lblCostoTurista = new JLabel("Costo base asiento turista:");
		
		textCostoTurista = new JTextField();
		textCostoTurista.setEditable(false);
		textCostoTurista.setColumns(10);
		
		JLabel lblCostoEjecutivo = new JLabel("Costo base asiento ejecutivo:");
		
		textCostoEjecutivo = new JTextField();
		textCostoEjecutivo.setEditable(false);
		textCostoEjecutivo.setColumns(10);
		
		JLabel lblCostoEquiExtra = new JLabel("Costo unidad de equipaje extra:");
		
		textEquiExtra = new JTextField();
		textEquiExtra.setEditable(false);
		textEquiExtra.setColumns(10);
		
		JLabel lblDescripcionRuta = new JLabel("Descripción:");
		
		JScrollPane scrollDescripcionRuta = new JScrollPane();
		
		JScrollPane scrollDescripcionRutaCorta = new JScrollPane();
		
		JLabel lblNombreVuelos = new JLabel("Nombre vuelos asociados:");
		
		JComboBox ListaVuelos = new JComboBox();
		
		JLabel lblVerVuelos = new JLabel("Para ver la información del vuelo seleccionado:");
		
		JTextArea textDescripcionRuta = new JTextArea(5,20);
		textDescripcionRuta.setLineWrap(true); 
		textDescripcionRuta.setWrapStyleWord(true);
		textDescripcionRuta.setEditable(false);
		
		JTextArea textDescripcionRutaCorta = new JTextArea(5,20);
		textDescripcionRutaCorta.setLineWrap(true); 
		textDescripcionRutaCorta.setWrapStyleWord(true);
		textDescripcionRutaCorta.setEditable(false);
		
		scrollDescripcionRuta.setViewportView(textDescripcionRuta);
		scrollDescripcionRutaCorta.setViewportView(textDescripcionRutaCorta);
		
		JButton btnVerDatosVuelo = new JButton("Ver datos");
		btnVerDatosVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String vuelo = (String) ListaVuelos.getSelectedItem();
            	limpiarVuelo();
            	if(vuelo != null) {
            		panelVerVuelos.setVisible(true);
            		DataVuelo datosVuelo = ICV.listarDatosVuelo(vuelo);
            		textNombreVuelo.setText(datosVuelo.getNombre());
            		textFechaVuelo.setText(datosVuelo.getFecha().toString());
            		textDuracionVuelo.setText(datosVuelo.getDuracion().toString());
            		textFechaAltaVuelo.setText(datosVuelo.getFechaAlta().toString());
            		textTuristaVuelo.setText(datosVuelo.getAsientosTurista().toString());
            		textEjecutivoVuelo.setText(datosVuelo.getAsientosEjecutivo().toString());
            		textReservas.setText("");
            		Set<String> reservasVuelo = ICV.listarReservasAsociadasVuelo(vuelo);
	            	if(!reservasVuelo.isEmpty()) {
            			String reservasString = "";
	            		for (String reserva : reservasVuelo) {
	            			reservasString = reservasString + reserva + "\n";
	            		}
	            		textReservas.setText(reservasString);
	            	}
	            	pack();
	            }else {
	            	JOptionPane.showMessageDialog(null, "Debes seleccionar un vuelo para ver sus datos");
	            }
	         }
            
            });
		
		JLabel lblDescripcionRutaCorta = new JLabel("Descripción Corta:");
		
		
		
		GroupLayout gl_panelVerRutas = new GroupLayout(panelVerRutas);
		gl_panelVerRutas.setHorizontalGroup(
			gl_panelVerRutas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelVerRutas.createSequentialGroup()
					.addGroup(gl_panelVerRutas.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelVerRutas.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelVerRutas.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelVerRutas.createSequentialGroup()
									.addComponent(lblHoraRuta, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textHoraRuta, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelVerRutas.createSequentialGroup()
									.addComponent(lblNombreRuta, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textNombreRuta, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelVerRutas.createSequentialGroup()
									.addComponent(lblFechaAltaRuta, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFechaAltaRuta, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelVerRutas.createSequentialGroup()
									.addComponent(lblCostoTurista, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textCostoTurista, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelVerRutas.createSequentialGroup()
									.addComponent(lblCostoEjecutivo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textCostoEjecutivo, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelVerRutas.createSequentialGroup()
									.addComponent(lblCostoEquiExtra, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textEquiExtra, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelVerRutas.createSequentialGroup()
									.addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelVerRutas.createSequentialGroup()
									.addComponent(lblDescripcionRuta, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(scrollDescripcionRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panelVerRutas.createSequentialGroup()
									.addComponent(lblDescripcionRutaCorta, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(scrollDescripcionRutaCorta, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(gl_panelVerRutas.createSequentialGroup()
							.addGap(92)
							.addComponent(lblVerRuta, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(28, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelVerRutas.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelVerRutas.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVerDatosVuelo)
						.addComponent(lblVerVuelos, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
						.addComponent(ListaVuelos, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombreVuelos, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
					.addGap(18))
		);
		gl_panelVerRutas.setVerticalGroup(
			gl_panelVerRutas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelVerRutas.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblVerRuta)
					.addGap(18)
					.addGroup(gl_panelVerRutas.createParallelGroup(Alignment.TRAILING)
						.addComponent(textNombreRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombreRuta))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelVerRutas.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblHoraRuta)
						.addComponent(textHoraRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelVerRutas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaAltaRuta)
						.addComponent(textFechaAltaRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_panelVerRutas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCostoTurista)
						.addComponent(textCostoTurista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_panelVerRutas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCostoEjecutivo)
						.addComponent(textCostoEjecutivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelVerRutas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCostoEquiExtra)
						.addComponent(textEquiExtra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelVerRutas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(textEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelVerRutas.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcionRuta)
						.addComponent(scrollDescripcionRuta, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panelVerRutas.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelVerRutas.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollDescripcionRutaCorta, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelVerRutas.createSequentialGroup()
							.addGap(2)
							.addComponent(lblDescripcionRutaCorta)))
					.addGap(13)
					.addComponent(lblNombreVuelos)
					.addGap(18)
					.addComponent(ListaVuelos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblVerVuelos)
					.addGap(18)
					.addComponent(btnVerDatosVuelo)
					.addGap(56))
		);
		
		
		panelVerRutas.setLayout(gl_panelVerRutas);
		
		JLabel lblInfoPaquete = new JLabel("Datos del Paquete de Rutas");
		lblInfoPaquete.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		
		JLabel lblNombre = new JLabel("Nombre del paquete:");
		
		textNombrePaquete = new JTextField();
		textNombrePaquete.setEditable(false);
		textNombrePaquete.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		
		JLabel lblFechaAlta = new JLabel("Fecha de alta:");
		
		textFechaAlta = new JTextField();
		textFechaAlta.setEditable(false);
		textFechaAlta.setColumns(10);
		
		JLabel lblValidez = new JLabel("Días de validez:");
		
		textValidez = new JTextField();
		textValidez.setEditable(false);
		textValidez.setColumns(10);
		
		JLabel lblCantRutas = new JLabel("Cantidad de rutas incluídas en el paquete:");
		
		textCantRutas = new JTextField();
		textCantRutas.setEditable(false);
		textCantRutas.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo:");
		
		textCosto = new JTextField();
		textCosto.setEditable(false);
		textCosto.setColumns(10);
		
		JLabel lblDescuento = new JLabel("Descuento:");
		
		textDescuento = new JTextField();
		textDescuento.setEditable(false);
		textDescuento.setColumns(10);
		
		JScrollPane scrollDescripcion = new JScrollPane(textDescripcion);
		
		JLabel lblCantEje = new JLabel("Cantidad de rutas con tipo Ejecutivo:");
		
		textCantEjec = new JTextField();
		textCantEjec.setEditable(false);
		textCantEjec.setColumns(10);
		
		JLabel lblCantTurista = new JLabel("Cantidad de rutas con tipo Turista:");
		
		textCantTurista = new JTextField();
		textCantTurista.setEditable(false);
		textCantTurista.setColumns(10);
		
		JLabel lblNombreRutas = new JLabel("Nombre de rutas incluídas:");
		
		
		JLabel lblDatosRuta = new JLabel("Para ver la infromación de la ruta seleccionada:");
		JButton VerRuta = new JButton("Ver datos");
		
		VerRuta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String rutaSeleccionada = (String) ListaNombreRutas.getSelectedItem();
            	limpiarRuta();
            	limpiarVuelo();
            	panelVerRutas.setVisible(false);
            	panelVerVuelos.setVisible(false);
            	if(rutaSeleccionada != null) {
            		panelVerRutas.setVisible(true);
            		DataRuta datosRuta = ICR.listarDatosRuta( rutaSeleccionada);
            		textNombreRuta.setText(datosRuta.getNombre());
            		textHoraRuta.setText(datosRuta.getHora().toString());
            		textFechaAltaRuta.setText(datosRuta.getFechaAlta().toString());
            		textCostoTurista.setText(datosRuta.getTuristaBase().toString());
            		textCostoEjecutivo.setText(datosRuta.getEjecutivoBase().toString());
            		textEquiExtra.setText(datosRuta.getUnidadEquipajeExtra().toString());
            		textEstado.setText(datosRuta.getEstado().toString());
            		textDescripcionRuta.setText(datosRuta.getDescripcion());
            		textDescripcionRutaCorta.setText(datosRuta.getDescCorta());
            		ListaVuelos.removeAllItems();
            		Set<String> vuelos = ICR.vuelosDeRuta(rutaSeleccionada);
                    for (String vuelo : vuelos) {
                   	 ListaVuelos.addItem(vuelo); 
                    }
            		pack();

            	}else {
            		JOptionPane.showMessageDialog(null, "Debes seleccionar una ruta para ver sus datos");
            	}
            }
            });
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblDescripcion, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollDescripcion, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
									.addComponent(lblDescuento)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textDescuento, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNombre)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textNombrePaquete, GroupLayout.PREFERRED_SIZE, 464, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblCantEje, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(gl_panel.createSequentialGroup()
															.addComponent(lblFechaAlta)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(textFechaAlta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(textCantEjec, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(lblCantTurista)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(textCantTurista, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(textCosto, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(lblCantRutas)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(textCantRutas, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(lblValidez)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(textValidez, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(VerRuta)
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
													.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_panel.createSequentialGroup()
															.addComponent(lblNombreRutas)
															.addGap(145))
														.addComponent(ListaNombreRutas, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
													.addGroup(gl_panel.createSequentialGroup()
														.addComponent(lblDatosRuta, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
														.addGap(14))))))
									.addGap(204))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(206)
							.addComponent(lblInfoPaquete)))
					.addGap(20))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(7)
					.addComponent(lblInfoPaquete)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textNombrePaquete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblFechaAlta)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(textFechaAlta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNombreRutas)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblValidez)
										.addComponent(textValidez, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCantRutas)
										.addComponent(textCantRutas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblCantEje))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(ListaNombreRutas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(17)
									.addComponent(lblDatosRuta)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(VerRuta))))
						.addComponent(textCantEjec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantTurista)
						.addComponent(textCantTurista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCosto)
						.addComponent(textCosto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDescuento)
								.addComponent(textDescuento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollDescripcion, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescripcion))))
					.addGap(20))
		);
		
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
	}
	
	public void limpiarRuta() {
		textNombreRuta.setText("");
		textHoraRuta.setText("");
		textFechaAltaRuta.setText("");
		textCostoTurista.setText("");
		textCostoEjecutivo.setText("");
		textEquiExtra.setText("");
		textEstado.setText("");
		if(textDescripcionRuta != null) {
			textDescripcionRuta.setText("");
		}
		if(textDescripcionRutaCorta != null) {
			textDescripcionRutaCorta.setText("");
		}
	}
	
	public void limpiarVuelo() {
		textNombreVuelo.setText("");
		textFechaVuelo.setText("");
		textDuracionVuelo.setText("");
		textFechaAltaVuelo.setText("");
		textTuristaVuelo.setText("");
		textEjecutivoVuelo.setText("");
		if(textReservas != null) {
			textReservas.setText("");
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textReservas;
	private JTextArea textDescripcionRuta;
	private JTextArea textDescripcionRutaCorta;
	private JTextArea textDescripcion;
	private JTextField textNombrePaquete;
	private JTextField textFechaAlta;
	private JTextField textValidez;
	private JTextField textCantRutas;
	private JTextField textCosto;
	private JTextField textDescuento;
	private JTextField textCantEjec;
	private JTextField textCantTurista;
	private JTextField textNombreRuta;
	private JTextField textHoraRuta;
	private JTextField textFechaAltaRuta;
	private JTextField textCostoTurista;
	private JTextField textCostoEjecutivo;
	private JTextField textEquiExtra;
	private JTextField textNombreVuelo;
	private JTextField textFechaVuelo;
	private JTextField textDuracionVuelo;
	private JTextField textFechaAltaVuelo;
	private JTextField textTuristaVuelo;
	private JTextField textEjecutivoVuelo;
	private JTextField textEstado;
}
