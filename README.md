RÉAU Arthur

> Q.1b Le test de ces constructeurs utilisent les opérations getX et getY. Ne trouvez-vous pas cela étrange qu’un test utilise d’autres opérations ? Que faire ?
	
	On pourrait tester toutes les méthodes afin d'avoir le moins de risque possible, cependant il serait inutile de tester certaines méthodes comme les Getters.
	
> Q.2b Est-ce que votre code est sûr lorsque toutes les instructions sont couvertes par au moins un test ?
	Non, nous ne sommes pas certains que tout les cas possibles sont couverts mais ça nous assure que les tests sont passées par les différents chemins (conditions, boucles, ...)

> Q.3a Expliquez en quoi il est impossible de tester en l’état cette opération.
    >> On veut donc utiliser le principe du Mock pour tester cette opération.
    
    Il est imposssible de tester en l'état l'opération parce qu'il est impoosible de prévoir la valeur donnée de Random.nextInt() à l'avance
