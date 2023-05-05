package com.example.cohelp;



public class DisModel {
    String Dist_Name,Cohelp_No,Amb_No,Oxi_No;

    public DisModel(String Dist_Name, String Cohelp_No,String Amb_No,String Oxi_No) {
        this.Dist_Name=Dist_Name;
        this.Cohelp_No=Cohelp_No;
        this.Amb_No=Amb_No;
        this.Oxi_No=Oxi_No;
    }
    public String getDist_Name() {
        return Dist_Name;
    }

    public String getCohelp_No() { return Cohelp_No; }

    public String getAmb_No() { return Amb_No; }

    public String getOxi_No() { return Oxi_No; }
}