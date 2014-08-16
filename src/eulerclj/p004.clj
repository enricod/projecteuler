(ns eulerclj.p004
  (:use eulerclj.euler)
  (:use eulerclj.sieve)
  (:use clojure.test))


; ============================ PROBLEMA 004 ============================

; A palindromic number reads the same both ways.
; The largest palindrome made from the product of two 2-digit numbers
; is 9009 = 91 × 99.
; Find the largest palindrome made from the product of two 3-digit numbers.


; inverte una stringa
(defn reverse-string [s]
  (apply str (reverse s)))

; true se il numero e' palindromo
(defn palindrome? [n]
  (= (str n) (reverse-string (str n))))

(def p004-range (range 999 100 -1))

; questo mi da tutte le moliplicazioni x*y
; sicuramente migliorabile!
(def v-coll
  (sort (for [x p004-range y p004-range :while (<= x y)] (* x y))))

(defn p004 []
  (last (filter #(palindrome? %) v-coll)))


(deftest test-problem004
  (is (= 906609 (p004))))


(defn -main
  "Risolve p004"
  [& args]
  (println "p004 ="  (p004))
)
