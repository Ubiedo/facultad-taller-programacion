package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.LayoutStyle.ComponentPlacement;

/*import logica.enumerado.*;
import logica.datatype.*;
import logica.controlador.*;*/
import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import logica.*;

class DataRutaRender extends JPanel implements ListCellRenderer<DataRuta> {
    private JTextArea txtNombre;
    private JTextArea txtDescripcion;
    private JTextArea txtHora;
    private JTextArea txtFechaAlta;
    private JTextArea txtTuristaBase;
    private JTextArea txtEjecutivoBase;
    private JTextArea txtUnidadEquipajeExtra;

    public DataRutaRender() {
        // Usamos un diseño de caja vertical
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 

        // Inicializamos los JTextAreas con un tamaño por defecto
        txtNombre = new JTextArea(1, 20);
        txtNombre.setLineWrap(true);
        txtNombre.setWrapStyleWord(true);
        txtNombre.setEditable(false);
        txtNombre.setOpaque(false);
        
        txtDescripcion = new JTextArea(2, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEditable(false);
        txtDescripcion.setOpaque(false);
        
        txtHora = new JTextArea(1, 20);
        txtHora.setLineWrap(true);
        txtHora.setWrapStyleWord(true);
        txtHora.setEditable(false);
        txtHora.setOpaque(false);
        
        txtFechaAlta = new JTextArea(1, 20);
        txtFechaAlta.setLineWrap(true);
        txtFechaAlta.setWrapStyleWord(true);
        txtFechaAlta.setEditable(false);
        txtFechaAlta.setOpaque(false);
        
        txtTuristaBase = new JTextArea(1, 20);
        txtTuristaBase.setLineWrap(true);
        txtTuristaBase.setWrapStyleWord(true);
        txtTuristaBase.setEditable(false);
        txtTuristaBase.setOpaque(false);
        
        txtEjecutivoBase = new JTextArea(1, 20);
        txtEjecutivoBase.setLineWrap(true);
        txtEjecutivoBase.setWrapStyleWord(true);
        txtEjecutivoBase.setEditable(false);
        txtEjecutivoBase.setOpaque(false);
        
        txtUnidadEquipajeExtra = new JTextArea(3, 20);
        txtUnidadEquipajeExtra.setLineWrap(true);
        txtUnidadEquipajeExtra.setWrapStyleWord(true);
        txtUnidadEquipajeExtra.setEditable(false);
        txtUnidadEquipajeExtra.setOpaque(false);


        txtNombre.setEditable(false);
        txtDescripcion.setEditable(false);
        txtHora.setEditable(false);
        txtFechaAlta.setEditable(false);
        txtTuristaBase.setEditable(false);
        txtEjecutivoBase.setEditable(false);
        txtUnidadEquipajeExtra.setEditable(false);

        // Agregamos los JTextAreas al panel
        add(txtNombre);
        add(txtDescripcion);
        add(txtHora);
        add(txtFechaAlta);
        add(txtTuristaBase);
        add(txtEjecutivoBase);
        add(txtUnidadEquipajeExtra);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends DataRuta> list, DataRuta value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        txtNombre.setText("Nombre: " + value.getNombre());
        txtDescripcion.setText("Descripción: " + value.getDescripcion());
        txtHora.setText("Hora: " + value.getHora().toString());
        txtFechaAlta.setText("Fecha Alta: " + value.getFechaAlta().toString());
        txtTuristaBase.setText("Turista Base: $" + value.getTuristaBase());
        txtEjecutivoBase.setText("Ejecutivo Base: $" + value.getEjecutivoBase());
        txtUnidadEquipajeExtra.setText("Unidad Equipaje Extra: $" + value.getUnidadEquipajeExtra());

        // Configuramos colores de selección
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        // Necesario para que se dibuje el fondo
        setOpaque(true); 
        return this;
    }
}



public class AgregarRutaAPaquete extends JInternalFrame{
	private JComboBox<String> comboBoxPaquetes;
	private JScrollPane scrollPane;
	private JList<DataRuta> lista;
	private JSpinner spinnerCantidad;
	public AgregarRutaAPaquete(IControladorUsuarios ICU, IControladorVuelos ICV, IControladorRutas ICR) {
		setClosable(true);
		setTitle("Agregar Ruta a Paquete");
		
		JSplitPane splitPane = new JSplitPane();
        getContentPane().add(splitPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        splitPane.setLeftComponent(panel);
		
		JLabel lblPaquetes = new JLabel("Paquetes sin compras:");
		
		comboBoxPaquetes = new JComboBox<String>();
		
		JLabel lblAerolineas = new JLabel("Aerolineas");
		
		
		JComboBox<String> comboBoxAerolineas = new JComboBox<String>();
		
	
		
		comboBoxPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Set<String> aerolineas = ICU.listarAerolineas();
				comboBoxAerolineas.removeAllItems();
				for(String aero: aerolineas) {
					comboBoxAerolineas.addItem(aero);			
				}
			}
		});
		
