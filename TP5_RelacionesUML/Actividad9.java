/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajo.práctico.pkg5;

public class Actividad9 {
    public static void main(String[] args) {
        Paciente paciente = new Paciente("Sofía Gómez", "OSDE");
        Profesional profesional = new Profesional("Dr. Ricardo López", "Cardiología");

        CitaMedica cita = new CitaMedica("2025-11-03", "10:30", paciente, profesional);

        System.out.println(cita);
        System.out.println("Paciente: " + cita.getPaciente().getNombre());
        System.out.println("Profesional: " + cita.getProfesional().getNombre());
    }
}
