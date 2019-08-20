(ns clj-commons.formatter-test
  (:require [clojure.test :refer :all]
            [clj-commons.formatter :refer [single-trailing-newline?]]))

(deftest single-trailing-newline?-test
  (is (single-trailing-newline? ""))
  (is (single-trailing-newline? "5\n"))
  (is (single-trailing-newline? "5\n\n\n5\n"))
  (is (not (single-trailing-newline? "5\n\n"))))
