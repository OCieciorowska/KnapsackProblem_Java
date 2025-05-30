# Knapsack Problem

## Struktura projektu

Projekt składa się z następujących klas:

Główne klasy

* Problem.java - główna klasa rozwiązująca problem plecakowy:
  
  - Generuje przedmioty o losowych wagach i wartościach w podanym zakresie
  - Implementuje algorytm rozwiązujący problem
  - Zawiera metodę solve() zwracającą rozwiązanie
    
* Item.java - reprezentuje pojedynczy przedmiot:
  -Przechowuje wartość i wagę przedmiotu
  - Zawiera proste gettery dla swoich właściwości
    
* Result.java - przechowuje wynik rozwiązania problemu:
  
  - Listę wybranych przedmiotów
  - Ilości każdego z przedmiotów
  - Całkowitą wartość i wagę rozwiązania
* Main.java - klasa uruchomieniowa:
  - Pobiera dane wejściowe od użytkownika
  - Wyświetla wygenerowany problem i jego rozwiązanie
* Testy:
  
ProblemTest.java - testy jednostkowe:
  - Sprawdzają poprawność generowania przedmiotów
  - Weryfikują działanie algorytmu dla skrajnych przypadków
  - Testują deterministyczne rozwiązania

## Algorytm

Algorytm działa w następujący sposób:

* Generuje listę przedmiotów o losowych wagach i wartościach
* Sortuje przedmioty malejąco według stosunku wartości do wagi
* Wybiera przedmioty w kolejności od najlepszego stosunku, biorąc maksymalną możliwą ilość każdego przedmiotu
* Zwraca rozwiązanie zawierające wybrane przedmioty, ich ilości oraz całkowitą wartość i wagę

## Testowanie

Projekt zawiera zestaw testów jednostkowych sprawdzających:

* Poprawność generowania przedmiotów
* Zachowanie dla skrajnych przypadków (np. gdy żaden przedmiot nie pasuje)
* Deterministyczne przypadki z znanymi rozwiązaniami
