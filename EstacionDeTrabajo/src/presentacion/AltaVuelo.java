package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import logica.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

//import logica.controlador.*;
import javax.swing.JButton;

public class AltaVuelo extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nombreTF;
	private Object[] options = {"Aceptar","Cancelar"};

	/*
	 * Create the frame.
	 */
	public AltaVuelo(IControladorVuelos ICV, IControladorUsuarios ICU) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
		setBounds(100, 100, 260, 333);
		setTitle("Alta De Vuelo");
		
		JComboBox<String> aerolineaCB = new JComboBox<String>();
		aerolineaCB.addItem("");
		Set<String> nicknamesUsuarios = ICU.listarAerolineas();
		for (String usuario : nicknamesUsuarios) {
			aerolineaCB.addItem(usuario);
		}
		
		JComboBox<String> rutaVueloCB = new JComboBox<String>();
		rutaVueloCB.setEnabled(false);
		/*---------------------------------------------------------------------------------------------------------------------*/
		aerolineaCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(aerolineaCB.getSelectedIndex() > 0) {
					rutaVueloCB.removeAllItems();
					rutaVueloCB.setEnabled(true);
					rutaVueloCB.addItem("");
					Set<String> rutas = ICU.listarRutasDeAerolinea((String) aerolineaCB.getSelectedItem());
					for (String ruta : rutas) {
						rutaVueloCB.addItem(ruta);
					}
				}
			}
		});
		/*---------------------------------------------------------------------------------------------------------------------*/
		
		JLabel aerolineaL = new JLabel("Aerolinea:");
		
		JLabel rutaVueloL = new JLabel("Ruta de Vuelo:");
		
		JLabel nombreL = new JLabel("Nombre:");
		
		nombreTF = new JTextField();
		nombreTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Fecha:");
		Date initDate = new Date();
		Date menor = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		SpinnerDateModel dateModel = new SpinnerDateModel(initDate, menor, null, java.util.Calendar.DAY_OF_MONTH);
		JSpinner fechaJS = new JSpinner(dateModel);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(fechaJS, "dd/MM/yyyy");
		fechaJS.setEditor(editor);
		
		JLabel duracionL = new JLabel("Duracion:");
		// crear calendario hh:mm
		Calendar calendario = Calendar.getInstance();
		// definir valor inicial 00:00
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.MINUTE, 0);
		Date startTime = calendario.getTime();
		// definir maximo valor 23:59
		calendario.set(Calendar.HOUR, 23);
		calendario.set(Calendar.MINUTE, 59);
		Date endTime = calendario.getTime();
		SpinnerDateModel timeModel = new SpinnerDateModel(startTime, null, endTime, Calendar.MINUTE);
		JSpinner duracionJS = new JSpinner(timeModel);
		duracionJS.setEditor(new JSpinner.DateEditor(duracionJS, "HH:mm"));
		
		JLabel asientosL = new JLabel("Cantidad de asientos");
		
		JLabel turistaL = new JLabel("Turista:");
		
		JLabel ejecutivoL = new JLabel("Ejecutivo:");
		
		JSpinner turistaJS = new JSpinner();
		turistaJS.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		
		JSpinner ejecutivoJS = new JSpinner();
		ejecutivoJS.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		
		JButton aceptar = new JButton("Aceptar");
		
		JButton cancelar = new JButton("Cancelar");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(asientosL, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(duracionL, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
								.addComponent(nombreL, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
								.addComponent(aerolineaCB, 0, 112, Short.MAX_VALUE)
								.addComponent(aerolineaL, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
								.addComponent(nombreTF, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
								.addComponent(duracionJS, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(fechaJS, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
								.addComponent(rutaVueloCB, 0, 106, Short.MAX_VALUE)
								.addComponent(rutaVueloL, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(turistaJS, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
								.addComponent(turistaL, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
								.addComponent(aceptar, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cancelar, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
								.addComponent(ejecutivoJS, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
								.addComponent(ejecutivoL, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(aerolineaL, Alignment.TRAILING)
						.addComponent(rutaVueloL, Alignment.TRAILING))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(aerolineaCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rutaVueloCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(nombreL))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(fechaJS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nombreTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(duracionL)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(duracionJS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(asientosL)
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(ejecutivoL)
						.addComponent(turistaL))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(turistaJS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ejecutivoJS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(aceptar)
						.addComponent(cancelar))
					.addGap(32))
		);
		getContentPane().setLayout(groupLayout);
		/*---------------------------------------------------------------------------------------------------------------------*/
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Crea el vuelo si esta todo bien.
				if(aerolineaCB.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Falta seleccionar Aerolinea!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					if(rutaVueloCB.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Falta Seleccionar Ruta De Vuelo!", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						if(nombreTF.getText() == null || nombreTF.getText().trim() == "") {
							JOptionPane.showMessageDialog(null, "Nombre Del Vuelo No Declarado!", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							if((int)turistaJS.getValue() + (int)ejecutivoJS.getValue() == 0) {
								JOptionPane.showMessageDialog(null, "La Cantidad Total De Asientos Debe Ser Mayor A 0!", "Error", JOptionPane.ERROR_MESSAGE);
							} else {
								if(ICV.altaVuelo((String)rutaVueloCB.getSelectedItem(), (String)nombreTF.getText(), ((Date) fechaJS.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), ((Date) duracionJS.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalTime(), (int)turistaJS.getValue(), (int)ejecutivoJS.getValue(), LocalDate.now(),"")) {
									JOptionPane.showMessageDialog(null, "Vuelo Registrado!");
									dispose();
								} else {
									int eleccion = JOptionPane.showOptionDialog(null,
										    "Ya existe un vuelo con el nombre \""+(String)rutaVueloCB.getSelectedItem()+"\", queres cambiar el nombre o cancelar?" ,
										    "Error",
										    JOptionPane.YES_NO_OPTION,
										    JOptionPane.ERROR_MESSAGE,
										    null,     //do not use a custom Icon
										    options,  //the titles of buttons
										    options[0]); //default button title
									if(eleccion == 1) {
										dispose();
									} else {
										rutaVueloCB.setSelectedIndex(0);
									}
								}
							}
						}
					}
				}
			}
		});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rutaVueloCB.removeAllItems();
				rutaVueloCB.setEnabled(false);
				aerolineaCB.setSelectedIndex(0);
				nombreTF.setText("");
				fechaJS.setValue(initDate);
				duracionJS.setValue(startTime);
				turistaJS.setValue(0);
				ejecutivoJS.setValue(0);
			}
		});
	}
}
