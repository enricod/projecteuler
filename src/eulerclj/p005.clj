(ns eulerclj.p005
  (:use eulerclj.euler)
  (:use eulerclj.sieve)
  (:use clojure.test))



; ============================ PROBLEM 005 ============================


; 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20

(def mas2 (reduce * (range 2 21)))

;(def p005r2
;  (lazy-seq (range 20 mas2)))

(defn divisibile [n m]
  " true se n è divisibile per m"
  (= 0 (mod n m)))


(defn divisible-by-all? [n, m]
  "true se n è divisibile per tutti i valori da 1 a m "
  (every? true? (map #(divisibile n %) (range 2 m)) ))                                       


(defn not-divisible-by-all? [n m]
  (not (divisible-by-all? n m)))

(defn p005 []
   (first (drop-while #(not-divisible-by-all? % 20) (range 20 mas2 20)  )))

(deftest test-divisible-by-all 
  (is (= true (divisible-by-all? 232792560 20))) 
  (is (= true (divisible-by-all? 2520 10)))   )

(deftest test-problem005
  (is (= 232792560 (p005))))

;
; This does not require programming at all.
; Compute the prime factorization of each number from 1 to 20,
; and multiply the greatest power of each prime together:
;
;20 = 2^2 * 5
;19 = 19
;18 = 2 * 3^2
;17 = 17
;16 = 2^4
;15 = 3 * 5
;14 = 2 * 7
;13 = 13
;11 = 11
;
;All others are included in the previous numbers.
;
;ANSWER: 2^4 * 3^2 * 5 * 7 * 11 * 13 * 17 * 19 = 232 792 560
;

(defn -main
    "Risolve p005"
    [& args]
    (println "p005 ="  (p005))
  )
