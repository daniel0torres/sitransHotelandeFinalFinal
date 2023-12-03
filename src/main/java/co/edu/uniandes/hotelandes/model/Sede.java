package co.edu.uniandes.hotelandes.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.List;

@Data
@Document("sedes")
public class Sede {

    @Id
    private ObjectId _id;
    private String nombre;
    private String telefono;
    private String direccion;
    private List<Habitacion> habitaciones; 


    public Sede(){}

    public Sede(String nombre, String telefono, String direccion, List<Habitacion> habitaciones) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.habitaciones = habitaciones;
    }   
    

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Habitacion> getHabitaciones() {
        return this.habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

}
