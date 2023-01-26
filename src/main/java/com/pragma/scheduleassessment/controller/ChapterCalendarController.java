package com.pragma.scheduleassessment.controller;

import com.pragma.scheduleassessment.dto.SchedulingRequestDTO;
import com.pragma.scheduleassessment.service.ChapterCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/scheduleEvent")
public class ChapterCalendarController {

    private final ChapterCalendarService chapterCalendarService;

    @PostMapping
    public ResponseEntity<?> scheduleEvent(@RequestBody SchedulingRequestDTO schedulingRequest) {
        return null;
    }

}