package com.usa.api.repository.crud;

import com.usa.api.model.Reservation;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReservationCrudRepository  extends CrudRepository<Reservation, Integer>{
   
    //JPQL
    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c GROUP BY c.client ORDER BY COUNT(c.client)desc")
    public List<Object[]> countTotalReservationByClient();
    
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date detaOne, Date dateTwo);
    
    public List<Reservation> findAllByStatus(String status);
}
