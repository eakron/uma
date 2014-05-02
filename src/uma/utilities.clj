(ns uma.utilities
  (require [cheshire.core :refer [generate-string]]
           [compojure.core :refer [routes]]
           [liberator.core :refer [resource]]))

(defn json-response
  "Wraps data with a simple Ring response object
   and optional status code. Also renames :_id to :id."
  [data & [status]]
  (generate-string
    {:status (or status 200)
     :body data}))

(defn not-found []
  (fn [request]
    (json-response
      {:error "No such route"}
      404)))

(def resource-defaults
  {:available-media-types ["application/json"]})
