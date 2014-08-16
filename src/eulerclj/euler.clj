(ns eulerclj.euler
    (:use eulerclj.sieve)
    (:use clojure.test))


;; in repl
;; (use 'eulerclj.euler :reload-all)
;; (clojure.test/run-all-tests #"eulerclj.euler")



(defn my-sieve [n]
  (let [n (int n)]
    "Returns a list of all primes from 2 to n"
    (let [root (int (Math/round (Math/floor (Math/sqrt n))))]
      (loop [i (int 3)
             a (int-array n)
             result (list 2)]
        (if (>= i n)
          (reverse result)
          (recur (+ i (int 2))
            (if (< i root)
              (loop [arr a
                     inc (+ i i)
                     j (* i i)]
                (if (>= j n)
                  arr
                  (recur (do (aset arr j (int 1)) arr)
                    inc
                    (+ j inc))))
              a)
            (if (zero? (aget a i))
              (conj result i)
              result)))))))


;; sequenza di fibonacci
;; f0 = 0
;; f1 = 1
;; fn = fn-1 + fn-2
;; 0 1 1 2 3 5 8 ...
(defn lazy-seq-fibo
  ; la prima senza parametri, crea una sequenza 0 1 e chiama la seconda funzione con i parametri 0 1
  ([]
    (concat [0 1] (lazy-seq-fibo 0 1)))
  ; la seconda chiama se stessa con i parametri (a+b) b in modo lazy
  ([a b]
    (let [n (+ a b)]
      (lazy-seq
        (cons n (lazy-seq-fibo b n))))))


;; 
;; f1 = 1
;; f2 = 2 + f1
;; fn = n + fn-1
;; 1 2 3 4  5  6  7
;; 1 3 6 10 15 21 28
(defn lazy-seq-triangle
  ([]
    (concat [1 3] (lazy-seq-triangle 3 3)))
  ([a b]
    (let [n (double (+ a b))]
      (lazy-seq
        (cons n (lazy-seq-triangle n (inc b) ))))))


(defn factors [x] 
  (filter #(== 0 (mod x %)) (sieve x )))


(def factorial
  (fn [n]
    (loop [cnt n acc 1]
      (if (zero? cnt)
        acc
        (recur (dec cnt) (* acc cnt))))))








; ============================ PROBLEMA 002 ============================

(defn less-than-four-million? [n]
  (< n 4000000))

(defn problem002-sol1 []
  (reduce + (filter even? 
  (take-while less-than-four-million? (lazy-seq-fibo)))))

; con closure
(defn problem002-sol2 []
  (reduce + 
    (filter even? 
      (take-while (fn [n] (< n 4000000)) (lazy-seq-fibo )))))

; con closure e notazione breve
(defn problem002-sol3 []
  (reduce + (filter even? (take-while #(< % 4000000) (lazy-seq-fibo )))))


(deftest test-problem002-sol1
  (is (= 4613732 (problem002-sol1))))

(deftest test-p002-2
  (is (= 4613732 (problem002-sol2))))

(deftest test-p002-3
  (is (= 4613732 (problem002-sol3))))




; ============================ PROBLEMA 003 ============================

; The prime factors of 13195 are 5, 7, 13 and 29.
; What is the largest prime factor of the number 600851475143 ?
; answer = 6857
; numeri primi < n


(def prob003n 600851475143)

; sequenza numeri primi in ordine decrescente
(def primes-seq 
     (reverse (sieve (Math/sqrt prob003n))))

; tra questi troviamo i fattori del numero n
(def myfactors 
     (filter #(== 0 (mod prob003n %)) primes-seq ))

; il primo e' il valore che cerchiamo
(defn problem003 [] (first myfactors))


(deftest test-problem003-1
  (is (= 6857 (problem003))))



; ============================ PROBLEMA 004 ============================


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

(defn problem004 [] 
  (last (filter #(palindrome? %) v-coll)))


(deftest test-problem004
  (is (= 906609 (problem004))))


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

(defn problem005 []
   (first (drop-while not-divisible-by-all? (range 20 mas2 20))))

(deftest test-problem005
  (is (= 232792560 (problem005))))

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



