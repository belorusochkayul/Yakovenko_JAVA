package com.example.firstandroid;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {
    ProgressBar progressBar;
    EditText urlEditText;
    Button buttonStartDownload;
    ImageView userImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        userImageview = (ImageView) findViewById(R.id.imageView);
        urlEditText = (EditText) findViewById(R.id.url);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        buttonStartDownload = (Button) findViewById(R.id.downloadImage);

        buttonStartDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInputUrl = urlEditText.getText().toString();
                Picasso
                        .get()
                        .load(userInputUrl)
                        .transform(new PicassoCircleTransformation())
                        .into(userImageview, new Callback() {
                            @Override
                            public void onSuccess() {
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });

                InputMethodManager imm;
                imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(buttonStartDownload.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        });

    }
}