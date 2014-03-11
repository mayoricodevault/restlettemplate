package com.jhc.figleaf.JobsRestApp.models;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamish dickson on 09/03/2014.
 *
 * Bit of dummy data
 */
public class Jobs {
    private static List<Job> jobList = new ArrayList<Job>();

    static {
        // just a few leptons for now
        jobList.add(new Job("electron", "one", 500));
        jobList.add(new Job("positron", "two", 500));
        jobList.add(new Job("muon", "three", 105700));
    }

    public static void addJob(Job job) {
        jobList.add(job);
    }

    public static boolean deleteJob(String name) {
        for (Job job : jobList) {
            if (job.getName().equals(name)) {
                jobList.remove(job);
                return true;
            }
        }
        return false;
    }

    public static void setJob(Job job) {
        if (jobList.contains(job)) {
            jobList.set(jobList.indexOf(job), job);
        } else {
            jobList.add(job);
        }
    }

    public static String getJobJson(String name) {
        for (Job job : jobList) {
            if (job.getName().equals(name)) {
                return new Gson().toJson(job);
            }
        }
        return new Gson().toJson(new Job("not", "found", 0));
    }

    public static String toJsonString() {
        return new Gson().toJson(jobList);
    }

    public static boolean isInKnownJob(String name) {
        for(Job job : jobList) {
            if (job.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
