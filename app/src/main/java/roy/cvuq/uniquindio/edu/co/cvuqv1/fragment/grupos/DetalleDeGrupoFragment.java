package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.grupos;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Grupo;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleDeGrupoFragment extends Fragment {

    TextView txtNombre;
    TextView txtSigla;
    TextView txtEmail;
    TextView txtCategoria;
    TextView txtLink;
    Grupo grupo;

    public DetalleDeGrupoFragment() {
        // Required empty public constructor
    }

    public static DetalleDeGrupoFragment newInstance(Grupo grupo) {
        DetalleDeGrupoFragment detalle = new DetalleDeGrupoFragment();
        detalle.setGrupo(grupo);
        Bundle bundle = new Bundle();
        detalle.setArguments(bundle);
        detalle.setRetainInstance(true);
        return detalle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_de_grupo, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txtNombre = (TextView)
                getView().findViewById(R.id.txtNombre);
        txtNombre.setText(grupo.getNombre());
        txtSigla = (TextView) getView().findViewById(R.id.txtSigla);
        txtSigla.setText(grupo.getSigla());
        txtEmail = (TextView) getView().findViewById(R.id.txtEmail);
        txtEmail.setText(grupo.getEmail());
        txtCategoria = (TextView) getView().findViewById(R.id.txtCategoria);
        txtCategoria.setText(grupo.getCategoria());
        txtLink = (TextView) getView().findViewById(R.id.txtLink);
        txtLink.setText(grupo.getLink());

    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
