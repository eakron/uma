(ns uma.models.occasion
  (require [uma.database :as db]
           [uma.models.course :refer [course]]
           [korma.core :refer :all]))

(defentity occasion
  (table :occasion))

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