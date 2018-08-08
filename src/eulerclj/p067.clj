(ns eulerclj.p067
  (:require [clojure.string :as str])
  (:import (java.io BufferedReader FileReader))
  (:use clojure.test))


; By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
;
; 3
; 7 4
; 2 4 6
; 8 5 9 3
;
; That is, 3 + 7 + 4 + 9 = 23.
;
; Find the maximum total from top to bottom of the triangle below:
;
; 75
; 95 64
; 17 47 82
; 18 35 87 10
; 20 04 82 47 65
; 19 01 23 75 03 34
; 88 02 77 73 07 63 67
; 99 65 04 28 06 16 70 92
; 41 41 26 56 83 40 80 70 33
; 41 48 72 33 47 32 37 16 94 29
; 53 71 44 65 25 43 91 52 97 51 14
; 70 11 33 28 77 73 17 78 39 68 17 57
; 91 71 52 38 17 14 91 43 58 50 27 29 48
; 63 66 04 68 89 53 67 30 73 16 69 87 40 31
; 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
;
; NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route.
; However, Problem 67, is the same challenge with a triangle containing one-hundred rows;
; it cannot be solved by brute force, and requires a clever method! ;o)


;; TRIANGOLO DI PROVA
(def t1 (list  [03]
               [7 4]
               [2 4 6]
               [8 5 9 3] ))



(def t2 (list [75]
              [95 64]
              [17 47 82]
              [18 35 87 10]
              [20  4 82 47 65]
              [19  1 23 75  3 34]
              [88  2 77 73  7 63 67]
              [99 65 04 28  6 16 70 92]
              [41 41 26 56 83 40 80 70 33]
              [41 48 72 33 47 32 37 16 94 29]
              [53 71 44 65 25 43 91 52 97 51 14]
              [70 11 33 28 77 73 17 78 39 68 17 57]
              [91 71 52 38 17 14 91 43 58 50 27 29 48]
              [63 66 04 68 89 53 67 30 73 16 69 87 40 31]
              [04 62 98 27 23  9 70 98 73 93 38 53 60 04 23] ))



(defn vect-concatenate [v1 v2]
  (into [] (concat v1 v2)))

; (apply max (nth triang 2))


;; a1
;; b1 b2
;; c1 c2 c3
;; d1 d2 d3 d4
;; e1 e2 e3 e4 e5
;; f1 f2 f3 f4 f5 f6
;;





;; r1 =   a1   a2   a3   a4
;; r2 = b1   b2  b3    b4    b5
;;
;; result = [ b1+a1  max(b2+a1,b2+a2) max(b3+a2,b3+a4) max(b4+a3,b4+a4) b5+a4 ]
(defn somma-righe [r1 r2]
  (loop [ i 0
          result [] ]
    (if (= i (count r2))
      result
      (if (= 1 (count r1))
        ;; riga1 di 1 solo elemento
        [(+ (first r1) (first r2)) (+ (first r1) (nth r2 1)) ]
        (do
          (if (= i 0)
            (recur (inc i) (conj result (+ (nth  r1 i ) (nth r2 i ))))
            (if (< i (count r1))
              ;; siamo in mezzo
              (recur (inc i) (conj result (max
                                            (+ (nth r1 (dec i)) (nth r2 i))
                                            (+ (nth r1 i) (nth r2 i ))) ))
              (recur (inc i) (conj result (+ (nth  r1 (dec i) ) (nth r2 i ))))
              )))))))



;(somma-righe (nth t2 0) (nth t2 1))
;(somma-righe [170 39] (nth t2 2))
;(somma-righe [187 217 86 121] (nth t2 3))
;(somma-righe [205 222 252 304 173 96 131] (nth t2 4))


(defn p067 [triang]
  (loop [rows triang]
  (if (= (count rows) 1)
    ;; torniamo il valore massimo
    (apply max (first rows))
    (let [ r1 (first rows)
           r2 (first (rest rows))
           altro (rest (rest rows))
           r3 (somma-righe r1 r2)]
      (recur (conj altro r3))))))


(defn process-line [line]
  (when-not (= "0" line)
    (into [] (map #(Integer/parseInt %) (str/split line #" ")))))



(defn process-file [file-name]
    (with-open [rdr (BufferedReader. (FileReader. file-name))]
      (remove empty? (doall (map process-line (line-seq rdr)))) ))


(deftest test-p067
  (is (= 7273 (p067 (process-file "p067.txt")))))

; (p067 (process-file "p067.txt"))
(defn -main
  "Risolve p067"
  [& args]
  (println "p067 ="  (p067 (process-file "p067.txt")) ))

