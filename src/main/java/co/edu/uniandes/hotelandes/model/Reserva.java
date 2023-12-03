package co.edu.uniandes.hotelandes.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

public class Reserva {
    @Id
    private ObjectId _id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkout;
    private String sede;

    public Reserva() {
    }

    
    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Reserva(ObjectId _id, Date checkin, Date checkout,String sede) {
        this._id = _id;
        this.checkin = checkin;
        this.checkout = checkout;
        this.sede= sede;
    }

  

    
    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Date getCheckin() {
        return this.checkin;
    }

    public Date getCheckout() {
        return this.checkout;
    }
    public void setIdCustom(){
        this._id = new ObjectId();
    }
    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }
    
}
