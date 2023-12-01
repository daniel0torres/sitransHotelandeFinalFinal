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
    private ObjectId id;
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

}
