package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/*import logica.controlador.IControladorRutas;
import logica.controlador.IControladorUsuarios;
import logica.datatype.DataAerolinea;
import logica.datatype.DataCliente;
import logica.datatype.DataClientePaquete;
import logica.datatype.DataClienteVuelo;
import logica.datatype.DataRuta;
import logica.enumerado.EstadoRuta;*/
import logica.*;
import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;

public class AceptarRechazarRuta extends JInternalFrame  {
    private String lastAerolinea = "";
    JComboBox listaRutas;
    public AceptarRechazarRuta(IControladorUsuarios ICU, IControladorRutas ICR) {
        setClosable(true);
        
        JLabel lblNewLabel = new JLabel("Elegir una Aerolinea:");
        JComboBox listaAerolineas = new JComboBox();

        JComboBox listaRutas = new JComboBox();
        
        Set<String> aerolineas = ICU.listarAerolineas();
        lastAerolinea = (String) listaAerolineas.getSelectedItem();
        for (String a : aerolineas) {
            listaAerolineas.addItem(a);
            
        }
        
        
        listaAerolineas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String aerolinea = (String) listaAerolineas.getSelectedItem();
                if ((aerolinea != lastAerolinea)) {
                    lastAerolinea = aerolinea;
                    //limpiarTexto(scrollPaneTexto);
                    listaRutas.removeAllItems();
                    Set<DataRuta> rutas = ICU.infoRutasDeAerolinea(aerolinea);;
                    for (DataRuta dr : rutas) {
                        if(dr.getEstado() == EstadoRuta.Ingresada) {
                            listaRutas.addItem(dr.getNombre());
                        }
                    }
                    setVisible(false);
                    setVisible(true);
                }
            }
        });
        JButton btnAceptar = new JButton("CONFIRMAR");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listaRutas.getItemCount() != 0) {
                    String ruta = (String) listaRutas.getSelectedItem();
                    ICR.confirmarRuta(ruta);
                    JOptionPane.showMessageDialog(null, "Ruta Confirmada!");
                    listaRutas.removeItem(ruta);
                    setVisible(false);
                    setVisible(true);
                }
                
               
            }
        });
        JButton btnRechazar = new JButton("RECHAZAR");
        btnRechazar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listaRutas.getItemCount() != 0) {
                    String ruta = (String) listaRutas.getSelectedItem();
                    ICR.rechazarRuta(ruta);
                    JOptionPane.showMessageDialog(null, "Ruta Rechazada!");
                    listaRutas.removeItem(ruta);
                    setVisible(false);
                    setVisible(true);
                }
                
               
            }
        }); 
        
       
        
        JLabel lblRutaDeVuelo = new JLabel("Elegir una Ruta de Vuelo:");
        
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(206, 206, 206));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                        .addComponent(listaAerolineas, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRutaDeVuelo, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                        .addComponent(listaRutas, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                    .addGap(21)
                    .addComponent(panelBotones, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(64, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panelBotones, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblNewLabel)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(listaAerolineas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(lblRutaDeVuelo)
                            .addGap(11)
                            .addComponent(listaRutas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(33, Short.MAX_VALUE))
        );
        
        
        
        
        GroupLayout gl_panelBotones = new GroupLayout(panelBotones);
        gl_panelBotones.setHorizontalGroup(
            gl_panelBotones.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelBotones.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelBotones.createParallelGroup(Alignment.LEADING)
                        .addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRechazar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(13, Short.MAX_VALUE))
        );
        gl_panelBotones.setVerticalGroup(
            gl_panelBotones.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelBotones.createSequentialGroup()
                    .addGap(24)
                    .addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(btnRechazar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(46, Short.MAX_VALUE))
        );
        panelBotones.setLayout(gl_panelBotones);
        getContentPane().setLayout(groupLayout);
    }
}
