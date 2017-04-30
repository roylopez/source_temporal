package roy.cvuq.uniquindio.edu.co.cvuqv1.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.AvisoConexionFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_gupo.AddGroupFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_investigador.AddInvestigatorFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.HomeFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.busqueda.SearchDialogFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.busqueda.SearchFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.grupos.ListaDeGruposFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.grupos.TabFragmentDetalleGrupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.investigadores.ListaInvestigadoresFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.investigadores.TabDetalleDeInvestigadorFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Grupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener,
        HomeFragment.OnOptionHomeListener,
        ListaInvestigadoresFragment.OnInvestigadorSeleccionadoListener,
        ListaDeGruposFragment.OnGrupoSeleccionadoListener,
        SearchDialogFragment.OnSearchListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Fragment actualFragment;
    private int IdContextName;
    private boolean isRestore;
    private TextView txtContextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);
        view.setOnClickListener(this);

        txtContextName = (TextView) findViewById(R.id.context_name);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        isRestore = false;

        if (savedInstanceState != null) {
            isRestore = true;
            actualFragment = getSupportFragmentManager().getFragment(savedInstanceState, "actualFragment");
            drawFragmentWithContextName(actualFragment, savedInstanceState.getInt("contextName"), actualFragment.getTag());
        } else {
            drawFragmentWithContextName(new HomeFragment(), R.string.texto_inicio, getResources().getString(R.string.tag_fragment_home));
        }
    }

    private boolean isInternetAccess() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        isRestore = false;
        int option = item.getItemId();

        switch (option) {
            case R.id.nav_investigadores:
                drawFragmentWithContextName(new ListaInvestigadoresFragment(), R.string.texto_titulo_lista_investigadores, getResources().getString(R.string.tag_fragment_listainvestigadores));
                break;

            case R.id.nav_buscar:
                SearchDialogFragment dialogo = new SearchDialogFragment();
                dialogo.setStyle(dialogo.STYLE_NORMAL, R.style.DialogoTitulo);
                dialogo.show(getSupportFragmentManager(), MainActivity.class.getName());
                break;

            case R.id.nav_actualizar:

                if (isInternetAccess()) {
                    Toast.makeText(this, "Con internet", Toast.LENGTH_SHORT).show();
                    //Hacer lo necesario para que obtenga la información de la base de datos

                } else {
                    final AvisoConexionFragment aviso = new AvisoConexionFragment();
                    aviso.setStyle(aviso.STYLE_NO_TITLE, R.style.CardView);
                    aviso.show(getSupportFragmentManager(), MainActivity.class.getName());

                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            aviso.dismiss();
                        }
                    }, 3000);
                }
                break;

            case R.id.nav_grupos:
                drawFragmentWithContextName(new ListaDeGruposFragment(), R.string.texto_grupos, getResources().getString(R.string.tag_fragment_listagrupos));
                break;

            case R.id.nav_idioma_espaniol:
                break;

            case R.id.nav_idioma_ingles:
                break;

            default:
                break;

        }

        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.app_navigation_drawer_header) {
            drawFragmentWithContextName(new HomeFragment(), R.string.texto_inicio, getResources().getString(R.string.tag_fragment_home));
            drawerLayout.closeDrawers();
        }
    }

    @Override
    public void onOptionHomeSelected(int optionId) {
        isRestore = false;
        switch (optionId) {
            case R.id.option_groups:
                drawFragmentWithContextName(new ListaDeGruposFragment(), R.string.texto_grupos, getResources().getString(R.string.tag_fragment_listagrupos));
                break;

            case R.id.option_add_group:
                drawFragmentWithContextName(new AddGroupFragment(), R.string.texto_nuevo_grupo, getResources().getString(R.string.tag_fragment_addgroup));
                break;

            case R.id.option_investigators:
                drawFragmentWithContextName(new ListaInvestigadoresFragment(), R.string.texto_titulo_lista_investigadores, getResources().getString(R.string.tag_fragment_listainvestigadores));
                break;

            case R.id.option_add_investigator:
                drawFragmentWithContextName(new AddInvestigatorFragment(), R.string.texto_nuevo_investigador, getResources().getString(R.string.tag_fragment_addinvestigator));
                break;

            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("contextName", IdContextName);
        getSupportFragmentManager().putFragment(outState, "actualFragment", actualFragment);
    }

    @Override
    public void onInvestigadorSeleccionado(Investigador investigador) {
        isRestore = false;

        TabDetalleDeInvestigadorFragment fragment = new TabDetalleDeInvestigadorFragment();
        Bundle bundle = new Bundle();

        /*
        Grupo grupo = investigador.getGrupo();
        grupo.setLider(null);
        grupo.setInvestigadores(new ArrayList<>());
        investigador.setGrupo(grupo);
        */

        bundle.putParcelable("investigador", investigador);
        fragment.setArguments(bundle);
        drawFragmentWithContextName(fragment, R.string.texto_titulo_lista_investigadores, getResources().getString(R.string.tag_fragment_detalleinvestigador));

    }

    @Override
    public void onGrupoSeleccionado(Grupo grupo) {
        isRestore = false;
        TabFragmentDetalleGrupo fragment = new TabFragmentDetalleGrupo();
        Bundle bundle = new Bundle();

        /*
        ArrayList<Investigador> investigadores = (ArrayList<Investigador>) grupo.getInvestigadores();

        for (int i = 0; i < investigadores.size(); i++) {
            investigadores.get(i).setGrupo(null);
        }
        grupo.setInvestigadores((List)investigadores);
        */

        bundle.putParcelable("grupo", grupo);
        fragment.setArguments(bundle);
        drawFragmentWithContextName(fragment, R.string.texto_grupos, getResources().getString(R.string.tag_fragment_detallegrupo));
    }

    @Override
    public void onAddInvestigator() {
        isRestore = false;
        drawFragmentWithContextName(new AddInvestigatorFragment(), R.string.texto_nuevo_investigador, getResources().getString(R.string.tag_fragment_addinvestigator));
    }

    @Override
    public void onAddGroup() {
        isRestore = false;
        drawFragmentWithContextName(new AddGroupFragment(), R.string.texto_nuevo_grupo, getResources().getString(R.string.tag_fragment_addgroup));
    }

    @Override
    public void onSearchListener(ArrayList<Grupo> grupos, ArrayList<Investigador> investigadores) {
        isRestore = false;
        SearchFragment searchFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("grupos", grupos);
        bundle.putParcelableArrayList("investigadores", investigadores);
        searchFragment.setArguments(bundle);
        drawFragmentWithContextName(searchFragment, R.string.texto_titulo_search, getResources().getString(R.string.tag_fragment_search));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            isRestore = true;
            super.onBackPressed();
            Fragment fragmentAfterBackPress = getCurrentFragment();

            if (fragmentAfterBackPress instanceof AddInvestigatorFragment) {
                drawFragmentWithContextName(new AddInvestigatorFragment(), R.string.texto_nuevo_investigador, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof ListaDeGruposFragment) {
                drawFragmentWithContextName(new ListaDeGruposFragment(), R.string.texto_grupos, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof HomeFragment) {
                drawFragmentWithContextName(new HomeFragment(), R.string.texto_inicio, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof ListaInvestigadoresFragment) {
                drawFragmentWithContextName(new ListaInvestigadoresFragment(), R.string.texto_lista_investigadores, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof TabFragmentDetalleGrupo) {
                drawFragmentWithContextName(fragmentAfterBackPress, R.string.texto_grupos, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof TabDetalleDeInvestigadorFragment) {
                drawFragmentWithContextName(fragmentAfterBackPress, R.string.texto_lista_investigadores, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof AddGroupFragment) {
                drawFragmentWithContextName(new AddGroupFragment(), R.string.texto_nuevo_grupo, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof SearchFragment) {
                drawFragmentWithContextName(fragmentAfterBackPress, R.string.texto_titulo_search, fragmentAfterBackPress.getTag());
            }

        }
    }

    private Fragment getCurrentFragment() {
        if (actualFragment instanceof HomeFragment) {
            try {
                FragmentManager fragmentManager = getSupportFragmentManager();
                String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
                Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
                return currentFragment;
            } catch (ArrayIndexOutOfBoundsException e) {
                finish();
                return null;
            }
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
            Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
            return currentFragment;
        }
    }

    private boolean navigateToSame(Fragment fragment) {
        boolean flag = false;
        if (actualFragment instanceof HomeFragment && fragment instanceof HomeFragment) {
            flag = true;
        } else if (actualFragment instanceof ListaDeGruposFragment && fragment instanceof ListaDeGruposFragment) {
            flag = true;
        } else if (actualFragment instanceof ListaInvestigadoresFragment && fragment instanceof ListaInvestigadoresFragment) {
            flag = true;
        } else if (actualFragment instanceof AddGroupFragment && fragment instanceof AddGroupFragment) {
            flag = true;
        } else if (actualFragment instanceof AddInvestigatorFragment && fragment instanceof AddInvestigatorFragment) {
            flag = true;
        }
        return flag;
    }

    private void drawFragmentWithContextName(Fragment fragment, int contextName, String tag) {

        if (isRestore) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, fragment, tag)
                    .commit();
        } else {
            if (navigateToSame(fragment)) {

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, fragment, tag)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, fragment, tag)
                        .addToBackStack(tag)
                        .commit();
            }
        }

        actualFragment = fragment;
        IdContextName = contextName;

        txtContextName.setText(contextName);
    }

}
