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
    private String checkIn;
    private String checkOut;

    public Reservas() {}

    public Reservas(String checkIn, String checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
