/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Actividad7 {
    public static void main(String[] args) {
        Motor motor = new Motor("Nafta 1.6", "MTR-00123");
        Vehiculo vehiculo = new Vehiculo("AB123CD", "Peugeot 208", motor);
        Conductor conductor = new Conductor("Laura Martínez", "L1234567");

        vehiculo.setConductor(conductor); // establece relación bidireccional

        System.out.println(vehiculo);
        System.out.println("Conductor del vehículo: " + vehiculo.getConductor().getNombre());
        System.out.println("Tipo de motor: " + vehiculo.getMotor().getTipo());
        System.out.println("Vehículo del conductor: " + conductor.getVehiculo().getPatente());
    }
}
