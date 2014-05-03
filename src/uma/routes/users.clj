(ns uma.routes.users
  (require [compojure.core :refer [defroutes context GET POST DELETE]]
           [liberator.core :refer [resource]]
           [uma.models.users :as users]
           [uma.utilities :refer [resource-defaults]]))

(defroutes routes
  (GET "/" []
    (resource resource-defaults
      :handle-ok (users/get-users)))
  (POST "/" {:keys [params]}
    (resource resource-defaults
      :allowed-methods [:post]
      :post! {::user (users/create-user params)}
      :handle-created ::user))
  (GET "/:id" [id]
    (resource resource-defaults
      :handle-ok (let [as-integer (Integer. id)]
                   (users/get-user-by-id as-integer))))
  (POST "/:id" {:keys [params]}
    (resource resource-defaults
      :allowed-methods [:post]
      :respond-with-entity true
      :post! (let [as-integer (Integer. (:id params))
                   mapped (dissoc params :id)]
               {::user (users/update-user as-integer mapped)})
      :handle-created ::user))
  (DELETE "/:id" [id]
    (resource resource-defaults
      :allowed-methods [:delete]
      :delete! (let [as-integer (Integer. id)]
                 (users/delete-user as-integer)))))
