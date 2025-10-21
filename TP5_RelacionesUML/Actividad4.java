/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Actividad4 {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Nación", "30-50000000-7");
        TarjetaDeCredito tarjeta = new TarjetaDeCredito("4509 3012 5678 9123", "12/28", banco);
        Cliente cliente = new Cliente("Luis Fernández", "40321987");

        tarjeta.setCliente(cliente); 

        System.out.println(tarjeta);
        System.out.println("Cliente de la tarjeta: " + tarjeta.getCliente().getNombre());
        System.out.println("Banco emisor: " + tarjeta.getBanco().getNombre());
        System.out.println("Tarjeta del cliente: " + cliente.getTarjeta().getNumero());
    }
}
