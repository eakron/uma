(ns uma.routes.horse
  (require [compojure.core :refer [GET POST DELETE defroutes]]
           [uma.models.horse :refer :all]
           [uma.utilities :refer [defroutes-with-crud json-response]]))

(defroutes routes
  (GET "/" []
    (json-response
      (get-horses)))
  (POST "/" {:keys [body]}
    (json-response
      (create-horse (mapfn body))))
  (GET "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (get-horse-by-id as-integer))))
  (POST "/:id" {:keys [body params]}
    (json-response
      (let [as-integer (Integer. (:id params))
            mapped (mapfn (dissoc body :id))]
        (update-horse as-integer mapped))))
  (DELETE "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (delete-horse as-integer)))))
