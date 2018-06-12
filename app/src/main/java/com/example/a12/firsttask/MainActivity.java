package com.example.a12.firsttask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    Button button;
    EditText innerX0,innerY0,innerX1,innerY1;
    TextView outX, outY;
    TextView outLength;
    Spinner spinner, innerspinner;

    int inX1,inX2, inY1,inY2;
    String[] data = {"1", "2", "3", "4", "5","6", "7", "8", "9"};
    String[] data2 = {"not define","1", "2", "3", "4", "5","6", "7", "8", "9"};
    List<DPoint> list = new ArrayList();

    int dimensionOfArea = 1000;


    String resX, resY;
    public void fillingList(){
        list.add(null);//0
        list.add(new DPoint(1.0/6, 1.0/6));//1
        list.add(new DPoint(3.0/6, 1.0/6));//2
        list.add(new DPoint(5.0/6, 1.0/6));//3
        list.add(new DPoint(5.0/6, 3.0/6));//4
        list.add(new DPoint(5.0/6, 5.0/6));//5
        list.add(new DPoint(3.0/6, 5.0/6));//6
        list.add(new DPoint(1.0/6, 5.0/6));//7
        list.add(new DPoint(1.0/6, 3.0/6));//8
        list.add(new DPoint(3.0/6, 3.0/6));//9
    }

    public void initialization(){
        innerX0 = (EditText) findViewById(R.id.editText8);
        innerY0 = (EditText) findViewById(R.id.editText10);
        innerX1 = (EditText) findViewById(R.id.editText9);
        innerY1 = (EditText) findViewById(R.id.editText11);

        outX = (TextView) findViewById(R.id.textView20);
        outY = (TextView) findViewById(R.id.textView22);
        outLength = (TextView) findViewById(R.id.textView26);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout);

        fillingList();

        initialization();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinner3);
        spinner.setAdapter(adapter);
        innerspinner = (Spinner) findViewById(R.id.spinner4);
        innerspinner.setAdapter(adapter2);

        spinner.setSelection(0);
        spinner.getSelectedItemPosition();

        innerspinner.setSelection(0);
        innerspinner.getSelectedItemPosition();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
               // ( (TextView)findViewById(R.id.textView26)).setText(String.valueOf(position+1));
               // outLength.setText(String.valueOf(calculatelength()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        innerspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                //( (TextView)findViewById(R.id.textView26)).setText(String.valueOf(position));
                //outLength.setText(String.valueOf(calculatelength()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });



        button =(Button) findViewById(R.id.button11);



        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.ResLL).setVisibility(View.VISIBLE);
                DPoint center = calculatecoordinates(dimensionOfArea);
                outLength.setText(String.valueOf(calculatelength()));


               // outY.setText(String.valueOf(center.y));

                outX.setText(innerX0.getText().toString()+" "+ innerX1.getText().toString() +" "+ String.valueOf((int)center.x));
                outY.setText(innerY0.getText().toString()+" "+ innerY1.getText().toString() +" "+ String.valueOf((int)center.y));



            }
        };
        button.setOnClickListener(oclBtnOk);

    }

    public double calculatelength(){
        if(list.get(innerspinner.getSelectedItemPosition())!=null){
            return dimensionOfArea/9;
        }else {
            return dimensionOfArea/3;
        }
    }

    public DPoint calculatecoordinates(int sizeOfArea){
        Double x=0.0,y=0.0;
        int externalpos = Integer.valueOf(spinner.getSelectedItemPosition());
        int innerpos = Integer.valueOf(innerspinner.getSelectedItemPosition());

        if(list.get(innerpos)==null){
            x=sizeOfArea*list.get (externalpos+1).x;
            y=sizeOfArea*list.get(externalpos+1).y;
            return new DPoint(x,y);
        }else {
            x=(sizeOfArea*list.get(externalpos+1).x-sizeOfArea/6)+
                    list.get(innerpos).x*sizeOfArea/3;
            y=(sizeOfArea*list.get(externalpos+1).y-sizeOfArea/6)+
                    list.get(innerpos).y*sizeOfArea/3;
            return new DPoint(x,y);
        }

    }
}
