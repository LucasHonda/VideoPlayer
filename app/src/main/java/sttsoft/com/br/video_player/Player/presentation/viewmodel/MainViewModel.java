package sttsoft.com.br.video_player.Player.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import sttsoft.com.br.video_player.Player.business.MainBusinessModel;

public class MainViewModel extends ViewModel {
    private final MainBusinessModel mainInterface;
    public final MutableLiveData<Boolean> changeVideo = new MutableLiveData<>();

    public MainViewModel() {
        this.mainInterface = MainBusinessModel.getInstance();
        mainInterface.setCodes();
    }

    public MutableLiveData<Boolean> getChangeVideo() { return changeVideo; }

    public void changeVideo(String code) {
        changeVideo.postValue(mainInterface.verifyCode(code));
    }

}
