package ca.bcit.comp3717.artvancouver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class DiscussionActivity extends AppCompatActivity {

    int artId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        EditText editText = findViewById(R.id.editText);

        Intent intent = getIntent();
        artId = intent.getIntExtra("artId",-1);

        if (artId != -1) {
            editText.setText(ForumActivity.artList.get(artId));
        } else {
            ForumActivity.artList.add("");
            artId = ForumActivity.artList.size() - 1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ForumActivity.artList.set(artId, String.valueOf(charSequence));
                ForumActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ca.bcit.comp3717.artvancouver", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(ForumActivity.artList);
                sharedPreferences.edit().putStringSet("artList", set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
