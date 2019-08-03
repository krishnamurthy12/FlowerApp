package com.krish.flowerapp.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

@Entity(tableName = "customers_table")
public class Customers {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "customer_id")
    private int id;

    @NonNull
    @ColumnInfo(name = "customer_name")
    private String customerName;

    @NonNull
    @ColumnInfo(name = "customer_number")
    private String mobileNumber;

    @NonNull
    @ColumnInfo(name = "customer_address")
    private String address;

    private Date createdDate;

    private Date updatedDate;


    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @Nullable
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(@Nullable String customerName) {
        this.customerName = customerName;
    }

    @NonNull
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(@NonNull String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }
}
