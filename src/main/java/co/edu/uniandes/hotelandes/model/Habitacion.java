package co.edu.uniandes.hotelandes.model;
import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


public class Habitacion {

    @Id
    private ObjectId _id;
    private String numero;
    private String tipoHabitacion;

    public Habitacion() {}

    public Habitacion(ObjectId _id, String numero, String tipoHabitacion) {
        this._id = _id;
        this.tipoHabitacion = tipoHabitacion;
        this.numero = numero;
    }
    
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ObjectId getId() {
        return this._id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public void setIdCustom() {
        this._id = new ObjectId();
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

}