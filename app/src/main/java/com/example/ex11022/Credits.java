package com.example.ex11022;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * The Credits Activity class represents the screen that displays the credits or information about the app.
 * It is an activity that is opened from the main screen when the user selects the "Credits" option from the menu.
 * This class handles the creation of the options menu and the action when a menu item is selected.
 *
 * @author eliya bitton eb3831@bs.amalnet.k12.il
 * @version 2.0
 * @since 30/3/2025
 */
public class Credits extends AppCompatActivity implements View.OnCreateContextMenuListener
{
    Intent gi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        gi = getIntent();
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
        if (st.charAt(0) == 'M')
        {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}