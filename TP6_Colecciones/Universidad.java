// Relación bidireccional 1 a N: un Profesor dicta muchos Cursos, y cada Curso tiene un Profesor.

import java.util.ArrayList;
import java.util.List;

// ---------- Clase Profesor ----------
class Profesor {
    private String id;
    private String nombre;
    private String especialidad;
    private List<Curso> cursos;  // Relación 1 a N (lista de cursos que dicta)

    public Profesor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cursos = new ArrayList<>();
    }

    // Agrega un curso y sincroniza la relación
    public void agregarCurso(Curso c) {
        if (!cursos.contains(c)) {
            cursos.add(c);
            if (c.getProfesor() != this) {
                c.setProfesor(this); // sincroniza el otro lado
            }
        }
    }

    // Elimina un curso y sincroniza la relación inversa
    public void eliminarCurso(Curso c) {
        if (cursos.remove(c)) {
            if (c.getProfesor() == this) {
                c.setProfesor(null);
            }
        }
    }

    // Muestra todos los cursos del profesor
    public void listarCursos() {
        System.out.println("\nCursos dictados por " + nombre + ":");
        if (cursos.isEmpty()) {
            System.out.println("  (ninguno)");
        } else {
            for (Curso c : cursos) {
                System.out.println("  - " + c.getCodigo() + " | " + c.getNombre());
            }
        }
    }

    // Info general
    public void mostrarInfo() {
        System.out.println("Profesor{id='" + id + "', nombre='" + nombre +
                "', especialidad='" + especialidad + "', cantidadCursos=" + cursos.size() + "}");
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public List<Curso> getCursos() { return cursos; }

    @Override
    public String toString() {
        return nombre + " (" + especialidad + ")";
    }
}

// ---------- Clase Curso ----------
class Curso {
    private String codigo;
    private String nombre;
    private Profesor profesor; // Relación 1 (un curso tiene un profesor)

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    // Sincroniza relación bidireccional
    public void setProfesor(Profesor p) {
        if (this.profesor != null && this.profesor != p) {
            this.profesor.eliminarCurso(this);
        }
        this.profesor = p;
        if (p != null && !p.getCursos().contains(this)) {
            p.agregarCurso(this);
        }
    }

    public Profesor getProfesor() { return profesor; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }

    public void mostrarInfo() {
        System.out.println("Curso{codigo='" + codigo + "', nombre='" + nombre +
                "', profesor=" + (profesor != null ? profesor.getNombre() : "Sin asignar") + "}");
    }

    @Override
    public String toString() {
        return nombre + " [" + codigo + "]";
    }
}

// ---------- Clase Universidad ----------
class Universidad {
    private String nombre;
    private List<Profesor> profesores;
    private List<Curso> cursos;

    public Universidad(String nombre) {
        this.nombre = nombre;
        this.profesores = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }

    public void agregarProfesor(Profesor p) { profesores.add(p); }
    public void agregarCurso(Curso c) { cursos.add(c); }

    public Profesor buscarProfesorPorId(String id) {
        for (Profesor p : profesores) {
            if (p.getId().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    public Curso buscarCursoPorCodigo(String codigo) {
        for (Curso c : cursos) {
            if (c.getCodigo().equalsIgnoreCase(codigo)) return c;
        }
        return null;
    }

    public void asignarProfesorACurso(String codigoCurso, String idProfesor) {
        Curso curso = buscarCursoPorCodigo(codigoCurso);
        Profesor profesor = buscarProfesorPorId(idProfesor);
        if (curso != null && profesor != null) {
            curso.setProfesor(profesor);
            System.out.println("✅ Asignado " + profesor.getNombre() + " al curso " + curso.getNombre());
        } else {
            System.out.println("⚠️ No se pudo asignar: verifique los datos.");
        }
    }

    public void listarProfesores() {
        System.out.println("\n=== Profesores de " + nombre + " ===");
        for (Profesor p : profesores) {
            p.mostrarInfo();
        }
    }

    public void listarCursos() {
        System.out.println("\n=== Cursos de " + nombre + " ===");
        for (Curso c : cursos) {
            c.mostrarInfo();
        }
    }

    // Eliminar curso y romper relación
    public void eliminarCurso(String codigo) {
        Curso c = buscarCursoPorCodigo(codigo);
        if (c != null) {
            if (c.getProfesor() != null) {
                c.getProfesor().eliminarCurso(c);
            }
            cursos.remove(c);
            System.out.println("Curso " + codigo + " eliminado.");
        }
    }

    // Eliminar profesor y dejar cursos sin asignar
    public void eliminarProfesor(String id) {
        Profesor p = buscarProfesorPorId(id);
        if (p != null) {
            for (Curso c : new ArrayList<>(p.getCursos())) {
                c.setProfesor(null);
            }
            profesores.remove(p);
            System.out.println("Profesor " + id + " eliminado y cursos actualizados.");
        }
    }

    // Reporte: cantidad de cursos por profesor
    public void reporteCursosPorProfesor() {
        System.out.println("\n=== Reporte: Cantidad de cursos por profesor ===");
        for (Profesor p : profesores) {
            System.out.println(p.getNombre() + " dicta " + p.getCursos().size() + " curso(s).");
        }
    }
}

// ---------- Clase principal ----------
public class MainUniversidad {
    public static void main(String[] args) {
        Universidad uni = new Universidad("UTN Virtual");

        // 1) Crear profesores
        Profesor p1 = new Profesor("P01", "Ana Torres", "Matemática");
        Profesor p2 = new Profesor("P02", "Carlos Pérez", "Programación");
        Profesor p3 = new Profesor("P03", "Laura Díaz", "Estadística");

        // 2) Crear cursos
        Curso c1 = new Curso("C01", "Álgebra");
        Curso c2 = new Curso("C02", "Programación I");
        Curso c3 = new Curso("C03", "Programación II");
        Curso c4 = new Curso("C04", "Probabilidad");
        Curso c5 = new Curso("C05", "Bases de Datos");

        // 3) Agregar a la universidad
        uni.agregarProfesor(p1);
        uni.agregarProfesor(p2);
        uni.agregarProfesor(p3);

        uni.agregarCurso(c1);
        uni.agregarCurso(c2);
        uni.agregarCurso(c3);
        uni.agregarCurso(c4);
        uni.agregarCurso(c5);

        // 4) Asignar profesores a cursos
        uni.asignarProfesorACurso("C01", "P01");
        uni.asignarProfesorACurso("C02", "P02");
        uni.asignarProfesorACurso("C03", "P02");
        uni.asignarProfesorACurso("C04", "P03");
        uni.asignarProfesorACurso("C05", "P03");

        // 5) Listar información
        uni.listarProfesores();
        uni.listarCursos();

        // 6) Cambiar profesor de un curso
        System.out.println("\n== Cambio de profesor en curso C05 ==");
        Curso curso = uni.buscarCursoPorCodigo("C05");
        if (curso != null) curso.setProfesor(p1);

        // 7) Eliminar un curso y verificar sincronización
        System.out.println("\n== Eliminar curso C01 ==");
        uni.eliminarCurso("C01");

        // 8) Eliminar un profesor y dejar cursos sin asignar
        System.out.println("\n== Eliminar profesor P02 ==");
        uni.eliminarProfesor("P02");

        // 9) Reporte final
        uni.reporteCursosPorProfesor();
    }
}
