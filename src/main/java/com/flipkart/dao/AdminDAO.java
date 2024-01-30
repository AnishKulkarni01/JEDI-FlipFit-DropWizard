package com.flipkart.dao;

import com.flipkart.bean.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    static AdminDAO adminDAO = null;

    public static synchronized AdminDAO getInstance() {
        if(adminDAO == null) {
            adminDAO =new AdminDAO();
        }

        return adminDAO;
    }
}
