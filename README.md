# Projet3-OC
Mettez votre logique à l'épreuve - Projet 3 du parcours développeur d'application JAVA

## Synopsis
Ce projet est rédigé en Java. Il a pour but de permettre à l'utilisateur de 
jouer aux jeux du mastermind et du plus ou moins.
Le jeu se déroule en mode console.

## Prérequis
### Installation du JDK
Rendez vous sur le site [d'oracle](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
et téléchargez la dernière version du JDK disponible.

### Installation d'un IDE
Rendez vous sur le site [d'Intellij](https://www.jetbrains.com/idea/download/#section=windows) et téléchargez la version
community si nécessaire.

### Créer une variable d'environnement renvoyant vers le dossier bin de votre JDK
Si vous êtes sous windows, vous pouvez utiliser la commande **set PATH=%PATH%;Votre\chemin\au\jdk** pour ajouter le
dossier bin au path.
Si vous êtes sous linux, vous pouvez utiliser la commande **export PATH=$PATH:/usr/java/jdk1.5.0_07/bin** en remplaçant
la version du jdk que vous avez télécharger.
La procédure devrait être identique sous MacOS.

## Téléchargement du projet
Utilisez la commande `git clone` afin de télécharger les sources ou téléchargez les manuellement.

### Lancement du projet avec le terminal
Rendez vous directement dans le dossier `P3\out\artifacts\main_jar` et lancez la commande 
`java -jar main.jar` dans votre terminal. L'application se lancera seule.

### Lancement du projet avec un IDE
Ouvrez le projet par le biais de votre IDE.
Vous pouvez directement vous rendre dans le dossier `P3/src/main/java/com/metaxiii` et cliquer
sur le bouton run de la classe Main pour lancer l'application.
Si le bouton run n'est pas possible, n'oubliez pas de d�finir le sdk pour le projet dans le project structure, indiquez les dossiers src\main\java comme dossier de source
et src\main\resources comme dossier de ressource.
Dans l'onglet dependencies, ajoutez les deux jars en cliquant sur le + qui sont situ�s dans le dossier lib du projet.
Dans le dernier cas où un message d'erreur vous indique Error: Could not find or load main class, ouvrez le project structure, d�truisez le module et reconstruisez le en indiquant les dossiers de source
et de ressources.

# Regles du jeu
## Configuration du jeu
Vous trouverez dans le dossier ``src\main\resources`` un fichier `config.properties`.
Celui-ci établi les règles de l'application pour les deux jeux en cours.
-   isDev : Indique si le lanceur de l'application est développeur ou non.
    Ce mode permet de voir la solution pour les tests principalement.
-   size : Indique la taille de la solution. Pour avoir une solution pour un jeu
sur plusieurs caractères, indiquez autant d'étoiles que nécessaire:
    -   Exemple : 
    
    *** -> solution => 747
    
    ** -> solution => 14
    
    **** -> solution 1111
    
-   errorMax : Définit le nombre d'erreur maximum pour les jeux.
-   possibleValueMastermind : Défini les valeurs possibles pour le mastermind
    -   Exemple :
    
    4 -> valeurs possibles => 0,1,2,3,4
    
    7 -> valeurs possibles =>0,1,2,3,4,5,6,7

Veuillez ne pas toucher au fichier log4j2.xml qui correspond au fichier de configuration
pour les logs (`P3\logs\app.log`).


# Librairies externes
Les Librairies utilisées pour ce projet sont 
``log4j-api-2.11.1`` et `log4j-core-2.11.1`.

Elles sont disponibles directement dans le dossier lib de l'application.

# Licence
Ce code a vocation a n'être utilisé que pour la validation d'un projet
OpenClassrooms. Tout utilisation sans autorisation est interdite.
