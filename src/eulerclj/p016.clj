(ns eulerclj.p016)

; 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

; What is the sum of the digits of the number 2^1000?

; calcola 2^n
(defn pow2 [n]
  "ritorna 2^n come bigint"
  (reduce * (replicate n (bigint 2))))

; trasforma in una sequenza di interi
(def the-digits-seq
    (map #(Long. (str %))
        (seq (str (pow2 1000)))))

; somma i numeri
(def res (reduce + the-digits-seq))

; answer = 1366

(defn -main
  "Risolve p016"
  [& args]
  (println "p016 ="  res))