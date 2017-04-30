package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private ImageView optionGroups;
    private ImageView optionInvestigators;
    private ImageView optionAddGroup;
    private ImageView optionAddInvestigator;
    private OnOptionHomeListener listener;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_home, container, false);

        optionGroups = (ImageView) vista.findViewById(R.id.option_groups);
        optionInvestigators = (ImageView) vista.findViewById(R.id.option_investigators);
        optionAddGroup = (ImageView) vista.findViewById(R.id.option_add_group);
        optionAddInvestigator = (ImageView) vista.findViewById(R.id.option_add_investigator);

        optionGroups.setOnClickListener(this);
        optionInvestigators.setOnClickListener(this);
        optionAddGroup.setOnClickListener(this);
        optionAddInvestigator.setOnClickListener(this);

        return vista;
    }

    @Override
    public void onClick(View v) {
        int option = v.getId();
        listener.onOptionHomeSelected(option);
    }

    public interface OnOptionHomeListener {
        public void onOptionHomeSelected(int optionId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                listener = (OnOptionHomeListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " Debe implementar la interfaz OnHomeOptionListener");
            }
        }
    }

}
