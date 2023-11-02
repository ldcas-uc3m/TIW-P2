package beans;


enum EnumPosicion {
	Delantero,
	Defensa,
	Medio,
	Portero
}

public class Posicion implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

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