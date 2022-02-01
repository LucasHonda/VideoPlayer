package sttsoft.com.br.video_player.Player.repository;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PlayerRepository {

    @GET(".")
    Call<ResponseBody> getChangeVideo();
}
