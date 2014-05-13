(ns uma.routes.course
  (require [uma.models.course :as course]
           [uma.utilities :refer [defroutes-with-crud resource-defaults]]
           [liberator.core :refer [resource]]
           [compojure.core :refer [GET]]))

(defroutes-with-crud routes
  {:read-all course/get-courses
   :read-by-id course/get-course-by-id
   :create course/create-course
   :update course/update-course
   :delete course/delete-course
   :mapfn course/mapfn}
  (GET "/:id/users" [id]
    (resource resource-defaults
      :handle-ok (let [as-integer (Integer. id)]
                   (course/get-registered-users as-integer)))))
