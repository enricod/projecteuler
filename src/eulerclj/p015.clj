(ns p015)

; utilizzo il triangolo di pascal
;
;	             1
;	           1 2 1
;	          1 3 3 1
;	        1  4 6  4 1
;	       1 5 10 10 5 1
;	      1 6 15 20 15 6 1

(defn somma-coppie [s] 
  (if (> (count s) 1)
    (cons (+ (nth s 0) (nth s 1)) (somma-coppie (rest s)))
    ()))

(defn next-pascal-row [s]
  (concat [1] (somma-coppie s) [1]))

(def grid-dim 20)
(def first-row [1 2 1])

(defn row-final [s] 
  (let [r s] 
    (if (= (count r) (inc (* 2 grid-dim)))
    r
    (row-final (next-pascal-row r)))))

(def sol (nth (row-final first-row) grid-dim))

; solution = 137846528820