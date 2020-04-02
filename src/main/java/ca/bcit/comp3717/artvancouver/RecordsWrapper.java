package ca.bcit.comp3717.artvancouver;

import java.io.Serializable;
import java.util.ArrayList;

public class RecordsWrapper implements Serializable {

    private ArrayList<Record> records;

    public RecordsWrapper(ArrayList<Record> records) {
        this.records = records;
    }

    public ArrayList<Record> getRecords() {
        return this.records;
    }
}
