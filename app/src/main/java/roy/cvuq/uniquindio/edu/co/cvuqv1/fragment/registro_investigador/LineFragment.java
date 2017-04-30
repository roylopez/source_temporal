package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_investigador;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.adapter.AdaptadorLineaRegistro;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_gupo.AddGroupFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineFragment extends DialogFragment implements View.OnClickListener, AdaptadorLineaRegistro.OnClickAdapterLine {

    private Button setLines;
    private RecyclerView rvLineasInvestigacion;
    private ArrayList<String> lineasInvestigacion;
    private ArrayList<String> lineasInvestigacionSeleccionadas;

    public LineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lineasInvestigacion = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.lineas_investigacion)));
        lineasInvestigacionSeleccionadas = new ArrayList<String>();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AdaptadorLineaRegistro adaptadorLineaRegistro = new AdaptadorLineaRegistro(this, lineasInvestigacion, lineasInvestigacionSeleccionadas);
        rvLineasInvestigacion.setAdapter(adaptadorLineaRegistro);
        rvLineasInvestigacion.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lines, container, false);
        rvLineasInvestigacion = (RecyclerView) v.findViewById(R.id.lista_lineas_investigacion);
        setLines = (Button) v.findViewById(R.id.button_set_lines);
        setLines.setOnClickListener(this);

        lineasInvestigacionSeleccionadas = getArguments().getStringArrayList("lineasSeleccionadas");

        return v;
    }

    @Override
    public void onClickPositionLine(int position) {
        lineasInvestigacionSeleccionadas.add(lineasInvestigacion.get(position));
    }

    @Override
    public void onClickRemoveLine(String line) {
        lineasInvestigacionSeleccionadas.remove(line);
    }

    @Override
    public void onClick(View v) {
        getDialog().dismiss();

        if (getTag().equals(AddInvestigatorFragment.class.getName())) {
            ((AddInvestigatorFragment) getFragmentManager().findFragmentByTag(getResources().getString(R.string.tag_fragment_addinvestigator))).showLines(lineasInvestigacionSeleccionadas);
        } else if (getTag().equals(AddGroupFragment.class.getName())) {
            ((AddGroupFragment) getFragmentManager().findFragmentByTag(getResources().getString(R.string.tag_fragment_addgroup))).showLines(lineasInvestigacionSeleccionadas);
        }
    }
}
