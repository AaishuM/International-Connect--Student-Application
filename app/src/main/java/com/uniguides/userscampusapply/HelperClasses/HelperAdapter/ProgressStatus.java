package com.uniguides.userscampusapply.HelperClasses.HelperAdapter;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ProgressStatus {
    private Date date;
    private String message;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDaysElapsed() {
        Calendar today = Calendar.getInstance();
        Calendar statusDate = Calendar.getInstance();
        statusDate.setTime(date);
        long diffInMillis = today.getTimeInMillis() - statusDate.getTimeInMillis();
        return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

}
