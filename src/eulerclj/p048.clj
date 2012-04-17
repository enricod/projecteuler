(ns eulerclj.p048)

(defn pw [x] 
  ; returns x^x
  (reduce * (repeat x x)))

; numero finale
(def sol1 (reduce + (map pw (range 1 1001))))

; numero finale come stringa
(def solstr (str sol1))

; prendiamo gli ultimi 10 caratteri
(def sol (.substring solstr (- (.length solstr) 10) (.length solstr)))



