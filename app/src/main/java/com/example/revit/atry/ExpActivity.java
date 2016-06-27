package com.example.revit.atry;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ExpActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private static RadioGroup myRadioGroup;
    private static Button skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        skip = (Button) findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ExpActivity.this, LoginActivity.class);//move to the explition window
                    startActivity(i);
                }
    });


    //checked radio button.
        RadioButton fb = (RadioButton) findViewById(R.id.radio5);
        fb.setChecked(true);
        myRadioGroup = (RadioGroup) findViewById(R.id.radioGroupExp);
        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checked) {
                RadioButton rb;
                switch (checked) {
                    case 1:
                        rb = (RadioButton) findViewById(R.id.radio5);
                        rb.setChecked(true);
                        break;
                    case 2:
                        rb = (RadioButton) findViewById(R.id.radio4);
                        rb.setChecked(true);
                        break;
                    case 3:
                        rb = (RadioButton) findViewById(R.id.radio3);
                        rb.setChecked(true);
                        break;
                    case 4:
                        rb = (RadioButton) findViewById(R.id.radio2);
                        rb.setChecked(true);
                        break;
                    case 5:
                        rb = (RadioButton) findViewById(R.id.radio1);
                        rb.setChecked(true);
                        break;
                    default:
                        break;
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 3:
                        skip.setText(getString(R.string.skip));
                        break;
                    case 4:
                        skip.setText(getString(R.string.contin));
                        break;
                    default:
                        break;
                }
                myRadioGroup.check(position+1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exp, menu);
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
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        int position;
        boolean isCurrent;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_exp, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setTextSize(30);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.section_image);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    textView.setText(getString(R.string.section1_format));
                    imageView.setImageResource(R.drawable.hi);
                    break;
                case 2:
                    textView.setText(getString(R.string.section2_format));
                    imageView.setImageResource(R.drawable.what_we_have);
                    break;
                case 3:
                    textView.setText(getString(R.string.section3_format));
                    imageView.setImageResource(R.drawable.sign_up);
                    break;
                case 4:
                    textView.setText(getString(R.string.section4_format));
                    imageView.setImageResource(R.drawable.chatbubbles);
                    break;
                case 5:
                    textView.setText(getString(R.string.section5_format));
                    imageView.setImageResource(R.drawable.chatting);
                    break;

            }
            return rootView;
        }

     //   public void setPosition(int position) {
       //     this.position = position;
        //}
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return ExpActivity.PlaceholderFragment.newInstance(position + 1);

        //    PlaceholderFragment p = new PlaceholderFragment();
          //  p.setPosition(position);
            //return p;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
                case 4:
                    return "SECTION 5";
            }
            return null;
        }
    }
}