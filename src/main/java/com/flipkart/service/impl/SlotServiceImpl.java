package com.flipkart.service.impl;

import com.flipkart.bean.Slot;
import com.flipkart.dao.SlotDAO;
import com.flipkart.exceptions.CustomerDneException;
import com.flipkart.exceptions.GymDneException;
import com.flipkart.exceptions.SlotDneException;
import com.flipkart.service.SlotServiceInterface;

import java.util.List;
import java.util.Set;

public class SlotServiceImpl implements SlotServiceInterface {
    SlotDAO slotDAO = SlotDAO.getInstance();

    public Set<String> getSlotsByCustomerId(String customerId){
        try {
            return slotDAO.getSlotsByCustomerId(customerId);
        } catch (CustomerDneException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Slot> getSlotsByGymId(String gymId){
        try {
            return slotDAO.getSlotsByGymId(gymId);
        } catch (GymDneException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Slot getSlotBySlotId(String slotId){
        try {
            return slotDAO.getSlotBySlotId(slotId);
        } catch (SlotDneException e) {
            System.out.println(e.getMessage());        }
        return null;
    }

    public void createSlot(String date, String startTime, String gymId){
        try {
            slotDAO.createSlot(date, startTime, gymId);
        } catch (GymDneException e) {
            System.out.println(e.getMessage());
        }
        return ;
    }

    public void updateSlot(String newValue, String updateColumn, String slotId) {
        try {
            slotDAO.updateSlot(newValue, updateColumn, slotId);
        } catch (SlotDneException e) {
            System.out.println(e.getMessage());
        }
        return;
    }

    public void deleteSlotById(String slotId){
        try {
            slotDAO.deleteSlotById(slotId);
        } catch (SlotDneException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Slot> getAvailableSlotsByCentreAndDate(String gymId, String date){
        try {
            return slotDAO.getSlotsByGymDate(gymId, date);
        } catch (SlotDneException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
