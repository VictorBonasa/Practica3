package com.victor.practicahibernate.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.victor.practicahibernate.Circuitos;
import com.victor.practicahibernate.Coches;
import com.victor.practicahibernate.Equipos;
import com.victor.practicahibernate.Pilotos;
import com.victor.practicahibernate.Ruedas;
import com.victor.practicahibernate.Sponsors;
import com.victor.practicahibernate.util.HibernateUtil;
import com.victor.practicahibernate.util.Util;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Ventana {

	private JFrame frmVtmHibernate;
	private JTabbedPane tabbedPane;
	
	private JTablaCircuitos tablaCircuitos;
	private JTablaCoches tablaCoches;
	private JTablaEquipos tablaEquipos;
	private JTablaPilotos tablaPilotos;
	private JTablaRuedas tablaRuedas;
	private JTablaSponsors tablaSponsors;
	private JTextField tfBusqueda;
	 


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frmVtmHibernate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
		inicializar();
}

private void inicializar() {
	
	try {
		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSession();
	
		cargarDatos();
	} catch (HibernateException he) {
		he.printStackTrace();
	}
}

/*
 * TODO
 */
private void cargarDatos() {
	//HibernateUtil.openSession();
	
	Query queryCircuitos = HibernateUtil.getCurrentSession().createQuery("FROM Circuitos");
	List<Circuitos> circuitos = queryCircuitos.list();
	tablaCircuitos.listar(circuitos);
	
	Query queryCoches = HibernateUtil.getCurrentSession().createQuery("FROM Coches");
	List<Coches> coches = queryCoches.list();
	tablaCoches.listar(coches);
	
	Query queryEquipos = HibernateUtil.getCurrentSession().createQuery("FROM Equipos");
	List<Equipos> equipos = queryEquipos.list();
	tablaEquipos.listar(equipos);
	
	Query queryPilotos = HibernateUtil.getCurrentSession().createQuery("FROM Pilotos");
	List<Pilotos> pilotos = queryPilotos.list();
	tablaPilotos.listar(pilotos);
	
	Query queryRuedas = HibernateUtil.getCurrentSession().createQuery("FROM Ruedas");
	List<Ruedas> ruedas = queryRuedas.list();
	tablaRuedas.listar(ruedas);
	
	Query querySponsors = HibernateUtil.getCurrentSession().createQuery("FROM Sponsors");
	List<Sponsors> sponsors = querySponsors.list();
	tablaSponsors.listar(sponsors);
	
	//HibernateUtil.closeSessionFactory();
	
	
}

private void alta() {
	
	switch (tabbedPane.getSelectedIndex()) {
		case 0:
			altaCircuito();
			break;
		case 1:
			altaCoche();
			break;
		case 2:
			altaEquipo();
			break;
		case 3:
			altaPiloto();
			break;
		case 4:
			altaRueda();
			break;
		case 5:
			altaSponsor();
			break;
		default:
	}
}

private void modificar() {
	
	switch (tabbedPane.getSelectedIndex()) {
		case 0:
			modificarCircuito();
			break;
		case 1:
			modificarCoche();
			break;
		case 2:
			modificarEquipo();
			break;
		case 3:
			modificarPiloto();
			break;
		case 4:
			modificarRueda();
			break;
		case 5:
			modificarSponsor();
			break;
		default:
	}
}

private void eliminar() {
	
	switch (tabbedPane.getSelectedIndex()) {
		case 0:
			eliminarCircuito();
			break;
		case 1:
			eliminarCoche();
			break;
		case 2:
			eliminarEquipo();
			break;
		case 3:
			eliminarPiloto();
			break;
		case 4:
			eliminarRueda();
			break;
		case 5:
			eliminarSponsor();
			break;
		default:
	}
}

