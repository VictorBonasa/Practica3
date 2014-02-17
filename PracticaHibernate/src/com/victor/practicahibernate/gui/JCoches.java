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

import com.victor.practicahibernate.Coches;
import com.victor.practicahibernate.Equipos;
import com.victor.practicahibernate.Pilotos;
import com.victor.practicahibernate.Ruedas;
import com.victor.practicahibernate.util.HibernateUtil;
import com.victor.practicahibernate.util.Util;
import com.victor.practicahibernate.util.Util.Accion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JCoches extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfColor;
	private JTextField tfPeso;
	
	private String nombre;
	private String color;
	private int peso;
	
	JList<Pilotos> listPilotos;
	JList<Equipos> listEquipos;
	JList<Ruedas> listRuedas;
	
	DefaultListModel<Pilotos> modeloListaPilotos;
	DefaultListModel<Equipos> modeloListaEquipos;
	DefaultListModel<Ruedas> modeloListaRuedas;
	
	private Pilotos pilotoSeleccionado;
	private Equipos equipoSeleccionado;
	private List<Ruedas> ruedasSeleccionadas;
	
	private Util.Accion accion;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JCoches dialog = new JCoches();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Accion mostrarDialogo() {
		setVisible(true);
		
		return accion;
	}
	
	private void inicializar(){
		
		modeloListaPilotos = new DefaultListModel<Pilotos>();
		listPilotos.setModel(modeloListaPilotos);
		modeloListaEquipos = new DefaultListModel<Equipos>();
		listEquipos.setModel(modeloListaEquipos);
		modeloListaRuedas = new DefaultListModel<Ruedas>();
		listRuedas.setModel(modeloListaRuedas);
		
		cargarPilotos();
		cargarEquipos();
		cargarRuedas();
		
	}
	
	private void cargarPilotos(){	
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Pilotos");
		List<Pilotos> pilotos = query.list();
		
		for(Pilotos piloto: pilotos){
			modeloListaPilotos.addElement(piloto);
		}
	}
	
	private void cargarEquipos(){
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Equipos");
		List<Equipos> equipos = query.list();
		
		for(Equipos equipo: equipos){
			modeloListaEquipos.addElement(equipo);
		}
	}
	
	private void cargarRuedas(){
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Ruedas");
		List<Ruedas> ruedas = query.list();
		
		for(Ruedas rueda: ruedas){
			modeloListaRuedas.addElement(rueda);
		}
	}
	
	public Coches getCoche(){
		
		Coches coche = new Coches();
		coche.setNombre(nombre);
		coche.setColor(color);
		coche.setPeso(peso);
		coche.setEquipos(equipoSeleccionado);
		coche.setPilotos(pilotoSeleccionado);
		coche.setRuedases(new HashSet<Ruedas>(ruedasSeleccionadas));
		
		return coche;
	}
	
	public void setCoche(Coches coche){
		
		tfNombre.setText(coche.getNombre());
		tfColor.setText(coche.getColor());
		tfPeso.setText(String.valueOf(coche.getPeso()));
		
		Query queryPilotos = HibernateUtil.getCurrentSession().createQuery("FROM Pilotos");
		List<Pilotos> pilotos = queryPilotos.list();
	
		for (Pilotos piloto : pilotos) {
			modeloListaPilotos.addElement(piloto);
			if(coche.getPilotos().equals(piloto))
				listPilotos.setSelectedValue(piloto, false);
		}
		
		Query queryEquipos = HibernateUtil.getCurrentSession().createQuery("FROM Equipos");
		List<Equipos> equipos = queryEquipos.list();
		
		for(Equipos equipo:equipos){
			modeloListaEquipos.addElement(equipo);
			if(coche.getEquipos().equals(equipo))
				listEquipos.setSelectedValue(equipo, false);
		}
		
		Query queryRuedas = HibernateUtil.getCurrentSession().createQuery("FROM Ruedas");
		List<Ruedas> ruedas = queryRuedas.list();
		
		for(Ruedas rueda:ruedas){
			modeloListaRuedas.addElement(rueda);
			if(coche.getRuedases().contains(rueda))
				listRuedas.setSelectedValue(rueda, false);
		}
	}
	
	private void aceptar(){
		if(tfNombre.getText().equals(""))
			return;
		 
		if(tfColor.getText().equals(""))
			return;
		 
		if(tfPeso.getText().equals(""))
			return;
		 
		nombre = tfNombre.getText();
		color = tfColor.getText();
		peso = Integer.parseInt(tfPeso.getText());
		pilotoSeleccionado = listPilotos.getSelectedValue();
		equipoSeleccionado = listEquipos.getSelectedValue();
		ruedasSeleccionadas = listRuedas.getSelectedValuesList();
		
		accion = Util.Accion.ACEPTAR;
		setVisible(false);
		 
	 }
	 
	 private void cancelar(){
		 accion = Util.Accion.CANCELAR;
		 setVisible(false);
	 }

	/**
	 * Create the dialog.
	 */
	public JCoches() {
		setBounds(100, 100, 527, 335);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nombre");
			lblNewLabel.setBounds(20, 24, 46, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Color");
			lblNewLabel_1.setBounds(176, 24, 46, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblPeso = new JLabel("Peso");
			lblPeso.setBounds(318, 24, 46, 14);
			contentPanel.add(lblPeso);
		}
		
		tfNombre = new JTextField();
		tfNombre.setBounds(65, 21, 86, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfColor = new JTextField();
		tfColor.setBounds(210, 21, 86, 20);
		contentPanel.add(tfColor);
		tfColor.setColumns(10);
		
		tfPeso = new JTextField();
		tfPeso.setBounds(348, 21, 86, 20);
		contentPanel.add(tfPeso);
		tfPeso.setColumns(10);
		
		JLabel lblPiloto = new JLabel("Piloto");
		lblPiloto.setBounds(20, 80, 46, 14);
		contentPanel.add(lblPiloto);
		
		JLabel lblEquipo = new JLabel("Equipo");
		lblEquipo.setBounds(210, 80, 46, 14);
		contentPanel.add(lblEquipo);
		
		JLabel lblRuedas = new JLabel("Ruedas");
		lblRuedas.setBounds(348, 80, 46, 14);
		contentPanel.add(lblRuedas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 105, 150, 148);
		contentPanel.add(scrollPane);
		
		listPilotos = new JList();
		scrollPane.setViewportView(listPilotos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(201, 105, 133, 148);
		contentPanel.add(scrollPane_1);
		
		listEquipos = new JList();
		scrollPane_1.setViewportView(listEquipos);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(362, 105, 139, 148);
		contentPanel.add(scrollPane_2);
		
		listRuedas = new JList();
		scrollPane_2.setViewportView(listRuedas);
		inicializar();
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
	}
}
