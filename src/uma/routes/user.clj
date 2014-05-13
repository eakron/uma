(ns uma.routes.user
  (require [uma.models.user :as user]
           [uma.utilities :refer [defroutes-with-crud]]))

(defroutes-with-crud routes
  {:read-all user/get-users
   :read-by-id user/get-user-by-id
   :create user/create-user
   :update user/update-user
   :delete user/delete-user
   :mapfn user/mapfn})
