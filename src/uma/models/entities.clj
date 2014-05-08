(ns uma.models.entities
  (require [korma.core :refer :all]))

(declare course occasion)

(defentity user
  (table :uma_user)
  (many-to-many course :registered))

(defentity course
  (has-many occasion)
  (many-to-many user :registered))

(defentity horse
  (many-to-many occasion :walks))

(defentity occasion
  (belongs-to course)
  (many-to-many horse :walks))

(defentity registered
  (belongs-to user)
  (belongs-to course))

(defentity teaches
  (belongs-to user)
  (belongs-to course))

(defentity walks
  (belongs-to horse)
  (belongs-to occasion))
