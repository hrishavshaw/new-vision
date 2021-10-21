package com.example.newvision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class GalleryActivity extends AppCompatActivity {
    GridView simpleGrid;
    int logos[] = {R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.images1,
    R.drawable.pic1,R.drawable.pic2,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        simpleGrid = (GridView)findViewById(R.id.simpleGridView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),logos);
        simpleGrid.setAdapter(customAdapter);
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GalleryActivity.this,SecondActivity.class);
                intent.putExtra("image",logos[position]);
                startActivity(intent);
            }
        });
    }
}
