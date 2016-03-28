package com.example.zhenli.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button but1 = null;
    private Button but3 = null;
    private Button but5 = null;
    private Button but7 = null;
    private EditText sa;
    private EditText ct;
    private RadioGroup rg;
    private RadioButton rb;
    String streetaddress;
    String cityaddress;
    String selectstate;
    String selectdegree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton1();
        addListenerOnButton3();
        addListenerOnButton5();
        addListenerOnButton7();
    }

    private void addListenerOnButton1() {
        final Context context = this;
        but1 = (Button) findViewById(R.id.button1);
        but1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sa = (EditText) findViewById(R.id.editText2);
                ct = (EditText) findViewById(R.id.editText3);
                rg = (RadioGroup) findViewById(R.id.degree);
                int selectedId = rg.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(selectedId);

                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                int spinner_pos = spinner.getSelectedItemPosition();
                String[] state_values = getResources().getStringArray(R.array.state_values);
                String c = state_values[spinner_pos];

                String a = sa.getText().toString();
                String b = ct.getText().toString();
                String d = rb.getText().toString();

                String aa = a.trim();
                String bb = b.trim();
                String cc = c.trim();

                if (aa.equals("")) {
                    TextView tv = (TextView) findViewById(R.id.displaytext1);
                    tv.setText("Please enter a Street.");
                    return;
                }
                else if (bb.equals("")) {
                    TextView tv = (TextView) findViewById(R.id.displaytext1);
                    tv.setText("Please enter a City.");
                    return;
                }
                else if (cc.equals("EMPTY")) {
                    TextView tv = (TextView) findViewById(R.id.displaytext1);
                    tv.setText("Please select a State.");
                    return;
                }

                new onbuttonclickHttpPost().execute(a, b, c, d);

            }
        });
    }

        private class onbuttonclickHttpPost extends AsyncTask<String, Void, JSONObject> {
            protected JSONObject doInBackground(String... params) {
                System.out.println("*** doInBackground");
                HttpClient httpclient = new DefaultHttpClient();
                //HttpPost httppost  = new HttpPost("http://cs-server.usc.edu:23069/hw9.php");
                HttpPost httppost  = new HttpPost("http://zli364-env.elasticbeanstalk.com");

                try{
                    streetaddress = params[0];
                    cityaddress = params[1];
                    selectstate = params[2];
                    selectdegree = params[3];

                    System.out.println("*** doInBackground1");

                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
                    nameValuePairs.add(new BasicNameValuePair("sa",streetaddress));
                    nameValuePairs.add(new BasicNameValuePair("ct", cityaddress));
                    nameValuePairs.add(new BasicNameValuePair("st", selectstate));
                    nameValuePairs.add(new BasicNameValuePair("degree", selectdegree));

                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    System.out.println("*** doInBackground2");
                    HttpResponse response = httpclient.execute(httppost);
                    System.out.println("*** doInBackground3");
                    String result = EntityUtils.toString(response.getEntity());
                    return new JSONObject(result);
                }
                catch(Exception e){
                    return new JSONObject();
                }
            }

            protected void onPostExecute(JSONObject result){
                    System.out.println("*** doInBackground4");
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    System.out.println("*** doInBackground5");
                    intent.putExtra("updateui", result.toString());
                    intent.putExtra("deg",selectdegree);
                    intent.putExtra("sa",streetaddress);
                    intent.putExtra("st",selectstate);
                    intent.putExtra("ct",cityaddress);
                startActivity(intent);
            }
        }

    private void addListenerOnButton3() {
        but3 = (Button) findViewById(R.id.button3);
        but3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addListenerOnButton5() {
        but5 = (Button) findViewById(R.id.button4);
        but5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("http://forecast.io");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private void addListenerOnButton7() {
        but7 = (Button) findViewById(R.id.button2);
        but7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.displaytext1);
                tv.setText("");

                sa = (EditText) findViewById(R.id.editText2);
                ct = (EditText) findViewById(R.id.editText3);
                rb = (RadioButton) findViewById(R.id.radioButton1);
                Spinner spinner = (Spinner) findViewById(R.id.spinner);

                sa.setText("");
                ct.setText("");
                rb.setChecked(true);
                spinner.setSelection(0);

                sa.setFocusable(true);
                sa.setFocusableInTouchMode(true);

                sa.requestFocus();
                sa.requestFocusFromTouch();
            }
        });
    }
}