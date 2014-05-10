(ns uma.models.occasion
  (require [uma.database :as db]
           [uma.models.entities :refer [course horse occasion]]
           [korma.core :refer :all]))

(def mapping-model
  {:id (Integer. id)
   :date (java.sql.Timestamp. date)
   :description description
   :startTime (java.sql.Time. startTime)
   :endTime (java.sql.Time. endTime)
   :price (Integer. price)
   :course_id (Integer. course_id)
   :cancelees (map #(Integer. %) cancelees)})

(defn get-occasions []
 (select occasion))

(defn get-occasion-by-id [id]
  (select occasion
    (where {:id id})))

(defn create-occasion [occasion]
  (insert occasion
    (values occasion)))

(defn update-occasion [id occasion]
  (update occasion
    (set-fields occasion)
    (where {:id id})))

(defn delete-occasion [id]
  (delete occasion
    (where {:id id})))
