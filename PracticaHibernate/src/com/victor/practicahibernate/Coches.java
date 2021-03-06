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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Coches generated by hbm2java
 */
@Entity
@Table(name = "coches", catalog = "hibernatevtm")
public class Coches implements java.io.Serializable {

	private Integer id;
	private Pilotos pilotos;
	private Equipos equipos;
	private String nombre;
	private String color;
	private Integer peso;
	private Set<Ruedas> ruedases = new HashSet<Ruedas>(0);

	public Coches() {
	}

	public Coches(Pilotos pilotos, Equipos equipos) {
		this.pilotos = pilotos;
		this.equipos = equipos;
	}

	public Coches(Pilotos pilotos, Equipos equipos, String nombre,
			String color, Integer peso, Set<Ruedas> ruedases) {
		this.pilotos = pilotos;
		this.equipos = equipos;
		this.nombre = nombre;
		this.color = color;
		this.peso = peso;
		this.ruedases = ruedases;
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
	@JoinColumn(name = "id_pilotos", nullable = false)
	public Pilotos getPilotos() {
		return this.pilotos;
	}

	public void setPilotos(Pilotos pilotos) {
		this.pilotos = pilotos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_equipos", nullable = false)
	public Equipos getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Equipos equipos) {
		this.equipos = equipos;
	}

	@Column(name = "nombre", length = 20)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "color", length = 20)
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "peso")
	public Integer getPeso() {
		return this.peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "coches")
	public Set<Ruedas> getRuedases() {
		return this.ruedases;
	}

	public void setRuedases(Set<Ruedas> ruedases) {
		this.ruedases = ruedases;
	}

}
