package com.victor.practicahibernate.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.hibernate.Query;

import com.victor.practicahibernate.Circuitos;
import com.victor.practicahibernate.Pilotos;
import com.victor.practicahibernate.util.HibernateUtil;
import com.victor.practicahibernate.util.Util;
import com.victor.practicahibernate.util.Util.Accion;

/**
 * Dialog con el que el usuario introduce informaci—n sobre un Actor
 * para insertar o modificar
 * @author Santiago Faci
 * @version 1.0
 */
public class JCircuitos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	
	private String nombre;
	private String ubicacion;
	private int distancia;
	private DefaultListModel<Pilotos> modeloListaPilotos;
	private JList<Pilotos> listPilotos;
	private List<Pilotos> pilotosSeleccionados;
	
	private Util.Accion accion;
	private JTextField tfUbicacion;
	private JTextField tfDistancia;
	

	/**
	 * Getters y setters para obtener y fijar información en la ventana
	 * @return
	 */
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.tfNombre.setText(nombre);
	}
	
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public Util.Accion getAccion() {
		return accion;
	}

	public void setAccion(Util.Accion accion) {
		this.accion = accion;
	}

	public Accion mostrarDialogo() {
		setVisible(true);
		
		return accion;
	}
	
private void inicializar() {
		
		modeloListaPilotos = new DefaultListModel<Pilotos>();
		listPilotos.setModel(modeloListaPilotos);
		/*
		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSession();
		*/
		cargarPilotos();
	}
	
	private void cargarPilotos() {
		
			Query query = HibernateUtil.getCurrentSession().createQuery("FROM Pilotos");
			List<Pilotos> pilotos = query.list();
		
		for (Pilotos piloto : pilotos) {
			modeloListaPilotos.addElement(piloto);
		}
	}
	
	public Circuitos getCircuito() {
		
		Circuitos circuito = new Circuitos();
		circuito.setNombre(nombre);
		circuito.setUbicacion(ubicacion);
		circuito.setDistancia(distancia);
		circuito.setPilotoses(new HashSet<Pilotos>(pilotosSeleccionados));
		
		
		return circuito;
	}
	
	public void setCircuito(Circuitos circuito){
		tfNombre.setText(circuito.getNombre());
		tfUbicacion.setText(circuito.getUbicacion());
		tfDistancia.setText(String.valueOf(circuito.getDistancia()));
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Pilotos");
		List<Pilotos> pilotos = query.list();
	
		for (Pilotos piloto : pilotos) {
			modeloListaPilotos.addElement(piloto);
			if(circuito.getPilotoses().contains(piloto))
				listPilotos.setSelectedValue(piloto, false);
		}
	}

	/**
	 * Se invoca cuando el usuario ha pulsado en Aceptar. Recoge y valida la información de las cajas de texto
	 * y cierra la ventana
	 */
	private void aceptar() {
		
		if (tfNombre.getText().equals(""))
			return;
		
		if (tfUbicacion.getText().equals(""))
			return;
		
		if (tfDistancia.getText().equals(""))
			return;

		nombre = tfNombre.getText();
		ubicacion = tfUbicacion.getText();
		distancia = Integer.parseInt(tfDistancia.getText());
		pilotosSeleccionados = listPilotos.getSelectedValuesList();
		
		accion = Accion.ACEPTAR;
		setVisible(false);
	}
	
	/**
	 * Invocado cuando el usuario cancela. Simplemente cierra la ventana
	 */
	private void cancelar() {
		accion = Accion.CANCELAR;
		setVisible(false);
	}
	
	/**
	 * Constructor. Crea la ventana
	 */
	public JCircuitos() {
		setModal(true);
		setTitle("Circuito");
		setBounds(100, 100, 290, 340);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(100, 22, 138, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 25, 80, 14);
		contentPanel.add(lblNewLabel);
		
		lblNewLabel_2 = new JLabel("Ubicacion");
		lblNewLabel_2.setBounds(10, 53, 89, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblDistancia = new JLabel("Distancia");
		lblDistancia.setBounds(10, 80, 89, 14);
		contentPanel.add(lblDistancia);
		
		tfUbicacion = new JTextField();
		tfUbicacion.setBounds(100, 53, 138, 20);
		contentPanel.add(tfUbicacion);
		tfUbicacion.setColumns(10);
		
		tfDistancia = new JTextField();
		tfDistancia.setBounds(100, 77, 138, 20);
		contentPanel.add(tfDistancia);
		tfDistancia.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 105, 138, 153);
		contentPanel.add(scrollPane);
		
		listPilotos = new JList<Pilotos>();
		scrollPane.setViewportView(listPilotos);
		
		JLabel lblPilotos = new JLabel("Pilotos");
		lblPilotos.setBounds(10, 105, 89, 14);
		contentPanel.add(lblPilotos);
		inicializar();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
