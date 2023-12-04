package co.edu.uniandes.hotelandes.model;

/**
 *
 * @author danielito
 */
public class ConsumoServicioHabitacion {
    Servicio servicio;
    Consumo consumo;
    String habitacion;

    public ConsumoServicioHabitacion( Consumo consumo, Servicio servicio, String habitacion) {
        this.consumo = consumo;
        this.servicio = servicio;
        this.habitacion = habitacion;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }
    
    
    
}
