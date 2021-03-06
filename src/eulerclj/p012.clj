; The sequence of triangle numbers is generated by adding the natural numbers. 
; So the 7^(th) triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:
;  
; 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
;
; Let us list the factors of the first seven triangle numbers:
;
;  1    1: 1
;  2    3: 1,3
;  3    6: 1,2,3,6
;  4   10: 1,2,5,10
;  5   15: 1,3,5,15
;  6   21: 1,3,7,21
;  7   28: 1,2,4,7,14,28
;  8   36:
;  9   45:
; 10   55:
; 11   66
; 12   78:

; We can see that 28 is the first triangle number to have over five divisors.
; 
; What is the value of the first triangle number to have over five hundred divisors?

(ns eulerclj.p012
	(:use eulerclj.euler))


;; dato un numero x, restituisce i divisori
(defn divisori [x] 	(filter #(== 0 (mod x %)) (range 1 (inc x))))

;; conta i divisori, sfruttando il fatto che
;; 300 e' divisibile per
; 1 e 300
; 2 e 150
; 3 e 100
; ecc ecc 
(defn divisori-count [x]
  (let [sqr (int (Math/sqrt x))]
    (let [s1 (filter #(== 0 (mod x %)) (range 1 sqr))]
      (let [res  (* 2 (count s1))]
        (if (== x (* rq rq))
          (inc res)
          res
        )))))



(def r  (first (filter #(> (divisori-count %) 500) (lazy-seq-triangle))))

; 76576500
(def res (int r))

(defn -main
  "Risolve p012"
  [& args]
  (println "p012 = "  res))