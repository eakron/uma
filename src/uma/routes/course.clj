(ns uma.routes.course
  (require [uma.models.course :as course]
           [uma.utilities :refer [defroutes-with-crud]]))

(defroutes-with-crud routes
  {:read-all course/get-courses
   :read-by-id course/get-course-by-id
   :create course/create-course
   :update course/update-course
   :delete course/delete-course})
