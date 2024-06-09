package com.bangladateupdate.byfoysaltech;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bangladateupdate.bangladatebyfoysaltech.BanglaDateUpdate;

public class MainActivity extends AppCompatActivity {

    TextView BanglaTimeTv, BanglaDateTv, EnglishDateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BanglaTimeTv = findViewById(R.id.BanglaTimeTv);
        BanglaDateTv = findViewById(R.id.BanglaDateTv);
        EnglishDateTv = findViewById(R.id.EnglishDateTv);


        BanglaDateUpdate banglaDateUpdate = new BanglaDateUpdate();

        String weekDay = banglaDateUpdate.getWeekDay();
        String bangladay = banglaDateUpdate.getBanglaDays();
        String BanglaMonth = banglaDateUpdate.getBanglaMonths();
        String BanglaYear = banglaDateUpdate.getBanglaYears();
        String banglaSeason = banglaDateUpdate.getBanglaSeason();
        String englishDate = banglaDateUpdate.getEnglishDate();


        BanglaDateTv.setText(banglaDateUpdate.getFullBanglaDate());
        banglaDateUpdate.startUpdatingTime(BanglaTimeTv, false, true);
        EnglishDateTv.setText(englishDate);



    }

}
