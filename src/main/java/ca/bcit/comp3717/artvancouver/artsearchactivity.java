package ca.bcit.comp3717.artvancouver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class artsearchactivity extends AppCompatActivity {

    private List<Record> records;
    private ArrayList<Record> passRecords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artsearchactivity);
        records = getRecordsData();
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

        for (int i = 0; i < records.size(); i++) {
//            do check on record[i] for search conditions and if match add to passRecords
        }

//        Code to filter art pieces and add to passRecords ArrayList.

        Log.i("Info", "button pressed");
        Intent intent = new Intent(this, MapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("records", passRecords);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
