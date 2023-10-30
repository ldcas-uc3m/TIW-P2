package entities;


import utils.ValidadorDNI;

public class JugadorJDBC implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	// constructor
    public JugadorJDBC(String nombre, String apellidos, String DNI, String alias, String posicion) throws IllegalArgumentException {
        
    	// comprobar si los campos son vacíos
    	if ((nombre.length() == 0) || (apellidos.length() == 0) || (DNI.length() == 0) || (alias.length() == 0) || (posicion.length() == 0)) {  // no, no hay otra forma de hacerlo
        	throw new IllegalArgumentException("Faltan datos");
        }
        
    	// validar DNI
        if (!ValidadorDNI.validarDNI(DNI)) {
        	throw new IllegalArgumentException("DNI no válido");
        }
        
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.DNI = DNI;
        this.alias = alias;
        this.posicion = posicion;
    }
    
    String nombre;
    String apellidos;
    String DNI;
    String alias;
    String posicion;
    
    public String getNombre() {
    	return nombre;
    }
    
    public void setNombre(String nombre) {
    	if (nombre.length() == 0) {
    		throw new IllegalArgumentException("Campo 'Nombre' vacío");
    	}
   
    	this.nombre = nombre;
    }
    
    public String getApellidos() {
    	return apellidos;
    }
    
    public void setApellidos(String apellidos) {
    	if (apellidos.length() == 0) {
    		throw new IllegalArgumentException("Campo 'Apellidos' vacío");
    	}
    	
    	this.apellidos = apellidos;
    }
    
    public String getDNI() {
    	return DNI;
    }
    
    public void setDNI(String DNI) {
    	// validar DNI
        if (!ValidadorDNI.validarDNI(DNI)) {
        	throw new IllegalArgumentException("DNI no válido");
        }
 
    	this.DNI = DNI;
    }
    
    public String getAlias() {
    	return alias;
    }
    
    public void setAlias(String alias) {
    	if (alias.length() == 0) {
    		throw new IllegalArgumentException("Campo 'Alias' vacío");
    	}
 
    	this.alias = alias;
    }
    
    public String getPosicion() {
    	return posicion;
    }
    
    public void setPosicion(String posicion) {
    	this.posicion = posicion;
    }

}
