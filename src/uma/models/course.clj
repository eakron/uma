(ns uma.models.course
  (require [uma.database :as db]
           [uma.models.entities :refer [occasion user course]]
           [korma.core :refer :all]))

(defn get-courses []
 (select course))

(defn get-course-by-id [id]
  (select course
    (where {:id id})))

(defn create-course [course]
  (insert course
    (values course)))

(defn update-course [id course]
  (update course
    (set-fields course)
    (where {:id id})))

(defn delete-course [id]
  (delete course
    (where {:id id})))
