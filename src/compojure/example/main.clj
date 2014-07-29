(ns compojure.example.main
  (:require
   [ring.server.standalone :as server]
   [compojure.example.routes :refer [app]])
  (:gen-class))

(defn -main []
  (ring.server.standalone/serve
   app
   {:port 3000
    :open-browser? false}))

