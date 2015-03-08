package com.swalloworks.rakurakukakeibo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTabHost host = (FragmentTabHost)findViewById(android.R.id.tabhost);
        host.setup(this, getSupportFragmentManager(), R.id.content);

        TabHost.TabSpec tabSpec1 = host.newTabSpec("tab1");
        Button button1 = new Button(this);
        button1.setWidth(60);
        button1.setBackgroundResource(R.drawable.tab_entry);
        tabSpec1.setIndicator(button1);
        Bundle bundle1 = new Bundle();
        bundle1.putString("name", "Tab1");
        host.addTab(tabSpec1, SampleFragment.class, bundle1);

        TabHost.TabSpec tabSpec2 = host.newTabSpec("tab2");
        Button button2 = new Button(this);
        button2.setBackgroundResource(R.drawable.tab_analysis);
        tabSpec2.setIndicator(button2);
        Bundle bundle2 = new Bundle();
        bundle2.putString("name", "Tab2");
        host.addTab(tabSpec2, SampleFragment.class, bundle2);

        TabHost.TabSpec tabSpec3 = host.newTabSpec("tab3");
        Button button3 = new Button(this);
        button3.setBackgroundResource(R.drawable.tab_setting);
        tabSpec3.setIndicator(button3);
        Bundle bundle3 = new Bundle();
        bundle3.putString("name", "Tab3");
        host.addTab(tabSpec3, SampleFragment.class, bundle3);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public static class SampleFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            textView.setText(getArguments().getString("name"));

            return textView;
        }

    }
}
