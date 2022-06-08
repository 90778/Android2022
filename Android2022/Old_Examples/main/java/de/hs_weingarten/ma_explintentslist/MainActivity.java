package com.example.sose21_u1_lkw;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private boolean isSoil = true;
    private boolean isGrit = false;
    private int selectedTruck = 0;
    private double weight = 1000;
    boolean isValidDay = false;
    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.truck_title);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView resultImage = (ImageView) findViewById(R.id.okImage);
                boolean isValid = false;
                switch (selectedTruck) {
                    case 0 : if (((isGrit == true) && (weight <= 30.0)) || ((isSoil == true) && (weight <= 25.0))){
                        isValid = true;
                    }
                        break;
                    case 1 : if (((isGrit == true) && (weight <= 20.0)) || ((isSoil == true) && (weight <= 20.0))) {
                        isValid = true;
                    }
                        break;
                    case 2 : if (((isGrit == true) && (weight <= 21.0)) || ((isSoil == true) && (weight <= 20.0))) {
                        isValid = true;
                    }
                        break;
                }
                if (isValidDay == false) {
                    isValid = false;
                }
                if (isValid) {
                    resultImage.setImageResource(R.drawable.ok);
                }
                else {
                    resultImage.setImageResource(R.drawable.notok);
                }
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.truckSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.trucks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        final TextView weightText= (TextView) findViewById(R.id.weightEdit);
        weightText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String theName = v.getText().toString();
                    // Toast.makeText(MainActivity.this,v.getText(), Toast.LENGTH_SHORT).show();
                    weight =  Double.parseDouble(v.getText().toString());
                    return true;
                }
                return false;
            }
        });

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        GregorianCalendar date = new GregorianCalendar(year, month, day-1);
                        // 6 = Samstag, 7 = Sonntag
                        int dayOfWeek=date.get(date.DAY_OF_WEEK);
                        if (dayOfWeek <= 5) {
                            isValidDay = true;
                        } else {
                            isValidDay = false;
                        }
                        // Toast.makeText(MainActivity.this,"Datum: " + day + "." + month + "." + year+"Tag"+dayOfWeek, Toast.LENGTH_LONG).show();
                        Button dateButton = (Button) findViewById(R.id.dateButton);
                        dateButton.setText("Datum: " + day + "." + month + "." + year);
                    }
                }, year, month, day );
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
    }

    public void dateClicked(View view) {
        datePickerDialog.show();
    }

    public void soilClicked(View view) {
        isSoil = true;
        isGrit = false;
    }


    public void gritClicked(View view) {
        isSoil = false;
        isGrit = true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedTruck = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}