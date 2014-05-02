(ns uma.models.users
  (require [uma.database :as db]
           [yesql.core :refer [defqueries]]))

(defqueries "uma/queries/users.sql")

(defn get-users [_]
  (get-users-raw db/spec))

(defn create-user [user]
  (create-user-raw<! db/spec user))

(defn get-user-by-id [id]
  (get-user-by-id-raw db/spec id))

(defn update-user [id user]
  (update-user-raw! db/spec id user))
