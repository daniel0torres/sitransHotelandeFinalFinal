package co.edu.uniandes.hotelandes.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("sedes")
public class Sede {

    @Id
    private ObjectId _id;
    private String nombre;
    private String telefono;
    private String direccion;

    public Sede(){}

    public Sede(String nombre, String telefono, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }
}
