package com.example.a12.firsttask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private Button button;
    private EditText innerX0, innerY0, innerX1, innerY1;
    private TextView outX, outY;
    private TextView outLength;
    private Spinner externalSpinner, innerSpinner;

    final private String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    final private String[] data2 = {"not define", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private List<DPoint> list = new ArrayList();

    final private int dimensionOfArea = 1000;

    private void fillingList() {
        list.add(null);//0
        list.add(new DPoint(1.0 / 6, 1.0 / 6));//1
        list.add(new DPoint(3.0 / 6, 1.0 / 6));//2
        list.add(new DPoint(5.0 / 6, 1.0 / 6));//3
        list.add(new DPoint(5.0 / 6, 3.0 / 6));//4
        list.add(new DPoint(5.0 / 6, 5.0 / 6));//5
        list.add(new DPoint(3.0 / 6, 5.0 / 6));//6
        list.add(new DPoint(1.0 / 6, 5.0 / 6));//7
        list.add(new DPoint(1.0 / 6, 3.0 / 6));//8
        list.add(new DPoint(3.0 / 6, 3.0 / 6));//9
    }

    private void initialization() {
        innerX0 = (EditText) findViewById(R.id.editText8);
        innerY0 = (EditText) findViewById(R.id.editText10);
        innerX1 = (EditText) findViewById(R.id.editText9);
        innerY1 = (EditText) findViewById(R.id.editText11);
        outX = (TextView) findViewById(R.id.textView20);
        outY = (TextView) findViewById(R.id.textView22);
        outLength = (TextView) findViewById(R.id.textView26);
    }

    private double calculateLength(int sizeOfArea) {
        if (list.get(innerSpinner.getSelectedItemPosition()) != null) {
            return sizeOfArea / 9;
        } else {
            return sizeOfArea / 3;
        }
    }

    private DPoint calculateCoordinates(int sizeOfArea) {
        Double x = 0.0, y = 0.0;
        int externalPos = Integer.valueOf(externalSpinner.getSelectedItemPosition());
        int innerPos = Integer.valueOf(innerSpinner.getSelectedItemPosition());

        if (list.get(innerPos) == null) {
            x = sizeOfArea * list.get(externalPos + 1).getX();
            y = sizeOfArea * list.get(externalPos + 1).getY();
            return new DPoint(x, y);
        } else {
            x = (sizeOfArea * list.get(externalPos + 1).getX() - sizeOfArea / 6) +
                    list.get(innerPos).getX() * sizeOfArea / 3;
            y = (sizeOfArea * list.get(externalPos + 1).getY() - sizeOfArea / 6) +
                    list.get(innerPos).getY() * sizeOfArea / 3;
            return new DPoint(x, y);
        }

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

        externalSpinner = (Spinner) findViewById(R.id.spinner3);
        externalSpinner.setAdapter(adapter);
        innerSpinner = (Spinner) findViewById(R.id.spinner4);
        innerSpinner.setAdapter(adapter2);

        externalSpinner.setSelection(0);
        innerSpinner.setSelection(0);

        button = (Button) findViewById(R.id.button11);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.ResLL).setVisibility(View.VISIBLE);
                DPoint center = calculateCoordinates(dimensionOfArea);
                outLength.setText(String.valueOf(calculateLength(dimensionOfArea)));
                outX.setText(innerX0.getText().toString() + " " + innerX1.getText().toString() + " " + String.valueOf((int) center.getX()));
                outY.setText(innerY0.getText().toString() + " " + innerY1.getText().toString() + " " + String.valueOf((int) center.getY()));

            }
        });

    }

   }
