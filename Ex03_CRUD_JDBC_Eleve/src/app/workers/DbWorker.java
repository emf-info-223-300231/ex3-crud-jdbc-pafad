package app.workers;

import app.beans.Personne;
import app.exceptions.MyDBException;
import app.helpers.SystemLib;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbWorker implements DbWorkerItf {

    private Connection dbConnexion;
    private List<Personne> listePersonnes;
    private ArrayList<String> listeChamps;
    
    /**
     * Constructeur du worker
     */
    public DbWorker() {
        listePersonnes = new ArrayList<>();
        listeChamps = new ArrayList<>();
    }

    @Override
    public void connecterBdMySQL(String nomDB) throws MyDBException {
        final String url_local = "jdbc:mysql://localhost:3306/" + nomDB;
        final String url_remote = "jdbc:mysql://LAPEMFB37-21.edu.net.fr.ch:3306/" + nomDB;
        final String user = "root";
        final String password = "emf123";

        System.out.println("url:" + url_local);
        try {
            dbConnexion = DriverManager.getConnection(url_local, user, password);
        } catch (SQLException ex) {
            throw new MyDBException(SystemLib.getFullMethodName(), ex.getMessage());
        }
    }

    @Override
    public void connecterBdHSQLDB(String nomDB) throws MyDBException {
        final String url = "jdbc:hsqldb:file:" + nomDB + ";shutdown=true";
        final String user = "SA";
        final String password = "";
        System.out.println("url:" + url);
        try {
            dbConnexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            throw new MyDBException(SystemLib.getFullMethodName(), ex.getMessage());
        }
    }

    @Override
    public void connecterBdAccess(String nomDB) throws MyDBException {
        final String url = "jdbc:ucanaccess://" + nomDB;
        System.out.println("url=" + url);
        try {
            dbConnexion = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            throw new MyDBException(SystemLib.getFullMethodName(), ex.getMessage());
        }
    }

    @Override
    public void deconnecter() throws MyDBException {
        try {
            if (dbConnexion != null) {
                dbConnexion.close();
            }
        } catch (SQLException ex) {
            throw new MyDBException(SystemLib.getFullMethodName(), ex.getMessage());
        }
    }

    @Override
    public List<Personne> lirePersonnes() throws MyDBException { 
        //Récupération des personnes
        try{
            //Pour éviter des injections SQL
            PreparedStatement requete = dbConnexion.prepareStatement("select PK_PERS, Nom, Prenom, Date_Naissance, No_rue, Rue, NPA, Ville, Actif, Salaire, date_modif from t_personne");
        
            //Execution de la requête
            ResultSet rs = requete.executeQuery();
            
            //Pour chaque résultats
            while(rs.next()){
                listePersonnes.add(new Personne(
                        rs.getInt("PK_PERS"), //int pkPers 
                        rs.getString("Nom"), //String nom
                        rs.getString("Prenom"), //String prenom
                        rs.getDate("Date_Naissance"), //Date dateNaissance
                        rs.getInt("No_rue"), //int noRue
                        rs.getString("Rue"), //String rue
                        rs.getInt("NPA"), //int npa
                        rs.getString("Ville"), //String localite
                        rs.getBoolean("Actif"), //boolean actif
                        rs.getDouble("Salaire"), //double salaire
                        rs.getDate("date_modif") //Date dateModif
                )); //Ajout de la personne
            }
        } catch (SQLException ex){
            throw new MyDBException(SystemLib.getFullMethodName(), ex.getMessage());
        }
        return listePersonnes;
    }
    
    @Override
    public Personne lire(int index) throws MyDBException {
        //Lire la personne à l'index actuelle
        return listePersonnes.get(index);
    }
    
    @Override
    public void modifier(Personne personne) throws MyDBException {
        
        //Requête vide
        PreparedStatement requete;
        
        //Construction de la requête finale en fonction de ce qu'il y a comme modification, cette requête peut économiser des perfomances en comparant les données
        String requeteFinale = "";
        try{
            
            //Préparation de la requête pour éviter les injections
            requete = dbConnexion.prepareStatement("select PK_PERS, Nom, Prenom, Date_Naissance, No_rue, Rue, NPA, Ville, Actif, Salaire, date_modif, no_modif from t_personne WHERE PK_PERS = " + personne.getPkPers());
            
            //Execution du select pour comparer les données
            ResultSet rs = requete.executeQuery();
            
            //Si les modifs sont présentes
            boolean modif = false;
            
            //S'il y a des résultats
            while(rs.next()){
                
                //Si le nom a été modifié
                if(!personne.getNom().equals(rs.getString("Nom"))){
                    
                    //Ajout de la requête modifiant le nom
                    requeteFinale += "UPDATE t_personne SET Nom = " + personne.getNom() + " WHERE PK_PERS = " + personne.getPkPers() + ";\n";
                    
                    //Il y a une modification
                    modif = true;
                }
                
                //Si le prénom a été modifié
                if(!personne.getPrenom().equals(rs.getString("Prenom"))){
                    
                    //Ajout de la requête modifiant le prénom
                    requeteFinale += "UPDATE t_personne SET Prenom = " + personne.getPrenom() + " WHERE PK_PERS = " + personne.getPkPers() + ";\n";
                    
                    //Il y a une modification
                    modif = true;
                }
                
                //Si la date de naissance a été modifié
                if(!personne.getDateNaissance().equals(rs.getDate("Date_naissance"))){
                    
                    //Ajout de la requête modifiant la date de naissance
                    requeteFinale += "UPDATE t_personne SET Date_naissance = " + personne.getDateNaissance() + " WHERE PK_PERS = " + personne.getPkPers() + ";\n";
                
                    //Il y a une modification
                    modif = true;
                }
                
                //Si le numéro de rue a été modifié
                if(personne.getNoRue() != rs.getInt("No_rue")){
                    
                    //Ajout de la requête modifiant le numéro de rue
                    requeteFinale += "UPDATE t_personne SET No_rue = " + personne.getNoRue() + " WHERE PK_PERS = " + personne.getPkPers() + ";\n";
                
                    //Il y a une modification
                    modif = true;
                }
                
                //Si la rue a été modifié
                if(!personne.getRue().equals(rs.getString("Rue"))){
                    
                    //Ajout de la requête modifiant la rue
                    requeteFinale += "UPDATE t_personne SET Rue = " + personne.getRue() + " WHERE PK_PERS = " + personne.getPkPers() + ";\n";
                    
                    //Il y a une modification
                    modif = true;
                }
                
                //Si le code postal a été modifié
                if(personne.getNpa() != rs.getInt("NPA")){
                    
                    //Ajout de la requête modifiant le code postal
                    requeteFinale += "UPDATE t_personne SET NPA = " + personne.getNpa() + " WHERE PK_PERS = " + personne.getPkPers() + ";\n";
                    
                    //Il y a une modification
                    modif = true;
                }
                
                //Si la ville a été modifié
                if(!personne.getLocalite().equals(rs.getString("Ville"))){
                    
                    //Ajout de la requête modifiant la ville
                    requeteFinale += "UPDATE t_personne SET Ville = " + personne.getLocalite() + " WHERE PK_PERS = " + personne.getPkPers() + ";\n";
                    
                    //Il y a une modification
                    modif = true;
                }
                
                //Si le boolean actif a été modifié
                if(personne.isActif() != rs.getBoolean("Actif")){
                    
                    //Ajout de la requête modifiant l'activité
                    requeteFinale += "UPDATE t_personne SET Actif = " + personne.isActif() + " WHERE PK_PERS = " + personne.getPkPers() + ";\n";
                    
                    //Il y a une modification
                    modif = true;
                }
                
                //Si le salaire a été modifié
                if(personne.getSalaire() != rs.getDouble("Salaire")){
                    
                    //Ajout de la requête modifiant le salaire
                    requeteFinale += "UPDATE t_personne SET Salaire = " + personne.getSalaire() + " WHERE PK_PERS = " + personne.getPkPers() + ";\n";
                    
                    //Il y a une modification
                    modif = true;
                }
                
                //S'il y a eu au moins une modification
                if(modif){
                    
                    //Mise à jour de la date de modification
                    requeteFinale += "UPDATE t_personne SET date_modif = " + Date.valueOf(LocalDate.now()) + " WHERE PK_PERS = " + personne.getPkPers() + ";\n";
                    
                    //Mise à jour du numéro de la modification
                    requeteFinale += "UPDATE t_personne SET no_modif = " + (rs.getInt("no_modif") + 1) + " WHERE PK_PERS = " + personne.getPkPers() + ";\n";
                }
                
                //Pour tester elle affiche la requête finale construite
                System.out.println(requeteFinale);
                
                //Preparation de la requête pour éviter les injections comme toujours
                requete = dbConnexion.prepareStatement(requeteFinale);
                
                //On met tout à jour avec cette méthode
                requete.executeUpdate();
            }
            
        } catch(SQLException ex){
            ex.printStackTrace();
            throw new MyDBException(SystemLib.getFullMethodName(), ex.getMessage());
        }
    }
    
    @Override
    public void effacer(Personne personne) throws MyDBException {
        PreparedStatement st;
        try{
            st = dbConnexion.prepareStatement("delete from t_personne where PK_PERS = ?");
            st.setInt(0, personne.getPkPers());
            st.executeUpdate();
        }catch (SQLException ex){
            throw new MyDBException(SystemLib.getFullMethodName(), ex.getMessage());
        }
    }
    
    @Override
    public void creer(Personne personne) throws MyDBException {
        //Pour éviter les injections SQL
        PreparedStatement st;
        try{
            st = dbConnexion.prepareStatement(
                    "insert into t_personne("
                            + "nom, prenom, date_naissance, no_rue, rue, npa, ville, actif, salaire, date_modif, no_modif"
                            + ") VALUES (?,?,?,?,?,?,?,?,?,?,?)"
            );
            
            //On place toute les valeurs justes dans la requête en fonction de la position
            st.setString(1, personne.getNom()); //Nom
            st.setString(2, personne.getPrenom()); //Prénom
            st.setDate(3, convertirDate(personne.getDateNaissance()));//Date de naissance
            st.setInt(4, personne.getNoRue()); //Numéro de rue
            st.setString(5, personne.getRue()); //La rue
            st.setInt(6, personne.getNpa()); //Le code postal
            st.setString(7, personne.getLocalite()); //La ville
            st.setBoolean(8, personne.isActif()); //La personne est active ou non 
            st.setDouble(9, personne.getSalaire()); //Le salaire
            st.setDate(10, convertirDate(personne.getDateModif())); //La date de la modif
            st.setInt(11, 0); //Le numéro de la modif
        
            st.executeUpdate();
        } catch (SQLException ex){
            throw new MyDBException(SystemLib.getFullMethodName(), ex.getMessage());
        }
    }

    /**
     * Permet la convetion de java.util.Date en java.sql.Date
     * @param date la date (java.util.Date)
     * @return La date (java.sql.Date)
     */
    public java.sql.Date convertirDate(java.util.Date date){
        return new java.sql.Date(date.getTime());   
    }
    
}
