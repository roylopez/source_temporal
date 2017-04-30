package roy.cvuq.uniquindio.edu.co.cvuqv1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_gupo.SelectInvestigatorsFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

public class AdaptadorSeleccionInvestigadores extends RecyclerView.Adapter<AdaptadorSeleccionInvestigadores.InvestigadorViewHolder> {

    private static ArrayList<Investigador> investigadores;
    private static OnClickAdapterInvestigator listener;
    private static ArrayList<Investigador> investigadoresSeleccionados;

    public AdaptadorSeleccionInvestigadores(SelectInvestigatorsFragment selectInvestigatorsFragment, ArrayList<Investigador> investigadores, ArrayList<Investigador> investigadoresSeleccionados) {
        this.investigadores = investigadores;
        this.investigadoresSeleccionados = investigadoresSeleccionados;
        this.listener = (OnClickAdapterInvestigator) selectInvestigatorsFragment;
    }

    @Override
    public InvestigadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_lider, parent, false);
        InvestigadorViewHolder investigadorVH = new InvestigadorViewHolder(itemView);
        investigadorVH.setIsRecyclable(false);
        return investigadorVH;
    }

    @Override
    public void onBindViewHolder(InvestigadorViewHolder holder, int position) {
        Investigador investigador = investigadores.get(position);
        holder.bindLine(investigador);
    }

    @Override
    public int getItemCount() {
        return investigadores.size();
    }

    public static class InvestigadorViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView txtNombre;
        private CheckBox chkInvestigador;

        public InvestigadorViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNombre = (TextView) itemView.findViewById(R.id.nombre_investigador);
            chkInvestigador = (CheckBox) itemView.findViewById(R.id.check_lider);
            chkInvestigador.setOnClickListener(this);
        }

        public void bindLine(Investigador investigador) {

            for (int i = 0; i < investigadoresSeleccionados.size(); i++) {
                if (investigadoresSeleccionados.get(i).getEmail().equals(investigador.getEmail())) {
                    chkInvestigador.setChecked(true);
                    break;
                }
            }

            txtNombre.setText(investigador.getNombre().concat(" ").concat(investigador.getApellido()));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.check_lider:
                    if (chkInvestigador.isChecked()) {
                        listener.onClickPositionInvestigator(getAdapterPosition());
                    } else {
                        listener.onClickRemoveInvestigator(investigadores.get(getAdapterPosition()));
                    }
                    break;

                default:
                    break;
            }
        }
    }

    public interface OnClickAdapterInvestigator {
        public void onClickPositionInvestigator(int position);

        public void onClickRemoveInvestigator(Investigador investigador);
    }
}
