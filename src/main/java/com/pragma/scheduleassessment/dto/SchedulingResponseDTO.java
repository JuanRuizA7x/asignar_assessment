package com.pragma.scheduleassessment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchedulingResponseDTO {

    private String id;
    private String summary;
    private EventDate start;
    private EventDate end;
    private List<Attendee> attendees;

}