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
// @NamedQuery(name="Posicion.getPosicionByName", query="SELECT p FROM Posicion p WHERE p.name LIKE :posicion")
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

	public void addJugador() throws Exception {
		if (this.num_jugadores < this.max_jugadores) {
			this.num_jugadores++;
		}
		else {
			throw new Exception("Lorem ipsum");
		}
	}
	
	public boolean isMax() {
		return (this.num_jugadores == this.max_jugadores);
	}

}