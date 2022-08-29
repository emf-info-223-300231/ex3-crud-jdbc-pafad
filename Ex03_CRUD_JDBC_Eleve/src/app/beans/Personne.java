package app.beans;

import app.helpers.DateTimeLib;
import java.util.Date;

/**
 * Bean "Personne".
 *
 * @author mettrauxpa
 */
public class Personne {
  private int pkPers;
  private String nom;
  private String prenom;
  private Date dateNaissance;
  private int noRue;
  private String rue;
  private int npa;
  private String localite;
  private boolean actif;
  private double salaire;
  private Date dateModif;

  public Personne( int pkPers, String nom, String prenom, Date dateNaissance,
          int noRue, String rue, int npa, String localite, boolean actif,
          double salaire, Date dateModif ) {
    this.pkPers = pkPers;
    this.nom = nom;
    this.prenom = prenom;
    this.dateNaissance = dateNaissance;
    this.noRue = noRue;
    this.rue = rue;
    this.npa = npa;
    this.localite = localite;
    this.actif = actif;
    this.salaire = salaire;
    this.dateModif = dateModif;
  }

  public Personne(  String nom, String prenom, Date dateNaissance,
          int noRue, String rue, int npa, String localite, boolean actif,
          double salaire, Date dateModif  ) {
    this(-1, nom, prenom, dateNaissance, noRue, rue, npa, localite, actif, salaire, dateModif);
  }

  public Personne () {
  }
  public Personne (boolean federer) {
       this("Federer", "Roger", DateTimeLib.createDate(8, 8, 1981), 
               17, "Rue du Nord", 4001, "Bâle", true, 1000000d,DateTimeLib.createDate(8, 8, 1981));
  }


  public double getSalaire() {
    return salaire;
  }

  public void setSalaire( double salaire ) {
    this.salaire = salaire;
  }

  public boolean isActif() {
    return actif;
  }

  public void setActif( boolean actif ) {
    this.actif = actif;
  }

  public String getLocalite() {
    return localite;
  }

  public void setLocalite( String localite ) {
    this.localite = localite;
  }

  public int getNpa() {
    return npa;
  }

  public void setNpa( int npa ) {
    this.npa = npa;
  }

  public String getRue() {
    return rue;
  }

  public void setRue( String rue ) {
    this.rue = rue;
  }

  public int getNoRue() {
    return noRue;
  }

  public void setNoRue( int no ) {
    this.noRue = no;
  }

  public Date getDateNaissance() {
    return dateNaissance;
  }

  public void setDateNaissance( Date dateNaissance ) {
    this.dateNaissance = dateNaissance;
  }

  public int getPkPers() {
    return pkPers;
  }

  public void setPkPers( int pk ) {
    this.pkPers = pk;
  }

  public String getNom() {
    return nom;
  }

  public void setNom( String nom ) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom( String prenom ) {
    this.prenom = prenom;
  }
  
  public Date getDateModif() {
    return dateModif;
  }

  public void setDateModif( Date dateModif ) {
    this.dateModif = dateModif;
  }

  @Override
  public String toString() {
    return "Personne: " + pkPers + ", nom=" + nom + ", prenom=" + prenom;
  }
  
  
}
