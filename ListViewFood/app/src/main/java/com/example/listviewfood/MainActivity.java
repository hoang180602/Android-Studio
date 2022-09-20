package com.example.listviewfood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"Nguyễn Hữu Hoàng 1", "Nguyễn Hữu Hoàng 2", "Nguyễn Hữu Hoàng 3","Nguyễn Hữu Hoàng 4","Nguyễn Hữu Hoàng 5", "Nguyễn Hữu Hoàng 5" };
    String mDescriptione[] = {"2050531200146", "2050531200146","2050531200146","2050531200146","2050531200146", "2050531200146"  };
    int images[] = {R.drawable.hamburger, R.drawable.kimpap, R.drawable.ramen, R.drawable.coke, R.drawable.ricechicken, R.drawable.cake };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescriptione, images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int poition, long id) {
                if(poition == 0){
                    Toast.makeText(MainActivity.this, "Nguyễn Hữu Hoàng 1", Toast.LENGTH_SHORT).show();
                }
                if(poition == 1){
                    Toast.makeText(MainActivity.this, "Nguyễn Hữu Hoàng 2", Toast.LENGTH_SHORT).show();
                }
                if(poition == 2){
                    Toast.makeText(MainActivity.this, "Nguyễn Hữu Hoàng 3", Toast.LENGTH_SHORT).show();
                }
                if(poition == 3){
                    Toast.makeText(MainActivity.this, "Nguyễn Hữu Hoàng 4", Toast.LENGTH_SHORT).show();
                }
                if(poition == 4){
                    Toast.makeText(MainActivity.this, "Nguyễn Hữu Hoàng 5", Toast.LENGTH_SHORT).show();
                }
                if(poition == 5){
                    Toast.makeText(MainActivity.this, "Nguyễn Hữu Hoàng 6", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    class MyAdapter extends ArrayAdapter<String>{

        Context context;
        String rTitle[];
        String rDescriptione[];
        int rImgs[];

        MyAdapter(Context c, String title[], String description[], int imgs[]){
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescriptione = description;
            this.rImgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater laoutinflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = getLayoutInflater().inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescriptione[position]);

            return row;
        }
    }

}