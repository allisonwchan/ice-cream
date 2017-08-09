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

;; To commit changes to file, use git commit -a and then git push -u origin master

;; Start Menu

(defcmd start []
  (print "Welcome to your adventure? What would you like to do? (start-game) (view-world-map) (help)"))

(defcmd start-game [] 
  (let [x 0 y 0]
    (-> @*game* (assoc :X x) (assoc :Y y) (assoc :response "You are now in Town1 at [0,0]."))))

(defcmd view-world-map []
  (print "
  _ _ _ _ _ _ Town3 _ _          _ _ _ _ _ _ _ _ Town5  
 |  Route              |        |                   
 |  102                |        |                   
 |               Route |        |Route                   
 |                 103 |        |104                   
Town2                  |        |                   
 |                     |        |                   
 |Route                |        |                   
 |101                  |        |                    
 |                   Town4 - - -                      
 |                                                   
 |                                                   
Town1                                            
"))

(defcmd help []
  (print "
#1 To start the game, type 'start-game'. 
#2 To move, type a cardinal direction and a number of steps, like (north 2). 
#3 To check the coordinates of your current location, type *game*. :X symbolizes your x-value and :Y symbolizes your y-value. 
#4 To check your current location, type (world x y), where x is your x-value and y is your y-value.
#5 To exit out of the Help menu or World Map, type 'start-game'.
"))

;; World Map
(def world-coordinates
  {[2,2] {:House1.1 {[-2,7] "TV" [-3,2] "Man" [2,5] "Woman" [3,7] "Staircase"}}
   [8,2] {:MyHouse {[-3,1] "Bookshelf" [-3,5] "Flowers" [0,4] "Mom" [0,6] "TV" [3,7] "Staircase" [3,1] "Flowers"}}
   [8,6] {:House1.2 {[-3,2] "Bookshelf" [-3,7] "Staircase" [-2,4] "Woman" [0,6] "TV" [2,4] "Woman" [3,2] "Flowers"}}
   [2,6] {:Store {[-4,3] "Man" [3,3] "Girl" [3,4] "Woman" [0,5] "Shop Owner"}}})

(defn world [x y]
  (cond (and (<= x 10) (<= y 10)) "Town1"
        (and (<= x 10) (and (> y 10) (<= y 16))) "Route101"
        (and (<= x 12) (and (> y 16) (<= y 24))) "Town2"
        (and (and (> x 10) (<= x 16)) (and (> y 24) (<= y 36))) "Route102"
        (and (and (> x 16) (<= x 56)) (and (>= y 28) (< y 44))) "Town3"
        (and (and (> x 56) (<= x 58)) (and (and (> y 24) (< = 36)) (and (> y 12) (<= y 20)))) "Route103"
        (and (and (> x 58) (<= x 74)) (and (>= y 2) (<= y 12))) "Town4"
        (and (and (> x 74) (<= x 86)) (and (>= y 2) (<= y 12))) "Route104"
        (and (and (> x 86) (<= x 106)) (and (> y 12) (<= y 32))) "Town5"))

(def current-location  
  (let [x (get @*game* :X)
        y (get @*game* :Y)]
    [x,y]))

(def enemy {:name "Small-Bird" :HP 17 :attack 3})
(def enemy2 {:name "Weird-Rat" :HP 19 :attack 4})
(def enemy3 {:name "Squishy-Caterpillar" :HP 14 :attack 2})
(def enemy4 {:name "Annoying-Moth" :HP 15 :attack 2})
(def enemy5 {:name "Angry-Flower" :HP 13 :attack 5})

(defn get-monster []
  (cond (= 1 (rand-int 9)) enemy
      (= 2 (rand-int 9)) enemy2
      (= 3 (rand-int 9)) enemy3
      (= 4 (rand-int 9)) enemy4
      (= 5 (rand-int 9)) enemy5
      :else nil))

(defn monster-map [] 
  {{[2,12] {:name "grass" :monster {enemy 1}}}
   {[2,13] {:name "grass2" :monster {enemy 1}}}})

;; Nav System
(defcmd north [n]
  (let [new-y (+ (get @*game* :Y) n)
        x (get @*game* :X)
        new-room-desc (get world [x new-y])
        random-monster (get-monster)]
    (-> @*game*
        (assoc :Y new-y)
        (assoc :response (str "You are now in " new-room-desc "\n"
                              (if random-monster
                                (str "A wild " random-monster " appeared! Attack/flee?")
                                "No enemies here!")))
        (assoc :monster random-monster))))

(defcmd south [n]
  (let [new-y (- (get @*game* :Y) n)
        x (get @*game* :X)
        new-room-desc (get world [x new-y])
        random-monster (get-monster)]
    (-> @*game*
        (assoc :Y new-y)
        (assoc :response (str "You are now in " new-room-desc "\n"
                              (if random-monster
                                (str "A wild " random-monster " appeared! Select (attack) (flee)")
                                "No enemies here!")))
        (assoc :monster random-monster))))

(defcmd west [n]
  (let [y (get @*game* :Y)
        new-x (- (get @*game* :X) n)
        new-room-desc (get world [new-x y])
        random-monster (get-monster)]
    (-> @*game*
        (assoc :X new-x)
        (assoc :response (str "You are now in " new-room-desc "\n"
                              (if random-monster
                                (str "A wild " random-monster " appeared! Attack/flee?")
                                "No enemies here!")))
        (assoc :monster random-monster))))

(defcmd east [n]
  (let [y (get @*game* :Y)
        new-x (+ (get @*game* :X) n)
        new-room-desc (get world [new-x y])
        random-monster (get-monster)]
    (-> @*game*
        (assoc :X new-x)
        (assoc :response (str "You are now at " new-room-desc "\n"
                              (if random-monster
                                (str "A wild " random-monster " appeared! Attack/flee?")
                                "No enemies here!")))
        (assoc :monster random-monster))))

(defcmd attack []
  (let [monster (get-monster)
        hp (get monster :HP)
        smack (- hp 8)
        current-location (:current-location @*game*)]
    (-> @*game* (assoc :HP smack)
      (if (<= hp 0)
        (-> @*game*
            (assoc :response (str "The wild " (:name monster) " fainted!"))
            (assoc :monster-map (dissoc (:monster-map @*game*) current-location))))
          (assoc :response "The monster appears to be very angry!"))))

;; Inventory
(defcmd game-menu []
  (print "
Select one of the following:
(financial-status)
(party)
(inventory)
(quit)
"))

(defcmd financial-status []
  (-> @*game*
      (assoc :money "500P")))

(defcmd party []
  (print "
Using: Water Turtle
HP: 18
Attack Damage: 9
"))

(defcmd inventory []
  (print "
Pokeball-twins: 5
Potions: 1
"))
              
;; Make dialogue with store owner:
;; "Hello? How may I help you? (buy) (sell) (quit)

(defcmd buy []
  (print "
Select an item and a quantity (for example, (Potion 2)):
Pokeball-twin 100P
Potion 100P
Better-Potion 200P
Flower-Bouquet 75P
Home-Decorations
"))


