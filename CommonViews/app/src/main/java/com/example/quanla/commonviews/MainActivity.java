package com.example.quanla.commonviews;

import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString() ;
    private Spinner spMonth;
    private Spinner spDay;
    private Spinner spYear;
    private Button btnTest;
    private CheckBox chFA;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private RadioButton rbUndefined;
    private RatingBar rtbSimple;
    private SeekBar sbSimple;
    private TextView txtSeekbar;
    private SearchView svSimple;
    private ProgressBar pbSimple;
    private RadioButton rbA;
    private RadioButton rbB;
    private EditText etName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getReferrence();
        setUI();
        addListeners();

    }

    private void addListeners() {


        chFA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, String.format("chFA.onCheckedChanged", chFA.isChecked() ));
            }
        });

        sbSimple.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, String.format("Seekbar : onProgressChanged: %s, %s", progress, fromUser));
                txtSeekbar.setText(String.format("%s/%s", progress, sbSimple.getMax()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch");
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, String.format("%s:%s", chFA.getId(), chFA.isChecked() ));


                Log.d(TAG, rbMale.isChecked()?"Male":rbFemale.isChecked()?"Female":"Undefined");

                Log.d(TAG, String.format("rating %s", rtbSimple.getRating() ));

                Log.d(TAG, String.format("seekbar: %s", sbSimple.getProgress() ));

                sbSimple.setProgress(sbSimple.getProgress()+10);

                svSimple.clearFocus();
                svSimple.setQuery("", false);
                svSimple.setIconified(true);

                pbSimple.setProgress(pbSimple.getProgress()+10);

                if(rbB.isChecked()){
                    Toast.makeText(MainActivity.this, "Good", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, "Noob", Toast.LENGTH_SHORT).show();
            }
        });

        chFA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, String.format("%s:%s", chFA.getId(), chFA.isChecked() ));
            }
        });

        svSimple.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, String.format("SeachView: onQueryTextChange", newText));
                return false;
            }
        });
    }

    public void getReferrence(){
        spMonth = (Spinner) this.findViewById(R.id.sp_month);
        spDay = (Spinner) this.findViewById(R.id.sp_day);
        spYear = (Spinner) this.findViewById(R.id.sp_year);
        btnTest = (Button) this.findViewById(R.id.btn_test);
        chFA = (CheckBox) this.findViewById(R.id.ch_FA);
        rbMale = (RadioButton) this.findViewById(R.id.rb_male);
        rbFemale = (RadioButton) this.findViewById(R.id.rb_female);
        rbUndefined = (RadioButton) this.findViewById(R.id.rb_undefined);
        rtbSimple = (RatingBar) this.findViewById(R.id.rtb_simple);
        sbSimple = (SeekBar) this.findViewById(R.id.sb_simple);
        txtSeekbar = (TextView) this.findViewById(R.id.txt_seekbar);
        svSimple = (SearchView) this.findViewById(R.id.sv_simple);
        pbSimple= (ProgressBar) this.findViewById(R.id.pb_simple);
        rbA = (RadioButton) this.findViewById(R.id.rb_A);
        rbB = (RadioButton) this.findViewById(R.id.rb_B);
        etName = (EditText) this.findViewById(R.id.et_name);
    }

    public void setUI(){
        String[] Month = new String[]{
          "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"

        };

        ArrayAdapter<String> monthArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                Month
        );

        Vector<Integer> Day = new Vector<>();
        for(int i=1; i<=31; i++){
            Day.add(i);
        }

        ArrayAdapter<Integer> dayArrayAdapter = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_spinner_item,
                Day
        );

        Vector<Integer> Year = new Vector<>();
        for(int i=1994; i<=2000; i++){
            Year.add(i);
        }

        ArrayAdapter<Integer> yearArrayAdapter = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_spinner_item,
                Year
        );

        spMonth.setAdapter(monthArrayAdapter);
        spDay.setAdapter(dayArrayAdapter);
        spYear.setAdapter(yearArrayAdapter);


        rbFemale.setChecked(true);

    }
}
