package com.patrones.modelo;

import java.util.List;

public class IteradorMascotasConcreto implements IteradorMascotas {
    private List<Mascota> mascotas;
    private int posicion = 0;

    public IteradorMascotasConcreto(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public boolean tieneSiguiente() {
        return posicion < mascotas.size();
    }

    @Override
    public Mascota siguiente() {
        return mascotas.get(posicion++);
    }
}
