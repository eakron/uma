(ns uma.mapping)

(defn as-integer [i]
  (Integer. i))

(defn as-timestamp [t]
  (java.sql.Timestamp. t))

(defn as-time [t]
  (java.sql.Time. t))

(defn as-integer-vector [ts]
  (mapv as-integer ts))

(defn apply-mapping [object mapper]
  (reduce
    (fn [acc [k v]]
      (if (contains? object k)
        (assoc acc k (v (k object)))
        acc))
    {}
    mapper))
