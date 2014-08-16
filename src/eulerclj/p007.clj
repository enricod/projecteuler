
; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13,
; we can see that the 6th prime is 13.
;
; What is the 10001st prime number?

(ns eulerclj.p007
	(:use eulerclj.euler)
  (:use eulerclj.sieve))


; RESULT = 104743
(defn p007 []
  (nth (sieve 200000) 10000))


(defn -main
  "Risolve p007"
  [& args]
  (println "p007 ="  (p007)))
