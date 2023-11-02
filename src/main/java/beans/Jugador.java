package beans;


import java.io.Serializable;

import utils.ValidadorDNI;

        
public class Jugador implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dni;

	private String nombre;
	private String apellidos;
	private String alias;

	private String posicion;


	public Jugador() { }


	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) throws IllegalArgumentException {
		// validar DNI
        if (!ValidadorDNI.validarDNI(dni)) {
        	throw new IllegalArgumentException("DNI no válido");
        }

		this.dni = dni;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) throws IllegalArgumentException {
		if (nombre.length() == 0) {
    		throw new IllegalArgumentException("Campo 'Nombre' vacío");
    	}

		this.nombre = nombre;
	}


	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) throws IllegalArgumentException {
		if (apellidos.length() == 0) {
    		throw new IllegalArgumentException("Campo 'Apellidos' vacío");
    	}

		this.apellidos = apellidos;
	}


	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getPosicion() {
		return this.posicion;
	}

	public void setPosicion(String posicion) throws IllegalArgumentException {
		this.posicion = posicion;
	}

}

