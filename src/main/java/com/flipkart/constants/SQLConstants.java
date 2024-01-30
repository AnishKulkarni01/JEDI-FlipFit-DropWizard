package com.flipkart.constants;

public class SQLConstants {
        //AUTHENTICATE
        public static final String AUTHENTICATE_USER = "SELECT * FROM jedi_flipfit_schema.User WHERE username = ? AND password = ? AND role = ?;";
        public static final String ADD_USER = "INSERT INTO jedi_flipfit_schema.User (username, password, role) VALUES (?, ?, ?);";
        public static final String GET_USER = "SELECT * FROM jedi_flipfit_schema.User WHERE username = ?;";
        public static final String UPDATE_USER_PASSWORD = "UPDATE jedi_flipfit_schema.User SET password = ? WHERE username = ?;";

        // ------------------------ GYM OWNER ------------------------
        public static final String FETCH_ALL_GYM_OWNERS_QUERY = "SELECT * FROM jedi_flipfit_schema.GymOwner;";
        public static final String FETCH_ALL_PENDING_GYM_OWNERS_QUERY = "SELECT * FROM jedi_flipfit_schema.GymOwner where isApproved = \"false\";";
        public static final String SEND_GYM_OWNER_APPROVAL_REQ_QUERY = "UPDATE jedi_flipfit_schema.GymOwner SET isApproved = 2 WHERE Id =?;";
        public static final String ADD_GYM_CENTRE_QUERY = "INSERT INTO jedi_flipfit_schema.GymCentre (centreId, ownerId, centreName, gstin, city, capacity, price, isApproved) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        //  ------------ GYM OWNER ------------
        public static final String REGISTER_GYM_OWNER = "Insert into GymOwner values (?,?,?,?,?,?,?);";
        public static final String LOGIN_GYM_OWNER = "Select * from GymOwner where name=? and password=?;";
        public static final String SQL_APPROVE_GYM_OWNER_BY_ID_QUERY="Update GymOwner Set isApproved=\"true\" WHERE gymOwnerId=?;";
        public static final String UPDATE_GYM_OWNER_DETAILS_EMAIL = "UPDATE jedi_flipfit_schema.GymOwner SET email = ? WHERE gymOwnerId =?;";
        public static final String SQL_REJECT_GYM_OWNER_BY_ID_QUERY = "DELETE FROM jedi_flipfit_schema.GymOwner where gymOwnerId = ?;";
        public static final String UPDATE_GYM_OWNER_DETAILS_CONTACT = "UPDATE jedi_flipfit_schema.GymOwner SET contact = ? WHERE gymOwnerId =?;";
        public static final String GET_GYM_OWNER_BY_ID = "SELECT * FROM jedi_flipfit_schema.GymOwner WHERE gymOwnerId = ?;";
        public static final String ADD_NEW_GYM_OWNER = "INSERT INTO jedi_flipfit_schema.GymOwner (username,password,email,contact) VALUES ( ?, ?, ?, ?);";
        public static final String GET_GYM_OWNER_BY_USERNAME = "SELECT * FROM jedi_flipfit_schema.GymOwner WHERE username = ?;";

        // ------------------------ GYM CENTRE ------------------------
        public static final String FETCH_GYM_CENTRES_BY_OWNER_ID = "SELECT * FROM jedi_flipfit_schema.GymCentre where ownerId = ?;";
        public static final String FETCH_ALL_PENDING_GYM_REQUESTS = "SELECT * FROM jedi_flipfit_schema.Gym where isApproved = \"false\";";
        public static final String FETCH_ALL_PENDING_GYM_REQUESTS_BY_GYMOWNERID = "SELECT * FROM jedi_flipfit_schema.Gym where gymOwnerId = ? AND isApproved=\"false\";";
        public static final String APPROVE_GYM_BY_ID="Update Gym Set isApproved=? WHERE gymId=?;";
        public static final String FETCH_GYM_CENTRES_BY_CITY = "SELECT * FROM jedi_flipfit_schema.GymCentre where city = ?;";
        public static final String SEND_GYM_ONBOARD_REQUEST = "INSERT INTO jedi_flipfit_schema.Gym (name,city,gstin,seats,gymOwnerId) VALUES ( ?, ?, ?, ?,?);";
        public static final String UPDATE_GYM_DETAILS = "UPDATE jedi_flipfit_schema.Gym SET ? = ? WHERE gymId =?;";
        public static final String FETCH_ALL_AREAS = "SELECT DISTINCT city FROM jedi_flipfit_schema.Gym WHERE isApproved=\"true\"";
        public static final String FETCH_GYMS_BY_OWNER = "SELECT * FROM jedi_flipfit_schema.Gym WHERE gymOwnerId = ? AND isApproved=\"true\"";
        public static final String FETCH_GYMS_BY_AREA = "SELECT * FROM jedi_flipfit_schema.Gym WHERE city = ? AND isApproved=\"true\"";
        public static final String FETCH_GYMS_BY_ID = "SELECT * FROM jedi_flipfit_schema.Gym WHERE gymId = ? AND isApproved=\"true\"";
        public static final String UPDATE_GYM_DETAILS_NAME = "UPDATE jedi_flipfit_schema.Gym SET name = ? WHERE gymId =?;";
        public static final String UPDATE_GYM_DETAILS_CITY = "UPDATE jedi_flipfit_schema.Gym SET city = ? WHERE gymId =?;";
        public static final String UPDATE_GYM_DETAILS_SEATS = "UPDATE jedi_flipfit_schema.Gym SET seats = ? WHERE gymId =?;";
        public static final String UPDATE_GYM_DETAILS_GSTIN = "UPDATE jedi_flipfit_schema.Gym SET gstin = ? WHERE gymId =?;";
        public static final String DELETE_GYM_BY_ID= "Delete from jedi_flipfit_schema.Gym where gymId = ?;";

