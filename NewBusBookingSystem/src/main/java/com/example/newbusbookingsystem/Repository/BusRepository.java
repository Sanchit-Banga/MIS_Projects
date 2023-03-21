package com.example.newbusbookingsystem.Repository;

import com.example.newbusbookingsystem.Model.BusModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<BusModel,Long> {
    @Modifying
    @Transactional
    @Query(value = "select u from BusModel u where u.source=?1 and u.destination=?2")
    List<BusModel> findAllSrcDst(String src, String dest);
}
