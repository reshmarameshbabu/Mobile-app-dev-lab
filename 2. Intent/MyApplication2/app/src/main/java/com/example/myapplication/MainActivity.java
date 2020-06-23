package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    Details details = new Details();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.marital_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                /*
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Details Submitted", Toast.LENGTH_LONG);
                toast.show();*/
                EditText e1 = (EditText) findViewById(R.id.editText2);
                details.name = e1.getText().toString();

                EditText e2 = (EditText) findViewById((R.id.editText3));
                details.address = e2.getText().toString();

                EditText e3 = (EditText) findViewById((R.id.editText4));
                details.age = e3.getText().toString();

                DatePicker d = (DatePicker) findViewById((R.id.simpleDatePicker));
                Integer day = (Integer) d.getDayOfMonth();
                Integer month = (Integer) (d.getMonth() + 1);
                Integer year = (Integer) d.getYear();
                details.dob = day.toString() + "/" + month.toString() + "/" + year.toString();

                RadioGroup r = (RadioGroup) findViewById(R.id.RadioGroup);
                Integer rid = (Integer)r.getCheckedRadioButtonId();

                RadioButton r1 = (RadioButton) findViewById(rid);
                details.gender = r1.getText().toString();

                final Spinner s = (Spinner) findViewById(R.id.spinner);
                String text = s.getSelectedItem().toString();
                details.marital = text;

                EditText e4 = (EditText) findViewById((R.id.editText6));
                details.phone = e4.getText().toString();

                TimePicker t = (TimePicker) findViewById(R.id.timePicker);
                Integer hour = (Integer) t.getHour();
                Integer min = (Integer) t.getMinute();
                details.time = hour.toString() + ":" + min.toString();

                CheckBox c1 = (CheckBox) findViewById(R.id.simpleCheckBox1);
                int flag = 0;
                String t1 = "";
                if(c1.isChecked())
                    t1 = c1.getText().toString();
                else
                    flag++;

                CheckBox c2 = (CheckBox) findViewById(R.id.simpleCheckBox2);
                String t2 = "";
                if(c2.isChecked())
                    t2 = c2.getText().toString();
                else
                    flag++;

                if(flag != 2)
                    details.addiction = t1 + " " + t2;
                else
                    details.addiction = "None";

                /*
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, (CharSequence) details.time, Toast.LENGTH_LONG);
                toast.show();*/

                Context context = getApplicationContext();
                Intent intent = new Intent(context, Details.class);
                intent.putExtra("Details", details);
                startActivity(intent);



            }
        });

    }

    private void createTable() {

    }
}
