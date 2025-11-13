package tp6_universidad;
import java.util.ArrayList;
import java.util.List;

class Profesor {
    private String id;
    private String nombre;
    private String especialidad;
    private List<Curso> cursos = new ArrayList<>();

    public Profesor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public List<Curso> getCursos() { return cursos; }

    public void agregarCurso(Curso c) {
        if (!cursos.contains(c)) {
            cursos.add(c);
        }
        if (c.getProfesor() != this) {
            c.setProfesor(this);
        }
    }

    public void eliminarCurso(Curso c) {
        cursos.remove(c);
        if (c.getProfesor() == this) {
            c.setProfesor(null);
        }
    }

    public void listarCursos() {
        System.out.println("Cursos de " + nombre + ":");
        for (Curso c : cursos) {
            System.out.println("- " + c.getCodigo() + " " + c.getNombre());
        }
        System.out.println();
    }

    public void mostrarInfo() {
        System.out.println("Profesor " + nombre + " (" + especialidad + ") - ID: " + id +
                " | Cantidad de cursos: " + cursos.size());
    }
}

class Curso {
    private String codigo;
    private String nombre;
    private Profesor profesor;

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public Profesor getProfesor() { return profesor; }

    public void setProfesor(Profesor p) {
        // quitar de profesor anterior
        if (this.profesor != null && this.profesor != p) {
            this.profesor.getCursos().remove(this);
        }
        this.profesor = p;
        // agregar al nuevo profesor
        if (p != null && !p.getCursos().contains(this)) {
            p.getCursos().add(this);
        }
    }

    public void mostrarInfo() {
        String nombreProf = (profesor != null ? profesor.getNombre() : "sin profesor");
        System.out.println("Curso: " + codigo + " - " + nombre + " | Profesor: " + nombreProf);
    }
}

class Universidad {
    private String nombre;
    private List<Profesor> profesores = new ArrayList<>();
    private List<Curso> cursos = new ArrayList<>();

    public Universidad(String nombre) {
        this.nombre = nombre;
    }

    public void agregarProfesor(Profesor p) { profesores.add(p); }
    public void agregarCurso(Curso c) { cursos.add(c); }

    public Profesor buscarProfesorPorId(String id) {
        for (Profesor p : profesores) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    public Curso buscarCursoPorCodigo(String codigo) {
        for (Curso c : cursos) {
            if (c.getCodigo().equals(codigo)) return c;
        }
        return null;
    }

    public void asignarProfesorACurso(String codigoCurso, String idProfesor) {
        Curso curso = buscarCursoPorCodigo(codigoCurso);
        Profesor profesor = buscarProfesorPorId(idProfesor);
        if (curso != null && profesor != null) {
            curso.setProfesor(profesor);
        }
    }

    public void listarProfesores() {
        System.out.println(" PROFESORES EN " + nombre + " ");
        for (Profesor p : profesores) {
            p.mostrarInfo();
            p.listarCursos();
        }
        System.out.println();
    }

    public void listarCursos() {
        System.out.println(" CURSOS EN " + nombre + " ");
        for (Curso c : cursos) {
            c.mostrarInfo();
        }
        System.out.println();
    }

    public void eliminarCurso(String codigo) {
        Curso c = buscarCursoPorCodigo(codigo);
        if (c != null) {
            if (c.getProfesor() != null) {
                c.getProfesor().eliminarCurso(c);
            }
            cursos.remove(c);
        }
    }

    public void eliminarProfesor(String id) {
        Profesor p = buscarProfesorPorId(id);
        if (p != null) {
            // dejar profesor = null en sus cursos
            for (Curso c : new ArrayList<>(p.getCursos())) {
                c.setProfesor(null);
            }
            profesores.remove(p);
        }
    }

    public void reporteCursosPorProfesor() {
        System.out.println(" REPORTE: CANTIDAD DE CURSOS POR PROFESOR ");
        for (Profesor p : profesores) {
            System.out.println(p.getNombre() + ": " + p.getCursos().size() + " curso(s)");
        }
        System.out.println();
    }
}

public class TP6_Universidad {
    public static void main(String[] args) {

        Universidad uni = new Universidad("UTN Virtual");

        // 1) Crear profesores y cursos
        Profesor pr1 = new Profesor("P01", "Ana Gomez", "Programacion");
        Profesor pr2 = new Profesor("P02", "Luis Perez", "Bases de Datos");
        Profesor pr3 = new Profesor("P03", "Carla Ruiz", "Redes");

        uni.agregarProfesor(pr1);
        uni.agregarProfesor(pr2);
        uni.agregarProfesor(pr3);

        Curso c1 = new Curso("PROG1", "Programacion I");
        Curso c2 = new Curso("PROG2", "Programacion II");
        Curso c3 = new Curso("BD1", "Base de Datos I");
        Curso c4 = new Curso("RED1", "Redes I");
        Curso c5 = new Curso("SO1", "Sistemas Operativos I");

        uni.agregarCurso(c1);
        uni.agregarCurso(c2);
        uni.agregarCurso(c3);
        uni.agregarCurso(c4);
        uni.agregarCurso(c5);

        // 3) Asignar profesores a cursos
        uni.asignarProfesorACurso("PROG1", "P01");
        uni.asignarProfesorACurso("PROG2", "P01");
        uni.asignarProfesorACurso("BD1", "P02");
        uni.asignarProfesorACurso("RED1", "P03");

        // 4) Listar cursos y profesores
        uni.listarCursos();
        uni.listarProfesores();

        // 5) Cambiar profesor de un curso
        System.out.println(" CAMBIAR PROFESOR DE PROG2 A P02 ");
        uni.asignarProfesorACurso("PROG2", "P02");
        uni.listarCursos();
        uni.listarProfesores();

        // 6) Remover un curso
        System.out.println(" ELIMINAR CURSO BD1 ");
        uni.eliminarCurso("BD1");
        uni.listarCursos();
        uni.listarProfesores();

        // 7) Remover profesor
        System.out.println(" ELIMINAR PROFESOR P03 ");
        uni.eliminarProfesor("P03");
        uni.listarCursos();
        uni.listarProfesores();

        // 8) Reporte
        uni.reporteCursosPorProfesor();
    }
}
