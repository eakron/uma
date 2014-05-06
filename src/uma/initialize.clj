(ns uma.initialize
  (require [korma.core :refer [insert values]]
           [uma.database :as db]
           [clojure.java.jdbc :as sql]
           [uma.models.user :refer [user]]
           [uma.models.course :refer [course]]
           [uma.models.horse :refer [horse]]
           [uma.models.occasion :refer [occasion]]))

(defn as-time [hour minute]
  (java.sql.Time. hour minute 0))

(defn as-timestamp [year month date]
  (java.sql.Timestamp. year month date 0 0 0 0))

(defn insert-test-data []
  (insert user
    (values
      [{:id 1 :firstName "fname1" :lastName "lname1" :personalIDNumber "1"
        :phoneNumbers "1,2" :email "a@a.a" :password "password1"
        :username "user1" :student true :streetAddress "r1" :postalCode "1"
        :city "townsville" :notes "noted" :guardians "1 & 2"
        :membershipNumber "1"}
       {:id 2 :firstName "fname2" :lastName "lname2" :personalIDNumber "2"
        :phoneNumbers "2,2" :email "a@a.a" :password "password2"
        :username "user2" :student true :streetAddress "r2" :postalCode "2"
        :city "townsville" :notes "noted" :guardians "1 & 2"
        :membershipNumber "1"}
       {:id 3 :firstName "fname3" :lastName "lname3" :personalIDNumber "3"
        :phoneNumbers "3,2" :email "a@a.a" :password "password3"
        :username "user3" :student true :streetAddress "r3" :postalCode "3"
        :city "townsville" :notes "noted" :guardians "1 & 2"
        :membershipNumber "1"}]))
  (insert course
    (values
      [{:id 1 :title "course1" :difficulty "easy" :semester "1"
        :startTime (as-time 14 0) :endTime (as-time 14 0) :seats 1 :price 2
        :notes "a" :weekday "Monday"}
       {:id 2 :title "course2" :difficulty "medium" :semester "1" :notes "a"
        :startTime (as-time 14 0) :endTime (as-time 14 0) :seats 1 :price 2
        :weekday "Monday"}
       {:id 3 :title "course3" :difficulty "hard" :semester "1"
        :startTime (as-time 14 0) :endTime (as-time 14 0) :seats 1 :price 2
        :notes "a" :weekday "Monday"}]))
  (insert horse
    (values
      [{:id 1 :name "horse1" :year "1999" :height "175" :color "fux"
        :description "asd"}
       {:id 2 :name "horse2" :year "1999" :height "175" :color "fux"
        :description "asd"}
       {:id 3 :name "horse3" :year "1999" :height "175" :color "fux"
        :description "asd"}]))
  (insert occasion
    (values
      [{:id 1 :date (as-timestamp 2014 05 25) :description "a"
        :startTime (as-time 14 0) :endTime (as-time 14 0) :price 1
        :course_id 1}
       {:id 2 :date (as-timestamp 2014 05 25) :description "a"
        :startTime (as-time 14 0) :endTime (as-time 14 0) :price 1
        :course_id 1}
       {:id 3 :date (as-timestamp 2014 05 25) :description "a"
        :startTime (as-time 14 0) :endTime (as-time 14 0) :price 1
        :course_id 1}])))
