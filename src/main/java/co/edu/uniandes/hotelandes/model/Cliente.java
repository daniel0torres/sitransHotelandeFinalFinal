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
    
    public Cliente(){
        consumos = new ArrayList<>();
    }

    public ArrayList<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(ArrayList<Consumo> consumos) {
        this.consumos = consumos;
    }

 
    public Cliente(String nombre, ArrayList<Consumo> consumos) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Cliente{" + "_id=" + _id + ", nombre=" + nombre + ", consumos=" + consumos + '}';
    }
    
    
    
}
    

   