package com.jhc.figleaf.JobsRestApp.models;

/**
 * Created by hamish dickson on 09/03/2014.
 *
 * Bean for particle
 */
public class Job {
    private int jobNumber;
    private String description;
    private String whoDo;
    private String status;


    public Job(int jobNumber, String description, String whoDo, String status) {
        this.jobNumber = jobNumber;
        this.description = description;
        this.whoDo = whoDo;
        this.status = status;
    }

    public int getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhoDo() {
        return whoDo;
    }

    public void setWhoDo(String whoDo) {
        this.whoDo = whoDo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        return jobNumber == job.jobNumber;

    }

    @Override
    public int hashCode() {
        return jobNumber;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobNumber=" + jobNumber +
                ", description='" + description + '\'' +
                ", whoDo='" + whoDo + '\'' +
                ", status=" + status +
                '}';
    }
}
