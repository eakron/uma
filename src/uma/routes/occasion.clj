(ns uma.routes.occasion
  (require [compojure.core :refer [GET POST DELETE defroutes]]
           [uma.models.occasion :refer :all]
           [uma.utilities :refer [defroutes-with-crud json-response]]))

(defroutes routes
  (GET "/" []
    (json-response
      (get-occasions)))
  (POST "/" {:keys [params]}
    (json-response
      (create-occasion (mapfn params))))
  (GET "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (get-occasion-by-id as-integer))))
  (POST "/:id" {:keys [params]}
    (json-response
      (let [as-integer (Integer. (:id params))
            mapped (mapfn (dissoc params :id))]
        (update-occasion as-integer mapped))))
  (DELETE "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (delete-occasion as-integer)))))