		JComboBox<Asiento> comboBoxAsiento = new JComboBox<Asiento>();
		comboBoxAsiento.addItem(Asiento.turista);
		comboBoxAsiento.addItem(Asiento.ejecutivo);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		spinnerCantidad = new JSpinner();
		
		JLabel lblTipodeAsiento = new JLabel("Tipo de Asiento:");
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String paquete = (String) comboBoxPaquetes.getSelectedItem();
				String aerolinea = (String) comboBoxAerolineas.getSelectedItem();
				Integer cantidad = (Integer) spinnerCantidad.getValue();
				Asiento tAsiento = (Asiento) comboBoxAsiento.getSelectedItem();
				try {
					String ruta = (lista.getSelectedValue()).getNombre();

					if (cantidad <= 0) {
						JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						Boolean exito = ICR.agregarRutaPaquete(ruta, paquete, cantidad, tAsiento);
						if (exito) {
			            	spinnerCantidad.setValue(0);
							JOptionPane.showMessageDialog(null, "La ruta se ha agregado al paquete exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "La ruta ya está asociada al paquete", "Error", JOptionPane.ERROR_MESSAGE);

						}
					}
				}
				catch(NullPointerException e) {
		            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna ruta.", "Error", JOptionPane.ERROR_MESSAGE);

				}
				
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		
		btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	spinnerCantidad.setValue(0);
            	dispose();
            }
        });
		
		
		
	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnAceptar)
							.addPreferredGap(ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
							.addComponent(btnCancelar)
							.addGap(23))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCantidad)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spinnerCantidad, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblTipodeAsiento)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBoxAsiento, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblAerolineas)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxAerolineas, 0, 244, Short.MAX_VALUE)
							.addGap(14))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblPaquetes)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxPaquetes, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(14, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(68)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPaquetes)
						.addComponent(comboBoxPaquetes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAerolineas)
						.addComponent(comboBoxAerolineas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxAsiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipodeAsiento)
						.addComponent(lblCantidad)
						.addComponent(spinnerCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnCancelar))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		
	
		
		 JPanel panelDerecho = new JPanel();
	        panelDerecho.setLayout(new BorderLayout());
	        splitPane.setRightComponent(panelDerecho);
	        
	        lista = new JList<>(new DataRuta[0]);
	        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lista.setCellRenderer(new DataRutaRender());
			
			scrollPane = new JScrollPane(lista);
			scrollPane.setPreferredSize(new Dimension(300, 200));
			panelDerecho.add(scrollPane,BorderLayout.CENTER);
			
			JLabel lblNewLabel = new JLabel("Rutas de vuelo de Aerolinea");
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        scrollPane.setColumnHeaderView(lblNewLabel);
	        
	        comboBoxAerolineas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String aeroSeleccionada = (String) comboBoxAerolineas.getSelectedItem();
					if (aeroSeleccionada != null) {
						Set<DataRuta> rutas = ICU.infoRutasDeAerolinea(aeroSeleccionada);
						DataRuta[] arregloRutas = rutas.toArray(new DataRuta[0]);
						lista.setListData(arregloRutas);
					}
				}
			});
	        pack();
	} 
	
	
	public void listarPaquetes(IControladorVuelos ICV) {
		Set<String> paquetesNoComprados = ICV.listarPaquetesNoComprados();
		comboBoxPaquetes.removeAllItems();
		for(String pq: paquetesNoComprados) {
			comboBoxPaquetes.addItem(pq);
			
		}
	}
}


