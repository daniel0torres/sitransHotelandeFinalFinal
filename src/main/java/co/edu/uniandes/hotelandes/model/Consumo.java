package co.edu.uniandes.hotelandes.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

public class Consumo {
    private ObjectId servicio;
    private int costo;
    private String tipoServico;
    private String nombreHabitacion;
    
    public Consumo(){
        
    }
    public Consumo(ObjectId servicio, int costo, Date fecha, String tipoServico,String nombreHabitacion) {
        this.servicio = servicio;
        this.costo = costo;
        this.fecha = fecha;
        this.tipoServico = tipoServico;
        this.nombreHabitacion = nombreHabitacion;  

    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ObjectId getServicio() {
        return servicio;
    }

    public void setServicio(ObjectId servicio) {
        this.servicio = servicio;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getNombreHabitacion() {
        return nombreHabitacion;
    }

    public void setNombreHabitacion(String nombreHabitacion) {
        this.nombreHabitacion = nombreHabitacion;
    }

    @Override
    public String toString() {
        return "Consumo{" + "servicio=" + servicio + "\n costo=" + costo + "\n tipoServico=" + tipoServico + "\n nombreHabitacion=" + nombreHabitacion + "\n fecha=" + fecha + '}';
    }

    
    
    
}
