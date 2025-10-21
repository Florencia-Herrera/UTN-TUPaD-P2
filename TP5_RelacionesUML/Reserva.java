/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Reserva {
    private String fecha;
    private String hora;
    private Cliente cliente1;
    private Mesa mesa;

    public Reserva(String fecha, String hora, Cliente cliente, Mesa mesa) {
        this.fecha = fecha;
        this.hora = hora;
        this.cliente1 = cliente; // asociación unidireccional
        this.mesa = mesa;       // agregación
    }

    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public Cliente getCliente() { return cliente1; }
    public Mesa getMesa() { return mesa; }

    @Override
    public String toString() {
        return "Reserva [fecha=" + fecha + ", hora=" + hora + 
               ", cliente=" + cliente1 + ", mesa=" + mesa + "]";
    }    
}
