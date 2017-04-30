package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.grupos;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.adapter.AdaptadorDeLinea;

public class LineasInvestigacionFragment extends Fragment implements AdaptadorDeLinea.OnClickAdaptadorDeLinea {

    AdaptadorDeLinea adaptador;
    RecyclerView listadoLineas;
    ArrayList<String> lineas;

    private TextView txtSinLineas;

    public LineasInvestigacionFragment() {
    }

    public static LineasInvestigacionFragment newInstance(ArrayList lineas) {
        LineasInvestigacionFragment lineasF = new LineasInvestigacionFragment();
        System.out.println("Lineas");
        lineasF.setLineas(lineas);
        Bundle bundle = new Bundle();
        lineasF.setArguments(bundle);
        lineasF.setRetainInstance(true);
        return lineasF;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_lineas_investigacion, container, false);

        txtSinLineas = (TextView) vista.findViewById(R.id.grupo_sin_lineas);
        txtSinLineas.setVisibility(View.INVISIBLE);

        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (lineas.size() > 0) {
            listadoLineas = (RecyclerView) getView().findViewById(R.id.listaLineas);
            adaptador = new AdaptadorDeLinea(lineas, this);
            listadoLineas.setAdapter(adaptador);
            listadoLineas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            txtSinLineas.setVisibility(View.VISIBLE);
            txtSinLineas.setText(R.string.texto_grupo_sin_lineas);
        }

    }

    public AdaptadorDeLinea getAdaptador() {
        return adaptador;
    }

    public void setAdaptador(AdaptadorDeLinea adaptador) {
        this.adaptador = adaptador;
    }

    public RecyclerView getListadoLineas() {
        return listadoLineas;
    }

    public void setListadoLineas(RecyclerView listadoLineas) {
        this.listadoLineas = listadoLineas;
    }

    public ArrayList<String> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<String> lineas) {
        this.lineas = lineas;
    }

    @Override
    public void onClickPosition(int pos) {
        Log.d("Hola", "bu");
    }
}
