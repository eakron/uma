(ns uma.models.horse
  (require [uma.database :as db]
           [korma.core :refer :all]))

(defentity horse
  (table :horse))

(defn get-horses []
 (select horse))

(defn get-horse-by-id [id]
  (select horse
    (where {:id id})))

(defn create-horse [horse]
  (insert horse
    (values horse)))

(defn update-horse [id horse]
  (update horse
    (set-fields horse)
    (where {:id id})))

(defn delete-horse [id]
  (delete horse
    (where {:id id})))
