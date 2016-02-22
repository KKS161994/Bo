package in.silive.bo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.silive.bo.R;

/**
 * Created by kartikey on 22/02/16.
 */
public class DownloadedPapers extends Fragment{
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       rootView=inflater.inflate(R.layout.downloaded_papers,container,false);
        return rootView;

    }
}
