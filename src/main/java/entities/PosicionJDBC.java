package entities;


enum EnumPosicion {
	Delantero,
	Defensa,
	Medio,
	Portero
}

public class PosicionJDBC implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
    public PosicionJDBC() {
    }
    
    public int numDelanteros = 0;
    public int numDefensas = 0;
    public int numMedios = 0;
    public int numPorteros = 0;
    
    public int maxDelanteros = 6;
    public int maxDefensas = 8;
    public int maxMedios = 8;
    public int maxPorteros = 3;
    
    public void añadirPosicion(String posicion) throws IllegalArgumentException{
    	
    	EnumPosicion pos = null;
    	
    	try {
    		pos = EnumPosicion.valueOf(posicion);
    	}
    	catch (IllegalArgumentException e){
    		throw new IllegalArgumentException("Posición no reconocida");
    	}
   
    	switch(pos) {
	    	case Delantero:
	    		if (this.numDelanteros < maxDelanteros) {
	    			this.numDelanteros += 1;
	    		}
	    		else throw new IllegalArgumentException("Se ha superado el número permitido de delanteros");
	    		
	    		break;
	    	case Defensa:
	    		if (this.numDefensas < maxDefensas) {
	    			this.numDefensas += 1;
	    		}
	    		else throw new IllegalArgumentException("Se ha superado el número permitido de defensas");
	
	    		break;
	    	case Medio:
	    		if (this.numMedios < maxMedios) {
	    			this.numMedios += 1;
	    		}
	    		else throw new IllegalArgumentException("Se ha superado el número permitido de medios");
	
	    		break;
	    	case Portero:
	    		if (this.numPorteros < maxPorteros) {
	    			this.numPorteros += 1;
	    		}
	    		else throw new IllegalArgumentException("Se ha superado el número permitido de porteros");
	
	    		break;
	    }
    }
    
	public void eliminarPosicion(String posicion) throws IllegalArgumentException{
	    	    	
	    	try {
	    		EnumPosicion pos = EnumPosicion.valueOf(posicion);
	    		
	    		switch(pos) {
		    		case Delantero:
		    			this.numDelanteros -= 1;		    		
		    			break;
		    		
			    	case Defensa:
		    			this.numDefensas -= 1;
			    		break;
			    		
			    	case Medio:
		    			this.numMedios -= 1;	
			    		break;
			    		
			    	case Portero:
		    			this.numPorteros -= 1;	
		    			break;
	    		}
	    		
	    	}
	    	catch (IllegalArgumentException e){
	    		throw new IllegalArgumentException("Posición no reconocida");
	    	}
	   
	    	
	    }

}