package co.edu.uniandes.hotelandes.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("sedes")
public class Sede {

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
