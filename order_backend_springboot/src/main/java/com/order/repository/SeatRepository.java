package com.order.repository;

import com.order.domain.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findAllByStatusIn(List<Integer> list);
    List<Seat> findByStatusIn(List<Integer> list);
}
