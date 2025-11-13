package tp5_bibliotecaylibros;
import java.util.ArrayList;
import java.util.List;

class Autor {
    private String id;
    private String nombre;
    private String nacionalidad;

    public Autor(String id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getNacionalidad() { return nacionalidad; }

    public void mostrarInfo() {
        System.out.println("Autor: " + nombre + " (" + nacionalidad + ") - ID: " + id);
    }

    @Override
    public String toString() {
        return nombre + " (" + nacionalidad + ")";
    }
}

class Libro {
    private String isbn;
    private String titulo;
    private int anioPublicacion;
    private Autor autor;

    public Libro(String isbn, String titulo, int anioPublicacion, Autor autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
    }

    public String getIsbn() { return isbn; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public Autor getAutor() { return autor; }

    public void mostrarInfo() {
        System.out.println("Libro: " + titulo +
                " | ISBN: " + isbn +
                " | Año: " + anioPublicacion +
                " | Autor: " + autor);
    }
}

class Biblioteca {
    private String nombre;
    private List<Libro> libros = new ArrayList<>();

    public Biblioteca(String nombre) {
        this.nombre = nombre;
    }

    public void agregarLibro(String isbn, String titulo, int anioPublicacion, Autor autor) {
        libros.add(new Libro(isbn, titulo, anioPublicacion, autor));
    }

    public void listarLibros() {
        System.out.println(" LIBROS EN " + nombre + " ");
        for (Libro l : libros) {
            l.mostrarInfo();
        }
        System.out.println();
    }

    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro l : libros) {
            if (l.getIsbn().equals(isbn)) return l;
        }
        return null;
    }

    public void eliminarLibro(String isbn) {
        libros.removeIf(l -> l.getIsbn().equals(isbn));
    }

    public int obtenerCantidadLibros() {
        return libros.size();
    }

    public void filtrarLibrosPorAnio(int anio) {
        System.out.println("LIBROS DEL AÑO " + anio + " ");
        for (Libro l : libros) {
            if (l.getAnioPublicacion() == anio) {
                l.mostrarInfo();
            }
        }
        System.out.println();
    }

    public void mostrarAutoresDisponibles() {
        System.out.println(" AUTORES DISPONIBLES ");
        ArrayList<String> nombres = new ArrayList<>();
        for (Libro l : libros) {
            String autorStr = l.getAutor().toString();
            if (!nombres.contains(autorStr)) {
                nombres.add(autorStr);
                System.out.println(autorStr);
            }
        }
        System.out.println();
    }
}

public class TP5_BibliotecayLibros {
    public static void main(String[] args) {

        // 1) Creamos biblioteca
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");

        // 2) Creamos autores
        Autor a1 = new Autor("A001", "Julio Cortazar", "Argentina");
        Autor a2 = new Autor("A002", "Isabel Allende", "Chile");
        Autor a3 = new Autor("A003", "Gabriel Garcia Marquez", "Colombia");

        // 3) Agregamos 5 libros
        biblioteca.agregarLibro("ISBN001", "Rayuela", 1963, a1);
        biblioteca.agregarLibro("ISBN002", "Bestiario", 1951, a1);
        biblioteca.agregarLibro("ISBN003", "La casa de los espiritus", 1982, a2);
        biblioteca.agregarLibro("ISBN004", "Cien años de soledad", 1967, a3);
        biblioteca.agregarLibro("ISBN005", "El amor en los tiempos del colera", 1985, a3);

        // 4) Listar libros
        biblioteca.listarLibros();

        // 5) Buscar por ISBN
        System.out.println("BUSCAR ISBN003 ");
        Libro buscado = biblioteca.buscarLibroPorIsbn("ISBN003");
        if (buscado != null) buscado.mostrarInfo();
        System.out.println();

        // 6) Filtrar por año
        biblioteca.filtrarLibrosPorAnio(1967);

        // 7) Eliminar libro por ISBN
        biblioteca.eliminarLibro("ISBN002");
        System.out.println(" LISTA LUEGO DE ELIMINAR ISBN002 ");
        biblioteca.listarLibros();

        // 8) Cantidad total de libros
        System.out.println("Cantidad total de libros: " + biblioteca.obtenerCantidadLibros());
        System.out.println();

        // 9) Listar autores
        biblioteca.mostrarAutoresDisponibles();
    }
}

   
