(ns uma.models.horse
  (require [uma.database :as db]
           [uma.mapping :refer [defmapper]]
           [uma.models.entities :refer [occasion horse]]
           [korma.core :refer :all]))

(defmapper mapfn
  {:id :integer
   :name :string
   :year :string
   :height :string
   :color :string
   :description :string})

(defn get-horses []
 (select horse))

(defn get-horse-by-id [id]
  (select horse
    (where {:id id})))

(defn create-horse [horse-map]
  (insert horse
    (values horse-map)))

(defn update-horse [id horse-map]
  (update horse
    (set-fields horse-map)
    (where {:id id})))

(defn delete-horse [id]
  (delete horse
    (where {:id id})))
