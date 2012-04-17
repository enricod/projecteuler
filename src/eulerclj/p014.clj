; The following iterative sequence is defined for the set of positive integers:

; n -> n/2 (n is even)
; n -> 3n + 1 (n is odd)

; Using the rule above and starting with 13, we generate the following sequence:

; 13  40  20  10  5  16  8  4  2  1
; It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. 
; Although it has not been proved yet (Collatz Problem), it is thought that all starting 
; numbers finish at 1.

; Which starting number, under one million, produces the longest chain?


(ns p014)

(defn transform [n]
  (if (even? n) 
    (/ n 2) 
    (inc (* 3 n))))

; costruisce la sequenza dato il numero n
(defn seq14 [n]
  (if 
    (seq? n) 
    (if 
      (== 1 (first n))
          n
         (seq14 (cons (transform (first n)) n)))
    (seq14 (cons n ()))))

; costruisce una mappa con x => (count (seq14 x)), dove x <= n

;; NON ANCORA RISOLTO
