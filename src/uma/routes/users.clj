(ns uma.routes.users
  (require [compojure.core :refer [defroutes context GET POST DELETE]]
           [liberator.core :refer [defresource]]
           [uma.models.users :as users]
           [uma.utilities :refer [resource-defaults]]))

(defresource get-resource resource-defaults
  :handle-ok (users/get-users))

(defresource create-resource [user] resource-defaults
  :allowed-methods [:post]
  :post! {::user (users/create-user user)}
  :handle-created ::user)

(defresource get-resource-by-id [id] resource-defaults
  :handle-ok (let [as-integer (Integer. id)]
               (users/get-user-by-id as-integer)))

(defresource update-resource [{:keys [id] :as user}] resource-defaults
  :allowed-methods [:post]
  :respond-with-entity true
  :post! (let [as-integer (Integer. id)
               mapped (dissoc user :id)]
           {::user (users/update-user as-integer mapped)})
  :handle-created ::user)

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
