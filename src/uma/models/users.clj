(ns uma.models.users
  (require [uma.database :as db]
           [korma.core :refer :all]))

(defentity users
  (table :uma_user))

(defn get-users []
 (select users))

(defn get-user-by-id [id]
  (select users
    (where {:id id})))

(defn create-user [user]
  (insert users
    (values user)))

(defn update-user [id user]
  (update users
    (set-fields user)
    (where {:id id})))

(defn delete-user [id]
  (delete users
    (where {:id id})))
