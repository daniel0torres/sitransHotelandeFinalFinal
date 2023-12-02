package co.edu.uniandes.hotelandes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Document("alojamientos")
public class Alojamiento {

    @Id
    private ObjectId _id;
    private String habitacion; 
    private ObjectId habitacionId;
    private List<ObjectId> consumos; // Lista de consumos (IDs)
    private ObjectId reserva; // ID de la reserva
    private String cliente; // Nombre del cliente
    private Reservas reservaInfo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaLlegada;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaSalida;

    public Alojamiento() {}

    public Alojamiento(ObjectId habitacion,  ObjectId reserva, String cliente, Date fechaLlegada, Date fechaSalida, String Habitacion) {
        this.habitacionId = habitacion;
        this.habitacion = Habitacion;
        this.consumos =new ArrayList<>();
        this.reserva = reserva;
        this.cliente = cliente;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida =fechaSalida;
    }

    public ObjectId getHabitacionId(){
        return habitacionId;
    }
    public Date getFechaLlegada(){
        return this.fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada){
        this.fechaLlegada = fechaLlegada;
    }

    public void setFechaSalida(Date fechaSalida){
        this.fechaSalida = fechaSalida;
    }


    public Date getFechaSalida(){
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
