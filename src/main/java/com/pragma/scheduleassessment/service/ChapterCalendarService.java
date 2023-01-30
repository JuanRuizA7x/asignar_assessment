package com.pragma.scheduleassessment.service;

import com.pragma.scheduleassessment.dto.*;
import com.pragma.scheduleassessment.exception.ChapterAndSpecialtyNotFoundException;
import com.pragma.scheduleassessment.exception.ConsultEventClientResponseNullException;
import com.pragma.scheduleassessment.exception.EventsNotFoundException;
import com.pragma.scheduleassessment.model.ChapterCalendarModel;
import com.pragma.scheduleassessment.repository.IChapterCalendarRepository;
import com.pragma.scheduleassessment.repository.IConsultEventClient;
import com.pragma.scheduleassessment.repository.IUpdateEventClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChapterCalendarService {

    private final IChapterCalendarRepository chapterCalendarRepository;
    private final IConsultEventClient consultEventClient;
    private final IUpdateEventClient updateEventClient;

    public SchedulingResponseDTO scheduleAssessment(SchedulingRequestDTO schedulingRequest) {

        String typeGetEvent = "listEvents";
        String typeUpdateEvent = "updateEvent";
        String summaryGetEvent;
        String summaryUpdateEvent;
        LocalDateTime initDate = LocalDateTime.now();
        Integer numEvents = 1;
        List<String> emails = new ArrayList<>();
        int countEmailsRegistered;
        ChapterCalendarModel dataCalendar = chapterCalendarRepository.findByChapterIdAndSpecialty(schedulingRequest.getChapterId(), schedulingRequest.getSpecialty()).orElseThrow(ChapterAndSpecialtyNotFoundException::new);
        summaryGetEvent = dataCalendar.getNameEventInitial();
        Event event = consultEventClient.getAvailableEvent(typeGetEvent, dataCalendar.getCalendarId(), summaryGetEvent, initDate, numEvents).getBody();
        if  (event == null){
            throw  new ConsultEventClientResponseNullException();
        }
        if (event.getItems().isEmpty() ){
            throw  new EventsNotFoundException();
        }

        countEmailsRegistered = event.getItems().get(0).getAttendees().size();
        for (int i = 0; i < countEmailsRegistered; i++) {
            emails.add(event.getItems().get(0).getAttendees().get(i).getEmail());
        }
        emails.addAll(schedulingRequest.getEmails());
        summaryUpdateEvent = dataCalendar.getNameEventFinal();
        return updateEventClient.updateEvent(typeUpdateEvent, dataCalendar.getCalendarId(), event.getItems().get(0).getId(),summaryUpdateEvent, emails).getBody();
    }

}