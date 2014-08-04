(ns uma.utilities
  (require [cheshire.core :refer [generate-string]]
           [compojure.core :refer [routes defroutes GET POST DELETE]]))

(defn json-response
  "Wraps data with a simple Ring response object
   and optional status code."
  [data & [status]]
  {:status (or status 200)
   :body data})

(defn not-found []
  (fn [request]
    (json-response
      {:error "No such route"}
      404)))
