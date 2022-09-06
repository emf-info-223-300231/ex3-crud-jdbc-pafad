package app.workers;

import app.beans.Personne;
import app.exceptions.MyDBException;
import java.util.List;

public interface DbWorkerItf {

  void connecterBdMySQL( String nomDB ) throws MyDBException;
  void connecterBdHSQLDB( String nomDB ) throws MyDBException;
  void connecterBdAccess( String nomDB ) throws MyDBException;
  List<Personne> lirePersonnes() throws MyDBException;
  void deconnecter() throws MyDBException; 
  void effacer(Personne personne) throws MyDBException;
  void creer(Personne personne) throws MyDBException;
  void modifier(Personne personne) throws MyDBException;
  Personne lire(int index) throws MyDBException;

}
