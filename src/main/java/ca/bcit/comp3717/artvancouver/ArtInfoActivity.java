package ca.bcit.comp3717.artvancouver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ArtInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_info);
        getSupportActionBar().hide();
    }

    public void onClickCloseInfo(View v) {
        finish();
    }
}
