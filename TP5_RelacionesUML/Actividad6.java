/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Actividad6 {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Carlos Díaz", "1134567890");
        Mesa mesa = new Mesa(12, 4);
        Reserva reserva = new Reserva("2025-10-20", "21:00", cliente1, mesa);

        System.out.println(reserva);
        System.out.println("Cliente: " + reserva.getCliente().getNombre());
        System.out.println("Mesa número: " + reserva.getMesa().getNumero());
    }
}
