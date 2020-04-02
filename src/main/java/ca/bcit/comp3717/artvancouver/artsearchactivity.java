package ca.bcit.comp3717.artvancouver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class artsearchactivity extends AppCompatActivity {

    private List<Record> records;
    private ArrayList<Record> passRecords = new ArrayList<>();
    private Spinner statusSpinner;
    private Spinner typeSpinner;
    private Spinner neighbourhoodSpinner;
    private Spinner yearSpinner;
    private Spinner ownershipSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artsearchactivity);
        records = getRecordsData();
        statusSpinner = findViewById(R.id.statusSpinner);
        typeSpinner = findViewById(R.id.typeSpinner);
        neighbourhoodSpinner = findViewById(R.id.neighbourhoodSpinner);
        yearSpinner = findViewById(R.id.yearSpinner);
        ownershipSpinner = findViewById(R.id.ownershipSpinner);
        populateSpinners();
    }

    /* Convert JSON String to BaseStudent Model via GSON */
    public List<Record> getRecordsData() {
        String filename = getResources().getString(R.string.json_file_name);
        String jsonString = getAssetsJSON(filename);
        Log.d("MainActivity", "Json: " + jsonString);
        Gson gson = new Gson();
        BaseRecords baseRecord = gson.fromJson(jsonString, BaseRecords.class);
        return  baseRecord.getRecords();
    }

    public String getAssetsJSON(String fileName) {
        String json = null;
        try {
            InputStream inputStream = this.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public void onSearchArt(View view) {
        String statusText = statusSpinner.getSelectedItem().toString();

        String typeText = typeSpinner.getSelectedItem().toString();

        String neighbourhoodText = neighbourhoodSpinner.getSelectedItem().toString();

        String yearText = yearSpinner.getSelectedItem().toString();

        String ownershipText = ownershipSpinner.getSelectedItem().toString();

        for (int i = 0; i < records.size(); i++) {
            boolean match = true;
            Fields recordFields =records.get(i).getFields();
            if(!(recordFields.getStatus() == null) && !recordFields.getStatus().equals(statusText)) {
                match = false;
            } else if (!(recordFields.getType() == null) && !recordFields.getType().equals(typeText)) {
                match = false;
            } else if (!(recordFields.getNeighbourhood() == null) && !recordFields.getNeighbourhood().equals(neighbourhoodText)) {
                match = false;
            } else if (!(recordFields.getYearofinstallation() == null) && !recordFields.getYearofinstallation().equals(yearText)) {
                match = false;
            } else if (!(recordFields.getOwnership() == null) && !recordFields.getOwnership().equals(ownershipText)) {
                match = false;
            }
            if (match) {
                passRecords.add(records.get(i));
            }
        }

        Log.i("Info", "button pressed");
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("records", new RecordsWrapper(passRecords));
        startActivity(intent);
    }

    public void populateSpinners() {
        ArrayList<String> statuses = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        ArrayList<String> neighbourhoods = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<String> owners = new ArrayList<>();
        Integer currentYear = LocalDate.now().getYear();

        for(Record record: records) {
            String status = record.getFields().getStatus();
            String type = record.getFields().getType();
            String neighbourhood = record.getFields().getNeighbourhood();
            Integer year = Integer.parseInt(record.getFields().getYearofinstallation());
            String owner = record.getFields().getOwnership();
            if (owner==null) {
                owner = "Unknown";
            }
            if (!statuses.contains(status) && !(status==null)) {
                statuses.add(status);
            }
            if (!types.contains(type) && !(type==null)) {
                types.add(type);
            }
            if (!neighbourhoods.contains(neighbourhood) && !(neighbourhood==null)) {
                neighbourhoods.add(neighbourhood);
            }
            if (!years.contains(year) && !(neighbourhood==null)) {
                if (year > 1800 && year < currentYear) {
                    years.add(year);
                }
            }
            Collections.sort(years);
            Collections.reverse(years);
            Log.d("Art ID", record.getRecordid());
            Log.d("Owner", owner);
            if (!owner.equals("Unknown")) {
                if (!owners.contains(owner)) {
                    owners.add(owner);
                }
            }
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statuses);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, neighbourhoods);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        neighbourhoodSpinner.setAdapter(adapter3);

        ArrayAdapter<Integer> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, owners);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ownershipSpinner.setAdapter(adapter5);
    }
}
