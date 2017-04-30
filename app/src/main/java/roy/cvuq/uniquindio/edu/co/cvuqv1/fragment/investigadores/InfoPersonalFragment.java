package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.investigadores;


import android.app.Activity;
import android.content.Context;
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
import roy.cvuq.uniquindio.edu.co.cvuqv1.adapter.AdaptadorDeGrupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.adapter.AdaptadorDeLinea;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.grupos.ListaDeGruposFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Grupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

public class InfoPersonalFragment extends Fragment implements AdaptadorDeGrupo.OnClickAdaptadorDeGrupo, AdaptadorDeLinea.OnClickAdaptadorDeLinea {

    private RecyclerView grupo;
    private RecyclerView lineas;
    private Investigador investigador;
    private ListaDeGruposFragment.OnGrupoSeleccionadoListener listener;

    private TextView txtSinGrupo;
    private TextView txtSinLineas;

    public InfoPersonalFragment() {
        // Required empty public constructor
    }

    public static InfoPersonalFragment newInstance(Investigador investigador) {
        InfoPersonalFragment info = new InfoPersonalFragment();
        info.setInvestigador(investigador);
        Bundle bundle = new Bundle();
        info.setArguments(bundle);
        info.setRetainInstance(true);
        return info;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_personal, container, false);

        txtSinGrupo = (TextView) view.findViewById(R.id.investigador_sin_grupo);
        txtSinGrupo.setVisibility(View.INVISIBLE);

        txtSinLineas = (TextView) view.findViewById(R.id.investigador_sin_lineas);
        txtSinLineas.setVisibility(View.INVISIBLE);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        grupo = (RecyclerView) getView().findViewById(R.id.grupo_inv_info);
        ArrayList<Grupo> grupoInv = new ArrayList<>();
        grupoInv.add(investigador.getGrupo());

        if(investigador.getGrupo() != null){
            AdaptadorDeGrupo adaptadorDeGrupo = new AdaptadorDeGrupo(grupoInv, this);
            grupo.setAdapter(adaptadorDeGrupo);
            grupo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            txtSinGrupo.setVisibility(View.VISIBLE);
            txtSinGrupo.setText(R.string.texto_investigador_sin_grupo);
        }

        if(investigador.getLineasInvestigacion().size() > 0){
            lineas = (RecyclerView) getView().findViewById(R.id.lineas_inv_info);
            AdaptadorDeLinea adaptadorDeLinea = new AdaptadorDeLinea(investigador.getLineasInvestigacion(), this);
            lineas.setAdapter(adaptadorDeLinea);
            lineas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            txtSinLineas.setVisibility(View.VISIBLE);
            txtSinLineas.setText(R.string.texto_investigador_sin_lineas);
        }

    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    @Override
    public void onClickPosition(int pos) {
        //listener.onGrupoSeleccionado(investigador.getGrupo());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                listener = (ListaDeGruposFragment.OnGrupoSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnGrupoSeleccionadoListener");
            }
        }
    }
}
