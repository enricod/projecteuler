(defn divisible-by-3-or-5? [n] 
   (or (== (mod n 3) 0 )
       (== (mod n 5) 0)))

(println (reduce + (filter divisible-by-3-or-5? (range 1000))))
