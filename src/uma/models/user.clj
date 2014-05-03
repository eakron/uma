(ns uma.models.user
  (require [uma.database :as db]
           [korma.core :refer :all]))

(defentity user
  (table :uma_user))

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
