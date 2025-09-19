package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment{
    interface EditCityDialogListener{
        void editCity(City updatedCity);

    }

    private EditCityDialogListener edit_listener;
    private City city;

    public static EditCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        EditCityFragment fragment = new EditCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditCityFragment.EditCityDialogListener) {
            edit_listener = (EditCityFragment.EditCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement EditCityDialogListener");

        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        city = (City) getArguments().getSerializable("city");

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Confirm", (dialog, which) -> {
                    city.setName(editCityName.getText().toString());
                    city.setProvince(editProvinceName.getText().toString());
                    edit_listener.editCity(city);
                })
                .create();
    }
}



