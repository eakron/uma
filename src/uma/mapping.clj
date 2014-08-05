(ns uma.mapping)

(defmulti map-to-clj
  (fn [type _]
    type))

(defmethod map-to-clj :integer [_ value]
  (Integer. value))

(defmethod map-to-clj :timestamp [_ value]
  (java.sql.Timestamp/valueOf value))

(defmethod map-to-clj :time [_ value]
  "java.sql.Time/valueOf expects a string in the format \"00:00:00\"
  but our use case doesn't need seconds. If we get \"00:00\" we
  simply add 0 seconds to it."
  (if (-> value (clojure.string/split #":") count (= 3))
    (java.sql.Time/valueOf value)
    (java.sql.Time/valueOf (str value ":00"))))

(defmethod map-to-clj :default [_ value]
  value)

(defn apply-mapping
  "Applies a mapping to an object. Not all mapping keys are
  required to exist in the object. Uses map-to-clj under the hood."
  [object mapping]
  (reduce
    (fn [acc [attribute type]]
      (if (contains? object attribute)
        (assoc acc attribute (map-to-clj type (attribute object)))
        acc))
    {}
    mapping))

(defmacro defmapper
  "Binds given symbol to a function which applies the mapping to
  a given object."
  [sym mapping]
  `(defn ~sym [~'object]
    (apply-mapping ~'object ~mapping)))
