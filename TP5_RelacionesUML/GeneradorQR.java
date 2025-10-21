/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo.práctico.pkg5;

public class GeneradorQR {
    public void generar(String valor, Usuario usuario) {
        CodigoQR codigo = new CodigoQR(valor, usuario);
        System.out.println("Se generó un nuevo código QR:");
        System.out.println(codigo);
    }    
}
