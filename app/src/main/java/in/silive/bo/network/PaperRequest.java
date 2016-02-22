package in.silive.bo.network;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import in.silive.bo.model.PaperModel;

/**
 * Created by kartikey on 22/02/16.
 */
public class PaperRequest extends RetrofitSpiceRequest<PaperModel.PapersList, BytePad> {
    private String data;

    public PaperRequest(String data) {
        super(PaperModel.PapersList.class, BytePad.class);
        this.data = data;
    }

    @Override
    public PaperModel.PapersList loadDataFromNetwork() throws Exception {
        return getService().papersList(data);
    }
}
