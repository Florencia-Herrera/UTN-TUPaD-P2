package trabajo.práctico.pkg5;

public class Actividad2 {

    public static void main(String[] args) {
        Bateria bateria = new Bateria("Samsung-4500", 4500);
        Celular celular = new Celular("351789456123", "Samsung", "S24", bateria);
        Usuario usuario = new Usuario("Ana Gómez", "40876543");

        celular.setUsuario(usuario); 

        System.out.println(celular);
        System.out.println("Usuario del celular: " + celular.getUsuario().getNombre());
        System.out.println("Batería: " + celular.getBateria());
        System.out.println("Celular del usuario: " + usuario.getCelular().getModelo());
    }
}
