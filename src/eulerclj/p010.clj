; The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

; Find the sum of all the primes below two million.
; answer: 142913828922

(ns eulerclj.p010
  (:use eulerclj.euler)
  (:use eulerclj.sieve))

(def myprimes (sieve 2000000))

(def res (reduce + myprimes ))

(defn -main
  "Risolve p010"
  [& args]
  (println "p010 ="  res))
