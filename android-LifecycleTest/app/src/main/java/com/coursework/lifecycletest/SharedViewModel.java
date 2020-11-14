package com.coursework.lifecycletest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author lolipop
 * @version 2020/11/14
 */
public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> sortTime = new MutableLiveData<>();

    public void setSortTime(String time) {
        sortTime.setValue(time);
    }

    public LiveData<String> getSortTime() {
        return sortTime;
    }
}
