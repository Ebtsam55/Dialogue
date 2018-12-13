package com.example.microsoft.dialogue;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity //implements Sender
{

    Dialog dialog;
    TextView Gender;
    TextView select;
    TextView Age;
    TextView seekBarText;
    DialogFragment dialogFragment;
    boolean isAdded; //Check if Fragment added to the fragment manager


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gender = (TextView) findViewById(R.id.gender);
        select = (TextView) findViewById(R.id.select);
        Age = (TextView) findViewById(R.id.age);
        seekBarText = (TextView) findViewById(R.id.seek_bar_progress);
        Log.i("option7Status", select.getText().toString());

        dialogFragment = new MyCustomDialog();
        Log.i("Statuss", "New instance created");


        if (savedInstanceState == null) {
            Log.i("Statuss", "Saved Nothing!");

        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("option", "OnActivity");
        super.onSaveInstanceState(outState);
        Log.i("Statuss", "Saving state..");

        //Save the whole Fragment state in the Activity saved instance to restore when Fragment killed
        // if there is a Fragment added to the fragment manager
        if (isAdded) {
            getSupportFragmentManager().putFragment(outState, "MyCustomDialog", dialogFragment);
        }


        outState.putString("select_txt", select.getText().toString());
        outState.putString("age_txt", Age.getText().toString());
        outState.putString("gender_txt", Gender.getText().toString());
        outState.putString("seekBar_txt", seekBarText.getText().toString());
        outState.putBoolean("isAdded", isAdded);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.i("Statuss", "Restoring state..");

        select.setText(savedInstanceState.getString("select_txt"));
        Age.setText(savedInstanceState.getString("age_txt"));
        Gender.setText(savedInstanceState.getString("gender_txt"));
        seekBarText.setText(savedInstanceState.getString("seekBar_txt"));
        isAdded = savedInstanceState.getBoolean("isAdded");

        //Retrieve Fragment State..
        // if there is a Fragment added to the fragment manager
        if (isAdded) {
            dialogFragment = (DialogFragment) getSupportFragmentManager().getFragment(savedInstanceState, "MyCustomDialog");
        }


    }

    @Override
    //Remmeber it is outside oncreate(Bundle savedInstanceState)function;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.tune_icon:
                Log.i("Statuss", "Item Selected");
                isAdded = true;
                showDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void showDialog() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        int backStackCount = manager.getBackStackEntryCount();

        //Prevent Backstack saving multiple instances of the fragment
        if (backStackCount < 1) {
            transaction.addToBackStack(null);
            transaction.add(dialogFragment, "MyCustomDialog").commit();
        } else {
            manager.popBackStack();
            transaction.addToBackStack(null);
            transaction.add(dialogFragment, "MyCustomDialog").commit();
        }

    }


}
