(ns uma.models.user
  (require [uma.database :as db]
           [uma.models.entities :refer [course user]]
           [uma.mapping :refer [defmapper]]
           [korma.core :refer :all]))

(defmapper mapfn
  {:id :integer
   :firstName :string
   :lastName :string
   :personalIDNumber :string
   :phoneNumbers :string
   :email :string
   :password :string
   :username :string
   :student :string
   :streetAddress :string
   :postalCode :string
   :city :string
   :notes :string
   :guardians :string
   :membershipNumber :string})

(defn get-users []
  (select user))

(defn get-user-by-id [id]
  (first
    (select user
      (where {:id id}))))

(defn create-user [user-map]
  (insert user
    (values user-map)))

(defn update-user [id user-map]
  (update user
    (set-fields user-map)
    (where {:id id})))

(defn delete-user [id]
  (delete user
    (where {:id id})))
