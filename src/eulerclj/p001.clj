; ============================ PROBLEM 001 ============================
;
; If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
; The sum of these multiples is 23.
;
; Find the sum of all the multiples of 3 or 5 below 1000.
;

(ns eulerclj.p001
  (:use eulerclj.euler)
  (:use clojure.test))

; Add all the natural numbers below one thousand that are multiples of 3 or 5.
; true o false se il numero e' divisibile per 3 o 5
(defn divisible-by-3-or-5? [n]
    (or
      (== (mod n 3) 0)
      (== (mod n 5) 0)))

; range 10000 crea una sequenza da 0 a 999
; filter torna solo i valori che soddisfano la condizione divisible-by ecc ecc
; reduce somma i primi 2 valori, poi il risultato con il terzo, ecc ecc
(defn p001 []
  (reduce + (filter divisible-by-3-or-5? (range 1000))))


(defn -main
  "Risolve p001"
  [& args]
  (println "Solving problem 001")
  (println "p001 = "  (p001)))
