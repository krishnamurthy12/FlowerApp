package com.krish.flowerapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.krish.flowerapp.R;

public class VendorRegistration extends AppCompatActivity {

    TextView Vendor_name,Vendor_address,Vendor_phone;
    String V_Name,V_Address,V_Phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_registration);

        Vendor_name = findViewById(R.id.et_vendor_name);
        Vendor_address = findViewById(R.id.et_cust_address);
        Vendor_phone = findViewById(R.id.et_vendor_phone);

        V_Name = Vendor_name.getText().toString();
        V_Address = Vendor_address.getText().toString();
        V_Phone = Vendor_phone.getText().toString();


    }
}
