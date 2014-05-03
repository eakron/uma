(ns uma.routes.users
  (require [compojure.core :refer [defroutes context GET POST]]
           [liberator.core :refer [defresource]]
           [uma.models.users :as users]
           [uma.utilities :refer [resource-defaults]]))

(defresource get-resource resource-defaults
  :handle-ok users/get-users)

(defresource create-resource resource-defaults
  :post! users/create-user)

(defresource get-resource-by-id [id] resource-defaults
  :handle-ok
    (let [as-integer (Integer. id)]
      (users/get-user-by-id as-integer)))

(defresource update-resource resource-defaults
  :post! users/update-user)

(defroutes routes
  (GET "/" request get-resource)
  (POST "/" request create-resource)
  (GET "/:id" [id] (get-resource-by-id id))
  (POST "/:id" request update-resource))
