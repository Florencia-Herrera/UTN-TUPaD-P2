package tp4_empleados;

public class PruebaEmpleados {
    public static void main(String[] args) {
        // Crear empleados con distintos constructores
        Empleado e1 = new Empleado(1001, "Florencia Herrera", "Desarrolladora", 150000);
        Empleado e2 = new Empleado("Alexis Noir", "QA");
        Empleado e3 = new Empleado("Melanie Ibarra", "Soporte");

        // Actualizar salarios con sobrecarga
        e1.actualizarSalario(5);          // Aumento del 5%
        e2.actualizarSalario(10);         // Aumento del 10%
        e3.actualizarSalario(25000.0);    // Aumento fijo

        // Mostrar información de cada empleado
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);

        // Mostrar total de empleados
        System.out.println("Total de empleados: " + Empleado.mostrarTotalEmpleados());
    }
}
