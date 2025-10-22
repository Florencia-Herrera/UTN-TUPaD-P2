
import java.util.ArrayList;
import java.util.Comparator;

// Enum de categorías con descripción
enum CategoriaProducto {
    ALIMENTOS("Productos comestibles"),
    ELECTRONICA("Dispositivos electrónicos"),
    ROPA("Prendas de vestir"),
    HOGAR("Artículos para el hogar");

    private final String descripcion;

    CategoriaProducto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

// Modelo de producto
class Producto {
    private String id;                 // identificador único
    private String nombre;             // nombre del producto
    private double precio;             // precio
    private int cantidad;              // stock disponible
    private CategoriaProducto categoria; // categoría del enum

    public Producto(String id, String nombre, double precio, int cantidad, CategoriaProducto categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    // Muestra la info del producto
    public void mostrarInfo() {
        System.out.println(this);
    }

    // Getters mínimos usados por Inventario
    public String getId() { return id; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public CategoriaProducto getCategoria() { return categoria; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return "Producto{id='" + id + "', nombre='" + nombre + "', precio=" + precio +
               ", cant=" + cantidad + ", categoria=" + categoria + "}";
    }
}

// Inventario que maneja la lista dinámica de productos
class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();

    // Agregar un producto
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    // Listar todos los productos
    public void listarProductos() {
        for (Producto p : productos) {
            p.mostrarInfo();
        }
    }

    // Buscar por ID (devuelve null si no está)
    public Producto buscarProductoPorId(String id) {
        for (Producto p : productos) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    // Eliminar por ID (true si lo eliminó)
    public boolean eliminarProducto(String id) {
        return productos.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }

    // Actualizar stock por ID (true si se pudo)
    public boolean actualizarStock(String id, int nuevaCantidad) {
        Producto p = buscarProductoPorId(id);
        if (p != null) {
            p.setCantidad(nuevaCantidad);
            return true;
        }
        return false;
    }

    // Filtrar por categoría
    public ArrayList<Producto> filtrarPorCategoria(CategoriaProducto categoria) {
        ArrayList<Producto> out = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria() == categoria) {
                out.add(p);
            }
        }
        return out;
    }

    // Total de unidades en stock (suma de cantidades)
    public int obtenerTotalStock() {
        int total = 0;
        for (Producto p : productos) {
            total += p.getCantidad();
        }
        return total;
    }

    // Producto con mayor stock (null si no hay)
    public Producto obtenerProductoConMayorStock() {
        if (productos.isEmpty()) return null;
        return productos.stream().max(Comparator.comparingInt(Producto::getCantidad)).orElse(null);
    }

    // Filtra por rango de precio [min, max]
    public ArrayList<Producto> filtrarProductosPorPrecio(double min, double max) {
        ArrayList<Producto> out = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getPrecio() >= min && p.getPrecio() <= max) {
                out.add(p);
            }
        }
        return out;
    }

    // Muestra todas las categorías con su descripción
    public void mostrarCategoriasDisponibles() {
        for (CategoriaProducto c : CategoriaProducto.values()) {
            System.out.println(c + " -> " + c.getDescripcion());
        }
    }
}

// Clase con pruebas pedidas en el enunciado
public class MainStock {
    public static void main(String[] args) {
        Inventario inv = new Inventario();

        // 1) Crear 5 productos y agregarlos
        inv.agregarProducto(new Producto("P01","Leche",1200, 50, CategoriaProducto.ALIMENTOS));
        inv.agregarProducto(new Producto("P02","TV",250000, 5, CategoriaProducto.ELECTRONICA));
        inv.agregarProducto(new Producto("P03","Remera",8000, 30, CategoriaProducto.ROPA));
        inv.agregarProducto(new Producto("P04","Silla",15000, 12, CategoriaProducto.HOGAR));
        inv.agregarProducto(new Producto("P05","Yerba",2500, 100, CategoriaProducto.ALIMENTOS));

        // 2) Listar todos con su info
        System.out.println("== LISTA COMPLETA ==");
        inv.listarProductos();

        // 3) Buscar por ID
        System.out.println("\n== BUSCAR P03 ==");
        Producto buscado = inv.buscarProductoPorId("P03");
        System.out.println(buscado != null ? buscado : "No encontrado");

        // 4) Filtrar por categoría
        System.out.println("\n== ALIMENTOS ==");
        for (Producto p : inv.filtrarPorCategoria(CategoriaProducto.ALIMENTOS)) {
            System.out.println(p);
        }

        // 5) Eliminar por ID y listar de nuevo
        System.out.println("\n== ELIMINAR P02 ==");
        System.out.println(inv.eliminarProducto("P02") ? "Eliminado" : "No estaba");
        inv.listarProductos();

        // 6) Actualizar stock de un producto existente
        System.out.println("\n== ACTUALIZA STOCK P05 ==");
        inv.actualizarStock("P05", 80);
        System.out.println(inv.buscarProductoPorId("P05"));

        // 7) Mostrar total de stock
        System.out.println("\nTotal stock = " + inv.obtenerTotalStock());

        // 8) Producto con mayor stock
        System.out.println("\nMayor stock = " + inv.obtenerProductoConMayorStock());

        // 9) Filtrar por precio entre 1000 y 3000
        System.out.println("\n== Precio entre 1000 y 3000 ==");
        ArrayList<Producto> rango = inv.filtrarProductosPorPrecio(1000, 3000);
        for (Producto p : rango) {
            System.out.println(p);
        }

        // 10) Categorías disponibles con descripción
        System.out.println("\n== CATEGORÍAS DISPONIBLES ==");
        inv.mostrarCategoriasDisponibles();
    }
}
