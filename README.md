# Grails_Multi_Build_Project
A Grails 3.2.3 Multi build Project
****************************************
Ceci est un projet Grails 3.2.3 avec des build multiple, c'est à dire avec plusieurs sous-projets comme module.
Les technologies utilisés sont :
- Grails : 3.2.3
- Gradle : 2.3, 3.0
*******************************************
Le Module Admin permet aux utilisateurs/administrateurs de gerer tous les Utilisateurs de l'application(publicateurs d'emplois, opérateurs ou régulateurs du sites, etc..), mais aussi les Tags ou secteurs professionnels, ainsi que la gestion des emplois à publier.
Ce module est l'application cliente au Microservice auquel une requete asynchrone de recupération d'emplois depuis d'autres sites est faite automatiquement 2 fois par jour.
Plugins  : 
- Quartz Job Sheduller,
- Spring Security core 3.1.2,
- Jenkings,// --Removed-- 
- Serenity,
- Sellenium,
- ....
*******************************************
Le Module Web sert d'entrée publique à l'ensemble des utilisateurs du site. 
Les Utilisateurs peuvent consulter ou rechercher un emploi suivant plusieurs critères.
Plugins : 
- angular, 
- asset-pipeline, 
- spring security Rest, jwt,
- ...
*******************************************
Le Microservice fait l'import de manière assynchrone des emploi d'autres sites à travers leurs APIs. 
Plugins : 
- ... ( // TODO   : update this )
*******************************************
Le Module Domain contient les Entités et les services utilisés par differentes applications.
*******************************************
Le module Specification est la base des spécifications et Tests à faire sur l'application. 
Plugins:
- Serenity
*******************************************
Le Module Mobile est remplacé par l'ionic App.
******************************************

Profiles :

- app-admin : appliaction web
- app-web :  application angular
- mod-mobile : plugin --rest-api
- mod-microservice : plugin --rest-api
- mod-domain : plugin 

**********************************************
// TODO :  faire un update pour les versions utilisées pour chaque plugin.
