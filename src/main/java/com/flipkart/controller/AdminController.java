package com.flipkart.controller;

import com.codahale.metrics.annotation.Timed;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.service.*;
import com.flipkart.service.impl.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminController {

    CustomerServiceInterface customerService = new CustomerServiceImpl();
    UserServiceInterface userService = new UserServiceImpl();
    GymServiceInterface gymService = new GymServiceImpl();
    SlotServiceInterface slotService = new SlotServiceImpl();
    BookingServiceInterface bookingService = new BookingServiceImpl();
    GymOwnerServiceInterface gymOwnerService=new GymOwnerServiceImpl();



    @GET
    @Path("/login")
    public Response adminLogin(@QueryParam("userName") String userName, @QueryParam("password") String password) {
       // boolean admin =false;
        try{
            if (userService.authenticate(userName, password,"ADMIN")) {
                userService.login(userName);
                System.out.println("Login Successful");
                return Response.ok("true").build();
            }
            return Response.ok("wrong credentials").build();

        }catch (Exception exception){
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }

    @GET
    @Path("/gym-owner/pending-list")
    public Response viewPendingGymOwners() {
        try{
            List<GymOwner> gymOwnerList = gymOwnerService.getPendingGymOwners();
            return Response.ok(gymOwnerList).build();
        } catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/gym-centre/pending-list")
    public Response viewPendingGymCentres(){
        try{
            List<Gym> gymCentreList = gymService.viewAllPendingRequests();
            return Response.ok(gymCentreList).build();
        } catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/gym-owner/approve")
    public Response approveGymOwner(@QueryParam("gymOwnerId") String gymOwnerId){
        try{
            gymOwnerService.approveGymOwner(gymOwnerId);
            return Response.ok("Request Handled").build();
        } catch (Exception e){
            return Response.status(400).entity("Invalid Gym Owner details").build();
        }
    }
    @POST
    @Path("/gym-owner/reject")
    public Response rejectGymOwner(@QueryParam("gymOwnerId") String gymOwnerId){
        try{
            gymOwnerService.rejectGymOwner(gymOwnerId);
            return Response.ok("Request Handled").build();
        } catch (Exception e){
            return Response.status(400).entity("Invalid Gym Owner details").build();
        }
    }

    @POST
    @Path("/gym-centre/approve")
    public Response approveGym(@QueryParam("gymId") String gymId){
        try{
            gymService.onBoardGym(gymId);
            return Response.ok("Request Handled").build();
        } catch (Exception e){
            return Response.status(400).entity("Invalid Gym Centre details").build();
        }

    }
    @POST
    @Path("/gym-centre/reject")
    public Response rejectGym(@QueryParam("gymId") String gymId){
        try{
            gymService.deleteGymRequest(gymId);
            return Response.ok("Request Handled").build();
        } catch (Exception e){
            return Response.status(400).entity("Invalid Gym Centre details").build();
        }

    }
}