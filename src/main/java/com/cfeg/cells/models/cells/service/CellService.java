package com.cfeg.cells.models.cells.service;

import com.cfeg.cells.models.cells.dto.CellResponse;
import com.cfeg.cells.models.cells.dto.CreateCellRequest;
import com.cfeg.cells.models.cells.dto.UpdateCellRequest;
import com.cfeg.cells.models.cells.entity.Cell;
import com.cfeg.cells.models.cells.repository.CellRepository;
import com.cfeg.cells.models.user.entity.User;
import com.cfeg.cells.models.user.enums.Role;
import com.cfeg.cells.models.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CellService {

    private final CellRepository cellRepository;
    private final UserRepository userRepository;

    @Transactional
    public CellResponse createCell(CreateCellRequest request) {
        User leader = userRepository.findById(request.leaderId())
                .filter(u -> u.getRole() == Role.LEADER)
                .orElseThrow(() -> new IllegalArgumentException("Asigned a valid role"));

        Cell cell = Cell.builder()
                .name(request.name())
                .zone(request.zone())
                .address(request.address())
                .meetingDay(request.meetingDay())
                .meetingTime(request.meetingTime())
                .leader(leader)
                .notes(request.notes())
                .archived(false)
                .active(true)
                .build();

        return toResponse(cellRepository.save(cell));
    }

    public List<CellResponse> getAll() {
        return cellRepository.findAllByArchivedFalse().stream()
                .map(this::toResponse)
                .toList();
    }

    public CellResponse getById(Long id) {
        return toResponse(cellRepository.findByIdAndArchivedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Célula no encontrada")));
    }

    @Transactional
    public CellResponse update(Long id, UpdateCellRequest request) {
        Cell cell = cellRepository.findByIdAndArchivedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Célula no encontrada"));

        cell.setName(request.name());
        cell.setZone(request.zone());
        cell.setAddress(request.address());
        cell.setMeetingDay(request.meetingDay());
        cell.setMeetingTime(request.meetingTime());
        cell.setNotes(request.notes());

        return toResponse(cellRepository.save(cell));
    }

    @Transactional
    public void archive(Long id) {
        Cell cell = cellRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Célula no encontrada"));
        cell.setArchived(true);
        cell.setActive(false);
        cellRepository.save(cell);
    }

    private CellResponse toResponse(Cell cell) {
        return new CellResponse(
                cell.getId(),
                cell.getName(),
                cell.getZone(),
                cell.getAddress(),
                cell.getMeetingDay(),
                cell.getMeetingTime(),
                cell.isArchived(),
                cell.isActive(),
                cell.getLeader() != null ? cell.getLeader().getId() : null,
                cell.getLeader() != null ? cell.getLeader().getName() : null,
                cell.getNotes()
        );
    }
}

