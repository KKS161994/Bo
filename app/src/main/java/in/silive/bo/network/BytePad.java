package in.silive.bo.network;

import in.silive.bo.model.Paper;
import in.silive.bo.model.PaperModel;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by kartikey on 22/02/16.
 */
public interface BytePad {
    @GET("/paper/getallpapers")
    PaperModel.PapersList papersList(
    @Query("query") String data
        );
}
