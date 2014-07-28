(ns uma.models.occasion
  (require [uma.database :as db]
           [uma.mapping :refer [defmapper]]
           [uma.models.entities :refer [course horse occasion]]
           [korma.core :refer :all]))

(defmapper mapfn
  {:id :integer
   :date :timestamp
   :description :string
   :startTime :time
   :endTime :time
   :price :integer
   :course_id :integer})

(defn get-occasions []
 (select occasion))

(defn get-occasion-by-id [id]
  (first
    (select occasion
      (where {:id id}))))

(defn create-occasion [occasion-map]
  (insert occasion
    (values occasion-map)))

(defn update-occasion [id occasion-map]
  (update occasion
    (set-fields occasion-map)
    (where {:id id})))

(defn delete-occasion [id]
  (delete occasion
    (where {:id id})))
