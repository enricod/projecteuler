
(ns eulerclj.p025
  (:use eulerclj.euler))

(defn has-less-digits? [n]
  (< (. (str n) length) 1000))

; non funziona!!!! 10^999 = infinity
(defn has-less-digits2? [n]
  (< n (Math/pow 10 999)))

(def l (take 5000 (lazy-seq-fibo)))

; result = 4782
(def res (count (take-while #(has-less-digits? %) l) )   ) 

