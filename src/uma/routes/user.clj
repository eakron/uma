(ns uma.routes.user
  (require [compojure.core :refer [GET POST DELETE defroutes]]
           [uma.models.user :refer :all]
           [uma.utilities :refer [defroutes-with-crud json-response]]))

(defroutes routes
  (GET "/" []
    (json-response
      (get-users)))
  (POST "/" {:keys [body]}
    (json-response
      (create-user (mapfn body))))
  (GET "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (get-user-by-id as-integer))))
  (POST "/:id" {:keys [body params]}
    (json-response
      (let [as-integer (Integer. (:id params))
            mapped (mapfn (dissoc body :id))]
        (update-user as-integer mapped))))
  (DELETE "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (delete-user as-integer)))))