        //    ------- Customer ---------------
        public static final String CUSTOMER_LOGIN_QUERY = "SELECT * FROM jedi_flipfit_schema.Customer WHERE username = ? AND password = ?;";
        public static final String ADD_NEW_CUSTOMER = "INSERT INTO jedi_flipfit_schema.Customer (username,password,email,contact) VALUES ( ?, ?, ?, ?);";
        public static final String GET_CUSTOMER_BY_ID = "SELECT * FROM jedi_flipfit_schema.Customer WHERE customerId = ?;";
        public static final String GET_CUSTOMER_BY_USERNAME = "SELECT * FROM jedi_flipfit_schema.Customer WHERE username = ?;";
        public static final String UPDATE_CUSTOMER_DETAILS_EMAIL = "UPDATE jedi_flipfit_schema.Customer SET email = ? WHERE customerId =?;";
        public static final String UPDATE_CUSTOMER_DETAILS_CONTACT = "UPDATE jedi_flipfit_schema.Customer SET contact = ? WHERE customerId =?;";

        //  ----------- Booking -----------
        public static final String GET_BOOKING_BY_CUSTOMER_ID ="Select * From jedi_flipfit_schema.Booking where customerId = ?;";
        public static final String CANCEL_BOOKING_BY_ID= "Delete from jedi_flipfit_schema.Booking where bookingId = ?;";
        public static final String ADD_BOOKING= "INSERT INTO jedi_flipfit_schema.Booking (customerID, slotId) values(?, ?);";
        public static final String COUNT_BOOKINGS_BY_SLOTID="Select COUNT(slotId) as NumBookedSeats from jedi_flipfit_schema.Booking where slotId=?";
        // ------------- Schedule -------------
        public static final String ADD_SCHEDULE="INSERT INTO jedi_flipfit_schema.Schedule(scheduleId, date, slotId, availability ) values (?,?,?,?);";
        public static final String GET_SCHEDULES_BY_DATE="SELECT * FROM jedi_flipfit_schema.Schedule WHERE date=?;";
        public static final String GET_SCHEDULE_BY_ID ="SELECT * FROM jedi_flipfit_schema.Schedule WHERE scheduleId=?;";
        public static final String MODIFY_SCHEDULE_AVAILABILITY ="UPDATE `flipfit`.`schedule` SET availability = ? WHERE (`scheduleId` = ?);";

        // ---------------- Slot ----------------
        public static final String FETCH_ALL_SLOTS ="SELECT * FROM jedi_flipfit_schema.Slot;";
        public static final String FETCH_SLOT_BY_GYMID ="SELECT * FROM jedi_flipfit_schema.Slot WHERE gymId=?;";
        //public static final String FETCH_SLOT_BY_CUSTOMERID ="SELECT * FROM jedi_flipfit_schema.Slot WHERE customerId=?;";

        public static final String ADD_SLOT ="INSERT INTO jedi_flipfit_schema.Slot( gymId,date, startTime) values (?, ?, ?);";
        public static final String FETCH_SLOT_BY_ID = "SELECT * FROM jedi_flipfit_schema.Slot WHERE slotId=?;";
        public static final String UPDATE_SLOT_DETAILS = "UPDATE jedi_flipfit_schema.Slot SET ? = ? WHERE slotId =?;";
        public static final String FETCH_SLOT_BY_ID_AND_CENTRE_ID = "SELECT * FROM jedi_flipfit_schema.Slot WHERE slotId=? AND centreId=?;";
        public static final String GET_BOOKING_BY_BOOKING_ID ="Select * From jedi_flipfit_schema.Booking where bookingId = ?;";
        public static final String GET_USERPLAN_BY_CUSTOMER_ID = "select * from slot join schedule where slot.slotId=schedule.slotId and schedule.scheduleId=?;";public static final String UPDATE_SLOT_DETAILS_DATE = "UPDATE jedi_flipfit_schema.Slot SET date = ? WHERE slotId =?;";
        public static final String DELETE_SLOT_BY_ID= "Delete from jedi_flipfit_schema.Slot where slotId = ?;";

        public static final String UPDATE_SLOT_DETAILS_STARTTIME = "UPDATE jedi_flipfit_schema.Slot SET startTime = ? WHERE slotId =?;";
}
