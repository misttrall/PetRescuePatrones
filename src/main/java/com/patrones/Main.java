package com.patrones;

import java.util.Scanner;

import com.patrones.modelo.Exportable;
import com.patrones.modelo.IteradorMascotas;
import com.patrones.modelo.Mascota;
import com.patrones.modelo.MascotaAdapter;
import com.patrones.modelo.Refugio;
import com.patrones.modelo.UsuarioInteresado;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Refugio refugio = Refugio.getInstancia();
        int opcion;

        do {
            System.out.println("  ----------------------------------");
            System.out.println("  | MENÚ PETRESCUE                 |");
            System.out.println("  |1. Registrar mascota            |");
            System.out.println("  |2. Listar mascotas              |");
            System.out.println("  |3. Adoptar mascota              |");
            System.out.println("  |4. Suscribir usuario interesado |");
            System.out.println("  |5. Buscar mascotas por especie  |");
            System.out.println("  |0. Salir                        |");
            System.out.println("  ----------------------------------");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {

                case 1:
                // Registrar mascota
                    // Solicita datos para mascota. (Nombre y especie)
                    System.out.print("Nombre de la mascota: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Especie de la mascota: ");
                    String especie = scanner.nextLine();
                    // Crea nueva instancia de mascota con los datos ingresados
                    Mascota nuevaMascota = new Mascota(nombre, especie);
                    // Registra a la mascota en refugio (el cual sigue patrón singleton)
                    refugio.registrarMascota(nuevaMascota);
                    break;

                case 2:
                // Listar mascotas
                    System.out.println("\nEstas son nuestras mascotas para adopción:");
                    // Se obtiene un iterator personalizado desde el refugio (patrón Iterator)
                    IteradorMascotas it = refugio.crearIterador();
                    // Se recorre cada mascota usando el iterator
                    while (it.tieneSiguiente()) {
                        Mascota m = it.siguiente();
                        // Se adapta la mascota a la interfaz Exportable (patrón Adapter)
                        Exportable adaptada = new MascotaAdapter(m);
                        // Se imprime la representación exportada de la mascota
                        System.out.println(adaptada.exportar());
                    }
                    break;

                case 3:
                // Adoptar mascota
                    // Muestra la lista de mascotas disponibles para adopción
                    System.out.println("Mascotas listas para adoptar:");
                    IteradorMascotas itAdoptar = refugio.crearIterador();
                    while (itAdoptar.tieneSiguiente()) {
                        Mascota m = itAdoptar.siguiente();
                        System.out.println("- " + m.getNombre() + " (" + m.getEspecie() + ")");
                    }
                    // Solicita el nombre de la mascota y del adoptante
                    System.out.print("Nombre de la mascota a adoptar: ");
                    String nombreMascota = scanner.nextLine();
                    System.out.print("Nombre del adoptante: ");
                    String nombreAdoptante = scanner.nextLine();
                    // Verifica si el adoptante está registrado como usuario interesado
                    if (!refugio.existeUsuario(nombreAdoptante)) {
                        System.out.println("El adoptante no está registrado como interesado.");
                        break;
                    }
                    // Intenta realizar la adopción
                    boolean adoptada = refugio.adoptarMascota(nombreMascota, nombreAdoptante);
                    if (adoptada) {
                        System.out.println("Mascota adoptada por " + nombreAdoptante + " correctamente.");
                    } else {
                        System.out.println("Mascota no encontrada.");
                    }
                    break;
                case 4:
                // Suscribir a un usuario interesado en adoptar
                    System.out.print("Nombre del usuario: ");
                    String usuario = scanner.nextLine();
                    System.out.print("Especie de interés: ");
                    String interes = scanner.nextLine();
                    UsuarioInteresado nuevoUsuario = new UsuarioInteresado(usuario, interes);
                    refugio.suscribir(nuevoUsuario);
                    // Notifica al usuario si ya hay mascotas disponibles de esa especie
                    IteradorMascotas iter = refugio.crearIterador();
                    while (iter.tieneSiguiente()) {
                        Mascota m = iter.siguiente();
                        if (m.getEspecie().equalsIgnoreCase(interes)) {
                            nuevoUsuario.notificar(m); // Notificación directa al usuario registrado (Uso de Observer)
                        }
                    }
                    break;
                case 5:
                // Buscar mascota por especie
                    System.out.print("Ingrese su especie de interés para buscar: ");
                    String especieBusqueda = scanner.nextLine();
                    System.out.println("Resultados para especie: " + especieBusqueda);
                    IteradorMascotas iterBuscador = refugio.crearIterador();
                    boolean encontrado = false;
                    while (iterBuscador.tieneSiguiente()) {
                        Mascota m = iterBuscador.siguiente();
                        if (m.getEspecie().equalsIgnoreCase(especieBusqueda)) {
                            Exportable exportada = new MascotaAdapter(m); // Uso del patrón Adapter
                            System.out.println(exportada.exportar());
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("No se encontraron mascotas para esa especie.");
                    }
                    break;
                case 0:
                // Cerrar programa
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}