------------------------------------------------------------------------

![](resources/logo_poly.png)
<td><h1>INF2010 - Structures de données et algorithmes</h1></td>

Merci au cours INF3500 pour le format du Markdown

------------------------------------------------------------------------

Travail pratique \#2
====================

Tables de hachage
=============================================================

Objectifs
---------
* Apprendre le fonctionnement d’une table de hachage

* Comprendre la complexité asymptotique d’une table de hachage

* Utiliser une table de hachage dans un problème complexe

Préparation au laboratoire
--------------------------
Pour ce laboratoire, il est recommandé d’utiliser l’[IDE IntelliJ](https://www.jetbrains.com/fr-fr/idea/download/) 
offert par JetBrains. Vous avez accès à la version complète (Ultimate) en tant qu’étudiant à Polytechnique Montréal. 
Il suffit de vous créer un compte étudiant en remplissant le [formulaire d'inscription étudiante](https://www.jetbrains.com/shop/eform/students).

Astuces
-------
### Gradle
Le projet utilise Gradle pour gérer les *builds*. Après ouverture du projet, il est nécessaire d'importer le projet 
Gradle à l'aide d'une fenêtre qui apparaîtra en bas à droite de votre écran.

![](resources/gradle_import.png)

Dans le cadre du laboratoire, les différentes *build configurations* nous permettront de générer des programmes 
contenant les tests implémentés à l'aide de [JUnit](https://junit.org/junit5/). 

##### *Build configuration* pour tous les tests (ou un dossier en particulier)
![](resources/all_tests.png)

##### *Build configuration* pour une classe en particulier
![](resources/class_tests.png)

##### *Build configuration* pour un test en particulier
![](resources/single_test.png)

------------------------------------------------------------------------

Partie 1 : Implémentation d'une table de hachage
---------------
Une table de hachage est une structure de données qui utilise une fonction de dispersement pour donner une valeur numérique à une clé qui peut être d’un type quelconque (string, int, MyCustomClass, ..). Cette valeur numérique retournée par la fonction de dispersement est utilisée comme indice dans un tableau, ce qui nous donne un opération d’accès en O(1).

Il arrive que la fonction de dispersement retourne la même valeur numérique pour deux clés différentes. Ce phénomène nommé « collision » est un problème connu des tables de hachage et plusieurs techniques existent pour pallier à ce problème. Dans le cadre de ce laboratoire, l’utilisation de listes chaînées nous permettra de gérer les collisions. La classe «Node» contenue dans HashMap.java vous permettra de créer des listes chaînées de la manière suivante :

![](resources/linkedList.png)

Pour bien implémenter ladite table de hachage, suivez les tests contenus dans HashMapTest.java. Aussi, n’oubliez pas d’utiliser hash(KeyType key) comme fonction de dispersement.

Il est recommandé de débuter par l'implémentation des méthodes get() et set() puisque les tests des autres méthodes utilisent ces fonctions afin de valider leur fonctionnement.

**ATTENTION : Une note de 0 sera attribuée à cette partie si l’étudiant utilise une table de hachage déjà implémentée provenant d’une librairie quelconque.**

------------------------------------------------------------------------

Partie 2 : Problème typique d'entrevue
----------------
Le jeu MasterMind.

![](resources/mastermind.jpg)

On vous demande d'implémenter l'algorithme du jeu de table MasterMind. 
Dans celui-ci on compare une séquence de couleurs secrète avec une séquence de couleurs d'essai. 
On doit ensuite indiquer le nombre de couleurs qui sont identiques et à la même position dans les deux séquences
et le nombre de couleur identiques mais à des positions différentes. Votre implémentation remplacera les couleurs
par des chiffres de 0 à 9.

### Entrées
* Deux chaines de caractères de taille N
* Chacune des chaines contient une suite de nombre (ex: "1234" et "1125")

### Sorties
* Un tableau contenant 2 entier (ex : [1, 2])
* Le premier indique le nombre de chiffre commun et à la même position
* Le second indique le nombre de chiffre commun qui ne sont pas à la même position

### Contraintes
Supposons deux séquences de taille N
* Complexité spatiale : O(N) en pire cas
* Complexité temporelle : O(N) en pire cas

Vous devez justifiez votre complexité spatiale et temporelle dans l'en-tête de la fonction _findMatches_

**Seul l'utilisation de la librairie java.util est permise pour cette partie**

------------------------------------------------------------------------

### Exemple 1
Entrées : "1354" et "1345"

Sorties : [2, 2]

Explication : 

Les chiffres '1' et '3' sont présent dans les deux nombre et leurs position sont identiques dans les deux nombres. Soit la position 0 pour le chiffre '1' et la position 1 pour le chiffre '3'. On trouve donc un 2 à la première position de la sortie.

Les chiffres '4' et '5' sont commun au deux nombres mais leur position est différente dans ceux-ci. On trouve donc un 2 à la seconde position de la sortie.   

------------------------------------------------------------------------

### Exemple 2
Entrées : "2012" et "4235"

Sorties : [0, 1]

Explication :

On trouve un 0 à la première position de la sortie puisqu'il n'y a aucun chiffre commun au deux nombres qui ce trouvent à la même position.

On trouve un 1 à la deuxième position de la sortie puisque le chiffre '2' est commun aux deux nombres et présent une seule fois dans la deuxième chaine.

------------------------------------------------------------------------

### Exemple 3
Entrées : "0033" et "3311"

Sorties : [0, 2]

Explication :

On trouve un 0 à la première position de la sortie puisqu'il n'y a aucun chiffre commun au deux nombres qui ce trouvent à la même position.

On trouve un 2 à la deuxième position de la sortie puisque le chiffre '3' est commun à différentes positions pour les deux nombres et il se trouve 2 fois dans chacun des nombres.

------------------------------------------------------------------------

Barème de correction
--------------------

||||
|-----------------|-----------------------------|-----|
| Partie 1        | Réussite des tests          | /14 |
| Partie 2        | Réussite des tests          | /2  |
|                 | Complexité attendue         | /2  |
|                 | Justification de complexité | /1  |
| Qualité du code |                             | /1  |
| Total           |                             | /20 |

Un chargé s’assurera que votre code ne contourne pas les tests avant de vous attribuer vos points dans la catégorie «Réussite des tests». Il est important de respecter les complexités en temps mises dans la description de chaque fonction. 

Pour avoir tous les points dans la catégorie « Complexité en temps » de la partie 2, vous devez réaliser un algorithme respectant les commentaires situés dans `MasterMindTest.java`.

**ATTENTION : Pour la Partie 1, une fonction n’ayant pas la bonne complexité entraîne la perte des points de tous les tests utilisant cette fonction.**

------------------------------------------------------------------------

**Correction automatique** : Les tests sont un bon moyen d'évaluer votre note avant la remise. Néanmoins, l’entièreté 
de votre code sera révisée par un chargé de laboratoire pour s'assurer qu'il réalise véritablement les tâches demandées.
Il peut donc y avoir des différences entre la note donnée par vos tests et votre note finale.

##### Qu'est-ce que du code de qualité ?
* Absence de code dédoublé
* Absence de *warnings* à la compilation
* Absence de code mort
* Respecte les mêmes conventions de codage dans tout le code produit
  * Langue utilisée
  * Noms des variables, fonctions et classes
* Variables, fonctions et classes avec des noms qui expliquent leur intention et non leur comportement

Le dernier commit de votre répertoire sera utilisé comme remise finale. Chaque jour de retard créera une pénalité 
additionnelle de 20 %. Aucun travail ne sera accepté après 4 jours de retard.


