package roy.cvuq.uniquindio.edu.co.cvuqv1.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Grupo;


public class AdaptadorDeGrupo extends RecyclerView.Adapter<AdaptadorDeGrupo.GrupoViewHolder> {

    private ArrayList<Grupo> grupo;
    private static OnClickAdaptadorDeGrupo listener;
    private static Fragment context;

    public AdaptadorDeGrupo(ArrayList<Grupo> grupo, Fragment listaDeGruposFragment) {
        this.grupo = grupo;
        listener = (OnClickAdaptadorDeGrupo) listaDeGruposFragment;
        this.context = listaDeGruposFragment;
    }

    @Override
    public GrupoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listado_grupos, parent, false);
        GrupoViewHolder grupoVH = new GrupoViewHolder(itemView);
        return grupoVH;
    }

    @Override
    public void onBindViewHolder(GrupoViewHolder holder, int position) {
        Grupo grupoC = grupo.get(position);
        holder.binGrupo(grupoC);
    }

    public interface OnClickAdaptadorDeGrupo {
        public void onClickPosition(int pos);
    }

    @Override
    public int getItemCount() {
        return grupo.size();
    }

    public static class GrupoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNombreGrupo;
        private TextView txtSigla;
        private TextView txtLinea1;
        private TextView txtLinea2;

        public GrupoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNombreGrupo = (TextView)
                    itemView.findViewById(R.id.nombre);
            txtSigla = (TextView)
                    itemView.findViewById(R.id.sigla);
            txtLinea1 = (TextView)
                    itemView.findViewById(R.id.linea1);
            txtLinea2 = (TextView)
                    itemView.findViewById(R.id.linea2);
        }

        public void binGrupo(Grupo grupo) {
            txtNombreGrupo.setText(grupo.getNombre());
            txtSigla.setText(grupo.getSigla());

            if (grupo.getLineasInvestigacion().size() < 2) {
                if (grupo.getLineasInvestigacion().size() == 1) {
                    txtLinea1.setText((String) grupo.getLineasInvestigacion().get(0));
                } else {
                    txtLinea1.setText(context.getResources().getString(R.string.texto_grupo_sin_lineas));
                }
            } else {
                txtLinea1.setText((String) grupo.getLineasInvestigacion().get(0));
                txtLinea2.setText((String) grupo.getLineasInvestigacion().get(1));
            }

        }

        @Override
        public void onClick(View view) {
            Log.d("TAG", "Element " + getAdapterPosition() + " clicked. " +
                    txtNombreGrupo.getText());
            listener.onClickPosition(getAdapterPosition());
        }
    }
}
