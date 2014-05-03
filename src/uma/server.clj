(ns uma.server
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [uma.middleware :as middleware]
            [uma.logging]
            [uma.utilities :as utilities]
            [uma.routes.user :as user]
            [uma.routes.horse :as horse]
            [uma.routes.occasion :as occasion]
            [uma.routes.course :as course]))

(defroutes app-routes
  (context "/users" [] user/routes)
  (context "/horses" [] horse/routes)
  (context "/courses" [] course/routes)
  (context "/occasions" [] occasion/routes)
  (utilities/not-found))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-request-logger)
      (middleware/wrap-json-params)
      (middleware/wrap-response-logger)
      (middleware/wrap-exception)))
