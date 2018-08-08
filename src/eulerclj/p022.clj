(ns eulerclj.p022
  
  (:use eulerclj.euler)
  (:use clojure.test))

; legge il contenuto del file
(def text (slurp "p022_names.txt"))

; seq ordinata dei nomi, dopo aver tolto gli apici
; 
(def names
  (sort 
    (map #(clojure.string/replace % "\"" "") 
       (seq (clojure.string/split text #",")))))

; trasforma una stringa in sequenza di interi, dove A=1, B=2 ...
(defn string-to-ints [s]
  (map #(- (int %) 64) (seq (.toCharArray s))))

; seq dei nomi trasformati in seq di interi
(def names-as-ints 
  (map #(string-to-ints % ) names))

; per ogni elemento sommiamo i numeri
(def names-as-sum 
  (map #(reduce + %) names-as-ints))


; trasforma la sequenza in un vettore i => iesimo elemento
(defn indexed [coll] (map vector (iterate inc 1) coll))

; data una sequenza di interi s torna la somma di i * s[i], dove i e' l'elemento i-esimo
(defn mult-and-sum [indexed]
  (reduce + (map #(* (get % 0) (get % 1)) indexed )))


; ===================== tests


; test sul risultato finale
(deftest test-p022 
         (is (= 871198282 (mult-and-sum (indexed names-as-sum)))))

