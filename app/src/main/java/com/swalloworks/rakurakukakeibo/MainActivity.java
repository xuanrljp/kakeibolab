package com.swalloworks.rakurakukakeibo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    FragmentTabHost host;


    //Tab选项卡的文字
    private String[] textContents = {"记帐本", "收据", "图表分析", "设置"};

    private final TabItem[] tabItems = {
                                  new TabItem("记帐",R.drawable.tab_indicator_img_entry)
                                  , new TabItem("收据",R.drawable.tab_indicator_img_gallery)
                                  , new TabItem("图表分析",R.drawable.tab_indicator_img_analysis)
                                  , new TabItem("设置",R.drawable.tab_indicator_img_setting)
                                  };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        host = (FragmentTabHost)findViewById(android.R.id.tabhost);
        host.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for(int i = 0; i < 4; i++) {
            TabHost.TabSpec tabSpec = host.newTabSpec("tab" + i);
            tabSpec.setIndicator(getTabItemView(tabItems[i]));
            Bundle bundle = new Bundle();
            bundle.putString("name", textContents[i]);
            host.addTab(tabSpec, TabContentFragment.class, bundle);
        }


        //host.getTabWidget().setDividerDrawable(null);
        host.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        //host.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tile);

        //注册Listener
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int i = host.getCurrentTab();
                Log.i("***Click tab no.", "------" + i);

            }
        });
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
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(TabItem item){
        View view = LayoutInflater.from(this).inflate(R.layout.tab_indicator_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(item.imageRes);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(item.title);
        //textView.setHighlightColor(Color.argb(255, 255, 153, 51));

//        if(index == 0) {
//            imageView.setColorFilter(Color.argb(255, 255, 153, 51), PorterDuff.Mode.SRC_ATOP);
//            textView.setTextColor(Color.argb(255, 255, 153, 51));
//        }

        return view;
    }

    static class TabItem {
        private final String title;
        private final int imageRes;

        TabItem(String title, int imageRes) {
            this.title = title;
            this.imageRes = imageRes;
        }
    }


    public static class TabContentFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            textView.setText(getArguments().getString("name"));

            return textView;
        }

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

}
