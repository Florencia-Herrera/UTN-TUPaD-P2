/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Actividad8 {

    public static void main(String[] args) {
        Usuario usuario = new Usuario("Andrés Molina", "andres.molina@mail.com");
        Documento documento = new Documento(
            "Contrato de Servicios",
            "Contenido del contrato...",
            "HASH123ABC",
            "2025-10-16",
            usuario
        );

        System.out.println(documento);
        System.out.println("Usuario que firmó: " + documento.getFirma().getUsuario().getNombre());
        System.out.println("Código de firma: " + documento.getFirma().getCodigoHash());
    }

}
