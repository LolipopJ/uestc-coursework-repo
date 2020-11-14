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

public class ViewRunCodeTimeFragment extends Fragment {
    static final String TAG = "LifeCycleTest";
    static final String TAG_MSG_OWNER = "ViewRunCodeTimeFragment: ";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        Log.d(TAG, TAG_MSG_OWNER + "onCreateView");
        return inflater.inflate(R.layout.fragment_view_run_code_time, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, TAG_MSG_OWNER + "onViewCreated");

        view.findViewById(R.id.to_run_code_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ViewRunCodeTimeFragment.this)
                        .navigate(R.id.action_ViewRunCodeTimeFragment_to_RunCodeFragment);
            }
        });

        TextView runCodeTimeTextView = view.findViewById(R.id.run_code_time_textview);

        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getSortTime().observe(getViewLifecycleOwner(), runCodeTimeTextView::setText);
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