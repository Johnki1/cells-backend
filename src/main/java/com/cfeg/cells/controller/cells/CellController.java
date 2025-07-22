package com.cfeg.cells.controller.cells;

import com.cfeg.cells.models.cells.dto.CellResponse;
import com.cfeg.cells.models.cells.dto.CreateCellRequest;
import com.cfeg.cells.models.cells.dto.UpdateCellRequest;
import com.cfeg.cells.models.cells.service.CellService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cells")
@RequiredArgsConstructor
public class CellController {

    private final CellService cellService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CellResponse> create(@RequestBody CreateCellRequest request) {
        return ResponseEntity.ok(cellService.createCell(request));
    }

    @GetMapping
    public ResponseEntity<List<CellResponse>> getAll() {
        return ResponseEntity.ok(cellService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CellResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cellService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CellResponse> update(@PathVariable Long id, @RequestBody UpdateCellRequest request) {
        return ResponseEntity.ok(cellService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> archive(@PathVariable Long id) {
        cellService.archive(id);
        return ResponseEntity.noContent().build();
    }
}

