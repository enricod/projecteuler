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


; Add all the natural numbers below one thousand that are multiples of 3 or 5.
; true o false se il numero e' divisibile per 3 o 5
(defn divisible-by-3-or-5? [n]
  (or 
   (== (mod n 3) 0) 
   (== (mod n 5) 0)))


; ============================ PROBLEMA 001 ============================

; range 10000 crea una sequenza da 0 a 999
; filter torna solo i valori che soddisfano la condizione divisible-by ecc ecc
; reduce somma i primi 2 valori, poi il risultato con il terzo, ecc ecc
(defn problem001 [] 
  (reduce + (filter divisible-by-3-or-5? (range 1000))))


; answer = 233168
(deftest test-problem001
  (is (= 233168  (problem001) )))


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

