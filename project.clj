(defproject clj-commons/formatter "0.1.0-SNAPSHOT"
  :description "A common Clojure formatter (WIP)"
  :url "https://github.com/clj-commons/formatter"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :repl-options {:init-ns clj-commons.formatter}
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.10.0"]]}})
