package com.cfeg.cells.models.cells.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record CreateCellRequest(
        String name,
        String zone,
        String address,
        DayOfWeek meetingDay,
        LocalTime meetingTime,
        Long leaderId,
        String notes
) {}
