package tp6_colecciones;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** * TP6 - Colecciones: Inventario con ArrayList y Enum*/
public class TP6_Colecciones {

    public static void main(String[] args) {
        // 1) Crear inventario y cargar al menos 5 productos
        Inventario inventario = new Inventario();

        inventario.agregarProducto(new Producto("P001", "Leche", 950.0, 30, CategoriaProducto.ALIMENTOS));
        inventario.agregarProducto(new Producto("P002", "Smartphone", 250000.0, 12, CategoriaProducto.ELECTRONICA));
        inventario.agregarProducto(new Producto("P003", "Remera", 6500.0, 50, CategoriaProducto.ROPA));
        inventario.agregarProducto(new Producto("P004", "Tostadora", 32000.0, 7, CategoriaProducto.HOGAR));
        inventario.agregarProducto(new Producto("P005", "Cafe", 1800.0, 20, CategoriaProducto.ALIMENTOS));

        System.out.println("\n--- 2) Listar productos ---");
        inventario.listarProductos();

        System.out.println("\n--- 3) Buscar por ID (P003) ---");
        Producto buscado = inventario.buscarProductoPorId("P003");
        System.out.println(buscado != null ? buscado : "No encontrado");

        System.out.println("\n--- 4) Filtrar por categoria ALIMENTOS ---");
        List<Producto> alimentos = inventario.filtrarPorCategoria(CategoriaProducto.ALIMENTOS);
        alimentos.forEach(System.out::println);

        System.out.println("\n--- 5) Eliminar producto P004 y listar ---");
        inventario.eliminarProducto("P004");
        inventario.listarProductos();

        System.out.println("\n--- 6) Actualizar stock de P002 a 25 ---");
        inventario.actualizarStock("P002", 25);
        inventario.listarProductos();

        System.out.println("\n--- 7) Total de stock disponible ---");
        System.out.println("Total unidades en stock: " + inventario.obtenerTotalStock());

        System.out.println("\n--- 8) Producto con mayor stock ---");
        Producto mayor = inventario.obtenerProductoConMayorStock();
        System.out.println(mayor != null ? mayor : "No hay productos");

        System.out.println("\n--- 9) Filtrar por precio entre 1.000 y 3.000 ---");
        List<Producto> entre = inventario.filtrarProductosPorPrecio(1000, 3000);
        entre.forEach(System.out::println);

        System.out.println("\n--- 10) Categorias disponibles ---");
        inventario.mostrarCategoriasDisponibles();
    }
}

/*  ENUM*/
enum CategoriaProducto {
    ALIMENTOS("Productos comestibles"),
    ELECTRONICA("Dispositivos electronicos"),
    ROPA("Prendas de vestir"),
    HOGAR("Articulos para el hogar");

    private final String descripcion;

    CategoriaProducto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

/*MODELO*/
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

    // Getters/setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public CategoriaProducto getCategoria() { return categoria; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public void mostrarInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Producto{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", precio=" + precio +
               ", cantidad=" + cantidad +
               ", categoria=" + categoria +
               " (" + categoria.getDescripcion() + ")}";
    }
}

/*  INVENTARIO */
class Inventario {
    private final ArrayList<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        // Evita duplicados por id
        if (buscarProductoPorId(p.getId()) == null) {
            productos.add(p);
        } else {
            System.out.println("Ya existe un producto con id " + p.getId());
        }
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("(Sin productos)");
            return;
        }
        // Ordenador opcional por nombre para salida prolija
        productos.stream()
                 .sorted(Comparator.comparing(Producto::getNombre))
                 .forEach(System.out::println);
    }

    public Producto buscarProductoPorId(String id) {
        for (Producto p : productos) {
            if (p.getId().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    public boolean eliminarProducto(String id) {
        Producto p = buscarProductoPorId(id);
        if (p != null) {
            return productos.remove(p);
        }
        System.out.println("No se encontro el producto con id " + id);
        return false;
    }

    public boolean actualizarStock(String id, int nuevaCantidad) {
        Producto p = buscarProductoPorId(id);
        if (p != null) {
            p.setCantidad(nuevaCantidad);
            return true;
        }
        System.out.println("No se encontro el producto con id " + id);
        return false;
    }

    public List<Producto> filtrarPorCategoria(CategoriaProducto categoria) {
        ArrayList<Producto> out = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria() == categoria) out.add(p);
        }
        return out;
    }

    public int obtenerTotalStock() {
        int total = 0;
        for (Producto p : productos) total += p.getCantidad();
        return total;
    }

    public Producto obtenerProductoConMayorStock() {
        if (productos.isEmpty()) return null;
        Producto max = productos.get(0);
        for (Producto p : productos) {
            if (p.getCantidad() > max.getCantidad()) max = p;
        }
        return max;
    }

    public List<Producto> filtrarProductosPorPrecio(double min, double max) {
        ArrayList<Producto> out = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getPrecio() >= min && p.getPrecio() <= max) out.add(p);
        }
        return out;
    }

    public void mostrarCategoriasDisponibles() {
        for (CategoriaProducto c : CategoriaProducto.values()) {
            System.out.println(c.name() + " → " + c.getDescripcion());
        }
    }
}
