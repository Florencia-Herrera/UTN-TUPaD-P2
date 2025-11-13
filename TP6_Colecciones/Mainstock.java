package tp6_stock;

import java.util.ArrayList;

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

class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int cantidad;
    private CategoriaProducto categoria;

    public Producto(String id, String nombre, double precio, int cantidad, CategoriaProducto categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public CategoriaProducto getCategoria() { return categoria; }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void mostrarInfo() {
        System.out.println("ID: " + id +
                " | Nombre: " + nombre +
                " | Precio: $" + precio +
                " | Cantidad: " + cantidad +
                " | Categoria: " + categoria +
                " (" + categoria.getDescripcion() + ")");
    }

    @Override
    public String toString() {
        return nombre + " (ID " + id + ")";
    }
}

class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void listarProductos() {
        System.out.println("LISTA DE PRODUCTOS ");
        for (Producto p : productos) {
            p.mostrarInfo();
        }
        System.out.println();
    }

    public Producto buscarProductoPorId(String id) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void eliminarProducto(String id) {
        productos.removeIf(p -> p.getId().equals(id));
    }

    public void actualizarStock(String id, int nuevaCantidad) {
        Producto p = buscarProductoPorId(id);
        if (p != null) {
            p.setCantidad(nuevaCantidad);
        }
    }

    public void filtrarPorCategoria(CategoriaProducto categoria) {
        System.out.println(" PRODUCTOS DE CATEGORIA " + categoria +"" );
        for (Producto p : productos) {
            if (p.getCategoria() == categoria) {
                p.mostrarInfo();
            }
        }
        System.out.println();
    }

    public int obtenerTotalStock() {
        int total = 0;
        for (Producto p : productos) {
            total += p.getCantidad();
        }
        return total;
    }

    public Producto obtenerProductoConMayorStock() {
        if (productos.isEmpty()) return null;
        Producto mayor = productos.get(0);
        for (Producto p : productos) {
            if (p.getCantidad() > mayor.getCantidad()) {
                mayor = p;
            }
        }
        return mayor;
    }

    public void filtrarProductosPorPrecio(double min, double max) {
        System.out.println("PRODUCTOS ENTRE $" + min + " y $" + max + " ");
        for (Producto p : productos) {
            if (p.getPrecio() >= min && p.getPrecio() <= max) {
                p.mostrarInfo();
            }
        }
        System.out.println();
    }

    public void mostrarCategoriasDisponibles() {
        System.out.println(" CATEGORIAS DISPONIBLES ");
        for (CategoriaProducto c : CategoriaProducto.values()) {
            System.out.println(c + " - " + c.getDescripcion());
        }
        System.out.println();
    }
}

public class TP6_Stock {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        // 1) Crear 5 productos
        inventario.agregarProducto(new Producto("P001", "Arroz", 1500, 20, CategoriaProducto.ALIMENTOS));
        inventario.agregarProducto(new Producto("P002", "Laptop", 250000, 5, CategoriaProducto.ELECTRONICA));
        inventario.agregarProducto(new Producto("P003", "Remera", 3500, 15, CategoriaProducto.ROPA));
        inventario.agregarProducto(new Producto("P004", "Cafetera", 18000, 8, CategoriaProducto.HOGAR));
        inventario.agregarProducto(new Producto("P005", "Fideos", 1200, 40, CategoriaProducto.ALIMENTOS));

        // 2) Listar todos
        inventario.listarProductos();

        // 3) Buscar por ID
        System.out.println(" BUSCAR P003 ");
        Producto buscado = inventario.buscarProductoPorId("P003");
        if (buscado != null) buscado.mostrarInfo();
        System.out.println();

        // 4) Filtrar por categoría
        inventario.filtrarPorCategoria(CategoriaProducto.ALIMENTOS);

        // 5) Eliminar por ID
        inventario.eliminarProducto("P002");
        System.out.println(" LISTA LUEGO DE ELIMINAR P002 ");
        inventario.listarProductos();

        // 6) Actualizar stock
        inventario.actualizarStock("P005", 60);
        System.out.println("P005 CON STOCK ACTUALIZADO ");
        inventario.buscarProductoPorId("P005").mostrarInfo();
        System.out.println();

        // 7) Total stock
        System.out.println("Total de unidades en stock: " + inventario.obtenerTotalStock());
        System.out.println();

        // 8) Producto con mayor stock
        Producto mayor = inventario.obtenerProductoConMayorStock();
        System.out.println("Producto con mayor stock: " + mayor);
        System.out.println();

        // 9) Filtrar por rango de precio
        inventario.filtrarProductosPorPrecio(1000, 3000);

        // 10) Mostrar categorías disponibles
        inventario.mostrarCategoriasDisponibles();
    }
}
