package app.presentation;

import app.beans.Personne;
import app.exceptions.MyDBException;
import app.helpers.DateTimeLib;
import app.helpers.JfxPopup;
import app.workers.DbWorker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import java.io.File;
import app.workers.DbWorkerItf;
import app.workers.PersonneManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author PA/STT
 */
public class MainCtrl implements Initializable {

    // DBs à tester
    private enum TypesDB {
        MYSQL, HSQLDB, ACCESS
    };

    // DB par défaut
    final static private TypesDB DB_TYPE = TypesDB.MYSQL;

    private DbWorkerItf dbWrk;
    private PersonneManager manPers;
    private boolean modeAjout;

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtPK;
    @FXML
    private TextField txtNo;
    @FXML
    private TextField txtRue;
    @FXML
    private TextField txtNPA;
    @FXML
    private TextField txtLocalite;
    @FXML
    private TextField txtSalaire;
    @FXML
    private CheckBox ckbActif;
    @FXML
    private Button btnDebut;
    @FXML
    private Button btnPrevious;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnEnd;
    @FXML
    private Button btnSauver;
    @FXML
    private Button btnAnnuler;
    @FXML
    private DatePicker dateNaissance;

    /*
   * METHODES NECESSAIRES A LA VUE
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Création de l'instance du worker avec la gestion de base de données
        dbWrk = new DbWorker();
        
        //Création d'un worker spécial qui servira à navigeur
        manPers = new PersonneManager();
        
        //Ouverture de la base de données
        ouvrirDB();
    }

    @FXML
    public void actionPrevious(ActionEvent event) {
        //Affichage de la personne précédente
        afficherPersonne(manPers.precedentPersonne());
    }

    @FXML
    public void actionNext(ActionEvent event) {
        //Affichage de la personne suivante
        afficherPersonne(manPers.suivantPersonne());
    }

    @FXML
    private void actionEnd(ActionEvent event) {
        //Affichage de la dernière personne
        afficherPersonne(manPers.finPersonne());
    }

    @FXML
    private void debut(ActionEvent event) {
        //affichage de la première personne
        afficherPersonne(manPers.debutPersonne());
    }

    @FXML
    private void menuAjouter(ActionEvent event) {
        //On rend invisible les boutons de navigation
        rendreVisibleBoutonsDepl(false);
        
        //On vire tout le contenu des txtFields
        effacerContenuChamps();
        
        //On met le mode ajout à true
        modeAjout = true;
    }

    @FXML
    private void menuModifier(ActionEvent event) {
        
        //On rend invisible les boutons de navigation
        rendreVisibleBoutonsDepl(false);
        
        //On désactive le mode ajout pour passer en modification
        modeAjout = false;
    }

    @FXML
    private void menuEffacer(ActionEvent event) {
        
        //Récupération de la peronne courante
        Personne personne = manPers.courantPersonne();
        
        //Boolean de confirmation pour la supression
        boolean confirm = JfxPopup.askConfirmation("Continuer", "Supression de personne", "Voulez-vous vraiment supprimer cette personne de la base de données ?");
        
        //S'il y a confirmation de l'utilisateur
        if(confirm){
            
            try{
                
                //Supression de la personne
                dbWrk.effacer(personne);
                
                //Mise à jour de la liste
                manPers.setPersonnes(dbWrk.lirePersonnes());
                
                //Afficher un message si tout se passe bien
                JfxPopup.displayInformation("Succès", "Suppression de personne", "Suppression de la personne effectué avec succès");
                
                //On affiche la dernière personne
                afficherPersonne(manPers.finPersonne());
                
            } catch (MyDBException ex){
                
                //Affichage d'un message d'erreur
                JfxPopup.displayError("Erreur", "Suppression de personne", ex.getMessage());
                
            }
            
        }
        
    }

    @FXML
    private void menuQuitter(ActionEvent event) {
        //On demande à l'utilisateur une confirmation
        boolean confirm = JfxPopup.askConfirmation("Quitter", "", "Voulez-vous vraiment quitter l'application ?");

        //Si oui
        if (confirm){
            
            //Quitter l'application
            Platform.exit();
            
        }
    }

    @FXML
    private void annulerPersonne(ActionEvent event) {
        
        //On affiche la personne courante
        afficherPersonne(manPers.courantPersonne());
        
        //On rend visible les boutons de navigation
        rendreVisibleBoutonsDepl(true);
    }

    @FXML
    private void sauverPersonne(ActionEvent event) {

        //Si l'on est en mode ajout
        if (modeAjout){
            try{
                //Création d'une personne à partir des valeurs de l'IHM.
                dbWrk.creer(new Personne(
                        txtNom.getText(),
                        txtPrenom.getText(),
                        DateTimeLib.localDateToDate(dateNaissance.getValue()),
                        Integer.parseInt(txtNo.getText()),
                        txtRue.getText(),
                        Integer.parseInt(txtNPA.getText()),
                        txtLocalite.getText(),
                        ckbActif.isSelected(),
                        Double.parseDouble(txtSalaire.getText()),
                        new Date()
                ));

                //Afficher un message si tout se passe bien
                JfxPopup.displayInformation("Succès", "Ajout de personne", "Ajout de la personne effectué avec succès");

                //Mise à jour de la liste
                manPers.setPersonnes(dbWrk.lirePersonnes());

                //On affiche la dernière personne, c'est à dire celle qui est créé
                afficherPersonne(manPers.finPersonne());

                //On met le mode ajout à false
                modeAjout = false;

                //Retour au menu principal
                rendreVisibleBoutonsDepl(true);

            } catch (MyDBException ex){ //S'il y a une erreur

                //Affichage d'un message d'erreur
                JfxPopup.displayError("Erreur", "Ajout de personne", ex.getMessage());
            }
        } else { //Si l'on modifie une personne

            try{
                
                //On prend la personne courante
                Personne personne = manPers.courantPersonne();
                
                //On set les modification dans l'objet de la personne
                personne.setNom(txtNom.getText());
                personne.setPrenom(txtPrenom.getText());
                personne.setDateNaissance(DateTimeLib.localDateToDate(dateNaissance.getValue()));
                personne.setNoRue(Integer.parseInt(txtNo.getText()));
                personne.setRue(txtRue.getText());
                personne.setNpa(Integer.parseInt(txtNPA.getText()));
                personne.setLocalite(txtLocalite.getText());
                personne.setActif(ckbActif.isSelected());
                personne.setSalaire(Double.parseDouble(txtSalaire.getText()));
                personne.setDateModif(new Date());
                
                //On passe au worker pour qu'il applique les changements dans la base de données
                dbWrk.modifier(personne);
                
                //Mise à jour de la liste
                manPers.setPersonnes(dbWrk.lirePersonnes());
                
                //Message si succès
                JfxPopup.displayInformation("Succès", "Modification de personne", "La personne a été modifié avec succès");
                
                //Retour au menu principal
                rendreVisibleBoutonsDepl(true);
                
                //Afficher la personne courante
                afficherPersonne(manPers.courantPersonne());
                
                //Retour au menu principal
            } catch (MyDBException ex){

                //Affichage d'un message d'erreur
                JfxPopup.displayError("Erreur", "Modification de personne", ex.getMessage());
            }
        }
    }

    public void quitter() {
        try{
            dbWrk.deconnecter(); // ne pas oublier !!!
        } catch (MyDBException ex){
            System.out.println(ex.getMessage());
        }
        Platform.exit();
    }

    /*
   * METHODES PRIVEES 
     */
    private void afficherPersonne(Personne p) {
        //Si la personne n'est pas null
        if (p != null){
            
            //On affiche les textes suivants
            txtPK.setText("" + p.getPkPers());
            txtPrenom.setText(p.getPrenom());
            txtNom.setText(p.getNom());
            txtLocalite.setText(p.getLocalite());
            txtNPA.setText("" + p.getNpa());
            txtSalaire.setText("" + p.getSalaire());
            ckbActif.setSelected(p.isActif());
            txtNo.setText("" + p.getNoRue());
            txtRue.setText(p.getRue());
            dateNaissance.setValue(new java.sql.Date(p.getDateNaissance().getTime()).toLocalDate());
            
        }
    }

