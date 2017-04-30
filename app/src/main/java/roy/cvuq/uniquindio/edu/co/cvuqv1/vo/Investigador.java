package roy.cvuq.uniquindio.edu.co.cvuqv1.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by USUARIO on 22/04/2017.
 */

public class Investigador implements Parcelable {

    private String nombre;
    private String genero;
    private String nacionalidad;
    private String categoria;
    private String apellido;
    private String email;
    private String link;
    private ArrayList<String> lineasInvestigacion;
    private String formacion;
    private Grupo grupo;

    public Investigador() {
    }

    protected Investigador(Parcel in) {
        nombre = in.readString();
        genero = in.readString();
        nacionalidad = in.readString();
        categoria = in.readString();
        apellido = in.readString();
        email = in.readString();
        link = in.readString();
        lineasInvestigacion = in.createStringArrayList();
        formacion = in.readString();
        grupo = in.readParcelable(Grupo.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(genero);
        dest.writeString(nacionalidad);
        dest.writeString(categoria);
        dest.writeString(apellido);
        dest.writeString(email);
        dest.writeString(link);
        dest.writeStringList(lineasInvestigacion);
        dest.writeString(formacion);
        dest.writeParcelable(grupo, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Investigador> CREATOR = new Creator<Investigador>() {
        @Override
        public Investigador createFromParcel(Parcel in) {
            return new Investigador(in);
        }

        @Override
        public Investigador[] newArray(int size) {
            return new Investigador[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<String> getLineasInvestigacion() {
        return lineasInvestigacion;
    }

    public void setLineasInvestigacion(ArrayList<String> lineasInvestigacion) {
        this.lineasInvestigacion = lineasInvestigacion;
    }

    public String getFormacion() {
        return formacion;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }
}
