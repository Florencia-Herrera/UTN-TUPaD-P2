/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Actividad12 {

    public static void main(String[] args) {
        Contribuyente contribuyente = new Contribuyente("Marta Ruiz", "27-40256789-3");
        Impuesto impuesto = new Impuesto(120000.0, contribuyente);
        Calculadora calculadora = new Calculadora();

        calculadora.calcular(impuesto); // dependencia de uso
    }
}
