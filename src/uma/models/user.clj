(ns uma.models.user
  (require [uma.database :as db]
           [uma.models.entities :refer [course user]]
           [korma.core :refer :all]))

(defn get-users []
  (select user))

(defn get-user-by-id [id]
  (select user
    (where {:id id})))

(defn create-user [user]
  (insert user
    (values user)))

(defn update-user [id user]
  (update user
    (set-fields user)
    (where {:id id})))

(defn delete-user [id]
  (delete user
    (where {:id id})))
