package com.example.zhenli.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DetailsActivity extends AppCompatActivity {
    private Button but1 = null;
    private Button but2 = null;
    private Button but3 = null;

    String streetaddress;
    String cityaddress;
    String selectstate;
    String deg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        try {
            but1 = (Button) findViewById(R.id.button8);
            but1.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
            but1.setSelected(true);
            but2 = (Button) findViewById(R.id.button9);
            but2.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));

            streetaddress = getIntent().getStringExtra("sa");
            cityaddress = getIntent().getStringExtra("ct");
            selectstate = getIntent().getStringExtra("st");
            deg = getIntent().getStringExtra("deg");

            TextView tit;
            tit = (TextView) findViewById(R.id.mytext1);
            tit.setText("More Details for " + cityaddress + ", " + selectstate);

            tit = (TextView) findViewById(R.id.t3);
            if(deg.equals("Fahrenheit"))
                tit.setText("Temp("+(char) 0x00B0+"F)");
            else
                tit.setText("Temp("+(char) 0x00B0+"C)");

            String ctime[] = new String[48];
            int cicon[] = new int[48];
            double temp[] = new double[48];
            int tt[] = new int[48];

            JSONObject obj = new JSONObject(getIntent().getStringExtra("updateui"));
            JSONObject hour = obj.getJSONObject("hourly");
            JSONArray hourdata = hour.getJSONArray("data");

            String timezone = obj.getString("timezone");

            SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
            format.setTimeZone(TimeZone.getTimeZone(timezone));

            for(int i=0;i<48;i++) {
                int ti = hourdata.getJSONObject(i).getInt("time");
                Date tii = new Date((long)ti*1000);
                ctime[i] = format.format(tii);

                String icon = hourdata.getJSONObject(i).getString("icon");
                if(icon.equals("clear-day"))
                    cicon[i]=R.drawable.clear;
                else if(icon.equals("clear-night"))
                    cicon[i]=R.drawable.clearnight;
                else if(icon.equals("rain"))
                    cicon[i]=R.drawable.rain;
                else if(icon.equals("snow"))
                    cicon[i]=R.drawable.snow;
                else if(icon.equals("sleet"))
                    cicon[i]=R.drawable.sleet;
                else if(icon.equals("wind"))
                    cicon[i]=R.drawable.wind;
                else if(icon.equals("fog"))
                    cicon[i]=R.drawable.fog;
                else if(icon.equals("cloudy"))
                    cicon[i]=R.drawable.cloudy;
                else if(icon.equals("partly-cloudy-day"))
                    cicon[i]=R.drawable.cloudday;
                else if(icon.equals("partly-cloudy-night"))
                    cicon[i]=R.drawable.cloudnight;

                temp[i] = hourdata.getJSONObject(i).getDouble("temperature");
                tt[i] = (int)temp[i];
            }

            int mintemp[] = new int[8];
            int maxtemp[] = new int[8];
            String timesec[] = new String[8];
            int iconsec[] = new int[8];

            JSONObject day = obj.getJSONObject("daily");
            JSONArray daydata = day.getJSONArray("data");
            String timezonesec = obj.getString("timezone");
            SimpleDateFormat formatsec = new SimpleDateFormat("EEEE, MMM dd");
            formatsec.setTimeZone(TimeZone.getTimeZone(timezonesec));

            for(int i=1;i<=7;i++)
            {
                double mit = daydata.getJSONObject(i).getDouble("temperatureMin");
                double mat = daydata.getJSONObject(i).getDouble("temperatureMax");

                mintemp[i] = (int)mit;
                maxtemp[i] = (int)mat;

                int ti = daydata.getJSONObject(i).getInt("time");
                Date tii = new Date((long)ti*1000);
                timesec[i] = formatsec.format(tii);
                String icon = daydata.getJSONObject(i).getString("icon");

                if(icon.equals("clear-day"))
                    iconsec[i]=R.drawable.clear;
                else if(icon.equals("clear-night"))
                    iconsec[i]=R.drawable.clearnight;
                else if(icon.equals("rain"))
                    iconsec[i]=R.drawable.rain;
                else if(icon.equals("snow"))
                    iconsec[i]=R.drawable.snow;
                else if(icon.equals("sleet"))
                    iconsec[i]=R.drawable.sleet;
                else if(icon.equals("wind"))
                    iconsec[i]=R.drawable.wind;
                else if(icon.equals("fog"))
                    iconsec[i]=R.drawable.fog;
                else if(icon.equals("cloudy"))
                    iconsec[i]=R.drawable.cloudy;
                else if(icon.equals("partly-cloudy-day"))
                    iconsec[i]=R.drawable.cloudday;
                else if(icon.equals("partly-cloudy-night"))
                    iconsec[i]=R.drawable.cloudnight;
            }


            View tl2 = (TableLayout) findViewById(R.id.tablelayout2);
            tl2.setVisibility(View.VISIBLE);
            View tl3 = (TableLayout) findViewById(R.id.tablelayout3);
            tl3.setVisibility(View.GONE);

            View trr;
            trr = (TableRow) findViewById(R.id.tr1);
            trr.setBackgroundResource(R.color.sysCyan);

            trr = (TableRow) findViewById(R.id.tr2);
            trr.setBackgroundResource(R.color.sysGray);
            trr = (TableRow) findViewById(R.id.tr4);
            trr.setBackgroundResource(R.color.sysGray);
            trr = (TableRow) findViewById(R.id.tr6);
            trr.setBackgroundResource(R.color.sysGray);
            trr = (TableRow) findViewById(R.id.tr8);
            trr.setBackgroundResource(R.color.sysGray);
            trr = (TableRow) findViewById(R.id.tr10);
            trr.setBackgroundResource(R.color.sysGray);
            trr = (TableRow) findViewById(R.id.tr12);
            trr.setBackgroundResource(R.color.sysGray);
            trr = (TableRow) findViewById(R.id.tr14);
            trr.setBackgroundResource(R.color.sysGray);
            trr = (TableRow) findViewById(R.id.tr16);
            trr.setBackgroundResource(R.color.sysGray);
            trr = (TableRow) findViewById(R.id.tr18);
            trr.setBackgroundResource(R.color.sysGray);
            trr = (TableRow) findViewById(R.id.tr20);
            trr.setBackgroundResource(R.color.sysGray);
            trr = (TableRow) findViewById(R.id.tr22);
            trr.setBackgroundResource(R.color.sysGray);
            trr = (TableRow) findViewById(R.id.tr24);
            trr.setBackgroundResource(R.color.sysGray);

            trr = (TableRow) findViewById(R.id.tr51);
            trr.setBackgroundResource(R.color.sysGray);
            View tr52 = (TableRow) findViewById(R.id.tr52);
            tr52.setVisibility(View.GONE);
            View tr53 = (TableRow) findViewById(R.id.tr53);
            tr53.setVisibility(View.GONE);
            View tr54 = (TableRow) findViewById(R.id.tr54);
            tr54.setVisibility(View.GONE);
            View tr55 = (TableRow) findViewById(R.id.tr55);
            tr55.setVisibility(View.GONE);
            View tr56 = (TableRow) findViewById(R.id.tr56);
            tr56.setVisibility(View.GONE);
            View tr57 = (TableRow) findViewById(R.id.tr57);
            tr57.setVisibility(View.GONE);

            View tr58 = (TableRow) findViewById(R.id.tr58);
            tr58.setVisibility(View.GONE);
            View tr59 = (TableRow) findViewById(R.id.tr59);
            tr59.setVisibility(View.GONE);

            View tr60 = (TableRow) findViewById(R.id.tr60);
            tr60.setVisibility(View.GONE);
            View tr61 = (TableRow) findViewById(R.id.tr61);
            tr61.setVisibility(View.GONE);

            View tr62 = (TableRow) findViewById(R.id.tr62);
            tr62.setVisibility(View.GONE);
            View tr63 = (TableRow) findViewById(R.id.tr63);
            tr63.setVisibility(View.GONE);

            View tr64 = (TableRow) findViewById(R.id.tr64);
            tr64.setVisibility(View.GONE);
            View tr65 = (TableRow) findViewById(R.id.tr65);
            tr65.setVisibility(View.GONE);

            View tr66 = (TableRow) findViewById(R.id.tr66);
            tr66.setVisibility(View.GONE);
            View tr67 = (TableRow) findViewById(R.id.tr67);
            tr67.setVisibility(View.GONE);

            View tr68 = (TableRow) findViewById(R.id.tr68);
            tr68.setVisibility(View.GONE);
            View tr69 = (TableRow) findViewById(R.id.tr69);
            tr69.setVisibility(View.GONE);

            View tr70 = (TableRow) findViewById(R.id.tr70);
            tr70.setVisibility(View.GONE);
            View tr71 = (TableRow) findViewById(R.id.tr71);
            tr71.setVisibility(View.GONE);

            View tr72 = (TableRow) findViewById(R.id.tr72);
            tr72.setVisibility(View.GONE);
            View tr73 = (TableRow) findViewById(R.id.tr73);
            tr73.setVisibility(View.GONE);

            View tr74 = (TableRow) findViewById(R.id.tr74);
            tr74.setVisibility(View.GONE);
            View tr75 = (TableRow) findViewById(R.id.tr75);
            tr75.setVisibility(View.GONE);

            tr52.setBackgroundResource(R.color.sysGray);
            tr54.setBackgroundResource(R.color.sysGray);
            tr56.setBackgroundResource(R.color.sysGray);
            tr58.setBackgroundResource(R.color.sysGray);
            tr60.setBackgroundResource(R.color.sysGray);
            tr62.setBackgroundResource(R.color.sysGray);
            tr64.setBackgroundResource(R.color.sysGray);
            tr66.setBackgroundResource(R.color.sysGray);
            tr68.setBackgroundResource(R.color.sysGray);
            tr70.setBackgroundResource(R.color.sysGray);
            tr72.setBackgroundResource(R.color.sysGray);
            tr74.setBackgroundResource(R.color.sysGray);

            TextView tv;
            ImageView iv;

            tv = (TextView) findViewById(R.id.t4);
            tv.setText(ctime[0]);

            iv = (ImageView) findViewById(R.id.t5);
            iv.setImageResource(cicon[0]);

            tv = (TextView) findViewById(R.id.t6);
            tv.setText(tt[0] + "");

            tv = (TextView) findViewById(R.id.t7);
            tv.setText(ctime[1]);

            iv = (ImageView) findViewById(R.id.t8);
            iv.setImageResource(cicon[1]);

            tv = (TextView) findViewById(R.id.t9);
            tv.setText(tt[1] + "");

            tv = (TextView) findViewById(R.id.t10);
            tv.setText(ctime[2]);

            iv = (ImageView) findViewById(R.id.t11);
            iv.setImageResource(cicon[2]);

            tv = (TextView) findViewById(R.id.t12);
            tv.setText(tt[2] + "");

            tv = (TextView) findViewById(R.id.t13);
            tv.setText(ctime[3]);

            iv = (ImageView) findViewById(R.id.t14);
            iv.setImageResource(cicon[3]);

            tv = (TextView) findViewById(R.id.t15);
            tv.setText(tt[3] + "");

            tv = (TextView) findViewById(R.id.t16);
            tv.setText(ctime[4]);

            iv = (ImageView) findViewById(R.id.t17);
            iv.setImageResource(cicon[4]);

            tv = (TextView) findViewById(R.id.t18);
            tv.setText(tt[4] + "");

            tv = (TextView) findViewById(R.id.t19);
            tv.setText(ctime[5]);

            iv = (ImageView) findViewById(R.id.t20);
            iv.setImageResource(cicon[5]);

            tv = (TextView) findViewById(R.id.t21);
            tv.setText(tt[5] + "");

            tv = (TextView) findViewById(R.id.t22);
            tv.setText(ctime[6]);

            iv = (ImageView) findViewById(R.id.t23);
            iv.setImageResource(cicon[6]);

            tv = (TextView) findViewById(R.id.t24);
            tv.setText(tt[6] + "");

            tv = (TextView) findViewById(R.id.t25);
            tv.setText(ctime[7]);

            iv = (ImageView) findViewById(R.id.t26);
            iv.setImageResource(cicon[7]);

            tv = (TextView) findViewById(R.id.t27);
            tv.setText(tt[7] + "");

            tv = (TextView) findViewById(R.id.t28);
            tv.setText(ctime[8]);

            iv = (ImageView) findViewById(R.id.t29);
            iv.setImageResource(cicon[8]);

            tv = (TextView) findViewById(R.id.t30);
            tv.setText(tt[8] + "");

            tv = (TextView) findViewById(R.id.t31);
            tv.setText(ctime[9]);

            iv = (ImageView) findViewById(R.id.t32);
            iv.setImageResource(cicon[9]);

            tv = (TextView) findViewById(R.id.t33);
            tv.setText(tt[9] + "");

            tv = (TextView) findViewById(R.id.t34);
            tv.setText(ctime[10]);

            iv = (ImageView) findViewById(R.id.t35);
            iv.setImageResource(cicon[10]);

            tv = (TextView) findViewById(R.id.t36);
            tv.setText(tt[10] + "");

            tv = (TextView) findViewById(R.id.t37);
            tv.setText(ctime[11]);

            iv = (ImageView) findViewById(R.id.t38);
            iv.setImageResource(cicon[11]);

            tv = (TextView) findViewById(R.id.t39);
            tv.setText(tt[11] + "");

            tv = (TextView) findViewById(R.id.t40);
            tv.setText(ctime[12]);

            iv = (ImageView) findViewById(R.id.t41);
            iv.setImageResource(cicon[12]);

            tv = (TextView) findViewById(R.id.t42);
            tv.setText(tt[12] + "");

            tv = (TextView) findViewById(R.id.t43);
            tv.setText(ctime[13]);

            iv = (ImageView) findViewById(R.id.t44);
            iv.setImageResource(cicon[13]);

            tv = (TextView) findViewById(R.id.t45);
            tv.setText(tt[13] + "");

            tv = (TextView) findViewById(R.id.t46);
            tv.setText(ctime[14]);

            iv = (ImageView) findViewById(R.id.t47);
            iv.setImageResource(cicon[14]);

            tv = (TextView) findViewById(R.id.t48);
            tv.setText(tt[14] + "");

            tv = (TextView) findViewById(R.id.t49);
            tv.setText(ctime[15]);

            iv = (ImageView) findViewById(R.id.t50);
            iv.setImageResource(cicon[15]);

            tv = (TextView) findViewById(R.id.t51);
            tv.setText(tt[15] + "");

            tv = (TextView) findViewById(R.id.t52);
            tv.setText(ctime[16]);

            iv = (ImageView) findViewById(R.id.t53);
            iv.setImageResource(cicon[16]);

            tv = (TextView) findViewById(R.id.t54);
            tv.setText(tt[16] + "");

            tv = (TextView) findViewById(R.id.t55);
            tv.setText(ctime[17]);

            iv = (ImageView) findViewById(R.id.t56);
            iv.setImageResource(cicon[17]);

            tv = (TextView) findViewById(R.id.t57);
            tv.setText(tt[17] + "");

            tv = (TextView) findViewById(R.id.t58);
            tv.setText(ctime[18]);

            iv = (ImageView) findViewById(R.id.t59);
            iv.setImageResource(cicon[18]);

            tv = (TextView) findViewById(R.id.t60);
            tv.setText(tt[18] + "");

            tv = (TextView) findViewById(R.id.t61);
            tv.setText(ctime[19]);

            iv = (ImageView) findViewById(R.id.t62);
            iv.setImageResource(cicon[19]);

            tv = (TextView) findViewById(R.id.t63);
            tv.setText(tt[19] + "");

            tv = (TextView) findViewById(R.id.t64);
            tv.setText(ctime[20]);

            iv = (ImageView) findViewById(R.id.t65);
            iv.setImageResource(cicon[20]);

            tv = (TextView) findViewById(R.id.t66);
            tv.setText(tt[20] + "");

            tv = (TextView) findViewById(R.id.t67);
            tv.setText(ctime[21]);

            iv = (ImageView) findViewById(R.id.t68);
            iv.setImageResource(cicon[21]);

            tv = (TextView) findViewById(R.id.t69);
            tv.setText(tt[21] + "");

            tv = (TextView) findViewById(R.id.t70);
            tv.setText(ctime[22]);

            iv = (ImageView) findViewById(R.id.t71);
            iv.setImageResource(cicon[22]);

            tv = (TextView) findViewById(R.id.t72);
            tv.setText(tt[22] + "");

            tv = (TextView) findViewById(R.id.t73);
            tv.setText(ctime[23]);

            iv = (ImageView) findViewById(R.id.t74);
            iv.setImageResource(cicon[23]);

            tv = (TextView) findViewById(R.id.t75);
            tv.setText(tt[23] + "");





            tv = (TextView) findViewById(R.id.ts1);
            tv.setText(ctime[24]);

            iv = (ImageView) findViewById(R.id.ts2);
            iv.setImageResource(cicon[24]);

            tv = (TextView) findViewById(R.id.ts3);
            tv.setText(tt[24] + "");

            tv = (TextView) findViewById(R.id.ts4);
            tv.setText(ctime[25]);

            iv = (ImageView) findViewById(R.id.ts5);
            iv.setImageResource(cicon[25]);

            tv = (TextView) findViewById(R.id.ts6);
            tv.setText(tt[25] + "");

            tv = (TextView) findViewById(R.id.ts7);
            tv.setText(ctime[26]);

            iv = (ImageView) findViewById(R.id.ts8);
            iv.setImageResource(cicon[26]);

            tv = (TextView) findViewById(R.id.ts9);
            tv.setText(tt[26] + "");

            tv = (TextView) findViewById(R.id.ts10);
            tv.setText(ctime[27]);

            iv = (ImageView) findViewById(R.id.ts11);
            iv.setImageResource(cicon[27]);

            tv = (TextView) findViewById(R.id.ts12);
            tv.setText(tt[27] + "");

            tv = (TextView) findViewById(R.id.ts13);
            tv.setText(ctime[28]);

            iv = (ImageView) findViewById(R.id.ts14);
            iv.setImageResource(cicon[28]);

            tv = (TextView) findViewById(R.id.ts15);
            tv.setText(tt[28] + "");

            tv = (TextView) findViewById(R.id.ts16);
            tv.setText(ctime[29]);

            iv = (ImageView) findViewById(R.id.ts17);
            iv.setImageResource(cicon[29]);

            tv = (TextView) findViewById(R.id.ts18);
            tv.setText(tt[29] + "");

            tv = (TextView) findViewById(R.id.ts19);
            tv.setText(ctime[30]);

            iv = (ImageView) findViewById(R.id.ts20);
            iv.setImageResource(cicon[30]);

            tv = (TextView) findViewById(R.id.ts21);
            tv.setText(tt[30] + "");

            tv = (TextView) findViewById(R.id.ts22);
            tv.setText(ctime[31]);

            iv = (ImageView) findViewById(R.id.ts23);
            iv.setImageResource(cicon[31]);

            tv = (TextView) findViewById(R.id.ts24);
            tv.setText(tt[31] + "");

            tv = (TextView) findViewById(R.id.ts25);
            tv.setText(ctime[32]);

            iv = (ImageView) findViewById(R.id.ts26);
            iv.setImageResource(cicon[32]);

            tv = (TextView) findViewById(R.id.ts27);
            tv.setText(tt[32] + "");

            tv = (TextView) findViewById(R.id.ts28);
            tv.setText(ctime[33]);

            iv = (ImageView) findViewById(R.id.ts29);
            iv.setImageResource(cicon[33]);

            tv = (TextView) findViewById(R.id.ts30);
            tv.setText(tt[33] + "");

            tv = (TextView) findViewById(R.id.ts31);
            tv.setText(ctime[34]);

            iv = (ImageView) findViewById(R.id.ts32);
            iv.setImageResource(cicon[34]);

            tv = (TextView) findViewById(R.id.ts33);
            tv.setText(tt[34] + "");

            tv = (TextView) findViewById(R.id.ts34);
            tv.setText(ctime[35]);

            iv = (ImageView) findViewById(R.id.ts35);
            iv.setImageResource(cicon[35]);

            tv = (TextView) findViewById(R.id.ts36);
            tv.setText(tt[35] + "");

            tv = (TextView) findViewById(R.id.ts37);
            tv.setText(ctime[36]);

            iv = (ImageView) findViewById(R.id.ts38);
            iv.setImageResource(cicon[36]);

            tv = (TextView) findViewById(R.id.ts39);
            tv.setText(tt[36] + "");

            tv = (TextView) findViewById(R.id.ts40);
            tv.setText(ctime[37]);

            iv = (ImageView) findViewById(R.id.ts41);
            iv.setImageResource(cicon[37]);

            tv = (TextView) findViewById(R.id.ts42);
            tv.setText(tt[37] + "");

            tv = (TextView) findViewById(R.id.ts43);
            tv.setText(ctime[38]);

            iv = (ImageView) findViewById(R.id.ts44);
            iv.setImageResource(cicon[38]);

            tv = (TextView) findViewById(R.id.ts45);
            tv.setText(tt[38] + "");

            tv = (TextView) findViewById(R.id.ts46);
            tv.setText(ctime[39]);

            iv = (ImageView) findViewById(R.id.ts47);
            iv.setImageResource(cicon[39]);

            tv = (TextView) findViewById(R.id.ts48);
            tv.setText(tt[39] + "");

            tv = (TextView) findViewById(R.id.ts49);
            tv.setText(ctime[40]);

            iv = (ImageView) findViewById(R.id.ts50);
            iv.setImageResource(cicon[40]);

            tv = (TextView) findViewById(R.id.ts51);
            tv.setText(tt[40] + "");

            tv = (TextView) findViewById(R.id.ts52);
            tv.setText(ctime[41]);

            iv = (ImageView) findViewById(R.id.ts53);
            iv.setImageResource(cicon[41]);

            tv = (TextView) findViewById(R.id.ts54);
            tv.setText(tt[41] + "");

            tv = (TextView) findViewById(R.id.ts55);
            tv.setText(ctime[42]);

            iv = (ImageView) findViewById(R.id.ts56);
            iv.setImageResource(cicon[42]);

            tv = (TextView) findViewById(R.id.ts57);
            tv.setText(tt[42] + "");

            tv = (TextView) findViewById(R.id.ts58);
            tv.setText(ctime[43]);

            iv = (ImageView) findViewById(R.id.ts59);
            iv.setImageResource(cicon[43]);

            tv = (TextView) findViewById(R.id.ts60);
            tv.setText(tt[43] + "");

            tv = (TextView) findViewById(R.id.ts61);
            tv.setText(ctime[44]);

            iv = (ImageView) findViewById(R.id.ts62);
            iv.setImageResource(cicon[44]);

            tv = (TextView) findViewById(R.id.ts63);
            tv.setText(tt[44] + "");

            tv = (TextView) findViewById(R.id.ts64);
            tv.setText(ctime[45]);

            iv = (ImageView) findViewById(R.id.ts65);
            iv.setImageResource(cicon[45]);

            tv = (TextView) findViewById(R.id.ts66);
            tv.setText(tt[45] + "");

            tv = (TextView) findViewById(R.id.ts67);
            tv.setText(ctime[46]);

            iv = (ImageView) findViewById(R.id.ts68);
            iv.setImageResource(cicon[46]);

            tv = (TextView) findViewById(R.id.ts69);
            tv.setText(tt[46] + "");

            tv = (TextView) findViewById(R.id.ts70);
            tv.setText(ctime[47]);

            iv = (ImageView) findViewById(R.id.ts71);
            iv.setImageResource(cicon[47]);

            tv = (TextView) findViewById(R.id.ts72);
            tv.setText(tt[47] + "");



            TableRow tablerow;
            tablerow = (TableRow) findViewById(R.id.tr26);
            tablerow.setBackgroundResource(R.color.sysOrange);
            tablerow = (TableRow) findViewById(R.id.tr27);
            tablerow.setBackgroundResource(R.color.sysBlue);
            tablerow = (TableRow) findViewById(R.id.tr28);
            tablerow.setBackgroundResource(R.color.sysMagenta);
            tablerow = (TableRow) findViewById(R.id.tr29);
            tablerow.setBackgroundResource(R.color.sysGreen);
            tablerow = (TableRow) findViewById(R.id.tr30);
            tablerow.setBackgroundResource(R.color.sysRed);
            tablerow = (TableRow) findViewById(R.id.tr31);
            tablerow.setBackgroundResource(R.color.sysYellow);
            tablerow = (TableRow) findViewById(R.id.tr32);
            tablerow.setBackgroundResource(R.color.sysPurple);

            tv = (TextView) findViewById(R.id.tt1);
            tv.setText(timesec[1]);


            tv = (TextView) findViewById(R.id.tt2);
            if(deg.equals("Fahrenheit"))
                tv.setText("Min: " + mintemp[1] + (char) 0x00B0 + "F" + " | Max: " + maxtemp[1] + (char) 0x00B0 +"F");
            else
                tv.setText("Min: " + mintemp[1] + (char) 0x00B0 + "C" + " | Max: " + maxtemp[1] + (char) 0x00B0 +"C");

            iv = (ImageView) findViewById(R.id.iiv1);
            iv.setImageResource(iconsec[1]);

            tv = (TextView) findViewById(R.id.tt3);
            tv.setText(timesec[2]);

            tv = (TextView) findViewById(R.id.tt4);
            if(deg.equals("Fahrenheit"))
                tv.setText("Min: " + mintemp[2] + (char) 0x00B0 + "F" + " | Max: " + maxtemp[2] + (char) 0x00B0 +"F");
            else
                tv.setText("Min: " + mintemp[2] + (char) 0x00B0 + "C" + " | Max: " + maxtemp[2] + (char) 0x00B0 +"C");

            iv = (ImageView) findViewById(R.id.iiv2);
            iv.setImageResource(iconsec[2]);

            tv = (TextView) findViewById(R.id.tt5);
            tv.setText(timesec[3]);

            tv = (TextView) findViewById(R.id.tt6);
            if(deg.equals("Fahrenheit"))
                tv.setText("Min: " + mintemp[3] + (char) 0x00B0 + "F" + " | Max: " + maxtemp[3] + (char) 0x00B0 +"F");
            else
                tv.setText("Min: " + mintemp[3] + (char) 0x00B0 + "C" + " | Max: " + maxtemp[3] + (char) 0x00B0 +"C");

            iv = (ImageView) findViewById(R.id.iiv3);
            iv.setImageResource(iconsec[3]);

            tv = (TextView) findViewById(R.id.tt7);
            tv.setText(timesec[4]);

            tv = (TextView) findViewById(R.id.tt8);
            if(deg.equals("Fahrenheit"))
                tv.setText("Min: " + mintemp[4] + (char) 0x00B0 + "F" + " | Max: " + maxtemp[4] + (char) 0x00B0 +"F");
            else
                tv.setText("Min: " + mintemp[4] + (char) 0x00B0 + "C" + " | Max: " + maxtemp[4] + (char) 0x00B0 +"C");

            iv = (ImageView) findViewById(R.id.iiv4);
            iv.setImageResource(iconsec[4]);

            tv = (TextView) findViewById(R.id.tt9);
            tv.setText(timesec[5]);

            tv = (TextView) findViewById(R.id.tt10);
            if(deg.equals("Fahrenheit"))
                tv.setText("Min: " + mintemp[5] + (char) 0x00B0 + "F" + " | Max: " + maxtemp[5] + (char) 0x00B0 +"F");
            else
                tv.setText("Min: " + mintemp[5] + (char) 0x00B0 + "C" + " | Max: " + maxtemp[5] + (char) 0x00B0 +"C");

            iv = (ImageView) findViewById(R.id.iiv5);
            iv.setImageResource(iconsec[5]);

            tv = (TextView) findViewById(R.id.tt11);
            tv.setText(timesec[6]);

            tv = (TextView) findViewById(R.id.tt12);
            if(deg.equals("Fahrenheit"))
                tv.setText("Min: " + mintemp[6] + (char) 0x00B0 + "F" + " | Max: " + maxtemp[6] + (char) 0x00B0 +"F");
            else
                tv.setText("Min: " + mintemp[6] + (char) 0x00B0 + "C" + " | Max: " + maxtemp[6] + (char) 0x00B0 +"C");

            iv = (ImageView) findViewById(R.id.iiv6);
            iv.setImageResource(iconsec[6]);

            tv = (TextView) findViewById(R.id.tt13);
            tv.setText(timesec[7]);

            tv = (TextView) findViewById(R.id.tt14);
            if(deg.equals("Fahrenheit"))
                tv.setText("Min: " + mintemp[7] + (char) 0x00B0 + "F" + " | Max: " + maxtemp[7] + (char) 0x00B0 +"F");
            else
                tv.setText("Min: " + mintemp[7] + (char) 0x00B0 + "C" + " | Max: " + maxtemp[7] + (char) 0x00B0 +"C");

            iv = (ImageView) findViewById(R.id.iiv7);
            iv.setImageResource(iconsec[7]);

            addListenerOnButton8();
            addListenerOnButton9();
            addListenerOnButton10();
        }
        catch (JSONException e){

        }

        }

    private void addListenerOnButton8() {
        but1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                View tl2 = (TableLayout) findViewById(R.id.tablelayout2);
                tl2.setVisibility(View.VISIBLE);
                View tl3 = (TableLayout) findViewById(R.id.tablelayout3);
                tl3.setVisibility(View.GONE);
                but1.setSelected(true);
                but2.setSelected(false);
            }
        });
    }

    private void addListenerOnButton9() {
        but2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                View tl2 = (TableLayout) findViewById(R.id.tablelayout2);
                tl2.setVisibility(View.GONE);
                View tl3 = (TableLayout) findViewById(R.id.tablelayout3);
                tl3.setVisibility(View.VISIBLE);
                but1.setSelected(false);
                but2.setSelected(true);
            }
        });
    }

    private void addListenerOnButton10() {
        but3 = (Button) findViewById(R.id.button10);
        but3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                View but51 = (Button) findViewById(R.id.button10);
                but51.setVisibility(View.GONE);
                View va = (TextView) findViewById(R.id.tss1);
                va.setVisibility(View.GONE);
                View vb = (TextView) findViewById(R.id.tss2);
                vb.setVisibility(View.GONE);

                View tr52 = (TableRow) findViewById(R.id.tr52);
                tr52.setVisibility(View.VISIBLE);
                View tr53 = (TableRow) findViewById(R.id.tr53);
                tr53.setVisibility(View.VISIBLE);
                View tr54 = (TableRow) findViewById(R.id.tr54);
                tr54.setVisibility(View.VISIBLE);
                View tr55 = (TableRow) findViewById(R.id.tr55);
                tr55.setVisibility(View.VISIBLE);

                View tr56 = (TableRow) findViewById(R.id.tr56);
                tr56.setVisibility(View.VISIBLE);
                View tr57 = (TableRow) findViewById(R.id.tr57);
                tr57.setVisibility(View.VISIBLE);
                View tr58 = (TableRow) findViewById(R.id.tr58);
                tr58.setVisibility(View.VISIBLE);
                View tr59 = (TableRow) findViewById(R.id.tr59);
                tr59.setVisibility(View.VISIBLE);

                View tr60 = (TableRow) findViewById(R.id.tr60);
                tr60.setVisibility(View.VISIBLE);
                View tr61 = (TableRow) findViewById(R.id.tr61);
                tr61.setVisibility(View.VISIBLE);
                View tr62 = (TableRow) findViewById(R.id.tr62);
                tr62.setVisibility(View.VISIBLE);
                View tr63 = (TableRow) findViewById(R.id.tr63);
                tr63.setVisibility(View.VISIBLE);

                View tr64 = (TableRow) findViewById(R.id.tr64);
                tr64.setVisibility(View.VISIBLE);
                View tr65 = (TableRow) findViewById(R.id.tr65);
                tr65.setVisibility(View.VISIBLE);
                View tr66 = (TableRow) findViewById(R.id.tr66);
                tr66.setVisibility(View.VISIBLE);
                View tr67 = (TableRow) findViewById(R.id.tr67);
                tr67.setVisibility(View.VISIBLE);

                View tr68 = (TableRow) findViewById(R.id.tr68);
                tr68.setVisibility(View.VISIBLE);
                View tr69 = (TableRow) findViewById(R.id.tr69);
                tr69.setVisibility(View.VISIBLE);
                View tr70 = (TableRow) findViewById(R.id.tr70);
                tr70.setVisibility(View.VISIBLE);
                View tr71 = (TableRow) findViewById(R.id.tr71);
                tr71.setVisibility(View.VISIBLE);

                View tr72 = (TableRow) findViewById(R.id.tr72);
                tr72.setVisibility(View.VISIBLE);
                View tr73 = (TableRow) findViewById(R.id.tr73);
                tr73.setVisibility(View.VISIBLE);
                View tr74 = (TableRow) findViewById(R.id.tr74);
                tr74.setVisibility(View.VISIBLE);
                View tr75 = (TableRow) findViewById(R.id.tr75);
                tr75.setVisibility(View.VISIBLE);
            }
        });
    }
}
