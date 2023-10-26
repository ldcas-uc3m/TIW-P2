package entities;

import java.io.Serializable;
import javax.persistence.*;

import utils.ValidadorDNI;



/**
 * The persistent class for the jugadores database table.
 * 
 */
@Entity
@Table(name="jugadores")
@NamedQuery(name="Jugador.findAll", query="SELECT j FROM Jugador j")
public class Jugador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String nombre;
	private String apellidos;
	private String alias;

	// uni-directional many-to-one association to Posiciones
	@ManyToOne()
	private Posicion posicion;


	public Jugador() { }

	public int getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		// validar DNI
        if (!ValidadorDNI.validarDNI(DNI)) {
        	throw new IllegalArgumentException("DNI no válido");
        }

		this.dni = dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		if (nombre.length() == 0) {
    		throw new IllegalArgumentException("Campo 'Nombre' vacío");
    	}

		this.nombre = nombre;
	}

	public BigInteger getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		if (apellidos.length() == 0) {
    		throw new IllegalArgumentException("Campo 'Apellidos' vacío");
    	}

		this.apellidos = apellidos;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		if (alias.length() == 0) {
    		throw new IllegalArgumentException("Campo 'Alias' vacío");
    	}

		this.alias = alias;
	}

	public String getPosicion() {
		return this.posicion.getNombre();
	}

	public void setPosicion(Posicion posicion) {
		posicion.addJugador();
	}

}