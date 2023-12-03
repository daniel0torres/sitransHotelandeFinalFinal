package co.edu.uniandes.hotelandes.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Document("reservas")
public class Reservas {
    @Id
    private ObjectId _id;
    private String cliente;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkout;
    private String sede;

    public Reservas() {}

    public Reservas( Date checkIn, Date checkOut, String cliente, String sede) {
        this.cliente = cliente;
        this.checkin = checkIn;
        this.checkout = checkOut;
        this.sede= sede;
    }
    public String getCliente(){
        return this.cliente;
    }
    public void setCheckin(Date checkin){
        this.checkin = checkin;
    }
    public void setCheckout(Date checkout){
        this.checkout = checkout;
    }
    public Date getCheckin(){
        return this.checkin;
    }
    public Date getCheckout(){
        return this.checkout;
    }
    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }
}