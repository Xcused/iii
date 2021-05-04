package com.example.iii;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iii.database.Controller;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class AddFriend extends AppCompatActivity {

    private TextInputEditText tNama,tTelpon;
    private Button simpanBtn;
    String nm,tlp;
    Controller controller = new Controller(this);

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_baru);

        tNama = (TextInputEditText)findViewById(R.id.tietNama);
        tTelpon = (TextInputEditText)findViewById(R.id.tietTelpon);
        simpanBtn = (Button)findViewById(R.id.buttonSave);


        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tNama.getText().toString().equals("") || tTelpon.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Data Belum komplit" ,Toast.LENGTH_SHORT).show();
                }else {

                    nm = tNama.getText().toString();
                    tlp = tTelpon.getText().toString();

                    HashMap<String,String> qvalue = new HashMap<>();
                    qvalue.put("nama",nm);
                    qvalue.put("telpon",tlp);

                    controller.insertData(qvalue);
                    callHome();
                }
            }
        });
    }

    public void callHome(){
        Intent intent = new Intent(AddFriend.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
