/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.workers;

import app.beans.Personne;
import java.util.List;

/**
 * Permet de gérer la navigation entre personnes
 * @author AbrahamL
 */
public class PersonneManager {
    
    //Partie déclarations
    private int index = 0; //Index courant
    private List<Personne> listePersonnes; //La liste de personnes
    
    public PersonneManager(){
        
    }
    
    public Personne courantPersonne(){
        //Retour de la personne à l'index actuelle
        return listePersonnes.get(index);
    }
    
    public Personne debutPersonne(){
        //Retour du premier index
        return listePersonnes.get(0);
    }
    
    public Personne finPersonne(){
        //Retour de la dernière personne
        return listePersonnes.get(listePersonnes.size()-1);
    }
    
    public Personne precedentPersonne(){
        //Gestion du nombre (Si l'index est en dessous de 0, retour au max, sinon index est décrémenté)
        index = index - 1 < 0 ? listePersonnes.size() - 1 : index -1;
        
        //Retour de la personne
        return listePersonnes.get(index);
    }
    
    public Personne setPersonnes(List<Personne> listePersonnes){
        this.listePersonnes = listePersonnes;
        
        //Retour de la permière personne
        return debutPersonne();
    }
    
    public Personne suivantPersonne(){
        //Gestion du nombre (Si l'index est en dessus du max, retour à 0, sinon index est incrémenté)
        index = index + 1 > listePersonnes.size() - 1 ? 0 : index + 1;
        
        //Retour de la personne
        return listePersonnes.get(index);
    }
}
