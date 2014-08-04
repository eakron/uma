(ns uma.routes.course
  (require [compojure.core :refer [GET POST DELETE defroutes]]
           [uma.models.course :refer :all]
           [uma.utilities :refer [defroutes-with-crud json-response]]))

(defroutes routes
  (GET "/" []
    (json-response
      (get-courses)))
  (POST "/" {:keys [params]}
    (json-response
      (create-course (mapfn params))))
  (GET "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (get-course-by-id as-integer))))
  (POST "/:id" {:keys [params]}
    (json-response
      (let [as-integer (Integer. (:id params))
            mapped (mapfn (dissoc params :id))]
        (update-course as-integer mapped))))
  (DELETE "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (delete-course as-integer))))
  (GET "/:id/users" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (get-registered-users as-integer)))))
