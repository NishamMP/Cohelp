package com.example.cohelp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.webkit.WebView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Web extends AppCompatActivity {

    WebView webView;
    String ogdate,ogtime;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView=findViewById(R.id.webView);



        Intent intent=getIntent();

        String Name=intent.getStringExtra("1Name");
        String Location=intent.getStringExtra("2Loca");
        String Veich_name=intent.getStringExtra("3Veich_Na");
        String Veich_no=intent.getStringExtra("4Veich_No");
        String Address=intent.getStringExtra("5Add");
        String Purpose=intent.getStringExtra("6Need");
        String With=intent.getStringExtra("7With");
        String Phone=intent.getStringExtra("8Phon");
        String go=intent.getStringExtra("go");
        String went=intent.getStringExtra("went");
        String Sign="                        ";

     try {
         DateFormat f=new SimpleDateFormat("dd-MM-yyyy HH:mm");
         Date d=f.parse(go);
         DateFormat date=new SimpleDateFormat("dd-MM-yyyy");
         DateFormat time=new SimpleDateFormat("hh:mm");

          ogdate=(date.format(d)).toString();
          ogtime=(time.format(d)).toString();
     }catch (ParseException e){
         e.printStackTrace();
     }



        String html="<html>\n" +
                "<head>\n" +
                "\t<html>\n" +
                "\t<head>\n" +
                "\t</head>\n" +
                "<body>\n" +
                "<br>\n" +
                "<br>\n" +
                "<br>\n" +
                "<h1 style=\"text-align:center;\"><u>സത്യവാങ്മൂലം</u></h1>\n" +
                "<br>\n" +
                "<br>\n" +
                "<br>\n" +
                "\n" +
                "<p style=\"font-size:20px;\"><b> "+Veich_no+"</b>  നമ്പർ വാഹനത്തിൽ <b>"+Veich_name+"</b> യാത്ര ചെയ്യുന്ന<b> "+Name+" </b> എന്ന ഞാൻ <b> "+Address+" </b>(വിലാസം)<b>"+Purpose+"</b> (ആവശ്യം)  " +
                "എന്നോടൊപ്പമുള്ളത് <b>"+With+"</b> ആണ്.\n" +
                "<p>ഞാൻ <b>(DATE) (TIME )(തീയതി/സമയം)</b> തിരിച്ചു പോകും </p>\n" +
                "<br>\n" +
                "<br>\n" +
                "<br>\n" +
                "<br>\n" +
                "<p style=\"text-align:right ;margin-right:50px;\">ഒപ്പ് : "+Sign+"</p>\n" +
                "<br>\n" +
                "<p style=\"text-align:right;\">പേര് : "+Name+" </p>\n" +
                "<p style=\"text-align:right;\">മൊബൈൽ നമ്പർ : "+Phone+"</p>\n" +
                "\n" +
                "\n" +
                "<p>തിയതി : "+ogdate+"</p>\n" +
                "<p>സമയം : "+ogtime+"</p>\n" +
                "<p>സ്ഥലം : "+Location+"</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
        webView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);

        createpdf();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public  void createpdf(){
        Context context=Web.this;
        PrintManager printManager=(PrintManager)Web.this.getSystemService(context.PRINT_SERVICE);
        PrintDocumentAdapter adapter=null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            adapter=webView.createPrintDocumentAdapter();
        }
        String JobName=getString(R.string.app_name)+"Document";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            PrintJob printJob=printManager.print(JobName,adapter,new PrintAttributes.Builder().build());
        }

    }
}