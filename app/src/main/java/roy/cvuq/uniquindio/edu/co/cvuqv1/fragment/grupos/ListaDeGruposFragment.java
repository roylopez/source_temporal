package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.grupos;


import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.adapter.AdaptadorDeGrupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.investigadores.TabDetalleDeInvestigadorFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Grupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

public class ListaDeGruposFragment extends Fragment implements
        AdaptadorDeGrupo.OnClickAdaptadorDeGrupo,
        View.OnClickListener {

    private AdaptadorDeGrupo adaptador;
    protected RecyclerView listadoGrupos;
    private ArrayList<Grupo> grupos;
    protected FloatingActionButton agregarGrupo;

    private OnGrupoSeleccionadoListener listener;

    public ListaDeGruposFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crearGrupos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_de_grupos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        agregarGrupo = (FloatingActionButton) getView().findViewById(R.id.add_group);
        agregarGrupo.setOnClickListener(this);
        agregarGrupo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#144d0b")));

        listadoGrupos = (RecyclerView) getView().findViewById(R.id.listaGrupos);
        adaptador = new AdaptadorDeGrupo(grupos, this);
        listadoGrupos.setAdapter(adaptador);
        listadoGrupos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        if (getView().findViewById(R.id.detalle_grupo) != null) {
            TabFragmentDetalleGrupo fragmentoDetalle = new TabFragmentDetalleGrupo();
            Bundle bundle = new Bundle();
            bundle.putParcelable("grupo", grupos.get(0));
            fragmentoDetalle.setArguments(bundle);
            drawFragmentDetalle(fragmentoDetalle);
        }
    }

    @Override
    public void onClickPosition(int pos) {
        if (getView().findViewById(R.id.detalle_grupo) != null) {
            TabFragmentDetalleGrupo fragmentoDetalle = new TabFragmentDetalleGrupo();
            Bundle bundle = new Bundle();
            bundle.putParcelable("grupo", grupos.get(pos));
            fragmentoDetalle.setArguments(bundle);
            drawFragmentDetalle(fragmentoDetalle);
        } else {
            listener.onGrupoSeleccionado(grupos.get(pos));
        }
    }

    public interface OnGrupoSeleccionadoListener {
        void onGrupoSeleccionado(Grupo grupo);

        void onAddGroup();
    }

    private void drawFragmentDetalle(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.detalle_grupo, fragment)
                .commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_group) {
            listener.onAddGroup();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                listener = (OnGrupoSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnPersonajeSeleccionadoListener");
            }
        }
    }

    private void crearGrupos() {
        grupos = new ArrayList();

        List<String> lineasG1 = new ArrayList<>(), lineasG2 = new ArrayList<>(), lineasG3 = new ArrayList<>(), lineasG4 = new ArrayList<String>();
        lineasG1.add("Bases de datos");
        lineasG1.add("Redes de computadoras");
        lineasG1.add("Mineria");

        List<Investigador> invest = new ArrayList<Investigador>();
        Investigador i1 = new Investigador();
        Investigador i2 = new Investigador();
        Investigador i3 = new Investigador();
        Investigador i4 = new Investigador();
        Investigador i5 = new Investigador();

        i1.setNombre("Pepito");
        i1.setApellido("Perez");
        i1.setLink("www.cvlac.com/pepito");
        i1.setCategoria("Categoria 2");
        i1.setLineasInvestigacion((ArrayList) lineasG1);

        i2.setNombre("Pepita");
        i2.setApellido("Lopez");
        i2.setLineasInvestigacion((ArrayList) lineasG1);

        i3.setNombre("Lupita");
        i3.setApellido("Luna");
        i3.setLineasInvestigacion((ArrayList) lineasG1);

        i4.setNombre("Marcus");
        i4.setApellido("Cornelious");
        i4.setLineasInvestigacion((ArrayList) lineasG1);

        i5.setNombre("Serena");
        i5.setApellido("Williams");
        i5.setLineasInvestigacion((ArrayList) lineasG1);

        invest.add(i1);
        invest.add(i2);
        invest.add(i3);
        invest.add(i4);
        invest.add(i5);

        lineasG2.add("Bases de datos");
        lineasG2.add("Mineria");
        lineasG2.add("Gestion del conocimiento");

        lineasG4.add("Redes de computadoras");
        lineasG4.add("Ingenieria de software");
        lineasG4.add("Mineria");


        Investigador lider = new Investigador();
        lider.setNombre("Manu");
        lider.setApellido("Lalu");
        lider.setLineasInvestigacion((ArrayList) lineasG2);


        Grupo g1 = new Grupo();
        g1.setCategoria("Categoria C");
        g1.setNombre("Grupo de Investigación en Redes, Información y Distribuciones GRID");
        //g1.setNombre("GEOIDE-G62");
        g1.setEmail("grid@micorreo.com");
        g1.setSigla("GRID");
        g1.setFoto(12300);
        g1.setLider(lider);
        g1.setInvestigadores(invest);
        g1.setLink("www.cvlac.com/grid");

        g1.setLineasInvestigacion(lineasG4);

        lider.setGrupo(g1);
        i1.setGrupo(g1);
        i2.setGrupo(g1);

        Grupo g2 = new Grupo();
        g2.setNombre("GEOIDE-G62");
        g2.setSigla("GEOIDE-G62");
        g2.setCategoria("Categoria D");
        g2.setLink("www.cvlac.com/geoide");
        g2.setLineasInvestigacion(lineasG2);
        g2.setLider(lider);
        g2.setInvestigadores(invest);


        Grupo g3 = new Grupo();
        g3.setNombre("SINFOCI");
        g3.setSigla("SINFOCI");
        g3.setCategoria("Categoria D");
        g3.setLink("www.cvlac.com/SINFOCI");
        g3.setLineasInvestigacion(lineasG2);
        g3.setLider(lider);
        g3.setInvestigadores(invest);

        Grupo g4 = new Grupo();
        g4.setNombre("CIDERA");
        g4.setSigla("CIDERA");
        g4.setCategoria("Categoria D");
        g4.setLink("www.cvlac.com/CIDERA");
        g4.setLineasInvestigacion(lineasG2);
        g4.setLider(lider);
        g4.setInvestigadores(invest);


        grupos.add(g1);
        grupos.add(g2);
        grupos.add(g3);
        grupos.add(g4);

    }
}
