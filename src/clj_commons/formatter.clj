(ns clj-commons.formatter
  (:require [clojure.string :as string]))

(defn format-str [s]
  (str (string/trimr s) \newline))

(defn tokens [s]
  ;; TODO: only reads the first object, we need to read all
  (read-string {:eof nil} s))

(defn single-trailing-newline? [s]
  (or (string/blank? s)
      (and (= (.charAt s (dec (count s))) \newline)
           (not= (.charAt s (dec (dec (count s)))) \newline))))
