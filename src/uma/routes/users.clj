(ns uma.routes.users
  (require [compojure.core :refer [defroutes context GET POST DELETE]]
           [liberator.core :refer [defresource]]
           [uma.models.users :as users]
           [uma.utilities :refer [resource-defaults]]))

(defresource get-resource resource-defaults
  :handle-ok (users/get-users))

(defresource create-resource [user] resource-defaults
  :allowed-methods [:post]
  :post! (users/create-user user))

(defresource get-resource-by-id [id] resource-defaults
  :handle-ok (let [as-integer (Integer. id)]
               (users/get-user-by-id as-integer)))

(defresource update-resource [id user] resource-defaults
  :allowed-methods [:post]
  :post! (let [as-integer (Integer. id)]
           (users/update-user id user)))

(defresource delete-resource [id] resource-defaults
  :allowed-methods [:delete]
  :delete! (let [as-integer (Integer. id)]
             (users/delete-user as-integer)))

(defroutes routes
  (GET "/" request get-resource)
  (POST "/" {:keys [params]} (create-resource params))
  (GET "/:id" [id] (get-resource-by-id id))
  (POST "/:id" {:keys [params]} (update-resource params))
  (DELETE "/:id" [id] (delete-resource id)))
