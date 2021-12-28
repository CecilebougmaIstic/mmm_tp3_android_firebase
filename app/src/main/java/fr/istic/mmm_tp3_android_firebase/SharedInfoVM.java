package fr.istic.mmm_tp3_android_firebase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedInfoVM extends ViewModel {
    private final MutableLiveData<User> data = new MutableLiveData<User>();

    public void setdata(User newData) {
        data.setValue(newData);
    }

    public LiveData<User> getData() {
        return data;
    }
}
