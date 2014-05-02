(ns uma.routes.users
  (require [compojure.core :refer [defroutes context GET POST]]
           [liberator.core :refer [defresource]]
           [uma.models.users :as users]
           [uma.utilities :refer [resource-defaults]]))

(defresource get-resource resource-defaults
  :handle-ok users/get-users)

(defresource create-resource resource-defaults
  :post! users/create-user)

(defresource get-resource-by-id resource-defaults
  :handle-ok users/get-user-by-id)

(defresource update-resource resource-defaults
  :post! users/update-user)

(defroutes routes
  (GET "/users" request get-resource)
  (POST "/users" request create-resource)
  (GET "/users/:id" request get-resource-by-id)
  (POST "/users/:id" request update-resource))
