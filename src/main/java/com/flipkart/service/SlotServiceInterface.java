package com.flipkart.service;

import com.flipkart.bean.Slot;

import java.util.List;
import java.util.Set;

public interface SlotServiceInterface {
    Set<String> getSlotsByCustomerId(String customerId);
    List<Slot> getSlotsByGymId(String gymId);
    Slot getSlotBySlotId(String slotId);
    void createSlot(String date, String startTime, String gymId);
    void updateSlot(String newValue, String updateColumn, String slotId);
    void deleteSlotById(String slotId);
}
