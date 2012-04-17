; 2520 is the smallest number that can be divided by each
; of the numbers from 1 to 10 without any remainder.
;
; What is the smallest number that is evenly divisible
; by all of the numbers from 1 to 20?

; moltiplicazione di tutti i valori

(ns p005)

(def r (range 2 21))

; 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20

(def l (list 11 12 13 14 15 16 17 18 19 20))

(def mas2 (reduce * r))
(def r2
  (lazy-seq
    (range 20 mas2)))

;(println mas2)

(defn divisibile [n m]
  (= 0 (mod n m)))

(defn divisible-by-all? [n]
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

(def res (first (drop-while not-divisible-by-all? (range 20 mas2 20))))

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