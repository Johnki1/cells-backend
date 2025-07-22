package com.cfeg.cells.models.cells.repository;

import com.cfeg.cells.models.cells.entity.Cell;
import com.cfeg.cells.models.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CellRepository extends JpaRepository <Cell, Long> {
    List<Cell> findAllByArchivedFalse();

    List<Cell> findAllByLeader(User leader);

    Optional<Cell> findByIdAndArchivedFalse(Long id);

}
