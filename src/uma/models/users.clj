(ns uma.models.users
  (require [uma.database :as db]
           [korma.core :refer :all]))

(defentity users
  (table :uma_user))

(defn get-users []
 (select users))
