import java.io.Serializable;

public class History implements Serializable {
    private String key;
    private int day;
    private int month;
    private int year;

    public History(String key, int day, int month, int year) {
        this.key = key;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

