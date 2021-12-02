(ns day-1.core
  (:require [clojure.string :as str])
  (:gen-class))

(def problem-input [199 200 208 210 200 207 240 269 260 263]) ;for testing porpouse only

(defn depth-increases [{:keys [count last-depth]} depth]
  (if (= -1 last-depth)
    {:count 0 :last-depth depth}
    (if (> depth last-depth)
      {:count (+ count 1) :last-depth depth}
      {:count count :last-depth depth})))

(defn count-increasing-windows [ measurements ]
  (let [ windows (partition 3 1 measurements)
        windows-sums (vec (map (fn [window] (reduce (fn [measure sum] (+ sum measure)) window)) windows))]
    (:count (reduce window-depth-increasing {:count 0 :last-window-sum -1} windows-sums))))

(defn window-depth-increasing [{:keys [count last-window-sum]} window-sum]
    (if (= -1 last-window-sum)
      {:count 0 :last-window-sum window-sum}
      (if (> window-sum last-window-sum)
        {:count (+ count 1) :last-window-sum window-sum}
        {:count count :last-window-sum window-sum})))

(defn -main
  "First exercise of advent of code 2021"
  [& args]
  (let [input-raw (slurp "input.txt")
        input (int-array (map #(Integer/parseInt %)(str/split-lines input-raw)))
        result-part-one (:count (reduce depth-increases {:count 0 :last-depth -1} input))
        result-part-two (count-increasing-windows input)]
    (println "Result part 1: " result-part-one)
    (println "Result part 2: " result-part-two)))
