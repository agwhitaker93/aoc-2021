(ns advent-of-code.day-1)

(def depths (load-file "resources/day_1.edn"))

(defn part-1-handler [{increases :increases prev :prev :as accum} val]
  (assoc accum :prev val :increases (if (> val prev)
                                      (inc increases)
                                      increases)))

(defn part-1 []
  (reduce part-1-handler {:prev (first depths) :increases 0} depths))

(defn window-ify
  "Takes a vector of values, i.e. [1 2 3 4 5] and turns it into the following [[1 2 3] [2 3 4] [3 4 5] [4 5] [5]]"
  [to-process]
  (reduce
   #(conj
     (vec (map
           (fn [val]
             (if (< (count val) 3)
               (conj val %2)
               val))
           %1))
     [%2])
   [] to-process))

(defn part-2 []
  (let [window-ified (window-ify depths)
        trimmed (filter #(= (count %1) 3) window-ified)
        summed (map #(reduce + %1) trimmed)]
    (reduce part-1-handler {:prev (first summed) :increases 0} summed)))
