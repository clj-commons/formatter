(ns clj-commons.formatter.namespaces-test
  (:require [clojure.test :refer :all]
            [clj-commons.formatter.namespaces :refer [reformat-namespace]]))

(deftest remove-unused-parts-of-ns-forms
  (is (= (reformat-namespace '(ns a.b
                                (:require)))
         '(ns a.b)))
  (is (= (reformat-namespace '(ns a.b
                                (:use)))
         '(ns a.b)))
  (is (= (reformat-namespace '(ns a.b
                                (:import)))
         '(ns a.b)))
  (is (= (reformat-namespace '(ns a.b
                                (:load)))
         '(ns a.b)))
  (testing ":gen-class is not removed"
    (is (= (reformat-namespace '(ns a.b
                                  (:gen-class)))
           '(ns a.b
              (:gen-class))))))

(deftest deduplicate-ns-forms
  (is (= (reformat-namespace
           '(ns a.b
              (:require [x.a])
              (:require [x.b])))
         '(ns a.b
            (:require [x.a]
                      [x.b])))))
