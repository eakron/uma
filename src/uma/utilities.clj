(ns uma.utilities
  (require [cheshire.core :refer [generate-string]]
           [compojure.core :refer [routes defroutes GET POST DELETE]]
           [liberator.core :refer [resource]]))

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

(def resource-defaults
  {:available-media-types ["application/json"]})

(defmacro defroutes-with-crud
  [sym {:keys [create read-all read-by-id update delete mapfn]} & body]
  `(defroutes ~sym
    (GET "/" []
      (resource resource-defaults
        :handle-ok (~read-all)))
    (POST "/" {:keys [~'params]}
      (resource resource-defaults
        :allowed-methods [:post]
        :post! {::user (~create (~mapfn ~'params))}
        :handle-created ::user))
    (GET "/:id" [~'id]
      (resource resource-defaults
        :handle-ok (let [~'as-integer (Integer. ~'id)]
                     (~read-by-id ~'as-integer))))
    (POST "/:id" {:keys [~'params]}
      (resource resource-defaults
        :allowed-methods [:post]
        :post! (let [~'as-integer (Integer. (:id ~'params))
                     ~'mapped (~mapfn (dissoc ~'params :id))]
                 {::user (~update ~'as-integer ~'mapped)})
        :handle-created ::user))
    (DELETE "/:id" [~'id]
      (resource resource-defaults
        :allowed-methods [:delete]
        :delete! (let [~'as-integer (Integer. ~'id)]
                   (~delete ~'as-integer))))
    ~@body))
