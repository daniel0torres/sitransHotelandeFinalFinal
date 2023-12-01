package co.edu.uniandes.hotelandes.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import co.edu.uniandes.hotelandes.model.Alojamiento;

public class AlojamientoRepositoryCustomImpl implements AlojamientoRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public AlojamientoRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Alojamiento> findAlojamientoConReserva() {
    LookupOperation lookupOperation = LookupOperation.newLookup()
            .from("reservas")
            .localField("reserva")
            .foreignField("_id")
            .as("reservaInfo");

    // $unwind descompone la lista en documentos individuales
    UnwindOperation unwindOperation = Aggregation.unwind("reservaInfo");

    Aggregation aggregation = Aggregation.newAggregation(lookupOperation, unwindOperation);
    AggregationResults<Alojamiento> results = mongoTemplate.aggregate(aggregation, "alojamientos", Alojamiento.class);

    return results.getMappedResults();
    }
    
    @Override
    public Alojamiento findByReserva(ObjectId reservaId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("reserva").is(reservaId));
        return mongoTemplate.findOne(query, Alojamiento.class);
    }


}
