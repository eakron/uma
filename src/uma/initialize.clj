(ns uma.initialize
  (require [korma.core :refer [exec-raw]]))

(defn drop-and-create-tables []
  (let [sql (slurp "src/uma/schema.sql")]
    (exec-raw sql)))
