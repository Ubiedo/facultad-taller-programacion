package presentacion;
/*
 * AltaUsuario
 * 
 * Implementacion de un internal frame para la ventana principal.
 *
 * 04/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import logica.*;
/*import logica.enumerado.*;
import logica.controlador.*;*/
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class AltaUsuario extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNickname;
	private JTextField textFieldEmail;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldNacionalidad;
	private JTextField textFieldNroDocumento;
	private JTextField textFieldNicknameAl;
	private JTextField textFieldEmailAl;
	private JTextField textFieldNombreAl;
	private JTextField textFieldWeb;
	private JTextField textFieldContraAl;
	private JTextField textFieldContraCl;

	public AltaUsuario(IControladorUsuarios ctrlUsuarios) {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Registrar un Usuario");
        setBounds(10, 40, 552, 389);
        
        textFieldNickname = new JTextField();
        textFieldNickname.setColumns(10);
        
        JLabel lblNickname = new JLabel("Nickname");
        
        textFieldEmail = new JTextField();
        textFieldEmail.setColumns(10);
        
        JLabel lblEmail = new JLabel("Email");
        
        textFieldNombre = new JTextField();
        textFieldNombre.setColumns(10);
        
        JLabel lblNombre = new JLabel("Nombre");
        
        textFieldApellido = new JTextField();
        textFieldApellido.setColumns(10);
        
        JLabel lblApellido = new JLabel("Apellido");
        
        JLabel lblFechaNac = new JLabel("Fecha de Nacimiento");
        
        JSpinner spinnerDia = new JSpinner();
        spinnerDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
        
        JSpinner spinnerMes = new JSpinner();
        spinnerMes.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        
        JSpinner spinnerAnio = new JSpinner();
        spinnerAnio.setModel(new SpinnerNumberModel(0, 0, 9999, 1));
        
        textFieldNacionalidad = new JTextField();
        textFieldNacionalidad.setColumns(10);
        
        JLabel lblNacionalidad = new JLabel("Nacionalidad");
        
        JComboBox comboBoxTipoDoc = new JComboBox();
        comboBoxTipoDoc.setModel(new DefaultComboBoxModel(new String[] {"CI", "Pasaporte"}));
        
        JLabel lblTipoDoc = new JLabel("Tipo de Documento");
        
        textFieldNroDocumento = new JTextField();
        textFieldNroDocumento.setColumns(10);
        
        JLabel lblNroDocumento = new JLabel("Nro. Documento");
        
        JButton btnCrearCliente = new JButton("Crear Cliente");
        btnCrearCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nickname = textFieldNickname.getText();
				String email = textFieldEmail.getText();
				String contra = textFieldContraCl.getText();
				String nombre = textFieldNombre.getText();
				String apellido = textFieldApellido.getText();
				Integer dia = (Integer) spinnerDia.getValue();
				Integer mes = (Integer) spinnerMes.getValue();
				Integer anio = (Integer) spinnerAnio.getValue();
				String nacionalidad = textFieldNacionalidad.getText();
				Documento tipoDocumento = Documento.CI;
				if (comboBoxTipoDoc.getSelectedItem() == "Pasaporte")
					tipoDocumento = Documento.PASAPORTE;
				String nroDocumento = textFieldNroDocumento.getText();
				LocalDate fechaNac = null;
				Boolean valida = true;
				
				if (nickname.isEmpty() || email.isEmpty() || contra.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || nacionalidad.isEmpty() || nroDocumento.isEmpty()) {
		            JOptionPane.showMessageDialog(AltaUsuario.this, "Campo(s) vacío(s)", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
		        }else {
		        	try {
		        		fechaNac = LocalDate.of(anio,mes,dia);
		        		}catch(DateTimeException e){
		        			valida = false;
		        			JOptionPane.showMessageDialog(AltaUsuario.this, "La fecha ingresada no es válida", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
		        	}
		        	if (valida) {
		        		Integer res = ctrlUsuarios.crearCliente(nickname, nombre, email, apellido, fechaNac, nacionalidad, tipoDocumento, nroDocumento, contra,"");
		        		if (res == 1) {
		        			JOptionPane.showMessageDialog(AltaUsuario.this, "Nickname o email ya en uso", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
		        		}else {
		        			limpiar();
		        			JOptionPane.showMessageDialog(AltaUsuario.this, "Cliente ingresado con éxito", "Alta Usuario",
		                            JOptionPane.INFORMATION_MESSAGE);
		        			setVisible(false);
		        		}
		        	}
		        }
			}
		});
        
        
        textFieldNicknameAl = new JTextField();
        textFieldNicknameAl.setColumns(10);
        
        JLabel lblNicknameAl = new JLabel("Nickname");
        
        textFieldEmailAl = new JTextField();
        textFieldEmailAl.setColumns(10);
        
        JLabel lblEmailAl = new JLabel("Email");
        
        textFieldNombreAl = new JTextField();
        textFieldNombreAl.setColumns(10);
        
        JLabel lblNombreAl = new JLabel("Nombre");
        
        textFieldWeb = new JTextField();
        textFieldWeb.setColumns(10);
        
        JLabel lblWeb = new JLabel("Sitio Web");
        
        JLabel lblDescripcion = new JLabel("Descripción");
        
        JTextArea textAreaDescripcion = new JTextArea();
        JScrollPane scrollPane = new JScrollPane();
        
        textFieldContraAl = new JTextField();
        textFieldContraAl.setColumns(10);
        
        JLabel lblContrasenaAl = new JLabel("Contraseña");
        
        textFieldContraCl = new JTextField();
        textFieldContraCl.setColumns(10);
        
        JLabel lblContrasea_1 = new JLabel("Contraseña");
        
        JButton btnCrearAerolinea = new JButton("Crear Aerolínea");
        btnCrearAerolinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nicknameAl = textFieldNicknameAl.getText();
				String emailAl = textFieldEmailAl.getText();
				String contraAl = textFieldContraAl.getText();
				String nombreAl = textFieldNombreAl.getText();
				String web = textFieldWeb.getText();
				String descripcion = textAreaDescripcion.getText();
				
				if (nicknameAl.isEmpty() || emailAl.isEmpty() || contraAl.isEmpty() || nombreAl.isEmpty() || web.isEmpty() || descripcion.isEmpty()) {
		            JOptionPane.showMessageDialog(AltaUsuario.this, "Campo(s) vacío(s)", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
		        }else {
	        		Integer res = ctrlUsuarios.crearAerolinea(nicknameAl, nombreAl, emailAl, web, descripcion, contraAl,"");
	        		if (res == 1) {
	        			JOptionPane.showMessageDialog(AltaUsuario.this, "Nickname o email ya en uso", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
	        		}else {
	        			limpiar();
	        			JOptionPane.showMessageDialog(AltaUsuario.this, "Aerolínea ingresada con éxito", "Alta Usuario",
	                            JOptionPane.INFORMATION_MESSAGE);
	        			setVisible(false);
		        	}
		        }
			}
		});
        
        
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(30)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(lblNombre)
        						.addComponent(lblEmail)
        						.addComponent(lblNickname)
        						.addComponent(lblApellido)
        						.addComponent(lblContrasea_1))
        					.addGap(28))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(lblNicknameAl)
        						.addComponent(lblEmailAl)
        						.addComponent(lblNombreAl)
        						.addComponent(lblContrasenaAl))
        					.addGap(28)))
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
        							.addComponent(textFieldNickname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
        							.addComponent(textFieldEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
        							.addComponent(textFieldNombre)
        							.addComponent(textFieldApellido))
        						.addComponent(textFieldContraCl, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(lblNacionalidad)
        						.addComponent(lblTipoDoc)
        						.addComponent(lblNroDocumento)
        						.addComponent(lblFechaNac))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        							.addComponent(textFieldNroDocumento)
        							.addComponent(comboBoxTipoDoc, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addGroup(groupLayout.createSequentialGroup()
        								.addComponent(spinnerDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addComponent(spinnerMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addComponent(spinnerAnio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        							.addComponent(textFieldNacionalidad))
        						.addComponent(btnCrearCliente)))
        				.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING)
        					.addGroup(groupLayout.createSequentialGroup()
        						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        							.addComponent(textFieldNicknameAl, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
        							.addGroup(groupLayout.createSequentialGroup()
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addComponent(textFieldNombreAl, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
        							.addComponent(textFieldEmailAl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
        							.addComponent(textFieldContraAl, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
        						.addGap(18)
        						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        							.addComponent(lblWeb)
        							.addComponent(lblDescripcion))
        						.addPreferredGap(ComponentPlacement.RELATED)
        						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        							.addComponent(textFieldWeb)
        							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
        					.addComponent(btnCrearAerolinea)))
        			.addGap(11))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textFieldNickname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNickname)
        				.addComponent(lblFechaNac)
        				.addComponent(spinnerDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(spinnerMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(spinnerAnio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblEmail)
        				.addComponent(lblNacionalidad)
        				.addComponent(textFieldNacionalidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblTipoDoc)
        				.addComponent(comboBoxTipoDoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textFieldContraCl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblContrasea_1))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblNroDocumento)
        						.addComponent(textFieldNroDocumento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnCrearCliente))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(textFieldApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblApellido)))
        				.addComponent(lblNombre))
        			.addGap(28)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textFieldNicknameAl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNicknameAl)
        				.addComponent(lblWeb)
        				.addComponent(textFieldWeb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(textFieldEmailAl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblEmailAl))
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(textFieldContraAl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblContrasenaAl)))
        						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(btnCrearAerolinea)
        						.addComponent(lblNombreAl)
        						.addComponent(textFieldNombreAl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(lblDescripcion)
        					.addGap(71)))
        			.addGap(72))
        );
        
        textAreaDescripcion.setLineWrap(true);
        scrollPane.setViewportView(textAreaDescripcion);
        getContentPane().setLayout(groupLayout);
	}
	private void limpiar() {
		textFieldNickname.setText("");
		textFieldEmail.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldNacionalidad.setText("");
		textFieldNroDocumento.setText("");
	}
}
