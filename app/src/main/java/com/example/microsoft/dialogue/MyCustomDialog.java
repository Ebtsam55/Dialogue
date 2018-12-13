package com.example.microsoft.dialogue;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.warkiz.widget.IndicatorSeekBar;


public class MyCustomDialog extends DialogFragment {

    // private static final String TAG = "MyCustomDialog";

    private Button ok_button;
    private Button cancel_button;
    private CheckBox option1;
    private CheckBox option2;
    private CheckBox option3;
    private RadioButton male;
    private RadioButton female;
    private IndicatorSeekBar seekBar;
    private Spinner spinner;
    private String itemSelected = "default";
    private Boolean saveOP1 = false;
    private Boolean saveOP2 = false;
    private Boolean saveOP3 = false;
    private Boolean saveMale = false;
    private Boolean saveFemale = false;
    private int savedSeekValue = 0;

    View view;
    Sender sender;

    @NonNull
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("option", "Iam on onCreate");
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog, container, false);
        Log.i("option", "Iam on onCreateView");

        //init Views
        initViews();


        seekBar.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {

            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String tickBelowText, boolean fromUserTouch) {

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                savedSeekValue = seekBar.getProgress();

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSelected = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).select.setText("");
                if (option1.isChecked()) {
                    saveOP1 = true;
                    ((MainActivity) getActivity()).select.append("option1");
                }


                if (option2.isChecked()) {
                    saveOP2 = true;
                    ((MainActivity) getActivity()).select.append("option2 ");
                }

                if (option3.isChecked()) {
                    saveOP3 = true;
                    ((MainActivity) getActivity()).select.append("option3 ");
                }

                if (!(option1.isChecked())) {
                    if (!(option2.isChecked())) {
                        if (!(option3.isChecked())) {
                            ((MainActivity) getActivity()).select.setText("default");


                        }
                    }
                }


                if (male.isChecked()) {
                    saveMale = true;
                    ((MainActivity) getActivity()).Gender.setText("male");

                } else if (female.isChecked()) {
                    ((MainActivity) getActivity()).Gender.setText("female");

                    saveFemale = true;
                }

                if (!(male.isChecked()) && !(female.isChecked())) {
                    ((MainActivity) getActivity()).Gender.setText("default");

                }


                ((MainActivity) getActivity()).seekBarText.setText("seek bar progress =" + String.valueOf(savedSeekValue));


                ((MainActivity) getActivity()).Age.setText(itemSelected);


                getDialog().dismiss();

            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //   sender=(Sender)getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("option", "Iam on onResume");


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i("optionSaveInstanceState", "Saving States..");
        outState.putBoolean("op1", saveOP1);
        outState.putBoolean("op2", saveOP2);
        outState.putBoolean("op3", saveOP3);
        outState.putBoolean("male", saveMale);
        outState.putBoolean("female", saveFemale);


    }

    @Override
    public void onDestroy() {
        Log.i("option", "Iam On Destroy");

        super.onDestroy();

    }

    @Override
    public void onPause() {
        Log.i("option", "Iam onPause");
        super.onPause();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle outState) {
        super.onActivityCreated(outState);

        if (outState != null) {
            Log.i("option", "restoring State");
            option1.setChecked(outState.getBoolean("op1"));
            option2.setChecked(outState.getBoolean("op2"));
            option3.setChecked(outState.getBoolean("op3"));
            male.setChecked(outState.getBoolean("male"));
            female.setChecked(outState.getBoolean("female"));
        }
    }

    public void initViews() {
        ok_button = view.findViewById(R.id.ok_button);
        cancel_button = view.findViewById(R.id.cancel_button);
        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
        option3 = view.findViewById(R.id.option3);
        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        seekBar = view.findViewById(R.id.seek_bar);
        spinner = view.findViewById(R.id.age_spinner);

    }


}

