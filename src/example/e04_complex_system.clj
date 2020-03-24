(ns example.e04-complex-system
  (:require [com.stuartsierra.component :as c]
            [clojure.edn :as edn]))

(defrecord ConfigA []
  c/Lifecycle
  (start [self]
    (println "Starting ConfigA.")
    (let [conf (edn/read-string (slurp "resources/config.edn"))]
      (assoc self :conf conf)))
  (stop [self]
    (println "Stoping ConfigA.")
    self))

(defn mk-config []
  (map->ConfigA {}))

(defrecord DatabaseC [config]
  c/Lifecycle
  (start [self]
    (println "Starting DatabaseC.")
    (println "My config is " (get-in config [:conf :db]))
    self)
  (stop [self]
    (println "Stoping DatabaseC.")
    self))

(defn mk-database []
  (map->DatabaseC {}))

(defrecord HttpEndpointD [database]
  c/Lifecycle
  (start [self]
    (println "Starting HttpEndpointD.")
    self)
  (stop [self]
    (println "Stoping HttpEndpointD.")
    self))

(defn mk-endpoint []
  (map->HttpEndpointD {}))

(defn mk-system []
  (c/system-map
    :config (mk-config)
    :database (c/using (mk-database) [:config])
    :endpoint (c/using (mk-endpoint) [:database])))

