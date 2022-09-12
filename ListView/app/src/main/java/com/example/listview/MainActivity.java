package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView lvCar;
    ImageButton imgBtnAdd;
    Button btnDelete, btnUpdate;
    EditText editAddCar;
    ArrayList<String> arrayCourse;

    int map = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCar = (ListView) findViewById(R.id.listViewCar);
        imgBtnAdd = (ImageButton) findViewById(R.id.imgButtonAdd);
        btnDelete = (Button) findViewById(R.id.buttonDelete);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        editAddCar = (EditText) findViewById(R.id.editTextAdd);
        arrayCourse = new ArrayList<>();

        arrayCourse.add("BMW1");
        arrayCourse.add("BMW2");
        arrayCourse.add("BMW3");
        arrayCourse.add("BMW4");

        ArrayAdapter adapter = new ArrayAdapter(
          MainActivity.this,
          android.R.layout.simple_expandable_list_item_1,
          arrayCourse
        );

        lvCar.setAdapter(adapter);

        imgBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String car = editAddCar.getText().toString();
                arrayCourse.add(car);
                adapter.notifyDataSetChanged();
            }
        });

        lvCar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editAddCar.setText(arrayCourse.get(i));
                map = i;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                arrayCourse.set(map, editAddCar.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayCourse.remove(map);
                editAddCar.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        lvCar.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent a = new Intent(MainActivity.this, MainActivity2.class);
                a.putExtra("Key_1", arrayCourse.get(i));
                startActivity(a);
                return false;
            }
        });
    }
}