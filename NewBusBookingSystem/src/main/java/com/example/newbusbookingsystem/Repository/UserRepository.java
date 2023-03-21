package com.example.newbusbookingsystem.Repository;

import com.example.newbusbookingsystem.Model.SeatBooking;
import com.example.newbusbookingsystem.Model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    @Query(value = "select u from SeatBooking u where u.userId=?3 and u.seatNo=?2 and u.busId=?1")
    public SeatBooking seatCheck(Long bid, int sno, Long userId);


}
