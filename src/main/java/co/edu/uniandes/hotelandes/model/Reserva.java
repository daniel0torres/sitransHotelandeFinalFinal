package co.edu.uniandes.hotelandes.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class Reserva {

    

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

    public Reserva( Date checkin, Date checkout,String sede) {
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

}
