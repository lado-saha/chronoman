package com.minsih.chronoman;

// TaskEntityListener.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.minsih.chronoman.model.Activity;
import com.minsih.chronoman.model.Site;
import com.minsih.chronoman.model.Task;
import com.minsih.chronoman.service.ActivityService;
import com.minsih.chronoman.service.SiteService;

import ch.qos.logback.classic.net.SyslogAppender;
import jakarta.persistence.PostUpdate;

@Component
public class TaskEntityListener {

    private final ActivityService activityService;
    private final SiteService siteService;

    @Autowired
    public TaskEntityListener(@Lazy ActivityService activityService, @Lazy SiteService siteService) {
        this.activityService = activityService;
        this.siteService = siteService;
    }

    @PostUpdate
    public void onTaskUpdate(Task task) {
        // Calculate the change in task duration
        int oldDuration = task.getOldDuration(); // Store old duration in Task entity
        int newDuration = task.getDuration();
        int durationChange = newDuration - oldDuration;
        System.out.println("CALLED ME NOW Task***************" + durationChange + " \n\n\n");

        Activity activity = task.getActivity();


        if (durationChange != 1000000000) {
            activity.setTotalTasksDuration(activity.getTotalTasksDuration() + durationChange);
            // The duration should always be greater or equals to that of the combined
            // duration of its children
            if (activity.getDuration() < activity.getTotalTasksDuration()) {
                activity.setOldDuration(activity.getDuration());
                activity.setDuration(activity.getTotalTasksDuration());
            }
            activityService.save(activity);

            // Update site's totalActivityDuration
            oldDuration = activity.getOldDuration();
            newDuration = activity.getDuration();
            durationChange = newDuration - oldDuration;
            if (durationChange != 0) {
                Site site = activity.getSite();

                site.setTotalActivitiesDuration(site.getTotalActivitiesDuration() + durationChange);
                if (site.getDuration() < site.getTotalActivitiesDuration()) {
                    site.setDuration(site.getTotalActivitiesDuration());
                }
                siteService.save(site);
            }
        }
    }
}
