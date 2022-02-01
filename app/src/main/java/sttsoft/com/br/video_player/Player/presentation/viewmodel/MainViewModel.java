package sttsoft.com.br.video_player.Player.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import sttsoft.com.br.video_player.Player.business.MainBusinessModel;
import sttsoft.com.br.video_player.Player.presentation.data.IOnSuccess;

public class MainViewModel extends ViewModel {
    private final MainBusinessModel mainInterface;
    public final MutableLiveData<Boolean> changeVideo = new MutableLiveData<>();

    public MainViewModel() {
        this.mainInterface = MainBusinessModel.getInstance();
        //mainInterface.setCodes();
    }

    public MutableLiveData<Boolean> getChangeVideo() { return changeVideo; }

    public void changeVideo() {
        Observable.interval(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        mainInterface.verifyServer(changeVideo::postValue);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

}
