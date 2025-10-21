/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Actividad14 {

    public static void main(String[] args) {
        Proyecto proyecto = new Proyecto("Corto Documental", 15);
        EditorVideo editor = new EditorVideo();

        editor.exportar("MP4", proyecto); // dependencia de creación
    }
}
