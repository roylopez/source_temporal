package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.grupos;


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
import roy.cvuq.uniquindio.edu.co.cvuqv1.adapter.AdaptadorDeInvestigador;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.investigadores.ListaInvestigadoresFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

public class InvestigadoresFragment extends Fragment implements AdaptadorDeInvestigador.OnClickAdaptadorDeInvestigador {

    public static final String LIDER = "lider";
    public static final String INTEGRANTE = "integrante";

    private AdaptadorDeInvestigador adaptador;
    private RecyclerView listadoIntegrantes;
    private RecyclerView lider;
    private Investigador lider_investigador;
    private ArrayList<Investigador> investigadores;
    private ListaInvestigadoresFragment.OnInvestigadorSeleccionadoListener listener;

    private TextView txtSinLider;
    private TextView txtSinInvestigadores;

    public InvestigadoresFragment() {
        // Required empty public constructor
    }

    public static InvestigadoresFragment newInstance(ArrayList<Investigador> investigadors, Investigador lider_investigador) {
        InvestigadoresFragment invs = new InvestigadoresFragment();
        invs.setInvestigadors(investigadors);
        invs.setLider_investigador(lider_investigador);
        Bundle bundle = new Bundle();
        invs.setArguments(bundle);
        invs.setRetainInstance(true);
        return invs;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_investigadores, container, false);

        txtSinLider = (TextView) vista.findViewById(R.id.grupo_sin_lider);
        txtSinLider.setVisibility(View.INVISIBLE);

        txtSinInvestigadores = (TextView) vista.findViewById(R.id.grupo_sin_investigadores);
        txtSinInvestigadores.setVisibility(View.INVISIBLE);

        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (investigadores.size() > 0) {
            listadoIntegrantes = (RecyclerView) getView().findViewById(R.id.listaIntegrantes);
            adaptador = new AdaptadorDeInvestigador(investigadores, this, "integrante");
            listadoIntegrantes.setAdapter(adaptador);
            listadoIntegrantes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            txtSinInvestigadores.setVisibility(View.VISIBLE);
            txtSinInvestigadores.setText(R.string.texto_grupo_sin_investigadores);
        }

        if (lider_investigador != null) {
            listadoIntegrantes = (RecyclerView) getView().findViewById(R.id.lider_lista);
            ArrayList<Investigador> lider_list = new ArrayList<>();
            lider_list.add(lider_investigador);
            adaptador = new AdaptadorDeInvestigador(lider_list, this, "lider");
            listadoIntegrantes.setAdapter(adaptador);
            listadoIntegrantes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            txtSinLider.setVisibility(View.VISIBLE);
            txtSinLider.setText(R.string.texto_grupo_sin_lider);
        }

    }

    public ArrayList getInvestigadors() {
        return investigadores;
    }

    public void setInvestigadors(ArrayList investigadors) {
        this.investigadores = investigadors;
    }


    public Investigador getLider_investigador() {
        return lider_investigador;
    }

    public void setLider_investigador(Investigador lider_investigador) {
        this.lider_investigador = lider_investigador;
    }

    @Override
    public void onClickInvestigatorPosition(int pos, String tipo) {
        /*
        if (tipo.equals(LIDER)) {
            listener.onInvestigadorSeleccionado(lider_investigador);
        } else if (tipo.equals(INTEGRANTE)) {
            listener.onInvestigadorSeleccionado(investigadores.get(pos));
        }
        */
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                listener = (ListaInvestigadoresFragment.OnInvestigadorSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnInvestigadorSeleccionadoListener");
            }
        }
    }
}
