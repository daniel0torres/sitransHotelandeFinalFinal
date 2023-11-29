package co.edu.uniandes.hotelandes.model;

import org.bson.types.ObjectId;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tipo_habitacion")
public class TipoHabitacion {

    @Id
    private ObjectId _id;
    private String nombre;
    private int costo_por_noche;
    private int capacidad;
    private String descripcion;

    public TipoHabitacion() {
    }

    public TipoHabitacion(String nombre, int costo_por_noche, int capacidad, String descripcion) {
        this.nombre = nombre;
        this.costo_por_noche = costo_por_noche;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
    }

    
    

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto_por_noche() {
        return costo_por_noche;
    }

    public void setCosto_por_noche(int costo_por_noche) {
        this.costo_por_noche = costo_por_noche;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    


    

}
