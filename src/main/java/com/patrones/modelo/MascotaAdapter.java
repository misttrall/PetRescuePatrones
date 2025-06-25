package com.patrones.modelo;

public class MascotaAdapter implements Exportable {
    private Mascota mascota;

    public MascotaAdapter(Mascota mascota) {
        this.mascota = mascota;
    }

    @Override
    public String exportar() {
        return "{ \"nombre\": \"" + mascota.getNombre() + "\", \"especie\": \"" + mascota.getEspecie() + "\" }";
    }
}