private void altaCircuito() {
	
	JCircuitos jCirtuitos = new JCircuitos();
	if (jCirtuitos.mostrarDialogo() == Util.Accion.CANCELAR)
		return;
	
	Circuitos circuito = jCirtuitos.getCircuito();
	Session sesion = HibernateUtil.getCurrentSession();
	sesion.beginTransaction();
	sesion.save(circuito);
	sesion.getTransaction().commit();
	sesion.close();
	
	cargarDatos();
}

private void altaCoche(){
	
	JCoches jCoches = new JCoches();
	if (jCoches.mostrarDialogo() == Util.Accion.CANCELAR)
		return;
	
	Coches coche = jCoches.getCoche();
	Session sesion = HibernateUtil.getCurrentSession();
	sesion.beginTransaction();
	sesion.save(coche);
	sesion.getTransaction().commit();
	sesion.close();
	
	cargarDatos();
	
}
private void altaEquipo(){
	
	JEquipos jEquipos = new JEquipos();
	
	if (jEquipos.mostrarDialogo() == Util.Accion.CANCELAR)
		return;
	
	Equipos equipo = jEquipos.getEquipo();
	Session sesion = HibernateUtil.getCurrentSession();
	sesion.beginTransaction();
	sesion.save(equipo);
	sesion.getTransaction().commit();
	sesion.close();
	
	cargarDatos();
	
}

private void altaPiloto(){
	
	JPilotos jPilotos = new JPilotos();
	if (jPilotos.mostrarDialogo() == Util.Accion.CANCELAR)
		return;
	
	Pilotos piloto = jPilotos.getPiloto();
	Session sesion = HibernateUtil.getCurrentSession();
	sesion.beginTransaction();
	sesion.save(piloto);
	sesion.getTransaction().commit();
	sesion.close();
	
	cargarDatos();
	
}

private void altaRueda(){
	
	JRuedas jRuedas = new JRuedas();
	if (jRuedas.mostrarDialogo() == Util.Accion.CANCELAR)
		return;
	
	Ruedas rueda = jRuedas.getRueda();
	Session sesion = HibernateUtil.getCurrentSession();
	sesion.beginTransaction();
	sesion.save(rueda);
	sesion.getTransaction().commit();
	sesion.close();
	
	cargarDatos();
	
}

private void altaSponsor(){
	
	JSponsors jSponsors = new JSponsors();
	if (jSponsors.mostrarDialogo() == Util.Accion.CANCELAR)
		return;
	
	Sponsors sponsor = jSponsors.getSponsor();
	Session sesion = HibernateUtil.getCurrentSession();
	sesion.beginTransaction();
	sesion.save(sponsor);
	sesion.getTransaction().commit();
	sesion.close();
	
	cargarDatos();
}

private void modificarCircuito(){
	
	Circuitos circuito = new Circuitos();
	
	circuito = tablaCircuitos.getCircuitoSeleccionado();
	
	JCircuitos jCirtuitos = new JCircuitos();
	jCirtuitos.setCircuito(circuito);
	
	if (jCirtuitos.mostrarDialogo() == Util.Accion.CANCELAR)
		return;
	
	circuito = jCirtuitos.getCircuito();
	Session sesion = HibernateUtil.getCurrentSession();
	sesion.beginTransaction();
	sesion.save(circuito);
	sesion.getTransaction().commit();
	sesion.close();
	
	cargarDatos();
}

private void modificarCoche(){
	
	Coches coche = new Coches();
	
	coche = tablaCoches.getCocheSeleccionado();
	
	JCoches jCoches = new JCoches();
	jCoches.setCoche(coche);
	
	if(jCoches.mostrarDialogo() == Util.Accion.CANCELAR)
		return;
	
	coche = jCoches.getCoche();
	Session sesion = HibernateUtil.getCurrentSession();
	sesion.beginTransaction();
	sesion.save(coche);
	sesion.getTransaction().commit();
	sesion.close();
	
	cargarDatos();
	
}

