package com.example.patients;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner s =  (Spinner) findViewById(R.id.martial);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.martial_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        Button b1 = (Button) findViewById(R.id.submit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e1 = (EditText) findViewById(R.id.name);
                EditText e2 = (EditText) findViewById(R.id.address);
                EditText e3 = (EditText) findViewById(R.id.age);
                DatePicker d = (DatePicker) findViewById(R.id.simpleDatePicker);
                TimePicker t = (TimePicker) findViewById(R.id.time);
                RadioGroup rg = (RadioGroup) findViewById(R.id.gender);
                RadioButton male = (RadioButton) findViewById(R.id.male);
                RadioButton female = (RadioButton) findViewById(R.id.female);
                int ch = rg.getCheckedRadioButtonId();
                RadioButton selected = (RadioButton) findViewById(ch);

               person p = new person();
               p.name =  e1.getText().toString();
               p.address = e2.getText().toString();
               p.age = e3.getText().toString();
               p.d = d;
               p.t = t;
               p.gender = selected.getText().toString();
                Spinner mySpinner = (Spinner) findViewById(R.id.martial);
                //String text = mySpinner.getSelectedItem().toString();
               //Toast.makeText(getApplicationContext(), text,Toast.LENGTH_LONG).show();

            }
        });
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
