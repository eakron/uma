(ns uma.utilities)

(defn json-response
  "Wraps data with a simple Ring response object
   and optional status code. Also renames :_id to :id."
  [data & [status]]
  {:status (or status 200)
   :body data})

(defn not-found []
  (fn [request]
    (json-response
      {:error "No such route"}
      404)))
