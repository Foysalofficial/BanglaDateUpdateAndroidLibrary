package com.bangladateupdate.byfoysaltech;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bangladateupdate.bangladatebyfoysaltech.BanglaDateUpdate;

import java.util.Locale;

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
        String getFullBanglaDate = banglaDateUpdate.now(new Locale("bn"));

        BanglaDateTv.setText(getFullBanglaDate);
        banglaDateUpdate.startUpdatingTime(BanglaTimeTv, false, true);

        String englishDate = banglaDateUpdate.getEnglishDate();
        EnglishDateTv.setText(englishDate);



    }

}
