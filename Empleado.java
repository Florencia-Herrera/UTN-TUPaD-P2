package tp4_empleados;

public class Empleado {
    // Atributos privados
    private int id;
    private String nombre;
    private String puesto;
    private double salario;

    // Atributos estáticos (compartidos)
    private static int totalEmpleados = 0;
    private static int siguienteId = 1;
    private static final double SALARIO_POR_DEFECTO = 100000.0;

    // Constructor con todos los parámetros
    public Empleado(int id, String nombre, String puesto, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        totalEmpleados++;

        if (id >= siguienteId) {
            siguienteId = id + 1;
        }
    }

    // Constructor con nombre y puesto (usa valores por defecto)
    public Empleado(String nombre, String puesto) {
        this.id = siguienteId++;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = SALARIO_POR_DEFECTO;
        totalEmpleados++;
    }

    // Sobrecarga: aumentar salario por porcentaje
    public void actualizarSalario(int porcentaje) {
        this.salario += this.salario * (porcentaje / 100.0);
    }

    // Sobrecarga: aumentar salario por monto fijo
    public void actualizarSalario(double aumento) {
        this.salario += aumento;
    }

    // Método toString para mostrar datos legibles
    @Override
    public String toString() {
        return String.format("Empleado{id=%d, nombre='%s', puesto='%s', salario=%.2f}",
                id, nombre, puesto, salario);
    }

    // Método estático para ver la cantidad de empleados creados
    public static int mostrarTotalEmpleados() {
        return totalEmpleados;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}
