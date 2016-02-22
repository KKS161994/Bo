package in.silive.bo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.astuetz.PagerSlidingTabStrip;
import com.octo.android.robospice.SpiceManager;
import in.silive.bo.R;
import in.silive.bo.adapter.SampleFragmentPagerAdapter;
import in.silive.bo.fragment.SearchPaper;

/**
 * Created by kartikey on 22/02/16.
 */
public class NavigationMain extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        SearchPaper.setSpiceManager(getSpiceManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));
        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
    }
    public static SpiceManager getSpicemanager(){
        return getSpicemanager();
    }
}
