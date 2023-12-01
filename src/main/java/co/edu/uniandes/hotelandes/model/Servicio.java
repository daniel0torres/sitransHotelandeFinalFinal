package co.edu.uniandes.hotelandes.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("servicios")
public class Servicio {

    @Id
    private ObjectId _id;
    private String tipo;
    private String descripcion;
    
    public Servicio() {}

    public Servicio(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    
}
