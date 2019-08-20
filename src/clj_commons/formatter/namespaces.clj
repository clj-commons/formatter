(ns clj-commons.formatter.namespaces)

;; Remove empty namespace parts, e.g. (:import) is removed.
;; Deduplicate multiple namespace parts (keeping in mind cljc)
;; Sort namespace declarations
;; Sort within each one, :as, :refer, :only, or whatever the right order is
;; Put first declaration on same line as :require, this matches common community usage

(defn reformat-namespace [ns-form]
  ns-form)

;; Steps:
;; Simplify
;; Reorder
;; Print
