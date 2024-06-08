package com.minsih.chronoman.model;

/**
 * Enum representing the status of tasks, activities, and sites in a construction project.
 * 
 * <p>Possible states include:</p>
 * <ul>
 *   <li>PLANNED: Indicates that the task, activity, or site is scheduled to begin in the future.</li>
 *   <li>ONGOING_NORMAL: Indicates that the task, activity, or site is currently in progress and on schedule.</li>
 *   <li>ONGOING_OVERTIME: Indicates that the task, activity, or site is currently in progress but has exceeded the scheduled time.</li>
 *   <li>COMPLETED: Indicates that the task, activity, or site has been successfully completed within the scheduled time.</li>
 *   <li>COMPLETED_UNDERTIME: Indicates that the task, activity, or site has been completed before the scheduled time.</li>
 *   <li>COMPLETED_OVERTIME: Indicates that the task, activity, or site has been completed but exceeded the scheduled time.</li>
 * </ul>
 */
public enum ProjectStatus{
    PLANNED,
    ONGOING_NORMAL,
    ONGOING_OVERTIME,
    COMPLETED,
    COMPLETED_EARLY,
    COMPLETED_OVERTIME
}
