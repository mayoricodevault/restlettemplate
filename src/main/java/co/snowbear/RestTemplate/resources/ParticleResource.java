package co.snowbear.RestTemplate.resources;

import co.snowbear.RestTemplate.models.Particle;
import co.snowbear.RestTemplate.models.Particles;
import com.wordnik.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hamish dickson on 08/03/2014.
 *
 * Go forth and use at will
 */

@Path("/particle")
@Api( value = "/particle", description = "Some of the universes particles" )
public class ParticleResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "List all the particles in the universe ... nearly ..",
            notes = "It's known that there may be a few more bits and bobs out there",
            response = Response.class,
            responseContainer = "JSON"
    )
    public Response getParticles() {
        String output = Particles.toJsonString();
        return Response.ok().entity(output).build();
    }

    @GET
    @Path("/{particleName}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Find particle details from it's name",
            notes = "It's known that there may be a few more bits and bobs out there",
            response = Response.class,
            responseContainer = "JSON"
    )
    public Response getParticle(@ApiParam(value = "Particle name", required = true) @PathParam("particleName") String particleName) {
        if (Particles.isInKnownParticles(particleName)) {
            return Response.ok().entity(Particles.getParticleJson(particleName)).build();
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    /**
     * Use POST to create new entities
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
    @ApiOperation(
            value = "Add a new particle to the known universe",
            notes = "This would have once won you the nobel prize ... now it makes you hated"
    )
    public Response addParticle(@ApiParam(value = "Particle that's been discovered", required = true) Particle particle) {
        Particles.addParticle(particle);

        // I'm going to return the whole lot just to be nice :)
        return Response.ok().entity(Particles.toJsonString()).build();
    }

    /**
     * Update or create a record if it doesn't exist
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Update an existing particle"
    )
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Particle not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    public Response updateParticle(
            @ApiParam(value = "Particle who's properties have changed", required = true) Particle particle) {
        Particles.setParticle(particle);
        return Response.ok().entity(Particles.toJsonString()).build();
    }

    @DELETE
    @Path("/destroy/{particleName}")
    @ApiOperation(
            value = "Delete particle from the universe",
            notes = "Particle physicists the world over will thank you for this"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid particle name supplied"),
                    @ApiResponse(code = 404, message = "Particle not found")
            }
    )
    public Response deleteParticle(
            @ApiParam(value = "Name of the particle to be destroyed", required = true) @PathParam("particleName") String particleName) {
        if (Particles.deleteParticle(particleName)) {
            return Response.ok().entity(Particles.toJsonString()).build();
        } else {
            return Response.status(400).build();
        }

    }
}
