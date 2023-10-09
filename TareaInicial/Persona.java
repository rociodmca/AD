package TareaInicial;

public class Persona {
    //Atributos
    private String nombre;
    private int edad;

    //Constructores
    public Persona() {
        this.nombre = "Pepe";
        this.edad = 20;
    }

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String ToString() {
        return "Persona: " + nombre + " tiene " + edad;
    }
    
}
