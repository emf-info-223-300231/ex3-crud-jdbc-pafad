package app.helpers;

import java.util.Locale;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Window;

/**
 * Affichage de popups.
 *
 * @author P-A Mettraux / J.C Stritt
 */
public class JfxPopup {

  /**
   * Affiche une popup de type "error" centrée sur son propriétaire.
   *
   * @param owner  la fenêtre propriétaire de la popup (pour centrage) ou null
   * @param title  le titre pour la popup
   * @param header un entête (si null = pas d'entête)
   * @param error  le message d'erreur
   */
  public static void displayError(Window owner, String title, String header, String error) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.initOwner(owner);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(error);
    alert.showAndWait();
  }

  /**
   * Affiche une popup de type "error" centrée sur l'écran.
   *
   * @param title  le titre pour la popup
   * @param header un entête (si null = pas d'entête)
   * @param error  le message d'erreur
   */
  public static void displayError(String title, String header, String error) {
    displayError(null, title, header, error);
  }



  /**
   * Affiche une popup de type "information" centrée sur son propriétaire.
   *
   * @param owner       la fenêtre propriétaire de la popup (pour centrage) ou null
   * @param title       le titre pour la popup
   * @param header      un entête (si null = pas d'entête)
   * @param information le message d'information
   */
  public static void displayInformation(Window owner, String title, String header, String information) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.initOwner(owner);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(information);
    alert.showAndWait();
  }

  /**
   * Affiche une popup de type "information" centrée sur l'écran.
   *
   * @param title       le titre pour la popup
   * @param header      un entête (si null = pas d'entête)
   * @param information le message d'information
   */
  public static void displayInformation(String title, String header, String information) {
    displayInformation(null, title, header, information);
  }



  /**
   * Affiche une popup de type "avertissement" centrée sur son propriétaire.
   *
   * @param owner   la fenêtre propriétaire de la popup (pour centrage) ou null
   * @param titl    le titre pour la popup
   * @param header  un entête (si null = pas d'entête)
   * @param warning le message d'avertissement
   */
  public static void displayWarning(Window owner, String title, String header, String warning) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.initOwner(owner);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(warning);
    alert.showAndWait();
  }

  /**
   * Affiche une popup de type "avertissement" centrée sur l'écran.
   *
   * @param titl    le titre pour la popup
   * @param header  un entête (si null = pas d'entête)
   * @param warning le message d'avertissement
   */
  public static void displayWarning(String title, String header, String warning) {
    displayWarning(null, title, header, warning);
  }



  /**
   * Affiche une popup de type "confirmation" (avec OK ou Annuler)
   * centrée sur son propriétaire..
   *
   * @param owner   la fenêtre propriétaire de la popup (pour centrage) ou null
   * @param title   le titre pour la popup
   * @param header  un entête (si null = pas d'entête)
   * @param message un message pour la popup
   *
   * @return true si OK a été sélectionné
   */
  public static boolean askConfirmation(Window owner, String title, String header, String message) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.initOwner(owner);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(message);

    Optional<ButtonType> rep = alert.showAndWait();
    return rep.get() == ButtonType.OK;
  }

  /**
   * Affiche une popup de type "confirmation" (avec OK ou Annuler)
   * centrée sur l'écran.
   *
   * @param title   le titre pour la popup
   * @param header  un entête (si null = pas d'entête)
   * @param message un message pour la popup
   *
   * @return true si OK a été sélectionné
   */
  public static boolean askConfirmation(String title, String header, String message) {
    return askConfirmation(null, title, header, message);
  }



  /**
   * Affiche une popup de type "confirmation" (avec OUI/NON)
   * centrée sur son propriétaire.
   *
   * @param owner     la fenêtre propriétaire de la popup (pour centrage) ou null
   * @param title     le titre pour la popup
   * @param header    un entête (si null = pas d'entête)
   * @param message   un message pour la popup
   * @param defaultNo true le bouton par défaut est le "non"
   *
   * @return true si OUI a été sélectionné
   */
  public static boolean askYesNo(Window owner, String title, String header, String message, boolean defaultNo) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.initOwner(owner);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(message);

    // messages dans les boutons suivant la "Locale" par défaut
    String msgYes = "Yes";
    String msgNo = "No";
    switch (Locale.getDefault().getLanguage()) {
      case "fr":
        msgYes = "Oui";
        msgNo = "No";
        break;
      case "de":
        msgYes = "Ja";
        msgNo = "Nein";
        break;
      case "it":
        msgYes = "Si";
        msgNo = "No";
        break;
    }

    // création des boutons
    ButtonType btnYes = new ButtonType(msgYes);
    ButtonType btnNo = new ButtonType(msgNo);
    alert.getButtonTypes().setAll(btnYes, btnNo);

    // si le bouton NON est choisi par défaut
    if (defaultNo) {
      // déactive le bouton OUI par défaut
      Button yesButton = (Button) alert.getDialogPane().lookupButton(btnYes);
      yesButton.setDefaultButton(false);

      // active le bouton NON par défaut
      Button noButton = (Button) alert.getDialogPane().lookupButton(btnNo);
      noButton.setDefaultButton(true);
    }

    // affiche la popup et attent la réponse
    Optional<ButtonType> rep = alert.showAndWait();
    return rep.get() == btnYes;
  }

  /**
   * Affiche une popup de type "confirmation" (avec OUI/NON)
   * centrée sur l'écran.
   *
   * @param title     le titre pour la popup
   * @param header    un entête (si null = pas d'entête)
   * @param message   un message pour la popup
   * @param defaultNo true le bouton par défaut est le "non"
   *
   * @return true si OUI a été sélectionné
   */
  public static boolean askYesNo(String title, String header, String message, boolean defaultNo) {
    return askYesNo(null, title, header, message, defaultNo);
  }



  /**
   * Affiche une popup de type "demande d'info" centrée sur son propriétaire.
   *
   * @param owner  la fenêtre propriétaire de la popup (pour centrage) ou null
   * @param title  le titre pour la popup
   * @param header un entête (si null = pas d'entête)
   * @param label  un message
   *
   * @return une valeur de type string or null
   */
  public static String askInfo(Window owner, String title, String header, String label) {
    TextInputDialog dia = new TextInputDialog();
    dia.initOwner(owner);
    dia.setTitle(title);
    dia.setHeaderText(header);
    dia.setContentText(label);

    Optional<String> text = dia.showAndWait();
    return text.isPresent() ? text.get() : null;
  }

  /**
   * Affiche une popup de type "demande d'info" centrée sur l'écran.
   *
   * @param title  le titre pour la popup
   * @param header un entête (si null = pas d'entête)
   * @param label  un message
   *
   * @return une valeur de type string or null
   */
  public static String askInfo(String title, String header, String label) {
    return askInfo(null, title, header, label);
  }

}
