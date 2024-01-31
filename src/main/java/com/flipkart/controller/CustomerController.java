package com.flipkart.controller;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.service.*;
import com.flipkart.service.impl.*;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {
    CustomerServiceInterface customerService = new CustomerServiceImpl();
    UserServiceInterface userService = new UserServiceImpl();
    GymServiceInterface gymService = new GymServiceImpl();
    SlotServiceInterface slotService = new SlotServiceImpl();
    BookingServiceInterface bookingService = new BookingServiceImpl();







    //Customer login
    @GET
    @Path("/login")
    public Response customerLogin(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        try{
            if (userService.authenticate(userName, password,"GYM_CUSTOMER")) {
                userService.login(userName);
                System.out.println("Login Successful");
                return getCustomerProfile(userName);
            } else {
                System.out.println("Login Failed for " + userName);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }catch (Exception exception){
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }

    }

    //View profile
    @GET
    @Path("/view-profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerProfile(@QueryParam("userName") String userName){
        Customer customerProfile = customerService.getCustomerFromId(customerService.getCustomerIdFromUsername(userName));
        System.out.println("Customer"+customerProfile);
        try{
            return Response.ok(customerProfile).build();
        }catch (Exception exception){
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }
    @GET
    @Path("/register")
    public Response customerRegister(@QueryParam("userName") String userName,@QueryParam("password") String password,@QueryParam("email") String email,@QueryParam("contact") String contact){
        //Customer customer=new Customer(userId,userName,email,password,customerPhone,cardDetails);
        boolean regDone=customerService.register(userName,password,email,contact);
        if(!regDone){
            return Response.notModified().build();
        }
        return Response.ok("Registered").build();
    }


    //get centres by City
    @GET
    @Path("/booking/get-city")
    public Response getCentresByCity(){
        try{
            List<String> gymCentreList = gymService.getAreas();
            return Response.ok(gymCentreList).build();
        }catch(Exception exception){
            exception.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        }
    }


    //get slots by Centre
    @GET
    @Path("/booking/get-slots-by-gym")
    public Response getSlotsByCity(@QueryParam("gymId") String gymId){
        try {

            List<Slot> slots =  slotService.getSlotsByGymId(gymId);
            return Response.ok(slots).build();
        }catch (Exception exception){
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    //book a slot
    @POST
    @Path("/booking")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response bookSlot(@QueryParam("slotId") String slotId){

        String date=slotService.getSlotBySlotId(slotId).getDate();
        System.out.println("\n\n\n\n"+ slotId+date);
        boolean slotBooked = bookingService.addBooking(customerService.getCustomerIdFromUsername(userService.getCurrentUsername()),slotId);
        if(slotBooked) return Response.ok("Slot Booked Successfully").build();
        else return Response.ok("Unable to book slot. Either there is no availability or you have an existing booking at same time").build();
    }

    @DELETE
    @Path("/cancel-booking")
    public Response cancelBooking(@QueryParam("bookingId") String bookingId){
        try {
            bookingService.deleteBooking(bookingId);
            return Response.ok("Booking Cancelled Successfully").build();
        }catch (Exception exception){
            return Response.status(400).entity("Something Went Wrong!").build();
        }
    }

    @GET
    @Path("/booking/get-my-booking")
    public Response getCustomerBookings(@QueryParam("customerId") String customerId){
        try {
            return Response.ok(bookingService.getBookingByCustomerId(customerId)).build();
        }catch (Exception exception){
            return Response.status(400).entity("Something Went Wrong!").build();
        }
    }
}