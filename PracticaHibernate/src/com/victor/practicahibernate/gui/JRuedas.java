package com.victor.practicahibernate.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import com.victor.practicahibernate.Ruedas;
import com.victor.practicahibernate.util.HibernateUtil;
import com.victor.practicahibernate.util.Util;
import com.victor.practicahibernate.util.Util.Accion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JRuedas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfPulgadas;
	private JTextField tfTipo;
	private JTextField tfPeso;
	private JList<Coches> listCoche;
	
	private int pulgadas;
	private String tipo;
	private int peso;
	private Coches cocheSeleccionado;
	
	DefaultListModel <Coches> modeloListaCoches;
	private Util.Accion accion;
	
	public Accion mostrarDialogo() {
		setVisible(true);
		
		return accion;
	}
	
private void inicializar (){
		
		modeloListaCoches =  new DefaultListModel <Coches>();
		listCoche.setModel(modeloListaCoches);
		
		cargarCoches();
	}
	
	private void cargarCoches(){	
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Coches");
		List<Coches> coches = query.list();
		
		for(Coches coche: coches){
			modeloListaCoches.addElement(coche);
		}
	}
	
	
	public Ruedas getRueda() {
		Ruedas rueda = new Ruedas();
		
		rueda.setPulgadas(pulgadas);
		rueda.setTipoNeumatico(tipo);
		rueda.setPeso(peso);
		rueda.setCoches(cocheSeleccionado);
		
		return rueda;
	}
	
public void setRueda(Ruedas rueda){
		
	tfPulgadas.setText(String.valueOf(rueda.getPulgadas()));
	tfTipo.setText(rueda.getTipoNeumatico());
	tfPeso.setText(String.valueOf(rueda.getPeso()));
		
		
		Query queryCoches = HibernateUtil.getCurrentSession().createQuery("FROM Coches");
		List<Coches> coches = queryCoches.list();
		
		for(Coches coche:coches){
			modeloListaCoches.addElement(coche);
			if(rueda.getCoches().equals(coche))
				listCoche.setSelectedValue(coche, false);
		}
	}
	
	private void aceptar(){
		
		if(tfPulgadas.getText().equals(""))
			return;
		
		if(tfTipo.getText().equals(""))
			return;
		
		if(tfPeso.getText().equals(""))
			return;
		
		pulgadas = Integer.parseInt(tfPulgadas.getText());
		tipo = tfTipo.getText();
		peso = Integer.parseInt(tfPeso.getText());
		cocheSeleccionado = listCoche.getSelectedValue();
		
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
			JRuedas dialog = new JRuedas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JRuedas() {
		setBounds(100, 100, 247, 430);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPulgadas = new JLabel("Pulgadas");
			lblPulgadas.setBounds(20, 11, 43, 14);
			contentPanel.add(lblPulgadas);
		}
		{
			JLabel lblTipoNeumatico = new JLabel("Tipo Neumatico");
			lblTipoNeumatico.setBounds(20, 36, 79, 14);
			contentPanel.add(lblTipoNeumatico);
		}
		{
			JLabel lblPeso = new JLabel("Peso");
			lblPeso.setBounds(17, 61, 46, 14);
			contentPanel.add(lblPeso);
		}
		{
			JLabel lblCoche = new JLabel("Coche");
			lblCoche.setBounds(17, 86, 46, 14);
			contentPanel.add(lblCoche);
		}
		{
			tfPulgadas = new JTextField();
			tfPulgadas.setBounds(109, 8, 86, 20);
			contentPanel.add(tfPulgadas);
			tfPulgadas.setColumns(10);
		}
		{
			tfTipo = new JTextField();
			tfTipo.setBounds(109, 33, 86, 20);
			contentPanel.add(tfTipo);
			tfTipo.setColumns(10);
		}
		{
			tfPeso = new JTextField();
			tfPeso.setBounds(109, 58, 86, 20);
			contentPanel.add(tfPeso);
			tfPeso.setColumns(10);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(27, 111, 172, 237);
			contentPanel.add(scrollPane);
			{
				listCoche = new JList();
				scrollPane.setViewportView(listCoche);
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
