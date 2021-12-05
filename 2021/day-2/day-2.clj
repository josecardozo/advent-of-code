(ns day-2)

(defn calculate-planned-course 
  "Day two exercise of advent of code 2021 - Part one"
  []
  (let [ input (clojure.string/split-lines (slurp "input.txt"))
        commands (map (fn [command] (hash-map :command (nth command 0) :units (nth command 1))) (mapv (fn [raw-command] (clojure.string/split raw-command #" ")) input))]
    (apply (fn [res] (* (:h-pos res) (:depth res))) (vector (reduce (fn [{:keys [depth h-pos]} command] (case (:command command)
                                                                                                           "up" {:depth (- depth (Integer/parseInt (:units command))) :h-pos h-pos}
                                                                                                           "down" {:depth (+ depth (Integer/parseInt (:units command))) :h-pos h-pos}
                                                                                                           "forward" {:depth depth :h-pos (+ h-pos (Integer/parseInt (:units command)))})) {:depth 0 :h-pos 0} commands))))
)

(defn calculate-planned-course 
  "Day two exercise of advent of code 2021 - Part two"
  []
  (let [ input (clojure.string/split-lines (slurp "input.txt"))
        commands (map (fn [command] (hash-map :command (nth command 0) :units (nth command 1))) (mapv (fn [raw-command] (clojure.string/split raw-command #" ")) input))]
    (apply (fn [res] (println "res: " res)(* (:h-pos res) (:depth res))) (vector (reduce (fn [{:keys [depth h-pos aim]} command]                                                                                
                                                                                             (case (:command command)
                                                                                               "up" {:aim (- aim (Integer/parseInt (:units command))) :h-pos h-pos :depth depth}
                                                                                               "down" {:aim (+ aim (Integer/parseInt (:units command))) :h-pos h-pos :depth depth}
                                                                                               "forward" {:depth (+ depth (* aim (Integer/parseInt (:units command)))) :h-pos (+ h-pos (Integer/parseInt (:units command))) :aim aim})) {:depth 0 :h-pos 0 :aim 0} commands))))
  )
