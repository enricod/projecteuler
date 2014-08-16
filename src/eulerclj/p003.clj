(ns eulerclj.p003
  (:use eulerclj.euler)
  (:use eulerclj.sieve)
  (:use clojure.test))

; ============================ PROBLEMA 003 ============================

; The prime factors of 13195 are 5, 7, 13 and 29.
; What is the largest prime factor of the number 600851475143 ?

;
; answer = 6857
; numeri primi < n


(def prob003n 600851475143)

; sequenza numeri primi in ordine decrescente
(def primes-seq 
     (reverse (sieve (Math/sqrt prob003n))))

; tra questi troviamo i fattori del numero n
(def myfactors 
     (filter #(== 0 (mod prob003n %)) primes-seq ))

; il primo e' il valore che cerchiamo
(defn p003 [] (first myfactors))


(deftest test-p003
  (is (= 6857 (p003))))
  
(defn -main
    "Risolve p003"
    [& args]
    (println "p003 ="  (p003))
  )  