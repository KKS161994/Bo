package in.silive.bo.services;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import in.silive.bo.network.BytePad;
import in.silive.bo.utilities.Config;

/**
 * Created by kartikey on 22/02/16.
 */
public class RetrofitBytepadService extends RetrofitGsonSpiceService{
    @Override
    public void onCreate() {
        super.onCreate();
    addRetrofitInterface(BytePad.class);
    }

    @Override
    protected String getServerUrl() {
        return Config.BASE_URL;
    }
}
