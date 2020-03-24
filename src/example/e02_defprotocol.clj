(ns example.e02-defprotocol)

; A protocol (like e.g. a java interface) defines common behaviour of different things
(defprotocol i-can-haz-name
  (say-name [self]))

(defrecord my-record [a b c]
  i-can-haz-name
  (say-name [self]
    (println (str a " to the " b " to the " c))))

(defrecord cat [name]
  i-can-haz-name
  (say-name [self] name))

(let [my-instance     (->my-record "a" "b" "c")
      my-big-instance (->my-record "A" "B" "C")
      my-cat          (->cat "Snowball")]
  (println (say-name my-instance))
  (println (say-name my-big-instance))
  (println (say-name my-cat)))