package sofka.correo;

import java.util.Objects;

import reactor.core.publisher.Flux;

public class Correo {
    private String nombre;
    private String dominio;
    private Boolean haveCorreos;

    public Correo(String nombre, String dominio, boolean haveCorreos) {
        this.nombre = nombre;
        this.dominio = dominio;
        this.haveCorreos = haveCorreos;
    }

    public Correo() {
    }

    public Boolean getHaveCorreos() {
        return haveCorreos;
    }

    public void setHaveCorreos(Boolean haveCorreos) {
        this.haveCorreos = haveCorreos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String correo) {
        this.nombre = correo;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    @Override
    public String toString() {
        return "Correo{" +
                nombre + dominio +
                '}';
    }

    public String toStringBool() {
        return "Correo{" +
                "email=" + nombre + dominio + " " +"bool: " + haveCorreos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correo correo = (Correo) o;
        return Objects.equals(this.nombre, correo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    public Flux<Correo> defaultIfEmpty(Correo fluxEstudiantes) {
        return null;
    }

    public void cambiarEstado() {
        if (this.haveCorreos) {
            this.haveCorreos = false;
        }
    }
}
