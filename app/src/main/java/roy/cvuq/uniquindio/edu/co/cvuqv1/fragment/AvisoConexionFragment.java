package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;

public class AvisoConexionFragment extends DialogFragment {

    public AvisoConexionFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_aviso_conexion, null);
        builder.setView(view);
        return builder.create();
    }
}

