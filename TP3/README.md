------------------------------------------------------------------------

![](resources/logo_poly.png)
<td><h1>INF2010 - Structures de données et algorithmes</h1></td>

Merci au cours INF3500 pour le format du Markdown

------------------------------------------------------------------------

Travail pratique \#3
====================

Arbre Binaire de recherche
=============================================================

Objectifs
---------
* Apprendre le fonctionnement d’un arbre binaire de recherche

* Comprendre la complexité asymptotique d’un arbre binaire de recherche

* Utiliser les concepts associés aux arbres binaires dans un problème complexe

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

Partie 1 : Implémentation d'un arbre AVL
---------------
Un arbre AVL est un arbre binaire de recherche équilibré. Celui-ci oblige que la différence entre la hauteur gauche et la hauteur droite soit inférieure à 2. Si cette condition n’est pas respectée, il vous faut rééquilibrer l’arbre avec l’algorithme des arbres AVL. Dans le cas du retrait d’un élément, une démarche spécifique doit être utilisée. L’explication de ces algorithmes sont disponibles dans les diapositives Cours05 et Cours06.

L’arbre AVL sera composé d’un ensemble de BinaryNodes ayant un lien ascendant vers leur parent. Voici un exemple d’un arbre AVL utilisant celles-ci.

![](resources/AVL.png)

Pour bien implémenter l’arbre AVL, suivez les tests contenus dans AvlTreeTester.java dans l’ordre de leur définition. Aussi, n’oubliez pas que root est un cas d’exception pour la plupart des fonctions à implémenter.

**ATTENTION : Une note de 0 sera automatiquement attribuée si vous utilisez un arbre binaire d’une librairie quelconque.**

**Certaines fonctions doivent obligatoirement être implémentées de manière itérative (non-récursif). Celles-ci sont identifiées avec le commentaire suivant  _'HAS TO BE ITERATIVE, NOT RECURSIVE'_**

------------------------------------------------------------------------

Partie 2 : Problème typique d'entrevue
----------------

On vous demande d'élaborer un algorithme répondant à la question d'entrevue suivante de deux manières différentes.
Premièrement de façon récursive dans la fonction `getBoundsRecursive` et ensuite de manière itérative dans la fonction `getBounds`.

Pour chacune des implémentations, on vous demande bien de respecter la contrainte de complexité temporelle et d'expliquer celle-ci. 
De plus, on vous demande de déterminer et d'expliquer la complexité spatiale de chacune des implémentations. 
L'objectif d'avoir deux implémentations différentes est d'observer les différences dans la complexité spatiale des deux implémentations.

### Entrées
* Un tableau d'entier trié en ordre croissant (ex : [1, 2, 2, 4])
* Un entier qu'on cherche dans le tableau (ex: 2)

### Sorties
* Un tableau contenant 2 entier (ex : [1, 2])
* Le premier indique la position de la première apparition de l'entier recherché
* Le second indique la position de la dernière apparition de l'entier recherché

### Contraintes
Supposons un tableau de taille N
* Complexité temporelle : O(log(N)) en pire cas
* Complexité spatiale en pire cas : À vous de la déterminer et de vous justifier

Si l'entier ne se trouve pas dans le tableau il faut retourner [-1, -1]

**Il est permis d’utiliser la librairie java.util pour cette partie. Une note de 0 sera attribuée à cette partie si l’étudiant utilise quelconque autre librairie.**

------------------------------------------------------------------------

### Exemple 1
Entrées : 
* Tableau : [1, 2, 5, 5, 5, 6, 7]
* entier : 5

Sorties : [2, 4]

Explication :

On rencontre l'entier 5 pour la première fois dans le tableau à la position 2 et pour la dernière fois à la position 4.

------------------------------------------------------------------------

### Exemple 2
Entrées :
* Tableau : [1, 2, 5, 5, 5, 6, 7]
* entier : 7

Sorties : [6, 6]

Explication :

On rencontre l'entier 7 pour la première fois dans le tableau à la position 6 et pour la dernière fois à la position 6.

------------------------------------------------------------------------

### Exemple 3
Entrées :
* Tableau : [1, 2, 5, 5, 5, 6, 7]
* entier : 0

Sorties : [-1, -1]

Explication :

L'entier 0 ne ce trouve pas dans le tableau il faut donc retourner [-1, -1] 

------------------------------------------------------------------------

Récursion
--------------------
Afin de déterminer la complexité spatiale d'une fonction récursive, il est important de comprendre le concept de la pile d'appels.

La pile d'appels est l'endroit en mémoire ou notre programme sauvegarde les appels aux fonctions ainsi que les paramètres de celles-ci.
Lors de l'utilisation d'une fonction récursive, les paramètres sont empilés à nouveau sur la pile à chaque appel récursif. L'espace
mémoire utilisé est donc plus grand lors d'une implémentation récursive comparée à une implémentation itérative.

Voici un exemple pour mieux illustrer la pile d'appel pour une fonction récursive.

Soit le code suivant
```java
class Main {
    public static void Main() {
        recursif(new Integer[] {1, 2, 3}, 3);
    }
    
    public static Integer[] recursif(Integer[] array, int number) {
        number--;
        if (number > 0)
            return recursif(array, number);
        
        return array;
    }
}
```

La pile d'appel lors de l'exécution de la fonction main serait la suivante

![](resources/callstack.png)

Barème de correction
--------------------

||||
|-----------------|-----------------------------|-----|
| Partie 1        | Réussite des tests          | /13 |
| Partie 2        | Réussite des tests          | /2  |
|                 | Complexité attendue         | /2  |
|                 | Justification de complexité | /2  |
| Qualité du code |                             | /1  |
| Total           |                             | /20 |

Un chargé s’assurera que votre code ne contourne pas les tests avant de vous attribuer vos points dans la catégorie «Réussite des tests». Il est important de respecter les complexités en temps mises dans la description de chaque fonction. 

Pour avoir tous les points dans la catégorie « Complexité en temps » de la partie 2, vous devez réaliser un algorithme respectant les commentaires situés dans `Interview.java`.

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


