// tp6/biblioteca/Autor.java
package tp6.biblioteca;

public class Autor {
    private String id;
    private String nombre;
    private String nacionalidad;

    public Autor(String id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public void mostrarInfo() {
        System.out.println(this);
    }

    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "Autor{id='" + id + "', nombre='" + nombre + "', nac='" + nacionalidad + "'}";
    }
}
