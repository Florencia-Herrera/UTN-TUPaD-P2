/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Actividad10 {
    public static void main(String[] args) {
        Titular1 titular = new Titular1("Julieta Ramírez", "40222333");
        CuentaBancaria cuenta = new CuentaBancaria("0001234500009876543210", 152000.75, "ABC123", "2025-10-10");

        cuenta.setTitular(titular); 

        System.out.println(cuenta);
        System.out.println("Titular: " + cuenta.getTitular().getNombre());
        System.out.println("Código de seguridad: " + cuenta.getClave().getCodigo());
        System.out.println("Cuenta del titular: " + titular.getCuenta().getCbu());
    }
}
