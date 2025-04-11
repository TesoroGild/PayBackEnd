# PayBackEnd

L'application est un logiciel qui calculera des remboursements de réclamations d'assurances de soins de santé suivant certaines règles.
Le fichier d'entrée, en format JSON, aura l'air de celui present à la racine du dépôt. Idem pour le fichier de sortie.

Le programme devra prendre le fichier d'entrée comme argument lors de l'exécution du logiciel dans une console. Le fichier de sortie devra également être spécifié à la console comme suit :
```bash
java -jar Refund.jar inputfile.json refunds.json
```

## Règles
Il existe 4 types de contrat différents : A, B, C, D.
