(ns eulerclj.sieve)

(def a1 (int-array 5))


(defn sieve
  "Returns a list of all primes from 2 to n"
  [n]
  (let [n (int n)]
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


(defn primes [max]
  (let [enqueue (fn [sieve n factor]
                  (let [m (+ n factor)]
		                  (assoc sieve m
                           (conj (sieve m) factor))))
        next-sieve (fn [sieve candidate]
                     (if-let [factors (sieve candidate)]
		                     (reduce #(enqueue %1 candidate %2)
                               (dissoc sieve candidate)
                               factors)
		                     (enqueue sieve candidate candidate)))]
    (apply concat (vals (reduce next-sieve {} (range 2 max))))))




(defn primes2 [max]
  (let [enqueue (fn [sieve n factor]
                  (let [m (+ n factor)]
		                  (if (sieve m)
		                    (recur sieve m factor)
		                    (assoc sieve m factor))))
        next-sieve (fn [sieve candidate]
                     (if-let [factor (sieve candidate)]
		                     (-> sieve
                         (dissoc candidate)
                         (enqueue candidate factor))
                       (enqueue sieve candidate candidate)))]
    (vals (reduce next-sieve {} (range 2 max)))))


(defn primes3 [max]
  (let [enqueue (fn [sieve n factor]
                  (let [m (+ n (+ factor factor))]
		                  (if (sieve m)
		                    (recur sieve m factor)
		                    (assoc sieve m factor))))
        next-sieve (fn [sieve candidate]
                     (if-let [factor (sieve candidate)]
		                     (-> sieve
                         (dissoc candidate)
                         (enqueue candidate factor))
                       (enqueue sieve candidate candidate)))]
    (cons 2 (vals (reduce next-sieve {} (range 3 max 2))))))



(defn lazy-primes3 []
  (letfn [(enqueue [sieve n step]
		   (let [m (+ n step)]
		     (if (sieve m)
		       (recur sieve m step)
		       (assoc sieve m step))))
          (next-sieve [sieve candidate]
		      (if-let [step (sieve candidate)]
			(-> sieve
			    (dissoc candidate)
			    (enqueue candidate step))
			(enqueue sieve candidate (+ candidate candidate))))
          (next-primes [sieve candidate]
		       (if (sieve candidate)
			 (recur (next-sieve sieve candidate) (+ candidate 2))
			 (cons candidate
			       (lazy-seq (next-primes (next-sieve sieve candidate)
						      (+ candidate 2))))))]
    (cons 2 (lazy-seq (next-primes {} 3)))))
