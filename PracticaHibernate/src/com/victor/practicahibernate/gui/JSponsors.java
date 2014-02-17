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

import com.victor.practicahibernate.Equipos;
import com.victor.practicahibernate.Sponsors;
import com.victor.practicahibernate.util.HibernateUtil;
import com.victor.practicahibernate.util.Util;
import com.victor.practicahibernate.util.Util.Accion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JSponsors extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfPresidente;
	private JTextField tfWeb;
	private JList<Equipos> listEquipos;
	
	private String nombre;
	private String presidente;
	private String web;
	private List<Equipos> equiposSeleccionados;
	
	DefaultListModel <Equipos> modeloListaEquipos;
	Util.Accion accion;
	
	public Accion mostrarDialogo() {
		setVisible(true);
		
		return accion;
	}
	
	private void inicializar(){
		modeloListaEquipos = new DefaultListModel();
		listEquipos.setModel(modeloListaEquipos);
		
		cargarEquipos();
		
	}
	
	private void cargarEquipos() {
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Equipos");
		List<Equipos> equipos = query.list();
		
		for(Equipos equipo:equipos){
			modeloListaEquipos.addElement(equipo);
		}
	}
	
	public Sponsors getSponsor(){
		Sponsors sponsor = new Sponsors();
		
		sponsor.setNombre(nombre);
		sponsor.setPresidente(presidente);
		sponsor.setWeb(web);
		sponsor.setEquiposes(new HashSet<Equipos>(equiposSeleccionados));
		
		return sponsor;
	}
	
public void setSponsor(Sponsors sponsor){
		
		tfNombre.setText(sponsor.getNombre());
		tfPresidente.setText(sponsor.getPresidente());
		tfWeb.setText(sponsor.getWeb());
		
		Query queryEquipos = HibernateUtil.getCurrentSession().createQuery("FROM Equipos");
		List<Equipos> equipos = queryEquipos.list();
		
		for(Equipos equipo:equipos){
			modeloListaEquipos.addElement(equipo);
			if(sponsor.getEquiposes().contains(equipo))
				listEquipos.setSelectedValue(equipo, false);
		}
}
	
	private void aceptar(){
		if(tfNombre.getText().equals(""))
			return;
		
		if(tfPresidente.getText().equals(""))
			return;
		
		if(tfWeb.getText().equals(""))
			return;
		
		nombre = tfNombre.getText();
		presidente = tfPresidente.getText();
		web = tfWeb.getText();
		equiposSeleccionados = listEquipos.getSelectedValuesList();
		
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
			JSponsors dialog = new JSponsors();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JSponsors() {
		setBounds(100, 100, 209, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(10, 11, 37, 14);
			contentPanel.add(lblNombre);
		}
		{
			JLabel lblPresidente = new JLabel("Presidente");
			lblPresidente.setBounds(10, 36, 72, 14);
			contentPanel.add(lblPresidente);
		}
		{
			JLabel lblWeb = new JLabel("Web");
			lblWeb.setBounds(10, 61, 46, 14);
			contentPanel.add(lblWeb);
		}
		{
			tfNombre = new JTextField();
			tfNombre.setBounds(80, 8, 86, 20);
			contentPanel.add(tfNombre);
			tfNombre.setColumns(10);
		}
		{
			tfPresidente = new JTextField();
			tfPresidente.setBounds(80, 33, 86, 20);
			contentPanel.add(tfPresidente);
			tfPresidente.setColumns(10);
		}
		{
			tfWeb = new JTextField();
			tfWeb.setBounds(80, 61, 86, 20);
			contentPanel.add(tfWeb);
			tfWeb.setColumns(10);
		}
		{
			JLabel lblEquipos = new JLabel("Equipos");
			lblEquipos.setBounds(80, 92, 46, 14);
			contentPanel.add(lblEquipos);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(24, 117, 142, 261);
			contentPanel.add(scrollPane);
			{
				listEquipos = new JList();
				scrollPane.setViewportView(listEquipos);
			}
		}
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
