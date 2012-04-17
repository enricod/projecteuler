(defn lazy-seq-fibo
 ([]
   (concat [0 1] (lazy-seq-fibo 0 1)))
 ([a b]
   (let [n (+ a b)]
     (lazy-seq
       (cons n (lazy-seq-fibo b n))))))

(take 10 (lazy-seq-fibo))