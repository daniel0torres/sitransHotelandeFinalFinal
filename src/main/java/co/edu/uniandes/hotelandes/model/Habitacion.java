package co.edu.uniandes.hotelandes.model;

import org.bson.types.ObjectId;


public class Habitacion {
    private TipoHabitacion tipoHabitacion;

    public Habitacion() {}

    public Habitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }



    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

}
