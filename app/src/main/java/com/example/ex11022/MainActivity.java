package com.example.ex11022;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * MainActivity is the main activity of the application.
 * This application saves and displays text from an internal file and includes three buttons:
 * Save, Reset, and Exit.
 * Each button performs a different operation on the file and the user interface.
 *
 * @author eliya bitton eb3831@bs.amalnet.k12.il
 * @version 2.0
 * @since 30/3/2025
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener
{
    private final String FILENAME = "inttest.txt";

    FileOutputStream fOS;
    OutputStreamWriter oSW;
    BufferedWriter bW;

    FileInputStream fIS;
    InputStreamReader iSR;
    BufferedReader bR;
    StringBuilder sB;


    Intent gi;
    EditText etInput;
    TextView tv;
    String input;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.etInput);
        tv = findViewById(R.id.tv);

        try
        {
            fIS = openFileInput(FILENAME);
            iSR = new InputStreamReader(fIS);
            bR = new BufferedReader(iSR);
            sB = new StringBuilder();

            String line = bR.readLine();
            while (line != null)
            {
                sB.append(line).append('\n');
                line = bR.readLine();
            }

            bR.close();
            iSR.close();
            fIS.close();

            tv.setText(sB.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Creates the options menu.
     * @param menu The menu to be created.
     * @return true if the menu is created successfully.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Handles menu item selections.
     * @param menuItem The selected menu item.
     * @return true if the menu item is handled successfully.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem)
    {
        String st = menuItem.getTitle().toString();
        if (st.charAt(0) == 'C')
        {
            gi = new Intent(this, Credits.class);
            startActivity(gi);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /**
     * Handles the save button click event.
     * appends the entered text from the input field to the file,
     * preserving the existing content and adding the new text at the end.
     * The text is also followed by a new line for clarity.
     *
     * @param view The view that triggered the click event
     */
    public void save(View view)
    {
        input = etInput.getText().toString();
        try
        {
            fOS = openFileOutput(FILENAME, MODE_APPEND);
            oSW = new OutputStreamWriter(fOS);
            bW = new BufferedWriter(oSW);

            bW.write(input + "\n");
            bW.close();
            oSW.close();
            fOS.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        tv.setText(tv.getText().toString() + input + "\n");
    }

    /**
     * Handles the reset button click event.
     * Resets all data that was entered and clears the file.
     *
     * @param view The view that triggered the click event
     */
    public void reset(View view)
    {
        try
        {
            FileOutputStream fOS = openFileOutput(FILENAME, MODE_PRIVATE);
            fOS.write("".getBytes());
            fOS.close();

            etInput.setText("");
            tv.setText("");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Handles the exit button click event.
     * appends the entered text from the input field to the file, displays the saved text
     * in the TextView, and exits the activity.
     *
     * @param view The view that triggered the click event
     */
    public void exit(View view)
    {
        String inputText = etInput.getText().toString();
        try
        {
            FileOutputStream fOS = openFileOutput(FILENAME, MODE_APPEND);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);

            bW.write(inputText);
            bW.newLine();
            bW.close();
            oSW.close();
            fOS.close();

        }

        catch (IOException exception)
        {
            exception.printStackTrace();
        }

        StringBuilder sB = new StringBuilder();
        try
        {
            FileInputStream fIS = openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            String line = bR.readLine();
            while (line != null)
            {
                sB.append(line).append('\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            fIS.close();
        }

        catch (IOException exception)
        {
            exception.printStackTrace();
        }

        tv.setText(sB.toString());
        finish();
    }
}