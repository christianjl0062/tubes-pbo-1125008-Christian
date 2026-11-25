package marvel.christian.tubes.pbo.models;

import java.util.ArrayList;
import java.util.List;

// Christian
public class DataContainer<T> {
    private List<T> records;
    private String pesanStatus;

    public DataContainer(String pesanStatus) {
        this.records = new ArrayList<>();
        this.pesanStatus = pesanStatus;
    }

    public void addRecord(T item) {
        records.add(item);
    }

    public void setRecords(List<T> newData) {
        this.records = newData;
    }

    public T getByIndex(int index) {
        if (index >= 0 && index < records.size()) {
            return records.get(index);
        } else {
            return null;
        }
    }

    public int getTotalRecords() {
        return records.size();
    }

    public List<T> getAllRecords() {
        return records;
    }

    public String getPesanStatus() {
        return pesanStatus;
    }

    public void setPesanStatus(String pesanStatus) {
        this.pesanStatus = pesanStatus;
    }

    public String printAllData() {
        String hasil = pesanStatus + " (Total: " + records.size() + ")\n";
        for (int i = 0; i < records.size(); i++) {
            hasil = hasil + (i + 1) + ". " + records.get(i).toString() + "\n";
        }
        return hasil;
    }
}
