(ns ice-cream.core
  (:gen-class))

(def ^:dynamic *game* (atom {}))

(defmacro defcmd [name args body]
  `(def ~name (fn ~args
                (compare-and-set! *game* @*game*
                                  ~body)
                (str (:response @*game*)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; Game Code
(defcmd start [] 
  (let [x 0 y 0]
    (-> @*game* (assoc :X x) (assoc :Y y) (assoc :response (str "You are now at the start")))))

(def Town1 {[-6,4] "House" [-6,-4] "House" [6,4] "House" [6,-4] "My House"})
(def Town2 {[-11,5] "House" [-4,5] "Gym" [3,5] "Store" [-8,0] "House" [0,0] "Pokemon Center"})
(def Town3 {[-7,4] "Gym" [-3,4] "House" [0,4] "House" [-9,0] "House" [-6,0] "House" [-3,0] "House" [0,0] "Pokemon Center"})
(def Town4 {[0,6] "House" [0,3] "House" [0,0] "Pokemon Center" [4,4] "Gym" [8,6] "Store" [8,3] "House" [8,0] "House"})
(def Town5 {[0,0] "Pokemon Center" [16,0] "Gym" [32,0] "Store" [-4,-5] "House" [4,-5] "House" [12,-5] "House" [20,-5] "House" [28,-5] "House" [36,-5] "House" [-4,-10] "House" [4,-10] "House" [12,-10] "House" [20,-10] "House" [28,-10] "House" [36,-10] "House"})
(def Town6 {[-10,0] "Haunted House" [-5,0] "Scary House" [0,0] "Pokemon Center" [-10,5] "Scary House" [0,-5] "Haunted House"})
;; Add the Pokemon appearing spot later, when you figure out how to do it lmao
(def Town7 {[-4,8] "Store" [0,8] "House" [4,8] "House" [8,8] "House" [-4,4] "House" [2,4] "Gym" [8,4] "House" [-4,0] "House" [0,0] "Pokemon Center" [4,0] "House" [8,0] "House"})
(def Town8 {[0,20] "Final Boss"})
;; Put in some dialogue (if it isn't too complicated and you don't get too frustrated haha

(defcmd north [n]
  (let [new-y (+ (get @*game* :Y) n)
        x (get @*game* :X)
        new-room-desc (get Town1 [x new-y])]
    (-> @*game* (assoc :Y new-y) (assoc :response (str "You are now in " new-room-desc)))))

(defcmd south [n]
  (let [new-y (- (get @*game* :Y) n)
        x (get @*game* :X)
        new-room-desc (get Town1 [x new-y])]
    (-> @*game* (assoc :Y new-y) (assoc :response (str "You are now in " new-room-desc)))))

(defcmd west [n]
  (let [y (get @*game* :Y)
        new-x (- (get @*game* :X) n)
        new-room-desc (get Town1 [new-x y])]
    (-> @*game* (assoc :X new-x) (assoc :response (str "You are now in " new-room-desc)))))

(defcmd east [n]
  (let [y (get @*game* :Y)
        new-x (+ (get @*game* :X) n)
        new-room-desc (get Town1 [new-x y])]
    (-> @*game* (assoc :X new-x) (assoc :response (str "You are now in " new-room-desc)))))





