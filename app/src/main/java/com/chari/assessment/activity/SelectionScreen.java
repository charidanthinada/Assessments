package com.chari.assessment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chari.assessment.R;

import java.util.ArrayList;
import java.util.List;

public class SelectionScreen extends AppCompatActivity {
    LinearLayout ll_our_records_camera,ll_our_records_gallery;
    Intent i;
    int PICK_IMAGE_MULTIPLE = 1;
    int PICK_IMAGE_CAMERA = 2;
    List<String> imagesEncodedList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_screen);
        ll_our_records_camera=(LinearLayout) findViewById(R.id.ll_our_records_camera);
        ll_our_records_gallery=(LinearLayout) findViewById(R.id.ll_our_records_gallery);

        ll_our_records_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, PICK_IMAGE_CAMERA);


            }
        });
        ll_our_records_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK  && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null)
                {

                    Uri mImageUri=data.getData();
                    if(mImageUri!=null){
                        // Here We need to call  the API for Uploading the image;
                        Toast.makeText(this, "Image Upload successfully",  Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(this, "Image Upload Failed",  Toast.LENGTH_LONG).show();




                }
                else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);


                        }
                        if(mArrayUri.size()>0){
                             // Here We need to call  the API for Uploading the image;
                            Toast.makeText(this, "Image Upload successfully",  Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(this, "Image Upload Failed",  Toast.LENGTH_LONG).show();

                    }
                }
            }
            else if (requestCode == PICK_IMAGE_CAMERA && resultCode == RESULT_OK  && null != data) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                if(bitmap!=null){

                    // Here We need to call  the API for Uploading the image;
                    Toast.makeText(this, "Image Upload successfully",  Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(this, "Image Upload Failed",  Toast.LENGTH_LONG).show();


            }
            else {
                Toast.makeText(this, "You haven't picked Image",  Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}