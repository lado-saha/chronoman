package com.minsih.chronoman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.minsih.chronoman.model.Activity;
import com.minsih.chronoman.model.Site;
import com.minsih.chronoman.service.SiteService;

import jakarta.persistence.PostUpdate;

@Component
public class ActivityEntityListener {

    // @Autowired
    // @Lazy
    // private SiteService siteService;

    // @PostUpdate
    // public void onActivityUpdate(Activity activity) {
    //     // Calculate the change in activity duration
    //     // Update site's totalActivityDuration
    //     int oldDuration = activity.getOldDuration();
    //     int newDuration = activity.getDuration();
    //     int durationChange = newDuration - oldDuration;

    //     System.out.println("CALLED ME NOW Activity***************\n\n\n");
    //     if (durationChange != 0) {
    //         Site site = activity.getSite();

    //         site.setTotalActivitiesDuration(site.getTotalActivitiesDuration() + durationChange);
    //         if (site.getDuration() < site.getTotalActivitiesDuration()) {
    //             site.setDuration(site.getTotalActivitiesDuration());
    //         }
    //         siteService.save(site);
    //     }
    // }
}
