package co.edu.uniandes.hotelandes.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Consumo {
    private String nombre;
    private int costo;
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
    public Consumo(String nombre, int costo, Date fecha) {
        this.nombre = nombre;
        this.costo = costo;
        this.fecha = fecha;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}

