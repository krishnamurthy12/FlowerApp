package com.krish.flowerapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.krish.flowerapp.R;

public class CustomerRegistration extends AppCompatActivity {

    TextView Customer_Name,Customer_Phone,Customer_Address;
    String C_Name,C_Phone,C_Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        Customer_Name = findViewById(R.id.et_cust_name);
        Customer_Phone = findViewById(R.id.et_cust_phone);
        Customer_Address = findViewById(R.id.et_cust_address);

        C_Name = Customer_Name.getText().toString();
        C_Phone = Customer_Phone.getText().toString();
        C_Address= Customer_Address.getText().toString();




    }
}
