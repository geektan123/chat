package com.example.android.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;    ListView listview;

    FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    DatabaseReference mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Students");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton send = (ImageButton) findViewById(R.id.send);
        editText = (EditText) findViewById(R.id.editTextTextPersonName);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                insertStudentData();
            }
        });
        get_data();
    }

    public void insertStudentData() {
        String name = "Tanay";
        String editor = editText.getText().toString();
        Student students = new Student(name, editor);
        mMessagesDatabaseReference.push().setValue(students);
        Toast.makeText(MainActivity.this, "data inserted", Toast.LENGTH_SHORT).show();

    }

public void get_data(){
        ArrayList<String> list=new ArrayList<>();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
    listview=findViewById(R.id.listview);
    listview.setAdapter(adapter);
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Students");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    list.add(dataSnapshot.child("editor").getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}