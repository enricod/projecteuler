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
    (let [n (bigint (+ a b))]
      (lazy-seq
        (cons n (lazy-seq-fibo b n))))))


;;
;; f1 = 1
;; f2 = 2 + f1
;; fn = n + fn-1
;;
;; 1 1
;; 2 3
;; 3 6
;; 4 10
;; 5 15
;; 6 21
;; 7 28

(defn lazy-seq-triangle
  ([]
    (concat [1 3] (lazy-seq-triangle 3 3)))
  ([a b]
    (let [n (bigint (+ a b))]
      (lazy-seq
       ; cons(x s) : Returns a new seq where x is the first element and seq is the rest.
        (cons n (lazy-seq-triangle n (inc b) ))))))


(defn factors [x]
  (filter #(== 0 (mod x %)) (sieve x )))


(def factorial
  (fn [n]
    (loop [cnt n acc 1]
      (if (zero? cnt)
        acc
        (recur (dec cnt) (* acc cnt))))))

















