package com.flipkart.controller;

import com.flipkart.bean.GymOwner;
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
    GymOwnerServiceInterface gymOwnerService = new GymOwnerServiceImpl();

    UserServiceInterface userService = new UserServiceImpl();
    GymServiceInterface gymService = new GymServiceImpl();
    SlotServiceInterface slotService = new SlotServiceImpl();
    BookingServiceInterface bookingService = new BookingServiceImpl();

    @Path("/gym-centres")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCentresByOwnerId(@QueryParam("gymOwnerId") String gymOwnerId) {
        return Response.ok(gymService.getGymsByOwnerId(gymOwnerId)).build();
    }
    @GET
    @Path("/view-profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGymOwnerProfile(@QueryParam("userName") String userName){
        GymOwner gymOwnerProfile = (gymOwnerService.getGymOwnerFromId(gymOwnerService.getOwnerIdByUsername(userName).get(0)));
        System.out.println("Customer"+gymOwnerProfile);
        try{
            return Response.ok(gymOwnerProfile).build();
        }catch (Exception exception){
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }
    @GET
    @Path("/login")
    public Response GymOwnerLogin(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        if (userService.authenticate(userName, password,"GYM_OWNER")) {
            userService.login(userName);
            System.out.println("Login Successful");
            return getGymOwnerProfile(userName);
        } else {
            System.out.println("Login Failed for " + userName);
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

@POST
@Path("/register")
@Consumes(MediaType.APPLICATION_JSON)
public Response GymOwnerRegister(@QueryParam("userName") String userName,@QueryParam("password") String password,@QueryParam("email") String email,@QueryParam("contact") String contact) {

    boolean registerdGymOwner = gymOwnerService.register(userName, password, email, contact);
    if(!registerdGymOwner)
        return Response.notModified().build();
    return Response.ok(registerdGymOwner).build();
}
@POST
@Path("/add-gym")
@Consumes(MediaType.APPLICATION_JSON)
public Response addGym(@QueryParam("gymId") String gymId) {
    boolean gymApproved = gymService.onBoardGym(gymId);
    if(!gymApproved)
        return Response.notModified().build();
    return Response.ok("Gym Accepted").build();
}


@Path("/get-approval-gym")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response requestGymCentreApproval(@QueryParam("name") String name,@QueryParam("gstin") String gstin,@QueryParam("city") String city,@QueryParam("seats") int seats,@QueryParam("gymOwnerId") String gymOwnerId) {
    gymService.sendGymRequest(name,gstin,city,seats,gymOwnerId);
    return Response.ok("Sent approval request to Admin").build();
}
@Path("/get-gym-centre")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response requestGymCentreById(@QueryParam("gymId") String gymId) {

    return Response.ok(gymService.getGymById(gymId)).build();
}


@Path("/get-available-slots")
@GET
@Consumes(MediaType.APPLICATION_JSON)
public Response getAvailableSlots(@QueryParam("gymId") String centreId,@QueryParam("Date") String strdate) {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date date;
    try {
        date = sdf.parse(strdate);
    } catch (ParseException e) {
        System.out.println("\n\n\n\n\n\n UNABLE TO PARSE");
    }
    return Response.ok(slotService.getAvailableSlotsByCentreAndDate(centreId,strdate)).build();
}


@Path("/get-centres-by-city")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getGymsByCity(@QueryParam("cityName") String cityName) {

    return Response.ok(gymService.getGymByAreas(cityName)).build();
}

@Path("/add-slot")
@POST
@Consumes(MediaType.APPLICATION_JSON)
public Response addSlots(@QueryParam("gymId") String gymId, @QueryParam("date") String date, @QueryParam("startTime") String startTime){
    try {
        slotService.createSlot(date, startTime, gymId);
    }catch (IllegalArgumentException exp){
        System.out.println("illegal arg");
        return Response.notModified().build();
    }
    return Response.ok("Added Slots").build();
}

}
