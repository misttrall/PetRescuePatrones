package com.patrones.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Refugio {
    private static Refugio instancia;
    private List<Mascota> mascotas;
    private List<Interesado> interesados;
    private Map<String, String> adopciones;

    private Refugio() {
        mascotas = new ArrayList<>();
        interesados = new ArrayList<>();
        adopciones = new HashMap<>();
    }

    public static Refugio getInstancia() {
        if (instancia == null) {
            instancia = new Refugio();
        }
        return instancia;
    }

    public void registrarMascota(Mascota m) {
        mascotas.add(m);
        notificarInteresados(m);
    }

    public void suscribir(Interesado i) {
        interesados.add(i);
    }

    private void notificarInteresados(Mascota m) {
        for (Interesado i : interesados) {
            i.notificar(m);
        }
    }

    public IteradorMascotas crearIterador() {
        return new IteradorMascotasConcreto(mascotas);
    }

    public boolean existeUsuario(String nombre) {
        for (Interesado i : interesados) {
            if (i instanceof UsuarioInteresado) {
                if (((UsuarioInteresado) i).getNombre().equalsIgnoreCase(nombre)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean adoptarMascota(String nombreMascota, String nombreUsuario) {
        for (Mascota m : mascotas) {
            if (m.getNombre().equalsIgnoreCase(nombreMascota)) {
                mascotas.remove(m);
                adopciones.put(nombreMascota, nombreUsuario);
                return true;
            }
        }
        return false;
    }
}
