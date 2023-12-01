package co.edu.uniandes.hotelandes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("alojamientos")
public class Alojamiento {

    @Id
    private ObjectId _id;
    private String habitacion; // Referencia a la habitación
    private List<ObjectId> consumos; // Lista de consumos (IDs)
    private ObjectId reserva; // ID de la reserva
    private String cliente; // Nombre del cliente
    private Reservas reservaInfo;
    private String fechaLlegada;
    private String fechaSalida;

    public Alojamiento() {}

    public Alojamiento(String habitacion,  ObjectId reserva, String cliente) {
        this.habitacion = habitacion;
        this.consumos =new ArrayList<>();
        this.reserva = reserva;
        this.cliente = cliente;
        this.fechaLlegada = "-1";
        this.fechaSalida = "-1";
    }

    public String getFechaLlegada(){
        return this.fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada){
        this.fechaLlegada = fechaLlegada;
    }

    public void setFechaSalida(String fechaSalida){
        this.fechaSalida = fechaSalida;
    }


    public String getFechaSalida(){
        return this.fechaSalida;
    }


    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public List<ObjectId> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<ObjectId> consumos) {
        this.consumos = consumos;
    }

    public ObjectId getReserva() {
        return reserva;
    }

    public void setReserva(ObjectId reserva) {
        this.reserva = reserva;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
