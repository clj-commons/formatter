(ns clj-commons.whitespace-test
  (:require [clojure.test :refer :all]))

(deftest fix-indentation
  (is (= (format-str "  (  def a 6) ")
         "(def a 5")))
