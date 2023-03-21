package com.example.newbusbookingsystem.Repository;

import com.example.newbusbookingsystem.Model.SeatBooking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SeatBookingRepository extends JpaRepository<SeatBooking,Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from SeatBooking where userId=?1 and busId=?2 and seatNo=?3")
    void deleteSeat(Long userId,Long busId,int seatno);
}
