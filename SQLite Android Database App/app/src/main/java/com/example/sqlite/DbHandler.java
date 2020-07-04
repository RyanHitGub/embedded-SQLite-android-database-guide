package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {

    //Database name, tables, columns are initialized here to be used throughout the DbHandler class
    private static  final int DB_VERSION = 1;
    private static final String DB_NAME = "sqlitedb";

    //Customer table
    private static final String TABLE_CUSTOMER = "customertable";
    private static final String KEY_CUSTOMER_ID = "customerid";
    private static final String KEY_CUST_FIRST_NAME = "custfirstname";
    private static final String KEY_CUST_LAST_NAME = "custlastname";
    private static final String KEY_CUST_GENDER = "custgender";
    private static final String KEY_CUST_DOB = "custdob";
    private static final String KEY_CUST_PHONE_NO = "custphonenumber";

    //Therapist table
    private static final String TABLE_THERAPIST = "therapisttable";
    private static final String KEY_THERAPIST_ID = "therapistid";
    private static final String KEY_THERAPIST_FIRST_NAME = "therapistfirstname";
    private static final String KEY_THERAPIST_LAST_NAME = "therapistlastname";
    private static final String KEY_THERAPIST_GENDER = "therapistgender";
    private static final String KEY_THERAPIST_DOB = "therapistdob";
    private static final String KEY_THERAPIST_PHONE_NO = "therapistphonenumber";

    //Timetable table
    private static final String TABLE_TIMETABLE = "timetabletable";
    private static final String KEY_TIMETABLE_ID = "timetableid";
    private static final String KEY_TT_PERIOD_BEGIN = "ttperiodbegin";
    private static final String KEY_TT_PERIOD_END = "ttperiodend";
    private static final String KEY_TT_START_TIME = "ttstarttime";
    private static final String KEY_TT_END_TIME = "ttendtime";
    private static final String KEY_TT_THERAPIST_ID = "tttherapistid";

    //Service table
    private static final String TABLE_SERVICE = "servicetable";
    private static final String KEY_SERVICE_ID = "serviceid";
    private static final String KEY_SERVICE_DESCRIPTION = "servicedescription";
    private static final String KEY_SERVICE_CATEGORY_ID = "servicecategoryid";
    private static final String KEY_SERVICE_PRICE = "serviceprice";
    private static final String KEY_SERVICE_LENGTH = "servicelength";

    //Service Category table
    private static final String TABLE_SERVICE_CATEGORY = "servicecategorytable";
    private static final String KEY_SERV_CAT_ID = "servcatid";
    private static final String KEY_SERV_CAT_NAME = "servcatname";
    private static final String KEY_SERV_CAT_DESCRIPTION = "servcatdescription";

    //Appointment table
    private static final String TABLE_APPOINTMENT = "appointmenttable";
    private static final String KEY_APPOINTMENT_ID = "appointmentid";
    private static final String KEY_APPOINTMENT_DATE = "appointmentdate";
    private static final String KEY_APPOINTMENT_TIME = "appointmenttime";
    private static final String KEY_APPOINTMENT_CUSTOMER_ID = "appointmentcustomerid";
    private static final String KEY_APPOINTMENT_THERAPIST_ID = "appointmenttherapistid";
    private static final String KEY_APPOINTMENT_SERVICE_ID = "appointmentserviceid";
    private static final String KEY_APPOINTMENT_NOTES = "appointmentnotes";

    //Invoice table
    private static final String TABLE_INVOICE = "invoicetable";
    private static final String KEY_INVOICE_ID = "invoiceid";
    private static final String KEY_INVOICE_DATE = "invoicedate";
    private static final String KEY_INVOICE_CUSTOMER_ID = "invoicecustomerid";
    private static final String KEY_INVOICE_APPOINTMENT_ID = "invoiceappointmentid";

    //Constructor
    public DbHandler (@Nullable Context context) {super(context, DB_NAME, null, DB_VERSION); }

    //Two initial methods onCreate and onUpgrade are required to create the database
    // Tables are added to the database in onCreate
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create Customer table
        String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + TABLE_CUSTOMER +
                "(" +
                KEY_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_CUST_FIRST_NAME + " TEXT NOT NULL, " +
                KEY_CUST_LAST_NAME + " TEXT NOT NULL, " +
                KEY_CUST_GENDER + " TEXT NOT NULL, " +
                KEY_CUST_DOB + " TEXT NOT NULL, " +
                KEY_CUST_PHONE_NO + " TEXT NOT NULL, " +
                "CHECK (" + KEY_CUST_GENDER + " = 'F' OR " + KEY_CUST_GENDER + " = 'M')" +
                ")";
        //Executes the SQL statement we have written
        db.execSQL(CREATE_CUSTOMER_TABLE);

        //Create Therapist table
        String CREATE_THERAPIST_TABLE = "CREATE TABLE " + TABLE_THERAPIST +
                "(" +
                KEY_THERAPIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_THERAPIST_FIRST_NAME + " TEXT NOT NULL, " +
                KEY_THERAPIST_LAST_NAME + " TEXT NOT NULL, " +
                KEY_THERAPIST_GENDER + " TEXT NOT NULL, " +
                KEY_THERAPIST_DOB + " TEXT NOT NULL, " +
                KEY_THERAPIST_PHONE_NO + " TEXT NOT NULL" +
                ")";
        db.execSQL(CREATE_THERAPIST_TABLE);

        //Create Timetable table
        String CREATE_TIMETABLE_TABLE = "CREATE TABLE " + TABLE_TIMETABLE +
                "(" +
                KEY_TIMETABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_TT_PERIOD_BEGIN + " TEXT NOT NULL, " +
                KEY_TT_PERIOD_END + " TEXT NOT NULL, " +
                KEY_TT_START_TIME + " TEXT NOT NULL, " +
                KEY_TT_END_TIME + " TEXT NOT NULL, " +
                KEY_TT_THERAPIST_ID + " INTEGER, " +
                "FOREIGN KEY (" + KEY_TT_THERAPIST_ID + ") REFERENCES " + TABLE_THERAPIST + " (" + KEY_THERAPIST_ID + ")" +
                ")";
        db.execSQL(CREATE_TIMETABLE_TABLE);

        //Create Service table
        String CREATE_SERVICE_TABLE = "CREATE TABLE " + TABLE_SERVICE +
                "(" +
                KEY_SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_SERVICE_DESCRIPTION + " TEXT NOT NULL UNIQUE, " +
                KEY_SERVICE_CATEGORY_ID + " INTEGER NOT NULL, " +
                KEY_SERVICE_PRICE + " TEXT DEFAULT '45.00', " +
                KEY_SERVICE_LENGTH + " TEXT NOT NULL, " +
                "FOREIGN KEY (" + KEY_SERVICE_CATEGORY_ID + ") REFERENCES " + TABLE_SERVICE_CATEGORY + " (" + KEY_SERV_CAT_ID + ")" +
                ")";
        db.execSQL(CREATE_SERVICE_TABLE);

        //Create Service Category table
        String CREATE_SERVICE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_SERVICE_CATEGORY +
                "(" +
                KEY_SERV_CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_SERV_CAT_NAME + " TEXT NOT NULL UNIQUE, " +
                KEY_SERV_CAT_DESCRIPTION + " TEXT NOT NULL" +
                ")";
        db.execSQL(CREATE_SERVICE_CATEGORY_TABLE);

        //Create Appointment table
        String CREATE_APPOINTMENT_TABLE = "CREATE TABLE " + TABLE_APPOINTMENT +
                "(" +
                KEY_APPOINTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_APPOINTMENT_DATE + " TEXT NOT NULL, " +
                KEY_APPOINTMENT_TIME + " TEXT NOT NULL, " +
                KEY_APPOINTMENT_CUSTOMER_ID + " INTEGER NOT NULL, " +
                KEY_APPOINTMENT_THERAPIST_ID + " INTEGER NOT NULL, " +
                KEY_APPOINTMENT_SERVICE_ID + " INTEGER NOT NULL, " +
                KEY_APPOINTMENT_NOTES + " TEXT, " +
                "FOREIGN KEY (" + KEY_APPOINTMENT_CUSTOMER_ID + ") REFERENCES " + TABLE_CUSTOMER + " (" + KEY_CUSTOMER_ID + "), " +
                "FOREIGN KEY (" + KEY_APPOINTMENT_THERAPIST_ID + ") REFERENCES " + TABLE_THERAPIST + " (" + KEY_THERAPIST_ID + "), " +
                "FOREIGN KEY (" + KEY_APPOINTMENT_SERVICE_ID + ") REFERENCES " + TABLE_SERVICE + " (" + KEY_SERVICE_ID + ")" +
                ")";
        db.execSQL(CREATE_APPOINTMENT_TABLE);

        //Create Invoice table
        String CREATE_INVOICE_TABLE = "CREATE TABLE " + TABLE_INVOICE +
                "(" +
                KEY_INVOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_INVOICE_DATE + " TEXT NOT NULL, " +
                KEY_INVOICE_CUSTOMER_ID + " INTEGER NOT NULL, " +
                KEY_INVOICE_APPOINTMENT_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + KEY_INVOICE_CUSTOMER_ID + ") REFERENCES " + TABLE_CUSTOMER + " (" + KEY_CUSTOMER_ID + "), " +
                "FOREIGN KEY (" + KEY_INVOICE_APPOINTMENT_ID + ") REFERENCES " + TABLE_APPOINTMENT + " (" + KEY_APPOINTMENT_ID + ")" +
                ")";
        db.execSQL(CREATE_INVOICE_TABLE);
    }

    //Older versions of tables are dropped in onUpgrade to be replaced by new versions
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop older tables if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THERAPIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMETABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVOICE);

        //Create the tables again
        onCreate(db);
    }

    //All other methods will go here

    //Insert row to Customer table
    public void insertCustomerRow(String firstName, String lastName, String gender,
                                  String dob, String phoneNumber){

        //Grants access to our database in write mode.
        SQLiteDatabase db = this.getWritableDatabase();

        //Map of values
        ContentValues contentValues = new ContentValues();
        //Values are assigned to their column
        contentValues.put(KEY_CUST_FIRST_NAME, firstName);
        contentValues.put(KEY_CUST_LAST_NAME, lastName);
        contentValues.put(KEY_CUST_GENDER, gender);
        contentValues.put(KEY_CUST_DOB, dob);
        contentValues.put(KEY_CUST_PHONE_NO, phoneNumber);

        //Insert the row into the table
        long temp = db.insert(TABLE_CUSTOMER, null, contentValues);
        //Close the database
        db.close();
    }

    //Insert row to Therapist table
    public void insertTherapistRow(String firstName, String lastName, String gender,
                                   String dob, String phoneNumber){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_THERAPIST_FIRST_NAME, firstName);
        contentValues.put(KEY_THERAPIST_LAST_NAME, lastName);
        contentValues.put(KEY_THERAPIST_GENDER, gender);
        contentValues.put(KEY_THERAPIST_DOB, dob);
        contentValues.put(KEY_THERAPIST_PHONE_NO, phoneNumber);

        long temp = db.insert(TABLE_THERAPIST, null, contentValues);
        db.close();
    }

    //Insert row to Timetable table
    public void insertTimetableRow(String periodBegin, String periodEnd, String startTime,
                                   String endTime, int therapistID){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TT_PERIOD_BEGIN, periodBegin);
        contentValues.put(KEY_TT_PERIOD_END, periodEnd);
        contentValues.put(KEY_TT_START_TIME, startTime);
        contentValues.put(KEY_TT_END_TIME, endTime);
        contentValues.put(KEY_TT_THERAPIST_ID, therapistID);

        long temp = db.insert(TABLE_TIMETABLE, null, contentValues);
        db.close();
    }

    //Insert row to Service table
    public void insertServiceRow(String description, int categoryID, String price,
                                  String length){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE_DESCRIPTION, description);
        contentValues.put(KEY_SERVICE_CATEGORY_ID, categoryID);
        contentValues.put(KEY_SERVICE_PRICE, price);
        contentValues.put(KEY_SERVICE_LENGTH, length);

        long temp = db.insert(TABLE_SERVICE, null, contentValues);
        db.close();
    }

    //Insert row to Service Category table
    public void insertServiceCategoryRow(String name, String description){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERV_CAT_NAME, name);
        contentValues.put(KEY_SERV_CAT_DESCRIPTION, description);

        long temp = db.insert(TABLE_SERVICE_CATEGORY, null, contentValues);
        db.close();
    }

    //Insert row to Appointment table
    public void insertAppointmentRow(String date, String time, int customerID, int therapistID,
                                     int serviceID, String notes){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_APPOINTMENT_DATE, date);
        contentValues.put(KEY_APPOINTMENT_TIME, time);
        contentValues.put(KEY_APPOINTMENT_CUSTOMER_ID, customerID);
        contentValues.put(KEY_APPOINTMENT_THERAPIST_ID, therapistID);
        contentValues.put(KEY_APPOINTMENT_SERVICE_ID, serviceID);
        contentValues.put(KEY_APPOINTMENT_NOTES, notes);

        long temp = db.insert(TABLE_APPOINTMENT, null, contentValues);
        db.close();
    }

    //Insert row to Invoice table
    public void insertInvoiceRow(String date, int customerID, int appointmentID){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_INVOICE_DATE, date);
        contentValues.put(KEY_INVOICE_CUSTOMER_ID, customerID);
        contentValues.put(KEY_INVOICE_APPOINTMENT_ID, appointmentID);

        long temp = db.insert(TABLE_INVOICE, null, contentValues);
        db.close();
    }

    //Populate all the tables with data
    private void populateTables(){

        //Customer
        insertCustomerRow("Ace", "Ventura", "M", "1981-03-28", "9991234");
        insertCustomerRow("Thomas", "Shelby", "M", "1987-11-13", "9995678");
        insertCustomerRow("Zoe", "Ren", "F", "1991-04-09", "9891357");
        insertCustomerRow("Jane", "Doe", "F", "1994-06-21", "9892468");
        insertCustomerRow("Adam", "Grabowski", "M", "1988-09-14", "8881145");
        insertCustomerRow("Grace", "Abbott", "F", "1990-04-27", "8887755");

        //Therapist
        insertTherapistRow("Bailey", "Allen", "F", "1990-01-29", "9997747");
        insertTherapistRow("Tammy", "Edson", "F", "1994-02-25", "9994474");
        insertTherapistRow("Johnathon", "Edlin", "M", "1991-05-03", "9996868");
        insertTherapistRow("Joe", "Rogan", "M", "1975-03-22", "9990011");
        insertTherapistRow("Christine", "Lewis", "F", "1988-07-21", "9993333");
        insertTherapistRow("Sandy", "Shore", "F", "1986-08-19", "9895655");

        //Timetable
        insertTimetableRow("2019-05-27", "2019-06-02", "09:00", "17:00", 1);
        insertTimetableRow("2019-05-27", "2019-06-02", "07:00", "15:00", 2);
        insertTimetableRow("2019-05-20", "2019-06-26", "07:00", "15:00", 1);
        insertTimetableRow("2019-05-20", "2019-06-26", "09:00", "17:00", 2);
        insertTimetableRow("2019-05-27", "2019-06-02", "13:00", "17:00", 3);
        insertTimetableRow("2019-05-27", "2019-06-02", "08:00", "12:00", 6);

        //Service
        insertServiceRow("Full Body Massage", 1, "75.00", "60 minutes");
        insertServiceRow("Upper Body Massage", 1, "45.00", "30 minutes");
        insertServiceRow("Lower Body Massage", 1, "45.00", "30 minutes");
        insertServiceRow("Facial Treatment", 2, "50.00", "45 minutes");
        insertServiceRow("Hot Stone Massage", 1, "80.00", "60 minutes");
        insertServiceRow("Suction Cup Therapy", 3, "80.00", "60 minutes");

        //Service Category
        insertServiceCategoryRow("Massage Services", "Covers a range of massages from traditional to hot stones.");
        insertServiceCategoryRow("Facial Services", "Relaxing and cleansing facial treatments.");
        insertServiceCategoryRow("Special Services", "Specialised services by trained professionals.");

        //Appointment
        insertAppointmentRow("2019-05-27", "09:15", 1, 1, 3, "Allergy to bee related products.");
        insertAppointmentRow("2019-05-27", "09:15", 4, 2, 1, "");
        insertAppointmentRow("2019-05-27", "13:15", 2, 1, 4, "");
        insertAppointmentRow("2019-05-28", "10:15", 3, 6, 5, "");
        insertAppointmentRow("2019-05-28", "16:00", 6, 3, 6, "");
        insertAppointmentRow("2019-05-30", "09:15", 1, 1, 2, "Allergy to bee related products.");

        //Invoice
        insertInvoiceRow("2019-05-27", 1, 1);
        insertInvoiceRow("2019-05-27", 4, 2);
        insertInvoiceRow("2019-05-27", 2, 3);
        insertInvoiceRow("2019-05-28", 3, 4);
        insertInvoiceRow("2019-05-28", 6, 5);
        insertInvoiceRow("2019-06-03", 1, 6);
    }

    //check data is only written to database once
    public void checkToPopulateTables(){

        //Grants access to our database in read mode
        SQLiteDatabase db = this.getReadableDatabase();

        //query the number of rows from Customer table
        String query = "SELECT count(*) FROM " + TABLE_CUSTOMER;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        //check if there is data in the first column
        //populate the database if it's empty
        int i = cursor.getInt(0);
        if (i < 1)
            populateTables();

        //close the database
        db.close();
    }


    //Display the table data in the application
    //Return Customer data
    public ArrayList<HashMap<String, String>> getCustomers(){

        SQLiteDatabase db = this.getReadableDatabase();

        //array list with a hashmap
        ArrayList<HashMap<String, String>> customerList = new ArrayList<>();
        //query to retrieve all information
        String query = "SELECT customerid, custfirstname, custlastname, custgender, custdob, custphonenumber FROM " +
                       TABLE_CUSTOMER;
        //pass the rawQuery result to a Cursor instance
        Cursor cursor = db.rawQuery(query,null);

        //write a loop that will collect the requested information and save it to a hashmap
        while (cursor.moveToNext()){
            HashMap<String,String> customer = new HashMap<>();
            customer.put("customerid",cursor.getString(cursor.getColumnIndex(KEY_CUSTOMER_ID)));
            customer.put("custfirstname",cursor.getString(cursor.getColumnIndex(KEY_CUST_FIRST_NAME)));
            customer.put("custlastname",cursor.getString(cursor.getColumnIndex(KEY_CUST_LAST_NAME)));
            customer.put("custgender",cursor.getString(cursor.getColumnIndex(KEY_CUST_GENDER)));
            customer.put("custdob",cursor.getString(cursor.getColumnIndex(KEY_CUST_DOB)));
            customer.put("custphonenumber",cursor.getString(cursor.getColumnIndex(KEY_CUST_PHONE_NO)));
            //add the hashmap to the array list
            customerList.add(customer);
        }
        db.close();
        return  customerList;
    }

    //Return Therapist data
    public ArrayList<HashMap<String, String>> getTherapists(){

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> therapistList = new ArrayList<>();
        String query = "SELECT therapistid, therapistfirstname, therapistlastname, therapistgender, therapistdob, " +
                "therapistphonenumber FROM " + TABLE_THERAPIST;
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> therapist = new HashMap<>();
            therapist.put("therapistid",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_ID)));
            therapist.put("therapistfirstname",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_FIRST_NAME)));
            therapist.put("therapistlastname",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_LAST_NAME)));
            therapist.put("therapistgender",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_GENDER)));
            therapist.put("therapistdob",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_DOB)));
            therapist.put("therapistphonenumber",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_PHONE_NO)));
            therapistList.add(therapist);
        }
        db.close();
        return  therapistList;
    }

    //Return Timetable data
    public ArrayList<HashMap<String, String>> getTimetables(){

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> timetableList = new ArrayList<>();
        String query = "SELECT timetableid, ttperiodbegin, ttperiodend, ttstarttime, ttendtime, " +
                "therapistfirstname, therapistlastname FROM " + TABLE_TIMETABLE +
                " INNER JOIN " + TABLE_THERAPIST + " ON " + TABLE_TIMETABLE + ".tttherapistid = " + TABLE_THERAPIST + ".therapistid" +
                " ORDER BY ttperiodbegin";
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> timetable = new HashMap<>();
            timetable.put("timetableid",cursor.getString(cursor.getColumnIndex(KEY_TIMETABLE_ID)));
            timetable.put("ttperiodbegin",cursor.getString(cursor.getColumnIndex(KEY_TT_PERIOD_BEGIN)));
            timetable.put("ttperiodend",cursor.getString(cursor.getColumnIndex(KEY_TT_PERIOD_END)));
            timetable.put("ttstarttime",cursor.getString(cursor.getColumnIndex(KEY_TT_START_TIME)));
            timetable.put("ttendtime",cursor.getString(cursor.getColumnIndex(KEY_TT_END_TIME)));
            timetable.put("therapistfirstname",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_FIRST_NAME)));
            timetable.put("therapistlastname",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_LAST_NAME)));
            timetableList.add(timetable);
        }
        db.close();
        return  timetableList;
    }

    //Return Service data
    public ArrayList<HashMap<String, String>> getServices(){

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> serviceList = new ArrayList<>();
        String query = "SELECT serviceid, servicedescription, servcatname, serviceprice, servicelength FROM " + TABLE_SERVICE +
                " INNER JOIN " + TABLE_SERVICE_CATEGORY + " ON " + TABLE_SERVICE + ".servicecategoryid = " +
                TABLE_SERVICE_CATEGORY + ".servcatid";
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> service = new HashMap<>();
            service.put("serviceid",cursor.getString(cursor.getColumnIndex(KEY_SERVICE_ID)));
            service.put("servicedescription",cursor.getString(cursor.getColumnIndex(KEY_SERVICE_DESCRIPTION)));
            service.put("servcatname",cursor.getString(cursor.getColumnIndex(KEY_SERV_CAT_NAME)));
            service.put("serviceprice",cursor.getString(cursor.getColumnIndex(KEY_SERVICE_PRICE)));
            service.put("servicelength",cursor.getString(cursor.getColumnIndex(KEY_SERVICE_LENGTH)));
            serviceList.add(service);
        }
        db.close();
        return  serviceList;
    }

    //Return Service Category data
    public ArrayList<HashMap<String, String>> getServiceCategories(){

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> serviceCategoryList = new ArrayList<>();
        String query = "SELECT servcatid, servcatname, servcatdescription FROM " + TABLE_SERVICE_CATEGORY;
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> serviceCategory = new HashMap<>();
            serviceCategory.put("servcatid",cursor.getString(cursor.getColumnIndex(KEY_SERV_CAT_ID)));
            serviceCategory.put("servcatname",cursor.getString(cursor.getColumnIndex(KEY_SERV_CAT_NAME)));
            serviceCategory.put("servcatdescription",cursor.getString(cursor.getColumnIndex(KEY_SERV_CAT_DESCRIPTION)));
            serviceCategoryList.add(serviceCategory);
        }
        db.close();
        return  serviceCategoryList;
    }

    //Return Appointment data
    public ArrayList<HashMap<String, String>> getAppointments(){

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> appointmentList = new ArrayList<>();
        String query = "SELECT appointmentid, appointmentdate, appointmenttime, custfirstname, custlastname, therapistfirstname, " +
                "therapistlastname, servicedescription, appointmentnotes FROM " + TABLE_APPOINTMENT +
                " INNER JOIN " + TABLE_CUSTOMER + " ON " + TABLE_APPOINTMENT + ".appointmentcustomerid = " + TABLE_CUSTOMER + ".customerid" +
                " INNER JOIN " + TABLE_THERAPIST + " ON " + TABLE_APPOINTMENT + ".appointmentcustomerid = " + TABLE_THERAPIST + ".therapistid" +
                " INNER JOIN " + TABLE_SERVICE + " ON " + TABLE_APPOINTMENT + ".appointmentcustomerid = " + TABLE_SERVICE + ".serviceid";
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> appointment = new HashMap<>();
            appointment.put("appointmentid",cursor.getString(cursor.getColumnIndex(KEY_APPOINTMENT_ID)));
            appointment.put("appointmentdate",cursor.getString(cursor.getColumnIndex(KEY_APPOINTMENT_DATE)));
            appointment.put("appointmenttime",cursor.getString(cursor.getColumnIndex(KEY_APPOINTMENT_TIME)));
            appointment.put("custfirstname",cursor.getString(cursor.getColumnIndex(KEY_CUST_FIRST_NAME)));
            appointment.put("custlastname",cursor.getString(cursor.getColumnIndex(KEY_CUST_LAST_NAME)));
            appointment.put("therapistfirstname",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_FIRST_NAME)));
            appointment.put("therapistlastname",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_LAST_NAME)));
            appointment.put("servicedescription",cursor.getString(cursor.getColumnIndex(KEY_SERVICE_DESCRIPTION)));
            appointment.put("appointmentnotes",cursor.getString(cursor.getColumnIndex(KEY_APPOINTMENT_NOTES)));
            appointmentList.add(appointment);
        }
        db.close();
        return  appointmentList;
    }

    //Return Invoice data
    public ArrayList<HashMap<String, String>> getInvoices(){

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> invoiceList = new ArrayList<>();
        String query = "SELECT invoiceid, invoicedate, serviceprice, custfirstname, custlastname, invoiceappointmentid " +
                "FROM " + TABLE_INVOICE +
                " INNER JOIN " + TABLE_CUSTOMER + " ON " + TABLE_INVOICE + ".invoicecustomerid = " + TABLE_CUSTOMER + ".customerid" +
                " INNER JOIN " + TABLE_APPOINTMENT + " ON " + TABLE_INVOICE + ".invoiceappointmentid = " + TABLE_APPOINTMENT + ".appointmentid" +
                " INNER JOIN " + TABLE_SERVICE + " ON " + TABLE_APPOINTMENT + ".appointmentserviceid = " + TABLE_SERVICE + ".serviceid";
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> invoice = new HashMap<>();
            invoice.put("invoiceid",cursor.getString(cursor.getColumnIndex(KEY_INVOICE_ID)));
            invoice.put("invoicedate",cursor.getString(cursor.getColumnIndex(KEY_INVOICE_DATE)));
            invoice.put("serviceprice",cursor.getString(cursor.getColumnIndex(KEY_SERVICE_PRICE)));
            invoice.put("custfirstname",cursor.getString(cursor.getColumnIndex(KEY_CUST_FIRST_NAME)));
            invoice.put("custlastname",cursor.getString(cursor.getColumnIndex(KEY_CUST_LAST_NAME)));
            invoice.put("invoiceappointmentid",cursor.getString(cursor.getColumnIndex(KEY_INVOICE_APPOINTMENT_ID)));
            invoiceList.add(invoice);
        }
        db.close();
        return  invoiceList;
    }


    //Perform queries to retrieve specific data from database
    //Query1
    public ArrayList<HashMap<String, String>> query1(int therapistID, String currentDate){

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> appointmentList = new ArrayList<>();
        String query = "SELECT therapistfirstname, therapistlastname, appointmentid, appointmentdate, custfirstname, custlastname " +
                "FROM " + TABLE_APPOINTMENT +
                " INNER JOIN " + TABLE_THERAPIST + " ON " + TABLE_APPOINTMENT + ".appointmenttherapistid = " + TABLE_THERAPIST + ".therapistid" +
                " INNER JOIN " + TABLE_CUSTOMER + " ON " + TABLE_APPOINTMENT + ".appointmentcustomerid = " + TABLE_CUSTOMER + ".customerid" +
                " WHERE therapistid = '" + therapistID + "' AND appointmentdate >= " + currentDate;
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> appointment = new HashMap<>();
            appointment.put("therapistfirstname",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_FIRST_NAME)));
            appointment.put("therapistlastname",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_LAST_NAME)));
            appointment.put("appointmentid",cursor.getString(cursor.getColumnIndex(KEY_APPOINTMENT_ID)));
            appointment.put("appointmentdate",cursor.getString(cursor.getColumnIndex(KEY_APPOINTMENT_DATE)));
            appointment.put("custfirstname",cursor.getString(cursor.getColumnIndex(KEY_CUST_FIRST_NAME)));
            appointment.put("custlastname",cursor.getString(cursor.getColumnIndex(KEY_CUST_LAST_NAME)));
            appointmentList.add(appointment);
        }
        db.close();
        return  appointmentList;
    }

    //Query2A
    public ArrayList<HashMap<String, String>> query2A(int therapistID){

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> therapistEarningsList = new ArrayList<>();
        String query = "SELECT therapistfirstname, therapistlastname, serviceprice FROM " + TABLE_INVOICE +
                " INNER JOIN " + TABLE_APPOINTMENT + " ON " + TABLE_INVOICE + ".invoiceappointmentid = " + TABLE_APPOINTMENT + ".appointmentid" +
                " INNER JOIN " + TABLE_SERVICE + " ON " + TABLE_APPOINTMENT + ".appointmentserviceid = " + TABLE_SERVICE + ".serviceid" +
                " INNER JOIN " + TABLE_THERAPIST + " ON " + TABLE_APPOINTMENT + ".appointmenttherapistid = " + TABLE_THERAPIST + ".therapistid" +
                " WHERE invoiceappointmentid = appointmentid" +
                " AND appointmenttherapistid = " + therapistID;
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> earnings = new HashMap<>();
            earnings.put("therapistfirstname",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_FIRST_NAME)));
            earnings.put("therapistlastname",cursor.getString(cursor.getColumnIndex(KEY_THERAPIST_LAST_NAME)));
            earnings.put("serviceprice",cursor.getString(cursor.getColumnIndex(KEY_SERVICE_PRICE)));
            therapistEarningsList.add(earnings);
        }
        db.close();
        return  therapistEarningsList;
    }


    //Query2B
    public double query2B(int therapistID){

        double temp = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> therapistEarningsList = new ArrayList<>();
        String query = "SELECT serviceprice " +
                "FROM " + TABLE_INVOICE +
                " INNER JOIN " + TABLE_APPOINTMENT + " ON " + TABLE_INVOICE + ".invoiceappointmentid = " + TABLE_APPOINTMENT + ".appointmentid" +
                " INNER JOIN " + TABLE_SERVICE + " ON " + TABLE_APPOINTMENT + ".appointmentserviceid = " + TABLE_SERVICE + ".serviceid" +
                " WHERE invoiceappointmentid = appointmentid" +
                " AND appointmenttherapistid = " + therapistID;
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> earnings = new HashMap<>();
            earnings.put("serviceprice",cursor.getString(cursor.getColumnIndex(KEY_SERVICE_PRICE)));
            therapistEarningsList.add(earnings);
            temp += Double.parseDouble(cursor.getString(cursor.getColumnIndex(KEY_SERVICE_PRICE)));
        }
        db.close();
        return  temp;
    }

    //Query3
    public ArrayList<HashMap<String, String>> query3(String currentDate){

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> serviceCategoryList = new ArrayList<>();
        String query = "SELECT servcatname, servicedescription, appointmentdate FROM " + TABLE_APPOINTMENT +
                " INNER JOIN " + TABLE_SERVICE + " ON " + TABLE_APPOINTMENT + ".appointmentserviceid = " + TABLE_SERVICE + ".serviceid" +
                " INNER JOIN " + TABLE_SERVICE_CATEGORY + " ON " + TABLE_SERVICE + ".servicecategoryid = " + TABLE_SERVICE_CATEGORY + ".servcatid" +
                " WHERE appointmentdate >= " + currentDate;
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> serviceCategory = new HashMap<>();
            serviceCategory.put("servcatname",cursor.getString(cursor.getColumnIndex(KEY_SERV_CAT_NAME)));
            serviceCategory.put("servicedescription",cursor.getString(cursor.getColumnIndex(KEY_SERVICE_DESCRIPTION)));
            serviceCategory.put("appointmentdate",cursor.getString(cursor.getColumnIndex(KEY_APPOINTMENT_DATE)));
            serviceCategoryList.add(serviceCategory);
        }
        db.close();
        return  serviceCategoryList;
    }

    //Delete rows from a table
    //Delete Customer
    public void DeleteCustomer(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CUSTOMER, KEY_CUSTOMER_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Delete Therapist
    public void DeleteTherapist(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_THERAPIST, KEY_THERAPIST_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Delete Timetable
    public void DeleteTimetable(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TIMETABLE, KEY_TIMETABLE_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Delete Service
    public void DeleteService(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SERVICE, KEY_SERVICE_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Delete Service Category
    public void DeleteServiceCategory(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SERVICE_CATEGORY, KEY_SERV_CAT_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Delete Appointment
    public void DeleteAppointment(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_APPOINTMENT, KEY_APPOINTMENT_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Delete Invoice
    public void DeleteInvoice(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CUSTOMER, KEY_CUSTOMER_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}