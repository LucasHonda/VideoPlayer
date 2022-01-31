package sttsoft.com.br.video_player.Player.business;

import android.util.Log;

import org.reactivestreams.Subscription;

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
import sttsoft.com.br.video_player.Player.repository.PlayerRepository;

public class MainBusinessModel {

    private static final String TAG = "MainBusinessModel";
    private static MainBusinessModel instance;
    private ArrayList<String> codes = new ArrayList<>();

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

    public boolean verifyCode() {
        Observable.interval(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        final PlayerRepository playerRepository = RAccess.createService(PlayerRepository.class);

                        Call<ResponseBody> call = playerRepository.getChangeVideo();

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                if(response.isSuccessful()) {

                                    Scanner scanner = new Scanner(response.body().byteStream());

                                    boolean retorno = scanner.nextBoolean();

                                    Log.i(TAG, "onResponse Trace retorno: " + retorno);

                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.i(TAG, "onFailure : " + t.getMessage());
                            }
                        });



                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {}
                });



        for (String c: codes) {
            if (c.equals(code)) {
                return true;
            }
        }
        return false;
    }
}
