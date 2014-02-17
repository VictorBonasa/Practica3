package com.victor.practicahibernate.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

import org.hibernate.Query;

import com.victor.practicahibernate.Circuitos;
import com.victor.practicahibernate.Coches;
import com.victor.practicahibernate.Equipos;
import com.victor.practicahibernate.Pilotos;
import com.victor.practicahibernate.util.HibernateUtil;
import com.victor.practicahibernate.util.Util;
import com.victor.practicahibernate.util.Util.Accion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPilotos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfNacionalidad;
	private JTextField tfDorsal;
	
	private JList<Equipos> listEquipo;
	private JList<Coches> listCoches;
	private JList<Circuitos> listCircuitos;
	
	private String nombre;
	private String nacionalidad;
	private int dorsal;
	private Equipos equipoSeleccionado;
	private List<Coches> cochesSeleccionados;
	private List<Circuitos> circuitosSeleccionados;
	
	DefaultListModel <Equipos> modeloListaEquipos;
	DefaultListModel <Coches> modeloListaCoches;
	DefaultListModel <Circuitos> modeloListaCircuitos;
	
	private Util.Accion accion;
	
	
	public Accion mostrarDialogo() {
		setVisible(true);
		
		return accion;
	}
	
	private void inicializar (){
		
		modeloListaEquipos = new DefaultListModel <Equipos>();
		listEquipo.setModel(modeloListaEquipos);
		modeloListaCoches =  new DefaultListModel <Coches>();
		listCoches.setModel(modeloListaCoches);
		modeloListaCircuitos = new DefaultListModel <Circuitos>();
		listCircuitos.setModel(modeloListaCircuitos);
		/*
		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSession();
		*/
		cargarEquipos();
		cargarCoches();
		cargarCircuitos();
	}
	
	private void cargarEquipos(){
		HibernateUtil.openSession();
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Equipos");
		List<Equipos> equipos = query.list();
		
		for(Equipos equipo: equipos){
			modeloListaEquipos.addElement(equipo);
		}
		HibernateUtil.closeSessionFactory();
	}
	
	private void cargarCoches(){	
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Coches");
		List<Coches> coches = query.list();
		
		for(Coches coche: coches){
			modeloListaCoches.addElement(coche);
		}
	}
	
	private void cargarCircuitos(){	
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Sponsors");
		List<Circuitos> circuitos = query.list();
		
		for(Circuitos circuito: circuitos){
			modeloListaCircuitos.addElement(circuito);
		}
	}
	
	public Pilotos getPiloto() {
		Pilotos piloto = new Pilotos();
		
		piloto.setNombre(nombre);
		piloto.setNacionalidad(nacionalidad);
		piloto.setDorsal(dorsal);
		piloto.setEquipos(equipoSeleccionado);
		piloto.setCocheses(new HashSet<Coches>(cochesSeleccionados));
		piloto.setCircuitoses(new HashSet<Circuitos>(circuitosSeleccionados));
		
		return piloto;
	}
	
public void setPiloto(Pilotos piloto){
		
		tfNombre.setText(piloto.getNombre());
		tfNacionalidad.setText(piloto.getNacionalidad());
		tfDorsal.setText(String.valueOf(piloto.getDorsal()));
		
		Query queryEquipos = HibernateUtil.getCurrentSession().createQuery("FROM Equipos");
		List<Equipos> equipos = queryEquipos.list();
		
		for(Equipos equipo:equipos){
			modeloListaEquipos.addElement(equipo);
			if(piloto.getEquipos().equals(equipo))
				listEquipo.setSelectedValue(equipo, false);
		}
		
		Query queryCoches = HibernateUtil.getCurrentSession().createQuery("FROM Coches");
		List<Coches> coches = queryCoches.list();
		
		for(Coches coche:coches){
			modeloListaCoches.addElement(coche);
			if(piloto.getCocheses().contains(coche))
				listCoches.setSelectedValue(coche, false);
		}
		
		Query queryCircuitos = HibernateUtil.getCurrentSession().createQuery("FROM Circuitos");
		List<Circuitos> circuitos = queryCircuitos.list();
		
		for(Circuitos circuito:circuitos){
			modeloListaCircuitos.addElement(circuito);
			if(piloto.getCircuitoses().contains(circuito))
				listCircuitos.setSelectedValue(circuito, false);
		}
	}
	
	private void aceptar(){
		
		if(tfNombre.getText().equals(""))
			return;
		
		if(tfNacionalidad.getText().equals(""))
			return;
		
		if(tfDorsal.getText().equals(""))
			return;
		
		nombre = tfNombre.getText();
		nacionalidad = tfNacionalidad.getText();
		dorsal = Integer.parseInt(tfDorsal.getText());
		equipoSeleccionado = listEquipo.getSelectedValue();
		cochesSeleccionados = listCoches.getSelectedValuesList();
		circuitosSeleccionados = listCircuitos.getSelectedValuesList();
		
		accion = Util.Accion.ACEPTAR;
		setVisible(false);
		
	}
	
	private void cancelar(){
		
		accion = Util.Accion.CANCELAR;
		setVisible(false);
		
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JPilotos dialog = new JPilotos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JPilotos() {
		setBounds(100, 100, 532, 374);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nombre");
			lblNewLabel.setBounds(10, 11, 46, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNacionalidad = new JLabel("Nacionalidad");
			lblNacionalidad.setBounds(181, 11, 91, 14);
			contentPanel.add(lblNacionalidad);
		}
		{
			JLabel lblDorsal = new JLabel("Dorsal");
			lblDorsal.setBounds(375, 11, 46, 14);
			contentPanel.add(lblDorsal);
		}
		{
			tfNombre = new JTextField();
			tfNombre.setBounds(64, 8, 86, 20);
			contentPanel.add(tfNombre);
			tfNombre.setColumns(10);
		}
		{
			tfNacionalidad = new JTextField();
			tfNacionalidad.setBounds(257, 8, 86, 20);
			contentPanel.add(tfNacionalidad);
			tfNacionalidad.setColumns(10);
		}
		{
			tfDorsal = new JTextField();
			tfDorsal.setBounds(420, 8, 86, 20);
			contentPanel.add(tfDorsal);
			tfDorsal.setColumns(10);
		}
		{
			JLabel lblEquipo = new JLabel("Equipo");
			lblEquipo.setBounds(60, 56, 62, 14);
			contentPanel.add(lblEquipo);
		}
		{
			JLabel lblCoches = new JLabel("Coches");
			lblCoches.setBounds(240, 56, 46, 14);
			contentPanel.add(lblCoches);
		}
		{
			JLabel lblCircuitos = new JLabel("Circuitos");
			lblCircuitos.setBounds(388, 56, 46, 14);
			contentPanel.add(lblCircuitos);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(28, 81, 135, 199);
			contentPanel.add(scrollPane);
			{
				listEquipo = new JList();
				scrollPane.setViewportView(listEquipo);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(192, 81, 135, 199);
			contentPanel.add(scrollPane);
			{
				listCoches = new JList();
				scrollPane.setViewportView(listCoches);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(357, 81, 135, 199);
			contentPanel.add(scrollPane);
			{
				listCircuitos = new JList();
				scrollPane.setViewportView(listCircuitos);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aceptar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		inicializar();
	}

}
