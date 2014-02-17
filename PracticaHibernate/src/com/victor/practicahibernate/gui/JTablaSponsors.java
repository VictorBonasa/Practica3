package com.victor.practicahibernate.gui;

import java.sql.Connection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;

import com.victor.practicahibernate.Ruedas;
import com.victor.practicahibernate.Sponsors;
import com.victor.practicahibernate.util.HibernateUtil;

public class JTablaSponsors extends JTable {
	
	private Connection conexion;
	private DefaultTableModel modeloDatos;
	
	public JTablaSponsors() {
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
		modeloDatos.addColumn("presidente");
		modeloDatos.addColumn("web");
		
		this.setModel(modeloDatos);
	}
	
	public void listar(List<Sponsors> sponsors) {
		
		modeloDatos.setNumRows(0);
		for (Sponsors sponsor : sponsors) {
			
			Object[] fila = new Object[]{sponsor.getNombre(), sponsor.getPresidente(), sponsor.getWeb()};
			modeloDatos.addRow(fila);
		}
	}
	
	public void listar(String filtro) {
		modeloDatos.setNumRows(0);
		
		Query querySponsors = HibernateUtil.getCurrentSession().createQuery("FROM Sponsors");
		List<Sponsors> sponsors = querySponsors.list();
		for (Sponsors sponsor : sponsors) {
			if(sponsor.getNombre().equals(filtro) ||
					sponsor.getPresidente().equals(filtro) ||
					sponsor.getWeb().equals(filtro)){
				Object[] fila = new Object[]{sponsor.getNombre(), sponsor.getPresidente(), sponsor.getWeb()};
				modeloDatos.addRow(fila);
			}
		}
	}
	
	public Sponsors getSponsorSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		int id = (Integer) getValueAt(filaSeleccionada, 0);
		
		Sponsors sponsor = (Sponsors) HibernateUtil.getCurrentSession().get(Sponsors.class, id);
		return sponsor;
	}


}
