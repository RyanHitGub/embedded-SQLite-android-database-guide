package com.example.sqlite.Activities;

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

import com.example.sqlite.Database.DbHandler;
import com.example.sqlite.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Query1Activity extends AppCompatActivity {

    DbHandler dbHandler = new DbHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        Toolbar toolbar = findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        ArrayList<HashMap<String, String>> customerList = dbHandler.query1(1, "27-05-2019");
        ListView listView = findViewById(R.id.listQuery1);
        ListAdapter adapter = new SimpleAdapter(Query1Activity.this, customerList, R.layout.database_list_query1,
                new String[]{"therapistfirstname", "therapistlastname", "appointmentid", "appointmentdate",
                        "custfirstname", "custlastname"},
                new int[]{R.id.txtTherapistFirstName, R.id.txtTherapistLastName, R.id.txtAppointmentID, R.id.txtAppointmentDate,
                        R.id.txtCustomerFirstName, R.id.txtCustomerLastName});
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
