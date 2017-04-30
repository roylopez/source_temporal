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
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;


public class AdaptadorDeInvestigador extends RecyclerView.Adapter<AdaptadorDeInvestigador.InvestigadorViewHolder> {

    private ArrayList<Investigador> investigadores;
    private static OnClickAdaptadorDeInvestigador listener;
    private String tipo;

    public AdaptadorDeInvestigador(ArrayList<Investigador> investigadores, Fragment listaInvestigadoresFragment, String tipo) {
        this.investigadores = investigadores;
        this.listener = (OnClickAdaptadorDeInvestigador) listaInvestigadoresFragment;
        this.tipo = tipo;
    }

    @Override
    public InvestigadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_investigadores, parent, false);
        InvestigadorViewHolder investigadorVH = new InvestigadorViewHolder(itemView, tipo);
        return investigadorVH;
    }

    @Override
    public void onBindViewHolder(InvestigadorViewHolder holder, int position) {
        Investigador investigador = investigadores.get(position);
        holder.binInvestigador(investigador);
    }

    @Override
    public int getItemCount() {
        return this.investigadores.size();
    }

    public static class InvestigadorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtNombreInvestigador;
        private TextView txtApellido;
        private TextView txtLinea1;
        private TextView txtLinea2;
        private TextView txtLinea3;
        private String tipo;

        public InvestigadorViewHolder(View itemView, String tipo) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNombreInvestigador = (TextView) itemView.findViewById(R.id.nombre_res);
            txtApellido = (TextView) itemView.findViewById(R.id.apellido_res);
            txtLinea1 = (TextView) itemView.findViewById(R.id.linea_inv1);
            txtLinea2 = (TextView) itemView.findViewById(R.id.linea_inv2);
            txtLinea3 = (TextView) itemView.findViewById(R.id.linea_inv3);
            this.tipo = tipo;
        }

        public void binInvestigador(Investigador investigador) {
            txtNombreInvestigador.setText(investigador.getNombre());
            txtApellido.setText(investigador.getApellido());
            txtLinea1.setText(investigador.getLineasInvestigacion().get(0));
            txtLinea2.setText(investigador.getLineasInvestigacion().get(1));
            txtLinea3.setText(investigador.getLineasInvestigacion().get(2));
        }

        @Override
        public void onClick(View view) {
            listener.onClickInvestigatorPosition(getAdapterPosition(), tipo);
        }

    }

    public interface OnClickAdaptadorDeInvestigador {
        void onClickInvestigatorPosition(int pos, String tipo);
    }
}


