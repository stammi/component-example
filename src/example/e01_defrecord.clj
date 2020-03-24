(ns example.e01-defrecord)

; a record is a hashmap with a number of predefined keys
(defrecord my-record [a b c])

; An constructor ->my-record is automatically created
(let [my-instance (->my-record "value-of-a" "value-of-b" "value-of-c")]
  (println (:b my-instance))
  (println my-instance)
  ; the instance is still a hash-map and can be manipulated like one
  (println (assoc my-instance :d "value-of-d")))


; a second constructor is created map->my-record
; use it if you do not have everything available at creation time
(let [my-incomplete-instance (map->my-record {:a "value-for-a"})
      ; and then add the other values later
      my-completed-instance (assoc my-incomplete-instance
                              :b "value-of-b"
                              :c "value-of-c")]
    (println my-completed-instance))