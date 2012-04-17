;
; You are given the following information, but you may prefer to do some research for yourself.
;
; 1 Jan 1900 was a Monday.
; Thirty days has September,
; April, June and November.
; All the rest have thirty-one,
; Saving February alone,
; Which has twenty-eight, rain or shine.
; And on leap years, twenty-nine.
; A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
;
; How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
;

(ns eulerclj.p019
  (:use eulerclj.euler clojure.test))

(defn divisibile-by-4? [n]
  (= 0 (mod n 4)))

(defn divisibile-by-400? [n]
  (= 0 (mod n 400)))

(defn leap-year? [n] 
  (and (divisibile-by-4? n)
       (not (divisibile-by-400? n))))

; month => 0 = gennaio
; day-in-week => 0 corrisponde a domenica
(defstruct day :year :month :day :day-in-week)

; 1 gen 1901 martedi'
(def first-day (struct day 1901 0 1 2))

; durata standard dei mesi
(def days-in-month {0 31, 1 28, 2 31, 3 30, 4 31, 5 30, 6 31, 7 31, 8 30, 9 31, 10 30, 11 31 } )

(defn inc-day-in-week [n] 
  (mod (inc n) 7))

; dato un giorno, ritorna il giorno successivo
(defn next-day [d] 
  (if (< (d :day) (get days-in-month (d :month)))
    ; se non siamo all'ultimo del mese, aumenta il giorno e basta
    (struct day (d :year) (d :month) (inc (d :day)) (inc-day-in-week (d :day-in-week)))
      ; siamo all'ultimo del mese ... forse
    (if (and 
          (leap-year? (d :year))
          (= 1 (d :month)))
            (if (= 29 (d :day))
              (struct day (d :year) 2 1 (inc-day-in-week (d :day-in-week)))
              ; se e' il 28 di un mese bisestile, allora incrementa il giorno
              (struct day (d :year) (d :month) (inc (d :day)) (inc-day-in-week (d :day-in-week))))
            ;  incrementa il mese
            (if (= 11 (d :month))
              ; se e' dic aumenta anno - mese va a 0
              (struct day (inc (d :year)) 0 1 (inc-day-in-week (d :day-in-week)))
              ; non siamo in dic - aumentiamo il mese
              (struct day (d :year) (inc (d :month)) 1 (inc-day-in-week (d :day-in-week)))))))


; costruiamo seq di tutti i giorni dal first day fino al 31 dic 2000
(def all-days (take-while 
           #(< (% :year) 2001) 
             (iterate next-day first-day)))

; teniamo solo i risultati validi
(def all-valid-days (filter #(and (= 1 (% :day)) (= 0 (% :day-in-week))) all-days))

; ===================== tests

(deftest test-divisibile-4
  (is (= true (divisibile-by-4? 16)))
  (is (= true (divisibile-by-4? 12)))
  (is (= false (divisibile-by-4? 13))))

(deftest test-divisibile-400
  (is (= true (divisibile-by-400? 1200)))
  (is (= false (divisibile-by-400? 13))))

; numero di giorni 
(deftest test-days-in-year
  (is (= 365 (reduce + (vals days-in-month )))))

(deftest test-leap
        (is (= true (leap-year? 1900))))

(deftest test-next-day 
         (is (= (next-day (struct day 1900 0 1 1)) (struct day 1900 0 2 2)))
         ; penultimo di gennaio
         (is (= (next-day (struct day 1900 0 30 1)) (struct day 1900 0 31 2)))
         ; ultimo di gennaio - deve diventare febbraio
         (is (= (next-day (struct day 1901 0 31 1)) (struct day 1901 1 1 2)))
         ; ultimo di dicembre - diventa 1 gennaio 
         (is (= (next-day (struct day 1901 11 31 1)) (struct day 1902 0 1 2)))
         (is (= (next-day (struct day 1900 1 28 1)) (struct day 1900 1 29 2)))
         (is (= (next-day (struct day 1900 1 29 1)) (struct day 1900 2 1 2)))
         )

; test sul risultato finale
(deftest test-res
         (is (= 171 (count all-valid-days))))

