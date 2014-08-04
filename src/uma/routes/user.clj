(ns uma.routes.user
  (require [compojure.core :refer [GET POST DELETE defroutes]]
           [uma.models.user :refer :all]
           [uma.utilities :refer [defroutes-with-crud json-response]]))

(defroutes routes
  (GET "/" []
    (json-response
      (get-users)))
  (POST "/" {:keys [params]}
    (json-response
      (create-user (mapfn params))))
  (GET "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (get-user-by-id as-integer))))
  (POST "/:id" {:keys [params]}
    (json-response
      (let [as-integer (Integer. (:id params))
            mapped (mapfn (dissoc params :id))]
        (update-user as-integer mapped))))
  (DELETE "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (delete-user as-integer)))))
