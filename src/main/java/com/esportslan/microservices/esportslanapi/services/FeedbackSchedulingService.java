package com.esportslan.microservices.esportslanapi.services;

import com.esportslan.microservices.esportslanapi.models.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class FeedbackSchedulingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackSchedulingService.class);

    @Autowired
    private EventService eventService;

    @Scheduled(cron = "0 0 1 * * *")
    public void updateFeedbackStatus() {
        List<Feedback> feedbackList = eventService.fetchOnwMonthOlderFeedbacks();
        if (feedbackList != null && !feedbackList.isEmpty()) {
            LOGGER.info("FeedbackScheduling: feedbacks found which are one month old: {}", feedbackList.size());
            for (Feedback feedback : feedbackList) {
                feedback.setFlag(true);
                LocalDate today = LocalDate.now();
                Date sqlDate = Date.valueOf(today);
                feedback.setDate(sqlDate.toString());
            }
            eventService.updateFeedbackDetails(feedbackList);
        } else {
            LOGGER.info("FeedbackScheduling: No feedbacks found which are one month old");
        }
    }
}
