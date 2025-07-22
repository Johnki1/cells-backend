package com.cfeg.cells.models.cells.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record CellResponse(
        Long id,
        String name,
        String zone,
        String address,
        DayOfWeek meetingDay,
        LocalTime meetingTime,
        boolean archived,
        boolean active,
        Long leaderId,
        String leaderName,
        String notes
) {}
