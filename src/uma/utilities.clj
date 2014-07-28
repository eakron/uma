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

(defmacro defroutes-with-crud
  [sym {:keys [create read-all read-by-id update delete mapfn]} & body]
  `(defroutes ~sym
    (GET "/" []
      (json-response
        (~read-all)))
    (POST "/" {:keys [~'params]}
      (json-response
        (~create (~mapfn ~'params))))
    (GET "/:id" [~'id]
      (json-response
        (let [~'as-integer (Integer. ~'id)]
          (~read-by-id ~'as-integer))))
    (POST "/:id" {:keys [~'params]}
      (json-response
        (let [~'as-integer (Integer. (:id ~'params))
              ~'mapped (~mapfn (dissoc ~'params :id))]
          (~update ~'as-integer ~'mapped))))
    (DELETE "/:id" [~'id]
      (json-response
        (let [~'as-integer (Integer. ~'id)]
          (~delete ~'as-integer))))
    ~@body))