private void modificarEquipo(){
	
	Equipos equipo = new Equipos();
	
	equipo = tablaEquipos.getEquipoSeleccionado();
	
	JEquipos jEquipos = new JEquipos();
	jEquipos.setEquipo(equipo);
	
	if(jEquipos.mostrarDialogo() == Util.Accion.CANCELAR)
		return;
	
	equipo = jEquipos.getEquipo();
	Session sesion = HibernateUtil.getCurrentSession();
	sesion.beginTransaction();
	sesion.save(equipo);
	sesion.getTransaction().commit();
	sesion.close();
	
	cargarDatos();
	
}

private void modificarPiloto(){
	
	Pilotos piloto = new Pilotos();
	
	piloto = tablaPilotos.getPilotoSeleccionado();
	
	JPilotos jPilotos = new JPilotos();
	jPilotos.setPiloto(piloto);
	
	if(jPilotos.mostrarDialogo() == Util.Accion.CANCELAR)
		return;
	
	piloto = jPilotos.getPiloto();
	Session sesion = HibernateUtil.getCurrentSession();
	sesion.beginTransaction();
	sesion.save(piloto);
	sesion.getTransaction().commit();
	sesion.close();
	
	cargarDatos();
	
}

private void modificarRueda(){
	
	Ruedas rueda = new Ruedas();
	
	rueda = tablaRuedas.getRuedaSeleccionado();
	
	JRuedas jRuedas = new JRuedas();
	jRuedas.setRueda(rueda);
	
	if(jRuedas.mostrarDialogo() == Util.Accion.CANCELAR)
		return;
	
	rueda = jRuedas.getRueda();
	Session sesion = HibernateUtil.getCurrentSession();
	sesion.beginTransaction();
	sesion.save(rueda);
	sesion.getTransaction().commit();
	sesion.close();
	
	cargarDatos();
	
}

private void modificarSponsor(){
	
	Sponsors sponsor = new Sponsors();
	
	sponsor = tablaSponsors.getSponsorSeleccionado();
			
	JSponsors jSponsor = new JSponsors();
	jSponsor.setSponsor(sponsor);
	
	if(jSponsor.mostrarDialogo() == Util.Accion.CANCELAR)
		return;
	
	sponsor = jSponsor.getSponsor();
	Session sesion = HibernateUtil.getCurrentSession();
	sesion.beginTransaction();
	sesion.save(sponsor);
	sesion.getTransaction().commit();
	sesion.close();
	
	cargarDatos();
	
}


private void eliminarCircuito(){
	
	Circuitos circuito = tablaCircuitos.getCircuitoSeleccionado();
	if (circuito == null)
		return;
	
	Session session = null;
	try {
		session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.delete(circuito);
		session.getTransaction().commit();
		session.close();
	} catch (Exception ex) {
		ex.printStackTrace();
		session.close();
	}
	
	cargarDatos();
	
}

private void eliminarCoche(){
	
	Coches coche = tablaCoches.getCocheSeleccionado();
	if (coche == null)
		return;

	Session session = null;
	try {
		session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.delete(coche);
		session.getTransaction().commit();
		session.close();
	} catch (Exception ex) {
		ex.printStackTrace();
		session.close();
	}
	
	cargarDatos();
	
}

private void eliminarEquipo(){
	
	Equipos equipo = tablaEquipos.getEquipoSeleccionado();
	if (equipo == null)
		return;

	Session session = null;
	try {
		session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.delete(equipo);
		session.getTransaction().commit();
		session.close();
	} catch (Exception ex) {
		ex.printStackTrace();
		session.close();
	}
	
	cargarDatos();
	
}

private void eliminarPiloto(){
	
	Pilotos piloto = tablaPilotos.getPilotoSeleccionado();
	if (piloto == null)
		return;

	Session session = null;
	try {
		session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.delete(piloto);
		session.getTransaction().commit();
		session.close();
	} catch (Exception ex) {
		ex.printStackTrace();
		session.close();
	}
	
	cargarDatos();
	
}

