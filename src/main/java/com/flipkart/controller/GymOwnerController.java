package com.flipkart.controller;

import com.flipkart.service.*;
import com.flipkart.service.impl.*;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.service.*;
import com.flipkart.service.impl.*;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Path("/gym-owner")
@Produces(MediaType.APPLICATION_JSON)
public class GymOwnerController {
    CustomerServiceInterface customerService = new CustomerServiceImpl();
    UserServiceInterface userService = new UserServiceImpl();
    GymServiceInterface gymService = new GymServiceImpl();
    SlotServiceInterface slotService = new SlotServiceImpl();
    BookingServiceInterface bookingService = new BookingServiceImpl();
    @GET
    @Path("view-all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCentresByOwnerId() {
        return Response.ok(gymOwnerService.viewAllGymOwners()).build();
    }

    @Path("/gym-centres")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCentresByOwnerId(@QueryParam("gymOwnerId") String gymOwnerId) {
        GymCentreService gymService = new GymCentreService();
        return Response.ok(gymService.getAllCentresByOwmerId(gymOwnerId)).build();
    }

    @GET
    @Path("/login")
    public Response GymOwnerLogin(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        GymOwner gymOwner = gymOwnerService.loginGymOwner(userName, password);
        if(gymOwner==null)
            return Response.notModified().build();
        return Response.ok(gymOwner).build();
    }
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GymOwnerRegister(GymOwner gymOwner) {
        GymOwner registerdGymOwner =  gymOwnerService.registerGymOwner(gymOwner);
        if(registerdGymOwner==null)
            return Response.notModified().build();
        return Response.ok(registerdGymOwner).build();
    }
    @POST
    @Path("/add-centre")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGymCentre(GymCentre gym) {
        GymCentre newGymCentre = gymService.addCenter(gym);
        if(newGymCentre==null)
            return Response.notModified().build();
        return Response.ok(newGymCentre).build();
    }

    @Path("/get-approval-owner")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestGymOwnerApproval(@QueryParam("gymOwnerId") String gymOwnerId) {
        gymOwnerService.requestGymOwnerApproval(gymOwnerId);
        return Response.ok("Sent approval request to Admin").build();
    }

    @Path("/get-approval-centre")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestGymCentreApproval(@QueryParam("gymId") String gymId) {
        gymService.requestGymCentreApproval(gymId);
        return Response.ok("Sent approval request to Admin").build();
    }
    @Path("/get-gym-centre")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestGymCentreById(@QueryParam("gymId") String gymId) {

        return Response.ok(gymService.getGymCentreById(gymId)).build();
    }


    @Path("/get-available-slots")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAvailableSlots(@QueryParam("centreId") String centreId,@QueryParam("Date") String strdate) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        java.util.Date date;
        Date sqlDate = null;
        try {
            date = sdf.parse(strdate);
            sqlDate = new Date(date.getTime());
        } catch (ParseException e) {
            System.out.println("\n\n\n\n\n\n UNABLE TO PARSE");
        }
        return Response.ok(gymService.getAvailableSlotsByCentreAndDate(centreId,sqlDate)).build();
    }


    @Path("/get-centres-by-city")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCentreByCity(@QueryParam("cityName") String cityName) {

        return Response.ok(gymService.getCentresByCity(cityName)).build();
    }

    @Path("/add-slots")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSlotsToGym(List<addSlotDTO> slotList){
        try {
            String centreID = slotList.get(0).getCentreID();
            slotService.addSlotListForGym(centreID, slotList);
        }catch (IllegalArgumentException exp){
            System.out.println("illegal arg");
            return Response.notModified().build();
        }
        return Response.ok("Added Slots").build();
    }

}
