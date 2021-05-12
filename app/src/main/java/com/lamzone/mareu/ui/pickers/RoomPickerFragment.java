package com.lamzone.mareu.ui.pickers;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.lamzone.mareu.DI.DependencyInjector;
import com.lamzone.mareu.R;
import com.lamzone.mareu.events.RoomSelectedEvent;
import com.lamzone.mareu.model.Room;
import com.lamzone.mareu.service.MareuApiService;
import com.lamzone.mareu.utils.MareuUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class RoomPickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        MareuApiService apiService = DependencyInjector.getMareuApiService();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_room_title);
        builder.setView(R.layout.room_dialog);
        List<String> list = MareuUtils.getRoomNames(apiService.getAllRooms());
        CharSequence[] tab = new CharSequence[list.size()];
        for(int i = 0; i<list.size();i++){
            tab[i] = list.get(i);
        }
        builder.setItems(tab,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int position) {
                EventBus.getDefault().post(new RoomSelectedEvent(apiService.getAllRooms().get(position)));
            }
        });
        return builder.create();
    }


}
