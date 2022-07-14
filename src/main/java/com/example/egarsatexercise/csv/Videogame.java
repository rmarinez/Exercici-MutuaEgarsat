package com.example.egarsatexercise.csv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exerciseEgarsat")
public class Videogame {

	@Id
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "coste")
	private Long coste;

	@Column(name = "plataforma")
	private String plataforma;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "disponibilidad")
	private String disponibilidad;

	public Videogame() {

	}

	public Videogame(String Nombre, Long Coste, String Plataforma, String Tipo, String Disponibilidad) {
		this.nombre = Nombre;
		this.coste = Coste;
		this.plataforma = Plataforma;
		this.tipo = Tipo;
		this.disponibilidad = Disponibilidad;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getCoste() {
		return coste;
	}

	public void setCoste(Long coste) {
		this.coste = coste;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	@Override
	public String toString() {
		return "DeveloperTutorial [nombre=" + nombre + ", coste=" + coste + ", plataforma=" + plataforma + ", tipo="
				+ tipo + ", disponibilidad=" + disponibilidad + "]";
	}

}
