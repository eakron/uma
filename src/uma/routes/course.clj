(ns uma.routes.course
  (require [compojure.core :refer [GET POST DELETE defroutes]]
           [uma.models.course :refer :all]
           [uma.utilities :refer [defroutes-with-crud json-response]]))

(defroutes routes
  (GET "/" []
    (json-response
      (get-courses)))
  (POST "/" {:keys [body]}
        (prn body)
    (json-response
      (create-course (mapfn body))))
  (GET "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (get-course-by-id as-integer))))
  (POST "/:id" {:keys [body params]}
    (json-response
      (let [as-integer (Integer. (:id params))
            mapped (mapfn (dissoc body :id))]
        (update-course as-integer mapped))))
  (DELETE "/:id" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (delete-course as-integer))))
  (GET "/:id/users" [id]
    (json-response
      (let [as-integer (Integer. id)]
        (get-registered-users as-integer)))))
