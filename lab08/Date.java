public abstract class Date {

    public final int year;
    public final int month;
    public final int dayOfMonth;

    public abstract int dayOfYear();
    public abstract Date nextDate();

    public Date(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public String toString() {
        return "" + year + "/" + month + "/" + dayOfMonth;
    }
}