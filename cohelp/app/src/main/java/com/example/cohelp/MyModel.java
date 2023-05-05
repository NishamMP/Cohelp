package com.example.cohelp;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class MyModel {
    String Slno,NameOfState,PhoneNo;

    public MyModel(String slno, String nameOfState, String phoneNo) {
        Slno = slno;
        NameOfState = nameOfState;
        PhoneNo = phoneNo;
    }

    public String getSlno() {
        return Slno;

    }

    public String getNameOfState() {
        return NameOfState;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }
}
