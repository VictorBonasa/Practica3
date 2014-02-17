package com.victor.practicahibernate.gui;

import java.sql.Connection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;

import com.victor.practicahibernate.Pilotos;
import com.victor.practicahibernate.Ruedas;
import com.victor.practicahibernate.util.HibernateUtil;

public class JTablaRuedas extends JTable {
	
	private Connection conexion;
	private DefaultTableModel modeloDatos;
	
	public JTablaRuedas() {
		super();

		inicializar();
	}

	private void inicializar() {
		
		modeloDatos = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
		
		modeloDatos.addColumn("Pulgadas");
		modeloDatos.addColumn("Tipo de Neumatico");
		modeloDatos.addColumn("Peso");
		
		this.setModel(modeloDatos);
	}
	
	public void listar(List<Ruedas> ruedas) {
		
		modeloDatos.setNumRows(0);
		for (Ruedas rueda : ruedas) {
			
			Object[] fila = new Object[]{rueda.getPulgadas(), rueda.getTipoNeumatico(), rueda.getPeso()};
			modeloDatos.addRow(fila);
		}
	}
	
	public void listar(String filtro) {
		modeloDatos.setNumRows(0);
		
		Query queryRuedas = HibernateUtil.getCurrentSession().createQuery("FROM Ruedas");
		List<Ruedas> ruedas = queryRuedas.list();
		for (Ruedas rueda : ruedas) {
			if(rueda.getPulgadas().equals(filtro) ||
					rueda.getTipoNeumatico().equals(filtro) ||
					rueda.getPeso().equals(filtro)){
				Object[] fila = new Object[]{rueda.getPulgadas(), rueda.getTipoNeumatico(), rueda.getPeso()};
				modeloDatos.addRow(fila);
			}
		}
	}
	
	public Ruedas getRuedaSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		int id = (Integer) getValueAt(filaSeleccionada, 0);
		
		Ruedas rueda = (Ruedas) HibernateUtil.getCurrentSession().get(Ruedas.class, id);
		return rueda;
	}


}
