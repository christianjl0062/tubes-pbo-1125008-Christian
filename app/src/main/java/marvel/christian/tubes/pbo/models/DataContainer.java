package marvel.christian.tubes.pbo.models;

// Christian
public class DataContainer<T> {
    private T isiData;
    private String pesanStatus;

    public DataContainer(T isiData, String pesanStatus) {
        this.isiData = isiData;
        this.pesanStatus = pesanStatus;
    }

    public T getIsiData() {
        return isiData;
    }
    public void setIsiData(T isiData) {
        this.isiData = isiData;
    }
    public String getPesanStatus() {
        return pesanStatus;
    }
    public void setPesanStatus(String pesanStatus) {
        this.pesanStatus = pesanStatus;
    }
}
