package com.bangladateupdate.bangladatebyfoysaltech;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BanglaDateUpdate {

    public final long UPDATE_INTERVAL = 1000;
    public Date currentDate = new Date();
    public final String[] BANGLA_WEEKDAYS = {"রবিবার", "সোমবার", "মঙ্গলবার", "বুধবার", "বৃহস্পতিবার", "শুক্রবার", "শনিবার"};
    private final String[] BANGLA_MONTHS = {"বৈশাখ", "জ্যৈষ্ঠ", "আষাঢ়", "শ্রাবণ", "ভাদ্র", "আশ্বিন", "কার্তিক", "অগ্রহায়ণ", "পৌষ", "মাঘ", "ফাল্গুন", "চৈত্র"};
    private final String[] SESSIONS = {"গ্রীষ্মকাল", "বর্ষাকাল", "শরৎকাল", "হেমন্তকাল", "শীতকাল", "বসন্তকাল"};
    private final String[] BANGLA_NUMBERS = {"০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"};

    private int[] getBanglaMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;

        int banglaMonth, banglaDate;
        switch (month) {
            case 1:
                banglaMonth = (dayOfMonth <= 13) ? 8 : 9;
                banglaDate = (dayOfMonth <= 13) ? dayOfMonth + 17 : dayOfMonth - 13;
                break;
            case 2:
                banglaMonth = (dayOfMonth <= 12) ? 9 : 10;
                banglaDate = (dayOfMonth <= 12) ? dayOfMonth + 18 : dayOfMonth - 12;
                break;
            case 3:
                banglaMonth = (dayOfMonth <= 14) ? 10 : 11;
                banglaDate = (dayOfMonth <= 14) ? dayOfMonth + 16 : dayOfMonth - 14;
                break;
            case 4:
                banglaMonth = (dayOfMonth <= 13) ? 11 : 0;
                banglaDate = (dayOfMonth <= 13) ? dayOfMonth + 17 : dayOfMonth - 13;
                break;
            case 5:
                banglaMonth = (dayOfMonth <= 14) ? 0 : 1;
                banglaDate = (dayOfMonth <= 14) ? dayOfMonth + 17 : dayOfMonth - 14;
                break;
            case 6:
                banglaMonth = (dayOfMonth <= 14) ? 1 : 2;
                banglaDate = (dayOfMonth <= 14) ? dayOfMonth + 17 : dayOfMonth - 14;
                break;
            case 7:
                banglaMonth = (dayOfMonth <= 15) ? 2 : 3;
                banglaDate = (dayOfMonth <= 15) ? dayOfMonth + 16 : dayOfMonth - 15;
                break;
            case 8:
                banglaMonth = (dayOfMonth <= 15) ? 3 : 4;
                banglaDate = (dayOfMonth <= 15) ? dayOfMonth + 16 : dayOfMonth - 15;
                break;
            case 9:
                banglaMonth = (dayOfMonth <= 15) ? 4 : 5;
                banglaDate = (dayOfMonth <= 15) ? dayOfMonth + 16 : dayOfMonth - 15;
                break;
            case 10:
                banglaMonth = (dayOfMonth <= 15) ? 5 : 6;
                banglaDate = (dayOfMonth <= 15) ? dayOfMonth + 15 : dayOfMonth - 15;
                break;
            case 11:
                banglaMonth = (dayOfMonth <= 14) ? 6 : 7;
                banglaDate = (dayOfMonth <= 14) ? dayOfMonth + 16 : dayOfMonth - 14;
                break;
            case 12:
                banglaMonth = (dayOfMonth <= 14) ? 7 : 8;
                banglaDate = (dayOfMonth <= 14) ? dayOfMonth + 16 : dayOfMonth - 14;
                break;
            default:
                banglaMonth = 0;
                banglaDate = 0;
        }
        return new int[]{banglaMonth, banglaDate};
    }

    private String convertToBanglaNumber(int number) {
        StringBuilder result = new StringBuilder();
        String numStr = String.valueOf(number);
        for (char digit : numStr.toCharArray()) {
            int digitVal = Character.getNumericValue(digit);
            result.append(BANGLA_NUMBERS[digitVal]);
        }
        return result.toString();
    }

    private int getBanglaYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return (calendar.get(Calendar.MONTH) < Calendar.APRIL && calendar.get(Calendar.DATE) <= 13) ? year - 594 : year - 593;
    }

    private String getBanglaWeekDayName(int dayOfWeek) {
        if (dayOfWeek >= 0 && dayOfWeek < BANGLA_WEEKDAYS.length) {
            return BANGLA_WEEKDAYS[dayOfWeek];
        } else {
            return "Invalid Day";
        }
    }

    public String getWeekDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return getBanglaWeekDayName(dayOfWeek - 1);
    }


    public String getEnglishDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd - MMMM - yyyy | EEEE", new Locale("bn"));
        String bengaliDate = dateFormat.format(calendar.getTime());
        return bengaliDate;
    }

    public void startUpdatingTime(TextView textView, boolean periodTrueOrfalse, boolean MorningOrafternoon) {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                int hour = (hourOfDay % 12 == 0) ? 12 : hourOfDay % 12;
                String period = (hourOfDay < 12) ? "পূর্বাহ্ণ" : "অপরাহ্ণ";
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                if (periodTrueOrfalse) {
                    String banglaTime = convertToBangla(hour) + ":" + convertToBangla(minute) + ":" + convertToBangla(second) + " " + period;
                    textView.setText(banglaTime);
                } else if (MorningOrafternoon) {
                    String banglaTime = convertToBangla(hour) + ":" + convertToBangla(minute) + ":" + convertToBangla(second) + " " + getTimePeriod();
                    textView.setText(banglaTime);
                } else {
                    String banglaTime = convertToBangla(hour) + ":" + convertToBangla(minute) + ":" + convertToBangla(second);
                    textView.setText(banglaTime);
                }
                handler.postDelayed(this, UPDATE_INTERVAL);
            }
        });
    }

    public String getTimePeriod() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 5 && hour < 12) {
            return "সকাল"; // Morning
        } else if (hour >= 12 && hour < 18) {
            return "দুপুর"; // Afternoon
        } else {
            return "রাত"; // Night
        }
    }

    private String convertToBangla(int time) {
        String[] banglaDigits = {"০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"};
        String timeStr = String.format(Locale.getDefault(), "%02d", time);
        StringBuilder banglaTime = new StringBuilder();
        for (char digit : timeStr.toCharArray()) {
            if (Character.isDigit(digit)) {
                banglaTime.append(banglaDigits[Character.getNumericValue(digit)]);
            } else {
                banglaTime.append(digit);
            }
        }
        return banglaTime.toString();
    }

    public String getFullBanglaDate() {
        Date currentDate = new Date();
        String banglaDate = "";
        try {
            int[] banglaMonthDate = getBanglaMonthDate(currentDate);
            String banglaMonth = BANGLA_MONTHS[banglaMonthDate[0]];
            String banglaDay = convertToBanglaNumber(banglaMonthDate[1]);
            String session = SESSIONS[banglaMonthDate[0] / 2];
            String year = convertToBanglaNumber(getBanglaYear(currentDate));
            banglaDate = banglaDay + " - " + banglaMonth + " - " + year + " | " + session;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return banglaDate;
    }

    public String getBanglaDays() {
        Date currentDate = new Date();
        String banglaDate = "";
        try {
            int[] banglaMonthDate = getBanglaMonthDate(currentDate);
            String banglaDay = convertToBanglaNumber(banglaMonthDate[1]);
            banglaDate = banglaDay;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return banglaDate;
    }

    public String getBanglaMonths() {
        Date currentDate = new Date();
        String banglaDate = "";
        try {
            int[] banglaMonthDate = getBanglaMonthDate(currentDate);
            String banglaMonth = BANGLA_MONTHS[banglaMonthDate[0]];
            banglaDate = banglaMonth;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return banglaDate;
    }

    public String getBanglaSeason() {
        Date currentDate = new Date();
        String banglaDate = "";
        try {
            int[] banglaMonthDate = getBanglaMonthDate(currentDate);
            String session = SESSIONS[banglaMonthDate[0] / 2];
            banglaDate = session;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return banglaDate;
    }

    public String getBanglaYears() {
        Date currentDate = new Date();
        String banglaDate = "";
        try {
            String year = convertToBanglaNumber(getBanglaYear(currentDate));
            banglaDate = year;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return banglaDate;
    }


}

