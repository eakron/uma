(ns uma.routes.horse
  (require [uma.models.horse :as horse]
           [uma.utilities :refer [defroutes-with-crud]]))

(defroutes-with-crud routes
  {:read-all horse/get-horses
   :read-by-id horse/get-horse-by-id
   :create horse/create-horse
   :update horse/update-horse
   :delete horse/delete-horse
   :mapfn horse/mapfn})
