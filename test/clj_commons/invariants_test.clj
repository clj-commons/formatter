(ns clj-commons.invariants-test
  (:require [clojure.test :refer :all]
            [clj-commons.formatter :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.clojure-test :refer [defspec]]))

(defn print-simply [x]
  (if (string? x)
    x
    (pr-str x)))



(defn repeat-str [n s]
  (apply str (repeat n s)))

(def non-zero-whitespace-generator
  (gen/fmap #(repeat-str % " ") (gen/fmap inc gen/nat)))

(def whitespace-generator
  (gen/fmap #(repeat-str % " ") gen/nat))

(def newline-gen
  (gen/fmap #(repeat-str % "\n") (gen/fmap #(+ 2 %) gen/nat)))

(defn bad-formatting-gen [tokens]
  (gen/fmap
    (fn [whitespaces]
      (apply str (interleave tokens whitespaces)))
    ;; TODO: generate zero-whitespace on boundaries of some tokens
    (gen/vector non-zero-whitespace-generator (count tokens))))

(def def-gen
  "Generates def's, with and without docstrings."
  (gen/fmap (fn [[name docstring value]]
              (filter some?
                      (list 'def name docstring value)))
            (gen/tuple gen/symbol
                       (gen/one-of [(gen/return nil) gen/string])
                       gen/simple-type)))

(def defn-gen
  "Generates defn's"
  (gen/fmap (fn [[name docstring args body]]
              (filter some?
                      (apply list 'defn name docstring args body)))
            (gen/tuple gen/symbol
                       (gen/one-of [(gen/return nil) gen/string])
                       (gen/vector gen/symbol)
                       (gen/tuple gen/any))))

(def form-gen (gen/one-of [def-gen defn-gen]))

(def code-gen
  (gen/fmap
    (fn [forms-and-newlines]
      (->> (mapcat identity forms-and-newlines)
           (map print-simply)
           (apply str)))
    (gen/vector (gen/tuple form-gen newline-gen))))


;; Start with components: ["(" "def" "a" "5" ")"]
;; Interpose with variable amounts of whitespace (should also allow 0 spaces between parens, brackets, braces, @, "",

(defspec whitespace-on-line-doesnt-affect-formatting
         10
         (prop/for-all [s (bad-formatting-gen ["(" "def" "a" "5" ")"])]
           (= (format-str s)
              "(def a 5)")))

(defspec newlines-between-forms-doesnt-affect-formatting
         100
         (prop/for-all [strs (gen/bind (gen/vector form-gen)
                                    (fn [forms]
                                      (gen/not-empty
                                        (gen/vector
                                          (gen/fmap
                                            (fn [newlines]
                                              (->> (interleave forms newlines)
                                                   (map print-simply)
                                                   (apply str)))
                                            (gen/vector newline-gen (count forms)))))))]
           (apply = (map format-str strs))))

(defspec formatting-is-stable
         100
         (prop/for-all [code code-gen]
           (= (format-str code)
              (format-str (format-str code)))))

(defspec exactly-same-tokens-exist-after-formatting
         100
         (prop/for-all [code code-gen]
           (= (tokens code)
              (tokens (format-str code)))))

(defspec there-should-always-be-a-single-trailing-newline
         50
         (prop/for-all [code code-gen]
           ;; TODO: generate code with 0 and 1 trailing newlines
           (single-trailing-newline? (format-str code))))
