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
  (= 0 (mod n m)))

(defn divisible-by-all? [n]
  "ci deve essere un modo migliore :) "
  (and (divisibile n 20)
    (and (divisibile n 19)
      (and (divisibile n 18)
        (and (divisibile n 17)
          (and (divisibile n 16)
            (and (divisibile n 15)
              (and (divisibile n 14)
                (and (divisibile n 13)
                  (and (divisibile n 12)
                    (and (divisibile n 11)
                      (and (divisibile n 10)
                        (and (divisibile n 9)
                          (and (divisibile n 8)
                            (and (divisibile n 7)
                              (and (divisibile n 6)
                                (and (divisibile n 5)
                                  (and (divisibile n 4)
                                    (and (divisibile n 3)
                                      (and (divisibile n 2)
                                        ))))))))))))))))))))


(defn not-divisible-by-all? [n]
  (not (divisible-by-all? n)))

(defn p005 []
   (first (drop-while not-divisible-by-all? (range 20 mas2 20))))

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
