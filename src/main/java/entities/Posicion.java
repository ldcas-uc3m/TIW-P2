package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the posiciones database table.
 * 
 */
@Entity
@Table(name="posiciones")
@NamedQuery(name="Posicion.getPosiciones", query="SELECT p FROM Posicion p")
public class Posicion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nombre;

	private int max_jugadores;
	private int num_jugadores;


	public Posicion() { }


	public String getNombre() {
		return this.nombre;
	}

	public int getMaxJugadores() {
		return this.max_jugadores;
	}

	public int getNumJugadores() {
		return this.num_jugadores;
	}


	public boolean isMax() {
		return (this.num_jugadores >= this.max_jugadores);
	}

	public void addJugador() throws IllegalArgumentException {
		if (this.isMax()) {
			throw new IllegalArgumentException("Número máximo de " + this.nombre + "s superado");
		}

		this.num_jugadores++;
	}
	
	public void removeJugador() throws IllegalArgumentException {
		if (this.num_jugadores <= 0) {
			throw new IllegalArgumentException("Número mínimo de " + this.nombre + "s superado");
		}

		this.num_jugadores--;
	}

}