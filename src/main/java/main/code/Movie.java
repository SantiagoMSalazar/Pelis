package main.code;

import org.jetbrains.annotations.NotNull;

public class Movie{
    private String idPelicula;
    private String nombrePelicula;
    private float calificacionPelicula;
    private String directorPelicula;
    private String generoPelicula;
    private String estadoPelicula;
    private int anioPelicula;

    public Movie(String idPelicula, String nombrePelicula, float calificacionPelicula, String directorPelicula,
                 String generoPelicula, String estadoPelicula, int anioPelicula) {
        this.idPelicula = idPelicula;
        this.nombrePelicula = nombrePelicula;
        this.calificacionPelicula = calificacionPelicula;
        this.directorPelicula = directorPelicula;
        this.generoPelicula = generoPelicula;
        this.estadoPelicula = estadoPelicula;
        this.anioPelicula = anioPelicula;
    }

    public Movie() {
    }


    public String getIdPelicula() {
        return this.idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public float getCalificacionPelicula() {
        return calificacionPelicula;
    }

    public void setCalificacionPelicula(float calificacionPelicula) {
        this.calificacionPelicula = calificacionPelicula;
    }

    public String getDirectorPelicula() {
        return directorPelicula;
    }

    public void setDirectorPelicula(String directorPelicula) {
        this.directorPelicula = directorPelicula;
    }

    public String getGeneroPelicula() {
        return generoPelicula;
    }

    public void setGeneroPelicula(String generoPelicula) {
        this.generoPelicula = generoPelicula;
    }

    public String getEstadoPelicula() {
        return estadoPelicula;
    }

    public void setEstadoPelicula(String estadoPelicula) {
        this.estadoPelicula = estadoPelicula;
    }

    public int getAnioPelicula() {
        return anioPelicula;
    }

    public void setAnioPelicula(int anioPelicula) {
        this.anioPelicula = anioPelicula;
    }
    public int compareToName(@NotNull Movie o) {
        return Integer.parseInt(this.nombrePelicula)-Integer.parseInt(o.nombrePelicula);
    }
    public int compareToYear(@NotNull Movie o) {
        return this.anioPelicula-o.anioPelicula;
    }
    public int compareToGenero(@NotNull Movie o) {
        return Integer.parseInt(this.nombrePelicula)-Integer.parseInt(o.nombrePelicula);
    }
    public int compareToID(@NotNull Movie o) {
        return Integer.parseInt(this.idPelicula)-Integer.parseInt(o.idPelicula);
    }

    @Override
    public String toString() {
        return idPelicula + "," + nombrePelicula + "," + calificacionPelicula + "," + directorPelicula
                + "," + generoPelicula + "," + estadoPelicula + "," + anioPelicula ;
    }


}
