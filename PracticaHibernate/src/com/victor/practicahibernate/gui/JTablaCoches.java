package com.victor.practicahibernate.gui;

import java.sql.Connection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;

import com.victor.practicahibernate.Circuitos;
import com.victor.practicahibernate.Coches;
import com.victor.practicahibernate.util.HibernateUtil;

public class JTablaCoches extends JTable {
	
	private Connection conexion;
	private DefaultTableModel modeloDatos;
	
	private static final boolean DEBUG = false; 
	
	public JTablaCoches() {
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
		modeloDatos.addColumn("color");
		modeloDatos.addColumn("peso");
		
		this.setModel(modeloDatos);
	}
	
	public void listar(List<Coches> coches) {
		
		modeloDatos.setNumRows(0);
		for (Coches coche:coches) {
			
			Object[] fila = new Object[]{coche.getNombre(),coche.getColor(), coche.getPeso()};
			modeloDatos.addRow(fila);
		}
	}
	
	public void listar(String filtro) {
		modeloDatos.setNumRows(0);
		
		Query queryCoches = HibernateUtil.getCurrentSession().createQuery("FROM Coches");
		List<Coches> coches = queryCoches.list();
		for (Coches coche : coches) {
			if(coche.getNombre().equals(filtro) ||
					coche.getColor().equals(filtro) ||
					coche.getPeso().equals(filtro)){
				Object[] fila = new Object[]{coche.getNombre(), coche.getColor(), coche.getPeso()};
				modeloDatos.addRow(fila);
			}

		}
	}
	
public Coches getCocheSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		int id = (Integer) getValueAt(filaSeleccionada, 0);
		
		Coches coche = (Coches) HibernateUtil.getCurrentSession().get(Coches.class, id);
		return coche;
	}


}
