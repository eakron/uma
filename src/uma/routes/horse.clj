(ns uma.routes.horse
  (require [compojure.core :refer [GET POST DELETE defroutes]]
           [uma.models.horse :refer :all]
           [uma.utilities :refer [defroutes-with-crud json-response]]))

(defroutes routes
  (GET "/" []
    (json-response
      (get-horses)))
  (POST "/" {:keys [params]}
    (json-response
      (create-horse (mapfn params))))
  (GET "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (get-horse-by-id as-integer))))
  (POST "/:id" {:keys [params]}
    (json-response
      (let [as-integer (Integer. (:id params))
            mapped (mapfn (dissoc params :id))]
        (update-horse as-integer mapped))))
  (DELETE "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (delete-horse as-integer)))))
