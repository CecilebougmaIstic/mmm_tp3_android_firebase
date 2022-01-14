# mmm_tp3_android_firebase
Sujet:
L’objectif de ce TP est de prendre en main le BaaS (Backend As A Service) Firebase https://
firebase.google.com afin de stocker, d’accèder à, et synchroniser des données entre plusieurs terminaux
mobiles sous Android et un service web (cf Firebase Android: https://firebase.google.com/docs/
android/setup). Le TP repose sur les applications réalisées lors du TP1 et TP2 (dont la correction est
disponible).
Exercice 3.1 (Mise en place de Firebase et synchronisation)
1. Créez un projet Firebase (https://console.firebase.google.com et mettez en place les li-
   brairies nécessaires à l’utilisation de Firebase sous Android (https://firebase.google.com/
   docs/android/setup).

2. Modifiez votre application du TP2 de façon à permettre l’ajout d’un client non plus dans une BD
   locale via ROOM mais via un appel à l’API de Firebase. Pour cela crééez une nouvelle classe qui
   dérive de IRepository.
3. Enfin, l’ensemble de l’application de façon à ce qu’elle récupère la liste des clients via l’API Firebase
4. Vérifiez la bonne synchronisation de l’ensemble (création de clients, récupération de clients) entre
   plusieurs terminaux, eg via plusieurs émulateurs.

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
Pré-requis: Installer Android Studio + un emulater android (Avd Manager sur Android Studio).

I- Récupération du projet

Le projet est récupérable sur un repository Github via la commande git clone <url http>. 
url http: https://github.com/CecilebougmaIstic/mmm_tp3_android_firebase.git

Exercice 3.1 (Mise en place de Firebase et synchronisation)
1. Créez un projet Firebase

Le projet sur le console Firebase se nomme: mmm-tp3-android-firebase   
url:https://console.firebase.google.com/project/mmm-tp3-android-firebase

2. Modifiez votre application du TP2 de façon à permettre l’ajout d’un client non plus dans une BD
   locale via ROOM mais via un appel à l’API de Firebase.

Le model de la classe user:
![img_1.png](img_1.png)


   
