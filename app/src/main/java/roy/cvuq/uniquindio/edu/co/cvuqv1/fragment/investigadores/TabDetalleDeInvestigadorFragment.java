package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.investigadores;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

import static android.R.attr.pivotX;
import static android.R.attr.pivotY;


public class TabDetalleDeInvestigadorFragment extends Fragment implements View.OnClickListener {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2;
    private Investigador investigador;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2;

    private boolean click;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        investigador = new Investigador();
        click = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        investigador = getArguments().getParcelable("investigador");

        View x = inflater.inflate(R.layout.fragment_tool_bar, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);


        materialDesignFAM = (FloatingActionMenu) x.findViewById(R.id.float_menu_compartir);
        materialDesignFAM.setOnClickListener(this);
        floatingActionButton1 = (com.github.clans.fab.FloatingActionButton) x.findViewById(R.id.item_compartir_facebook);
        floatingActionButton2 = (com.github.clans.fab.FloatingActionButton) x.findViewById(R.id.item_compartir_twitter);

        floatingActionButton1.setColorNormalResId(R.color.android_darkgreen);
        floatingActionButton1.setOnClickListener(this);
        floatingActionButton1.setColorPressed(R.color.android_green);

        floatingActionButton2.setColorNormalResId(R.color.android_darkgreen);
        floatingActionButton2.setOnClickListener(this);
        floatingActionButton2.setColorPressed(R.color.android_green);


        materialDesignFAM.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    materialDesignFAM.getMenuIconView().setImageResource(R.drawable.ic_close);
                } else {
                    materialDesignFAM.getMenuIconView().setImageResource(R.drawable.ic_share);
                }
            }
        });


        viewPager.setAdapter(new MyAdapter(getChildFragmentManager(), investigador));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        TextView txtTitulo = (TextView) x.findViewById(R.id.txtTitulo_toolbar);
        txtTitulo.setText(investigador.getNombre());

        return x;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_compartir_facebook:
                Toast.makeText(getActivity(), "Compartiendo en facebook", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_compartir_twitter:
                Toast.makeText(getActivity(), "Compartiendo en twitter", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    class MyAdapter extends FragmentPagerAdapter {
        private Investigador investigador;

        public MyAdapter(FragmentManager fm, Investigador investigador) {
            super(fm);
            this.investigador = investigador;
        }

        /**
         * Return fragment with respect to Position .
         */
        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            switch (position) {
                case 0:
                    f = DetalleDeInvestigadorFragment.newInstance(investigador);

                    break;
                case 1:
                    f = InfoPersonalFragment.newInstance(investigador);
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        /**
         * This method returns the title of the tab according to the position.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.texto_datos_personales);
                case 1:
                    return getResources().getString(R.string.texto_informacion_adicional);
            }
            return null;
        }
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

}
