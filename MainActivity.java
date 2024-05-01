package com.example.firebaseconnection;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText id = findViewById(R.id.et_id);
        EditText message = findViewById(R.id.et_message);
        Button send_btn = findViewById(R.id.btn_send);
        Button receive_btn = findViewById(R.id.btn_receive);
        Button update_btn = findViewById(R.id.btn_update);
        Button delete_btn = findViewById(R.id.btn_delete);


        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_str = id.getText().toString();
                String message_str = message.getText().toString();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference(id_str);
                databaseReference.setValue(message_str);
                Toast.makeText(MainActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
            }
        });

        receive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_str = id.getText().toString();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference(id_str);
                databaseReference.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String message_str = task.getResult().getValue().toString();
                        Toast.makeText(MainActivity.this, message_str, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_str = id.getText().toString();
                String message_str = message.getText().toString();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference(id_str);
                databaseReference.setValue(message_str);
                Toast.makeText(MainActivity.this, "Message updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_str = id.getText().toString();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference(id_str);
                databaseReference.removeValue();
                Toast.makeText(MainActivity.this, "Message deleted", Toast.LENGTH_SHORT).show();
            }
        });




    }

}
