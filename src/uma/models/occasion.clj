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
   :course_id :integer
   :cancelees :integers})

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
