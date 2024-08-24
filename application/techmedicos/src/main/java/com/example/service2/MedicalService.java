package com.example.service2;

import com.example.models.Employee;

import java.util.ArrayList;

public interface MedicalService {
    Employee login(int id, String password);
    //Save to database - Must Allow user to store file, extract path of that file and load the data in doctor Array and Send it to database.
    ArrayList <String> showDoctors();
}
