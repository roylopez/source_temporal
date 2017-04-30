package roy.cvuq.uniquindio.edu.co.cvuqv1.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USUARIO on 22/04/2017.
 */

public class Grupo implements Parcelable {

    private String nombre;
    private String sigla;
    private String link;
    private String categoria;
    private Investigador lider;
    private List<String> lineasInvestigacion;
    private List<Investigador> investigadores;
    private String email;
    private int foto;

    public Grupo() {
        investigadores = new ArrayList<Investigador>();
        lineasInvestigacion = new ArrayList<String>();
    }

    protected Grupo(Parcel in) {
        nombre = in.readString();
        sigla = in.readString();
        link = in.readString();
        categoria = in.readString();
        lider = in.readParcelable(Investigador.class.getClassLoader());
        lineasInvestigacion = in.createStringArrayList();
        investigadores = in.createTypedArrayList(Investigador.CREATOR);
        email = in.readString();
        foto = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(sigla);
        dest.writeString(link);
        dest.writeString(categoria);
        dest.writeParcelable(lider, flags);
        dest.writeStringList(lineasInvestigacion);
        dest.writeTypedList(investigadores);
        dest.writeString(email);
        dest.writeInt(foto);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Grupo> CREATOR = new Creator<Grupo>() {
        @Override
        public Grupo createFromParcel(Parcel in) {
            return new Grupo(in);
        }

        @Override
        public Grupo[] newArray(int size) {
            return new Grupo[size];
        }
    };

    public void setInvestigadores(List<Investigador> investigadores) {
        this.investigadores = investigadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Investigador getLider() {
        return lider;
    }

    public void setLider(Investigador lider) {
        this.lider = lider;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public List getInvestigadores() {
        return investigadores;
    }

    public void setInvestigadores(ArrayList investigadores) {
        this.investigadores = investigadores;
    }

    public List<String> getLineasInvestigacion() {
        return lineasInvestigacion;
    }

    public void setLineasInvestigacion(List<String> lineasInvestigacion) {
        this.lineasInvestigacion = lineasInvestigacion;
    }
}