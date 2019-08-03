package com.krish.flowerapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CustomersDAO {

    @Insert
    void insertCustomer(Customers customers);

    @Insert
    void insertFlowers(Customers customers);


    @Query("SELECT * FROM customers_table")
    LiveData<List<Customers>> getAllCustomers();

    @Query("SELECT * FROM customers_table WHERE customer_id=:customerID")
    LiveData<Customers> getCustomer(String customerID);

    @Update
    void update(Customers customers);

    @Delete
    int delete(Customers customers);
}
