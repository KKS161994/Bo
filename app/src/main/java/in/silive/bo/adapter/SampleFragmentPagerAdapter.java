package in.silive.bo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;

import in.silive.bo.R;
import in.silive.bo.TitleFragment;
import in.silive.bo.fragment.DownloadedPapers;
import in.silive.bo.fragment.SearchPaper;

/**
 * Created by kartikey on 22/02/16.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Tab1", "Tab2", "Tab3"};
    private int tabIcons[] = {R.drawable.cross};

    public SampleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new SearchPaper();
            case 1:
                return new DownloadedPapers();

        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    @Override
    public int getPageIconResId(int position) {
        return tabIcons[0];
    }
}