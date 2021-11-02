package com.usa.api.service;

import com.usa.api.model.Reservation;
import com.usa.api.model.custom.CountClient;
import com.usa.api.model.custom.StatusAmount;
import com.usa.api.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> reservationAux = reservationRepository.getReservation(reservation.getIdReservation());
            if (reservationAux.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> reservationAux = reservationRepository.getReservation(reservation.getIdReservation());
            if (!reservationAux.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    reservationAux.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    reservationAux.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    reservationAux.get().setStatus(reservation.getStatus());
                }
                return reservationRepository.save(reservationAux.get());
            }
        }
        return reservation;
    }

    public boolean delete(int id) {
        Optional<Reservation> reservationAux = reservationRepository.getReservation(id);
        if (!reservationAux.isEmpty()) {
            reservationRepository.delete(reservationAux.get());
            return true;
        }
        return false;
    }

    public List<CountClient> getTopClient() {
        return reservationRepository.getTopClients();
    }

    public StatusAmount getStatusReport() {
        List<Reservation> completed = reservationRepository.getReservationsByStatus("completed");
        List<Reservation> cancelled = reservationRepository.getReservationsByStatus("cancelled");
        
        StatusAmount statusAmount = new StatusAmount(completed.size(), cancelled.size());
        
        return statusAmount;
    }
    
    public List<Reservation> getReservationPeriod(String date1, String date2) {
        
        // yyyy-mm-dd
        SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne = new Date();
        Date dateTwo = new Date();
        
        try {
            dateOne = parseDate.parse(date1);
            dateTwo = parseDate.parse(date2);
        } catch (ParseException e) {
        }
        
        if (dateOne.before(dateTwo)) {
            return reservationRepository.getReservationByPeriod(dateOne, dateTwo);
        } else {
            return new ArrayList<>();
        }       
    }
    
}
