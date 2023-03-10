package com.pragma.scheduleassessment.repository;

import com.pragma.scheduleassessment.dto.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@FeignClient(name = "get-available-event", url = "${CLIENTE_FEING}")
public interface IConsultEventClient {

    @GetMapping()
    ResponseEntity<Event> getAvailableEvent(
            @RequestParam String api,
            @RequestParam String type,
            @RequestParam String calendarId,
            @RequestParam String summary,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime initDate,
            @RequestParam Integer numEvents
    );

}