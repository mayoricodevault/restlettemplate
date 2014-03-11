package com.jhc.figleaf.JobsRestApp.models;

import com.google.gson.Gson;
import com.jhc.figleaf.JobsRestApp.models.Particle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamish dickson on 09/03/2014.
 *
 * Bit of dummy data
 */
public class Particles {
    private static List<Particle> particleList = new ArrayList<Particle>();

    static {
        // just a few leptons for now
        particleList.add(new Particle("electron", "one", 500));
        particleList.add(new Particle("positron", "two", 500));
        particleList.add(new Particle("muon", "three", 105700));
    }

    public static void addParticle(Particle particle) {
        particleList.add(particle);
    }

    public static boolean deleteParticle(String name) {
        for (Particle particle : particleList) {
            if (particle.getName().equals(name)) {
                particleList.remove(particle);
                return true;
            }
        }
        return false;
    }

    public static void setParticle(Particle particle) {
        if (particleList.contains(particle)) {
            particleList.set(particleList.indexOf(particle), particle);
        } else {
            particleList.add(particle);
        }
    }

    public static String getParticleJson(String name) {
        for (Particle particle : particleList) {
            if (particle.getName().equals(name)) {
                return new Gson().toJson(particle);
            }
        }
        return new Gson().toJson(new Particle("not", "found", 0));
    }

    public static String toJsonString() {
        return new Gson().toJson(particleList);
    }

    public static boolean isInKnownParticles(String name) {
        for(Particle particle : particleList) {
            if (particle.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
