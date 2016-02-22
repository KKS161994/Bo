package in.silive.bo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import in.silive.bo.R;
import in.silive.bo.activity.NavigationMain;
import in.silive.bo.model.PaperModel;
import in.silive.bo.network.PaperRequest;

/**
 * Created by kartikey on 22/02/16.
 */
public class SearchPaper extends Fragment{
   private View rootView;
    Button button;
    PaperRequest paperRequest;
    public static SpiceManager spiceManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.search_paper,container,false);

        button= (Button) rootView.findViewById(R.id.searchPaperButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             paperRequest=new PaperRequest("data");
               spiceManager.execute(paperRequest,"Paper", DurationInMillis.ONE_MINUTE,new ExecutingPaperRequest());
            }
        });
        return rootView;
    }

    public static void setSpiceManager(SpiceManager spiceManager) {
        SearchPaper.spiceManager = spiceManager;
    }

    public class ExecutingPaperRequest implements RequestListener<PaperModel.PapersList> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(getActivity(),"Request Failed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(PaperModel.PapersList paperModels) {
        Toast.makeText(getActivity(),"Request Succes with size "+paperModels.size(),Toast.LENGTH_SHORT).show();
        }
    }
}
