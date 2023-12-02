package co.edu.uniandes.hotelandes.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document("clientes")
public class Cliente {

    @Id
    private ObjectId _id;
    private String nombre;
    private List<Consumo> consumos;
    
    public Cliente(){
        
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    public Cliente(String nombre, List<Consumo> consumos) {
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
    
    
    
}
    

   