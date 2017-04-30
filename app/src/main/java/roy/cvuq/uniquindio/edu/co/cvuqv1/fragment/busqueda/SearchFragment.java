package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.busqueda;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.adapter.AdaptadorDeGrupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.adapter.AdaptadorDeInvestigador;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.grupos.ListaDeGruposFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.investigadores.ListaInvestigadoresFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Grupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements AdaptadorDeGrupo.OnClickAdaptadorDeGrupo,
        AdaptadorDeInvestigador.OnClickAdaptadorDeInvestigador{

    private RecyclerView grupos;
    private RecyclerView investigadores;
    private ArrayList<Grupo> listaGrupos;
    private ArrayList<Investigador> listaInvestigadores;

    ListaDeGruposFragment.OnGrupoSeleccionadoListener listener1;
    ListaInvestigadoresFragment.OnInvestigadorSeleccionadoListener listener2;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listaGrupos = getArguments().getParcelableArrayList("grupos");
        listaInvestigadores = getArguments().getParcelableArrayList("investigadores");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        grupos = (RecyclerView)getView().findViewById(R.id.list_search_gr);
        AdaptadorDeGrupo adaptadorDeGrupo = new AdaptadorDeGrupo(listaGrupos,this);
        grupos.setAdapter(adaptadorDeGrupo);
        grupos.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        investigadores = (RecyclerView)getView().findViewById(R.id.list_search_inv);
        AdaptadorDeInvestigador adaptadorDeInvestigador = new AdaptadorDeInvestigador(listaInvestigadores,this, "integrante");
        investigadores.setAdapter(adaptadorDeInvestigador);
        investigadores.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                listener1 = (ListaDeGruposFragment.OnGrupoSeleccionadoListener) activity;
                listener2 = (ListaInvestigadoresFragment.OnInvestigadorSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnGruposSeleccionadoListener");
            }
        }
    }

    @Override
    public void onClickInvestigatorPosition(int pos, String tipo) {
        listener2.onInvestigadorSeleccionado(listaInvestigadores.get(pos));
    }

    @Override
    public void onClickPosition(int pos) {
        listener1.onGrupoSeleccionado(listaGrupos.get(pos));
    }
}
