/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Actividad13 {

    public static void main(String[] args) {
        Usuario usuario = new Usuario("Lucía Torres", "lucia.torres@example.com");
        GeneradorQR generador = new GeneradorQR();

        generador.generar("QR-VALOR-001", usuario); // dependencia de creación
    }
}
