(ns uma.routes.occasion
  (require [uma.models.occasion :as occasion]
           [uma.utilities :refer [defroutes-with-crud]]))

(defroutes-with-crud routes
  {:read-all occasion/get-occasions
   :read-by-id occasion/get-occasion-by-id
   :create occasion/create-occasion
   :update occasion/update-occasion
   :delete occasion/delete-occasion
   :mapfn occasion/mapfn})
