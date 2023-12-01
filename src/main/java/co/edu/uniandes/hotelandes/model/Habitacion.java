package co.edu.uniandes.hotelandes.model;

import org.bson.types.ObjectId;


public class Habitacion {
    private ObjectId _id;
    private TipoHabitacion tipoHabitacion;

    public Habitacion() {}

    public Habitacion(ObjectId _id, TipoHabitacion tipoHabitacion) {
        this._id = _id;
        this.tipoHabitacion = tipoHabitacion;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

}
