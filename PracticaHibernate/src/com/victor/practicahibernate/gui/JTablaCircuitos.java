package com.victor.practicahibernate.gui;

import java.sql.Connection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;

import com.victor.practicahibernate.Circuitos;
import com.victor.practicahibernate.util.HibernateUtil;

public class JTablaCircuitos extends JTable {
	
	private Connection conexion;
	private DefaultTableModel modeloDatos;
	
	public JTablaCircuitos() {
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
		
		modeloDatos.addColumn("nombre");
		modeloDatos.addColumn("ubicacion");
		modeloDatos.addColumn("distancia");
		
		this.setModel(modeloDatos);
	}
	
	public void listar(List<Circuitos> circuitos) {
		
		modeloDatos.setNumRows(0);
		for (Circuitos circuito : circuitos) {
			
			Object[] fila = new Object[]{circuito.getNombre(), circuito.getUbicacion(), circuito.getDistancia()};
			modeloDatos.addRow(fila);
		}
	}
	
	public void listar(String filtro) {
		modeloDatos.setNumRows(0);
		
		Query queryCircuitos = HibernateUtil.getCurrentSession().createQuery("FROM Circuitos");
		List<Circuitos> circuitos = queryCircuitos.list();
		for (Circuitos circuito : circuitos) {
			if(circuito.getNombre().equals(filtro) ||
				circuito.getUbicacion().equals(filtro) ||
				circuito.getDistancia().equals(filtro)){
				Object[] fila = new Object[]{circuito.getNombre(), circuito.getUbicacion(), circuito.getDistancia()};
				modeloDatos.addRow(fila);
			}

		}
	}
	
	public Circuitos getCircuitoSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		int id = (Integer) getValueAt(filaSeleccionada, 0);
		
		Circuitos circuito = (Circuitos) HibernateUtil.getCurrentSession().get(Circuitos.class, id);
		return circuito;
	}


}
