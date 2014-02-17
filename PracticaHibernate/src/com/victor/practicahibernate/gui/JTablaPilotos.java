package com.victor.practicahibernate.gui;

import java.sql.Connection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;

import com.victor.practicahibernate.Circuitos;
import com.victor.practicahibernate.Equipos;
import com.victor.practicahibernate.Pilotos;
import com.victor.practicahibernate.util.HibernateUtil;

public class JTablaPilotos extends JTable {
	
	private Connection conexion;
	private DefaultTableModel modeloDatos;
	
	public JTablaPilotos() {
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
		modeloDatos.addColumn("nacionalidad");
		modeloDatos.addColumn("dorsal");
		
		this.setModel(modeloDatos);
	}
	
	public void listar(List<Pilotos> pilotos) {
		
		modeloDatos.setNumRows(0);
		for (Pilotos piloto : pilotos) {
			
			Object[] fila = new Object[]{piloto.getNombre(), piloto.getNacionalidad(), piloto.getDorsal()};
			modeloDatos.addRow(fila);
		}
	}
	
	public void listar(String filtro) {
		modeloDatos.setNumRows(0);
		
		Query queryPilotos = HibernateUtil.getCurrentSession().createQuery("FROM Pilotos");
		List<Pilotos> pilotos = queryPilotos.list();
		for (Pilotos piloto : pilotos) {
			if(piloto.getNombre().equals(filtro) ||
					piloto.getNacionalidad().equals(filtro) ||
					piloto.getDorsal().equals(filtro)){
				Object[] fila = new Object[]{piloto.getNombre(), piloto.getNacionalidad(), piloto.getDorsal()};
				modeloDatos.addRow(fila);
			}

		}
	}
	
	public Pilotos getPilotoSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		int id = (Integer) getValueAt(filaSeleccionada, 0);
		
		Pilotos piloto = (Pilotos) HibernateUtil.getCurrentSession().get(Pilotos.class, id);
		return piloto;
	}


}
