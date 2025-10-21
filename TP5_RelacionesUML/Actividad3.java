package trabajo.práctico.pkg5;

public class Actividad3 {

    public static void main(String[] args) {
        Autor autor = new Autor("Jorge Luis Borges", "Argentina");
        Editorial editorial = new Editorial("Sudamericana", "Av. de Mayo 1234, Buenos Aires");
        Libro libro = new Libro("Ficciones", "978-987-1138-01-5", autor, editorial);

        System.out.println(libro);
        System.out.println("Autor del libro: " + libro.getAutor().getNombre());
        System.out.println("Editorial: " + libro.getEditorial().getNombre());
    }
    
}
