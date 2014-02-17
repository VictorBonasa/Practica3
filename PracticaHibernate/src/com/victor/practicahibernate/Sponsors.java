package com.victor.practicahibernate;

// Generated 14-feb-2014 1:27:39 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Sponsors generated by hbm2java
 */
@Entity
@Table(name = "sponsors", catalog = "hibernatevtm")
public class Sponsors implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private String presidente;
	private String web;
	private Set<Equipos> equiposes = new HashSet<Equipos>(0);

	public Sponsors() {
	}

	public Sponsors(String nombre, String presidente, String web,
			Set<Equipos> equiposes) {
		this.nombre = nombre;
		this.presidente = presidente;
		this.web = web;
		this.equiposes = equiposes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nombre", length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "presidente", length = 50)
	public String getPresidente() {
		return this.presidente;
	}

	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}

	@Column(name = "web", length = 50)
	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "equipos_sponsors", catalog = "hibernatevtm", joinColumns = { @JoinColumn(name = "id_sponsors", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_equipos", nullable = false, updatable = false) })
	public Set<Equipos> getEquiposes() {
		return this.equiposes;
	}

	public void setEquiposes(Set<Equipos> equiposes) {
		this.equiposes = equiposes;
	}

}
