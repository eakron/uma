(ns uma.server
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [uma.middleware :as middleware]
            [uma.logging]
            [uma.utilities :as utilities]
            [uma.routes.users :as users]))

(defroutes app-routes
  (context "/users" [] users/routes)
  (utilities/not-found))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-request-logger)
      (middleware/wrap-json-params)
      (middleware/wrap-response-logger)
      (middleware/wrap-exception)))
