/**
 * In a nonleap year for the French Revolutionary Calendar, the first
 * twelve months have 30 days and month 13 has five days.
 */
public class FrenchRevolutionaryDate extends Date {

    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        return (month - 1) * 30 + dayOfMonth;
    }

    @Override
    public Date nextDate() {
        int newDay, newMonth, newYear;
        if ((dayOfMonth == 30) || (dayOfMonth == 5 && month == 13)) {
            newDay = 1;
            newMonth = month + 1;
        } else {
            newDay = dayOfMonth + 1;
            newMonth = month;
        }
        if (newMonth == 14) {
            newMonth = 1;
            newYear = year + 1;
        } else {
            newYear = year;
        }
        return new FrenchRevolutionaryDate(newYear, newMonth, newDay);
    }
}