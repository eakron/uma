(ns uma.routes.users
  (require [uma.models.users :as users]
           [uma.utilities :refer [defroutes-with-crud]]))

(defroutes-with-crud routes
  {:read-all users/get-users
   :read-by-id users/get-user-by-id
   :create users/create-user
   :update users/update-user
   :delete users/delete-user})
