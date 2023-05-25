package com.mobiledevelopertest.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiledevelopertest.R;
import com.mobiledevelopertest.utils.SharedPrefManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailsPageActivity extends AppCompatActivity {

    TextView fname, lname, email;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        SharedPrefManager.getInstance(DetailsPageActivity.this);
        int ct = SharedPrefManager.read(SharedPrefManager.KEY_COUNTER,0);
        getSupportActionBar().setTitle("Open counter : "+ct);

        fname = (TextView) findViewById(R.id.fname);
        lname = (TextView) findViewById(R.id.lname);
        email = (TextView) findViewById(R.id.email);

        avatar = findViewById(R.id.avatar);
        try {
            JSONObject jsonObject = new JSONObject(getIntent().getStringExtra("json"));

            fname.setText(jsonObject.getString("first_name"));
            lname.setText(jsonObject.getString("last_name"));
            email.setText(jsonObject.getString("email"));

            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(
                    DetailsPageActivity.this));
            DisplayImageOptions options;
            options = new DisplayImageOptions
                    .Builder()
                    .showStubImage(
                            R.drawable.loading)
                    .showImageForEmptyUri(
                            R.drawable.noimage)
                    .showImageOnFail(R.drawable.noimage)
                    .cacheInMemory()
                    .cacheOnDisc()
                    .build();
            imageLoader.displayImage(jsonObject.getString("avatar"), avatar, options, null);
            //Log.d("pololo",""+jsonObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}