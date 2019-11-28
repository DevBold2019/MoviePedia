package com.example.moviepedia;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;



public class DetailDialog extends AppCompatDialogFragment {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.more_details_layout, null);
/*

            builder.setView(view)
                    .setTitle("Login")
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String username = editTextUsername.getText().toString();
                            String password = editTextPassword.getText().toString();
                            listener.applyTexts(username, password);
                        }
                    });
*/

            return builder.create();
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);

        }


    }

