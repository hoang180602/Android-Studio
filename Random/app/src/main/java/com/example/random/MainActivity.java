package com.example.random;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvCar;
    Button btnAdd, btnEdit, btnDelete;
    EditText editCar;
    ArrayList<String> arrayCourse;

    int map = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCar = (ListView) findViewById(R.id.listView);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editCar = (EditText) findViewById(R.id.editTextCar);
        arrayCourse = new ArrayList<>();

        arrayCourse.add("BMW");
        arrayCourse.add("TOYOTA");
        arrayCourse.add("MISUBISI");
        arrayCourse.add("CX-5");
        arrayCourse.add("FORD");

        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayCourse
        );

        lvCar.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String car = editCar.getText().toString();
                arrayCourse.add(car);
                adapter.notifyDataSetChanged();
            }
        });

        lvCar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editCar.setText(arrayCourse.get(i));
                map = i;

             }
         });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayCourse.remove(map);
                editCar.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayCourse.set(map, editCar.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        lvCar.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent a = new Intent(MainActivity.this, BMW_infomation.class);
                    a.putExtra("Key_1", arrayCourse.get(i));
                    startActivity(a);
                return false;
            }
        });
    }
}