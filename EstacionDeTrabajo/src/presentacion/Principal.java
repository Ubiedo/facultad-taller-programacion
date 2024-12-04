package presentacion;
import publicar.*;
/*
 * Principal
 * 
 * Implementacion de la ventana principal.
 *
 * 31/08/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import publicar.WebServices;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
//import logica.controlador.*;
import java.util.Scanner;
import logica.*;
public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// Interfaces
	private IControladorUsuarios ICU;
	private IControladorRutas ICR;
	private IControladorVuelos ICV;
	// internal frames:
	private AltaUsuario altaUsuarioInternalFrame;
	private AltaRutaVuelo altaRutaVueloInternalFrame;
	private AltaCiudad altaCiudadInternalFrame;
	private ConsultarUsuario consultarUsuarioInternalFrame;
	private AceptarRechazarRuta aceptarRechazarRutaInternalFrame;
	private ConsultarVuelo consultarVueloInternalFrame;
	private ModificarDatosUsuario modificarDatosUsuarioInternalFrame;
	private ConsultarRutaDeVuelo consultarRutaDeVueloInternalFrame;
	private AltaVuelo altaVueloInternalFrame;
	private AgregarRutaAPaquete agregarRutaAPaqueteInternalFrame;
	private CrearPaquete crearPaqueteInternalFrame;
	private ReservarVuelo reservarVueloInternalFrame;
	private ComprarPaquete comprarPaqueteInternalFrame; 
	private ConsultarPaqueteDeRutas consultarPaqueteDeRutasInternalFrame;
	private ConsultarTop5 consultarTop5;
	//para consola
	private AltaCategoriaConConsola altaCategoriaConsola;
	private Scanner scanner;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        WebServices p = new WebServices();
			        p.publicar();
					Principal frame = new Principal();
					/*frame.setUndecorated(true);*/
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		ICU = Fabrica.getInstance().getIUsuarios();
		ICR = Fabrica.getInstance().getIRutas();
		ICV = Fabrica.getInstance().getIVuelos();
		initialize(); // se inicializa la ventana base.
		setExtendedState(JFrame.MAXIMIZED_BOTH); //pantalla completa
		// carga de InternalFrame para los casos de uso:
        altaUsuarioInternalFrame = new AltaUsuario(ICU);
        altaUsuarioInternalFrame.setVisible(false);
        
        altaRutaVueloInternalFrame = new AltaRutaVuelo(ICR, ICU);
        altaRutaVueloInternalFrame.setVisible(false);
        
        altaCiudadInternalFrame = new AltaCiudad(ICR);
        altaCiudadInternalFrame.setVisible(false);
        
        //consultarUsuarioInternalFrame = new ConsultarUsuario();
        //consultarUsuarioInternalFrame.setVisible(false);

        altaCategoriaConsola = new AltaCategoriaConConsola(ICR);

        crearPaqueteInternalFrame = new CrearPaquete(ICV);
        crearPaqueteInternalFrame.setVisible(false);
        
    
        
      //  consultarVueloInternalFrame = new ConsultarVuelo(ICU,ICR,ICV);// el ICV solo se lo voy a pasar momentaneamente para probar casos de prueba mios, antes de entregar hay que sacarlo junto con los casos
        //consultarVueloInternalFrame.setVisible(false);
        
        //consultarRutaDeVueloInternalFrame= new ConsultarRutaDeVuelo(ICU, ICR, ICV); //tambien revisar luego ICV
       // consultarRutaDeVueloInternalFrame.setVisible(false);
        
        agregarRutaAPaqueteInternalFrame = new AgregarRutaAPaquete(ICU,ICV,ICR);
        agregarRutaAPaqueteInternalFrame.setVisible(false);
        
        reservarVueloInternalFrame = new ReservarVuelo(ICU, ICR, ICV);
        reservarVueloInternalFrame.setVisible(false);
        
        consultarPaqueteDeRutasInternalFrame = new ConsultarPaqueteDeRutas(ICU, ICR, ICV); 
		consultarPaqueteDeRutasInternalFrame.setVisible(false);
        
        getContentPane().add(altaUsuarioInternalFrame);
        getContentPane().add(altaRutaVueloInternalFrame);
        getContentPane().add(altaCiudadInternalFrame);
        //getContentPane().add(consultarUsuarioInternalFrame);
        getContentPane().add(crearPaqueteInternalFrame);
      //  getContentPane().add(consultarVueloInternalFrame);
       // getContentPane().add(consultarRutaDeVueloInternalFrame);
        getContentPane().add(agregarRutaAPaqueteInternalFrame);
        getContentPane().add(reservarVueloInternalFrame);
        getContentPane().add(consultarPaqueteDeRutasInternalFrame);
	}
	
	private void initialize() {
		setTitle("Volando.uy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		/*-----------------------------------------------------------------------------------------------------CPane-----------*/
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		/*-----------------------------------------------------------------------------------------------------Menu------------*/
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setBorderPainted(false);
		setJMenuBar(menuBar);
		/*-----------------------------------------------------------------------------------------------------Sistema---------*/
		JMenu menuSistema = new JMenu("Sistema");
		menuSistema.getPopupMenu().setBorder(new LineBorder(Color.GRAY, 1));
		menuSistema.setBorderPainted(false);
		menuBar.add(menuSistema);
		
		JMenuItem menuSistemaComprarPaquete = new JMenuItem("Comprar Paquete");
	//	menuSistemaComprarPaquete.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuSistemaComprarPaquete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) { // Cargar datos al sistema
            	comprarPaqueteInternalFrame = new ComprarPaquete(ICV, ICU);
            	comprarPaqueteInternalFrame.setVisible(true);
                getContentPane().add(comprarPaqueteInternalFrame);
            }
        });
		menuSistema.add(menuSistemaComprarPaquete);
		
        JMenuItem menuAceptarRechazarRuta = new JMenuItem("Aceptar/Rechazar Ruta");
        //menuSistemaReservarVuelo.setBorder(BorderFactory.createLineBorder(mbarbg));
        menuAceptarRechazarRuta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Muestra Ventana.
                aceptarRechazarRutaInternalFrame = new AceptarRechazarRuta(ICU, ICR);
                aceptarRechazarRutaInternalFrame.setVisible(true);
                getContentPane().add(aceptarRechazarRutaInternalFrame);
            }
        });
        menuSistema.add(menuAceptarRechazarRuta);
        
		JMenuItem menuSistemaReservarVuelo = new JMenuItem("Reservar Vuelo");
		//menuSistemaReservarVuelo.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuSistemaReservarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Muestra Ventana.
                reservarVueloInternalFrame = new ReservarVuelo(ICU, ICR, ICV);
                reservarVueloInternalFrame.setVisible(true);
                getContentPane().add(reservarVueloInternalFrame);
            }
        });
		menuSistema.add(menuSistemaReservarVuelo);
		
		JSeparator separator_1 = new JSeparator();
		menuSistema.add(separator_1);
		
		JMenuItem menuSistemaCargarDatos = new JMenuItem("Cargar Datos");
		//menuSistemaCargarDatos.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuSistemaCargarDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) { // Cargar datos al sistema
				// cargar datos
            	ICR.leerDatosCategorias();
            	ICR.leerDatosCiudades();
            	ICU.leerDatosUsuarios();
            	ICR.leerDatosRutasVuelo();
            	ICV.leerDatosVuelo();
            	ICV.leerDatosPaquetes();
            	ICR.leerDatosPaquetesRutas();
            	ICV.leerDatosReservas();
            	ICV.leerDatosCompraPaquetes();
            	ICU.leerDatosCheckIn();
            	ICU.leerDatosSeguidos();
				JOptionPane.showMessageDialog(null, "Datos Cargados!");
        		menuSistemaCargarDatos.setEnabled(false); // desactivarlo para que no pueda volver a cargarse y generar problemas
            }
        });
		menuSistema.add(menuSistemaCargarDatos);
		
		JSeparator separator = new JSeparator();
		menuSistema.add(separator);
		
		JMenuItem menuSistemaSalir = new JMenuItem("Salir");
		//menuSistemaSalir.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuSistemaSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) { // Cierra la app
                setVisible(false);
                dispose();
            }
        });
		menuSistema.add(menuSistemaSalir);
		
		
		/*-----------------------------------------------------------------------------------------------------Crear-----------*/
		JMenu menuCrear = new JMenu("Crear");
		menuCrear.getPopupMenu().setBorder(new LineBorder(Color.GRAY, 1));
		menuCrear.setBorderPainted(false);
		menuBar.add(menuCrear);
		
		JMenuItem menuCrearNuevoUsuario = new JMenuItem("Nuevo Usuario");
		//menuCrearNuevoUsuario.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuCrearNuevoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Muestra Ventana
            	altaUsuarioInternalFrame.setVisible(true);
            }
        });
		menuCrear.add(menuCrearNuevoUsuario);
		
		JMenuItem menuCrearNuevaRuta = new JMenuItem("Nueva Ruta");
		//menuCrearNuevaRuta.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuCrearNuevaRuta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Muestra Ventana
                altaRutaVueloInternalFrame = new AltaRutaVuelo(ICR, ICU);
            	altaRutaVueloInternalFrame.setVisible(true);
            	getContentPane().add(altaRutaVueloInternalFrame);
            }
        });
		menuCrear.add(menuCrearNuevaRuta);
		
		JMenuItem menuCrearNuevoVuelo = new JMenuItem("Nuevo Vuelo");
		//menuCrearNuevoVuelo.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuCrearNuevoVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Muestra Ventana.
                altaVueloInternalFrame= new AltaVuelo(ICV,ICU);
                altaVueloInternalFrame.setVisible(true);
                getContentPane().add(altaVueloInternalFrame);
            }
        });
		menuCrear.add(menuCrearNuevoVuelo);
		
		JMenuItem menuCrearNuevaCiudad = new JMenuItem("Nueva Ciudad");
		//menuCrearNuevaCiudad.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuCrearNuevaCiudad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Muestra Ventana.
            	altaCiudadInternalFrame.setVisible(true);
            }
        });
		menuCrear.add(menuCrearNuevaCiudad);
		
		JMenuItem menuCrearNuevoPaquete = new JMenuItem("Nuevo Paquete");
		//menuCrearNuevoPaquete.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuCrearNuevoPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearPaqueteInternalFrame.setVisible(true);
			}
		});
		menuCrear.add(menuCrearNuevoPaquete);
		
		//cambiar a consola para Alta de categoría
		JMenuItem menuNuevaCategoria = new JMenuItem("Nueva Categoría");
		//menuNuevaCategoria.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuNuevaCategoria.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//salir de GUI e iniciar consola
				setVisible(false);
				if(scanner == null) {
					Scanner scanner = new Scanner(System.in);
					altaCategoriaConsola.setScanner(scanner);					
				}
				altaCategoriaConsola.realizarAlta();
				setVisible(true);
				}
		});
		menuCrear.add(menuNuevaCategoria);
		/*-----------------------------------------------------------------------------------------------------Editar----------*/
		JMenu menuEdit = new JMenu("Editar");
		menuEdit.getPopupMenu().setBorder(new LineBorder(Color.GRAY, 1));
		menuEdit.setBorderPainted(false);
		menuBar.add(menuEdit);
		
		JMenuItem menuEditarUsuario = new JMenuItem("Editar Usuario");
		//menuEditarUsuario.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuEditarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Muestra Ventana
                modificarDatosUsuarioInternalFrame = new ModificarDatosUsuario(ICU);
                modificarDatosUsuarioInternalFrame.setVisible(true);
                getContentPane().add(modificarDatosUsuarioInternalFrame);
            }
        });
		menuEdit.add(menuEditarUsuario);
		
		JMenuItem menuAgregarRutaPaquete = new JMenuItem("Agregar Ruta a Paquete");
		//menuAgregarRutaPaquete.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuAgregarRutaPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				agregarRutaAPaqueteInternalFrame.listarPaquetes(ICV);
				agregarRutaAPaqueteInternalFrame.setVisible(true);
			}
		});
		menuEdit.add(menuAgregarRutaPaquete);
		/*-----------------------------------------------------------------------------------------------------Ver-------------*/
		JMenu menuView = new JMenu("Ver");
		menuView.getPopupMenu().setBorder(new LineBorder(Color.GRAY, 1));
		menuView.setBorderPainted(false);
		menuBar.add(menuView);
		
		JMenuItem menuVerConsultaDeUsuario = new JMenuItem("Consulta de Usuario");
		//menuVerConsultaDeUsuario.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuVerConsultaDeUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestra Ventana
				consultarUsuarioInternalFrame = new ConsultarUsuario(ICU,ICR,ICV);
				getContentPane().add(consultarUsuarioInternalFrame);
            	consultarUsuarioInternalFrame.setVisible(true);
			}
		});
		menuView.add(menuVerConsultaDeUsuario);
		
		JMenuItem menuVerConsultaDeVuelo = new JMenuItem("Consulta de Vuelo");
		//menuVerConsultaDeVuelo.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuVerConsultaDeVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestra Ventana
				consultarVueloInternalFrame = new ConsultarVuelo(ICU,ICR,ICV);// el ICV solo se lo voy a pasar momentaneamente para probar casos de prueba mios, antes de entregar hay que sacarlo junto con los casos
				getContentPane().add(consultarVueloInternalFrame);
            	consultarVueloInternalFrame.setVisible(true);
			}
		});
		menuView.add(menuVerConsultaDeVuelo);
		
		JMenuItem menuVerConsultaDeRutaDeVuelo = new JMenuItem("Consulta de Ruta de Vuelo");
		//menuVerConsultaDeRutaDeVuelo.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuVerConsultaDeRutaDeVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestra Ventana
				consultarRutaDeVueloInternalFrame= new ConsultarRutaDeVuelo(ICU, ICR, ICV); //tambien revisar luego ICV
				getContentPane().add(consultarRutaDeVueloInternalFrame);
				consultarRutaDeVueloInternalFrame.setVisible(true);
			}
		});
		menuView.add(menuVerConsultaDeRutaDeVuelo);
		
		JMenuItem menuVerConsultaDePaqueteDeRutas = new JMenuItem("Consulta de Paquete de Rutas");
		//menuVerConsultaDePaqueteDeRutas.setBorder(BorderFactory.createLineBorder(mbarbg));
		menuVerConsultaDePaqueteDeRutas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestra Ventana
				consultarPaqueteDeRutasInternalFrame = new ConsultarPaqueteDeRutas(ICU, ICR, ICV); 
				getContentPane().add(consultarPaqueteDeRutasInternalFrame);
				consultarPaqueteDeRutasInternalFrame.setVisible(true);
			}
		});
		menuView.add(menuVerConsultaDePaqueteDeRutas);
		
		JMenuItem verTop5 = new JMenuItem("Consulta de Rutas Más Visitadas");
		verTop5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestra Ventana
				consultarTop5 = new ConsultarTop5(ICR); 
				getContentPane().add(consultarTop5);
				consultarTop5.setVisible(true);
			}
		});
		menuView.add(verTop5);
	}
}
