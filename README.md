# PartPicker

## Descrizione
Un applicazione per la creazione e condivisione di configurazioni di PC tra utenti appassionati di hardware.

### Prerequisiti
- XAMPP
- Java 21 o superiore
- MySQL Workbench

### Passaggi da seguire

1. **Avvio di XAMPP**  
   Apri il Pannello di Controllo di XAMPP e avvia i moduli **Apache** e **MySQL**.

2. **Creazione delle tabelle**  
   In MySQLWorkbench, crea un nuovo database per l'applicazione usando le istruzioni in **partpicker.sql**

3. **Popolamento delle Tabelle**  
   Riempi le tabelle usando le istruzioni in **fill.sql**

4. **Avvio dell'applicazione**
   Avvia l'app usando il comando **java -jar .\PartPicker-all.jar** oppure **./gradlew run**
