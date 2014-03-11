package com.jhc.figleaf.JobsRestApp.models;

/**
 * Created by hamish dickson on 09/03/2014.
 *
 * Bean for particle
 */
public class Job {
    private String name;
    private String another;
    // in keV
    private int mass;

    public Job() {
    }


    public Job(String name, String another, int mass) {
        this.name = name;
        this.another = another;
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "name='" + name + '\'' +
                ", another='" + another + '\'' +
                ", mass=" + mass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job particle = (Job) o;

        return !(name != null ? !name.equals(particle.name) : particle.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
