package com.example.sqlite;

//import libraries we will be using
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

//Customer Activity
public class CustomerActivity extends AppCompatActivity {

    //Create a reference to our DbHandler, allows us to access our database methods
    DbHandler dbHandler = new DbHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        //Put our action bar into this activity
        Toolbar toolbar = findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        //Using our database reference to call our method to populate our database with data
        //Note, that when creating the other activities, do not include this in them
        dbHandler.checkToPopulateTables();

        //Retrieve the customers' information from the database and store it in customerList
        ArrayList<HashMap<String, String>> customerList = dbHandler.getCustomers();

        //Get the list we will display the information in. It is the ListView item from activity_customer.xml
        ListView listView = findViewById(R.id.dbList);

        //Set what is shown in the list and how it is displayed
        //We are showing customerList with the display set by database_list_customer
        ListAdapter adapter = new SimpleAdapter(CustomerActivity.this, customerList, R.layout.database_list_customer,
        //Note that "customerid", "custfirstname" etc are the same column names we are returning from getCustomers method
                new String[]{"customerid", "custfirstname", "custlastname", "custgender", "custdob", "custphonenumber"},
                new int[]{R.id.txtCustomerID, R.id.txtCustomerFirstName, R.id.txtCustomerLastName, R.id.txtCustomerGender,
                          R.id.txtCustomerDOB, R.id.txtCustomerPhoneNo});
        //Put it into our list
        listView.setAdapter(adapter);
    }

    //Create the menu in the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //Use action_bar_menu.xml
        inflater.inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Give menu items functionality when selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //When the customer item is clicked go from current activity to CustomerActivity
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

