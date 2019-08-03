package com.krish.flowerapp.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "vendors_table")
public class Vendors {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "vendors_id")
    private String id;

    @Nullable
    @ColumnInfo(name = "vendors_name")
    private String customerName;

    @NonNull
    @ColumnInfo(name = "vendors_number")
    private String mobileNumber;

    @NonNull
    @ColumnInfo(name = "vendors_address")
    private String address;


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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
