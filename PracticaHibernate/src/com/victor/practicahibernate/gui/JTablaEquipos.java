package com.victor.practicahibernate.gui;

import java.sql.Connection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;

import com.victor.practicahibernate.Coches;
import com.victor.practicahibernate.Equipos;
import com.victor.practicahibernate.util.HibernateUtil;


public class JTablaEquipos extends JTable {
	
	private Connection conexion;
	private DefaultTableModel modeloDatos;
	
	public JTablaEquipos() {
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
		modeloDatos.addColumn("fundador");
		modeloDatos.addColumn("presidente");
		
		this.setModel(modeloDatos);
	}
	
	public void listar(List<Equipos> equipos) {
		
		modeloDatos.setNumRows(0);
		for (Equipos equipo : equipos) {
			
			Object[] fila = new Object[]{equipo.getNombre(), equipo.getFundador(), equipo.getPresidente()};
			modeloDatos.addRow(fila);
		}
	}
	
	public void listar(String filtro) {
		modeloDatos.setNumRows(0);
		
		Query queryEquipos = HibernateUtil.getCurrentSession().createQuery("FROM Equipos");
		List<Equipos> equipos = queryEquipos.list();
		for (Equipos equipo : equipos) {
			if(equipo.getNombre().equals(filtro) ||
					equipo.getFundador().equals(filtro) ||
					equipo.getPresidente().equals(filtro)){
				Object[] fila = new Object[]{equipo.getNombre(), equipo.getFundador(), equipo.getPresidente()};
				modeloDatos.addRow(fila);
			}

		}
	}
	
public Equipos getEquipoSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		int id = (Integer) getValueAt(filaSeleccionada, 0);
		
		Equipos equipo = (Equipos) HibernateUtil.getCurrentSession().get(Equipos.class, id);
		return equipo;
	}


}
