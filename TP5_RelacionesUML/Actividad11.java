/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Actividad11 {

    public static void main(String[] args) {
        Artista artista = new Artista("Gustavo Cerati", "Rock");
        Cancion cancion = new Cancion("Crimen", artista);
        Reproductor reproductor = new Reproductor();

        reproductor.reproducir(cancion); 
    }
}
