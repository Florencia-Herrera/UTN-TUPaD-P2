/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Actividad5 {
    public static void main(String[] args) {
        Propietario propietario = new Propietario("María López", "40123456");
        Computadora computadora = new Computadora("Dell", "SN12345678", "ASUS-B550", "AMD-B550");

        computadora.setPropietario(propietario); 

        System.out.println(computadora);
        System.out.println("Propietario de la computadora: " + computadora.getPropietario().getNombre());
        System.out.println("Modelo de la placa madre: " + computadora.getPlacaMadre().getModelo());
        System.out.println("Computadora del propietario: " + propietario.getComputadora().getMarca());
    } 
}
