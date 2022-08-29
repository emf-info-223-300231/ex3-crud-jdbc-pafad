package app.workers;

import app.beans.Personne;
import app.exceptions.MyDBException;
import app.helpers.SystemLib;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DbWorkerTest {

  static DbWorker dbWrk;
  static PersonneManager manPers;
  static String DB_NAME = "223_personne_1table";
  static int lastPK = -1;

  public DbWorkerTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
    dbWrk = new DbWorker();
    manPers = new PersonneManager();
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
    // dbWrk.deconnecter();
  }

  @Test
  public void a0_testerConnecterMYSQL() throws MyDBException {
    System.out.println(SystemLib.getTestCurrentMethod());
    dbWrk.connecterBdMySQL(DB_NAME);
  }

  @Test
  public void b_testerLirePersonnes() throws MyDBException {
    System.out.println(SystemLib.getCurrentMethod());
    List<Personne> personnes = dbWrk.lirePersonnes();
    manPers.setPersonnes(personnes);
    boolean ok = !personnes.isEmpty();
    if (ok) {
      int cnt = 0;
      for (Personne p : personnes) {
        cnt++;
        System.out.println("  " + cnt + ". " + p);
      }
    }
    assertTrue(ok);
  }

  @Test
  public void c1_testerDebutPersonne() {
    System.out.println(SystemLib.getCurrentMethod());
    Personne p = manPers.debutPersonne();
    boolean ok = p != null;
    if (ok) {
      System.out.println("  " + p);
    }
    assertTrue(ok);
  }

  @Test
  public void c1_testerFinPersonne() {
    System.out.println(SystemLib.getCurrentMethod());
    Personne p = manPers.finPersonne();
    boolean ok = p != null;
    if (ok) {
      System.out.println("  " + p);
    }
    assertTrue(ok);
  }

  @Test
  public void d_testerCreer() throws MyDBException {
    System.out.println(SystemLib.getCurrentMethod());
    Personne federer = new Personne(true);
    dbWrk.creer(federer);
    lastPK = federer.getPkPers();
  }

  @Test
  public void e_testerLireUnePersonne() throws MyDBException {
    System.out.println(SystemLib.getCurrentMethod());
    boolean ok = lastPK >= 0;
    if (ok) {
      Personne p = dbWrk.lire(lastPK);
      ok = p != null;
      System.out.println("  " + p);
    }
    assertTrue(ok);
  }

  @Test
  public void f_testerModifier() throws MyDBException {
    System.out.println(SystemLib.getCurrentMethod());
    String NOM_TEST = "Durand";
    if (lastPK >= 0) {
      Personne p1 = dbWrk.lire(lastPK);
      String savedName = p1.getNom();
      p1.setNom(NOM_TEST);
      dbWrk.modifier(p1);
      System.out.println("  " + p1);
      Personne p2 = dbWrk.lire(p1.getPkPers());
      if (p2.getNom().equals(NOM_TEST)) {
        p1.setNom(savedName);
        dbWrk.modifier(p1);
      }
    }
  }

  @Test
  public void g_testerEffacer() throws MyDBException {
    System.out.println(SystemLib.getCurrentMethod());
    boolean ok = lastPK >= 0;
    if (lastPK >= 0) {
      Personne p = dbWrk.lire(lastPK);
      dbWrk.effacer(p);
    }
  }

  @Test
  public void z_testerDeconnecter() throws MyDBException {
    System.out.println(SystemLib.getCurrentMethod());
    dbWrk.deconnecter();
  }

}
