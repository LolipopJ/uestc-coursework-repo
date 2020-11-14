package com.coursework.lifecycletest;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Arrays;
import java.util.Random;

public class RunCodeFragment extends Fragment {
    static final String TAG = "LifeCycleTest";
    static final String TAG_MSG_OWNER = "RunCodeFragment: ";
    static final String RUN_CODE_TAG = "RunCodeTest";

    private SharedViewModel model;

    int[] unsortedArray;
    int randomMax = 100000, randomLength = 6000;
    long sortTime;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        Log.d(TAG, TAG_MSG_OWNER + "onCreateView");
        return inflater.inflate(R.layout.fragment_run_code, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, TAG_MSG_OWNER + "onViewCreated");

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        view.findViewById(R.id.view_run_code_time_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RunCodeFragment.this)
                        .navigate(R.id.action_RunCodeFragment_to_ViewRunCodeTimeFragment);
            }
        });

        view.findViewById(R.id.run_code_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runSort();
            }
        });

        // 生成随机数
        unsortedArray = generateUnsortedArray();
        TextView unsortedArrayTextView = view.findViewById(R.id.unsorted_array_textview);
        unsortedArrayTextView.setText(Arrays.toString(unsortedArray));

        TextView sortedArrayTextView = view.findViewById(R.id.sorted_array_textview);
        sortedArrayTextView.setText("[]");
    }

    private int[] generateUnsortedArray() {
        int max = randomMax, length = randomLength;
        int[] unsortedArray = new int[length];
        Random r = new Random(System.currentTimeMillis());

        for (int i = 0; i < length; i++) {
            unsortedArray[i] = r.nextInt(max);
        }

        return unsortedArray;
    }

    private void runSort() {
        int[] sortedArray;

        // 计算选择排序时间
        long beginTime = System.currentTimeMillis();
        sortedArray = selectSort(unsortedArray);
        long endTime = System.currentTimeMillis();
        sortTime = endTime - beginTime;
        Log.d(RUN_CODE_TAG, "sort time: " + sortTime);

        // 存储本次排序花费的时间
        model.setSortTime(sortTime + " ms");

        View view = getView();
        assert view != null;

        // 页面赋值
        TextView sortedArrayTextView = view.findViewById(R.id.sorted_array_textview);
        sortedArrayTextView.setText(Arrays.toString(sortedArray));
    }

    // 选择排序算法
    private int[] selectSort(int[] unsortedArray) {
        for (int i = 0; i < unsortedArray.length - 1; i++){
            int index = i;
            int min = unsortedArray[i];
            for (int j = i + 1; j < unsortedArray.length; j++){
                if (min> unsortedArray[j]){
                    min = unsortedArray[j];
                    index = j;
                }
            }
            if (index != i){
                unsortedArray[index] = unsortedArray[i];
                unsortedArray[i] = min;
            }
        }
        return unsortedArray;
    }

    @Override
    public void onAttach (@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, TAG_MSG_OWNER + "onAttach");
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, TAG_MSG_OWNER + "onCreate");
    }

    @Override
    public void onStart () {
        super.onStart();
        Log.d(TAG, TAG_MSG_OWNER + "onStart");
    }

    @Override
    public void onResume () {
        super.onResume();
        Log.d(TAG, TAG_MSG_OWNER + "onResume");
    }

    @Override
    public void onPause () {
        super.onPause();
        Log.d(TAG, TAG_MSG_OWNER + "onPause");
    }

    @Override
    public void onStop () {
        super.onStop();
        Log.d(TAG, TAG_MSG_OWNER + "onStop");
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        Log.d(TAG, TAG_MSG_OWNER + "onDestroyView");
    }

    @Override
    public void onDetach () {
        super.onDetach();
        Log.d(TAG, TAG_MSG_OWNER + "onDetach");
    }
}