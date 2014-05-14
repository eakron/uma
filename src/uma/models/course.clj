(ns uma.models.course
  (require [uma.database :as db]
           [uma.mapping :refer [defmapper]]
           [uma.models.entities :refer [user course registered]]
           [korma.core :refer :all]))

(defmapper mapfn
  {:id :integer
   :title :string
   :difficulty :string
   :semester :string
   :weekday :string
   :startTime :time
   :endTime :time
   :seats :integer
   :price :integer
   :notes :string})

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

(defn get-registered-users [id]
  (select user
    (join registered (= :registered.uma_user_id :id))
    (where {:registered.course_id id})))
