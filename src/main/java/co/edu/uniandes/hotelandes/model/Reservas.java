package co.edu.uniandes.hotelandes.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("reservas")
public class Reservas {
    @Id
    private ObjectId _id;
    private String cliente;
    private String checkin;
    private String checkout;
    private String sede;

    public Reservas() {}

    public Reservas(String checkIn, String checkOut, String cliente, String sede) {
        this.cliente = cliente;
        this.checkin = checkIn;
        this.checkout = checkOut;
        this.sede= sede;
    }
    public String getCliente(){
        return this.cliente;
    }
}