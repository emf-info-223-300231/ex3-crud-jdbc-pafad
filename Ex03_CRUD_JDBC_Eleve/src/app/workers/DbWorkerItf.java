package app.workers;

import app.beans.Personne;
import app.exceptions.MyDBException;
import java.util.List;

public interface DbWorkerItf {

  void connecterBdMySQL( String nomDB ) throws MyDBException;
  void connecterBdHSQLDB( String nomDB ) throws MyDBException;
  void connecterBdAccess( String nomDB ) throws MyDBException;
  void deconnecter() throws MyDBException; 
  void effacer();
  void creer();
  void modifier();
  Personne lire(int index);

}