    private void ouvrirDB() {
        try{
            switch (DB_TYPE){
                case MYSQL:
                    dbWrk.connecterBdMySQL("223_personne_1table");
                    break;
                case HSQLDB:
                    dbWrk.connecterBdHSQLDB("../data" + File.separator + "223_personne_1table");
                    break;
                case ACCESS:
                    dbWrk.connecterBdAccess("../data/access" + File.separator + "223_Personne_1table.accdb");
                    break;
                default:
                    System.out.println("Base de données pas définie");
            }
            System.out.println("------- DB OK ----------");
            
            //Lecture des personnes et ajout dans le navigateur et la db
            manPers.setPersonnes(dbWrk.lirePersonnes());
            
            //Afficher une personne
            afficherPersonne(manPers.precedentPersonne());
        } catch (MyDBException ex){
            JfxPopup.displayError("ERREUR", "Une erreur s'est produite", ex.getMessage());
            System.exit(1);
        }
    }

    private void rendreVisibleBoutonsDepl(boolean b) {
        btnDebut.setVisible(b);
        btnPrevious.setVisible(b);
        btnNext.setVisible(b);
        btnEnd.setVisible(b);
        btnAnnuler.setVisible(!b);
        btnSauver.setVisible(!b);
    }

    private void effacerContenuChamps() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtPK.setText("");
        txtNo.setText("");
        txtRue.setText("");
        dateNaissance.getEditor().clear(); //Pour virer le contenu du calendrier
        txtNPA.setText("");
        txtLocalite.setText("");
        txtSalaire.setText("");
        ckbActif.setSelected(false);
    }

}
