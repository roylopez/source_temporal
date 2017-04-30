package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_gupo;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_investigador.LineFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddGroupFragment extends Fragment implements View.OnClickListener {

    public static final int PICK_IMAGE = 1;

    private TextView txtLiderGrupo;
    private Investigador lider;
    private TextView txtLineasInvestigacion;
    private ArrayList<String> lineasSeleccionadas;
    private Spinner categoriaGrupo;

    private LinearLayout agregarImagen;
    private TextView textoImagen;
    private ImageView imagenGrupo;
    private Uri selectedImagePath;

    private TextView txtInvestigadores;
    private ArrayList<Investigador> investigadoresSeleccionados;

    public AddGroupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lineasSeleccionadas = new ArrayList<String>();
        investigadoresSeleccionados = new ArrayList<Investigador>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_add_group, container, false);

        categoriaGrupo = (Spinner) vista.findViewById(R.id.spinner_categoria);
        String[] categorias = getResources().getStringArray(R.array.categorias_grupo);
        ArrayAdapter<String> adaptadorCategorias = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categorias);
        adaptadorCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaGrupo.setAdapter(adaptadorCategorias);

        txtLiderGrupo = (TextView) vista.findViewById(R.id.lider_grupo);
        txtLiderGrupo.setOnClickListener(this);

        txtLineasInvestigacion = (TextView) vista.findViewById(R.id.lineas_investigacion);
        txtLineasInvestigacion.setOnClickListener(this);

        agregarImagen = (LinearLayout) vista.findViewById(R.id.agregar_imagen);
        agregarImagen.setOnClickListener(this);
        imagenGrupo = (ImageView) vista.findViewById(R.id.image_group);
        textoImagen = (TextView) vista.findViewById(R.id.texto_fotografia_add_group);

        txtInvestigadores = (TextView) vista.findViewById(R.id.investigadores);
        txtInvestigadores.setOnClickListener(this);

        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lider_grupo:
                showDialogLider(AddGroupFragment.class.getName());
                break;

            case R.id.lineas_investigacion:
                showDialogLine(AddGroupFragment.class.getName());
                break;

            case R.id.agregar_imagen:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.texto_seleccionar_imagen)), PICK_IMAGE);
                break;

            case R.id.investigadores:
                showDialogInvestigators(AddGroupFragment.class.getName());
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            selectedImagePath = data.getData();
            showImage();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            lider = (Investigador) savedInstanceState.getParcelable("lider");
            showLider(lider);
            lineasSeleccionadas = savedInstanceState.getStringArrayList("lineasSeleccionadas");
            showLines(lineasSeleccionadas);
            investigadoresSeleccionados = savedInstanceState.getParcelableArrayList("investigadoresSeleccionados");
            showInvestigators(investigadoresSeleccionados);

            if (savedInstanceState.getString("referenciaFotografia") != null) {
                selectedImagePath = Uri.parse(savedInstanceState.getString("referenciaFotografia"));
                showImage();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("lider", lider);
        outState.putStringArrayList("lineasSeleccionadas", lineasSeleccionadas);
        outState.putParcelableArrayList("investigadoresSeleccionados", investigadoresSeleccionados);
        if (selectedImagePath != null) {
            outState.putString("referenciaFotografia", selectedImagePath.toString());
        }
    }

    private void showImage() {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImagePath);
            imagenGrupo.setImageBitmap(bitmap);
            textoImagen.setText(getResources().getString(R.string.texto_cambiar_fotografia));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDialogLider(String nombreClase) {
        LiderFragment dialogLider = new LiderFragment();
        dialogLider.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
        dialogLider.show(getFragmentManager(), nombreClase);
    }

    private void showDialogLine(String nombreClase) {
        LineFragment dialogLine = new LineFragment();
        dialogLine.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("lineasSeleccionadas", lineasSeleccionadas);
        dialogLine.setArguments(bundle);
        dialogLine.show(getFragmentManager(), nombreClase);
    }

    private void showDialogInvestigators(String nombreClase) {
        SelectInvestigatorsFragment dialogInvestigators = new SelectInvestigatorsFragment();
        dialogInvestigators.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("investigadoresSeleccionados", investigadoresSeleccionados);
        dialogInvestigators.setArguments(bundle);
        dialogInvestigators.show(getFragmentManager(), nombreClase);
    }

    public void showLider(Investigador investigador) {
        if (investigador != null) {
            lider = investigador;
            txtLiderGrupo.setText(investigador.getNombre().concat(" ").concat(investigador.getApellido()));
        }
    }

    public void showLines(ArrayList<String> lineas) {
        String textoLineas = new String("");
        for (int i = 0; i < lineas.size(); i++) {
            if (i == (lineas.size()) - 1) {
                textoLineas = textoLineas.concat(lineas.get(i));
            } else {
                textoLineas = textoLineas.concat(lineas.get(i)).concat(",");
            }
        }

        if (lineas.size() == 0) {
            textoLineas = getResources().getString(R.string.texto_lineas_investigacion);
        }
        txtLineasInvestigacion.setText(textoLineas);
        lineasSeleccionadas = lineas;
    }

    public void showInvestigators(ArrayList<Investigador> investigadores) {
        String textoInvestigadores = new String("");
        for (int i = 0; i < investigadores.size(); i++) {
            if (i == (investigadores.size() - 1)) {
                textoInvestigadores = textoInvestigadores.concat(investigadores.get(i).getNombre().concat(" ").concat(investigadores.get(i).getApellido()));
            } else {
                textoInvestigadores = textoInvestigadores.concat(investigadores.get(i).getNombre().concat(" ").concat(investigadores.get(i).getApellido())).concat(", ");
            }
        }

        if (investigadores.size() == 0) {
            textoInvestigadores = getResources().getString(R.string.texto_investigadores);
        }
        txtInvestigadores.setText(textoInvestigadores);
        investigadoresSeleccionados = investigadores;
    }
}
