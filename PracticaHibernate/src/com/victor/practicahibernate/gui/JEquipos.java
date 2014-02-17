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
import com.victor.practicahibernate.Sponsors;
import com.victor.practicahibernate.util.HibernateUtil;
import com.victor.practicahibernate.util.Util;
import com.victor.practicahibernate.util.Util.Accion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JEquipos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfFundador;
	private JTextField tfPresidente;
	private JLabel lblPilotos;
	private JLabel lblCoches;
	private JLabel lblSponsors;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JList <Pilotos> listPilotos;
	private JList <Coches> listCoches;
	private JList <Sponsors> listSponsors;
	
	private String nombre;
	private String fundador;
	private String presidente;
	
	private List <Pilotos> pilotosSeleccionados;
	private List <Coches> cochesSeleccionados;
	private List <Sponsors> sponsorsSeleccionados;
	
	DefaultListModel <Pilotos> modeloListaPilotos;
	DefaultListModel <Coches> modeloListaCoches;
	DefaultListModel <Sponsors> modeloListaSponsors;
	
	private Util.Accion accion;
	
	public Accion mostrarDialogo() {
		setVisible(true);
		
		return accion;
	}
	
	private void inicializar (){
		
		modeloListaPilotos = new DefaultListModel <Pilotos>();
		listPilotos.setModel(modeloListaPilotos);
		modeloListaCoches =  new DefaultListModel <Coches>();
		listCoches.setModel(modeloListaCoches);
		modeloListaSponsors = new DefaultListModel <Sponsors>();
		listSponsors.setModel(modeloListaSponsors);
		
		cargarPilotos();
		cargarCoches();
		cargarSponsors();
	}
	
	private void cargarPilotos(){	
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Pilotos");
		List<Pilotos> pilotos = query.list();
		
		for(Pilotos piloto: pilotos){
			modeloListaPilotos.addElement(piloto);
		}
	}
	
	private void cargarCoches(){	
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Coches");
		List<Coches> coches = query.list();
		
		for(Coches coche: coches){
			modeloListaCoches.addElement(coche);
		}
	}
	
	private void cargarSponsors(){	
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Sponsors");
		List<Sponsors> sponsors = query.list();
		
		for(Sponsors sponsor: sponsors){
			modeloListaSponsors.addElement(sponsor);
		}
	}
	
	public Equipos getEquipo() {
		Equipos equipo = new Equipos();
		
		equipo.setNombre(nombre);
		equipo.setFundador(fundador);
		equipo.setPresidente(presidente);
		equipo.setPilotoses(new HashSet<Pilotos>(pilotosSeleccionados));
		equipo.setCocheses(new HashSet<Coches>(cochesSeleccionados));
		equipo.setSponsorses(new HashSet<Sponsors>(sponsorsSeleccionados));
		
		return equipo;
	}
	
	public void setEquipo(Equipos equipo){
		
		tfNombre.setText(equipo.getNombre());
		tfFundador.setText(equipo.getFundador());
		tfPresidente.setText(String.valueOf(equipo.getPresidente()));
		
		Query queryPilotos = HibernateUtil.getCurrentSession().createQuery("FROM Pilotos");
		List<Pilotos> pilotos = queryPilotos.list();
	
		for (Pilotos piloto : pilotos) {
			modeloListaPilotos.addElement(piloto);
			if(equipo.getPilotoses().contains(piloto))
				listPilotos.setSelectedValue(piloto, false);
		}
		
		Query queryCoches = HibernateUtil.getCurrentSession().createQuery("FROM Coches");
		List<Coches> coches = queryCoches.list();
		
		for(Coches coche:coches){
			modeloListaCoches.addElement(coche);
			if(equipo.getCocheses().contains(coche))
				listCoches.setSelectedValue(coche, false);
		}
		
		Query querySponsors = HibernateUtil.getCurrentSession().createQuery("FROM Sponsors");
		List<Sponsors> sponsors = querySponsors.list();
		
		for(Sponsors sponsor:sponsors){
			modeloListaSponsors.addElement(sponsor);
			if(equipo.getSponsorses().contains(sponsor))
				listSponsors.setSelectedValue(sponsor, false);
		}
	}
	
	private void aceptar(){
		
		if(tfNombre.getText().equals(""))
			return;
		
		if(tfFundador.getText().equals(""))
			return;
		
		if(tfPresidente.getText().equals(""))
			return;
		
		nombre = tfNombre.getText();
		fundador = tfFundador.getText();
		presidente = tfPresidente.getText();
		pilotosSeleccionados = listPilotos.getSelectedValuesList();
		cochesSeleccionados = listCoches.getSelectedValuesList();
		sponsorsSeleccionados = listSponsors.getSelectedValuesList();
		
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
			JEquipos dialog = new JEquipos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JEquipos() {
		setBounds(100, 100, 520, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nombre");
			lblNewLabel.setBounds(10, 26, 46, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fundador");
			lblNewLabel_1.setBounds(162, 26, 75, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblPresidente = new JLabel("Presidente");
			lblPresidente.setBounds(322, 26, 85, 14);
			contentPanel.add(lblPresidente);
		}
		
		tfNombre = new JTextField();
		tfNombre.setBounds(66, 23, 86, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfFundador = new JTextField();
		tfFundador.setBounds(226, 23, 86, 20);
		contentPanel.add(tfFundador);
		tfFundador.setColumns(10);
		
		tfPresidente = new JTextField();
		tfPresidente.setBounds(408, 23, 86, 20);
		contentPanel.add(tfPresidente);
		tfPresidente.setColumns(10);
		
		lblPilotos = new JLabel("Pilotos");
		lblPilotos.setBounds(26, 87, 46, 14);
		contentPanel.add(lblPilotos);
		
		lblCoches = new JLabel("Coches");
		lblCoches.setBounds(191, 87, 46, 14);
		contentPanel.add(lblCoches);
		
		lblSponsors = new JLabel("Sponsors");
		lblSponsors.setBounds(361, 87, 46, 14);
		contentPanel.add(lblSponsors);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 117, 151, 187);
		contentPanel.add(scrollPane);
		
		listPilotos = new JList();
		scrollPane.setViewportView(listPilotos);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(201, 112, 157, 192);
		contentPanel.add(scrollPane_1);
		
		listCoches = new JList();
		scrollPane_1.setViewportView(listCoches);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(371, 112, 123, 192);
		contentPanel.add(scrollPane_2);
		
		listSponsors = new JList();
		scrollPane_2.setViewportView(listSponsors);
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
