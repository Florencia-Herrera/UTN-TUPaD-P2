
import java.util.ArrayList;
import java.util.List;

//  Clase Autor 
class Autor {
    private String id;
    private String nombre;
    private String nacionalidad;

    public Autor(String id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public void mostrarInfo() {
        System.out.println(this);
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Autor{id='" + id + "', nombre='" + nombre + "', nacionalidad='" + nacionalidad + "'}";
    }
}

//  Clase Libro 
class Libro {
    private String isbn;
    private String titulo;
    private int anioPublicacion;
    private Autor autor; // Asociación con Autor

    public Libro(String isbn, String titulo, int anioPublicacion, Autor autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public Autor getAutor() {
        return autor;
    }

    public void mostrarInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Libro{ISBN='" + isbn + "', título='" + titulo + "', año=" + anioPublicacion +
               ", autor=" + (autor != null ? autor.getNombre() : "Desconocido") + "}";
    }
}

//  Clase Biblioteca
class Biblioteca {
    private String nombre;
    private List<Libro> libros = new ArrayList<>();

    public Biblioteca(String nombre) {
        this.nombre = nombre;
    }

    // Composición: la biblioteca crea y contiene sus libros
    public void agregarLibro(String isbn, String titulo, int anioPublicacion, Autor autor) {
        libros.add(new Libro(isbn, titulo, anioPublicacion, autor));
    }

    public void listarLibros() {
        System.out.println("\n--- Libros en la biblioteca \"" + nombre + "\" ---");
        if (libros.isEmpty()) {
            System.out.println("No hay libros cargados.");
        } else {
            for (Libro l : libros) {
                l.mostrarInfo();
            }
        }
    }

    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro l : libros) {
            if (l.getIsbn().equalsIgnoreCase(isbn)) {
                return l;
            }
        }
        return null;
    }

    public boolean eliminarLibro(String isbn) {
        return libros.removeIf(l -> l.getIsbn().equalsIgnoreCase(isbn));
    }

    public int obtenerCantidadLibros() {
        return libros.size();
    }

    public List<Libro> filtrarLibrosPorAnio(int anio) {
        List<Libro> filtrados = new ArrayList<>();
        for (Libro l : libros) {
            if (l.getAnioPublicacion() == anio) {
                filtrados.add(l);
            }
        }
        return filtrados;
    }

    public void mostrarAutoresDisponibles() {
        System.out.println("\n--- Autores disponibles en \"" + nombre + "\" ---");
        for (Libro l : libros) {
            if (l.getAutor() != null) {
                System.out.println(l.getAutor());
            }
        }
    }
}

// Clase principal (Main) 
public class MainBiblioteca {
    public static void main(String[] args) {
        // 1) Crear biblioteca
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");

        // 2) Crear autores
        Autor a1 = new Autor("A01", "Jorge Luis Borges", "Argentina");
        Autor a2 = new Autor("A02", "Julio Cortázar", "Argentina");
        Autor a3 = new Autor("A03", "Isabel Allende", "Chile");

        // 3) Agregar libros (composición)
        biblioteca.agregarLibro("ISBN01", "Ficciones", 1944, a1);
        biblioteca.agregarLibro("ISBN02", "Rayuela", 1963, a2);
        biblioteca.agregarLibro("ISBN03", "La casa de los espíritus", 1982, a3);
        biblioteca.agregarLibro("ISBN04", "El Aleph", 1949, a1);
        biblioteca.agregarLibro("ISBN05", "Bestiario", 1951, a2);

        // 4) Listar todos los libros
        biblioteca.listarLibros();

        // 5) Buscar libro por ISBN
        System.out.println("\n== Buscar libro ISBN03 ==");
        Libro encontrado = biblioteca.buscarLibroPorIsbn("ISBN03");
        System.out.println(encontrado != null ? encontrado : "No encontrado.");

        // 6) Filtrar libros por año
        System.out.println("\n== Libros publicados en 1949 ==");
        for (Libro l : biblioteca.filtrarLibrosPorAnio(1949)) {
            System.out.println(l);
        }

        // 7) Eliminar un libro por ISBN
        System.out.println("\n== Eliminar libro ISBN02 ==");
        System.out.println(biblioteca.eliminarLibro("ISBN02") ? "Eliminado correctamente." : "No se encontró el libro.");
        biblioteca.listarLibros();

        // 8) Mostrar cantidad total de libros
        System.out.println("\nCantidad total de libros: " + biblioteca.obtenerCantidadLibros());

        // 9) Mostrar autores disponibles
        biblioteca.mostrarAutoresDisponibles();
    }
}
