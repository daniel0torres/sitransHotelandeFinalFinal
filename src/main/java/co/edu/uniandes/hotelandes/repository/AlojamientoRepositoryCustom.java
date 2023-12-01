package co.edu.uniandes.hotelandes.repository;

import java.util.List;

import co.edu.uniandes.hotelandes.model.Alojamiento;


public interface AlojamientoRepositoryCustom {
    List<Alojamiento> findAlojamientoConReserva();
}
