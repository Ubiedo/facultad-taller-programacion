package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Set;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import logica.*;
//import logica.controlador.*;

public class ComprarPaquete extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private Object[] options = {"Aceptar","Cancelar"};

	/*
	 * Create the frame.
	 */
	public ComprarPaquete(IControladorVuelos ICV, IControladorUsuarios ICU) {
		setClosable(true);
		setTitle("Compra De Paquete");
		setBounds(100, 100, 350, 160);
		
		JLabel paqueteSelectorL = new JLabel("Seleccionar Paquete");
		
		JComboBox<String> paqueteSelectorCB = new JComboBox<String>();
		paqueteSelectorCB.addItem("");
		Set<String> nombrePaquete = ICV.listarPaquetesConRutas();
		for (String paqute : nombrePaquete) {
			paqueteSelectorCB.addItem(paqute);
		}
		
		JLabel lblNewLabel = new JLabel("Seleccionar Cliente");
		
		JComboBox<String> clienteSelector = new JComboBox<String>();
		clienteSelector.addItem("");
		Set<String> nicknameCliente = ICU.listarClientes();
		for (String cliente : nicknameCliente) {
			clienteSelector.addItem(cliente);
		}
		clienteSelector.setEnabled(false);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(paqueteSelectorCB, 0, 186, Short.MAX_VALUE)
						.addComponent(paqueteSelectorL, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
						.addComponent(clienteSelector, 0, 186, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(paqueteSelectorL)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(paqueteSelectorCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(clienteSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(164, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		/*---------------------------------------------------------------------------------------------------------------------*/
		paqueteSelectorCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(paqueteSelectorCB.getSelectedIndex() == 0) {
					clienteSelector.setEnabled(false);
				} else {
					clienteSelector.setEnabled(true);
				}
				clienteSelector.setSelectedIndex(0);
			}
		});
		clienteSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clienteSelector.getSelectedIndex() > 0) {
					LocalDate realizado = LocalDate.now();
					if(ICV.comprarPaquete((String)paqueteSelectorCB.getSelectedItem(), (String)clienteSelector.getSelectedItem(), realizado, ICV.getCostoPaquete((String)paqueteSelectorCB.getSelectedItem()), ICV.calcularVencimientoCompra(realizado, (String)paqueteSelectorCB.getSelectedItem()))) {
						JOptionPane.showMessageDialog(null, "Compra Registrado!");
					} else {
						int eleccion = JOptionPane.showOptionDialog(null,
							    "El cliente \""+(String)clienteSelector.getSelectedItem()+"\", ya tiene una copmra relizada del paquete \""+(String)paqueteSelectorCB.getSelectedItem()+"\". Deseas intentar con otro cliente?" ,
							    "Error",
							    JOptionPane.YES_NO_OPTION,
							    JOptionPane.ERROR_MESSAGE,
							    null,     //do not use a custom Icon
							    options,  //the titles of buttons
							    options[0]); //default button title
						if(eleccion == 1) {
							dispose();
						}
					}
				}
				clienteSelector.setSelectedIndex(0);
			}
		});
	}

}
