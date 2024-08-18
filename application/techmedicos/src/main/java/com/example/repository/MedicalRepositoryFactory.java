package com.example.repository;

public class MedicalRepositoryFactory {
    public static MedicalRepository getMedicalRepository(String dbType){
        if(dbType.equals("mysql")){
            return new MySqlMedicalRepository();
        }
        //throw an exception of dbtype not found
        return null;
    }
}