private void eliminarRueda(){
	
	Ruedas rueda = tablaRuedas.getRuedaSeleccionado();
	if (rueda == null)
		return;

	Session session = null;
	try {
		session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.delete(rueda);
		session.getTransaction().commit();
		session.close();
	} catch (Exception ex) {
		ex.printStackTrace();
		session.close();
	}
	
	cargarDatos();
	
}

private void eliminarSponsor(){
	
	Sponsors sponsor = tablaSponsors.getSponsorSeleccionado();
	if (sponsor == null)
		return;

	Session session = null;
	try {
		session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.delete(sponsor);
		session.getTransaction().commit();
		session.close();
	} catch (Exception ex) {
		ex.printStackTrace();
		session.close();
	}
	
	cargarDatos();
	
}

private void buscar() {
	
	if (tfBusqueda.getText().length() == 0) {
		cargarDatos();
		return;
	}
	
	if (tfBusqueda.getText().length() < 2) 
		return;
	
	String filtro = tfBusqueda.getText();
	tablaCircuitos.listar(filtro);
	tablaCoches.listar(filtro);
	tablaEquipos.listar(filtro);
	tablaPilotos.listar(filtro);
	tablaRuedas.listar(filtro);
	tablaSponsors.listar(filtro);
}

private void limpiarBusqueda(){
	
	tfBusqueda.setText("");
	cargarDatos();
	
}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVtmHibernate = new JFrame();
		frmVtmHibernate.setTitle("VTM Hibernate");
		frmVtmHibernate.setBounds(100, 100, 580, 352);
		frmVtmHibernate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("New label");
		frmVtmHibernate.getContentPane().add(lblNewLabel, BorderLayout.SOUTH);
		
		JToolBar toolBar = new JToolBar();
		frmVtmHibernate.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btAadir = new JButton("A\u00F1adir");
		btAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alta();
			}
		});
		toolBar.add(btAadir);
		
		JButton btModificar = new JButton("Modificar");
		btModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		toolBar.add(btModificar);
		
		JButton btEliminar = new JButton("Eliminar");
		btEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		toolBar.add(btEliminar);
		
		JButton btGuardar = new JButton("Guardar");
		toolBar.add(btGuardar);
		
		JLabel lblBsqueda = new JLabel("B\u00FAsqueda Avanzada:");
		toolBar.add(lblBsqueda);
		
		tfBusqueda = new JTextField();
		tfBusqueda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				buscar();
			}
		});
		toolBar.add(tfBusqueda);
		tfBusqueda.setColumns(10);
		
		JButton btX = new JButton("X");
		btX.setPreferredSize(new Dimension(50,0));
		btX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarBusqueda();
				//TODO
			}
		});
		toolBar.add(btX);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmVtmHibernate.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Circuitos", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tablaCircuitos = new JTablaCircuitos();
		scrollPane.setViewportView(tablaCircuitos);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Coches", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		tablaCoches = new JTablaCoches();
		scrollPane_1.setViewportView(tablaCoches);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Equipos", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2, BorderLayout.CENTER);
		
		tablaEquipos = new JTablaEquipos();
		scrollPane_2.setViewportView(tablaEquipos);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Pilotos", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panel_3.add(scrollPane_3, BorderLayout.CENTER);
		
		tablaPilotos = new JTablaPilotos();
		scrollPane_3.setViewportView(tablaPilotos);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Ruedas", null, panel_4, null);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		panel_4.add(scrollPane_4, BorderLayout.CENTER);
		
		tablaRuedas = new JTablaRuedas();
		scrollPane_4.setViewportView(tablaRuedas);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Sponsors", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_5 = new JScrollPane();
		panel_5.add(scrollPane_5, BorderLayout.CENTER);
		
		tablaSponsors = new JTablaSponsors();
		scrollPane_5.setViewportView(tablaSponsors);
		
		JMenuBar menuBar = new JMenuBar();
		frmVtmHibernate.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
	}

}
