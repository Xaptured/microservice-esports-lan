package com.esportslan.microservices.esportslanapi.services;

import com.esportslan.microservices.esportslanapi.clienthelpers.TheJackFolioDBClientHelper;
import com.esportslan.microservices.esportslanapi.enums.EventStatus;
import com.esportslan.microservices.esportslanapi.enums.LANTeamStatus;
import com.esportslan.microservices.esportslanapi.enums.PaymentStatus;
import com.esportslan.microservices.esportslanapi.exceptions.BadRequestErrorException;
import com.esportslan.microservices.esportslanapi.exceptions.InternalErrorException;
import com.esportslan.microservices.esportslanapi.exceptions.ValidationException;
import com.esportslan.microservices.esportslanapi.models.*;
import com.esportslan.microservices.esportslanapi.servicehelpers.EventServiceHelper;
import com.esportslan.microservices.esportslanapi.utilities.CityCodes;
import com.esportslan.microservices.esportslanapi.utilities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    private static final String MEDIA_TOURNAMENT = "/media/tournaments/";
    private static final DateTimeFormatter MMT_FORMAT = DateTimeFormatter.ofPattern("MMddyyyy");
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private EventServiceHelper eventServiceHelper;
    @Autowired
    private TheJackFolioDBClientHelper theJackFolioDBClientHelper;
    @Autowired
    private LiveUpdateEventPublisher liveUpdateEventPublisher;

    @Value("${tournament.images.folder}")
    private String tournamentImageFolder;
    @Value("${server.base.url}")
    private String baseURL;
    @Value("${makemytrip.base.url}")
    private String makeMyTripBaseURL;
    @Value("${googlemap.base.url}")
    private String googleMapsBaseURL;

    public void saveOrUpdateEvent(Event event, boolean isUpdate) {
        eventServiceHelper.validateEvent(event);
        theJackFolioDBClientHelper.saveOrUpdateEvent(event, isUpdate);
    }

    public void saveTeams(List<LANTeam> teams) {
        eventServiceHelper.validateTeams(teams);
        theJackFolioDBClientHelper.saveTeams(teams);
    }

    public List<Event> fetchFutureEventsWRTEmail(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchFutureEventsWRTEmail(email);
    }

    public List<Event> fetchPastEventsWRTEmail(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchPastEventsWRTEmail(email);
    }

    public List<Event> fetchLiveEventsWRTEmail(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchLiveEventsWRTEmail(email);
    }

    public List<LANTeam> fetchTeamWithTeamMate(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchTeamWithTeamMate(email);
    }

    public void updateTeamStatus(String email, String eventName, LANTeamStatus status) {
        eventServiceHelper.validateTeamStatusUpdateParams(email, eventName, status);
        theJackFolioDBClientHelper.updateTeamStatus(email, eventName, status);
    }

    public List<Event> fetchPastEventsForParticipants(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchPastEventsForParticipants(email);
    }

    public List<Event> fetchFutureEventsForParticipants(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchFutureEventsForParticipants(email);
    }

    public void saveOrUpdateAudience(Audience audience) {
        eventServiceHelper.validateAudience(audience);
        theJackFolioDBClientHelper.saveOrUpdateAudience(audience);
        if (audience.getStatus().equals(PaymentStatus.COMPLETED)) {
            String ticketNumber = audience.getEventName() + "-" + Utils.generateUUID();
            AudienceTicket audienceTicket = new AudienceTicket(audience.getEmail(), audience.getEventName(), ticketNumber, false, false);
            theJackFolioDBClientHelper.saveAudienceTicket(audienceTicket);
        }
    }

    public List<Event> fetchPastEventsForAudience(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchPastEventsForAudience(email);
    }

    public List<Event> fetchFutureEventsForAudience(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchFutureEventsForAudience(email);
    }

    public List<Event> fetchLiveEventsForAudience(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchLiveEventsForAudience(email);
    }

    public List<Event> findLANEventsNotRegisteredByAudience(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.findLANEventsNotRegisteredByAudience(email);
    }

    public List<Event> fetchInactiveEventForAdmin() {
        return theJackFolioDBClientHelper.fetchInactiveEventForAdmin();
    }

    public void updateEventStatus(String eventName, EventStatus status) {
        if (Utils.isStringEmptyOrBlank(eventName)) {
            throw new ValidationException("Event name is invalid");
        }
        if (Utils.isStringEmptyOrBlank(status.toString())) {
            throw new ValidationException("Status is invalid");
        }
        theJackFolioDBClientHelper.updateEventStatus(eventName, status);
    }

    public Event fetchLANEventDetails(String eventName) {
        if (Utils.isStringEmptyOrBlank(eventName)) {
            throw new ValidationException("Event name is invalid");
        }
        return theJackFolioDBClientHelper.fetchLANEventDetails(eventName);
    }

    public List<LANTeam> fetchParticipatedTeamDetails(String eventName) {
        if (Utils.isStringEmptyOrBlank(eventName)) {
            throw new ValidationException("Event name is invalid");
        }
        return theJackFolioDBClientHelper.fetchParticipatedTeamDetails(eventName);
    }

    public long fetchUnsentEmailForAudienceCount() {
        return theJackFolioDBClientHelper.fetchUnsentEmailForAudienceCount();
    }

    public List<AudienceTicket> fetchUnsentEmailForAudience() {
        return theJackFolioDBClientHelper.fetchUnsentEmailForAudience();
    }

    public void savePendingPayment(Audience audience) {
        theJackFolioDBClientHelper.savePendingPayments(audience);
    }

    public void saveFailedPayment(Audience audience) {
        theJackFolioDBClientHelper.saveFailedPayments(audience);
    }

    public void saveInitiatePayment(Audience audience) {
        theJackFolioDBClientHelper.saveInitiatePayment(audience);
    }

    public List<Audience> fetchAllPendingPayments() {
        return theJackFolioDBClientHelper.fetchPendingPayments();
    }

    public List<Audience> fetchAllFailedPayments() {
        return theJackFolioDBClientHelper.fetchFailedPayments();
    }

    public Audience fetchInitiatePayment(String merchantTransactionId) {
        return theJackFolioDBClientHelper.fetchInitiatePayment(merchantTransactionId);
    }

    public void deletePendingPayment(String email, String eventName) {
        theJackFolioDBClientHelper.deletePendingPayment(email, eventName);
    }

    public void deleteFailedPayment(String email, String eventName) {
        theJackFolioDBClientHelper.deleteFailedPayment(email, eventName);
    }

    public void saveSubUser(SubUser subUser) {
        String userName = subUser.getName() + subUser.getEventName();
        String userPassword = Utils.generateUUID();
        subUser.setUserName(userName);
        subUser.setUserPassword(userPassword);
        theJackFolioDBClientHelper.saveSubUser(subUser);
    }

    public void updateSubUser(SubUser subUser) {
        theJackFolioDBClientHelper.updateSubUser(subUser);
    }

    public void updateActive(String eventName) {
        theJackFolioDBClientHelper.updateActive(eventName);
    }

    public List<SubUser> fetchUnsentEmailSubUsers() {
        return theJackFolioDBClientHelper.fetchUnsentEmailSubUsers();
    }

    public SubUserResult verifySubUserCredentials(SubUser user) {
        SubUser subUser = theJackFolioDBClientHelper.fetchSubUserByUserName(user.getUserName());
        if (!subUser.isActive()) {
            throw new BadRequestErrorException("Username doesn't activated yet");
        }
        boolean result = user.getUserPassword().equals(subUser.getUserPassword());
        SubUserResult subUserResult = new SubUserResult(result, subUser.getEventName());
        return subUserResult;

    }

    public boolean verifyAudienceTicket(AudienceTicket audienceTicket) {
        AudienceTicket ticket = theJackFolioDBClientHelper.fetchAudienceTicketDetails(audienceTicket.getEventName(), audienceTicket.getEmail());
        if (ticket.isCheckedIn()) {
            throw new BadRequestErrorException("User already checked in");
        }
        boolean result = ticket.getTicketNumber().equals(audienceTicket.getTicketNumber());
        if (result) {
            theJackFolioDBClientHelper.updateCheckedInStatus(audienceTicket.getEventName(), audienceTicket.getEmail());
        }
        return result;
    }

    public void updateFeedback(Feedback feedback) {
        LocalDate today = LocalDate.now();
        Date sqlDate = Date.valueOf(today);
        feedback.setDate(sqlDate.toString());

        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback);
        updateFeedbackDetails(feedbacks);
    }

    public void updateFeedbackDetails(List<Feedback> feedbacks) {
        eventServiceHelper.validateFeedbackDetails(feedbacks);
        theJackFolioDBClientHelper.updateFeedbackDetails(feedbacks);
    }

    public Feedback fetchFeedbackDetails(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchFeedbackDetails(email);
    }

    public List<Feedback> fetchOnwMonthOlderFeedbacks() {
        return theJackFolioDBClientHelper.fetchOnwMonthOlderFeedbacks();
    }

    public void publishUpdateRequestDetails(UpdateRequest request) {
        eventServiceHelper.validateUpdateRequestDetails(request);

        UpdateRequestEvent event = new UpdateRequestEvent();
        event.setEventId(UUID.randomUUID().toString());
        event.setCategory(request.getCategory());
        event.setTournamentId(request.getTournamentId());
        event.setTitle(request.getTitle());
        event.setMessage(request.getMessage());
        event.setCreatedAt(Instant.now());

        liveUpdateEventPublisher.publish(event);
    }

    public void saveTournamentImages(TournamentImages tournamentImages) {
        try {
            String tournamentName = tournamentImages.getTournamentName();

            Path basePath = Paths.get(tournamentImageFolder);
            Path folder = basePath.resolve(tournamentName);

            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
            }

            TournamentImageDBRequest request = new TournamentImageDBRequest();
            request.setTournamentName(tournamentName);

            List<Image> images = new ArrayList<>();
            for (MultipartFile file : tournamentImages.getImages()) {

                String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
                Path imagePath = folder.resolve(fileName);

                Files.copy(file.getInputStream(), imagePath);

                String url = baseURL + MEDIA_TOURNAMENT + tournamentName + "/" + fileName;
                Image image = new Image();
                image.setImageName(fileName);
                image.setImagePath(url);

                images.add(image);
            }
            request.setImages(images);

            theJackFolioDBClientHelper.saveTournamentImages(request);
        } catch (IOException exception) {
            throw new InternalErrorException("IO exception occurred while saving images " + exception.getMessage(), exception);
        }
    }

    public List<Image> fetchImagesForTournament(String tournamentName) {
        return theJackFolioDBClientHelper.fetchImagesByTournamentName(tournamentName);
    }

    public StayLink generateStayLinks(String eventName) {
        if (Utils.isStringEmptyOrBlank(eventName)) {
            throw new ValidationException("Event name is invalid");
        }

        Event eventDetails = theJackFolioDBClientHelper.fetchLANEventDetails(eventName);
        eventServiceHelper.validateEvent(eventDetails);

        String eventDate = eventDetails.getEventDetails().getDate();
        System.out.println("Actual date: " + eventDate);
        LocalDate date = LocalDate.parse(eventDate, INPUT_FORMAT);
        String checkInDate = date.minusDays(1).format(MMT_FORMAT);
        System.out.println("Check In date: " + checkInDate);
        String checkOutDate = date.plusDays(1).format(MMT_FORMAT);
        System.out.println("Check Out date: " + checkOutDate);

        String cityCode = CityCodes.resolveCityCode(eventDetails.getAddress().getCity());

        String url = UriComponentsBuilder
                .fromHttpUrl(makeMyTripBaseURL)
                .queryParam("checkin", checkInDate)
                .queryParam("checkout", checkOutDate)
                .queryParam("city", cityCode)
                .queryParam("country", "IN")
                .queryParam("roomStayQualifier", "2e0e")
                .build()
                .toUriString();

        return new StayLink("MAKEMYTRIP", url);
    }

    public TravelLink generateTravelLinks(String eventName) {
        if (Utils.isStringEmptyOrBlank(eventName)) {
            throw new ValidationException("Event name is invalid");
        }

        Event eventDetails = theJackFolioDBClientHelper.fetchLANEventDetails(eventName);
        eventServiceHelper.validateEvent(eventDetails);

        String destination = eventDetails.getAddress().getCity();

        String url = UriComponentsBuilder
                .fromHttpUrl(googleMapsBaseURL)
                .queryParam("api", "1")
                .queryParam("destination", destination)
                .build()
                .toUriString();

        return new TravelLink(
                destination,
                "GOOGLE_MAPS",
                url
        );
    }
}
