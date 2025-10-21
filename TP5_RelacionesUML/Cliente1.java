/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Cliente1 {
    private String nombre;
    private String telefono;

    public Cliente1(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return "Cliente [nombre=" + nombre + ", telefono=" + telefono + "]";
    }    
}
