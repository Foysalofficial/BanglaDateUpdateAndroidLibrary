package com.bangladateupdate.bangladatebyfoysaltech;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BanglaDateUpdate {
    public final long UPDATE_INTERVAL = 1000;
    private int bDay;
    private int bMonth;
    private int bYear;

    public void addMonth(int year, int month) {
        bYear = (month % 12 == 0) ? year - 1 : year;
        bMonth = (month % 12 == 0) ? 12 : month % 12;
        bDay = 1;
    }

    public String now(Locale locale) {
        Calendar today = Calendar.getInstance();
        return toBanglaDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH), locale);
    }

    public String toBanglaDate(int gYear, int gMonth, int gDay, Locale locale) {
        int[] totalDaysInMonth = isLeapYear(gYear) ? new int[]{31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 29, 30} : new int[]{31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 30};

        int banglaYear = (gMonth < 3 || (gMonth == 3 && gDay < 14)) ? gYear - 594 : gYear - 593;
        int epochYear = (gMonth < 4 || (gMonth == 4 && gDay < 14)) ? gYear - 1 : gYear;

        Calendar banglaCalendar = Calendar.getInstance();
        banglaCalendar.set(gYear, gMonth, gDay);
        Calendar epochCalendar = Calendar.getInstance();
        epochCalendar.set(epochYear, 4, 14);

        long diff = banglaCalendar.getTimeInMillis() - epochCalendar.getTimeInMillis();
        long difference = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        int banglaMonthIndex = 0;
        for (int i = 1; i <= 12; i++) {
            if (difference <= totalDaysInMonth[i]) {
                banglaMonthIndex = i;
                break;
            }
            difference -= totalDaysInMonth[i];
        }
        int banglaDate = (int) difference;
        String banglaSeason = getBanglaSeason(banglaMonthIndex);

        return bDate(translateNumbersToBangla(Integer.toString(banglaYear)), getBanglaMonths()[banglaMonthIndex], translateNumbersToBangla(Integer.toString(banglaDate)),banglaSeason);
    }

    private boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    private String translateNumbersToBangla(String inputNumber) {
        String[] banglaNumbers = {"০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"};
        StringBuilder sb = new StringBuilder();
        for (char ch : inputNumber.toCharArray()) {
            if (Character.isDigit(ch)) {
                sb.append(banglaNumbers[ch - '0']);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    private String bDate(String year, String month, String day, String Season) {
        return day + " " + month + ", " + year + ", " + Season;
    }

    public String[] getBanglaMonths() {
        return new String[]{"বৈশাখ", "জ্যৈষ্ঠ", "আষাঢ়", "শ্রাবণ", "ভাদ্র", "আশ্বিন", "কার্তিক", "অগ্রহায়ণ", "পৌষ", "মাঘ", "ফাল্গুন", "চৈত্র"};
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

    public String getEnglishDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd - MMMM - yyyy | EEEE", new Locale("bn"));
        String bengaliDate = dateFormat.format(calendar.getTime());
        return bengaliDate;
    }


    private static String getBanglaSeason(int month) {
        String[] banglaSeasons = {"গ্রীষ্মকাল", "বর্ষাকাল", "শরৎকাল", "হেমন্তকাল", "শীতকাল", "বসন্তকাল"};
        String season;
        if (month == 0 || month == 1 || month == 2) {
            season = banglaSeasons[0]; // গ্রীষ্মকাল
        } else if (month == 2 || month == 3) {
            season = banglaSeasons[1]; // বর্ষাকাল
        } else if (month == 4 || month == 5) {
            season = banglaSeasons[2]; // শরৎকাল
        } else if (month == 6 || month == 7) {
            season = banglaSeasons[3]; // হেমন্তকাল
        } else if (month == 8 || month == 9) {
            season = banglaSeasons[4]; // শীতকাল
        } else {
            season = banglaSeasons[5]; // বসন্তকাল
        }
        return season;
    }

}


