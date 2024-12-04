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
import javax.swing.LayoutStyle.ComponentPlacement;
import java.util.ArrayList;
import java.util.List;
import logica.*;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTable;
/*import logica.enumerado.*;
import logica.datatype.*;
import logica.controlador.*;*/

public class ConsultarTop5 extends JInternalFrame{
	public ConsultarTop5(IControladorRutas ICR) {
		setMaximumSize(new Dimension(1000, 1000));
		//---------------------------------------------------DESCOMENTAR PARA CASOS PRUEBA-------------------------------------------
		//casosPrueba(ICU, ICR, ICV);
		//---------------------------------------------------------------------------------------------------------------------------
		setClosable(true);
		setTitle("Consulta De Rutas Mas Visitadas");
		String test;
		List<String> myList = new ArrayList<>(0);
		//----------------------------------------------------------------------------------------------------------------------------
		List<DataRutaVisitas> rutas = ICR.top5();
		
		JLabel lblNewLabel_4_1 = new JLabel("#");
		
		JLabel lblNewLabel_1_4 = new JLabel("1   ");
		
		JLabel lblNewLabel_1 = new JLabel("2   ");
		
		JLabel lblNewLabel_1_3 = new JLabel("3   ");
		
		JLabel lblNewLabel_1_1 = new JLabel("4   ");
		
		JLabel lblNewLabel_1_2 = new JLabel("5   ");
		
		JLabel lblNewLabel_5 = new JLabel("Ruta de Vuelo");
		
		JLabel Ruta1 = new JLabel("Ruta de Vuelo");
		Ruta1.setText(rutas.get(0).getRuta());
		
		JLabel Al1 = new JLabel("Aerolínea");
		Al1.setText(rutas.get(0).getAerolinea());
		
		JLabel Ruta2 = new JLabel("Ruta de Vuelo");
		Ruta2.setText(rutas.get(1).getRuta());
		
		JLabel Ruta3 = new JLabel("Ruta de Vuelo");
		Ruta3.setText(rutas.get(2).getRuta());
		
		JLabel Ruta5 = new JLabel("Ruta de Vuelo");
		Ruta5.setText(rutas.get(4).getRuta());
		
		JLabel lblNewLabel_5_1 = new JLabel("Aerolínea");
		
		JLabel Ruta4 = new JLabel("Ruta de Vuelo");
		Ruta4.setText(rutas.get(3).getRuta());
		
		JLabel Al2 = new JLabel("Aerolínea");
		Al2.setText(rutas.get(1).getAerolinea());
		
		JLabel Al3 = new JLabel("Aerolínea");
		Al3.setText(rutas.get(2).getAerolinea());
		
		JLabel Al4 = new JLabel("Aerolínea");
		Al4.setText(rutas.get(3).getAerolinea());
		
		JLabel Al5 = new JLabel("Aerolínea");
		Al5.setText(rutas.get(4).getAerolinea());
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Ciudad de Orígen");
		
		JLabel O1 = new JLabel("Ciudad de Orígen");
		O1.setText(rutas.get(0).getOrigen().getNombre() + rutas.get(0).getOrigen().getPais());
		
		JLabel O2 = new JLabel("Ciudad de Orígen");
		O2.setText(rutas.get(1).getOrigen().getNombre() + rutas.get(1).getOrigen().getPais());
		
		JLabel O3 = new JLabel("Ciudad de Orígen");
		O3.setText(rutas.get(2).getOrigen().getNombre() + rutas.get(2).getOrigen().getPais());
		
		JLabel O4 = new JLabel("Ciudad de Orígen");
		O4.setText(rutas.get(3).getOrigen().getNombre() + rutas.get(3).getOrigen().getPais());
		
		JLabel O5 = new JLabel("Ciudad de Orígen");
		O5.setText(rutas.get(4).getOrigen().getNombre() + rutas.get(4).getOrigen().getPais());
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("Ciudad de Destino");
		
		JLabel D1 = new JLabel("Ciudad de Destino");
		D1.setText(rutas.get(0).getDestino().getNombre() + rutas.get(0).getDestino().getPais());
		
		JLabel D2 = new JLabel("Ciudad de Destino");
		D2.setText(rutas.get(1).getDestino().getNombre() + rutas.get(1).getDestino().getPais());
		
		JLabel D3 = new JLabel("Ciudad de Destino");
		D3.setText(rutas.get(2).getDestino().getNombre() + rutas.get(2).getDestino().getPais());
		
		JLabel D4 = new JLabel("Ciudad de Destino");
		D4.setText(rutas.get(3).getDestino().getNombre() + rutas.get(3).getDestino().getPais());
		
		JLabel D5 = new JLabel("Ciudad de Destino");
		D5.setText(rutas.get(4).getDestino().getNombre() + rutas.get(4).getDestino().getPais());
		
		JLabel lblNewLabel_5_1_2 = new JLabel("Visitas");
		
		JLabel V1 = new JLabel("Visitas");
		V1.setText(String.valueOf(rutas.get(0).getVisitas()));
		
		JLabel V2 = new JLabel("Visitas");
		V2.setText(String.valueOf(rutas.get(1).getVisitas()));
		
		JLabel V4 = new JLabel("Visitas");
		V4.setText(String.valueOf(rutas.get(3).getVisitas()));
		
		JLabel V5 = new JLabel("Visitas");
		V5.setText(String.valueOf(rutas.get(4).getVisitas()));
		
		JLabel V3 = new JLabel("Visitas");
		V3.setText(String.valueOf(rutas.get(2).getVisitas()));

		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_4_1)
							.addGap(18)
							.addComponent(lblNewLabel_5))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1_4)
							.addGap(18)
							.addComponent(Ruta1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(Ruta2))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1_3)
							.addGap(18)
							.addComponent(Ruta3))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1_1)
							.addGap(18)
							.addComponent(Ruta4))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1_2)
							.addGap(18)
							.addComponent(Ruta5)))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_5_1)
							.addGap(58)
							.addComponent(lblNewLabel_5_1_1)
							.addGap(80)
							.addComponent(lblNewLabel_5_1_1_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(Al1)
								.addComponent(Al2)
								.addComponent(Al3)
								.addComponent(Al4)
								.addComponent(Al5))
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(O1)
								.addComponent(O2)
								.addComponent(O3)
								.addComponent(O4)
								.addComponent(O5))
							.addGap(56)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(D5)
								.addComponent(D4)
								.addComponent(D3)
								.addComponent(D2)
								.addComponent(D1))))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(66)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(V2)
								.addComponent(V1)
								.addComponent(V3)
								.addComponent(V4)
								.addComponent(V5)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(48)
							.addComponent(lblNewLabel_5_1_2)))
					.addContainerGap(286, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4_1)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_5_1)
						.addComponent(lblNewLabel_5_1_1)
						.addComponent(lblNewLabel_5_1_1_1)
						.addComponent(lblNewLabel_5_1_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_4)
						.addComponent(Ruta1)
						.addComponent(Al1)
						.addComponent(O1)
						.addComponent(D1)
						.addComponent(V1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(Ruta2)
						.addComponent(Al2)
						.addComponent(O2)
						.addComponent(D2)
						.addComponent(V2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_3)
						.addComponent(Ruta3)
						.addComponent(Al3)
						.addComponent(O3)
						.addComponent(D3)
						.addComponent(V3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1)
						.addComponent(Ruta4)
						.addComponent(Al4)
						.addComponent(O4)
						.addComponent(D4)
						.addComponent(V4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_2)
						.addComponent(Ruta5)
						.addComponent(Al5)
						.addComponent(O5)
						.addComponent(D5)
						.addComponent(V5))
					.addContainerGap(547, Short.MAX_VALUE))
		);
		//scrollPaneTexto_1_1.setViewportView(descripcionRuta_1);
		getContentPane().setLayout(groupLayout);
		//getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel, listaAerolineas, lblAerolinea, lblRutaDeVuelo, listaRutas, panel_1, lblNombre_1, txtNombreRuta, lblHora, txtHoraRuta, lblDescripcion, scrollPaneTexto_1, descripcionRuta_1, lblPrecioEjecutivo, txtPrecioEjecutivoRuta, lblPrecioTurista, txtPrecioTuristaRuta, lblPrecioUnidadDe, txtPrecioUnEqExtraRuta, lblFechaDeAlta, txtFechaDeAltaRuta, lblDatosDeLa, lblVuelo, listaVuelos, btnInfoVuelo, lblNombre, txtNombreVuelo, lblFecha, txtFechaVuelo, lblTitulo, lblReservasAsociadas, scrollPaneTexto, list, lblDuracion, txtDuracionVuelo, lblAsientosEjecutivos, txtAsientosEjecutivosVuelo, lblFechaAlta, txtFechaDeAltaVuelo, lblAsientosTuristas, txtAsientosTuristasVuelo}));
		
		
	}
	
	public static void main(String[] args) {
		
	}
}

