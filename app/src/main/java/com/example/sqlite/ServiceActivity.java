package com.example.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceActivity extends AppCompatActivity {

    DbHandler dbHandler = new DbHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Toolbar toolbar = findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        ArrayList<HashMap<String, String>> serviceList = dbHandler.getServices();
        ListView listView = findViewById(R.id.dbList);
        ListAdapter adapter = new SimpleAdapter(ServiceActivity.this, serviceList, R.layout.database_list_service,
                new String[]{"serviceid", "servicedescription", "servcatname", "serviceprice", "servicelength"},
                new int[]{R.id.txtServiceID, R.id.txtServiceDescription, R.id.txtServiceCategory, R.id.txtServicePrice,
                          R.id.txtServiceLength});
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_customer:
                Intent customer = new Intent(this, CustomerActivity.class);
                startActivity(customer);
                break;

            case R.id.action_therapist:
                Intent therapist = new Intent(this, TherapistActivity.class);
                startActivity(therapist);
                break;

            case R.id.action_timetable:
                Intent timetable = new Intent(this, TimetableActivity.class);
                startActivity(timetable);
                break;

            case R.id.action_serv_cat:
                Intent serviceCategory = new Intent(this, ServiceCategoryActivity.class);
                startActivity(serviceCategory);
                break;

            case R.id.action_service:
                Intent service = new Intent(this, ServiceActivity.class);
                startActivity(service);
                break;

            case R.id.action_appointment:
                Intent appointment = new Intent(this, AppointmentActivity.class);
                startActivity(appointment);
                break;

            case R.id.action_invoice:
                Intent invoice = new Intent(this, InvoiceActivity.class);
                startActivity(invoice);
                break;

            case R.id.action_query1:
                Intent query = new Intent(this, Query1Activity.class);
                startActivity(query);
                break;

            case R.id.action_query2:
                Intent query2 = new Intent(this, Query2Activity.class);
                startActivity(query2);
                break;

            case R.id.action_query3:
                Intent query3 = new Intent(this, Query3Activity.class);
                startActivity(query3);
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
