package ca.bcit.comp3717.artvancouver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ArtInfoActivity extends AppCompatActivity {

    Record passedRecord;
    ImageView image;
    TextView photoCredits;
    TextView owner;
    TextView address;
    TextView neighbourhood;
    TextView description;
    RatingBar rBar;
    ArtRating existingRating;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("ratings");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_info);
        getSupportActionBar().hide();
        passedRecord = (Record) getIntent().getSerializableExtra("record");
        populateActivity();
        setUpRatingBar();
    }

    private void setUpRatingBar() {
        rBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            boolean ratingChanged = false;
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (!ratingChanged) {
                    String recordID = passedRecord.getRecordid();
                    float prevValue = existingRating.getRating();
                    long prevCountRatings = existingRating.getNumberRatings();
                    float newValue = ((prevValue * prevCountRatings) + rating) / ++prevCountRatings;
                    Log.d("Error value", Float.toString(newValue));
                    ArtRating artRating = new ArtRating(recordID, newValue,
                            prevCountRatings);
                    reference.child(recordID).setValue(artRating);
                    Toast toast = Toast.makeText(rBar.getContext(),
                            "Thanks for rating! " + rating + " Stars", Toast.LENGTH_SHORT);
                    toast.show();
                    ratingChanged = true;
                    ratingBar.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        reference.child(passedRecord.getRecordid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    existingRating = new ArtRating(passedRecord.getRecordid());
                    existingRating.setRating(0);
                    existingRating.setNumberRatings(0);
                    rBar.setRating(existingRating.getRating());
                } else {
                    existingRating = dataSnapshot.getValue(ArtRating.class);
                    rBar.setRating(existingRating.getRating());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    private void populateActivity() {
        rBar = findViewById(R.id.artRatingBar);
        image = findViewById(R.id.artThumbnail);
        Photourl url = passedRecord.getFields().getPhotourl();
        String imageID = null;
        if (!(url == null)) {
            imageID = passedRecord.getFields().getPhotourl().getId();
        }
        if (!(imageID == null)) {
            String photoUrlStart = "https://opendata.vancouver.ca/explore/dataset/public-art/files/";
            String photoUrlEnd = "/download/";
            String photoURL = photoUrlStart + imageID + photoUrlEnd;
            Picasso.get().load(photoURL).fit().centerCrop().into(image);
        } else {
            image.setImageResource(R.drawable.unavailable_image);
        }
        photoCredits = findViewById(R.id.artPhotoCredits);
        String credits = passedRecord.getFields().getPhotocredits();
        if (!(credits == null)) {
            photoCredits.setText(credits);
        }
        owner = findViewById(R.id.artOwner);
        String artOwner = passedRecord.getFields().getOwnership();
        if (!(artOwner == null)) {
            owner.setText(artOwner);
        }
        address = findViewById(R.id.artStreetAddress);
        String artAddress = passedRecord.getFields().getSiteAddress();
        if (!(artAddress == null)) {
            address.setText(artAddress);
        }
        neighbourhood = findViewById(R.id.artNeighbourhood);
        String artNeighbourhood = passedRecord.getFields().getNeighbourhood();
        if (!(artNeighbourhood == null)) {
            neighbourhood.setText(artNeighbourhood);
        }
        description = findViewById(R.id.artDescription);
        String artDescription = passedRecord.getFields().getDescriptionofwork();
        if (!(artDescription == null)) {
            description.setText(artDescription);
        }
    }

    public void onClickCloseInfo(View v) {
        finish();
    }
}
