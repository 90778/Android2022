package de.hs_weingarten.ma_explintentslist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class TruckAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Truck Details");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void truckSaveClicked(View view) {
        EditText name = (EditText) findViewById(R.id.truckName);
        EditText weight = (EditText) findViewById(R.id.truckWeight);
        EditText axles = (EditText) findViewById(R.id.truckAxles);

        // für eine korrekte Arbeitsweise müssten Fehleingaben abgefangen werden.
        TruckSingleton.getInstance().addTruck(
                // name.getText --> CharSequence --> in String umwandeln
                name.getText().toString(),
                // weight.getText() ist eine CharSequence --> in String umwandeln --> in Double umwandeln
                Double.parseDouble(weight.getText().toString()),
                // weight.getText() ist eine CharSequence --> in String umwandeln --> in Integer umwandeln
                Integer.parseInt(axles.getText().toString()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Back Button schließt die Activity
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
