(ns uma.mapping)

(defmulti map-to-clj
  (fn [type _]
    type))

(defmethod map-to-clj :integer [_ value]
  (Integer. value))

(defmethod map-to-clj :timestamp [_ value]
  (java.sql.Timestamp/valueOf value))

(defmethod map-to-clj :time [_ value]
  (java.sql.Time/valueOf value))

(defmethod map-to-clj :default [_ value]
  value)

(defn apply-mapping [object mapping]
  (println object)
  (reduce
    (fn [acc [attribute type]]
      (println (str attribute " " type))
      (if (contains? object attribute)
        (assoc acc attribute (map-to-clj type (attribute object)))
        acc))
    {}
    mapping))

(defmacro defmapper [sym mapping]
  `(defn ~sym [~'object]
    (apply-mapping ~'object ~mapping)))
