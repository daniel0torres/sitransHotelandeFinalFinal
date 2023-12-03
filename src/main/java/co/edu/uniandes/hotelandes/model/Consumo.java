package co.edu.uniandes.hotelandes.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

public class Consumo {
    private ObjectId servicio;
    private int costo;
    private String nombre;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCosto() {
        return this.costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
    
    public Consumo(){
        
    }
    public Consumo(ObjectId servicio, int costo, Date fecha, String nombre) {
        this.servicio = servicio;
        this.costo = costo;
        this.fecha = fecha;
        this.nombre = nombre;
    }


    public ObjectId getServicio() {
        return servicio;
    }

    public void setServicio(ObjectId servicio) {
        this.servicio = servicio;
    }

    public Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}

