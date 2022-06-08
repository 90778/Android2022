package de.hs_weingarten.ma_explintentslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TruckListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<String> TruckStrings = new ArrayList<String>();
    private TruckSingleton truckSingleton;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(getApplicationContext(), TruckAdd.class);
                startActivity(intentAdd);
            }
        });

        ListView listView = (ListView) findViewById(R.id.truckList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TruckStrings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        setTitle("Truck App");
    }

    @Override
    public void onStart() {
        super.onStart();

        TruckStrings.clear();
        for (int i = 0 ; i < truckSingleton.getInstance().getNoTrucks() ; i++){
            Truck truck =  truckSingleton.getInstance().getTruck(i);
            TruckStrings.add(truck.getName());
        }
        adapter.notifyDataSetChanged();
    }


    public void onItemClick(AdapterView<?>lV, View view, int pos, long id) {
        final Intent i = new Intent(this, TruckDetailActivity.class);
        // die Position des ausgewählten Elements entspricht dem Index des Arrays TruckStrings
        i.putExtra("truckIndex", pos);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_city_list, menu);
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
}
