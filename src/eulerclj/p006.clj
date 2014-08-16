; The sum of the squares of the first ten natural numbers is,
;
; 1^2 + 2^2 + ... + 10^2 = 385
; The square of the sum of the first ten natural numbers is,
;
;(1 + 2 + ... + 10)^2 = 552 = 3025
;
; Hence the difference between the sum of the squares of the
; first ten natural numbers and the square of the sum is 3025  385 = 2640.
;
; Find the difference between the sum of the squares of the first one
; hundred natural numbers and the square of the sum.

(ns eulerclj.p006)

(defn sum-of-squares [r]
  (reduce +
    (map #(Math/pow %1 %2) r (repeat 2))))

(defn square-of-sum [r]
  (Math/pow
    (reduce + r) 2))

(defn p006 [r]
; {:test (fn [] (is (= (range 1 11) 2640)))}
  (int (- (square-of-sum r)
    (sum-of-squares r))))

; result = 25164150


;(println (result (range 1 11)))
;(println (result (range 1 101)))

(defn -main
  "Risolve p006"
  [& args]
  (println "p006 ="  (p006 (range 1 101)))
)
