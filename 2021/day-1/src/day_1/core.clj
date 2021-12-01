(ns day-1.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn depth-increases [{:keys [count last-depth]} depth]
  (if (> depth last-depth)
    {:count (+ count 1) :last-depth depth}
    {:count count :last-depth depth}))

(defn -main
  "First exercise of advent of code 2021"
  [& args]
  (let [input-raw (slurp "input.txt")
        input (int-array (map #(Integer/parseInt %)(str/split-lines input-raw)))
        result (reduce depth-increases {:count 0 :last-depth 0} input)]
    (println (:count result))
    (:count result)))
