(ns uma.models.users
  (require [uma.database :as db]
           [yesql.core :refer [defqueries]]))

(defqueries "uma/queries/users.sql")

(defn get-users [_]
  (get-users-raw db/spec))
