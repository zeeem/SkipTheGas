package com.example.skipthegas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * This is a class which implements a fragment displaying to a driver that a ride has been cancelled
 * by the rider who originally requested it
 */
public class RideCancelledFragment extends DialogFragment {
    public interface OnFragmentInteractionListener {
        void onOkPressed();
    }

    /**
     * onCreateDialog method for RideCancelledFragment fragment
     * @param savedInstanceState
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.ride_cancelled_fragment_layout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Notice:")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Ride Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }
}
