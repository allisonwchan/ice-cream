(ns ice-cream.core
  (:gen-class))

(def ^:dynamic *game* (atom {}))

(defmacro defcmd [name args body]
  `(def ~name (fn ~args
                (compare-and-set! *game* @*game*
                                  ~body)
                (str (:response @*game*)))))

(defn -main
  "I didn't do a whole lot ... yet."
  [& args]
  (println "Hello World!"))

;; To commit changes to file, use git commit -a and then git push -u origin master

;; Start Menu
(defn start []
  (println "Welcome to your adventure! What would you like to do? 
(start-game) 
(view-world-map) 
(help)
"))

(defcmd start-game []
  (let [h 21 x 0 y 0 z 500]
    (-> @*game*
        (assoc :X x
               :Y y
               :pokedollars z
               :HP h
               :pokemon nil
               :response "You are now outside in Town1 at [0,0]."))))
(defn view-world-map []
  (println "
  _ _ _ _ _ _ _ _ Town3 _ _           _ _ _ _ _ _ _ _ Town5  
 |  Route                  |        |                   
 |  102                    |        |                   
 |                   Route |        |Route                   
 |                     103 |        |104                   
Town2                      |        |                   
 |                         |        |                   
 |Route                    |        |                   
 |101                      |        |                    
 |                      Town 4 - - -                
 |                                                   
 |                                                   
Town1                                            
"))

(defn help []
  (println "
#1 To start the game, type 'start-game'. 
#2 To move, type a cardinal direction and a number of steps, like (north 2). 
#3 To check the coordinates of your current location, type *game*. :X symbolizes your x-value and :Y symbolizes your y-value. 
#4 To check your current location, type (world x y), where x is your x-value and y is your y-value.
#5 To exit out of the Help menu or World Map, type 'start-game'.
"))

;; World Map
(defn Town1-map []
 (println "
                   - ^ -                       |  Legend: + <your starting
                                               |          location>
          _ _ _            _ _ _               |          - ^ - <exit to Route101
         |     |          |     |              |                 [5,10]>                   |H1.1 |          |H1.2 |              |          H1.1 <House1.1 [2,6]>
         |     |          |     |              |          H1.2 <House1.2 [8,6]>
          - ^ -            - ^ -               |          L <Lab [2,2]>
                                               |          H <Home [8,2]>
                                               |          ^ <door>
          _ _ _            _ _ _               |          *Map not to scale 
         |     |          |     |              |         
         |L    |          |H    |              |            
         |     |          |     |              |            
          - ^ -            - ^ -               |  
                                               |
                                               |
+                                              |
[0,0]                                          |
"))

(defn House11-map []
  (println "
 _ _ _ _ _           |  Legend: F <flowers>
|F W      |          |          K <bookshelf>
|         |          |          M <man>
|       TV|          |          W <woman>
| M       |          |          TV <television>
|        K|          |  *Map not to scale
 - - ^ - -           |
"))

(defn House12-map []
  (println "
 _ _ _ _ _      | Legend: K <bookshelf>
|S  TV    |     |         S <staircase>
|         |     |         TV <television>
|  W  W   |     |         W <woman>
|         |     |         ^ <door>
|K       F|     | *Map not to scale
 - - ^ - -      |
"))

(defn Home-map []
  (println "
 _ _ _ _ _      | Legend: K <bookshelf>
|        S|     |         F <flowers>
|K        |     |         W <woman (mom)>
|    W  TV|     |         TV <televsion>
|         |     |         S <staircase>
|F        |     |         ^ <door>
 - - ^ - -      | *Map not to scale
"))

(defn Route101-map []
  (println "
         Town2
         - ^ -                        |  Legend: Town2 
|                     |               |          - ^ -  <Entrance to Town2 [5,16]>
|                     |               |          
|                     |               |          - ^ -
|                     |               |          Town1  <Exit to Town1 [5,10]>
|                     |               |  *Everywhere is tall-grass
|                     |               |  *Width 10
         - ^ -                        |  *Map not to scale
         Town1                        |
"))
(defn Town2-map []
  (println "                 
                 102                      |  Legend: G <Gym2>
                - ^ -                     |          H2.1 <House2.1 [8,24]>
                                          |          H2.2 <House2.2 [8,18]> 
                                          |          PC <PokemonCenter2 [10,24]>
 _ _ _ _ _ _ _ _ _      _ _ _     _ _ _   |          $ <PokeMart2 [10,18]>
|                 |    |     |   |     |  |          - ^ - <exit to Route101
|        G        |    |H2.1 |   |PC   |  |                [5,16]>
 - - - - ^ - - - -      - ^ -     - ^ -   |          102 
                                          |          - ^ - <entrance to Route102>
                                          |                [5,24]>
                        _ _ _     _ _ _   |          ^ <door>
                       |     |   |     |  |          *Map not to scale       
                       |H2.2 |   |$    |  |          
                       |     |   |     |  |          
                        - ^ -     - ^ -   |          
                                          |          
            - ^ -                         |
"))

(defn Gym2-map []
  (println "
 _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _     |  Legend: L <Gym Leader>  
|                    L                      |    |          T <Pokemon Trainer>
|                                           |    |          ^ <door>  
|          T                   T            |    |  *Map not to scale
|                                           |    |            
|          T                   T            |    |          
|                                           |    |  
|          T                   T            |    |
 - - - - - - - - - - ^ - - - - - - - - - - -     |
"))

(defn House21-map []
  (println "
 _ _ _ _ _                  |  Legend: TV <television>
|    TV   |                 |          M <man>
|         |                 |          ^ <door>
|    M    |                 |  *Map not to scale
|         |                 |
 - - ^ - -                  |
"))

(defn PokemonCenter2-map []
  (println "
 _ _ _ _ _ _ _                |  Legend: N <Nurse Joy>
|      N      |               |          W <woman>
|             |               |          M <man>
|             |               |          ^ <door>
|        M    |               |  *Map not to scale
| W           |               |
|             |               |
 - - - ^ - - -                |
"))

(defn House22-map []
  (println "
 _ _ _ _ _                   |  Legend: TV <television>           
|      TV |                  |          W <woman>
|         |                  |          F <flowers>
|      WW |                  |  *Map not to scale
|         |                  |
|F        |                  |
 - - ^ - -                   |
"))

(defn PokeMart2-map []
  (println "
 _ _ _ _ _ _ _                 |  Legend: R <StoreOwner2> 
|      R      |                |          M <man>
|             |                |          W <woman>
| M          W|                |          G <girl>
| G           |                |          ^ <door>
| W        M  |                |  *Map not to scale
|             |                |
 - - - ^ - - -                 |
"))

(defn Route102-map []
  (println "
 _ _ _ _ _ _ _ _ _ _ _ _              |  Legend: - ^ -
|                       |             |          Town2  <Exit to Town2 [5,24]>
|                       > Town3       |  
|                       |             |          |
|      - - - - - - - - -              |          > Town3 
|     |                               |          |       <Entrance to Town3 
|     |                               |                  [13,28]>
 - ^ -                                |  *Width 12,16
 Town2                                |  *Map not to scale
"))

(defn Town3-map []
  (println "                                                
    _ _ _ _ _     _ _ _ _ _ _ _ _ _     _ _ _ _ _ _                     
   |         |   |                 |   |           |       
   |PC       |   |G                |   |$          |       
   |         |   |                 |   |           |      
    - - ^ - -     - - - - ^ - - - -     - - - ^ - -       
|                                                     |   
>                                                     > 103   
|   _ _ _   _ _ _   _ _ _   _ _ _   _ _ _   _ _ _     |    
   |     | |     | |     | |     | |     | |     |         
   |H3.1 | |H3.2 | |H3.3 | |H3.4 | |H3.5 | |H3.6 |         
   |     | |     | |     | |     | |     | |     |         
    - ^ -   - ^ -   - ^ -   - ^ -   - ^ -   - ^ -         
                                                         
    _ _ _   _ _ _   _ _ _   _ _ _   _ _ _   _ _ _          
   |     | |     | |     | |     | |     | |     |           
   |H3.7 | |H3.8 | |H3.9 | |H3.10| |H3.11| |H3.12|         
   |     | |     | |     | |     | |     | |     |         
    - ^ -   - ^ -   - ^ -   - ^ -   - ^ -   - ^ - 

         
----------------------------------------------------------------------------------
Legend: PC <PokemonCenter3 [16,30]>
        G <Gym3 [34,30]>
        $ <PokeMart3 [52,30]>
        H3.1 <House3.1 [14,24]>      H3.7 <House3.7 [14,16]>
        H3.2 <House3.2 [22,24]>     H3.8 <House3.8 [22,16]>
        H3.3 <House3.3 [30,24]>     H3.9 <House3.9 [30,16]>
        H3.4 <House3.4 [38,24]>     H3.10 <House3.10 [38,16]>
        H3.5 <House3.5 [46,24]>     H3.11 <House3.11 [46,16]>
        H3.6 <House3.6 [52,24]>     H3.12 <House3.12 [52,16]>
        |                             |
        >                             > 103
        | <Exit to Route102 [13,28]   |     <Exit to Route103 [53,28]>
        ^ <door>
*Map not to scale
"))

(defn PokemonCenter3-map []
  (println "
 _ _ _ _ _ _ _ _ _           |  Legend: N <Nurse Joy>
|        N        |          |          M <man>
|                 |          |          W <woman>
|B                |          |          B <boy>
|              W  |          |          G <girl>
| W          G    |          |          S <staircase>
|S  M            S|          |          ^ <door>
 - - - - ^ - - - -           |  *Map not to scale
"))

(defn Gym3-map []
  (println "
 _ _ _ _ _ _ _ _ _ _ _ _ _ _ _      |  Legend: L <Gym Leader>
|              L              |     |          T <Pokemon Trainer>
|            T   T            |     |          ^ <door>
|          T       T          |     |  *Map not to scale          
|        T           T        |     |          
|      T               T      |     |  
|    T                   T    |     |
 - - - - - - - ^ - - - - - - -      |
"))

(defn PokeMart3-map []
  (println "
 _ _ _ _ _ _ _ _ _ _ _           |  Legend: R <StoreOwner3>           
|          R          |          |          M <man>
|                     |          |          W <woman>
|     B              M|          |          B <boy>
|  W            W     |          |          ^ <door>
|              B   M  |          |  *Map not to scale
|  M                  |          |
 - - - - - ^ - - - - -           |
"))

(defn House31-map []
  (println "
 _ _ _ _ _            |  Legend: TV <television>
|    TV   |           |          M <man>
|         |           |          W <woman>
|         |           |          ^ <door>
|       M |           |  *Map not to scale
| W       |           |
 - - ^ - -            |
"))

(defn House32-map []
  (println "
 _ _ _ _ _              |  Legend: B <bookshelf>
|    B    |             |          M <man>
|    M    |             |          F <flowers>
|         |             |          ^ <door>
|         |             |  *Map not to scale
|F        |             |
 - - ^ - -              |
"))

(defn House33-map []
  (println "
 _ _ _ _ _            |  Legend: TV <television>
|         |           |          M <man>
|         |           |          B <boy>
| M     TV|           |          ^ <door>
|         |           |  *Map not to scale
| B       |           |
 - - ^ - -            |
"))

(defn House35-map []
  (println "
 _ _ _ _ _             |  Legend: TV <television>
|    TV   |            |          G <girl>
|         |            |          F <flowers>
|  G   G  |            |          ^ <door>
|         |            |  *Map not to scale
|F        |            |
 - - ^ - -             |
"))

(defn House37-map []
  (println "
 _ _ _ _ _             |  Legend: TV <television>
|F      TV|            |          F <flowers>
|         |            |          K <bookshelf>
|F        |            |          W <woman>
|        K|            |          ^ <door>
|F W      |            |  *Map not to scale
 - - ^ - -             |
"))

(defn House38-map []
  (println "
 _ _ _ _ _          |  Legend: TV <television>
|    TV   |         |          F <flowers>
|         |         |          M <man>
|         |         |          W <woman>
|  M      |         |          ^ <door>
|F W      |         |  *Map not to scale
 - - ^ - -          |
"))

(defn House310-map []
  (println "
 _ _ _ _ _            |  Legend: TV <television>
|F        |           |          F <flowers>
|G        |           |          W <woman>
|       TV|           |          G <girl>
|         |           |          ^ <door>
|  W      |           |  *Map not to scale
 - - ^ - -            |
"))

(defn House312-map []
  (println "
 _ _ _ _ _                |  Legend: TV <television>
|    TV   |               |          K <bookshelf>
|         |               |          M <man>
|K B      |               |          B <boy>
|         |               |          ^ <door>
|      M  |               |  *Map not to scale
 - - ^ - -                |
"))

(defn Route103-map []
  (println "
       _ _ _ _ _ _ _ _         |  Legend:       |
      |               |        |          Town3 >
Town3 >               |        |                |   <Exit to Town3 [53,28]>
      |               |        |
       - -            |        |          - V -
          |           |        |          Town4     <Entrance to Town4 [55,20]>
          |           |        |  *Everywhere is tall-grass
          |           |        |  *Width 16
          |           |        |  *Map not to scale
          |           |        |
          |           |        |
          |           |        |
          |           |        |
              - V -            |
              Town4            |
"))
(defn Town4-map []
  (println "
          - ^ -                      |  Legend: HH <HauntedHouse [50,18]>
   _ _ _         _ _ _               |          SH <ScaryHouse [60,18]>
  |     |       |     |              |          PC <PokemonCenter4 [55,13]>
  |HH   <       >   SH|              |          - ^ - <Exit to Route103 [55,20]>
  |     |       |     |              |          |
   - - -         - - -     |         |          > 104
          _ _ _            > 104     |          |     <Exit to Route104 [63,20]>
         |     |           |         |          <,> <door>
         |PC   <                     |  *Map not to scale
         |     |                     |
          - - -                      |
"))

(defn ScaryHouse-map []
  (println "
 _ _ _ _ _ _ _              |  Legend: M <man>
|             |             |          ^ <door>
|             |             |  *Map not to scale
|             |             |
|      M      |             |
|             |             |
|             |             |
|             |             |
 - - - ^ - - -              |
"))

(defn PokemonCenter4-map []
  (println "
 _ _ _ _ _ _ _         |  Legend: N <Nurse Joy>
|      N      |        |          M <man>
|             |        |          S <staircase>
|             |        |          ^ <door>
|           M |        |  *Map not to scale
|             |        |
|             |        |
|S           S|        |
 - - - ^ - - -         |
"))
(defn Route104-map []
  (println "
              _ _ _ _ _ _ _ _                |  Legend:       |
             |               |               |          Town4 >
             |               > Town5         |                |  <Exit to Town4
             |       _ _ _ _ |               |                   [63,20]>
             |      |                        |          |
             |      |                        |          > Town5 
             |      |                        |          |        <Exit to Town5
             |      |                        |                   [74,28]>
       _ _ _ |      |                        |
      |             |                        |
Town4 >             |                        |
      |             |                        |
       - - - - - - -                         |
"))
(defn Town5-map []
  (println "
                                               |  Legend: |
                                               |          >
                                               |          | <exit to Route104
|                                              |            [74,28]>
>                                           ?  |          ? <Final Boss [94,28]>
|                                              |
                                               |
                                               |
                                               |
"))

(def current-location "Town1")
(def town-location "outside")

(defn check-location [x y]
  (cond (and (and (>= x 0) (<= x 10)) (and (>= y 0) (< y 10))) "You are in Town1."
        (and (and (>= x 0) (<= x 10)) (and (>= y 10) (<= y 16))) "You are in Route101."
        (and (and (>= x 0) (<= x 12)) (and (> y 16) (< y 24))) "You are in Town2."
        (and (and (>= x 0) (<= x 13)) (and (>= y 24) (<= y 36))) "You are in Route102."
        (and (and (> x 13) (< x 53)) (and (>= y 20) (<= y 36))) "You are in Town3."
        (and (and (>= x 53) (< x 63)) (and (= y 20) (<= y 36))) "You are in Route103."
        (and (and (>= x 47) (< x 63)) (and (>= y 10) (< y 20))) "You are in Town4."
        (and (and (>= x 63) (<= x 74)) (and (>= y 13) (<= y 38))) "You are in route104."
        (and (and (> x 74) (<= x 94)) (and (>= y 18) (<= y 38))) "You are in Town5."
        :else "You have lost your way. Please return to the map."))

(def world-coordinates
  {;; Town1
   [2,6] "House1.1" [8,6] "House1.2" [2,2] "Lab" [8,2] "Home" [5,10] "Exit of Town1/Entrance to Route101"
   ;; Town2
   [3,24] "Gym2" [8,24] "House2.1" [8,18] "House2.2" [10,24] "PokemonCenter2" [10,18] "PokeMart2" [5,16] "Exit of Route101/Entrance to Town2" [5,24] "Exit of Town2/Entrance to Route102" 
   ;; Town3
   [16,30] "PokemonCenter3" [34,30] "Gym3" [52,30] "PokeMart3" [14,24] "House3.1" [22,24] "House3.2" [30,24] "House3.3" [38,24] "House3.4" [46,24] "House3.5" [52,24] "House3.6" [14,16] "House3.7" [22,16] "House3.8" [30,16] "House3.9" [38,16] "House3.10" [46,16] "House3.11" [52,16] "House3.12" [13,28] "Exit to Route102/Entrance to Town3" [53,28] "Exit of Town3/Entrance to Route103"
   ;; Town4
   [50,18] "HauntedHouse" [60,18] "ScaryHouse" [55,13] "PokemonCenter4" [55,20] "Exit to Route103/Entrance to Town4" [63,20] "Exit of Town4/Entrance to Route104"
   ;; Town5
   [74,28] "Exit to Route104/Entrance to Town5" [94,28] "FinalBoss"})

;; Monster Zone 1
(def small-bird {:name "Small-Bird" :HP 17 :attack 3})
(def weird-mouse {:name "Weird-Mouse" :HP 19 :attack 4})
(def squishy-caterpillar {:name "Squishy-Caterpillar" :HP 14 :attack 2})
(def annoying-fly {:name "Annoying-Fly" :HP 15 :attack 2})
(def angry-flower {:name "Angry-Flower" :HP 13 :attack 5})
;; Monster Zone 2
(def medium-bird {:name "Medium-Bird" :HP 25 :attack 7})
(def crazy-rat {:name "Crazy-Rat" :HP 26 :attack 10})
(def floppy-butterfly {:name "Floppy-Butterfly" :HP 23 :attack 6})
;; Monster Zone 3
(def insane-mega-fly {:name "Insane-Mega-Fly" :HP 25 :attack 8})
(def carnivorous-flower {:name "Carnivorous-Flower" :HP 20 :atttack 25})
(def buff-rock {:name "Buff-Rock" :HP 27 :attack 12})
(def water-snek {:name "Water-Snek" :HP 25 :attack 11})
(def skinny-lizard {:name "Skinny-Lizard" :HP 18 :attack 5})
(def large-lizard {:name "Large-Lizard" :HP 26 :attack 10})
;; Monster Zone 4
(def sparkly-snowflake {:name "Sparkly-Snowflake" :HP 19 :attack 12})
(def scary-ghost {:name "Scary-Ghost" :HP 21 :attack 13})
(def bye-felicia {:name "Bye-Felicia" :HP 30 :attack 0})
(def fire-dragon {:name "Fire-Dragon" :HP 30 :attack 20})
(def squeaky-elevator {:name "Squeaky-Elevator" :HP 24 :attack 15})
(def poisonous-ghost {:name "Poisonous-Ghost" :HP 22 :attack 15})
(def vicious-chipmunk {:name "Vicious-Chipmunk" :HP 25 :attack 24})

(defn get-monster []
  (if (= town-location "tall-grass")
    (cond (= current-location "Route101")
          (cond (= (rand-int 4) 0) small-bird 
                (= (rand-int 4) 1) weird-mouse 
                (= (rand-int 4) 2) squishy-caterpillar 
                (= (rand-int 4) 3) annoying-fly 
                (= (rand-int 4) 4) angry-flower 
                :else nil)
          (= current-location "Route102")
          (cond (= (rand-int 4) 0) medium-bird 
                (= (rand-int 4) 1) crazy-rat
                (> (rand-int 4) 1) floppy-butterfly 
                :else nil)
          (= current-location "Route103")
          (cond (= (rand-int 9) 0) insane-mega-fly 
                (= (rand-int 9) 1) carnivorous-flower 
                (or (= (rand-int 9) 2) (= (rand-int 9) 3)) buff-rock
                (or (= (rand-int 9) 4) (= (rand-int 9) 5)) water-snek
                (> (rand-int 9) 6) skinny-lizard 
                (= (rand-int 9) 6) large-lizard
                :else nil)
          (= current-location "Route104")
          (cond (or (= (rand-int 19) 0) (= (rand-int 19) 1)) sparkly-snowflake
                (> (rand-int 19) 12) scary-ghost
                (or (= (rand-int 19) 2) (= (rand-int 19) 3)) bye-felicia
                (= (rand-int 20) 4) fire-dragon
                (or (= (rand-int 19) 5) (= (rand-int 19) 6)) squeaky-elevator
                (and (<= (rand-int 20) 7) (>= (rand-int 19) 10)) poisonous-ghost
                (or (= (rand-int 19) 11) (= (rand-int 19) 12)) vicious-chipmunk
                :else nil)
          :else nil)))

(def pokedex (atom {}))
(swap! pokedex assoc "Water-Turtle" 1)

(def inventory (atom {}))
(swap! inventory assoc "Pokeball" 5)  
  
;; Nav System
(defcmd north [n]
  (let [y (+ (get @*game* :Y) n)
        x (get @*game* :X)
        monsta (get-monster)
        new-room-desc (world-coordinates [x y])]
    (-> @*game*
        (assoc :Y y
               :response (str "You are at " new-room-desc ". "
                              (if monsta
                                (str "A wild " monsta " appeared! Will you toss a Pokeball to catch it?")))
               :pokemon monsta))))

(defcmd south [n]
  (let [new-y (- (get @*game* :Y) n)
        x (get @*game* :X)
        monsta (get-monster)
        new-room-desc (get world-coordinates [x new-y])]
    (-> @*game*
        (assoc :Y new-y)
        (assoc :response (str "You are at " new-room-desc ". "
                              (if monsta
                                (str "A wild " monsta " appeared! Will you toss a Pokeball to catch it?")))
               :pokemon monsta))))

(defcmd west [n]
  (let [y (get @*game* :Y)
        new-x (- (get @*game* :X) n)
        monsta (get-monster)
        new-room-desc (get world-coordinates [new-x y])]
    (-> @*game*
        (assoc :X new-x)
        (assoc :response (str "You are at " new-room-desc ". "
                              (if monsta
                                (str "A wild " monsta " appeared! Will you toss a Pokeball to catch it?")))
               :pokemon monsta))))

(defcmd east [n]
  (let [y (get @*game* :Y)
        new-x (+ (get @*game* :X) n)
        monsta (get-monster)
        new-room-desc (get world-coordinates [new-x y])]
    (-> @*game*
        (assoc :X new-x)
        (assoc :response (str "You are at " new-room-desc ". "
                              (if monsta
                                (str "A wild " monsta " appeared! Will you toss a Pokeball to catch it?")))
               :pokemon monsta))))

;; Catching Pokemon
(defn toss [n]
  (cond (= n "Pokeball")
        (cond (= current-location "Route101")
              (cond (= (get @*game* :pokemon) small-bird)
                    (do
                      (assoc @*game* :pokemon nil)
                      (if (<= (get @inventory n) 0)
                        "You are out of Pokeballs!"
                        (do
                          (if (contains? @pokedex "Small-Bird")
                            (swap! pokedex assoc "Small-Bird" (+ (get @pokedex "Small-Bird") 1))
                            (swap! pokedex conj ["Small-Bird" 1]))
                          (swap! inventory assoc n (- (get @inventory n) 1))
                          (println "You caught a Small-Bird!"))))
                    (= (get @*game* :pokemon) weird-mouse)
                    (do
                      (assoc @*game* :pokemon nil)
                      (if (<= (get @inventory n) 0)
                        "You are out of Pokeballs!"
                        (do
                          (if (contains? @pokedex "Weird-Mouse")
                            (swap! pokedex assoc "Weird-Mouse" (+ (get @pokedex "Weird-Mouse") 1))
                            (swap! pokedex conj ["Weird-Mouse" 1]))
                          (swap! inventory assoc n (- (get @inventory n) 1))
                          (println "You caught a Weird-Mouse!"))))
                    (= (get @*game* :pokemon) squishy-caterpillar)
                    (do
                      (assoc @*game* :pokemon nil)
                      (if (<= (get @inventory n) 0)
                        "You are out of Pokeballs!"
                        (do
                          (if (contains? @pokedex "Squishy-Caterpillar")
                            (swap! pokedex assoc "Squishy-Caterpillar" (+ (get @pokedex "Squishy-Caterpillar") 1))
                            (swap! pokedex conj ["Squishy-Caterpillar" 1]))
                          (swap! inventory assoc n (- (get @inventory n) 1))
                          (println "You caught a Squishy-Caterpillar!"))))
                    (= (get @*game* :pokemon) annoying-fly)
                    (do
                        (assoc @*game* :pokemon nil)
                        (if (<= (get @inventory n) 0)
                          "You are out of Pokeballs!"
                          (do
                            (if (contains? @pokedex "Annoying-Fly")
                              (swap! pokedex assoc "Annoying-Fly" (+ (get @pokedex "Annoying-Fly") 1))
                              (swap! pokedex conj ["Annoying-Fly" 1]))
                            (swap! inventory assoc n (- (get @inventory n) 1))
                            (println "You caught an Annoying-Fly!"))))
                    (= (get @*game* :pokemon) angry-flower)
                    (do
                      (assoc @*game* :pokemon nil)
                      (if (<= (get @inventory n) 0)
                        "You are out of Pokeballs!"
                        (do
                          (if (contains? @pokedex "Angry-Flower")
                            (swap! pokedex assoc "Angry-Flower" (+ (get @pokedex "Angry-Flower") 1))
                            (swap! pokedex conj ["Angry-Flower" 1]))
                          (swap! inventory assoc n (- (get @inventory n) 1))
                          (println "You caught an Angry-Flower!"))))
                    :else "You cannot catch this.")
              (= current-location "Town2")
              (cond (= (get @*game* :pokemon) medium-bird)
                    (if (<= (rand-int 9) 7)
                      (do
                        (assoc @*game* :pokemon nil)
                        (if (<= (get @inventory n) 0)
                          "You are out of Pokeballs!"
                          (do
                            (if (contains? @pokedex "Medium-Bird")
                              (swap! pokedex assoc "Medium-Bird" (+ (get @pokedex "Medium-Bird") 1))
                              (swap! pokedex conj ["Medium-Bird" 1]))
                            (swap! inventory assoc n (- (get @inventory n) 1))
                            (println "You caught a Medium-Bird!"))))
                      "Aww! You didn't catch the Medium-Bird! Toss another Pokeball?")
                    (= (get @*game* :pokemon) crazy-rat)
                    (if (= (rand-int 1) 0)
                      (do
                        (assoc @*game* :pokemon nil)
                        (if (<= (get @inventory n) 0)
                          "You are out of Pokeballs!"
                          (do
                            (if (contains? @pokedex "Crazy-Rat")
                              (swap! pokedex assoc "Crazy-Rat" (+ (get @pokedex "Crazy-Rat") 1))
                              (swap! pokedex conj ["Crazy-Rat" 1]))
                            (swap! inventory assoc n (- (get @inventory n) 1))
                            (println "You caught a Crazy-Rat!"))))
                      "Aww! You didn't catch the Crazy-Rat! Toss another Pokeball?")
                    (= (get @*game* :pokemon) floppy-butterfly)
                    (do
                      (assoc @*game* :pokemon nil)
                      (if (<= (get @inventory n) 0)
                        "You are out of Pokeballs!"
                        (do
                          (if (contains? @pokedex "Floppy-Butterfly")
                            (swap! pokedex assoc "Floppy-Butterfly" (+ (get @pokedex "Floppy-Butterfly") 1))
                            (swap! pokedex conj ["Floppy-Butterfly" 1]))
                          (swap! inventory assoc n (- (get @inventory n) 1))
                          (println "You caught a Floppy Butterfly!"))))
                    :else "You cannot catch this.")
              (= current-location "Town3")
              (cond (= (get @*game* :pokemon) insane-mega-fly)
                    (if (<= (rand-int 9) 7)
                      (do
                        (assoc @*game* :pokemon nil)
                        (if (<= (get @inventory n) 0)
                          "You are out of Pokeballs!"
                          (do
                            (if (contains? @pokedex "Insane-Mega-Fly")
                              (swap! pokedex assoc "Insane-Mega-Fly" (+ (get @pokedex "Insane-Mega-Fly") 1))
                              (swap! pokedex conj ["Insane-Mega-Fly" 1]))
                            (swap! inventory assoc n (- (get @inventory n) 1))
                            (println "You caught an Insane-Mega-Fly!"))))
                      "Aww! You didn't catch the Insane-Mega-Fly! Toss another Pokeball?")
                    (= (get @*game* :pokemon) carnivorous-flower)
                    (if (<= (rand-int 9) 6)
                      (do
                        (assoc @*game* :pokemon nil)
                        (if (<= (get @inventory n) 0)
                          "You are out of Pokeballs!"
                          (do
                            (if (contains? @pokedex "Carnivorous-Flower")
                              (swap! pokedex assoc "Carnivorous-Flower" (+ (get @pokedex "Carnivorous-Flower") 1))
                              (swap! pokedex conj ["Carnivorous-Flower" 1]))
                            (swap! inventory assoc n (- (get @inventory n) 1))
                            (println "You caught a Carnivorous-Flower!"))))
                      "Aww! You didn't catch the Carnivorous-Flower! Toss another Pokeball?")
                    (= (get @*game* :pokemon) buff-rock)
                    (if (<= (rand-int 9) 8)
                      (do
                        (assoc @*game* :pokemon nil)
                        (if (<= (get @inventory n) 0)
                          "You are out of Pokeballs!"
                          (do
                            (if (contains? @pokedex "Buff-Rock")
                              (swap! pokedex assoc "Buff-Rock" (+ (get @pokedex "Buff-Rock") 1))
                              (swap! pokedex conj ["Buff-Rock" 1]))
                            (swap! inventory assoc n (- (get @inventory n) 1))
                            (println "You caught a Buff-Rock!"))))
                      "Aww! You didn't catch the Buff-Rock! Toss another Pokeball?")
                    (= (get @*game* :pokemon) water-snek)
                    (if (<= (rand-int 9) 8)
                      (do
                        (assoc @*game* :pokemon nil)
                        (if (<= (get @inventory n) 0)
                          "You are out of Pokeballs!"
                          (do
                            (if (contains? @pokedex "Water-Snek")
                              (swap! pokedex assoc "Water-Snek" (+ (get @pokedex "Water-Snek") 1))
                              (swap! pokedex conj ["Water-Snek" 1]))
                            (swap! inventory assoc n (- (get @inventory n) 1))
                            (println "You caught a Water-Snek!"))))
                      "Aww! You didn't catch the Water-Snek! Toss another Pokeball?")
                    (= (get @*game* :pokemon) skinny-lizard)
                    (do
                      (assoc @*game* :pokemon nil)
                      (if (<= (get @inventory n) 0)
                        "You are out of Pokeballs!"
                        (do
                          (if (contains? @pokedex "Skinny-Lizard")
                            (swap! pokedex assoc "Skinny-Lizard" (+ (get @pokedex "Skinny-Lizard") 1))
                            (swap! pokedex conj ["Skinny-Lizard" 1]))
                          (swap! inventory assoc n (- (get @inventory n) 1))
                          (println "You caught a Skinny-Lizard!"))))
                    (= (get @*game* :pokemon) large-lizard)
                    (if (<= (rand-int 9) 7)
                      (do
                        (assoc @*game* :pokemon nil)
                        (if (<= (get @inventory n) 0)
                          "You are out of Pokeballs!"
                          (do
                            (if (contains? @pokedex "Large-Lizard")
                              (swap! pokedex assoc "Large-Lizard" (+ (get @pokedex "Large-Lizard") 1))
                              (swap! pokedex conj ["Large-Lizard" 1]))
                            (swap! inventory assoc n (- (get @inventory n) 1))
                            (println "You caught a Large-Lizard!"))))
                      "Aww! You didn't catch the Large-Lizard. Toss another Pokeball?")
                    :else "You cannot catch this.")
              (= current-location "Route104")
              (cond (= (get @*game* :pokemon) sparkly-snowflake)
                    (if (<= (rand-int 9) 7)
                      (do
                        (assoc @*game* :pokemon nil)
                        (if (<= (get @inventory n) 0)
                          "You are out of Pokeballs!"
                          (do
                            (if (contains? @pokedex "Sparkly-Snowflake")
                              (swap! pokedex assoc "Sparkly-Snowflake" (+ (get @pokedex "Sparkly-Snowflake") 1))
                              (swap! pokedex conj ["Sparkly-Snowflake" 1]))
                            (swap! inventory assoc n (- (get @inventory n) 1))
                            (println "You caught a Sparkly-Snowflake!"))))
                      "Aww! You didn't catch the Sparkly-Snowflake! Toss another Pokeball?")
                    (= (get @*game* :pokemon) scary-ghost)
                    (do
                      (assoc @*game* :pokemon nil)
                      (if (<= (get @inventory n) 0)
                        "You are out of Pokeballs!"
                        (do
                          (if (contains? @pokedex "Scary-Ghost")
                            (swap! pokedex assoc "Scary-Ghost" (+ (get @pokedex "Scary-Ghost") 1))
                            (swap! pokedex conj ["Scary-Ghost" 1]))
                          (swap! inventory assoc n (- (get @inventory n) 1))
                          (println "You caught a Scary-Ghost!"))))
                    (= (get @*game* :pokemon) bye-felicia)
                    (do
                      (assoc @*game* :pokemon nil)
                      (if (<= (get @inventory n) 0)
                        "You are out of Pokeballs!"
                        (do
                          (if (contains? @pokedex "Bye-Felicia")
                            (swap! pokedex assoc "Bye-Felicia" (+ (get @pokedex "Bye-Felicia") 1))
                            (swap! pokedex conj ["Bye-Felicia" 1]))
                          (swap! inventory assoc n (- (get @inventory n) 1))
                          (println "You caught a Bye-Felicia!"))))
                    (= (get @*game* :pokemon) fire-dragon)
                    (if (<= (rand-int 9) 2)
                      (do
                        (assoc @*game* :pokemon nil)
                        (if (<= (get @inventory n) 0)
                          "You are out of Pokeballs!"
                          (do
                            (if (contains? @pokedex "Fire-Dragon")
                              (swap! pokedex assoc "Fire-Dragon" (+ (get @pokedex "Fire-Dragon") 1))
                              (swap! pokedex conj ["Fire-Dragon" 1]))
                            (swap! inventory assoc n (- (get @inventory n) 1))
                            (println "You caught a Fire-Dragon!"))))
                      "Aww! You didn't catch the Fire-Dragon! Toss another Pokeball?")
                    (= (get @*game* :pokemon) squeaky-elevator)
                    (do
                      (assoc @*game* :pokemon nil)
                      (if (<= (get @inventory n) 0)
                        "You are out of Pokeballs!"
                        (do
                          (if (contains? @pokedex "Squeaky-Elevator")
                            (swap! pokedex assoc "Squeaky-Elevator" (+ (get @pokedex "Squeaky-Elevator") 1))
                            (swap! pokedex conj ["Squeaky-Elevator" 1]))
                          (swap! inventory assoc n (- (get @inventory n) 1))
                          (println "You caught a Squeaky-Elevator!"))))
                    (= (get @*game* :pokemon) poisonous-ghost)
                    (if (<= (rand-int 9) 6)
                      (do
                        (assoc @*game* :pokemon nil)
                        (if (<= (get @inventory n) 0)
                          "You are out of Pokeballs!"
                          (do
                            (if (contains? @pokedex "Poisonous-Ghost")
                              (swap! pokedex assoc "Poisonous-Ghost" (+ (get @pokedex "Poisonous-Ghost") 1))
                              (swap! pokedex conj ["Poisonous-Ghost" 1]))
                            (swap! inventory assoc n (- (get @inventory n) 1))
                            (println "You caught a Poisonous-Ghost!"))))
                      "Aww! You didn't catch the Poisonous-Ghost! Toss another Pokeball?")
                    (= (get @*game* :pokemon) vicious-chipmunk)
                    (if (<= (rand-int 9) 7)
                      (do
                        (assoc @*game* :pokemon nil)
                        (if (<= (get @inventory n) 0)
                          "You are out of Pokeballs!"
                          (do
                            (if (contains? @pokedex "Vicious-Chipmunk")
                              (swap! pokedex assoc "Vicious-Chipmunk" (+ (get @pokedex "Vicious-Chipmunk") 1))
                              (swap! pokedex conj ["Vicious-Chipmunk" 1]))
                            (swap! inventory assoc n (- (get @inventory n) 1))
                            (println "You caught a Vicious-Chipmunk!"))))
                      "Aww! You didn't catch the Vicious-Chipmunk! Toss another Pokeball?")
                    :else "You cannot catch that.")
              :else "You cannot catch things here.")
        :else "You cannot toss that."))

;; Include Great Balls and Ultra Balls?
;; Look at buy function -- why does Pokedollars stay as 500?

(defn enter [n]
  (let [x (get @*game* :X)
        y (get @*game* :Y)]
    (cond (and (= current-location "Town1") (= town-location "outside"))
          (cond (and (and (= x 2) (= y 6)) (= n "House1.1"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in House1.1.")
                  (House11-map))
                (and (and (= x 8) (= y 6)) (= n "House1.2"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in House1.2.")
                  (House12-map))
                (and (and (= x 2) (= y 2)) (= n "Lab"))
                (do (println "The professor is out of town and has locked the doors."))
                (and (and (= x 8) (= y 2)) (= n "Home"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in your home.")
                  (Home-map))
                (and (and (= x 5) (= y 10)) (= n "Route101"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are now in Route101. Beware of wild Pokemon in the tall grass!")
                  (Route101-map))
                :else "There is nothing to enter here.")
          (and (= current-location "Route101") (= town-location "tall-grass"))
          (cond (and (and (= x 5) (= y 10)) (= n "Town1"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are back in Town1.")
                  (Town1-map))
                (and (and (= x 5) (= y 16)) (= n "Town2"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are now in Town2.")
                  (Town2-map))
                :else "There is nothing to enter here.")
          (and (= current-location "Town2") (= town-location "outside"))
          (cond (and (and (= x 5) (= y 16)) (= n "Route101"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are back in Route101.")
                  (Route101-map))
                (and (and (= x 3) (= y 24)) (= n "Gym2"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in Town2's Gym.")
                  (Gym2-map))
                (and (and (= x 8) (= y 24)) (= n "House2.1"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in House2.1.")
                  (House21-map))
                (and (and (= x 8) (= y 18)) (= n "House2.2"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in House2.2.")
                  (House22-map))
                (and (and (= x 10) (= y 24)) (= n "PokemonCenter2"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in Town2's Pokemon Center.")
                  (PokemonCenter2-map))
                (and (and (= x 10) (= y 18)) (= n "PokeMart2"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in Town2's PokeMart.")
                  (PokeMart2-map))
                (and (and (= x 5) (= y 24)) (= n "Route102"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are now in Route102.")
                  (Route102-map))
                :else "There is nothing to enter here.")
          (and (= current-location "Route102") (= town-location "tall-grass"))
          (cond (and (and (= x 5) (= y 24)) (= n "Town2"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are back in Town2.")
                  (Town2-map))
                (and (and (= x 13) (= y 28)) (= n "Town3"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are now in Town3.")
                  (Town3-map))
                :else "There is nothing to enter here.")
          (and (= current-location "Town3") (= town-location "outside"))
          (cond (and (and (= x 13) (= y 28)) (= n "Route102"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are back in Route102.")
                  (Route102-map))
                (and (and (= x 16) (= y 30)) (= n "PokemonCenter3"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in Town3's Pokemon Center.")
                  (PokemonCenter3-map))
                (and (and (= x 34) (= y 30)) (= n "Gym3"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in Town3's Gym.")
                  (Gym3-map))
                (and (and (= x 52) (= y 30)) (= n "PokeMart3"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in Town3's PokeMart.")
                  (PokeMart3-map))
                (and (and (= x 14) (= y 24)) (= n "House3.1"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in House3.1.")
                  (House31-map))
                (and (and (= x 22) (= y 24)) (= n "House3.2"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in House3.2.")
                  (House32-map))
                (and (and (= x 30) (= y 24)) (= n "House3.3"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in House3.3.")
                  (House33-map))
                (and (and (= x 38) (= y 24)) (= n "House3.4"))
                "The door is locked..."
                (and (and (= x 46) (= y 24)) (= n "House3.5"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in House3.5.")
                  (House35-map))
                (and (and (= x 52) (= y 24)) (= n "House3.6"))
                "The door is locked..."
                (and (and (= x 14) (= y 16)) (= n "House3.7"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in House3.7.")
                  (House37-map))
                (and (and (= x 22) (= y 16)) (= n "House3.8"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in House3.8.")
                  (House38-map))
                (and (and (= x 30) (= y 16)) (= n "House3.9"))
                "The door is locked..."
                (and (and (= x 38) (= y 16)) (= n "House3.10"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in House3.10.")
                  (House310-map))
                (and (and (= x 46) (= y 16)) (= n "House3.11"))
                "The door is locked..."
                (and (and (= x 52) (= y 16)) (= n "House3.12"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in House3.12.")
                  (House312-map))
                (and (and (= x 53) (= y 28)) (= n "Route103"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are now in Route103, the path away from civilization...")
                  (Route103-map))
                :else "There is nothing to enter here.")
          (and (= current-location "Route103") (= town-location "tall-grass"))
          (cond (and (and (= x 53) (= y 28)) (= n "Town3"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are back in Town3 and back in civilization.")
                  (Town3-map))
                (and (and (= x 55) (= y 20)) (= n "Town4"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are now in Town4, the ghost town.")
                  (Town4-map))
                :else "There is nothing to enter here.")
          (and (= current-location "Town4") (= town-location "outside"))
          (cond (and (and (= x 55) (= y 20)) (= n "Route103"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are back in Route103, the path to civilization.")
                  (Route103-map))
                (and (and (= x 50) (= y 18)) (= n "HauntedHouse"))
                "The door is locked, and a sign reads, 'The HauntedHouse is closed to the public for presenting dangerous situations to visitors. Sorry for the inconvenience."
                (and (and (= x 60) (= y 18)) (= n "ScaryHouse"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in the ScaryHouse.")
                  (ScaryHouse-map))
                (and (and (= x 55) (= y 13)) (= n "PokemonCenter4"))
                (do
                  (alter-var-root #'town-location (constantly n))
                  (println "You are now in Town4's Pokemon Center.")
                  (PokemonCenter4-map))
                (and (and (= x 63) (= y 20)) (= n "Route104"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are now in Route104, the path to a mysterious place...")
                  (Route104-map))
                :else "There is nothing to enter here.")
          (and (= current-location "Route104") (= town-location "tall-grass"))
          (cond (and (and (= x 63) (= y 20)) (= n "Town4"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are back in Town4.")
                  (Town4-map))
                (and (and (= x 74) (= y 28)) (= n "Town5"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are now in the most mysterious town, Town5.")
                  (Town5-map))
                :else "There is nothing to enter here.")
          (and (= current-location "Town5") (= town-location "outside"))
          (cond (and (and (= x 74) (= y 28)) (= n "Route104"))
                (do
                  (alter-var-root #'current-location (constantly n))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are back in Route104.")
                  (Route104-map))
                :else "There is nothing to enter here.")
          :else "You are in the wrong town.")))

(defn outside-message []
  (println "You are back outside."))

(defn exit [n]
  (let [x (get @*game* :X)
        y (get @*game* :Y)]
    (cond (= current-location "Town1") 
          (cond (and (= town-location "House1.1") (= n "House1.1"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "House1.2") (= n "House1.2"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "Home") (= n "Home"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (and (= x 5) (= y 10)) (= n "Town1"))
                (do
                  (alter-var-root #'current-location (constantly "Route101"))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are now in Route101. Beware of Pokemon in the tall grass!"))
                :else "There is nothing to exit from.")
          (= current-location "Route101")
          (cond (and (and (= x 5) (= y 16)) (= n "Route101"))
                (do
                  (alter-var-root #'current-location (constantly "Town2"))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are now in Town2."))
                (and (and (= x 5) (= y 10)) (= n "Route101"))
                (do
                  (alter-var-root #'current-location (constantly "Town1"))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are back in Town1."))
                :else "There is nothing to exit from.")
          (= current-location "Town2")
          (cond (and (= town-location "Gym2") (= n "Gym2"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "House2.1") (= n "House2.1"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "House2.2") (= n "House2.2"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "PokemonCenter2") (= n "PokemonCenter2"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "PokeMart2") (= n "PokeMart2"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (and (= x 5) (= y 16)) (= n "Town2"))
                (do
                  (alter-var-root #'current-location (constantly "Route101"))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are now in Route101. Beware of wild Pokemon in the tall-grass!"))
                (and (and (= x 5) (= y 24)) (= n "Town2"))
                (do
                  (alter-var-root #'current-location (constantly "Route102"))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are now in Route102."))
                :else "There is nothing to exit from.")
          (= current-location "Route102")
          (cond (and (and (= x 5) (= y 24)) (= n "Route102"))
                (do
                  (alter-var-root #'current-location (constantly "Town2"))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are back in Town2."))
                (and (and (= x 13) (= y 28)) (= n "Route102"))
                (do
                  (alter-var-root #'current-location (constantly "Town3"))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are now in Town3, the largest town!"))
                :else "There is nothing to exit from.")
          (= current-location "Town3")
          (cond (and (= town-location "PokemonCenter3") (= n "PokemonCenter3"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "Gym3") (= n "Gym3"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "PokeMart3") (= n "PokeMart3"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "House3.1") (= n "House3.1"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "House3.2") (= n "House3.2"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "House3.3") (= n "House3.3"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "House3.5") (= n "House3.5"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "House3.7") (= n "House3.7"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "House3.8") (= n "House3.8"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "House3.10") (= n "House3.10"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "House3.12") (= n "House3.12"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (and (= x 13) (= y 28)) (= n "Town3"))
                (do
                  (alter-var-root #'current-location (constantly "Route102"))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are back in Route102."))
                (and (and (= x 53) (= y 28)) (= n "Town3"))
                (do
                  (alter-var-root #'current-location (constantly "Route103"))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are now in Route103, the path away from civilization..."))
                :else "There is nothing to exit from.")
          (= current-location "Route103")
          (cond (and (and (= x 53) (= y 28)) (= n "Route103"))
                (do
                  (alter-var-root #'current-location (constantly "Town3"))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are now in Town3, the largest town!"))
                (and (and (= x 55) (= y 20)) (= n "Route103"))
                (do
                  (alter-var-root #'current-location (constantly "Town4"))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are now in Town4, the ghost town..."))
                :else "There is nothing to exit from.")
          (= current-location "Town4")
          (cond (and (= town-location "ScaryHouse") (= n "ScaryHouse"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (= town-location "PokemonCenter4") (= n "PokemonCenter4"))
                (do
                  (alter-var-root #'town-location (constantly "outside"))
                  (outside-message))
                (and (and (= x 55) (= y 20)) (= n "Town4"))
                (do
                  (alter-var-root #'current-location (constantly "Route103"))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are back in Route103, the path to civilization."))
                (and (and (= x 63) (= y 20)) (= n "Town4"))
                (do
                  (alter-var-root #'current-location (constantly "Route104"))
                  (alter-var-root #'town-location (constantly "tall-grass"))
                  (println "You are in Route104, the path to a mystery..."))
                :else "There is nothing to exit from.")
          (= current-location "Route104")
          (cond (and (and (= x 63) (= y 20)) (= n "Route104"))
                (do
                  (alter-var-root #'current-location (constantly "Town4"))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are back in Town4."))
                (and (and (= x 74) (= y 28)) (= n "Route104"))
                (do
                  (alter-var-root #'current-location (constantly "Town5"))
                  (alter-var-root #'town-location (constantly "outside"))
                  (println "You are now in Town5, the most mysterious place..."))
                :else "There is nothing to exit from.")
          (= current-location "Town5")
          (if (and (and (= x 74) (= y 28)) (= n "Town5"))
            (do
              (alter-var-root #'current-location (constantly "Route104"))
              (alter-var-root #'town-location (constantly "Route104"))
              (println "You are back in Route104."))
            "There is nothing to exit from.")
          :else "There is nothing to exit from.")))

;; Interacting with NPC's
(defn talk-to [n]
  (cond (= current-location "Town1")
        (cond (= town-location "House1.1")
              (cond (= n "Man") "Man: Town1 is such a peaceful place to be!"
                    (= n "Woman") "Woman: My husband really loves this place for how peaceful it is. But sometimes I wish I could see some adventure..."
                    :else "You cannot talk to that.")
              (= town-location "House1.2")
              (cond (= n "Woman1") "Woman: I love sitting at home and watching TV with my best friend!"
                    (= n "Woman2") "Woman: She's my best friend. I love that she loves TV shows but sometimes I wish she could be motivated to do something else for once..."
                    :else "You cannot talk to that.")
              (= town-location "Home")
              (if (= n "Mom") "Mom: Before you go off and act dumb, let me give you some advice. Make sure to pass through the entrances and exits of the towns and routes, or else you'll end up in a second dimension that you can't escape from. I don't want that happening to you. Next, I heard from the professor, who's out of town right now, about your Pokedex, and he said you have 20 Pokemon to catch to complete your Pokedex. Finally, don't forget to change your underwear everyday!! Hygiene, okay?"
                  "You cannot talk to that.")
              :else "You cannot talk to that here.")
        (= current-location "Town2")
        (cond (= town-location "Gym2")
              (cond (= n "PokemonTrainer1") "Trainer: I admired Leader Bob ever since I was a kid! :D"
                    (= n "PokemonTrainer2") "Trainer: Leader Bob is amazing with Pokemon!"
                    (= n "PokemonTrainer3") "Trainer: Leader Bob taught me how to interact with all kinds of Pokemon. I'm truly greateful for that."
                    (= n "PokemonTrainer4") "Trainer: Leader Bob is the man!!!!!"
                    (= n "PokemonTrainer5") "Trainer: I can't believe Leader Bob is so young but so intelligent!!"
                    (= n "PokemonTrainer6") "Trainer: Leader Bob is the most amazing guy I've ever known!"
                    (= n "LeaderBob") "Bob: I see that you're working to complete your Pokedex. Well, Route102 has a ton of Pokemon worth catching! Watch out for Crazy-Rat, though. They can be quite troublesome." 
                    :else "You cannot talk to that.")
              (= town-location "House2.1")
              (if (= n "Man") "Man: Town2 is such a great place to live, and the Gym Leader, Bob, is an extremely nice young man. How lucky I am!"
                  "You cannot talk to that.")
              (= town-location "PokemonCenter2")
              (cond (= n "Nurse Joy") "Welcome to Town2's Pokemon Center! We're known for our friendliness and intimacy with Pokemon."
                    (= n "Man") "I'm actually from Town1, but I really love Town2!"
                    (= n "Woman") "I definitely love Town2 more than Town3 because Town3 is always too crowded!"
                    :else "You cannot talk to that.")
              (= town-location "House2.2")
              (cond (= n "Woman1") "Woman: Aren't flowers just lovely?"
                    (= n "Woman2") "Woman: I'm always so tired... But looking at flowers just brightens my day!"
                    :else "You cannot talk to that.")
              (= town-location "PokeMart2")
              (cond (= n "StoreOwner2") "Welcome! I've got a number of items in store. What would you like?
Pokeballs 100P/ea
Great-Balls 200P/ea
Flowers 50P
Box of food 250P"
                    (= n "Man")
                    (do
                      (println "Hey! I take it it's your first time here in Town2. Lemme give you a little gift!")
                      (swap! *game* assoc (+ (get @*game* :pokedollars) 100))
                      (println "You received 100P!"))
                    (= n "Woman") "I love shopping!"
                    :else "You cannot talk to that.")
              :else "You cannot talk to that here.")
        (= current-location "Town3")
        (cond (= town-location "PokemonCenter3")
              (cond (= n "Nurse Joy") "Nurse Joy: Welcome to Town3's Pokemon Center! We're known for our urban environment and awesome PokeMart!"
                    (= n "Man") "Man: Nurse Joy is so pretty... I heard she has a twin or something in Town2."
                    (= n "Woman") "Woman: When I was a kid, I really wanted to go around and catch Pokemon. But now, I'm happy here because it's always so lively!"
                    (= n "Boy") "Boy: I wanna catch 'em all!! Ahahahaha!!! But I'm not that motivated..."
                    (= n "Girl") "Girl: Ok but the shopping center here is just soooo amazing. I feel like that I tell that to everyone, but I just can't get over it!!"
                    :else "You cannot talk to that.")
              (= town-location "Gym3")
              (cond (= n "PokemonTrainer1") "Trainer: Leader Mary is so kind and pretty!"
                    (= n "PokemonTrainer2") "Trainer: I heard Leader Bob in Town2 had a crush on Leader Mary... I mean I don't blame him! She's gorgeous!"
                    (= n "PokemonTrainer3") "Trainer: Everyone here seems to have a crush on Mary, but I just admire her..."
                    (= n "PokemonTrainer4") "Trainer: Leader Mary taught me how to be confident in myself."
                    (= n "PokemonTrainer5") "Trainer: Whenever people ask me why I love Leader Mary, I think I could talk for a whole month!"
                    (= n "PokemonTrainer6") "Trainer: I just wanna fight Brogs! While training under Leader Mary!! Yeah!!!"
                    (= n "PokemonTrainer7") "Trainer: Leader Mary may have a pretty face but her training is no joke!!"
                    (= n "PokemonTrainer8") "Trainer: Whenever I try asking Leader Mary about Leader Bob, she gets all crabby..."
                    (= n "PokemonTrainer9") "Trainer: I aspire to be as strong and as amazing as Leader Mary!"
                    (= n "PokemonTrainer10") "Trainer: Leader Mary told me that she doesn't like talking about Leader Bob because she wants to focus on honing her skills, and because she wants her students to focus on training, not gossiping."
                    (= n "LeaderMary") "Mary: I want all my students to be confident but always kind to Pokemon. Well, I hope everyone can be that!"
                    :else "You cannot talk to that.")
              (= town-location "PokeMart3")
              (cond (= n "StoreOwner3") "Welcome! What would you like to buy?
Pokeball 100P/ea
Great-Ball 200P/ea
Ultra-Ball 300P/ea
Cough Medicine 200P/bottle
Box of food 250P
Drink 50P/bottle
Flowers 50P
Backpack 150P/ea"
                    (= n "Man1") "Man: The store owner here is a really nice guy."
                    (= n "Man2") "Man: The store is always so crowded because of all the stuff you can buy!"
                    (= n "Man3") "Man: Hmm... What do I need to buy again...?"
                    (= n "Woman1") "Woman: I often go shopping here because of all the things I can buy."
                    (= n "Woman2") "Woman: Everyone says the items here are amazing, but I guess my expectations were too high..."
                    (= n "Boy1") "Boy: There's so many types of Pokeballs I can buy here!"
                    (= n "Boy2") "Boy: I learned the hard way that some items here are good for catching Pokemon and some aren't..."
                    :else "You cannot talk to that.")
              (= town-location "House3.1")
              (cond (= n "Man") "Man: It's pretty noisy in the residential area, but it's not bad."
                    (= n "Woman") "Woman: Our son went off to catch Pokemon, and he seems very happy about it."
                    :else "You cannot talk to that.")
              (= town-location "House3.2")
              (if (= n "Man") "Man: It's always very lively here, even in the residential area. Sometimes it gets a bit rowdy though..."
                  "You cannot talk to that.")
              (= town-location "House3.3")
              (cond (= n "Man") "Man: My son really wants to be a Pokemon Trainer under Leader Mary, but I don't think he has the discipline for it..."
                    (= n "Boy") "Boy: I really, really want to train under Leader Mary! Everybody tells me she's really awesome!!"
                    :else "You cannot talk to that.")
              (= town-location "House3.5")
              (cond (= n "Girl1") "Girl: My best friend is training under Leader Mary, but she isn't as hardcore as the ones always at the Gym..."
                    (= n "Girl2") "Girl: Even though I'm not that crazy about becoming a Pokemon Trainer, I love being under Leader Mary's mentorship!"
                    :else "You cannot talk to that.")
              (= town-location "House3.7")
              (if (= n "Woman")
                (do
                  (println "Woman: My husband was a Pokemon Trainer, but he was killed by a feral Pokemon. However, he absolutely loved it, and I guess I've made it my life choice to serve other deserving Pokemon Trainers. You seem like someone determined to achieve something. Here's a gift for you.")
                  (swap! *game* assoc :pokedollars (+ (get @*game* :pokedollars) 200))
                  (println "You received 200P!"))
                  "You cannot talk to that.")
              (= town-location "House3.8")
              (cond (= n "Man") "Man: Wow, Pokemon Trainers in this town are truly intense!"
                    (= n "Woman") "Woman: We live next to a Pokemon Trainer, and he's always out of his house. However, at night all kinds of sounds come from his house. Wonder what kind of training he's up to..."
                    :else "You cannot talk to that.")
              (= town-location "House3.10")
              (cond (= n "Woman") "Woman: I can't believe I live between two Pokemon Trainers! They're so dang loud at night!"
                    (= n "Girl") "Girl: Mommy always complains about the Pokemon Trainers... But I thnk they're kinda cool... Don't tell Mommy, though!"
                    :else "You cannot talk to that.")
              (= town-location "House3.12")
              (cond (= n "Man") "Man: Ah, great to be done with shopping. That place is always packed!"
                    (= n "Boy") "Boy: My dad really hates shopping."
                    :else "You cannot talk to that.")
              :else "You cannot talk to that here.")
        (= current-location "Town4")
        (cond (= town-location "ScaryHouse")
              (if (= n "Man")
                (do
                  (println "Man: BOOOO! Whoa, you're not scared! You're a tough one. Here's your prize.")
                  (swap! *game* assoc :pokedollars (+ (get @*game* :pokedollars) 300))
                  (println "You received 300P from the weird man!"))
                "You cannot talk to that.")
              (= town-location "PokemonCenter4")
              (cond (= n "Nurse Joy") "Nurse Joy: Welcome to Town4, the ghost town! We're known for being a place to avoid, so having a visitor is always a great feeling!"
                    (= n "Man") "Man: This place is extremely creepy but extremely interesting, especially the HauntedHouse. A lot of strange things happened there, and those things are fun to read about."
                    :else "You cannot talk to that.")
              :else "You cannot talk to that here.")
        (= current-location "Town5")
        (if (= n "FinalBoss") "Congratulations, you have reached the end of the game."
            "You cannot talk to that.")
        :else "You cannot talk to that here."))
              
;; Inventory
(defn menu []
  (println "
Type one of the following:
(money)
(party)
(items)
To exit out, move in any direction or type another command.
"))

(defn money []
  (let [m (get @*game* :pokedollars)]
  (println "You have" m "Pokedollars left.")))

(defn party []
  (println "
Using: Water-Turtle
HP: 18
Attack Damage: 9
"))

(def pokemon-items {
                    "Pokeball" {:price 100 :description "It is a decent capturing method with decent probabilities of catching Pokemon."}
                    "Great-Ball" {:price 200 :descrption "It has a higher probability of catching Pokemon than Pokeballs."}
                    "Ultra-Ball" {:price 300 :description "It has a higher probability of catching Pokemon than Great-Balls."}})

(defn after-purchase []
  (do (println "Thanks! Would you like anything else?")))

(defn buy [n]
  (let [m (get @*game* :pokedollars)]
  (cond (= town-location "PokeMart2")
        (cond (= n "Pokeball")
              (do
                (if (contains? @inventory n)
                  (swap! inventory assoc n (+ (get @inventory n) 1))
                  (swap! inventory conj [n 1]))
                (assoc @*game* :pokedollars (- m 100))
                (after-purchase))
              (= n "Great-Ball")
              (do
                (if (contains? @inventory n)
                  (swap! inventory assoc n (+ (get @inventory n) 1))
                  (swap! inventory conj [n 1]))
                (assoc *game* :pokedollars (- m 200))
                (after-purchase))
              (= n "Flowers")
              (do
                (if (contains? @inventory n)
                  (swap! inventory assoc n (+ (get @inventory n) 1))
                  (swap! inventory conj [n 1]))
                (assoc *game* :pokedollars (- m 50))
                (after-purchase))
              (= n "Box of food")
              (do
                (if (contains? @inventory n)
                  (swap! inventory assoc n (+ (get @inventory n) 1))
                  (swap! inventory conj [n 1]))
                (assoc *game* :pokedollars (- m 250))
                (after-purchase))
              :else "Sorry, we don't sell that here. 
Pokeball 100P/ea
Great-Ball 200P/ea
Flowers 50P
Box of food 250P")
        (= town-location "PokeMart3")
        (cond (= n "Pokeball")
              (do
                (if (contains? @inventory n)
                  (swap! inventory assoc n (+ (get @inventory n) 1))
                  (swap! inventory conj [n 1]))
                (assoc *game* :pokedollars (- m 100))
                (after-purchase))
              (= n "Great-Ball")
              (do
                (if (contains? @inventory n)
                  (swap! inventory assoc n (+ (get @inventory n) 1))
                  (swap! inventory conj [n 1]))
                (assoc *game* :pokedollars(- m 200))
                (after-purchase))
              (= n "Ultra-Ball")
              (do
                (if (contains? @inventory n)
                  (swap! inventory assoc n (+ (get @inventory n) 1))
                  (swap! inventory conj [n 1]))
                (assoc *game* :pokedollars (- m 300))
                (after-purchase))
              (= n "Cough-Medicine")
              (do
                (if (contains? @inventory n)
                  (swap! inventory assoc n (+ (get @inventory n) 1))
                  (swap! inventory conj [n 1]))
                (assoc *game* :pokedollars(- m 200))
                (after-purchase))
              (= n "Box of food")
              (do
                (if (contains? @inventory n)
                  (swap! inventory assoc n (+ (get @inventory n) 1))
                  (swap! inventory conj [n 1]))
                (assoc *game* :pokedollars (- m 250))
                (after-purchase))
              (= n "Drink")
              (do
                (if (contains? @inventory n)
                  (swap! inventory assoc n (+ (get @inventory n) 1))
                  (swap! inventory conj [n 1]))
                (assoc *game* :pokedollars(- m 50))
                (after-purchase))
              (= n "Flowers")
              (do
                (if (contains? @inventory n)
                  (swap! inventory assoc m (+ (get @inventory n) 1))
                  (swap! inventory conj [n 1]))
                (assoc *game* :pokedollars (- m 50))
                (after-purchase))
              (= n "Backpack")
              (do
                (if (contains? @inventory n)
                  (swap! inventory assoc n (+ (get @inventory n) 1))
                  (swap! inventory conj [n 1]))
                (assoc *game* :pokedollars (- m 150))
                (after-purchase))
              :else "Sorry, we don't sell that here.
Pokeball 100P/ea
Great-Ball 200P/ea
Ultra-Ball 300P/ea
Cough Medicine 200P/bottle
Box of food 250P
Drink 50P/bottle
Flowers 50P
Backpack 150P/ea")
        :else "There is nothing being sold here.")))
            
(start)
