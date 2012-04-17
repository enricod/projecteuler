(ns p009)

;
; A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
; a^2 + b^2 = c^2
; For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
; There exists exactly one Pythagorean triplet for which a + b + c = 1000.
; Find the product abc.

(def r2 (range 1 500))

(defn mysquare [n]
  (* n n))

(def my-seq
  (for [x r2 y r2 z r2
                  :when (and
                          (= 1000 (+ x y z))
                          (and
                             (and
                               (<= x y) (<= y z))
                             (=  (+ (mysquare x) (mysquare y))
                             (mysquare z)))
                          )]
              [x y z] ))




(def my-seq2 (list* (first my-seq)))

; result = 31875000
(def res (reduce *  my-seq2 ))