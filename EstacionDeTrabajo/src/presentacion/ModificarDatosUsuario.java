package presentacion;
/*
 * ModificarDatosUsuario
 * 
 * Implementacion de un internal frame para la ventana principal.
 *
 * 04/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import logica.*;
/*import logica.enumerado.*;
import logica.datatype.*;
import logica.controlador.*;*/
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.SpinnerDateModel;
import java.util.Date;

public class ModificarDatosUsuario extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nicknameTF;
	private JTextField emailTF;
	private JTextField nombreTF;
	private JTextField apellidoTF;
	private JTextField nroDocumentoTF;
	private JTextField webTF;
	private JTextField descripcionTF;
	// variables para funcionamiento
	private boolean esCliente;
	private boolean haySeleccion;
	private DataUsuario data;
	private JTextField nacionalidadTF;
	/*
	 * Create the frame.
	 */
	public ModificarDatosUsuario(IControladorUsuarios ICU) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
		setBounds(100, 100, 466, 333);
		setTitle("Modificar Datos De Usuario");
		esCliente = false;
		haySeleccion = false;
		JSplitPane splitPane = new JSplitPane();
		
		JPanel panelLeft = new JPanel();
		splitPane.setLeftComponent(panelLeft);
		
		JLabel tituloSeleccionar = new JLabel("Seleccionar Usuario");
		tituloSeleccionar.setHorizontalAlignment(SwingConstants.LEFT);
		
		JComboBox<String> userSelector = new JComboBox<String>();
		userSelector.addItem("");
		Set<String> nicknamesUsuarios = ICU.listarUsuarios();
		for (String usuario : nicknamesUsuarios) {
			userSelector.addItem(usuario);
		}
		userSelector.setPreferredSize(new Dimension(100, 20));
		GroupLayout gl_panelLeft = new GroupLayout(panelLeft);
		gl_panelLeft.setHorizontalGroup(
			gl_panelLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLeft.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelLeft.createParallelGroup(Alignment.LEADING)
						.addComponent(userSelector, 0, 93, Short.MAX_VALUE)
						.addComponent(tituloSeleccionar))
					.addContainerGap())
		);
		gl_panelLeft.setVerticalGroup(
			gl_panelLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLeft.createSequentialGroup()
					.addContainerGap()
					.addComponent(tituloSeleccionar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(272))
		);
		panelLeft.setLayout(gl_panelLeft);
		/*-----------------------------------------------------------------------------------------------------Right Pane------*/
		JPanel panelRight = new JPanel();
		panelRight.setBackground(new Color(206, 206, 206));
		splitPane.setRightComponent(panelRight);
		
		JPanel panelComun = new JPanel();
		
		JPanel panelCliente = new JPanel();
		
		JPanel panelAerolinea = new JPanel();
		
		JPanel panelEspera = new JPanel();
		
		JPanel aceptarCancelar = new JPanel();
		GroupLayout gl_panelRight = new GroupLayout(panelRight);
		gl_panelRight.setHorizontalGroup(
			gl_panelRight.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelRight.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRight.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelCliente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addComponent(panelAerolinea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addComponent(panelEspera, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addComponent(panelComun, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addComponent(aceptarCancelar, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelRight.setVerticalGroup(
			gl_panelRight.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRight.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelComun, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelCliente, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelAerolinea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(aceptarCancelar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelEspera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JButton cancelar = new JButton("Cancelar");
		
		JButton aceptar = new JButton("Aceptar");
		
		GroupLayout gl_aceptarCancelar = new GroupLayout(aceptarCancelar);
		
		gl_aceptarCancelar.setHorizontalGroup(
			gl_aceptarCancelar.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_aceptarCancelar.createSequentialGroup()
					.addContainerGap(88, Short.MAX_VALUE)
					.addComponent(aceptar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cancelar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_aceptarCancelar.setVerticalGroup(
			gl_aceptarCancelar.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_aceptarCancelar.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_aceptarCancelar.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelar)
						.addComponent(aceptar)))
		);
		aceptarCancelar.setLayout(gl_aceptarCancelar);
		
		JLabel esperaL = new JLabel("Sin usuario para mostrar informacion.");
		panelEspera.add(esperaL);
		
		webTF = new JTextField();
		webTF.setColumns(10);
		
		JLabel webL = new JLabel("Web:");
		
		descripcionTF = new JTextField();
		descripcionTF.setColumns(10);
		
		JLabel descripcionL = new JLabel("Descripcion:");
		GroupLayout gl_panelAerolinea = new GroupLayout(panelAerolinea);
		gl_panelAerolinea.setHorizontalGroup(
			gl_panelAerolinea.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelAerolinea.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.LEADING)
						.addComponent(webL)
						.addComponent(descripcionL))
					.addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
					.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.TRAILING)
						.addComponent(descripcionTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(webTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panelAerolinea.setVerticalGroup(
			gl_panelAerolinea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAerolinea.createSequentialGroup()
					.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.BASELINE)
						.addComponent(webTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(webL))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelAerolinea.createParallelGroup(Alignment.BASELINE)
						.addComponent(descripcionTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(descripcionL))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelAerolinea.setLayout(gl_panelAerolinea);
		
		JLabel apellidoL = new JLabel("Apellido:");
		
		apellidoTF = new JTextField();
		apellidoTF.setColumns(10);
		
		SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
		JSpinner fechaJS = new JSpinner(dateModel);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(fechaJS, "dd/MM/yyyy");
		fechaJS.setEditor(editor);
		
		JLabel nacimientoL = new JLabel("Nacimiento:");
		
		nroDocumentoTF = new JTextField();
		nroDocumentoTF.setColumns(10);
		
		JLabel nroDocumentoL = new JLabel("Nro Documento:");
		
		nacionalidadTF = new JTextField();
		nacionalidadTF.setColumns(10);
		
		JLabel nacionalidadL = new JLabel("Nacionalidad:");
		
		JComboBox<String> tipoDocumentoCB = new JComboBox<String>();
		tipoDocumentoCB.setModel(new DefaultComboBoxModel<String>(new String[] {"CI", "Pasaporte"}));
		
		JLabel tipoDocumentoL = new JLabel("Tipo Documento:");
		GroupLayout gl_panelCliente = new GroupLayout(panelCliente);
		gl_panelCliente.setHorizontalGroup(
			gl_panelCliente.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCliente.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCliente.createParallelGroup(Alignment.LEADING)
						.addComponent(apellidoL)
						.addComponent(nacimientoL)
						.addComponent(tipoDocumentoL)
						.addComponent(nroDocumentoL)
						.addComponent(nacionalidadL))
					.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
					.addGroup(gl_panelCliente.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(apellidoTF)
						.addComponent(nacionalidadTF)
						.addComponent(nroDocumentoTF)
						.addComponent(tipoDocumentoCB, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fechaJS))
					.addContainerGap())
		);
		gl_panelCliente.setVerticalGroup(
			gl_panelCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCliente.createSequentialGroup()
					.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(apellidoL)
						.addComponent(apellidoTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(nacimientoL)
						.addComponent(fechaJS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(tipoDocumentoL)
						.addComponent(tipoDocumentoCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(nroDocumentoL)
						.addComponent(nroDocumentoTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(nacionalidadL)
						.addComponent(nacionalidadTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		panelCliente.setLayout(gl_panelCliente);
		
		JLabel nicknameL = new JLabel("Nickname:");
		
		nicknameTF = new JTextField();
		nicknameTF.setEditable(false);
		nicknameTF.setColumns(10);
		
		emailTF = new JTextField();
		emailTF.setEditable(false);
		emailTF.setColumns(10);
		
		nombreTF = new JTextField();
		nombreTF.setColumns(10);
		
		JLabel emailL = new JLabel("Email:");
		
		JLabel nombreL = new JLabel("Nombre:");
		GroupLayout gl_panelComun = new GroupLayout(panelComun);
		gl_panelComun.setHorizontalGroup(
			gl_panelComun.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelComun.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelComun.createParallelGroup(Alignment.LEADING)
						.addComponent(nombreL)
						.addComponent(emailL)
						.addComponent(nicknameL))
					.addPreferredGap(ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
					.addGroup(gl_panelComun.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(nombreTF)
						.addComponent(emailTF)
						.addComponent(nicknameTF))
					.addContainerGap())
		);
		gl_panelComun.setVerticalGroup(
			gl_panelComun.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelComun.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelComun.createParallelGroup(Alignment.BASELINE)
						.addComponent(nicknameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nicknameL))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelComun.createParallelGroup(Alignment.BASELINE)
						.addComponent(emailTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(emailL))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelComun.createParallelGroup(Alignment.BASELINE)
						.addComponent(nombreL)
						.addComponent(nombreTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelComun.setLayout(gl_panelComun);
		panelRight.setLayout(gl_panelRight);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 281, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		/*---------------------------------------------------------------------------------------------------------------------*/
		panelEspera.setBackground(null);
		panelComun.setBackground(null);
		panelCliente.setBackground(null);
		panelAerolinea.setBackground(null);
		aceptarCancelar.setBackground(null);
		panelEspera.setVisible(true);
		panelComun.setVisible(false);
		panelCliente.setVisible(false);
		panelAerolinea.setVisible(false);
		aceptarCancelar.setVisible(false);
		/*---------------------------------------------------------------------------------------------------------------------*/
		userSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEspera.setVisible(true);
				panelComun.setVisible(false);
				panelCliente.setVisible(false);
				panelAerolinea.setVisible(false);;
				aceptarCancelar.setVisible(false);
				if(userSelector.getSelectedItem().equals("")) {
					haySeleccion = false;
				} else {
					haySeleccion = true;
					panelComun.setVisible(true);
					panelEspera.setVisible(false);
					data = ICU.listarDatosUsuarioNickname((String) userSelector.getSelectedItem());
					nicknameTF.setText(data.getNickname());
					emailTF.setText(data.getEmail());
					nombreTF.setText(data.getNombre());
					esCliente = ICU.esCliente((String) userSelector.getSelectedItem());
					if(esCliente) {
						DataCliente dc = (DataCliente) data;
						// set contenido de los TF
						apellidoTF.setText(dc.getApellido());
						LocalDate miFecha = LocalDate.parse(dc.getFechaNac());
						Date date = Date.from(miFecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
						fechaJS.setValue(date);
						if(dc.getTipoDocumento() == Documento.CI) {
							tipoDocumentoCB.setSelectedItem("CI");
						} else {
							tipoDocumentoCB.setSelectedItem("Pasaporte");
						}
						nroDocumentoTF.setText(dc.getNroDocumento());
						nacionalidadTF.setText(dc.getNacionalidad());
						// mostrar las relevantes a Cliente
						panelCliente.setVisible(true);
					} else {
						DataAerolinea da = (DataAerolinea) data;
						// set contenido de los TF
						webTF.setText(da.getWeb());
						descripcionTF.setText(da.getDescripcion());
						// mostrar las relevantes a Aerolinea
						panelAerolinea.setVisible(true);
					}
					aceptarCancelar.setVisible(true);
				}
		        panelRight.revalidate();
		        panelRight.repaint();
			}
		});
		/*---------------------------------------------------------------------------------------------------------------------*/
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Modifica los datos
				if(haySeleccion == false) {
					JOptionPane.showMessageDialog(null, "Para realizar esta accion es necesario seleccionar un usuario!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					if(esCliente) {
						ICU.setDatosCliente(nicknameTF.getText(), nombreTF.getText(), apellidoTF.getText(), ((Date) fechaJS.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), nacionalidadTF.getText(), (String)tipoDocumentoCB.getSelectedItem(), nroDocumentoTF.getText());
					} else {
						ICU.setDatosAerolinea(nicknameTF.getText(), nombreTF.getText(), webTF.getText(), descripcionTF.getText());
					}
					JOptionPane.showMessageDialog(null, "Datos Modificados!");
					userSelector.setSelectedIndex(0);
				}
			}
		});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(data != null) {
					nicknameTF.setText(data.getNickname());
					emailTF.setText(data.getEmail());
					nombreTF.setText(data.getNombre());
					esCliente = ICU.esCliente((String) userSelector.getSelectedItem());
					if(esCliente) {
						DataCliente dc = (DataCliente) data;
						// set contenido de los TF
						apellidoTF.setText(dc.getApellido());
						LocalDate miFecha = LocalDate.parse(dc.getFechaNac());
						Date date = Date.from(miFecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
						fechaJS.setValue(date);
						nroDocumentoTF.setText(dc.getNroDocumento());
						nacionalidadTF.setText(dc.getNacionalidad());
						// mostrar las relevantes a Cliente
						panelCliente.setVisible(true);
					} else {
						DataAerolinea da = (DataAerolinea) data;
						// set contenido de los TF
						webTF.setText(da.getWeb());
						descripcionTF.setText(da.getDescripcion());
						// mostrar las relevantes a Aerolinea
						panelAerolinea.setVisible(true);
					}
				}
			}
		});
		/*---------------------------------------------------------------------------------------------------------------------*/
		//this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
		//    @Override
		//    public void internalFrameClosing(javax.swing.event.InternalFrameEvent e) {
		//        // Cambiar la selección del combo box al cerrarse el InternalFrame
		//        userSelector.setSelectedIndex(0); // Cambia a la opción deseada
		//    }
		//});
	}
}
