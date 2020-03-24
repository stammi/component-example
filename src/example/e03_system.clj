(ns example.e03-system
  (:require [com.stuartsierra.component :as c]))

(defrecord ConfigA []
  c/Lifecycle
  (start [self]
    (println "Starting ConfigA.")
    self)
  (stop [self]
    (println "Stoping ConfigA.")
    self))

(defn mk-config []
  (map->ConfigA {}))

(defrecord DatabaseC [config]
  c/Lifecycle
  (start [self]
    (println "Starting DatabaseC.")
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

