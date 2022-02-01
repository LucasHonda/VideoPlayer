package sttsoft.com.br.video_player.Player.business;

import android.util.Log;

import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sttsoft.com.br.video_player.Player.Utils.remote.source.RAccess;
import sttsoft.com.br.video_player.Player.presentation.data.IOnSuccess;
import sttsoft.com.br.video_player.Player.repository.PlayerRepository;

public class MainBusinessModel {
    private static final String TAG = "MainBusinessModel";
    private static MainBusinessModel instance;
    private ArrayList<String> codes = new ArrayList<>();
    private Observable<Response<ResponseBody>> response;

    public static MainBusinessModel getInstance() {
        if (instance == null) {
            instance = new MainBusinessModel();
        }
        return instance;
    }

    public static void clearInstance() { instance = null; }

    // Regras de negocio
    public void setCodes() {
        codes.add("7896045505357");
        codes.add("78935495");
        codes.add("7896045523412");
        codes.add("7896045506064");
        codes.add("8712000025649");
        codes.add("7896045506040");
        codes.add("78936683");
        codes.add("78905498");
        codes.add("7896045505869");
        codes.add("7896045501038");
        codes.add("7896045505364");
        codes.add("7896045506057");
        codes.add("7896045505371");
        codes.add("7896045506071");
        codes.add("7896045506248");
        codes.add("7896045506217");
        codes.add("7896045502196");
        codes.add("7896045506347");
    }

    public Observable<Response<ResponseBody>> getResponse() {
        return response;
    }

    public void verifyServer(IOnSuccess responseReturn) {
        final PlayerRepository playerRepository = RAccess.createService(PlayerRepository.class);

        try {
            Call<ResponseBody> call = playerRepository.getChangeVideo();

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if(response != null && response.isSuccessful()) {
                        Scanner scanner = new Scanner(response.body().byteStream());

                        int responseValue = scanner.nextInt();

                        Log.i(TAG, "onResponse Trace retorno: " + responseValue);

                        responseReturn.onSuccess(responseValue == 1);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
