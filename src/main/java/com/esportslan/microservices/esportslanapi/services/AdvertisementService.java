package com.esportslan.microservices.esportslanapi.services;

import com.esportslan.microservices.esportslanapi.clienthelpers.TheJackFolioDBClientHelper;
import com.esportslan.microservices.esportslanapi.models.Advertisement;
import com.esportslan.microservices.esportslanapi.servicehelpers.EventServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {
    private static final String MEDIA_PATH = "/media/";

    @Value("${server.base.url}")
    private String baseURL;
    @Autowired
    private EventServiceHelper eventServiceHelper;
    @Autowired
    private TheJackFolioDBClientHelper theJackFolioDBClientHelper;

    public void saveAdvertisement(Advertisement advertisement) {
        eventServiceHelper.validateAdvertisementDetails(advertisement);
        theJackFolioDBClientHelper.saveAdvertisement(advertisement);
    }

    public List<Advertisement> fetchALlAdvertisements() {
        List<Advertisement> advertisements = theJackFolioDBClientHelper.getAdvertisementDetails();
        advertisements.stream().forEach(advertisement -> advertisement.setTargetUrl(baseURL + MEDIA_PATH + advertisement.getImagePath()));
        return advertisements;
    }
}
