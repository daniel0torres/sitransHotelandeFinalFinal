package co.edu.uniandes.hotelandes.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document("clientes")
public class Cliente {

    @Id
    private ObjectId _id;
    
    
    private String nombre;
    
    public Cliente(){
        
    }

    public Cliente(String nombre) {
        this.nombre = nombre;
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
    

   