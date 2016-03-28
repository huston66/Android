package com.example.zhenli.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Main2Activity extends AppCompatActivity {
    private Button but4 = null;
    private Button but5 = null;
    private Button but6 = null;

    String streetaddress;
    String cityaddress;
    String selectstate;
    String deg;
    String weatherdes;
    String imguri;

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    JSONObject obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        try{
            FacebookSdk.sdkInitialize(getApplicationContext());
            callbackManager = CallbackManager.Factory.create();
            shareDialog = new ShareDialog(this);

            shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                        @Override
                        public void onSuccess(Sharer.Result result) {
                            Toast.makeText(getApplicationContext(), "Facebook Post Successful", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(getApplicationContext(), "Posted Cancelled", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(FacebookException e) {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                        }

                    }
            );

            obj = new JSONObject(getIntent().getStringExtra("updateui"));
            addListenerOnButton5();
            addListenerOnButton6();
            addListenerOnButton7();

            streetaddress = getIntent().getStringExtra("sa");
            cityaddress = getIntent().getStringExtra("ct");
            selectstate = getIntent().getStringExtra("st");
            deg = getIntent().getStringExtra("deg");

            JSONObject curr = obj.getJSONObject("currently");
            String timezone = obj.getString("timezone");

            double temperature = curr.getDouble("temperature");
            double dewPoint = curr.getDouble("dewPoint");
            double humidity = curr.getDouble("humidity");
            double windSpeed = curr.getDouble("windSpeed");
            double visibility;
            if(curr.isNull( "visibility" ) == false)
                visibility = curr.getDouble("visibility");
            else
                visibility = -1;
            double precipIntensity = curr.getDouble("precipIntensity");
            double precipProbability = curr.getDouble("precipProbability");
            String summary = curr.getString("summary");
            String icon = curr.getString("icon");

            weatherdes = summary+", "+Math.round(temperature);
            if(deg.equals("Fahrenheit"))
                weatherdes += (char) 0x00B0 + "F";
            else
                weatherdes += (char) 0x00B0 + "C";

            System.out.println(icon);

            JSONObject daily = obj.getJSONObject("daily");
            JSONArray dailydate = daily.getJSONArray("data");
            double tempmin = dailydate.getJSONObject(0).getDouble("temperatureMin");
            double tempmax = dailydate.getJSONObject(0).getDouble("temperatureMax");
            int sunrise = dailydate.getJSONObject(0).getInt("sunriseTime");
            int sunset = dailydate.getJSONObject(0).getInt("sunsetTime");
            Date sunrisetime=new Date((long)sunrise*1000);
            Date sunsettime=new Date((long)sunset*1000);

            SimpleDateFormat format = new SimpleDateFormat("h:mm a");
            format.setTimeZone(TimeZone.getTimeZone(timezone));
            String srise = format.format(sunrisetime);
            String sset = format.format(sunsettime);

            TextView tv;
            tv = (TextView) findViewById(R.id.text2);
            ImageView iv;
            iv = (ImageView) findViewById(R.id.imageView1);

            if(icon.equals("clear-day"))
                iv.setImageResource(R.drawable.clear);
            else if(icon.equals("clear-night"))
                iv.setImageResource(R.drawable.clearnight);
            else if(icon.equals("rain"))
                iv.setImageResource(R.drawable.rain);
            else if(icon.equals("snow"))
                iv.setImageResource(R.drawable.snow);
            else if(icon.equals("sleet"))
                iv.setImageResource(R.drawable.sleet);
            else if(icon.equals("wind"))
                iv.setImageResource(R.drawable.wind);
            else if(icon.equals("fog"))
                iv.setImageResource(R.drawable.fog);
            else if(icon.equals("cloudy"))
                iv.setImageResource(R.drawable.cloudy);
            else if(icon.equals("partly-cloudy-day"))
                iv.setImageResource(R.drawable.cloudday);
            else if(icon.equals("partly-cloudy-night"))
                iv.setImageResource(R.drawable.cloudnight);

            if(icon.equals("clear-day"))
                imguri = "http://cs-server.usc.edu:23069/clear.png";
            else if(icon.equals("clear-night"))
                imguri = "http://cs-server.usc.edu:23069/clearnight.png";
            else if(icon.equals("rain"))
                imguri = "http://cs-server.usc.edu:23069/rain.png";
            else if(icon.equals("snow"))
                imguri = "http://cs-server.usc.edu:23069/snow.png";
            else if(icon.equals("sleet"))
                imguri = "http://cs-server.usc.edu:23069/sleet.png";
            else if(icon.equals("wind"))
                imguri = "http://cs-server.usc.edu:23069/wind.png";
            else if(icon.equals("fog"))
                imguri = "http://cs-server.usc.edu:23069/fog.png";
            else if(icon.equals("cloudy"))
                imguri = "http://cs-server.usc.edu:23069/cloudy.png";
            else if(icon.equals("partly-cloudy-day"))
                imguri = "http://cs-server.usc.edu:23069/cloudday.png";
            else if(icon.equals("partly-cloudy-night"))
                imguri = "http://cs-server.usc.edu:23069/cloudnight.png";

            tv = (TextView) findViewById(R.id.mytext7);
            tv.setText(summary + " in " + cityaddress + ", " + selectstate);

            tv = (TextView) findViewById(R.id.mytext8);
            tv.setText(Math.round(temperature) + "");

            tv = (TextView) findViewById(R.id.mytext9);
            if(deg.equals("Fahrenheit"))
                tv.setText((char) 0x00B0 + "F");
            else
                tv.setText((char) 0x00B0 + "C");

            tv = (TextView) findViewById(R.id.mytext10);
            tv.setText("L:" + Math.round(tempmin) + (char) 0x00B0 + " | " + "H:" + Math.round(tempmax) + (char) 0x00B0);

            tv = (TextView) findViewById(R.id.text2);
            String precimap="";
            if(deg.equals("Fahrenheit"))
            {
                precipIntensity=precipIntensity*0.039370;
            }

            if(precipIntensity>=0&&precipIntensity<0.002)
            {
                precimap="None";
            }
            else if(precipIntensity>=0.002&&precipIntensity<0.017)
            {
                precimap="Very Light";
            }
            else if(precipIntensity>=0.017&&precipIntensity<0.1)
            {
                precimap="Light";
            }
            else if(precipIntensity>=0.1&&precipIntensity<0.4)
            {
                precimap="Moderate";
            }
            else if(precipIntensity>=0.4)
            {
                precimap="Heavy";
            }

            tv.setText(precimap);
            tv = (TextView) findViewById(R.id.text4);
            tv.setText(Math.round(precipProbability*100)+" %");

            tv = (TextView) findViewById(R.id.text6);
            if(deg.equals("Fahrenheit"))
                tv.setText(windSpeed+" mph");
            else
                tv.setText(windSpeed+" m/s");

            tv = (TextView) findViewById(R.id.text8);
            if(deg.equals("Fahrenheit"))
                tv.setText(Math.round(dewPoint) + "" + (char) 0x00B0 + "F");
            else
                tv.setText(Math.round(dewPoint) + "" + (char) 0x00B0 + "C");

            tv = (TextView) findViewById(R.id.text10);
            tv.setText(Math.round(humidity*100)+" %");

            tv = (TextView) findViewById(R.id.text12);

            DecimalFormat df2 = new DecimalFormat("#0.00");
            if(visibility != -1) {
                if (deg.equals("Fahrenheit"))
                    tv.setText(df2.format(visibility) + " mi");
                else
                    tv.setText(df2.format(visibility) + " km");
            }
            else
            {
                tv.setText("N/A");
            }

            tv = (TextView) findViewById(R.id.text14);
            tv.setText(srise+"");

            tv = (TextView) findViewById(R.id.text16);
            tv.setText(sset+"");

        }catch(JSONException e){

        }
    }

    private void addListenerOnButton5() {
        but4 = (Button) findViewById(R.id.button5);
        but4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra("updateui", obj.toString());
                intent.putExtra("sa",streetaddress);
                intent.putExtra("st",selectstate);
                intent.putExtra("ct",cityaddress);
                intent.putExtra("deg",deg);
                startActivity(intent);
            }
        });
    }

    private void addListenerOnButton6() {
        but5 = (Button) findViewById(R.id.button6);
        but5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Main22Activity.class);
                intent.putExtra("updateui", obj.toString());
                startActivity(intent);
            }
        });
    }

    private void addListenerOnButton7() {
        but6 = (Button) findViewById(R.id.button7);
        but6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // this part is optional

                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle("Current Weather in " + cityaddress + ", " + selectstate)
                        .setContentDescription(
                                weatherdes)
                        .setContentUrl(Uri.parse("forecast.io"))
                        .setImageUrl(Uri.parse(imguri))
                        .build();

                shareDialog.show(linkContent);
            }
        });
    }

    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void handler(){

    }
}