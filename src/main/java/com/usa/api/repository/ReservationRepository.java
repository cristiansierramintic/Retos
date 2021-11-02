package com.usa.api.repository;

import com.usa.api.model.Client;
import com.usa.api.model.Reservation;
import com.usa.api.model.custom.CountClient;
import com.usa.api.repository.crud.ReservationCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public void delete(Reservation reservation) {
        reservationCrudRepository.delete(reservation);
    }

    public List<Reservation> getReservationsByStatus(String status) {
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<Reservation> getReservationByPeriod(Date dateOne, Date dateTwo) {
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }

    public List<CountClient> getTopClients() {

        List<CountClient> res = new ArrayList<>();

        List<Object[]> report = reservationCrudRepository.countTotalReservationByClient();
        for (int i = 0; i < report.size(); i++) {
            res.add(new CountClient((Long) report.get(i)[1], (Client) report.get(i)[0]));
        }
        return res;
    }
}
