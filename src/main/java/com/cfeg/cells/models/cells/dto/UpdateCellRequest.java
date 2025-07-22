package com.cfeg.cells.models.cells.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record UpdateCellRequest(
        String name,
        String zone,
        String address,
        DayOfWeek meetingDay,
        LocalTime meetingTime,
        String notes
) {}
