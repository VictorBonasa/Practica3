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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Pilotos generated by hbm2java
 */
@Entity
@Table(name = "pilotos", catalog = "hibernatevtm")
public class Pilotos implements java.io.Serializable {

	private Integer id;
	private Equipos equipos;
	private String nombre;
	private String nacionalidad;
	private Integer dorsal;
	private Set<Coches> cocheses = new HashSet<Coches>(0);
	private Set<Circuitos> circuitoses = new HashSet<Circuitos>(0);

	public Pilotos() {
	}

	public Pilotos(Equipos equipos) {
		this.equipos = equipos;
	}

	public Pilotos(Equipos equipos, String nombre, String nacionalidad,
			Integer dorsal, Set<Coches> cocheses, Set<Circuitos> circuitoses) {
		this.equipos = equipos;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.dorsal = dorsal;
		this.cocheses = cocheses;
		this.circuitoses = circuitoses;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_equipos", nullable = false)
	public Equipos getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Equipos equipos) {
		this.equipos = equipos;
	}

	@Column(name = "nombre", length = 30)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "nacionalidad", length = 30)
	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Column(name = "dorsal")
	public Integer getDorsal() {
		return this.dorsal;
	}

	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pilotos")
	public Set<Coches> getCocheses() {
		return this.cocheses;
	}

	public void setCocheses(Set<Coches> cocheses) {
		this.cocheses = cocheses;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "pilotos_circuitos", catalog = "hibernatevtm", joinColumns = { @JoinColumn(name = "id_pilotos", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_circuitos", nullable = false, updatable = false) })
	public Set<Circuitos> getCircuitoses() {
		return this.circuitoses;
	}

	public void setCircuitoses(Set<Circuitos> circuitoses) {
		this.circuitoses = circuitoses;
	}

}