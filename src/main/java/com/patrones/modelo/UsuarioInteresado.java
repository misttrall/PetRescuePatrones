package com.patrones.modelo;

public class UsuarioInteresado implements Interesado {
    private String nombre;
    private String preferencia;

    public UsuarioInteresado(String nombre, String preferencia) {
        this.nombre = nombre;
        this.preferencia = preferencia;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void notificar(Mascota m) {
        if (m.getEspecie().equalsIgnoreCase(preferencia)) {
            System.out.println(nombre + " fue notificado de un(a) " + m.getEspecie() + " llamado " + m.getNombre());
        }
    }
}