package co.edu.uniandes.hotelandes.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document("clientes")
public class Cliente {

    @Id
    private ObjectId _id;
    private String nombre;
    private ArrayList<Consumo> consumos;
    private ArrayList<Reserva> reservas;

   

    public Cliente(String nombre, ArrayList<Consumo> consumos, ArrayList<Reserva> reservas) {
        this.nombre = nombre;
        this.consumos = consumos;
        this.reservas = reservas;
    }
    
    public Cliente(){
        consumos = new ArrayList<>();
        reservas = new ArrayList<>();
    }
    
     public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public ArrayList<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(ArrayList<Consumo> consumos) {
        this.consumos = consumos;
    }

 
    
    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
    
    
}
    

   